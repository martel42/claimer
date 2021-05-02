package com.feeldip.spring.claimer.repository;

import com.feeldip.spring.claimer.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminEntityRepository extends JpaRepository<AdminEntity, Long> {

    AdminEntity findAdminEntityByUsernameAdmin(String username);

    void deleteByNameAdmin(String username);
}
