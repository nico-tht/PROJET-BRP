package fr.formation.inti.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.inti.entity.Book;
import fr.formation.inti.entity.Users;


public interface BookDao extends JpaRepository<Book, Integer>{

	List<Book> findAllByUsers(Users user);
	
	List<Book> findAllByCategory(String category);
	
	
	@Query(value = "select * from book b where b.title like %:keyword% or b.author like %:keyword% or b.category like %:keyword% or b.isbn like %:keyword%", nativeQuery = true)
	List<Book> findByKeyword(String keyword);
	
	Book findByBookId(Integer id);

}
