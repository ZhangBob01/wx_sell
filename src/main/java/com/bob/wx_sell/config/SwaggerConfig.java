package com.bob.wx_sell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-24 16:58
 * @Description:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).select()
                //自定义扫描路径
                .apis(RequestHandlerSelectors.basePackage("com.bob.wx_sell.controller"))
                .paths(PathSelectors.any()).build();

    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("微信订餐API文档")
                .description("restful 风格接口")
                .termsOfServiceUrl("api.bob.com")
                .version("1.0")
                .build();
    }
}
