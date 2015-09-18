package com.conference.presentations.service;

import java.util.List;

import com.conference.presentations.model.User;

public interface UserService {

	User findById(Integer id);
	
	List<User> findAll();

	void saveOrUpdate(User user);
	
	void delete(int id);
	
}
