package fr.formation.inti.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.inti.dao.BookDao;
import fr.formation.inti.entity.Book;
import fr.formation.inti.entity.Inventory;
import fr.formation.inti.entity.Users;


@Service("bookServiceImpl")
@Transactional
public class BookServiceImpl implements BookService {
	
	
	@Autowired
	private BookDao bookDao;
	


	@Override
	public void save(Book book) {

		bookDao.save(book);		
	}



	@Override
	public List<Book> findAllByUsers(Users user) {
		
		return bookDao.findAllByUsers(user);
	}



	@Override
	public Book findById(Integer id) {
		// TODO Auto-generated method stub
		return bookDao.findByBookId(id); 
	}



	@Override
	public void deleteById(Integer id) {
		bookDao.deleteById(id);
		
	}



	@Override
	public List<Book> findAll() {
		
		return bookDao.findAll();
	}



	@Override
	public List<Book> findAllByCategory(String category) {
		
		return bookDao.findAllByCategory(category);
	}



	@Override
	public List<Book> findByKeyword(String keyword) {

		return bookDao.findByKeyword(keyword);
	}



//	@Override
//	public List<Book> findAllByInventoryAndUsers(Inventory inventory, Users user) {
//		// TODO Auto-generated method stub
//		return bookDao.findAllByInventoryAndUsers(inventory, user);
//	}

}

