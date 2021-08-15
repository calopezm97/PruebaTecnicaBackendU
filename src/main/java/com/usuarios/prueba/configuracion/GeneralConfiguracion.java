package com.usuarios.prueba.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile("dev")
public class GeneralConfiguracion implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "POST", "DELETE");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry
                .addRedirectViewController("/", "/swagger-ui");
        registry
                .addRedirectViewController("/swagger-ui", "/swagger-ui/index.html");
    }
}
