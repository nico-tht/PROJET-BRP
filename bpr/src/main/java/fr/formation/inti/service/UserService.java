package fr.formation.inti.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import fr.formation.inti.entity.Users;

public interface UserService extends UserDetailsService {

	Users findUserBy(String userName, String password);
	
	Users findByUsername(String username);
	
//	public List<String> getUserRoles(String userName);
	
//	void addUser(Users user);
	
//	Users save(Users user);
	void save(Users user);
	
	void update(Users user);
	
	void deleteUser(Users user);
	
	void changePassword(Users user, String newPassword);
}