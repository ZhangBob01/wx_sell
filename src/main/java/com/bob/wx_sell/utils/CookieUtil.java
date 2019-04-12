package com.bob.wx_sell.utils;

import javax.print.attribute.standard.MediaSize;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: toudaizhi
 * @Date: 2019-02-26 22:55
 * @Description:
 */
public class CookieUtil {

    public static void set(HttpServletResponse response,
                           String name,
                           String value,
                           int maxAge){
        Cookie cookie = new Cookie(name,value);

        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static Cookie get(HttpServletRequest request,
                           String name){
        Map<String,Cookie> cookieMap = readCookieMap(request);
        if(cookieMap.containsKey(name)){
            return cookieMap.get(name);
        }else {
            return null;
        }
    }

    private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {

        //获取cookie
        Cookie[] cookies = request.getCookies();

        Map<String,Cookie> cookieMap = new HashMap<>();

        if(cookies!=null){
            for(Cookie cookie:cookies){
                cookieMap.put(cookie.getName(),cookie);
            }
        }
        return cookieMap;
    }
}
