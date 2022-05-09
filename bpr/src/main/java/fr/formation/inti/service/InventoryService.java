package fr.formation.inti.service;

import java.util.List;

import fr.formation.inti.entity.Book;
import fr.formation.inti.entity.Inventory;
import fr.formation.inti.entity.Users;

public interface InventoryService {
	
	void save(Inventory inventory);
	
	void deleteByBook(Book book);
	
	List<Inventory> findAllByUsers(Users user);
	
	List<Inventory> findAll();
	
//	Book findById(Integer id);
	
//	void deleteById(Integer id);
	
//	List<Book> findAll();
	
}