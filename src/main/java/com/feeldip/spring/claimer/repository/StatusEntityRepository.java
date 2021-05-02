package com.feeldip.spring.claimer.repository;

import com.feeldip.spring.claimer.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusEntityRepository extends JpaRepository<StatusEntity, Long> {
}
