package com.bob.wx_sell.controller;

import com.bob.wx_sell.config.ProjectUrlConfig;
import com.bob.wx_sell.constant.CookieConstant;
import com.bob.wx_sell.constant.RedisConstant;
import com.bob.wx_sell.dataobject.SellerInfo;
import com.bob.wx_sell.enums.ResultEnum;
import com.bob.wx_sell.service.SellerService;
import com.bob.wx_sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: toudaizhi
 * @Date: 2019-02-26 21:13
 * @Description:
 */
@Controller
@RequestMapping("/seller/")
@Slf4j
public class SellerUserController {
    @Autowired
    private SellerService sellerService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String,Object> map){
        //1.openid和数据库数据对比
        SellerInfo sellerInfo = sellerService.findSellerByOpenid(openid);
        if (sellerInfo == null){
            map.put("msg", ResultEnum.login_fail.getMessage());
            map.put("url", "/seller/order/list");
            return new ModelAndView("common/error");
        }
        //2.设置token至redis
        String token = UUID.randomUUID().toString();

        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),
                openid,RedisConstant.EXPIRE, TimeUnit.SECONDS);


        //3.设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);

        return new ModelAndView("redirect:"+projectUrlConfig.getSell()+"/seller/order/list");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String,Object> map){
        //1. 从cookie里查询
        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);
        if(cookie != null){
            //2. 从redis中清除信息
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
            //3. 从cookie中清除信息
            CookieUtil.set(response,CookieConstant.TOKEN,null,0);
        }
        map.put("msg", ResultEnum.logout_success.getMessage());
        map.put("url", "/seller/order/list");
        return new ModelAndView("common/success", map);
    }
}
