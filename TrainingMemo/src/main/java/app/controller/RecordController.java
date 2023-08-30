package app.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.service.RecordService;

@Controller
@RequestMapping("/record")
public class RecordController {
	
	@Autowired RecordService recordService;

	@GetMapping
	public String displayRecordForm(@RequestParam("menu") String menu, Model model) {

		model.addAttribute("menu", menu);
		
		Date todaysDate = new Date(System.currentTimeMillis());
		
		model.addAttribute("date", todaysDate);

		return "/record";
	}
	
	@GetMapping("/previous")
	public String displayRecordFormPrevious(@RequestParam Map<String, Object> param, Model model) {

		String menu = (String)param.get("menu");
		String date = (String)param.get("date");
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(Date.valueOf(date));
		cal.add(Calendar.DATE, -1);
		Date previousDate = new Date(cal.getTime().getTime());
 		
		model.addAttribute("menu", menu);
		model.addAttribute("date", previousDate);

		return "/record";
	}
	
	@GetMapping("/next")
	public String displayRecordFormNext(@RequestParam Map<String, Object> param, Model model) {

		String menu = (String)param.get("menu");
		String date = (String)param.get("date");
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(Date.valueOf(date));
		cal.add(Calendar.DATE, 1);
		Date nextDate = new Date(cal.getTime().getTime());
 		
		model.addAttribute("menu", menu);
		model.addAttribute("date", nextDate);

		return "/record";
	}


	@PostMapping
	public String registerRecord(
			@RequestParam Map<String, String> params) {
		
		String menu = params.get("menu");
		String strDate = params.get("date");
		String strWeight = params.get("weight");
		
		Date date = Date.valueOf(strDate);
		int weight = Integer.parseInt(strWeight);
		
		recordService.registerRecord(menu, date, weight);

		return "redirect:/toppage";
	}
}
