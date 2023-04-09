package com.social.media;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.social.media.dtos.PostDTO;
import com.social.media.dtos.UserDTO;

@SpringBootTest
@RunWith(SpringRunner.class)
class SocialMediaBackendApplicationTests {

	@Autowired
	private RestTemplate restTemplate;

	// User test cases.
	@Test
	public void testGetUserById() {
		ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity("http://localhost:8888/users/152",
				UserDTO.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		UserDTO userDTO = responseEntity.getBody();
		assertEquals(userDTO.getId(), 152);
		assertEquals(userDTO.getEmail(), "user1@gmail.com");
		assertEquals(userDTO.getName(), "user1");
		assertEquals(userDTO.getBio(), "bio1");
	}

	@Test
	public void testGetPostById() {
		ResponseEntity<PostDTO> responseEntity = restTemplate.getForEntity("http://localhost:8888/posts/11",
				PostDTO.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		PostDTO postDTO = responseEntity.getBody();
		assertEquals(postDTO.getId(), 11);
		assertEquals(postDTO.getContent(), "post10");
		assertEquals(postDTO.getLikes(), 6);
	}

}
