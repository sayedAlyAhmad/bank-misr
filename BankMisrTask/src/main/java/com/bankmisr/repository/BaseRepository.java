package com.bankmisr.repository;

import java.io.Serializable;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
 
@NoRepositoryBean
public interface BaseRepository <T, ID extends Serializable> extends JpaRepository<T, ID> {

	// use if needed 
    @Modifying
    @Transactional
    @Query("update #{#entityName} t SET t.isDeleted = :isDeleted WHERE t.id = :id")
    int updateIsDeleted(@Param("id") ID id, @Param("isDeleted") boolean isDeleted);
 

}
