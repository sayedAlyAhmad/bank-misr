package com.bankmisr;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
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

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bankmisr.controller.StatusController;
import com.bankmisr.dto.StatusDto;
import com.bankmisr.mapper.StatusMapper;
import com.bankmisr.model.Status;
import com.bankmisr.service.StatusService;

@RunWith(SpringRunner.class)
@WebMvcTest(StatusController.class)
public class StatusControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StatusService statusService;

	@MockBean
	private StatusMapper statusMapper;

	private Status status;
	private StatusDto statusDto;

	@Before(value = "")
	public void setUp() {
		status = new Status("In Progress");
		statusDto = new StatusDto(1L, "In Progress");
	}

	@Test
	public void testFindAll() throws Exception {
		List<Status> statuses = Arrays.asList(status);
		List<StatusDto> statusDtos = Arrays.asList(statusDto);

		when(statusService.findAll()).thenReturn(statuses);
		when(statusMapper.map(statuses)).thenReturn(statusDtos);

		mockMvc.perform(get("/statuses")).andExpect(status().isOk()).andExpect(jsonPath("$.data", hasSize(1)))
				.andExpect(jsonPath("$.data[0].name", is(status.getName())));

		verify(statusService, times(1)).findAll();
	}

	@Test
	public void testFindById() throws Exception {
		when(statusService.findById(1L)).thenReturn(status);
		when(statusMapper.map(status)).thenReturn(statusDto);

		mockMvc.perform(get("/statuses/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.name", is(status.getName())));

		verify(statusService, times(1)).findById(1L);
	}

	@Test
	public void testDeleteById() throws Exception {
		doNothing().when(statusService).deleteById(1L);

		mockMvc.perform(delete("/statuses/1")).andExpect(status().isOk());

		verify(statusService, times(1)).deleteById(1L);
	}

	@Test
	public void testInsert() throws Exception {
		StatusDto newStatusDto = new StatusDto(null, "New Status");
		Status newStatus = new Status("New Status");

		when(statusMapper.unMap(any(StatusDto.class))).thenReturn(newStatus);
		when(statusService.insert(any(Status.class))).thenReturn(newStatus);
		when(statusMapper.map(newStatus)).thenReturn(new StatusDto(2L, "New Status"));

		mockMvc.perform(
				post("/statuses").contentType(MediaType.APPLICATION_JSON).content("{ \"name\": \"New Status\" }"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.data.id", is(2)))
				.andExpect(jsonPath("$.data.name", is("New Status")));

		verify(statusService, times(1)).insert(any(Status.class));
	}

	@Test
	public void testUpdate() throws Exception {
		Status updatedStatus = new Status("Updated Status");
		StatusDto updatedStatusDto = new StatusDto(1L, "Updated Status");

		when(statusMapper.unMap(any(StatusDto.class))).thenReturn(updatedStatus);
		when(statusService.update(any(Status.class))).thenReturn(updatedStatus);
		when(statusMapper.map(updatedStatus)).thenReturn(updatedStatusDto);

		mockMvc.perform(put("/statuses").contentType(MediaType.APPLICATION_JSON)
				.content("{ \"id\": 1, \"name\": \"Updated Status\" }")).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id", is(1))).andExpect(jsonPath("$.data.name", is("Updated Status")));

		verify(statusService, times(1)).update(any(Status.class));
	}
}
