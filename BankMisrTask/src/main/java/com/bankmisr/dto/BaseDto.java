package com.bankmisr.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseDto<ID extends Serializable> {

    private ID id;

    private int isDeleted;

}