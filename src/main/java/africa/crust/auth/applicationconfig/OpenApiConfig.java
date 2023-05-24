package africa.crust.auth.applicationconfig;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
@SecurityScheme(name = "BearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@SecurityScheme(name = "SECRET_KEY", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER, paramName = "SECRET_KEY")
public class OpenApiConfig {
    Contact crust = new Contact()
            .name("Crust Africa Support")
            .email("team@crust.africa")
            .url("crust.africa");

    @Bean
    public OpenAPI configAPI(){
        return new OpenAPI()
                .info(new Info()
                .title("Crust Api")
                .version("Version 1.00")
                .description("Crust Core API Middle Layer")
                .contact(crust)
                .termsOfService("An online digital wallet !!! "));
    }
}