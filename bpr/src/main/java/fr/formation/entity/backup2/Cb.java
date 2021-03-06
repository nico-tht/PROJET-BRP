package fr.formation.entity.backup2;
// Generated 4 mai 2022, 08:23:27 by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Cb generated by hbm2java
 */
@Entity
@Table(name = "cb", catalog = "test_brp")
public class Cb implements java.io.Serializable {

	private Integer cbId;
	private String cbHolder;
	private String cbCryptogram;
	private Set<Payment> payments = new HashSet<Payment>(0);

	public Cb() {
	}

	public Cb(String cbHolder, String cbCryptogram) {
		this.cbHolder = cbHolder;
		this.cbCryptogram = cbCryptogram;
	}

	public Cb(String cbHolder, String cbCryptogram, Set<Payment> payments) {
		this.cbHolder = cbHolder;
		this.cbCryptogram = cbCryptogram;
		this.payments = payments;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "cb_id", unique = true, nullable = false)
	public Integer getCbId() {
		return this.cbId;
	}

	public void setCbId(Integer cbId) {
		this.cbId = cbId;
	}

	@Column(name = "cb_holder", nullable = false, length = 45)
	public String getCbHolder() {
		return this.cbHolder;
	}

	public void setCbHolder(String cbHolder) {
		this.cbHolder = cbHolder;
	}

	@Column(name = "cb_cryptogram", nullable = false, length = 45)
	public String getCbCryptogram() {
		return this.cbCryptogram;
	}

	public void setCbCryptogram(String cbCryptogram) {
		this.cbCryptogram = cbCryptogram;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cb")
	public Set<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

}
