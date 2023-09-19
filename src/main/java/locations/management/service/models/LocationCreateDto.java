package locations.management.service.models;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationCreateDto {

	private String address;

	private String code;

	private GeoLocation geoLocation;

	private String name;

	private int rewardCheckinPoints;

	private List<String> tags;

	private String type;
}
