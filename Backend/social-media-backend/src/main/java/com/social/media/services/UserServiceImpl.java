package com.social.media.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.media.comparators.UserComparator;
import com.social.media.dtos.UserDTO;
import com.social.media.exceptions.NoRecordFoundException;
import com.social.media.models.Post;
import com.social.media.models.User;
import com.social.media.repositories.PostRepository;
import com.social.media.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

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
		if (userDTO.getName() != null) {
			user.setName(userDTO.getName());
		}
		if (userDTO.getBio() != null) {
			user.setBio(userDTO.getBio());
		}
		user = userRepository.save(user);
		userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

	@Override
	public String deleteUserById(Integer userId) throws NoRecordFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NoRecordFoundException("User not found with Id : " + userId));
		if (!user.getPosts().isEmpty()) {
			for (Post post : user.getPosts()) {
				post.setUser(null);
				postRepository.save(post);
			}
		}
		userRepository.delete(user);
		return "User " + userId + " deleted from database.";
	}

	@Override
	public List<UserDTO> getAllUsers() throws NoRecordFoundException {
		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			throw new NoRecordFoundException("No any users found in database.");
		} else {
			List<UserDTO> userDTOs = new ArrayList<>();
			for (User user : users) {
				UserDTO userDTO = modelMapper.map(user, UserDTO.class);
				userDTOs.add(userDTO);
			}
			return userDTOs;
		}
	}

	@Override
	public List<UserDTO> getTopFiveMostActiveUsers() throws NoRecordFoundException {
		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			throw new NoRecordFoundException("No any users found in database.");
		} else {
			Collections.sort(users, new UserComparator());
			List<UserDTO> userDTOs = new ArrayList<>();
			int limit = 5;
			for (User user : users) {
				if (limit == 0)
					break;
				UserDTO userDTO = modelMapper.map(user, UserDTO.class);
				userDTO.setTotalPosts(user.getPosts().size());
				userDTOs.add(userDTO);
				limit--;
			}
			return userDTOs;
		}
	}

}
