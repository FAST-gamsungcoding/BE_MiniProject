package com.gamsung.backend.global.jwt.repository;

import com.gamsung.backend.global.jwt.entity.JwtRedisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtRedisRepository extends JpaRepository<JwtRedisEntity, String> {

}
