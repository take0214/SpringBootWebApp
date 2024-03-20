package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.common.LogUtils;
import app.entity.TrainingListEntity;
import app.mapper.TrainingListMapper;

@RestController
@RequestMapping("/training")
public class TrainingController {
	
	@Autowired
	TrainingListMapper trainingListMapper;

	@GetMapping
	public ResponseEntity<Object> getTraining(Model model) {
		LogUtils.info("getTraining開始");
		
		TrainingListEntity[] list = null;
		try {			
			list = trainingListMapper.find();
		} catch (Exception e) {
			LogUtils.error("トレーニング一覧の取得に失敗しました");
			return ResponseEntity.internalServerError().body(e);
		}
		
		HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        
        LogUtils.info("getTraining終了");
 
        return ResponseEntity.ok().headers(headers).body(list);
	}
	
	@PostMapping
	public ResponseEntity<Object> addTraining(@RequestBody MultiValueMap<String,String> reqParam) {
		LogUtils.info("addTraining開始");
		
		try {
			trainingListMapper.insert(reqParam.getFirst("name"), reqParam.getFirst("bodyPart"));			
		} catch (Exception e) {
			LogUtils.error("トレーニングの登録に失敗しました" + e.toString());
			return ResponseEntity.internalServerError().body(e);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		
		LogUtils.info("addTraining終了");
 
        return ResponseEntity.ok().headers(headers).body("");
	}
}
