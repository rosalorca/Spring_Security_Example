package com.openclassrooms.securityozlem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

//@ComponentScan({"com.openclassrooms.securityozlem.controller"}) //----> pour enlever les utilisateurs manuelles
//@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class}) ----> ça sert rien
public class SecurityOzlemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityOzlemApplication.class, args);
    }

}
