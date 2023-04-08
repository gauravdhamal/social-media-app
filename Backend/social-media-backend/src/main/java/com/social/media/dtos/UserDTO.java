package com.social.media.dtos;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private Integer id;

	@Size(min = 1, max = 50)
	private String name;

	@Email
	@Column(unique = true)
	private String email;

	@Size(min = 0, max = 200)
	private String bio;
	
	@Column(insertable = false)
	@CreatedDate
	private LocalDateTime created_at;

	@Column(insertable = false)
	@LastModifiedDate
	private LocalDateTime updated_at;

}
