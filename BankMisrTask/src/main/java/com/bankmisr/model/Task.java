package com.bankmisr.model;

 
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "task")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Task extends BaseEntity<Long> {
 
	private static final long serialVersionUID = 1L;
	private String title;
	private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;
	
	
}
