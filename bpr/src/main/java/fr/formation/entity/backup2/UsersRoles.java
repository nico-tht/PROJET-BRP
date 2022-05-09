package fr.formation.entity.backup2;
// Generated 4 mai 2022, 08:23:27 by Hibernate Tools 4.3.5.Final

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
 * UsersRoles generated by hbm2java
 */
@Entity
@Table(name = "users_roles", catalog = "test_brp")
public class UsersRoles implements java.io.Serializable {

	private Integer roleId;
	private Users users;
	private String userRole;

	public UsersRoles() {
	}

	public UsersRoles(Users users, String userRole) {
		this.users = users;
		this.userRole = userRole;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "role_id", unique = true, nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "user_role", nullable = false, length = 45)
	public String getUserRole() {
		return this.userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}