package com.bankmisr.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankmisr.dto.UserDto;
import com.bankmisr.email.service.EmailServiceImpl;
import com.bankmisr.mapper.BaseMapper;
import com.bankmisr.mapper.UserMapper;
import com.bankmisr.model.User;
import com.bankmisr.service.BaseService;
import com.bankmisr.service.UserService;
import com.bankmisr.util.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/bank-misr/email")
@RequiredArgsConstructor
@Slf4j
public class UserController extends BaseController<User, UserDto, Long> {

	private final UserService userService;
	private final UserMapper userMapper;
	private final EmailServiceImpl emailServiceImpl;

	@Override
	protected BaseService<User, Long> getService() {
		// TODO Auto-generated method stub
		return userService;
	}

	@Override
	protected BaseMapper<User, UserDto> getMapper() {
		// TODO Auto-generated method stub
		return userMapper;
	}
	
	@PostMapping("/send-welcome")
	@Operation(summary = "Send Welcome Email", description = "Send a welcome email to the specified user")
	public ApiResponse<String> sendWelcomeEmail(
	        @RequestParam(name = "to") String to, 
	        @RequestParam(name = "name") String name) {

	    try {
	        emailServiceImpl.sendWelcomeEmail(to, name);
	        return ApiResponse.ok("Welcome email sent successfully to " + to);
	    } catch (Exception e) {
	        return ApiResponse.ok("Failed to send email: " + e.getMessage());
	    }
	}
}
