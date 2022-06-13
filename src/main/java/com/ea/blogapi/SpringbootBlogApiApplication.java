package com.ea.blogapi;

import com.ea.blogapi.security.JwtAuthenticationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EntityScan(basePackageClasses = { SpringbootBlogApiApplication.class, Jsr310Converters.class })

public class SpringbootBlogApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBlogApiApplication.class, args);
    }


    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


}
