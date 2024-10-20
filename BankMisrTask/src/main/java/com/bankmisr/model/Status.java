package com.bankmisr.model;
 
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "status")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Status extends BaseEntity<Long>{

	private static final long serialVersionUID = 1L;
 
	private String name;

}
