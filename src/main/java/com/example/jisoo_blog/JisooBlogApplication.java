package com.example.jisoo_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JisooBlogApplication {

    public static void main( String[] args ) {
        SpringApplication.run( JisooBlogApplication.class, args );
    }

}
