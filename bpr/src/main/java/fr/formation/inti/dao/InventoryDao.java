package fr.formation.inti.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.Book;
import fr.formation.inti.entity.Inventory;
import fr.formation.inti.entity.Users;


public interface InventoryDao extends JpaRepository<Inventory, Integer>{
	
	void deleteByBook(Book book);
	
//	Book findByBookId(Integer id);
	
	List<Inventory> findAllByUsers(Users user);

}
