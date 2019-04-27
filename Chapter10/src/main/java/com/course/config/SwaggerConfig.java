package com.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")  //访问路径
                .select()
                .paths(PathSelectors.regex("/.*")) //正则匹配，匹配路径
                .build();
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder().title("我的接口文档")
                .contact(new Contact("txf","","928436460@qq.com"))
                .description("这是我的一个Swaggerui生成的接口文档")
                .build();
    }


}
