package com.social.media.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.media.comparators.PostComparator;
import com.social.media.dtos.PostDTO;
import com.social.media.dtos.UserDTO;
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
		post.setLikes(0);
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

	@Override
	public String incrementLikesByPostId(Integer postId) throws NoRecordFoundException {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new NoRecordFoundException("Post not found with Id : " + postId));
		if (post.getLikes() == null)
			post.setLikes(1);
		else {
			post.setLikes(post.getLikes() + 1);
			post = postRepository.save(post);
		}
		return "Likes incremented by 1. Total likes : " + post.getLikes();
	}

	@Override
	public String decrementLikesByPostId(Integer postId) throws NoRecordFoundException {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new NoRecordFoundException("Post not found with Id : " + postId));
		if (post.getLikes() == 0) {
			throw new NoRecordFoundException("Not able to decrease likes as currently there are 0 likes.");
		} else {
			post.setLikes(post.getLikes() - 1);
			post = postRepository.save(post);
			return "Likes decremented by 1. Total likes : " + post.getLikes();
		}
	}

	@Override
	public List<PostDTO> getAllPosts() throws NoRecordFoundException {
		List<Post> posts = postRepository.findAll();
		if (posts.isEmpty()) {
			throw new NoRecordFoundException("No any posts found in database.");
		} else {
			List<PostDTO> postDTOs = new ArrayList<>();
			for (Post post : posts) {
				PostDTO postDTO = modelMapper.map(post, PostDTO.class);
				postDTOs.add(postDTO);
			}
			return postDTOs;
		}
	}

	@Override
	public List<PostDTO> getTopMostLikedPost() throws NoRecordFoundException {
		List<Post> posts = postRepository.findAll();
		if (posts.isEmpty()) {
			throw new NoRecordFoundException("No any posts found in database.");
		} else {
			Collections.sort(posts, new PostComparator());
			int limit = 5;
			List<PostDTO> postDTOs = new ArrayList<>();
			for (Post post : posts) {
				if (limit == 0)
					break;
				PostDTO postDTO = modelMapper.map(post, PostDTO.class);
				postDTOs.add(postDTO);
				limit--;
			}
			return postDTOs;
		}
	}

	@Override
	public UserDTO getUserByPostId(Integer postId) throws NoRecordFoundException {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new NoRecordFoundException("Post not found with Id : " + postId));
		if (post.getUser() == null) {
			throw new NoRecordFoundException("No any user registered with post : " + postId);
		} else {
			User user = post.getUser();
			UserDTO userDTO = modelMapper.map(user, UserDTO.class);
			return userDTO;
		}
	}

}
