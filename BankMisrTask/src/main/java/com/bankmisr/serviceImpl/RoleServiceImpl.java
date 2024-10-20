package com.bankmisr.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankmisr.model.Role;
import com.bankmisr.repository.BaseRepository;
import com.bankmisr.repository.RoleRepository;
import com.bankmisr.service.RoleService;

 import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

	private final RoleRepository roleRepository;
	@Override
	protected BaseRepository<Role, Long> getRepository() {
		// TODO Auto-generated method stub
		return roleRepository;
	}

}
