package locations.management.service;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@EnableCaching
@SpringBootApplication
public class LocationsManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationsManagementServiceApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
	    return new ModelMapper();
	}
}
