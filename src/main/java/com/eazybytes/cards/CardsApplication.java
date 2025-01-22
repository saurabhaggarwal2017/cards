package com.eazybytes.cards;

import com.eazybytes.cards.dto.CardsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(CardsContactInfoDto.class)
@OpenAPIDefinition(
        info = @Info(
                title = "Card Microservice.",
                description = "Card service for creating cards for account users.",
                contact = @Contact(
                        name = "Saurabh Kumar",
                        url = "https://github.com/saurabhaggarwal2017",
                        email = "saurabhaggarwal2017@gmail.com"
                ),
                version = "V1"
        ),
        externalDocs = @ExternalDocumentation(
                description = "For more information check out tutor github.",
                url = "https://github.com/eazybytes/microservices/tree/3.3.2/section2/cards"
        )
)
public class CardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardsApplication.class, args);
    }

}
