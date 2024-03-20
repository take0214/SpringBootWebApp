package app.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.common.LogUtils;
import app.entity.TrainingRecordEntity;
import app.mapper.TrainingListMapper;
import app.mapper.TrainingRecordMapper;

@RestController
@RequestMapping("/dayly-record")
public class DaylyRecordController {
	
	@Autowired
	TrainingListMapper trainingListMapper;
	
	@Autowired
	TrainingRecordMapper trainingRecordMapper;

	@GetMapping
	public ResponseEntity<Object> getDaylyRecord(@RequestParam("date") long date) {
		LogUtils.info("getDaylyRecord開始");
		
		TrainingRecordEntity[] list = {};
		try {			
			list = trainingRecordMapper.findByDate(new Date(date));
		} catch (Exception e) {
			LogUtils.error("トレーニング記録一覧の取得に失敗しました");
			return ResponseEntity.internalServerError().body(e);
		}
		
		HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        
        LogUtils.info("getDaylyRecord終了");
 
        return ResponseEntity.ok().headers(headers).body(list);
	}
}
