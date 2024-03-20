package app.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.common.LogUtils;
import app.entity.TrainingListEntity;
import app.entity.TrainingRecordEntity;
import app.mapper.TrainingListMapper;
import app.mapper.TrainingRecordMapper;

@RestController
@RequestMapping("/record")
public class RecordController {
	
	@Autowired
	TrainingListMapper trainingListMapper;
	
	@Autowired
	TrainingRecordMapper trainingRecordMapper;

	@GetMapping
	public ResponseEntity<Object> getRecord(@RequestParam("id") int trainingId) {
		LogUtils.info("getRecord開始");
		LogUtils.info(String.valueOf(trainingId));
		
		TrainingRecordEntity[] list = {};
		try {			
			list = trainingRecordMapper.findByTrainingId(trainingId);
		} catch (Exception e) {
			LogUtils.error("トレーニング記録一覧の取得に失敗しました");
			return ResponseEntity.internalServerError().body(e);
		}
		
		HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        
        LogUtils.info("getRecord終了");
 
        return ResponseEntity.ok().headers(headers).body(list);
	}
	
	@PostMapping
	public ResponseEntity<Object> addRecord(@RequestBody MultiValueMap<String,String> reqParam) {
		LogUtils.info("addRecord開始");
		
		long date = Long.parseLong(reqParam.getFirst("date"));
		int weight = Integer.parseInt(reqParam.getFirst("weight"));
		int reps = Integer.parseInt(reqParam.getFirst("reps"));
		int trainingId = Integer.parseInt(reqParam.getFirst("trainingId"));
				
		try {
			trainingRecordMapper.insert(new Date(date), weight, reps, trainingId);
		} catch (Exception e) {
			LogUtils.error("トレーニング記録の登録に失敗しました" + e.toString());
			return ResponseEntity.internalServerError().body(e);
		}
		
		try {
			TrainingListEntity training = trainingListMapper.findById(trainingId);
			if( weight > training.getMaxWeight()) {
				training.setMaxWeight(weight);
			}
			if( training.getLastDay().before(new Date(date))) {
				training.setLastDay(new Date(date));
			}
			
			trainingListMapper.update(trainingId, training.getMaxWeight(), training.getLastDay());
		} catch (Exception e) {
			LogUtils.error("トレーニング一覧の更新に失敗しました" + e.toString());
			return ResponseEntity.internalServerError().body(e);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		
		LogUtils.info("addRecord終了");
 
        return ResponseEntity.ok().headers(headers).body("");
	}
}
