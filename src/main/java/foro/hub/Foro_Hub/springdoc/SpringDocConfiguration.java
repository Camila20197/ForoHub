package foro.hub.Foro_Hub.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de SpringDoc para generar la documentación de la API utilizando Swagger.
 */
@Configuration
public class SpringDocConfiguration {

    /**
     * Configura la definición de un esquema de seguridad basado en tokens JWT.
     *
     * @return Un objeto OpenAPI con el esquema de seguridad configurado.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .components(new Components()
            .addSecuritySchemes("bearer-key",
            new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }
}
