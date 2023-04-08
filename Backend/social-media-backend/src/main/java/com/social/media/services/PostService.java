package com.social.media.services;

import com.social.media.dtos.PostDTO;
import com.social.media.exceptions.NoRecordFoundException;

public interface PostService {

	/**
	 * 
	 * @param postDTO: Takes postDTO object as a parameter and create one new user
	 *                 in database.
	 * @return: Returns PostDTO object with unique ID after saving it in database.
	 */
	public PostDTO createPost(PostDTO postDTO);

	/**
	 * 
	 * @param postId: To find post in database using postId.
	 * @return: postDTO object retrieved from database.
	 * @throws NoRecordFoundException: If no post found with given Id then will
	 *                                 throw NoRecordFoundException.
	 */
	public PostDTO getPostById(Integer postId) throws NoRecordFoundException;

	/**
	 * 
	 * @param postId:  To find post in database using postId.
	 * @param postDTO: It contains information about post which needs to be update
	 *                 in database.
	 * @return: postDTO object retrieved from database.
	 * @throws NoRecordFoundException: If no post found with given Id then will
	 *                                 throw NoRecordFoundException.
	 */
	public PostDTO updatePostById(Integer postId, PostDTO postDTO) throws NoRecordFoundException;

	/**
	 * 
	 * @param postId: To find post in database using postId.
	 * @return: String message contains information about deleted operation.
	 * @throws NoRecordFoundException: If no post found with given Id then will
	 *                                 throw NoRecordFoundException.
	 */
	public String deletePostById(Integer postId) throws NoRecordFoundException;

	/**
	 * 
	 * @param postId: To get the post from database.
	 * @param userId: To get the user from database.
	 * @return: It will return String message about the information about operation.
	 * @throws NoRecordFoundException: if no Post/User found in database then will
	 *                                 throw a NoRecordFoundException.
	 */
	public String assignPostToUser(Integer postId, Integer userId) throws NoRecordFoundException;

}
