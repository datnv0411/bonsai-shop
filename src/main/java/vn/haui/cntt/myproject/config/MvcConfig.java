package vn.haui.cntt.myproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login-success").setViewName("login-success");
        registry.addViewController("/login-error").setViewName("login-error");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get("./user");
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("src/main/resources/static/images/user/**").addResourceLocations("file:/" + uploadPath + "/");
    }
}
