package edu.epitech.timemanager.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        title = "TimeManager API",
        description = "The API documentation for the TimeManager app",
        contact = @Contact(
            name = "Even POUYADOU",
            url = "https://github.com/Alphamplyer/epitech-timemanager/issues",
            email = "even.pouyadou@gmail.com"
        )
    ),
    servers = @Server(url = "http://localhost:4000")
)
@SecurityScheme(
    name = "api",
    scheme = "basic",
    type = SecuritySchemeType.HTTP,
    in = SecuritySchemeIn.HEADER
)
public class OpenAPIConfiguration {
}
