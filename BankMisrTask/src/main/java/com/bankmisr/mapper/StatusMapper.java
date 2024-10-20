package com.bankmisr.mapper;

import org.mapstruct.Mapper;

import com.bankmisr.dto.StatusDto;
import com.bankmisr.model.Status;

@Mapper(componentModel = "spring")
public interface StatusMapper extends BaseMapper<Status, StatusDto>{

}
