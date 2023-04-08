package com.social.media.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.media.dtos.UserDTO;
import com.social.media.exceptions.NoRecordFoundException;
import com.social.media.models.User;
import com.social.media.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		user = userRepository.save(user);
		userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

	@Override
	public UserDTO getuserById(Integer userId) throws NoRecordFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NoRecordFoundException("User not found with Id : " + userId));
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

	@Override
	public UserDTO updateUserById(Integer userId, UserDTO userDTO) throws NoRecordFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NoRecordFoundException("User not found with Id : " + userId));
		if (user.getName() != null) {
			user.setName(user.getName());
		}
		if (user.getBio() != null) {
			user.setBio(user.getBio());
		}
		user = userRepository.save(user);
		userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

	@Override
	public String deleteUserById(Integer userId) throws NoRecordFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NoRecordFoundException("User not found with Id : " + userId));
		userRepository.delete(user);
		return "User +" + userId + "+ deleted from database.";
	}

}
