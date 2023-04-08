package com.social.media.dtos;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonProperty;

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

	@Size(min = 1, max = 50, message = "Name must contains min 1 character and max 50 characters.")
	private String name;

	@Email
	@Column(unique = true)
	private String email;

	@Size(min = 0, max = 200, message = "Bio might be empty or contains at max 200 characters.")
	private String bio;

	@Column(insertable = false)
	@CreatedDate
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDateTime created_at;

	@Column(insertable = false)
	@LastModifiedDate
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDateTime updated_at;

}
