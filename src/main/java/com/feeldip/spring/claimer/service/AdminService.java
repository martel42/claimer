package com.feeldip.spring.claimer.service;

import com.feeldip.spring.claimer.entity.AdminEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdminService {
    List<AdminEntity> getAllAdmins();
    AdminEntity getAdminByID(Long id);
    AdminEntity getAdminByUsername(String username);
    AdminEntity getAdminByUsernameAndPassword(String username, String password);

    void saveOrUpdateAdmin(AdminEntity admin, Long idRole);

    void deleteAllAdmins();
    void deleteAdminById(Long id);
    void deleteAdminByUsername(String username);
}
