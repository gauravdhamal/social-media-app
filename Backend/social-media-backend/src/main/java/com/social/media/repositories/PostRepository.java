package com.social.media.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.social.media.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
