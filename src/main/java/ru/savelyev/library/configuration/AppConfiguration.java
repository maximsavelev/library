package ru.savelyev.library.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class AppConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
