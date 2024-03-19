package world.tour.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import world.tour.controller.model.TeamData;
import world.tour.dao.TeamDao;
import world.tour.entity.Rider;
import world.tour.entity.Team;

@Service
public class WorldTourService {

	@Autowired
	private TeamDao teamDao;

	/*
	 *Team CRUD 
	 */
	
	@Transactional(readOnly = false)
	public TeamData saveTeam(TeamData teamData) {
		Long teamId = teamData.getTeamId();
		Team team = findOrCreateTeam(teamId);

		copyTeamFields(team, teamData);

		return new TeamData(teamDao.save(team));
	}

	private void copyTeamFields(Team team, TeamData teamData) {
		team.setTeamId(teamData.getTeamId());
		team.setTeamName(teamData.getTeamName());
		team.setTeamNationality(teamData.getTeamNationality());
	}

	private Team findOrCreateTeam(Long teamId) {
		Team team;

		if (Objects.isNull(teamId)) {
			team = new Team();
		} else {
			team = findTeamById(teamId);
			//watch for potential issues
			team.getRiders().clear();
		}

		return team;
	}

	private Team findTeamById(Long teamId) {
		return teamDao.findById(teamId)
				.orElseThrow(() -> new NoSuchElementException("Team with ID = " + teamId + "was not found."));
	}

	@Transactional(readOnly = true)
	public List<TeamData> retrieveAllTeams() {
		List<Team> teams = teamDao.findAll();
		List<TeamData> teamDatas = new LinkedList<TeamData>();
		
		for (Team team : teams) {
			TeamData transfer = new TeamData(team);
			
			transfer.getRiders().clear();
			
			teamDatas.add(transfer);
		}
		
		return teamDatas;
	}

	@Transactional(readOnly = true)
	public TeamData retrieveOneTeam(Long teamId) {
		Team team = findTeamById(teamId);
		Set<Rider> riders = team.getRiders();
		
		for(Rider rider : riders) {
			rider.getTypes().clear();
		}
		
		TeamData teamData = new TeamData(team);
		
		return teamData;
	}

}
