package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    public OpenAPI defineOpenApi(){
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("dev");

        Contact contact = new Contact();
        contact.setEmail("yousefnegm@gmail.com");
        contact.setName("yousef negm");

        Info information = new Info()
                .title("Hotel Reservation System API")
                .version("1.0")
                .description("api for reserving hotel rooms")
                .contact(contact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}


