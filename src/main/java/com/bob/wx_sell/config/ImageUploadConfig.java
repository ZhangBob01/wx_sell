package com.bob.wx_sell.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * @Auther: toudaizhi
 * @Date: 2019-03-06 14:43
 * @Description:
 */
@Component
public class ImageUploadConfig {
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大KB,MB
        //factory.setMaxFileSize("2MB");
        factory.setMaxFileSize(DataSize.ofBytes(2*1024*1024L));
        //设置总上传数据总大小
        //factory.setMaxRequestSize("10MB");
        factory.setMaxFileSize(DataSize.ofBytes(10*1024*1024L));
        return factory.createMultipartConfig();
    }
}
