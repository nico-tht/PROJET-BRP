package fr.formation.inti.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import fr.formation.inti.entity.Book;
import fr.formation.inti.entity.Photo;
import fr.formation.inti.entity.Users;
import fr.formation.inti.service.BookService;
import fr.formation.inti.service.PhotoService;
import fr.formation.inti.service.UserService;

@Controller
public class PhotoController {

	@Value("${uploadDir}")
	private String uploadFolder;

	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	
	@RequestMapping(value = "/addProfilePhoto", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> createProduct(Model model, HttpServletRequest request,final @RequestParam("image") MultipartFile file) {
		try {
			//String uploadDirectory = System.getProperty("user.dir") + uploadFolder;
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			log.info("uploadDirectory:: " + uploadDirectory);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			log.info("FileName: " + file.getOriginalFilename());
			if (fileName == null || fileName.contains("..")) {
				model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
				return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
			}
			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					log.info("Folder Created");
					dir.mkdirs();
				}
				// Save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				log.info("in catch");
				e.printStackTrace();
			}
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			Users userupdate = userService.findByUsername(username);
			
			byte[] imageData = file.getBytes();
			Photo photo = new Photo();
			photo.setPhoto(imageData);
			photo.setPhotoRole("PROFILE");
			photo.setUsers(userupdate);
			photoService.saveImage(photo);
			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
			return new ResponseEntity<>("Product Saved With File - " + fileName, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception: " + e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/addPrimaryPhoto", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> addprimaryphoto(Model model, HttpServletRequest request,final @RequestParam("image") MultipartFile file) {
		try {
			//String uploadDirectory = System.getProperty("user.dir") + uploadFolder;
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			log.info("uploadDirectory:: " + uploadDirectory);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			log.info("FileName: " + file.getOriginalFilename());
			if (fileName == null || fileName.contains("..")) {
				model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
				return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
			}
			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					log.info("Folder Created");
					dir.mkdirs();
				}
				// Save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				log.info("in catch");
				e.printStackTrace();
			}
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			Users userupdate = userService.findByUsername(username);
			
			byte[] imageData = file.getBytes();
			Photo photo = new Photo();
			photo.setPhoto(imageData);
			photo.setPhotoRole("PRIMARY");
			photo.setUsers(userupdate);
			photoService.saveImage(photo);
			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
			return new ResponseEntity<>("Product Saved With File - " + fileName, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception: " + e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
//	@RequestMapping(value = "/image/display/{id}", method = RequestMethod.GET)
//	@ResponseBody
//	void showImage(@PathVariable("id") Integer id, HttpServletResponse response, Optional<Photo> imageGallery,Model model)
//			throws ServletException, IOException {
//		log.info("Id :: " + id);
//		imageGallery = photoService.getImageById(id);
//		model.addAttribute("photo", id);
//		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
//		response.getOutputStream().write(imageGallery.get().getPhoto());
//		response.getOutputStream().close();
//	}
//	@ResponseBody
	@RequestMapping(value = "/image/display/{id}", method = RequestMethod.GET)
	public String showImage(@PathVariable("id") Integer id, HttpServletResponse response, Optional<Photo> imageGallery,Model model)
			throws ServletException, IOException {
		log.info("Id :: " + id);
		imageGallery = photoService.getImageById(id);
		ArrayList<Photo> content = new ArrayList<Photo>();
		imageGallery.ifPresent(content::add);
		model.addAttribute("imageGallery", imageGallery);
		
//		byte[] encodeBase64 = Base64.getEncoder().encode(imageGallery.get().getPhoto());
//		String base64Encoded = new String(encodeBase64, "UTF-8");
		String base64Encoded = Base64.getMimeEncoder().encodeToString(imageGallery.get().getPhoto());
		model.addAttribute("contentImage", base64Encoded );
		
		ModelAndView modelAndView = new ModelAndView("view");
		modelAndView.addObject("contentImage",base64Encoded );
		
		
		
//		return modelAndView;
		return "listphoto";
		
//		
//		model.addAttribute("photo", id);
//		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
//		response.getOutputStream().write(imageGallery.get().getPhoto());
//		response.getOutputStream().close();
//		
//		return null;
	}

	@RequestMapping(value = "/image/imageDetails", method = RequestMethod.GET)
	String showProductDetails(@RequestParam("photoId") Integer id, Optional<Photo> photo, Model model) {
		try {
			log.info("Id :: " + id);
			if (id != 0) {
				photo = photoService.getImageById(id);
			
				log.info("products :: " + photo);
				if (photo.isPresent()) {
					model.addAttribute("id", photo.get().getPhotoId());
					model.addAttribute("description", photo.get().getPhotoRole());
					model.addAttribute("name", photo.get().getUsers());
					return "imagedetails";
				}
				return "pageuser";
			}
		return "redirect:/home";
		} catch (Exception e) {
			e.printStackTrace();
			return "pageuser";
		}	
	}

	@RequestMapping(value = "/image/show", method = RequestMethod.GET)
	String show(Model map) {
		List<Photo> images = photoService.getAllActiveImages();
		map.addAttribute("images", images);
		return "pageuser";
	}
	
//	@ResponseBody
	@RequestMapping(path="/list")
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = userService.findByUsername(username);
		
		String role = "PROFILE";
		
		List<Photo> list = photoService.findAllByUsersAndPhotoRole(user, role);
		model.addAttribute("list", list); 

		return "listphoto";

	}
	
	@RequestMapping("/accueil-logged")
	public String welcome(Model model) {
		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String username = auth.getName();
//		Users user = userService.findByUsername(username);
//		
//		String role = "PRIMARY";
//		
//		List<Photo> listP = photoService.findAllByPhotoRole(role);
//		model.addAttribute("list", listP); 
		
		List<Book> list = bookService.findAll();
		model.addAttribute("list", list);
		
		return "accueil-logged";
	}
	
	
	
	
	
	@RequestMapping(value = "/image/profile", method = RequestMethod.GET)
	public String profileImage(HttpServletResponse response, Optional<Photo> imageGallery,Model model)
			throws ServletException, IOException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = userService.findByUsername(username);
		
		String role = "PROFILE";
		
		Photo photo = photoService.findByUsersAndPhotoRole(user, role);
		
//		imageGallery = photo;
		ArrayList<Photo> content = new ArrayList<Photo>();
//		imageGallery.ifPresent(content::add);
		model.addAttribute("imageGallery", photo);

		
//		String base64Encoded = Base64.getMimeEncoder().encodeToString(imageGallery.get().getPhoto());
		String base64Encoded = Base64.getMimeEncoder().encodeToString(photo.getPhoto());
		model.addAttribute("contentImage", base64Encoded );
		
		ModelAndView modelAndView = new ModelAndView("view");
		modelAndView.addObject("contentImage",base64Encoded );
		
		

		return "listphoto";
		
	}
	
	@RequestMapping("/profile")
	public String profile(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = userService.findByUsername(username);
		
		String role = "PROFILE";
		String city = user.getAddress().getCity();
		
		Photo photo = photoService.findByUsersAndPhotoRole(user, role);
		

		ArrayList<Photo> content = new ArrayList<Photo>();

		model.addAttribute("imageGallery", photo);		

		String base64Encoded = Base64.getMimeEncoder().encodeToString(photo.getPhoto());
		model.addAttribute("contentImage", base64Encoded );
		
		ModelAndView modelAndView = new ModelAndView("view");
		modelAndView.addObject("contentImage",base64Encoded );
		
		model.addAttribute("username", user);
		model.addAttribute("city", city);
		
		return "profilepage";
	}
	
//	@RequestMapping("/primary")
//	public String primary(Model model) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String username = auth.getName();
//		Users user = userService.findByUsername(username);
//		
//		String role = "PROFILE";
//		String city = user.getAddress().getCity();
//		
//		Photo photo = photoService.findByUsersAndPhotoRole(user, role);
//		
//
//		ArrayList<Photo> content = new ArrayList<Photo>();
//
//		model.addAttribute("imageGallery", photo);		
//
//		String base64Encoded = Base64.getMimeEncoder().encodeToString(photo.getPhoto());
//		model.addAttribute("contentImage", base64Encoded );
//		
//		ModelAndView modelAndView = new ModelAndView("view");
//		modelAndView.addObject("contentImage",base64Encoded );
//		
//		model.addAttribute("username", user);
//		model.addAttribute("city", city);
//		
//		return "profilepage";
//	}
	
//	@RequestMapping(value = "/listphoto", method = RequestMethod.GET)
//	protected String loginPage(Model model) {
//		return "listphoto";
//	}
	
	
	

		
}
	



