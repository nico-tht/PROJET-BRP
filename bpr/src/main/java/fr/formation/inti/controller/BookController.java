package fr.formation.inti.controller;

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

import fr.formation.inti.entity.Book;
import fr.formation.inti.entity.Users;
import fr.formation.inti.service.BookService;
import fr.formation.inti.service.UserService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/newbook", method = RequestMethod.POST)
	protected String addbook(@ModelAttribute("book") Book book, Model model, BindingResult result,
			@RequestParam String title, @RequestParam String category, @RequestParam String author,
			@RequestParam String isbn, @RequestParam Float price, @RequestParam Float replacementCost) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = userService.findByUsername(username);

		
//		book.setTitle(title);
//		book.setAuthor(author);
//		book.setCategory(category);
//		book.setIsbn(isbn);
//		book.setPrice(price);
//		book.setReplacementCost(replacementCost);
		book.setUsers(user);

		bookService.save(book);
		model.addAttribute("bookAttr", book);

		return "redirect:/accueil-logged";
	}
	
	@RequestMapping(value = "/listbook", method = RequestMethod.GET)
	protected String listbook(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = userService.findByUsername(username);
		
		List<Book> list = bookService.findAllByUsers(user);
		model.addAttribute("list", list);
	
		return "listbook";
	}
	
	@RequestMapping(value = "/editbook/{bookId}", method = RequestMethod.GET)
	protected String editbook(Model model, @PathVariable(value = "bookId",required = false) Integer bookId) {


		Book book = bookService.findById(bookId);
		model.addAttribute("book", book);
		
		return "pageeditbook";
	}
	
	@RequestMapping(value = "/updatebook", method = RequestMethod.POST)
	protected String updatebook(@ModelAttribute("book") Book book, Model model, BindingResult result, @RequestParam Integer bookId) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = userService.findByUsername(username);

		Book bookupdate = new Book();
		
		bookupdate.setTitle(book.getTitle());
		bookupdate.setAuthor(book.getAuthor());
		bookupdate.setCategory(book.getCategory());
		bookupdate.setIsbn(book.getIsbn());
		bookupdate.setPrice(book.getPrice());
		bookupdate.setReplacementCost(book.getReplacementCost());
		bookupdate.setUsers(book.getUsers());

		bookService.save(book);
		model.addAttribute("bookAttr", book);

		return "redirect:/accueil-logged";
	}

}
