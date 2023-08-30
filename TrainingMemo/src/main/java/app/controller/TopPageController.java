package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.entity.TrainingMenuEntity;
import app.service.TopPageService;

@Controller
@RequestMapping("/toppage")
public class TopPageController {
	
	@Autowired
	TopPageService topPageService;

	@GetMapping
	public String displayTopPage(Model model) {
		
		List<TrainingMenuEntity> trainingMenuEntity = topPageService.getTrainingMenu();
		
		model.addAttribute("menuList", trainingMenuEntity);
		
		return "toppage";
	}
	
	
}
