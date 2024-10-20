package com.bankmisr.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto extends BaseDto<Long>{
	private Long id;
	private String title;
	private String description;
	private long statusId;
}
