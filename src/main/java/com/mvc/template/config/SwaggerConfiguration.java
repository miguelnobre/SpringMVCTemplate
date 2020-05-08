package com.mvc.template.config;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.FileReader;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);

    @Bean
    public Docket swaggerApi() {
        try {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.regex("/.*"))
                    .build()
                    .apiInfo(swaggerApiInfo());
        } catch (Exception e) {
            log.info("Error during Swagger Configuration", e);
            return null;
        }
    }

    private ApiInfo swaggerApiInfo() throws Exception {
        MavenXpp3Reader pomReader = new MavenXpp3Reader();
        Model model = pomReader.read(new FileReader("pom.xml"));

        log.info("Pom Version: {}", model.getVersion());

        return new ApiInfoBuilder()
                .title("Spring MVC Application")
                .description("Spring MVC Template")
                .version(model.getVersion())
                .build();
    }
}