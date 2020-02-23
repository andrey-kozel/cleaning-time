package dev.akozel.cleaningtime.rest.swagger;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * SwaggerConfiguration. Swagger configuration
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("dev.akozel.cleaningtime.rest"))
                .paths(PathSelectors.any())
                .build()
                .enable(true)
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(getApiKey()));
    }

    private ApiKey getApiKey() {
        return new ApiKey("AUTHORIZATION", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(new SecuredUrls())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("AUTHORIZATION", authorizationScopes));
    }

    private static class SecuredUrls implements Predicate<String> {

        private static final List<String> PUBLIC_URL_REQUEST = Arrays.asList("/v1/auth", "/v1/registrations");

        @Override
        public boolean apply(String url) {
            return !PUBLIC_URL_REQUEST
                    .stream()
                    .anyMatch(url::equals);
        }

    }

}
