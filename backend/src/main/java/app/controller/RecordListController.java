package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.entity.TrainingRecordEntity;
import app.service.RecordListService;

@Controller
@RequestMapping("/recordlist")
public class RecordListController {

	@Autowired RecordListService recordListService;
	
	@GetMapping
	public String displayRecordPage(@RequestParam("menu") String menu, Model model) {
		
		List<TrainingRecordEntity> records = recordListService.getTrainingRecord(menu);
		
		model.addAttribute("recordList", records);
		
		return "recordlist";
	}
}
