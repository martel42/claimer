package com.feeldip.spring.claimer.repository;

import com.feeldip.spring.claimer.entity.ClaimerEntity;
import com.feeldip.spring.claimer.service.ClaimerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimerEntityRepository extends JpaRepository<ClaimerEntity, Long> {

    ClaimerEntity findClaimerEntityByPhoneUser(String phone);

    ClaimerEntity findClaimerEntityByEmailUser(String email);

    void deleteByPhoneUser(String phone);

    void deleteByEmailUser(String email);
}
