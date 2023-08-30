package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.param.RegisterParam;
import app.service.RegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired RegisterService registerService;
	
	@GetMapping
	public String displayRegiserPage(Model model) {
		
		model.addAttribute("registerParam", new RegisterParam());
		return "register";
	}
	
	@PostMapping
	public String registerTrainingMenu(@ModelAttribute RegisterParam registerParam ) {
		
		registerService.registerTrainingMenu(
				registerParam.getName(), 
				registerParam.getEnglishName(), 
				registerParam.getPart(), 
				registerParam.getReps(),
				registerParam.getSet());
		
		return "redirect:/toppage";
	}

}
