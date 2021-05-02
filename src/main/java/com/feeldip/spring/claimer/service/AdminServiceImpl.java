package com.feeldip.spring.claimer.service;

import com.feeldip.spring.claimer.entity.AdminEntity;
import com.feeldip.spring.claimer.exception.alreadyexist.AdminAlreadyExistException;
import com.feeldip.spring.claimer.exception.nosuch.NoSuchAdminException;
import com.feeldip.spring.claimer.exception.nosuch.NoSuchRoleException;
import com.feeldip.spring.claimer.exception.nosuch.NoSuchTypeException;
import com.feeldip.spring.claimer.repository.AdminEntityRepository;
import com.feeldip.spring.claimer.repository.RoleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminEntityRepository adminEntityRepository;
    @Autowired
    private RoleEntityRepository roleEntityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Cacheable("admin")
    public List<AdminEntity> getAllAdmins() {
        return adminEntityRepository.findAll();
    }

    @Override
    @Cacheable("admin")
    public AdminEntity getAdminByID(Long id) {
        if(adminEntityRepository.findById(id).isEmpty())
            throw new NoSuchAdminException("There is no Admin with id: " + id);
        return adminEntityRepository.findById(id).get();
    }

    @Override
    @Cacheable("admin")
    public AdminEntity getAdminByUsername(String username) {
        if(adminEntityRepository.findAdminEntityByUsernameAdmin(username) == null)
            throw new NoSuchAdminException("There is no Admin with username: " + username);
        return adminEntityRepository.findAdminEntityByUsernameAdmin(username);
    }

    @Override
    @Cacheable("admin")
    public AdminEntity getAdminByUsernameAndPassword(String username, String password) {
        AdminEntity adminEntity = adminEntityRepository.findAdminEntityByUsernameAdmin(username);
        if (adminEntity != null) {
            if (passwordEncoder.matches(password, adminEntity.getPasswordAdmin())) {
                return adminEntity;
            }
        }
        return null;
    }


    @Override
    @Caching(evict = {
            @CacheEvict("admin"),
            @CacheEvict("answer"),
            @CacheEvict("claim")
    })
    public void saveOrUpdateAdmin(AdminEntity admin, Long idRole) {
        if (roleEntityRepository.findById(idRole).isEmpty())
            throw new NoSuchRoleException("There is no Role with this id: " + idRole);
        if(adminEntityRepository.findAdminEntityByUsernameAdmin(admin.getUsernameAdmin()) != null)
            throw new AdminAlreadyExistException("Admin with this username is already exist");
        admin.setRoleEntity(roleEntityRepository.findById(idRole).get());
        admin.setPasswordAdmin(passwordEncoder.encode(admin.getPasswordAdmin()));
        adminEntityRepository.save(admin);
    }


    @Override
    @Caching(evict = {
            @CacheEvict("admin"),
            @CacheEvict("answer"),
            @CacheEvict("claim")
    })
    public void deleteAllAdmins() {
        adminEntityRepository.deleteAll();;
    }

    @Override
    @Caching(evict = {
            @CacheEvict("admin"),
            @CacheEvict("answer"),
            @CacheEvict("claim")
    })
    public void deleteAdminById(Long id) {
        if(adminEntityRepository.findById(id).isEmpty())
            throw new NoSuchAdminException("There is no Admin with id: " + id);
        adminEntityRepository.deleteById(id);
    }

    @Override
    @Caching(evict = {
            @CacheEvict("admin"),
            @CacheEvict("answer"),
            @CacheEvict("claim")
    })
    public void deleteAdminByUsername(String username) {
        if(adminEntityRepository.findAdminEntityByUsernameAdmin(username) == null)
            throw new NoSuchAdminException("There is no Admin with username: " + username);
        adminEntityRepository.deleteByNameAdmin(username);
    }
}
