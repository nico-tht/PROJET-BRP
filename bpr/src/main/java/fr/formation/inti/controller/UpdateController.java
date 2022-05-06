//package fr.formation.inti.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import fr.formation.inti.entity.Users;
//import fr.formation.inti.service.UserService;
//
//@Controller
//public class UpdateController {
//	
//	@Autowired
//	private UserService userService;
//
//	@RequestMapping(value= "/update", method = RequestMethod.POST)
//	public void update(Model model, Authentication authentication ) {
//		String username = authentication.getName();
//		Users user = userService.findByUsername(username);
//		model.addAttribute("user", user);
//		
//	}
//	
//}
