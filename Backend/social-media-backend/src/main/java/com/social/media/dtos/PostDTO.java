package com.social.media.dtos;

import java.time.LocalDateTime;

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

	private String content;

	private LocalDateTime created_at;

	private LocalDateTime updated_at;

	private Integer likes;

}
