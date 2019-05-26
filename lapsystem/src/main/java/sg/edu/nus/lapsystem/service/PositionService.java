package sg.edu.nus.lapsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.lapsystem.model.Position;
import sg.edu.nus.lapsystem.repository.PositionRepository;


@Service
public class PositionService {
	@Autowired
	private PositionRepository pr;
	
	public void save(Position position) {
		pr.saveAndFlush(position);
	}
}
