package com.bankmisr.repository;

import com.bankmisr.model.User;

public interface UserRepository extends BaseRepository<User, Long> {

	User findByUsername(String username);

}
