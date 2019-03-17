package com.graynaud.eu4savedisplayerbo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.graynaud.eu4savedisplayerbo.repository")
public class JpaConfiguration {
}
