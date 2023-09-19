package locations.management.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class LocationsManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationsManagementServiceApplication.class, args);
	}

}
