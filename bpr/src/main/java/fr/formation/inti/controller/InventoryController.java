package fr.formation.inti.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.inti.dao.BookDao;
import fr.formation.inti.entity.Book;
import fr.formation.inti.entity.Inventory;
import fr.formation.inti.entity.Users;
import fr.formation.inti.service.BookService;
import fr.formation.inti.service.InventoryService;
import fr.formation.inti.service.UserService;

@Controller
public class InventoryController {

	@Autowired
	private BookService bookService;

	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/addcart/{bookId}", method = RequestMethod.GET)
	protected String addcart(@PathVariable(value = "bookId",required = false) Integer bookId, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = userService.findByUsername(username);

		Book book = bookService.findById(bookId);
		
		Inventory inventory = new Inventory();
		
		List<Inventory> list = inventoryService.findAll();
		
		if (book.getUsers().equals(user)) {
			return "redirect:/accueil-logged";
		} 
		
		for (Inventory temp : list) {
			if (book.getBookId()==temp.getBook().getBookId()) {
				return "redirect:/accueil-logged";	
			}				
		}
			
		inventory.setUsers(user);
		inventory.setBook(book);

				
		inventoryService.save(inventory);
		model.addAttribute("inventoryAttr", inventory);

		return "redirect:/cart";		
		
	}
	
	@RequestMapping("/cart")
	public String search(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = userService.findByUsername(username);		
		
		List<Inventory> list = inventoryService.findAllByUsers(user);
		
//		Float price = null;
//		Float totalguarantee = null;
//		
//		for (Inventory inventory : list) {
//			price += inventory.getBook().getPrice();
//			totalguarantee += inventory.getBook().getReplacementCost();
//		}
//		Float total = price + totalguarantee;
//		
//		model.addAttribute("total", total);
//		model.addAttribute("price", price);
//		model.addAttribute("guarantee", totalguarantee);

		model.addAttribute("list", list);
		
		return "pagecart";
	}
	
	@RequestMapping(value = "/deletebookcart/{bookId}", method = RequestMethod.GET)
	protected String deletebookcart(Model model, @PathVariable(value = "bookId",required = false) Integer bookId) {
			
		Book book = bookService.findById(bookId);
		
		inventoryService.deleteByBook(book);
		
		return "redirect:/cart";
	}
	

}
