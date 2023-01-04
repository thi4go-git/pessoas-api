package net.ddns.cloudtecnologia.pessoas.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("net.ddns.cloudtecnologia.pessoas.rest.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Pessoas API")
                .description("API de Pessoas")
                .version("1.0.0")
                .contact(contact())
                .build();
    }

    private Contact contact() {
        // Dados de contato do desenvolvedor
        return new Contact("Thiago Junior",
                "https://www.linkedin.com/in/thiago-melo-84246a149/",
                "thi4go19@gmail.com");
    }


}
