package com.social.media.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.media.dtos.UserDTO;
import com.social.media.exceptions.NoRecordFoundException;
import com.social.media.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO) {
		UserDTO userDTO2 = userService.createUser(userDTO);
		return new ResponseEntity<UserDTO>(userDTO2, HttpStatus.CREATED);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getuserById(@PathVariable("userId") Integer userId) throws NoRecordFoundException {
		UserDTO userDTO = userService.getuserById(userId);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUserById(@PathVariable("userId") Integer userId,
			@RequestBody @Valid UserDTO userDTO) throws NoRecordFoundException {
		UserDTO userDTO2 = userService.updateUserById(userId, userDTO);
		return new ResponseEntity<UserDTO>(userDTO2, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUserById(@PathVariable("userId") Integer userId) throws NoRecordFoundException {
		String result = userService.deleteUserById(userId);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
