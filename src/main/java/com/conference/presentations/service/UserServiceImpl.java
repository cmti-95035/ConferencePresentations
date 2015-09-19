package com.conference.presentations.service;

import java.util.List;

import com.conference.presentations.dao.ResearchFieldDao;
import com.conference.presentations.model.ResearchField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conference.presentations.dao.UserDao;
import com.conference.presentations.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	UserDao userDao;
	ResearchFieldDao researchFieldDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setResearchFieldDao(ResearchFieldDao researchFieldDao) { this.researchFieldDao =  researchFieldDao;}
	@Override
	public User findById(Integer id) {
		return userDao.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public void saveOrUpdate(User user) {

		if (findById(user.getId())==null) {
			userDao.save(user);
		} else {
			userDao.update(user);
		}

	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
	}

	@Override
	public List<ResearchField> findAllFields() {
		return researchFieldDao.findAll();
	}
}
