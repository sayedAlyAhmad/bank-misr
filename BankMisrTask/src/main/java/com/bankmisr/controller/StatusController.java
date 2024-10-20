package com.bankmisr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankmisr.dto.StatusDto;
import com.bankmisr.mapper.BaseMapper;
import com.bankmisr.mapper.StatusMapper;
import com.bankmisr.model.Status;
import com.bankmisr.service.BaseService;
import com.bankmisr.service.StatusService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/bank-misr/status")
@RequiredArgsConstructor
@Slf4j
public class StatusController extends BaseController<Status, StatusDto, Long> {
	@Autowired
	StatusService statusService;
	@Autowired
	StatusMapper statusMapper;

	@Override
	protected BaseService<Status, Long> getService() {
		// TODO Auto-generated method stub
		return statusService;
	}

	@Override
	protected BaseMapper<Status, StatusDto> getMapper() {
		// TODO Auto-generated method stub
		return statusMapper;
	}

}
