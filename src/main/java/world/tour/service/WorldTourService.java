package world.tour.service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import world.tour.controller.model.RiderData;
import world.tour.controller.model.TeamData;
import world.tour.controller.model.TypeData;
import world.tour.dao.RiderDao;
import world.tour.dao.TeamDao;
import world.tour.dao.TypeDao;
import world.tour.entity.Rider;
import world.tour.entity.Team;
import world.tour.entity.Type;

@Service
public class WorldTourService {

	@Autowired
	private TeamDao teamDao;

	@Autowired
	private RiderDao riderDao;

	@Autowired
	private TypeDao typeDao;
	/*
	 * Team CRUD
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
			// watch for potential issues
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

		for (Rider rider : riders) {
			rider.getTypes().clear();
		}

		TeamData teamData = new TeamData(team);

		return teamData;
	}

	@Transactional(readOnly = false)
	public void deleteTeamById(Long teamId) {
		Team team = findTeamById(teamId);
		Set<Rider> riders = team.getRiders();

		for (Rider rider : riders) {
			rider.setTeam(null);
		}

		teamDao.delete(team);
	}

	/*
	 * Rider CRUD
	 */
	@Transactional(readOnly = false)
	public RiderData saveRider(Long teamId, RiderData riderData) {
		Team team = findOrCreateTeam(teamId);
		Long riderId = riderData.getRiderId();
		Rider rider = findOrCreateRider(riderId);

		Set<Rider> riders = team.getRiders();

		copyRiderFields(rider, riderData);

		rider.setTeam(team);
		riders.add(rider);

		return new RiderData(riderDao.save(rider));
	}

	private void copyRiderFields(Rider rider, RiderData riderData) {
		rider.setRiderId(riderData.getRiderId());
		rider.setFirstName(riderData.getFirstName());
		rider.setLastName(riderData.getLastName());
		rider.setRiderNationality(riderData.getRiderNationality());
	}

	private Rider findOrCreateRider(Long riderId) {
		Rider rider;

		if (Objects.isNull(riderId)) {
			rider = new Rider();
		} else {
			rider = findRiderById(riderId);
		}

		return rider;
	}

	private Rider findRiderById(Long riderId) {
		return riderDao.findById(riderId)
				.orElseThrow(() -> new NoSuchElementException("Rider with ID= " + riderId + " was not found."));
	}

	@Transactional(readOnly = true)
	public RiderData retrieveRiderById(Long riderId) {
		Rider rider = findRiderById(riderId);

		return new RiderData(rider);
	}

	public RiderData addTypeToRider(RiderData riderData, Long typeId) {
		Long riderId = riderData.getRiderId();
		Rider rider = findRiderById(riderId);
		
		Type type = findOrCreateType(typeId);
		
		Set<Type> types = rider.getTypes();
		
		types.add(type);
		rider.setTypes(types);
		
		return new RiderData(rider);
	}

	private Type findOrCreateType(Long typeId) {
		Type type;
		
		if(Objects.isNull(typeId)) {
			type = new Type();
		}else {
			type = findTypeById(typeId);
		}
		
		return type;
	}

	private Type findTypeById(Long typeId) {
		Type type = typeDao.findById(typeId)
				.orElseThrow(() -> new NoSuchElementException("Type with ID= " + typeId + " was not found."));
		
		return type;
	}
}
