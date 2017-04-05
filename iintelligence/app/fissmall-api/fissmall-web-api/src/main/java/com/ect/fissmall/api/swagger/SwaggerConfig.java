package com.ect.fissmall.api.swagger;

/**
 * Created by lenovo on 2016/7/14.
 */

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * Every Docket bean is picked up by the swagger-mvc framework - allowing for multiple
     * swagger groups i.e. same code base multiple swagger resource listings.
     */
//    @Bean
//    public Docket customDocket(){
//        return new Docket(DocumentationType.SWAGGER_2).select()
//        		.apis(RequestHandlerSelectors.basePackage("com.ect.fissmall.api"))
//        		.paths(PathSelectors.ant("/api/*"))
//        		.build();
//    }

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
}
