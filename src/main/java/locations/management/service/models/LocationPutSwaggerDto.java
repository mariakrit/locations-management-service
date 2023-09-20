package locations.management.service.models;

import java.util.List;

import lombok.Data;

@Data
public class LocationPutSwaggerDto {

	private String code;

	private int rewardCheckinPoints;

	private List<String> tags;

}
