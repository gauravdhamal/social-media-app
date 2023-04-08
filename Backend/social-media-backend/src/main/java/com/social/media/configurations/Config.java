package com.social.media.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class Config {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
