package com.example.employeeservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Employee Service API",
                version = "1.0",
                contact = @io.swagger.v3.oas.annotations.info.Contact(
                        name = "Nguyen Van Loc",
                        email = "nvl150424@gmail.com",
                        url = "https://laptrinhfullstack.vercel.app"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://laptrinhfullstack.com/license"
                ),
                termsOfService = "https://laptrinhfullstack.com/terms",
                description = "Documentation Employee Service API v1.0"

        ),
        servers = {
                @Server(
                        url = "http://localhost:8887",
                        description = "Local server"
                ),
                @Server(
                        url = "https://employee-service.dev.com",
                        description = "Production server"
                ),
                @Server(
                        url = "https://employee-service.dev.com",
                        description = "Production server"
                )
        }

)
public class OpenApiConfig {
}
