package locations.management.service.models;

import java.util.List;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = LocationEntity.LOCATION)
@Entity
public class LocationEntity extends EntityWithUUID {

	public static final String LOCATION = "location";
	private static final String ADDRESS = "address";
	private static final String CODE = "code";
	private static final String GEOLOCATION = "geolocation";
	private static final String NAME = "name";
	private static final String REWARDCHECKINPOINTS = "reward_checkin_points";
	private static final String TAGS = "tags";
	private static final String TYPE = "type";

	@Column(name = LocationEntity.ADDRESS)
	@Nullable
	private String address;

	@Column(name = LocationEntity.CODE)
	@Nullable
	private String code;

	@Embedded
	@Column(name = LocationEntity.GEOLOCATION)
	@NotNull
	private GeoLocation geoLocation;

	@Column(name = LocationEntity.NAME)
	@Nullable
	private String name;

	@Column(name = LocationEntity.REWARDCHECKINPOINTS)
	@NotNull
	private int rewardCheckinPoints;

	@Column(name = LocationEntity.TAGS)
	@Nullable
	private List<String> tags;

	@Column(name = LocationEntity.TYPE)
	@Nullable
	private String type;

}