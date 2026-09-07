package com.udacity.vehicles.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
    

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("cars")
                .pathsToMatch("/cars/**")
                .build();
    }

  @Bean
  public OpenAPI locationOpenAPI() {
      return new OpenAPI()
              .info(new Info().title("Vehicle API")
              .description("This is the final project of the third chapter of Udacity's Java Web developer course.")     
              .version("v0.0.1")
              .license(new License().name("Vehicle API").url("http://www.udacity.com/tos")))
              .externalDocs(new ExternalDocumentation()
              .description("License of API")
              .url("\"http://www.udacity.com/license"));
  }
}
