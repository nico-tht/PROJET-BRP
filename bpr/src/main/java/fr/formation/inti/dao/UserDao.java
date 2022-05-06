package fr.formation.inti.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.inti.entity.Users;

public interface UserDao extends JpaRepository<Users, Integer>{

	@Query("select u from Users u where u.password=?2 and u.username=?1")
	Users findUserBy(String username, String password);
	
	@Query("select u from Users u where u.username = ?1")
	Users findByUsername(String username);
	
//	// [USER,ADMIN,..]
//	public List<String> getUserRoles(String userName);
	
//	@Query("insert into Users (USERNAME, PASSWORD, first_name, last_name, email) values (?, ?, ?, ?, ?) and insert into Address (address, zip_code, city, country) values (?, ?, ?, ?)") 
	
}
