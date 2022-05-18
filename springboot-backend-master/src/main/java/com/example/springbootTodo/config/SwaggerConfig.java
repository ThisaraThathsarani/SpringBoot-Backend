package com.example.springbootTodo.config;

import com.example.springbootTodo.constant.Constant;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springdoc.core.Constants.SPRINGDOC_ENABLED;

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnProperty(name = SPRINGDOC_ENABLED, matchIfMissing = true)
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {

        License license = new License();
        license.setName(Constant.SwaggerConfig.LICENSE);
        license.setUrl(Constant.SwaggerConfig.LICENSE_URL);

        Contact contact = new Contact();
        contact.setName(Constant.SwaggerConfig.CONTACT_NAME);
        contact.setUrl(Constant.SwaggerConfig.CONTACT_URL);
        contact.setEmail(Constant.SwaggerConfig.CONTACT_EMAIL);

        return new OpenAPI().info(
                new Info()
                        .title(Constant.SwaggerConfig.TITLE)
                        .description(Constant.SwaggerConfig.DESCRIPTION)
                        .termsOfService(Constant.SwaggerConfig.TERMS_OF_SERVICE_URL)
                        .contact(contact)
                        .license(license)
        );
    }
}
