package com.social.media.dtos;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

	private Integer id;

	@Size(min = 1, max = 300, message = "Content must contains min 1 character and max 300 characters.")
	private String content;

	@Column(insertable = false)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@CreatedDate
	private LocalDateTime created_at;

	@Column(insertable = false)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@LastModifiedDate
	private LocalDateTime updated_at;

	@Min(value = 0)
	private Integer likes;

}
