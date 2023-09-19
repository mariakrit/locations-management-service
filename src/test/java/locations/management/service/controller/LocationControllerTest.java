package locations.management.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import locations.management.service.models.Location;
import locations.management.service.models.LocationEntity;
import locations.management.service.repository.LocationRepository;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LocationController.class)
public class LocationControllerTest {

	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mvc;

	@MockBean
	private LocationRepository locationRepository;

	@Mock
	public LocationController locationController;
	
	@Autowired
	ObjectMapper objectMapper;


	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
    public void testRetriveAllLocations() throws Exception {
        List<LocationEntity> mockLocations = new ArrayList<>();
        mockLocations.add(new LocationEntity());
        mockLocations.add(new LocationEntity());

        when(locationRepository.findAll())
               .thenReturn(mockLocations);

        mvc.perform(get("/location/retrieveAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(mockLocations.size()));

        verify(locationRepository).findAll();
    }
	
	@Test
    public void testRetrieveByIdLocation() throws Exception {
        UUID locationUuid = UUID.randomUUID();
        LocationEntity locationToRetreive = createTestLocation();
        locationToRetreive.setUuid(locationUuid);

        when(locationRepository.findById(locationUuid))
               .thenReturn(Optional.of(locationToRetreive));

        mvc.perform(get("/location/retrieveById/{uuid}", locationUuid))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        		.andReturn()
        		.getResponse();

        verify(locationRepository).findById(locationUuid);
    }

	@Test
	public void testCreateLocation() throws Exception {
		LocationEntity locationToSave = createTestLocation();
		LocationEntity savedLocation = createTestLocation();
		savedLocation.setUuid(UUID.randomUUID());

		when(locationRepository.save(locationToSave)).thenReturn(savedLocation);

		mvc.perform(post("/location/create")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(locationToSave)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn()
				.getResponse();

		verify(locationRepository, times(1)).save(locationToSave);
		verifyNoMoreInteractions(locationRepository);
	}

	@Test
	public void testUpdateLocation() throws Exception {
		UUID locationUuid = UUID.randomUUID();
		LocationEntity locationToUpdate = createTestLocation();
		locationToUpdate.setUuid(locationUuid);

		Map<String, Object> inputFields = new HashMap<>();
		inputFields.put("address", "Address Test");

		when(locationRepository.findById(locationUuid)).thenReturn(Optional.of(locationToUpdate));

		when(locationRepository.save(any(LocationEntity.class))).thenReturn(locationToUpdate);

		mvc.perform(put("/location/update/{uuid}", locationUuid)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(inputFields)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn()
				.getResponse();

		verify(locationRepository).findById(locationUuid);
		verify(locationRepository).save(any(LocationEntity.class));
	}
	
	@Test
    public void testDeleteLocation() throws Exception {
        UUID locationUuid = UUID.randomUUID();
        LocationEntity locationToDelete = createTestLocation();
        locationToDelete.setUuid(locationUuid);

        when(locationRepository.findById(locationUuid))
               .thenReturn(Optional.of(locationToDelete));

        mvc.perform(delete("/location/delete/{uuid}", locationUuid))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        		.andReturn()
        		.getResponse();

        verify(locationRepository).findById(locationUuid);
        verify(locationRepository).deleteById(locationUuid);
    }

	private LocationEntity createTestLocation() {

		List<String> tags = Arrays.asList("popular", "favorite", "innovation_center");

		return LocationEntity.builder()
				.address("Voutadon 29-23, Athina 118 54")
				.code("000000A")
				.location(Location.builder().lat(37.978693).lon(23.712884).build())
				.name("HQ")
				.rewardCheckinPoints(1)
				.tags(tags)
				.type("office")
				.build();
		}
}