package world.tour.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import world.tour.controller.model.RiderData;
import world.tour.controller.model.TeamData;
import world.tour.controller.model.TypeData;
import world.tour.service.WorldTourService;

@RestController
@RequestMapping("/world_tour")
@Slf4j
public class WorldTourController {
	
	@Autowired
	private WorldTourService wTS;

	/*
	 *Team CRUD 
	 */
	
	@PostMapping("/team")
	@ResponseStatus(code = HttpStatus.CREATED)
	public TeamData insertTeam (@RequestBody TeamData teamData) {
		log.info("Creating team {}.", teamData);
		
		return wTS.saveTeam(teamData);
	}
	
	@GetMapping("/team")
	public List<TeamData> retrieveTeams() {
		log.info("Retrieving all teams.");
		
		return wTS.retrieveAllTeams();
	}
	
	@GetMapping("/team/{teamId}")
	public TeamData retrieveOneTeam(@PathVariable Long teamId) {
		log.info("Retrieving team with ID= {}", teamId);
		
		return wTS.retrieveOneTeam(teamId);
	}
	
	//if time allows retrieveByTeamName
	
	@PutMapping("/team/{teamId}")
	public TeamData updateTeam(@PathVariable Long teamId, @RequestBody TeamData teamData) {
		log.info("Updating team with ID= {}", teamId);
		teamData.setTeamId(teamId);
		
		return wTS.saveTeam(teamData);
	}
	
	@DeleteMapping("/team")
	public void deleteAllTeams() {
		log.info("Attempting to delete all teams");
		
		throw new UnsupportedOperationException("Deleting all teams is not allowed.");
	}
	
	@DeleteMapping("/team/{teamId}")
	public Map<String, String> deleteTeamById(@PathVariable Long teamId){
		log.info("Deleting team with ID= {}", teamId);
		wTS.deleteTeamById(teamId);
		
		return Map.of("Message", "Team with ID= " + teamId + " was deleted successfully.");
	}
	
	//deleteTeamByName??
	
	/*
	 * Rider CRUD
	 */
	@PostMapping("/team/{teamId}/rider")
	@ResponseStatus(code = HttpStatus.CREATED)
	public RiderData insertRider(@PathVariable Long teamId, @RequestBody RiderData riderData) {
		log.info("Adding rider {} to team with ID= {}", riderData, teamId);
		
		return wTS.saveRider(teamId, riderData);
	}
	
	@GetMapping("/rider/{riderId}")
	public RiderData retrieveRiderById(@PathVariable Long riderId) {
		log.info("Retrieving rider with ID= {}", riderId);
		
		return wTS.retrieveRiderById(riderId);
	}
	
	@PutMapping("rider/{riderId}")
	public RiderData addTypeToRider(@RequestBody RiderData riderData, @PathVariable Long riderId, Long typeId) {
		log.info("Adding type to rider");
		
		return wTS.addTypeToRider(riderData, typeId);
	}
}//end of class
