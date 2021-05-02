package com.feeldip.spring.claimer.service;

import com.feeldip.spring.claimer.entity.StatusEntity;
import com.feeldip.spring.claimer.exception.alreadyexist.StatusAlreadyExistException;
import com.feeldip.spring.claimer.exception.nosuch.NoSuchStatusException;
import com.feeldip.spring.claimer.exception.nosuch.NoSuchTypeException;
import com.feeldip.spring.claimer.repository.StatusEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusEntityRepository statusEntityRepository;


    @Override
    @Cacheable("status")
    public List<StatusEntity> getAllStatuses() {
        return statusEntityRepository.findAll();
    }

    @Override
    @Cacheable("status")
    public StatusEntity getStatusByID(Long id) {
        if(statusEntityRepository.findById(id).isEmpty())
            throw new NoSuchStatusException("There is no Status with id: " + id);
        return statusEntityRepository.findById(id).get();
    }


    @Override
    @Caching(evict = {
            @CacheEvict("answer"),
            @CacheEvict("status")
    })
    public void saveOrUpdateStatus(StatusEntity status) {
        if(statusEntityRepository.findById(status.getIdStatus()).get().getNameStatus()
        .equals(status.getNameStatus())) {
            throw new StatusAlreadyExistException("Status with this name is already exist");
        }
        statusEntityRepository.save(status);
    }


    @Override
    @Caching(evict = {
            @CacheEvict("answer"),
            @CacheEvict("status")
    })
    public void deleteAllStatuses() {
        statusEntityRepository.deleteAll();
    }

    @Override
    @Caching(evict = {
            @CacheEvict("answer"),
            @CacheEvict("status")
    })
    public void deleteStatusById(Long id) {
        if(statusEntityRepository.findById(id).isEmpty())
            throw new NoSuchStatusException("There is no Status with id: " + id);
        statusEntityRepository.deleteById(id);
    }
}
