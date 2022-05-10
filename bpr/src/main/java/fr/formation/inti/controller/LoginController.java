package fr.formation.inti.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.inti.entity.Address;
import fr.formation.inti.entity.Users;
import fr.formation.inti.service.UserService;

@Controller
public class LoginController {

//	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	protected String loginPage(Model model) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	protected String loginConnection(Model model, @ModelAttribute Users user) {
		user = userService.findUserBy(user.getUsername(), user.getPassword());
		if (user != null) {
			return "redirect:/accueil-logged";
		} else {
			model.addAttribute("error", "Login ou mot de passe incorrect");
			return "login";
		}
	}


	@RequestMapping("/signup")
	public String signup(Model model, @ModelAttribute Users user) {
		model.addAttribute("user", user);
		return "signup";
	}

	@RequestMapping("/")
	public String homepage(Model model) {
		return "accueil";
	}

	@RequestMapping("/edit")
	public String edit(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = userService.findByUsername(username);
		model.addAttribute("user", user);
		return "pageuser";
	}



	@RequestMapping("/forum")
	public String forum(Model model) {
		return "forum";
	}

	
	@RequestMapping("/addbook")
	public String addbook(Model model) {
		return "pageaddbook";
	}

//	@RequestMapping(value="/add", method = RequestMethod.POST)
//	protected String addUser(@ModelAttribute("user") Users user, Model model, BindingResult result) {
//
//		userService.save(user);
//		model.addAttribute("userAttr", user);
//		
//		return "redirect:/login";
//	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	protected String addUser(@ModelAttribute("user") Users user, Model model, BindingResult result) {
//String pwd = user.getPassword();
//String password = encoder.encode(pwd);
//user.setPassword(password);
//		user.getPassword()
		String p = new String(new BCryptPasswordEncoder().encode(user.getPassword()));
//		user.setPassword(p);
//		Users useradd = new Users();
//		useradd.setEmail(user.getEmail());
//		useradd.setFirstName(user.getFirstName());
//		useradd.setLastName(user.getLastName());
//		useradd.setUsername(user.getUsername());
//		useradd.setPassword(user.getPassword());
//		useradd.setAddress(user.getAddress());
		user.setPassword(p);
		userService.save(user);
		model.addAttribute("userAttr", user);

		return "redirect:/recapuser";
	}
	
	@RequestMapping("/recapuser")
	public String recapuser(Model model) {
		
		return "recapsignup";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	protected String updateUser(@ModelAttribute("user") Users user, Model model, BindingResult result) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users userupdate = userService.findByUsername(username);
		userupdate.setFirstName(user.getFirstName());
		userupdate.setLastName(user.getLastName());
		userupdate.setEmail(user.getEmail());
		userupdate.setAddress(user.getAddress());
		

		userService.update(userupdate);
		model.addAttribute("userAttr", userupdate);
		return "pageuser";
	}
	
	@RequestMapping(value = "/profileinfo", method = RequestMethod.POST)
	protected String profileinfo(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = userService.findByUsername(username);
		String city = user.getAddress().getCity();

		model.addAttribute("username", user);
		model.addAttribute("city", city);
		return "redirect:/profile";
	}
	
//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	protected String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		String username = request.getParameter("username");
//		String firstName = request.getParameter("firstName");
//		String lastName = request.getParameter("lastName");
//		String email = request.getParameter("email");
//		String address = request.getParameter("address");
//		String city = request.getParameter("city");
//		String country = request.getParameter("country");
//		String zipCode = request.getParameter("zipCode");
//
//
//		Users user = userService.findByUsername(username);
//		Address addressUpdate = new Address(address,city,zipCode,country);
//		
//		user.setUsername(username);
//		user.setFirstName(firstName);
//		user.setLastName(lastName);
//		user.setEmail(email);
//		user.setAddress(addressUpdate);
//		user.setLastName(lastName);
//
//		userService.save(user);
//		
//		return "redirect:/pageuser";
//		
//	}

	@RequestMapping(value = "/deleteuser", method = RequestMethod.GET)
	protected String deleteuser() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = userService.findByUsername(username);
		
		userService.deleteUser(user);
		
		
		return "redirect:/";
	}
	
	@RequestMapping("/payment")
	public String payment(Model model) {
		return "paymentpage";
	}
	@RequestMapping("/paypal")
	public String paypal(Model model) {
		return "paypalpaymentpage";
	}
	@RequestMapping("/paymentconfirmed")
	public String paymentconfirmed(Model model) {
		return "paymentConfirmed";
	}
	
	@RequestMapping("/booknotavailable")
	public String twobook(Model model) {
		return "addtwotimebook";
	}
	
	@RequestMapping("/itsyourbook")
	public String yourbook(Model model) {
		return "addyourbook";
	}

}
