package com.strong.BloodDonation.Utils;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Blood Donation API", version = "2.5", description = "Blood Donation RestFul API"))
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder().group("Blood Donation Security").pathsToMatch("/api/v1/**").build();
    }

}
