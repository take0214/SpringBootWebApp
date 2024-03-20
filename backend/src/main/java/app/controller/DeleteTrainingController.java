package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.common.LogUtils;
import app.mapper.TrainingListMapper;
import app.mapper.TrainingRecordMapper;

@RestController
@RequestMapping("/delete-training")
public class DeleteTrainingController {
	
	@Autowired
	TrainingListMapper trainingListMapper;
	
	@Autowired
	TrainingRecordMapper trainingRecordMapper;
	
	@GetMapping
	public ResponseEntity<Object> deleteTraining(@RequestParam("id") int id) {
		LogUtils.info("deleteTraining開始");
		
		int count;
		try {
			count = trainingListMapper.detele(id);
			trainingRecordMapper.deteleByTrainingId(id);
		} catch (Exception e) {
			LogUtils.error("トレーニングの削除に失敗しました" + e.toString());
			return ResponseEntity.internalServerError().body(e);
		}
		
		if (count < 1) {
			LogUtils.error("トレーニングの削除に失敗しました");
			return ResponseEntity.badRequest().body("");
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		
		LogUtils.info("deleteTraining終了");
 
        return ResponseEntity.ok().headers(headers).body("");
	}
}
