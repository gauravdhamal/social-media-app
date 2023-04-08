package com.social.media.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.social.media.dtos.PostDTO;
import com.social.media.exceptions.NoRecordFoundException;
import com.social.media.models.Post;
import com.social.media.repositories.PostRepository;

public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

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
		if (post.getContent() != null) {
			post.setContent(post.getContent());
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

}
