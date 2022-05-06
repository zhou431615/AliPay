package com.zhoujk.alipay.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author : zhoujiankang
 * @Desc:
 * @since : 2022/5/4 11:09
 */


@Configuration
@EnableSwagger2
public class SwaggerConfig
{

    public Docket createRestApi()
    {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.zhoujk")).paths(PathSelectors.any()).build();

    }


    /**构建api文档的详细信息函数
     *
     * @return  apiInfo
     */
    private ApiInfo apiInfo()
    {

        return new ApiInfoBuilder()
                //页面标题
                .title("Alipay演示项目API")
                //项目描述
                .description("Alipay演示项目API接口界面")
                //服务条款网址
                .termsOfServiceUrl("https://zhoujk.top")
                //作者信息
                .contact(new Contact("千叶", "zhoujk.top", "zhou431613@163.com"))
                //许可证
                .license("Apache 3.0")
                //版本
                .version("2.0").build();
    }
}
