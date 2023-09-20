package locations.management.service.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Component
public class SwaggerConfig {

	@Bean
	public OpenAPI springOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Location Management API Docs")
						.description("Location Management API")
						.version("v2.0.0"));
	}
}
