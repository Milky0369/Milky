package com.milky.candy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.milky.candy.domain.User;

public interface UserDbRepository extends JpaRepository<User, Long> {
	User findByUserIdAndUserPwd(String userId, String userPwd);
}
