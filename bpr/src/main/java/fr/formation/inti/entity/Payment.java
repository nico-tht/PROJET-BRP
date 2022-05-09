package fr.formation.inti.entity;
// Generated 4 mai 2022, 10:40:33 by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Payment generated by hbm2java
 */
@Entity
@Table(name = "payment", catalog = "test_brp")
public class Payment implements java.io.Serializable {

	private Integer paymentId;
	private Cb cb;
	private Rental rental;
	private Users users;
	private float amount;
	private String paymentDate;
	private float commission;

	public Payment() {
	}

	public Payment(Cb cb, Rental rental, Users users, float amount, String paymentDate, float commission) {
		this.cb = cb;
		this.rental = rental;
		this.users = users;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.commission = commission;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "payment_id", unique = true, nullable = false)
	public Integer getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cb_id", nullable = false)
	public Cb getCb() {
		return this.cb;
	}

	public void setCb(Cb cb) {
		this.cb = cb;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rental_id", nullable = false)
	public Rental getRental() {
		return this.rental;
	}

	public void setRental(Rental rental) {
		this.rental = rental;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "amount", nullable = false, precision = 12, scale = 0)
	public float getAmount() {
		return this.amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@Column(name = "payment_date", nullable = false, length = 45)
	public String getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Column(name = "commission", nullable = false, precision = 12, scale = 0)
	public float getCommission() {
		return this.commission;
	}

	public void setCommission(float commission) {
		this.commission = commission;
	}

}