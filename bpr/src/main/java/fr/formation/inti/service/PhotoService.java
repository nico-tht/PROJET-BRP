package fr.formation.inti.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import fr.formation.inti.entity.Book;
import fr.formation.inti.entity.Photo;
import fr.formation.inti.entity.Users;

@Service
public interface PhotoService {
	
	void saveImage(Photo photo);
	
	List<Photo> getAllActiveImages();
	
	Optional<Photo> getImageById(Integer photoId);
	
	List<Photo> findAllByUsersAndPhotoRole(Users user, String role);
	
	List<Photo> findAllByPhotoRole(String role);
	
	Photo findByUsersAndPhotoRole(Users user, String role);
}