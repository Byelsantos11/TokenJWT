package com.Loja.com.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


//Classe de renderizar as pages
@Controller
public class PageController {
	
	@GetMapping("/")
	public String login() {
		return "Login";
	}
	
	@GetMapping("/Login.html")
	public  String Login() {
		return "Login"; 
}
	
	@GetMapping("/AdmHome.html")
	public String HomeAdm() {
		return "AdmHome";
	}
	

}
