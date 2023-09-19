package locations.management.service.models;

import java.util.List;

import lombok.Data;

@Data
public class LocationCreateSwaggerDto {

	private String address;

	private String code;

	private Location location;

	private String name;

	private int rewardCheckinPoints;

	private List<String> tags;

	private String type;
}
