package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import com.example.demo.util.Constantes;

@Configuration
public class ConfigApp {

	@Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        String activeProfile = System.getProperty("spring.profiles.active", Constantes.ENV);
        String propertiesFilename = "application-" + activeProfile + ".properties";

        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource(propertiesFilename));

        return configurer;
    }
	
}
