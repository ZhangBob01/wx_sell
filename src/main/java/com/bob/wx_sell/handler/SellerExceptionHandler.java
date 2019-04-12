package com.bob.wx_sell.handler;

import com.bob.wx_sell.VO.ResultVO;
import com.bob.wx_sell.config.ProjectUrlConfig;
import com.bob.wx_sell.enums.ResultEnum;
import com.bob.wx_sell.exception.ResponseBankException;
import com.bob.wx_sell.exception.SellException;
import com.bob.wx_sell.exception.SellerAuthorizeException;
import com.bob.wx_sell.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: toudaizhi
 * @Date: 2019-02-27 16:09
 * @Description:
 */
@ControllerAdvice
public class SellerExceptionHandler {

    @Autowired
    private  ProjectUrlConfig projectUrlConfig;

    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWechatOpenAuthorize())
                .concat("/seller/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getSell())
                .concat("/seller/login"));
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellEeception(SellException e){
        System.out.println(e.getCode()+":"+e.getMessage());
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handlerResponseBankException(){

    }
}
