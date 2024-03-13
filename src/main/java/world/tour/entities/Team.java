package world.tour.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Team {
	private Long teamId;
	private String teamName;
	private String teamNationality;
	
	Set<Rider> riders = new HashSet<>();
}
