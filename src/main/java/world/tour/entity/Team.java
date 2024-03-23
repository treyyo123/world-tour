package world.tour.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long teamId;
	
	private String teamName;
	private String teamNationality;
	
	@OneToMany(mappedBy = "team", cascade =CascadeType.PERSIST, orphanRemoval = false)
	Set<Rider> riders = new HashSet<>();
}
