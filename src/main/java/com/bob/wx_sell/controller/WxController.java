package com.bob.wx_sell.controller;

import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @Auther: toudaizhi
 * @Date: 2019-01-04 13:52
 * @Description:
 */
@RestController
@RequestMapping("/wx")
public class WxController {

    private String TOKEN = "1234567abcdefg";
    @GetMapping("/checkUser")
    public void checkUser(@RequestParam("signature") String signature,
                          @RequestParam("echostr") String echostr,
                          @RequestParam("timestamp") String timestamp,
                          @RequestParam("nonce") String nonce,
                          HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("signature:" + signature);
        System.out.println("timestamp:" + timestamp);
        System.out.println("nonce:" + nonce);
        System.out.println("echostr:" + echostr);

        PrintWriter pw = response.getWriter();
        pw.append(echostr);
        pw.flush();
    }

    @GetMapping("/getToken")
    public void getToken(){
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx20dd97f0730cc4d9&secret=d0db0d9f2550234a6ee3268e9c6bfb49";
    }
}
