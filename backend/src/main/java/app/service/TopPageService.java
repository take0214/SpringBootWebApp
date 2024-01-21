package app.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.TrainingMenuEntity;
import app.repository.TrainingMenuRepository;

@Service
public class TopPageService {
	
	@Autowired
	TrainingMenuRepository trainingMenuRepository;

	public List<TrainingMenuEntity> getTrainingMenu() {
		
		List<Map<String, Object>> mapMenu= trainingMenuRepository.find();
		
		List<TrainingMenuEntity> menus = new ArrayList<TrainingMenuEntity>();
		
		for (int i = 0; i < mapMenu.size() ; i++) {
			TrainingMenuEntity trainingMenuEntity = new TrainingMenuEntity();
			
			trainingMenuEntity.setName((String)mapMenu.get(i).get("name"));
			trainingMenuEntity.setEnglishName((String)mapMenu.get(i).get("english_name"));
			trainingMenuEntity.setPart((String)mapMenu.get(i).get("part"));
			trainingMenuEntity.setMaxWeight((int)mapMenu.get(i).get("max_weight"));
			trainingMenuEntity.setReps((int)mapMenu.get(i).get("reps"));
			trainingMenuEntity.setSet((int)mapMenu.get(i).get("set"));
			trainingMenuEntity.setCreateDate((Timestamp)mapMenu.get(i).get("create_date"));
			trainingMenuEntity.setUpdateDate((Timestamp)mapMenu.get(i).get("update_date"));
			
			menus.add(trainingMenuEntity);
		}
		
		return menus;
	}
}
