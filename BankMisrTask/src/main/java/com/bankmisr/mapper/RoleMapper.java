package com.bankmisr.mapper;

import org.mapstruct.Mapper;

import com.bankmisr.dto.RoleDto;
import com.bankmisr.model.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper extends BaseMapper<Role, RoleDto>{

}
