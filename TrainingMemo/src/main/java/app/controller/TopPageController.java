package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/toppage")
public class TopPageController {

	@GetMapping
	public String displayTopPage() {
		return "toppage";
	}
}
