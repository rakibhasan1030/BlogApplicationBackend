package org.rakibhasan.blog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;


@EnableWebMvc
@Configuration
public class OpenAIConfig implements WebMvcConfigurer {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Blog Application Documentation")
                        .description("This project is developed by Rakib Hasan")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Rakib Hasan")
                                .url("https://www.linkedin.com/in/rakibhasan1030/")
                                .email("rakibhasan1030@gmail.com"))
                        .license(new License()
                                .name("License of APIs")
                                .url("API License URL"))
                        .termsOfService("Terms and Service"))
                .addSecurityItem(new SecurityRequirement().addList("BlogApplicationSecurityScheme"))
                .components(new Components().addSecuritySchemes("BlogApplicationSecurityScheme", new SecurityScheme()
                        .name("BlogApplicationSecurityScheme").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
