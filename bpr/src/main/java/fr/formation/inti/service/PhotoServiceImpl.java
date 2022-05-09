package fr.formation.inti.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import fr.formation.inti.dao.PhotoDao;
import fr.formation.inti.entity.Book;
import fr.formation.inti.entity.Photo;
import fr.formation.inti.entity.Users;


@Service("photoServiceImpl")
@Transactional
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	private PhotoDao photoDao;

//	@Autowired
//	private UserService userService;
//	

	@Override
	public void saveImage(Photo photo) {
		photoDao.save(photo);
		
	}

	@Override
	public List<Photo> getAllActiveImages() {
		return photoDao.findAll();
	}

	@Override
	public Optional<Photo> getImageById(Integer photoId) {
		return photoDao.findById(photoId);
	}

	@Override
	public List<Photo> findAllByUsersAndPhotoRole(Users user, String role) {
		
		return photoDao.findAllByUsersAndPhotoRole(user, role);
	}

	@Override
	public Photo findByUsersAndPhotoRole(Users user, String role) {
		// TODO Auto-generated method stub
		return photoDao.findByUsersAndPhotoRole(user, role);
	}

	@Override
	public List<Photo> findAllByPhotoRole(String role) {
		// TODO Auto-generated method stub
		return photoDao.findAllByPhotoRole(role);
	}






}

