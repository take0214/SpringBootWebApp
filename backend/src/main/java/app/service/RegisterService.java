package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.repository.TrainingMenuRepository;
import app.repository.TrainingRecordRepository;

@Service
public class RegisterService {

	@Autowired TrainingMenuRepository trainingMenuRepository;
	
	@Autowired TrainingRecordRepository trainingRecordRepository;
	
	public void registerTrainingMenu(String name, String englishName, String part, int reps, int set) {
		
		trainingMenuRepository.insert(name, englishName, part, reps, set);
		
		trainingRecordRepository.createTable(englishName);
	}
}
