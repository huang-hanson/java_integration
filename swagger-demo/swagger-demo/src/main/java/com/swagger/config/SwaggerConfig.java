package com.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration // 配置类
@EnableSwagger2 // 开启 swagger2 的自动配置
public class SwaggerConfig {

    /**
     * swagger 配置
     * @param environment 环境
     */
    @Bean
    public Docket docket1(Environment environment) {

        // 设置环境范围
        Profiles profiles = Profiles.of("dev","test");
        // 如果在该环境返回内则返回：true，反之返回 false
        boolean flag = environment.acceptsProfiles(profiles);

        // 设置请求头
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new ParameterBuilder()
                .name("token") // 字段名
                .description("token") // 描述
                .modelRef(new ModelRef("string")) // 数据类型
                .parameterType("header") // 参数类型
                .defaultValue("default value") // 默认值：可自己设置
                .hidden(true) // 是否隐藏
                .required(false) // 是否必须
                .build());

        // 创建一个 swagger 的 bean 实例
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(flag) // 是否开启 swagger：true -> 开启，false -> 关闭
                .groupName("hanson") // 修改组名为 "hanson"
                // 配置基本信息
                .apiInfo(apiInfo())

                // 配置接口信息
                .select() // 设置扫描接口
                // 配置如何扫描接口
                .apis(RequestHandlerSelectors
                                //.any() // 扫描全部的接口，默认
                                //.none() // 全部不扫描
                                .basePackage("com.swagger.controller") // 扫描指定包下的接口，最为常用
                        //.withClassAnnotation(RestController.class) // 扫描带有指定注解的类下所有接口
                        //.withMethodAnnotation(PostMapping.class) // 扫描带有只当注解的方法接口

                )
                .paths(PathSelectors
                                .any() // 满足条件的路径，该断言总为true
                        //.none() // 不满足条件的路径，该断言总为false（可用于生成环境屏蔽 swagger）
                        //.ant("/user/**") // 满足字符串表达式路径
                        //.regex("") // 符合正则的路径
                )
                .build()

                // 添加请求头参数
                .globalOperationParameters(parameters);
    }

    @Bean
    public Docket docket2(Environment environment) {

        // 设置环境范围
        Profiles profiles = Profiles.of("dev","test");
        // 如果在该环境返回内则返回：true，反之返回 false
        boolean flag = environment.acceptsProfiles(profiles);

        // 设置请求头
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new ParameterBuilder()
                .name("token") // 字段名
                .description("token") // 描述
                .modelRef(new ModelRef("string")) // 数据类型
                .parameterType("header") // 参数类型
                .defaultValue("default value") // 默认值：可自己设置
                .hidden(true) // 是否隐藏
                .required(false) // 是否必须
                .build());

        // 创建一个 swagger 的 bean 实例
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(flag) // 是否开启 swagger：true -> 开启，false -> 关闭
                .groupName("huang") // 修改组名为 "huang"
                // 配置基本信息
                .apiInfo(apiInfo())

                // 配置接口信息
                .select() // 设置扫描接口
                // 配置如何扫描接口
                .apis(RequestHandlerSelectors
                                .any() // 扫描全部的接口，默认
                        //.none() // 全部不扫描
//                                .basePackage("com.swagger.controller") // 扫描指定包下的接口，最为常用
                        //.withClassAnnotation(RestController.class) // 扫描带有指定注解的类下所有接口
                        //.withMethodAnnotation(PostMapping.class) // 扫描带有只当注解的方法接口

                )
                .paths(PathSelectors
                                .ant("/error")
//                                .any() // 满足条件的路径，该断言总为true
                        //.none() // 不满足条件的路径，该断言总为false（可用于生成环境屏蔽 swagger）
                        //.ant("/user/**") // 满足字符串表达式路径
                        //.regex("") // 符合正则的路径
                )
                .build()

                // 添加请求头参数
                .globalOperationParameters(parameters);
    }

    // 基本信息设置
    private ApiInfo apiInfo() {
        Contact contact = new Contact(
                "hanson", // 作者姓名
                "https://blog.csdn.net/weixin_45683778/article/details/136211224?spm=1001.2014.3001.5502", // 作者主页
                "777777777@163.com"); // 作者邮箱
        return new ApiInfoBuilder()
                .title("汉森-接口文档") // 标题
                .description("众里寻他千百度，慕然回首那人却在灯火阑珊处") // 描述
                .termsOfServiceUrl("https://www.baidu.com") // 跳转连接
                .version("1.0") // 版本
                .license("swagger-的使用(详细教程)")
                .licenseUrl("https://www.baidu.com")
                .contact(contact)
                .build();
    }
}