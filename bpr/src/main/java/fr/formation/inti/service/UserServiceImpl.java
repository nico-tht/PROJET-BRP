package fr.formation.inti.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.inti.dao.UserDao;
import fr.formation.inti.entity.Users;
import fr.formation.inti.entity.UsersRoles;


@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users userInfo = userDao.findByUsername(username);
		System.out.println("UserInfo = " + userInfo);
		
		if (userInfo == null) {
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
		
		UserDetails userDetails = (UserDetails) new User(userInfo.getUsername(), userInfo.getPassword(), mapRolesToAuthorities(userInfo.getUsersRoleses()));
//		UserDetails userDetails = (UserDetails) new User(userInfo.getUsername(), userInfo.getPassword()); 
		
		return userDetails;
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<UsersRoles> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.getUserRole())).collect(Collectors.toList());
	}

	@Override
	public Users findUserBy(String userName, String password) {
		// TODO Auto-generated method stub
		return userDao.findUserBy(userName, password);
	}

//	@Override
//	public void addUser(Users user) {
//		this.userDao.save(user);
//		
//	}

	@Override
	public void save(Users user) {
		
		UsersRoles role = new UsersRoles(user,"USER");
		Set<UsersRoles> roles = new HashSet<UsersRoles>();
		roles.add(role);
		user.setUsersRoleses(roles);
		userDao.save(user);
	}
//	@Override
//	public Users save(Users user) {
//		// TODO Auto-generated method stub
//		return userDao.save(user);
//	}

	@Override
	public Users findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}

	@Override
	public void update(Users user) {
		userDao.save(user);
		
	}

	@Override
	public void deleteUser(Users user) {
		userDao.delete(user);
		
	}

	// Role ADMIN, USER...

//  Set < GrantedAuthority > grantedAuthorities = new HashSet < > ();
//  grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//  grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	

//
//  return new org.springframework.security.core.userdetails.User(userInfo.getUsername(), userInfo.getPassword(),
//      grantedAuthorities);

}

