package com.bankmisr.mapper;

import org.mapstruct.Mapper;

import com.bankmisr.dto.UserDto;
import com.bankmisr.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto>{

}
