package com.quasar.operation.app.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class MessageProcessorSwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.quasar.operation.app.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalResponses(HttpMethod.POST, responses())
                .apiInfo(apiInfo());
    }

    private List<Response> responses() {
        return Arrays.asList(
            new ResponseBuilder().code("404").description("Ocurrio un problema al desifrar el mensaje").build());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Operación Fuego de Quasar: Message Processor",
                "Procesador de mensages de satelites para la operacion Operación Fuego de Quasar.",
                "v1",
                null,
                new Contact("Juan Manuel Rodriguez", "https://github.com/rexyti/quasar-fire-operation",
                        "ing.rodriguezjm@gmail.com"),
                null, null , Collections.emptyList());
    }

    
}