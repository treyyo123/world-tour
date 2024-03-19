package world.tour.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import world.tour.entity.Team;

public interface TeamDao extends JpaRepository<Team, Long> {

}
