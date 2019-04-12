package com.bob.wx_sell.aspect;

import com.bob.wx_sell.constant.CookieConstant;
import com.bob.wx_sell.constant.RedisConstant;
import com.bob.wx_sell.exception.SellerAuthorizeException;
import com.bob.wx_sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: toudaizhi
 * @Date: 2019-02-27 10:10
 * @Description:
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;

//    @Pointcut("execution(public * com.bob.wx_sell.controller.Seller*.*(..))"+
//    "&& !execution(public * com.bob.wx_sell.controller.SellerUserController.*(..))")
//    public void verify(){
//
//    }

//    @Before("verify()")
//    public void doVerify(){
//        //1.获取HttpServletRequest
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        //2.查询cookie
//        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
//        if(cookie == null){
//            log.warn("【登录校验】Cookie中查不到token");
//            throw new SellerAuthorizeException();
//        }
//        //3.查询redis中的openid
//        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
//        if(StringUtils.isEmpty(tokenValue)){
//            log.warn("【登录校验】Redis中查不到token");
//            throw new SellerAuthorizeException();
//        }
//
//    }
}
