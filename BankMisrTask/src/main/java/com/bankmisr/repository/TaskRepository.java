package com.bankmisr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bankmisr.model.Task;

public interface TaskRepository extends BaseRepository<Task, Long> {

	@Query("""
	        SELECT t FROM Task t
	        LEFT JOIN t.status status
	        WHERE (:title IS NULL OR t.title = :title)
	        AND (:description IS NULL OR t.description = :description)
	        AND (:status IS NULL OR status.name = :status)
	    """)
	List<Task> filterTask(@Param("title") String title, 
	                          @Param("description") String description,
	                          @Param("status") String status);
}
