package com.graphql.ShoppingCart.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerAPIConfig {

    @Bean
    public OpenAPI defineOpenApi(){
        Server server=new Server();
        server.setUrl("http://localhost:9090");
        server.setDescription("This is spring boot development project");
        Contact myContact= new Contact();
        myContact.setName("Er Sakib Khan");
        myContact.setEmail("sakibtech88@gmail.com");
        myContact.setUrl("www.javatpoint.com");
        Info appInfo=new Info();
        appInfo.setTitle("This is Shopping App");
        appInfo.setVersion("v1.0.0");
        appInfo.setSummary("This application based on online shopping like flipkart");
        appInfo.setContact(myContact);
        return new OpenAPI().info(appInfo).servers(List.of(server));


    }
}
