package world.tour.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Type {
	private Long typeId;
	private String typeName;
	
	private Set<Rider> riders = new HashSet<>();
}
