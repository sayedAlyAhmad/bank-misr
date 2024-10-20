package com.bankmisr.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bankmisr.dto.TaskDto;
import com.bankmisr.model.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper extends BaseMapper<Task, TaskDto> {
	@Mapping(source = "status.id", target = "statusId")
    TaskDto map(Task task);

 
}
