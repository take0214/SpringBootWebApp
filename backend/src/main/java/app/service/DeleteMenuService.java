package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.repository.TrainingMenuRepository;
import app.repository.TrainingRecordRepository;

@Service
public class DeleteMenuService {
	
	@Autowired
	TrainingMenuRepository trainingMenuRepository;
	
	@Autowired
	TrainingRecordRepository trainingRecordRepository;
	
	public void deleteMenu(String menu) {
		
		trainingRecordRepository.dropTable(menu);
		
		trainingMenuRepository.delete(menu);
	}
}
