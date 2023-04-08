package com.social.media.services;

import com.social.media.dtos.UserDTO;
import com.social.media.exceptions.NoRecordFoundException;

public interface UserService {

	/**
	 * 
	 * @param userDTO: it takes userDTO as a parameter to create one user and save
	 *                 to database.
	 * @return: It returns one user along with unique Id which is provided
	 *          automatically by database.
	 */
	public UserDTO createUser(UserDTO userDTO);

	/**
	 * 
	 * @param userId: To search user with help of ID in database.
	 * @return: It will return userDTO object after retrieving from database.
	 * @throws NoRecordFoundException: if no any user found in database then will
	 *                                 throw NoRecordFoundException.
	 */
	public UserDTO getuserById(Integer userId) throws NoRecordFoundException;

	/**
	 * 
	 * @param userId:  To search user with help of ID in database.
	 * @param userDTO: It contains information that needs to be update.
	 * @return: It will return updated userDTO object after performing update in
	 *          database.
	 * @throws NoRecordFoundException: if no any user found in database then will
	 *                                 throw NoRecordFoundException.
	 */
	public UserDTO updateUserById(Integer userId, UserDTO userDTO) throws NoRecordFoundException;

	/**
	 * 
	 * @param userId: To search user with help of ID in database.
	 * @return: It returns string message regarding details of operation.
	 * @throws NoRecordFoundException: if no any user found in database then will
	 *                                 throw NoRecordFoundException.
	 */
	public String deleteUserById(Integer userId) throws NoRecordFoundException;

}
