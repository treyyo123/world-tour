package world.tour.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import world.tour.controller.model.TeamData;
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
	
	@PutMapping("/team/{teamId}")
	public TeamData updateTeam(@PathVariable Long teamId, @RequestBody TeamData teamData) {
		log.info("Updating team with ID= {}", teamId);
		teamData.setTeamId(teamId);
		
		return wTS.saveTeam(teamData);
	}
}//end of class
