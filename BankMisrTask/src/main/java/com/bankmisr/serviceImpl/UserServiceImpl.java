package com.bankmisr.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankmisr.email.service.EmailServiceImpl;
import com.bankmisr.model.User;
import com.bankmisr.repository.BaseRepository;
import com.bankmisr.repository.UserRepository;
import com.bankmisr.service.UserService;

import lombok.RequiredArgsConstructor;

@Service("userService")
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {
	private final UserRepository userRepository;

	@Override
	protected BaseRepository<User, Long> getRepository() {
		// TODO Auto-generated method stub
		return userRepository;
	}

 
 

 
	
	

}
