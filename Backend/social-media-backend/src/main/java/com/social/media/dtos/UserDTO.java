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
public class UserDTO {

	private Integer id;

	private String name;

	private String email;

	private String bio;

	private LocalDateTime created_at;

	private LocalDateTime updated_at;

}
