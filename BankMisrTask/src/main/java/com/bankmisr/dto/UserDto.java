package com.bankmisr.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto<Long> {
	private Long id;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String name;
	private String businessTitle;
}
