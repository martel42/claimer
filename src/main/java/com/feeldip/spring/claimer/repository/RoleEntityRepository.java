package com.feeldip.spring.claimer.repository;

import com.feeldip.spring.claimer.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByAuthorityRole(String authority);
}
