package com.bankmisr.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankmisr.model.Status;
import com.bankmisr.repository.BaseRepository;
import com.bankmisr.repository.StatusRepository;
import com.bankmisr.service.StatusService;

 import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class StatusServiceImpl extends BaseServiceImpl<Status, Long> implements StatusService {
	private final StatusRepository statusRepository;

	@Override
	protected BaseRepository<Status, Long> getRepository() {
		// TODO Auto-generated method stub
		return statusRepository;
	}

}
