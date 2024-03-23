package world.tour.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import world.tour.entity.Type;

public interface TypeDao extends JpaRepository<Type, Long> {

}
