package world.tour.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import world.tour.entity.Rider;
import world.tour.entity.Team;

@Data
@NoArgsConstructor
public class TeamData {
	private Long teamId;
	private String teamName;
	private String teamNationality;
	Set<RiderData> riders = new HashSet<>();
	
	public TeamData(Team team) {
		this.teamId = team.getTeamId();
		this.teamName = team.getTeamName();
		this.teamNationality = team.getTeamNationality();
		
		for(Rider rider : team.getRiders()) {
			this.riders.add(new RiderData(rider));
		}
	}
}
