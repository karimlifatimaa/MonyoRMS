package com.example.monyorms.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String bearerSecuritySchemeName = "bearerAuth"; // JWT üçün Bearer token adı
        final String authUserIdHeaderName = "X-Auth-User-Id"; // X-Auth-User-Id header adı

        return new OpenAPI()
                .info(new Info()
                        .title("Monyo RMS") // API-nizin adı
                        .version("1.0") // API-nizin versiyası
                        .description("Monyo RMS üçün API sənədləşdirməsi, JWT və X-Auth-User-Id ilə.")) // Əlavə təsvir əlavə edildi
                .addSecurityItem(new SecurityRequirement().addList(bearerSecuritySchemeName)) // JWT tələb edən endpointlər üçün
                .addSecurityItem(new SecurityRequirement().addList(authUserIdHeaderName)) // X-Auth-User-Id tələb edən endpointlər üçün
                .components(new Components()
                        .addSecuritySchemes(bearerSecuritySchemeName, // JWT Bearer token sxemi
                                new SecurityScheme()
                                        .name(bearerSecuritySchemeName)
                                        .type(SecurityScheme.Type.HTTP) // HTTP əsaslı təhlükəsizlik sxemi
                                        .scheme("bearer") // Bearer token istifadə olunur
                                        .bearerFormat("JWT")) // Bearer formatının JWT olduğunu göstərir
                        .addSecuritySchemes(authUserIdHeaderName, // X-Auth-User-Id üçün yeni təhlükəsizlik sxemi
                                new SecurityScheme()
                                        .name(authUserIdHeaderName)
                                        .type(SecurityScheme.Type.APIKEY) // Bu, headerdə ötürülən API Key kimi qəbul edilir
                                        .in(SecurityScheme.In.HEADER) // Harada ötürüldüyünü göstərir (Headerdə)
                                        .description("Daxil olmuş istifadəçinin ID-si (Adətən 'X-Auth-User-Id' kimi göndərilir)"))); // Bu hissə Swagger UI-da görünəcək
    }
}
