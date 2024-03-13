package world.tour.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Rider {
	private Long riderId;
	private String firstName;
	private String lastName;
	private String riderNationality;
	
	private Team team;
	
	private Set<Type> types = new HashSet<>();
}
