package com.zhou.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


/**
 * @Product: IntelliJ IDEA
 * TODO:类文件描述
 * @PackageName: com.zhou.config
 * @ClassName: SwaggerConfig
 * @Author: 周志刚
 * @CreateDate: 2021/4/28 16:24
 * @Version: 0.0.1
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger2.enable}")
    private boolean enable;

    @Bean
    public Docket createDocket(){
        /**
         * 这是为了我们在用 swagger 测试接口的时候添加头部信息
         */
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        ParameterBuilder refreshTokenPar = new ParameterBuilder();
        tokenPar.name("authorization").description("swagger测试用(模拟authorization传入)非必填 header")
                .modelRef(new ModelRef("string")).parameterType("header").required(false);
        refreshTokenPar.name("refresh_token").description("swagger测试用(模拟刷新token传入)非必填 header")
                .modelRef(new ModelRef("string")).parameterType("header").required(false);
                        /**
                         * 多个的时候 就直接添加到 pars 就可以了
                         */
        pars.add(tokenPar.build());
        pars.add(refreshTokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zhou.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .enable(enable);

    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("周志刚后台管理系统")
                .description("周志刚后台管理系统后端接口文档")
                .termsOfServiceUrl("")
                .version("1.0")
                .contact(new Contact("周先森","https://github.com/zhouzhigang-GitHub","zhouzhigang1227@163.com"))
                .build();
    }

}
