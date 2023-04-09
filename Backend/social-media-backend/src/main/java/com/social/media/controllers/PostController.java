package com.social.media.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.media.dtos.PostDTO;
import com.social.media.dtos.UserDTO;
import com.social.media.exceptions.NoRecordFoundException;
import com.social.media.services.PostService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/posts/")
	public ResponseEntity<PostDTO> createPost(@RequestBody @Valid PostDTO postDTO) {
		PostDTO postDTO2 = postService.createPost(postDTO);
		return new ResponseEntity<PostDTO>(postDTO2, HttpStatus.CREATED);
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable("postId") Integer postId) throws NoRecordFoundException {
		PostDTO postDTO = postService.getPostById(postId);
		return new ResponseEntity<PostDTO>(postDTO, HttpStatus.OK);
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> updatePostById(@PathVariable("postId") Integer postId,
			@RequestBody @Valid PostDTO postDTO) throws NoRecordFoundException {
		PostDTO postDTO2 = postService.updatePostById(postId, postDTO);
		return new ResponseEntity<PostDTO>(postDTO2, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<String> deletePostById(@PathVariable("postId") Integer postId) throws NoRecordFoundException {
		String result = postService.deletePostById(postId);
		return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);
	}

	@PostMapping("/posts/{postId}/{userId}")
	public ResponseEntity<String> assignPostToUser(@PathVariable("postId") Integer postId,
			@PathVariable("userId") Integer userId) throws NoRecordFoundException {
		String message = postService.assignPostToUser(postId, userId);
		return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
	}

	@PostMapping("/posts/{postId}/like")
	public ResponseEntity<String> incrementLikesByPostId(@PathVariable("postId") Integer postId)
			throws NoRecordFoundException {
		String message = postService.incrementLikesByPostId(postId);
		return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
	}

	@PostMapping("/posts/{postId}/unlike")
	public ResponseEntity<String> decrementLikesByPostId(@PathVariable("postId") Integer postId)
			throws NoRecordFoundException {
		String message = postService.decrementLikesByPostId(postId);
		return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
	}

	@GetMapping("/analytics/posts")
	public ResponseEntity<List<PostDTO>> getAllPosts() throws NoRecordFoundException {
		List<PostDTO> postDTOs = postService.getAllPosts();
		return new ResponseEntity<List<PostDTO>>(postDTOs, HttpStatus.OK);
	}

	@GetMapping("/analytics/posts/top-liked")
	public ResponseEntity<List<PostDTO>> getTopMostLikedPost() throws NoRecordFoundException {
		List<PostDTO> postDTOs = postService.getTopMostLikedPost();
		return new ResponseEntity<List<PostDTO>>(postDTOs, HttpStatus.OK);
	}

	@GetMapping("/posts/user/{postId}")
	public ResponseEntity<UserDTO> getUserByPostId(@PathVariable("postId") Integer postId)
			throws NoRecordFoundException {
		UserDTO userDTO = postService.getUserByPostId(postId);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}

}
