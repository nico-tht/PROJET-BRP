package fr.formation.inti.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.formation.inti.entity.Users;
import fr.formation.inti.service.UserService;

@Controller
public class PasswordController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public String processChangePassword(HttpServletRequest request, HttpServletResponse response,
	        Model model, RedirectAttributes ra,
	        @AuthenticationPrincipal Authentication authentication) throws ServletException {

	    String oldPassword = request.getParameter("oldPassword");
	    String newPassword = request.getParameter("newPassword");
		
	    if (oldPassword.equals(newPassword)) {
	        model.addAttribute("message", "Your new password must be different than the old one.");
	         
	        return "redirect:/edit";
	    }
	    
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = userService.findByUsername(username);
	     
	    if (!bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
	        ra.addAttribute("message", "Your old password is incorrect.");
	        return "redirect:/edit";
	         
	    } else {
	        userService.changePassword(user, newPassword);
	        request.logout();
	        ra.addFlashAttribute("message", "You have changed your password successfully. "
	                + "Please login again.");
	         
	        return "redirect:/login";          
	    }
	    
	}
	
}
