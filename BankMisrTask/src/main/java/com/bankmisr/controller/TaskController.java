package com.bankmisr.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankmisr.dto.TaskDto;
import com.bankmisr.mapper.BaseMapper;
import com.bankmisr.mapper.TaskMapper;
import com.bankmisr.model.Task;
import com.bankmisr.service.BaseService;
import com.bankmisr.service.TaskService;
import com.bankmisr.util.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/bank-misr/task")
@RequiredArgsConstructor
@Slf4j
public class TaskController extends BaseController<Task, TaskDto, Long> {
	
	private final TaskService taskService;
	private final TaskMapper taskMapper;
	
	@Override
	protected BaseService<Task, Long> getService() {
		// TODO Auto-generated method stub
		return taskService;
	}

	@Override
	protected BaseMapper<Task, TaskDto> getMapper() {
		// TODO Auto-generated method stub
		return taskMapper;
	}
	
	@GetMapping("/filter")
	@Operation(summary = "Filter tasks", description = "Retrieve tasks based on title, description, and status")
	public ApiResponse<List<TaskDto>> filterTasks(
	        @RequestParam(name = "title", required = false) String title,
	        @RequestParam(name = "description", required = false) String description,
	        @RequestParam(name = "status", required = false) String status) {

	    List<TaskDto> tasks =  taskService.filterTask(title, description, status);
	    return ApiResponse.ok(tasks);
	}
	
	

}
