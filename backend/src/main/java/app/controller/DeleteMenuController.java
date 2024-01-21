package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.service.DeleteMenuService;

@Controller
@RequestMapping("/deletemenu")
public class DeleteMenuController {
	
	@Autowired 
	DeleteMenuService deleteMenuService;

	@GetMapping
	public String displayRecordForm(@RequestParam("menu") String menu, Model model) {

		deleteMenuService.deleteMenu(menu);

		return "redirect:/toppage";
	}

}
