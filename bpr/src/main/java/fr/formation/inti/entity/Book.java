package fr.formation.inti.entity;
// Generated 5 mai 2022, 16:52:21 by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Book generated by hbm2java
 */
@Entity
@Table(name = "book", catalog = "test_brp")
public class Book implements java.io.Serializable {

	private Integer bookId;
	private Users users;
	@Column(name = "title")
	private String title;
	@Column(name = "isbn")
	private String isbn;
	@Column(name = "author")
	private String author;
	@Column(name = "category")
	private String category;
	@Column(name = "rental_rate")
	private Integer rentalRate;
	@Column(name = "replacement_cost")
	private Float replacementCost;
	@Column(name = "price")
	private Float price;
	private Set<Inventory> inventories = new HashSet<Inventory>(0);
	private Set<Forum> forums = new HashSet<Forum>(0);
	private Set<Photo> photos = new HashSet<Photo>(0);

	public Book() {
	}

	public Book(Users users, String title, String isbn, String author, String category, float replacementCost,
			float price) {
		this.users = users;
		this.title = title;
		this.isbn = isbn;
		this.author = author;
		this.category = category;
		this.replacementCost = replacementCost;
		this.price = price;
	}

	public Book(Users users, String title, String isbn, String author, String category, Integer rentalRate,
			float replacementCost, float price, Set<Inventory> inventories, Set<Photo> photos, Set<Forum> forums) {
		this.users = users;
		this.title = title;
		this.isbn = isbn;
		this.author = author;
		this.category = category;
		this.rentalRate = rentalRate;
		this.replacementCost = replacementCost;
		this.price = price;
		this.inventories = inventories;
		this.photos = photos;
		this.forums = forums;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "book_id", unique = true, nullable = false)
	public Integer getBookId() {
		return this.bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "title", nullable = false, length = 45)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "ISBN", nullable = false, length = 45)
	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Column(name = "author", nullable = false, length = 45)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "category", nullable = false, length = 45)
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "rental_rate")
	public Integer getRentalRate() {
		return this.rentalRate;
	}

	public void setRentalRate(Integer rentalRate) {
		this.rentalRate = rentalRate;
	}

	@Column(name = "replacement_cost", nullable = false, precision = 12, scale = 0)
	public float getReplacementCost() {
		return this.replacementCost;
	}

	public void setReplacementCost(float replacementCost) {
		this.replacementCost = replacementCost;
	}

	@Column(name = "price", nullable = false, precision = 12, scale = 0)
	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	public Set<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(Set<Inventory> inventories) {
		this.inventories = inventories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	public Set<Forum> getForums() {
		return this.forums;
	}

	public void setForums(Set<Forum> forums) {
		this.forums = forums;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	public Set<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}

}