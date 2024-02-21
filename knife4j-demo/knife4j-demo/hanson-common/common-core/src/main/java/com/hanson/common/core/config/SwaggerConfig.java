package com.hanson.common.core.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2    //开启 Swagger2
@EnableKnife4j     //开启 knife4j，可以不写
@EnableAutoConfiguration
@ConditionalOnProperty(name = "swagger.enable", matchIfMissing = true)
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        // 创建一个 swagger 的 bean 实例
        return new Docket(DocumentationType.SWAGGER_2)
                // 配置基本信息
                .apiInfo(apiInfo())
                // 配置接口信息，设置扫描接口
                .select()

                /*
                 * RequestHandlerSelectors
                 *      .any() // 扫描全部的接口，默认
                 *      .none() // 全部不扫描
                 *      .basePackage("com.hanson.server") // 扫描指定包下的接口，最为常用
                 *      .withClassAnnotation(RestController.class) // 扫描带有指定注解的类下所有接口
                 *      .withMethodAnnotation(PostMapping.class) // 扫描带有只当注解的方法接口
                 */

                // 此包路径下的类，才生成接口文档
                .apis(RequestHandlerSelectors.basePackage("com.hanson.server"))
                // 加了 RestController 注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                // 加了 ApiOperation 注解的方法，才生成接口文档
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))  // @ApiOperation：swagger 常用注解，用户标注方法描述

                /*
                 * PathSelectors
                 *      .any() // 满足条件的路径，该断言总为true
                 *      .none() // 不满足条件的路径，该断言总为false（可用于生成环境屏蔽 swagger）
                 *      .ant("/user/**") // 满足字符串表达式路径
                 *      .regex("") // 符合正则的路径
                 */

                .paths(PathSelectors.any())

                .build()


                // 安全模式
                .securitySchemes(securitySchemes())
                // 安全上下文
                .securityContexts(securityContexts())
                .pathMapping("/");
    }

    /**
     * 基本信息设置
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 标题
                .title("汉升-接口文档")
                // 描述
                .description("众里寻他千百度，慕然回首那人却在灯火阑珊处")
                // 服务条款链接
                .termsOfServiceUrl("https://www.baidu.com")
                // 许可证
                .license("swagger-的使用(详细教程)")
                // 许可证链接
                .licenseUrl("https://blog.csdn.net/weixin_45683778/article/details/136211224")
                // 联系我
                .contact(new Contact(
                        "黄汉升",
                        "https://blog.csdn.net/weixin_45683778/article/details/136211224",
                        "77777777@163.com"))
                // 版本
                .version("1.0")
                .build();
    }

    /**
     * 安全模式，这里指定token通过Authorization头请求头传递
     */
    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> apiKeyList = new ArrayList<SecurityScheme>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeyList;
    }

    /**
     * 安全上下文
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .operationSelector(o -> o.requestMappingPattern().matches("/.*"))
                        .build());
        return securityContexts;
    }

    /**
     * 默认的全局鉴权策略
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }


}

