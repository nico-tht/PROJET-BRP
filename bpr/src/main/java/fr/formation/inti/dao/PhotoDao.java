package fr.formation.inti.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.inti.entity.Book;
import fr.formation.inti.entity.Photo;
import fr.formation.inti.entity.Users;

public interface PhotoDao extends JpaRepository<Photo, Integer>{

	List<Photo> findAllByUsersAndPhotoRole(Users user, String role);
	
}
