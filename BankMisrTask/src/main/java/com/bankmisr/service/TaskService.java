package com.bankmisr.service;

import java.util.List;

import com.bankmisr.dto.TaskDto;
import com.bankmisr.model.Task;

public interface TaskService extends BaseService<Task, Long> {
	
	List<TaskDto> filterTask(String title, String description, String status);

}
