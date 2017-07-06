package com.unq.tip;

import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import com.unq.tip.model.Item;
import org.joda.time.DateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.joda.DateTimeFormatterFactory;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class TripplannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripplannerApplication.class, args);
    }
/*
    @Bean
	public JodaModule jacksonJodaModule() {
		JodaModule module = new JodaModule();
		DateTimeFormatterFactory formatterFactory = new DateTimeFormatterFactory();
		formatterFactory.setIso(DateTimeFormat.ISO.DATE);
		module.addSerializer(DateTime.class, new DateTimeSerializer(
				new JacksonJodaFormat(formatterFactory.createDateTimeFormatter()
						.withZoneUTC())));
		return module;
	}*/

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
			/*@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:9000");
			}*/

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }


}
/*

@Configuration
public  class RepositoryConfig extends RepositoryRestConfiguration{

	@Override
	protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Item.class);
	}
}
*/