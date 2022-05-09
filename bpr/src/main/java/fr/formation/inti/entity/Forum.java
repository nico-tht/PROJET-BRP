package fr.formation.inti.entity;
// Generated 4 mai 2022, 10:40:33 by Hibernate Tools 4.3.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Forum generated by hbm2java
 */
@Entity
@Table(name = "forum", catalog = "test_brp")
public class Forum implements java.io.Serializable {

	private Integer forumId;
	private Book book;
	private String name;
	private Date startDate;
	private Date endDate;
	private String message;
	private Integer etat;

	public Forum() {
	}

	public Forum(String name, Date startDate) {
		this.name = name;
		this.startDate = startDate;
	}

	public Forum(Book book, String name, Date startDate, Date endDate, String message, Integer etat) {
		this.book = book;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.message = message;
		this.etat = etat;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "forum_id", unique = true, nullable = false)
	public Integer getForumId() {
		return this.forumId;
	}

	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id")
	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", nullable = false, length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date", length = 10)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "message", length = 16777215)
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "etat")
	public Integer getEtat() {
		return this.etat;
	}

	public void setEtat(Integer etat) {
		this.etat = etat;
	}

}