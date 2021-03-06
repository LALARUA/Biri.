package cn.zx.biri.zuul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMVCConfig implements WebMvcConfigurer{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String system = System.getProperty("os.name").toLowerCase();
        if (system.contains("win")){
            registry.addResourceHandler("/bookImg/**").addResourceLocations("file:E:/gitRep/BiriPic/bookPic/");

        }else if (system.contains("linux")){
            registry.addResourceHandler("/bookImg/**").addResourceLocations("file:opt/BiriPic/bookPic/");

        }
           registry.addResourceHandler("/uploadImg/**").addResourceLocations("file:/");
    }

}
