package com.restapiangulardemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapiangulardemo.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {}