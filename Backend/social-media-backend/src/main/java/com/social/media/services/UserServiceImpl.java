package com.social.media.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.social.media.dtos.UserDTO;
import com.social.media.exceptions.NoRecordFoundException;
import com.social.media.repositories.UserRepository;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDTO createUser(UserDTO userDTO) {
		return null;
	}

	@Override
	public UserDTO getuserById(Integer userId) throws NoRecordFoundException {
		return null;
	}

	@Override
	public UserDTO updateUserById(Integer userId, UserDTO userDTO) throws NoRecordFoundException {
		return null;
	}

	@Override
	public String deleteUserById(Integer userId) throws NoRecordFoundException {
		return null;
	}

}
