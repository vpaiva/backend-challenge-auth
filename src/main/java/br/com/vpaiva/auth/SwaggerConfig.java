package br.com.vpaiva.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

  @Bean
  public Docket greetingApi() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("br.com.vpaiva.auth.web"))
        .paths(PathSelectors.any()).build().apiInfo(this.metaData());

  }

  private ApiInfo metaData() {
    return new ApiInfoBuilder().title("Auth REST API")
        .description("\"Projeto de authenticação\"").version("1.0.0")
        .license("BSD")
        .licenseUrl("https://opensource.org/licenses/BSD-3-Clause").build();
  }


}
