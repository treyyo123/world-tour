package world.tour.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import world.tour.entity.Rider;

public interface RiderDao extends JpaRepository<Rider, Long> {

}
