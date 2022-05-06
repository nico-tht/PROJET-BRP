package fr.formation.inti.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.inti.entity.Book;
import fr.formation.inti.entity.Users;


public interface BookDao extends JpaRepository<Book, Integer>{

	List<Book> findAllByUsers(Users user);
	
	Book findByBookId(Integer id);

}
