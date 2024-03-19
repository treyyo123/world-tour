package world.tour.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Rider {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long riderId;
	@EqualsAndHashCode.Exclude
	private String firstName;
	
	@EqualsAndHashCode.Exclude
	private String lastName;
	
	@EqualsAndHashCode.Exclude
	private String riderNationality;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "team_id", nullable = true)
	private Team team;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "rider_type",
			joinColumns = @JoinColumn(name = "rider_id"),
			inverseJoinColumns = @JoinColumn(name = "type_id")
			)
	private Set<Type> types = new HashSet<>();
}
