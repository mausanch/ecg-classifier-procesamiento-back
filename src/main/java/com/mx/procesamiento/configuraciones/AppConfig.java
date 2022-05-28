package com.mx.procesamiento.configuraciones;

import java.text.SimpleDateFormat;

import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableSpringDataWebSupport
@EnableJpaAuditing(auditorAwareRef ="auditorAware")
@Slf4j
public class AppConfig implements WebMvcConfigurer{

	@Bean
    public ObjectMapper objectMapper() {
        log.info("Inicio Log");
        return new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE).findAndRegisterModules();
    }
   
	
	@Bean (name="SimpleDateTime")
	public SimpleDateFormat simpleDateTimeFormat (){
		return new SimpleDateFormat("dd/MM/yyyy");
    }

    
	@Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource);
        return localValidatorFactoryBean;
    }
   
 
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    } 
     
    @Bean
    public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/procesamiento/**")
                .allowedOrigins("http://localhost:4200","http://localhost:4201")
                .allowedMethods("OPTIONS","POST","GET","PUT","PATCH","DELETE");
			}
		};
	}

}