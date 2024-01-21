package app.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.TrainingRecordEntity;
import app.repository.TrainingRecordRepository;

@Service
public class RecordListService {
	
	@Autowired
	TrainingRecordRepository trainingRecordRepository;

	public List<TrainingRecordEntity> getTrainingRecord(String menu) {
		
		List<Map<String, Object>> mapRecord= trainingRecordRepository.find(menu);
		
		List<TrainingRecordEntity> records = new ArrayList<TrainingRecordEntity>();
		
		for (int i = 0; i < mapRecord.size() ; i++) {
			TrainingRecordEntity trainingRecordEntity = new TrainingRecordEntity();
			
			trainingRecordEntity.setDate((Date)mapRecord.get(i).get("date"));
			trainingRecordEntity.setWeight((Integer)mapRecord.get(i).get("weight"));
			trainingRecordEntity.setFeeling((String)mapRecord.get(i).get("feeling"));
			
			records.add(trainingRecordEntity);
		}
		
		return records;
	}
}
