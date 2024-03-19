package world.tour.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import world.tour.entity.Rider;
import world.tour.entity.Type;

@Data
@NoArgsConstructor
public class RiderData {
	private Long riderId;
	private String firstName;
	private String lastName;
	private String riderNationality;
	private Set<TypeData> types = new HashSet<>();
	
	public RiderData(Rider rider) {
		this.riderId = rider.getRiderId();
		this.firstName = rider.getFirstName();
		this.lastName = rider.getLastName();
		this.riderNationality = rider.getRiderNationality();
		
		for(Type type : rider.getTypes()) {
			this.types.add(new TypeData(type));
		}
	}

}
