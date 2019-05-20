package sg.edu.nus.lapsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.lapsystem.model.Position;

public interface PositionRepository extends JpaRepository<Position, String> {
	Position findByPositionName(String positionName);
}
