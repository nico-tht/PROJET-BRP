package fr.formation.inti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.inti.dao.InventoryDao;
import fr.formation.inti.entity.Book;
import fr.formation.inti.entity.Inventory;
import fr.formation.inti.entity.Users;


@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryDao inventoryDao;

	@Override
	public void save(Inventory inventory) {
		inventoryDao.save(inventory);		
	}

	@Override
	public void deleteByBook(Book book) {
		inventoryDao.deleteByBook(book);
		
	}

	@Override
	public List<Inventory> findAllByUsers(Users user) {
		// TODO Auto-generated method stub
		return inventoryDao.findAllByUsers(user);
	}

	@Override
	public List<Inventory> findAll() {
		// TODO Auto-generated method stub
		return inventoryDao.findAll();
	}



}

