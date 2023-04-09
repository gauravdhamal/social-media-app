package com.social.media.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.social.media.exceptions.NoRecordFoundException;
import com.social.media.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

	@Query("FROM Post WHERE user.id = ?1")
	public List<Post> getPostsByUserId(Integer userId) throws NoRecordFoundException;

}
