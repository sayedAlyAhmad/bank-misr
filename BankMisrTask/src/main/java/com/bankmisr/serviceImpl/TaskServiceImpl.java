package com.bankmisr.serviceImpl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankmisr.dto.TaskDto;
import com.bankmisr.mapper.TaskMapper;
import com.bankmisr.model.Task;
import com.bankmisr.repository.BaseRepository;
import com.bankmisr.repository.TaskRepository;
import com.bankmisr.service.TaskService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl extends BaseServiceImpl<Task, Long> implements TaskService {

	private final TaskRepository taskRepository;
	private final TaskMapper taskMapper;
	@Override
	protected BaseRepository<Task, Long> getRepository() {
		// TODO Auto-generated method stub
		return taskRepository;
	}
	@Override
	public List<TaskDto> filterTask(String title, String description, String status) {
		// TODO Auto-generated method stub
		List<Task> task = taskRepository.filterTask(title, description, status);
		List<TaskDto> taskDto = taskMapper.map(task);
		
		if(!taskDto.isEmpty()) {
			return taskDto;
		}
		return Collections.emptyList();
	}

}
