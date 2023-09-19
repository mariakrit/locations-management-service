package locations.management.service.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.fasterxml.jackson.databind.JsonMappingException;

import locations.management.service.measure.time.MeasureTime;
import locations.management.service.models.LocationPutSwaggerDto;
import locations.management.service.models.LocationCreateSwaggerDto;
import locations.management.service.models.LocationEntity;
import locations.management.service.repository.LocationRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping(value = "/location")
@RestController
@CacheConfig(cacheNames = {"location"})
@Tag(name = "Location Management", description = "Endpoints for managing locations")
public class LocationController {

	Logger log = LoggerFactory.getLogger(LocationController.class);

	private final LocationRepository locationRepository;

	@Autowired
	public LocationController(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}



	@MeasureTime
	@CacheEvict(cacheNames = "location", allEntries = true)
	@GetMapping(value = { "/retrieveAll" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LocationEntity> retrieveAll() {

		return locationRepository.findAll();
	}



	@MeasureTime
	@Cacheable(value = "location")
	@GetMapping(value = { "/retrieveById/{uuid}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LocationEntity> retrieveById(
			@PathVariable @Parameter(description = "The Id of the location to retrieve.") 
			UUID uuid) {
		
		return locationRepository.findById(uuid).map(ResponseEntity::ok)
				.orElseThrow(() -> new RuntimeException("Location not exist with id: " + uuid));
	}



	@MeasureTime
	@CachePut(cacheNames = "location")
	@PostMapping(value = {"/create" }, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			   schema = @Schema(implementation = LocationCreateSwaggerDto.class))
			 })	
	public ResponseEntity<LocationEntity> create(
			@RequestBody @Parameter(description = "The input location to be created.") 
			LocationEntity locationEntity) {
		
		return new ResponseEntity<>(locationRepository.save(locationEntity), HttpStatus.CREATED);

	}



	@MeasureTime
    @CachePut(value = "location", key = "#uuid")//update 'location' cache with this specific updated uuid record
	@PutMapping(value = {"/update/{uuid}" }, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			   schema = @Schema(implementation = LocationPutSwaggerDto.class))
			 })	
	public ResponseEntity<LocationEntity> updateLocation(
			@PathVariable @Parameter(description = "The Id to find the existing location.")
			UUID uuid, 
			@RequestBody @Parameter(description = "The fields to be updated.") 
			Map<String, Object> inputFields) throws JsonMappingException {

		LocationEntity location = locationRepository.findById(uuid)
				.orElseThrow(() -> new RuntimeException("Location not exist with id: " + uuid));

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.updateValue(location, inputFields);

		
		return ResponseEntity.ok(locationRepository.save(location));
	}



	@MeasureTime
    @CacheEvict(value = "location", key = "#uuid")
	@DeleteMapping(value = {"/delete/{uuid}" })
	public ResponseEntity<UUID> deleteLocation(
			@PathVariable @Parameter(description = "The Id of the location to delete.") 
			UUID uuid) {

		locationRepository.findById(uuid)
				.orElseThrow(() -> new RuntimeException("Location not exist with id: " + uuid));
		
		locationRepository.deleteById(uuid);

		return new ResponseEntity<>(uuid, HttpStatus.OK);
	}
}