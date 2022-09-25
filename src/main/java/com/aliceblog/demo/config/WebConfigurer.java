package com.aliceblog.demo.config;


import com.aliceblog.demo.Interceptor.PublishInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {



    @Autowired
   private PublishInterceptor publishInterceptor;



    private String filePath = "/BlogResources/blogimg";
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/blogimg/**").addResourceLocations("file:"+filePath+"/");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

       registry.addInterceptor(publishInterceptor).addPathPatterns("/html/publishBlog.html");
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
                .allowCredentials(true).maxAge(3600);
    }

}
