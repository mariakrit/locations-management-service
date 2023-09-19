package locations.management.service.models;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

@Data
@NoArgsConstructor
@MappedSuperclass
@AllArgsConstructor
public class EntityWithUUID {

    private static final String ID = "uuid";

	@Id
    @Column(name = ID)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private UUID uuid;

}
