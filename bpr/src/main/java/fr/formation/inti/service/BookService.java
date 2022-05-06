package fr.formation.inti.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetailsService;

import fr.formation.inti.entity.Book;
import fr.formation.inti.entity.Users;

public interface BookService {
	
//	@Query("insert into book (title,ISBN,author,category,replacement_cost,price,user_id) values (?,?,?,?,?,?,?)")
	void save(Book book);
	
	List<Book> findAllByUsers(Users user);
	
	Book findById(Integer id);
	
}