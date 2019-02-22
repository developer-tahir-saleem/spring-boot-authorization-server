package com.tahir.authorization.server.authorizationserver.service.impl;

import com.tahir.authorization.server.authorizationserver.dao.UserDao;
import com.tahir.authorization.server.authorizationserver.model.User;
import com.tahir.authorization.server.authorizationserver.repository.UserRepository;
import com.tahir.authorization.server.authorizationserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service(value = "userService")
//public class UserServiceImpl implements UserDetailsService, UserService {
//
//	@Autowired
//	private UserDao userDao;
//
//	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
//		User user = userDao.findByUsername(userId);
//		if(user == null){
//			throw new UsernameNotFoundException("Invalid username or password.");
//		}
//		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
//	}
//
//	private List<SimpleGrantedAuthority> getAuthority() {
//		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
//	}
//
//	public List<User> findAll() {
//		List<User> list = new ArrayList<>();
//		userDao.findAll().iterator().forEachRemaining(list::add);
//		return list;
//	}
//
//	@Override
//	public User findOne(long id) {
//		return userDao.findById(id).get();
//	}
//
//	@Override
//	public void delete(long id) {
//		userDao.deleteById(id);
//	}
//
//	@Override
//    public User save(User user) {
//        return userDao.save(user);
//    }
//}
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;


	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(userId);

		if (user.isPresent()) {

			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), getAuthority());
	}

	@Override
	public User findOne(long id) {
		return userRepository.findById(id).get();
	}


	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
//            userRepository.delete(user.get());
		} else {
		}
	}

	@Override
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}


}
