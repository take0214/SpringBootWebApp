package app.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.repository.TrainingMenuRepository;
import app.repository.TrainingRecordRepository;

@Service
public class RecordService {

	@Autowired TrainingRecordRepository trainingRecordRepository;
	
	@Autowired TrainingMenuRepository trainingMenuRepository;
	
	public void registerRecord(String englishName, Date date, int weight) {
		
		trainingRecordRepository.insert(englishName, date, weight);
		
		if (weight > trainingMenuRepository.findMaxWeight(englishName)) {
			trainingMenuRepository.updateMaxWeight(weight);
		}
	}
}
