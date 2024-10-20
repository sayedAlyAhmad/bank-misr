package com.bankmisr.common;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bankmisr.controller.TaskController;
import com.bankmisr.dto.TaskDto;
import com.bankmisr.mapper.BaseMapper;
import com.bankmisr.model.Status;
import com.bankmisr.model.Task;
import com.bankmisr.service.BaseService;

public class TaskControllerTest {
	private MockMvc mockMvc;

	@Mock
	private BaseService<Task, Long> taskService;

	@Mock
	private BaseMapper<Task, TaskDto> taskMapper;

	@InjectMocks
	private TaskController taskController; // Assuming TaskController extends BaseController

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
	}

	@Test
	void testFindAll() throws Exception {
		List<Task> tasks = Arrays.asList(new Task("Task1", "Description1", new Status("OPEN")));
		List<TaskDto> taskDtos = Arrays.asList(new TaskDto());

		when(taskService.findAll()).thenReturn(tasks);
		when(taskMapper.map(tasks)).thenReturn(taskDtos);

		mockMvc.perform(get("/tasks")).andExpect(status().isOk()).andExpect(jsonPath("$[0].title").value("Task1"));

		verify(taskService, times(1)).findAll();
	}

	@Test
	void testFindById() throws Exception {
		Task task = new Task("Task1", "Description1", new Status("OPEN"));
		TaskDto taskDto = new TaskDto();

		when(taskService.findById(1L)).thenReturn(task);
		when(taskMapper.map(task)).thenReturn(taskDto);

		mockMvc.perform(get("/tasks/1")).andExpect(status().isOk()).andExpect(jsonPath("$.title").value("Task1"));

		verify(taskService, times(1)).findById(1L);
	}

	@Test
	void testInsert() throws Exception {
		Task task = new Task("Task1", "Description1", new Status("OPEN"));
		TaskDto taskDto = new TaskDto();

		when(taskMapper.unMap(any(TaskDto.class))).thenReturn(task);
		when(taskService.insert(any(Task.class))).thenReturn(task);
		when(taskMapper.map(task)).thenReturn(taskDto);

		mockMvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON)
				.content("{\"title\": \"Task1\", \"description\": \"Description1\", \"status\": \"OPEN\"}"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1L));

		verify(taskService, times(1)).insert(any(Task.class));
	}

	@Test
	void testUpdate() throws Exception {
		Task task = new Task("Task1", "Description1", new Status("OPEN"));

		when(taskMapper.unMap(any(TaskDto.class))).thenReturn(task);
		when(taskService.update(any(Task.class))).thenReturn(task);

		mockMvc.perform(put("/tasks").contentType(MediaType.APPLICATION_JSON).content(
				"{\"id\": 1, \"title\": \"Task1 Updated\", \"description\": \"Description1 Updated\", \"status\": \"OPEN\"}"))
				.andExpect(status().isOk());

		verify(taskService, times(1)).update(any(Task.class));
	}

	@Test
	void testDeleteById() throws Exception {
		doNothing().when(taskService).deleteById(1L);

		mockMvc.perform(delete("/tasks/1")).andExpect(status().isOk());

		verify(taskService, times(1)).deleteById(1L);
	}

	@Test
	void testFindAllPaging() throws Exception {
		List<Task> tasks = Arrays.asList(new Task("Task1", "Description1", new Status("OPEN")));
		List<TaskDto> taskDtos = Arrays.asList(new TaskDto());
		Page<Task> page = new PageImpl<>(tasks, PageRequest.of(0, 10), tasks.size());

		when(taskService.findAll(any(PageRequest.class))).thenReturn(page);
		when(taskMapper.map(any(Task.class))).thenReturn(taskDtos.get(0));

		mockMvc.perform(get("/tasks/page").param("page", "0").param("limit", "10")).andExpect(status().isOk())
				.andExpect(jsonPath("$.totalElements").value(1));

		verify(taskService, times(1)).findAll(any(PageRequest.class));
	}

}
