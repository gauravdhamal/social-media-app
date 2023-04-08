package com.social.media.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.media.dtos.PostDTO;
import com.social.media.exceptions.NoRecordFoundException;
import com.social.media.models.Post;
import com.social.media.models.User;
import com.social.media.repositories.PostRepository;
import com.social.media.repositories.UserRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDTO createPost(PostDTO postDTO) {
		Post post = modelMapper.map(postDTO, Post.class);
		post = postRepository.save(post);
		postDTO = modelMapper.map(post, PostDTO.class);
		return postDTO;
	}

	@Override
	public PostDTO getPostById(Integer postId) throws NoRecordFoundException {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new NoRecordFoundException("Post not found with Id : " + postId));
		PostDTO postDTO = modelMapper.map(post, PostDTO.class);
		return postDTO;
	}

	@Override
	public PostDTO updatePostById(Integer postId, PostDTO postDTO) throws NoRecordFoundException {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new NoRecordFoundException("Post not found with Id : " + postId));
		if (postDTO.getContent() != null) {
			post.setContent(postDTO.getContent());
		}
		post = postRepository.save(post);
		postDTO = modelMapper.map(post, PostDTO.class);
		return postDTO;
	}

	@Override
	public String deletePostById(Integer postId) throws NoRecordFoundException {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new NoRecordFoundException("Post not found with Id : " + postId));
		postRepository.delete(post);
		return "Post " + postId + " deleted from database.";
	}

	@Override
	public String assignPostToUser(Integer postId, Integer userId) throws NoRecordFoundException {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new NoRecordFoundException("Post not found with Id : " + postId));
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NoRecordFoundException("User not found with Id : " + userId));
		if (post.getUser() != null) {
			throw new NoRecordFoundException(
					"This post already belongs to another user with Id : " + post.getUser().getId());
		} else {
			post.setUser(user);
			user.getPosts().add(post);
			postRepository.save(post);
			userRepository.save(user);
			return "Post " + postId + " added to user " + userId;
		}
	}

}
