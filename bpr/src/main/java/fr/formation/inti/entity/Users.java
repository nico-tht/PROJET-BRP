package fr.formation.inti.entity;
// Generated 4 mai 2022, 10:40:33 by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.UniqueConstraint;


/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users", catalog = "test_brp", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Users implements java.io.Serializable {

	private Integer userId;
	@Column(name = "address")
	private Address address;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "email")
	private String email;
	private String phone;
	private Set<Rental> rentals = new HashSet<Rental>(0);
	private Set<Book> books = new HashSet<Book>(0);
	private Set<Photo> photos = new HashSet<Photo>(0);
	private Set<UsersRoles> usersRoleses = new HashSet<UsersRoles>(0);
	private Set<Inventory> inventories = new HashSet<Inventory>(0);
	private Set<Payment> payments = new HashSet<Payment>(0);

	public Users() {
	}

	public Users(String username, String password, String firstName, String lastName, String email) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Users(Address address, String username, String password, String firstName, String lastName, String email,
			String phone, Set<Rental> rentals, Set<Book> books, Set<Photo> photos, Set<UsersRoles> usersRoleses,
			Set<Inventory> inventories, Set<Payment> payments) {
		this.address = address;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.rentals = rentals;
		this.books = books;
		this.photos = photos;
		this.usersRoleses = usersRoleses;
		this.inventories = inventories;
		this.payments = payments;
	}
	
	public Users(Address address, String username, String password, String firstName, String lastName, String email) {
		this.email = email;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Column(name = "username", unique = true, nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "first_name", nullable = false, length = 45)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false, length = 45)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "email", nullable = false, length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone", length = 45)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Rental> getRentals() {
		return this.rentals;
	}

	public void setRentals(Set<Rental> rentals) {
		this.rentals = rentals;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Book> getBooks() {
		return this.books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users",cascade = CascadeType.PERSIST)
	public Set<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users",cascade = CascadeType.ALL)
	public Set<UsersRoles> getUsersRoleses() {
		return this.usersRoleses;
	}

	public void setUsersRoleses(Set<UsersRoles> usersRoleses) {
		this.usersRoleses = usersRoleses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users", cascade = CascadeType.ALL)
	public Set<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(Set<Inventory> inventories) {
		this.inventories = inventories;
	}

	@Override
	public String toString() {
		return username;
	}
	
	

}