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

import com.social.media.dtos.PostDTO;
import com.social.media.exceptions.NoRecordFoundException;
import com.social.media.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/")
	public ResponseEntity<PostDTO> createPost(@RequestBody @Valid PostDTO postDTO) {
		PostDTO postDTO2 = postService.createPost(postDTO);
		return new ResponseEntity<PostDTO>(postDTO2, HttpStatus.CREATED);
	}

	@GetMapping("{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable("postId") Integer postId) throws NoRecordFoundException {
		PostDTO postDTO = postService.getPostById(postId);
		return new ResponseEntity<PostDTO>(postDTO, HttpStatus.OK);
	}

	@PutMapping("/{postId}")
	public ResponseEntity<PostDTO> updatePostById(@PathVariable("postId") Integer postId,
			@RequestBody @Valid PostDTO postDTO) throws NoRecordFoundException {
		PostDTO postDTO2 = postService.updatePostById(postId, postDTO);
		return new ResponseEntity<PostDTO>(postDTO2, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<String> deletePostById(@PathVariable("postId") Integer postId) throws NoRecordFoundException {
		String result = postService.deletePostById(postId);
		return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);
	}

}
