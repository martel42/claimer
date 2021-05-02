package com.feeldip.spring.claimer.service;

import com.feeldip.spring.claimer.entity.ClaimEntity;
import com.feeldip.spring.claimer.entity.ClaimerEntity;
import com.feeldip.spring.claimer.exception.alreadyexist.ClaimerAlreadyExistException;
import com.feeldip.spring.claimer.exception.nosuch.NoSuchClaimerException;
import com.feeldip.spring.claimer.exception.nosuch.NoSuchTypeException;
import com.feeldip.spring.claimer.repository.ClaimerEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimerServiceImpl implements ClaimerService {

    @Autowired
    private ClaimerEntityRepository claimerEntityRepository;


    @Override
    @Cacheable("claimer")
    public List<ClaimerEntity> getAllClaimers() {
        return claimerEntityRepository.findAll();
    }

    @Override
    @Cacheable("claimer")
    public ClaimerEntity getClaimerID(Long id) {
        if(claimerEntityRepository.findById(id).isEmpty())
            throw new NoSuchClaimerException("There is no Claimer with id: " + id);
        return claimerEntityRepository.findById(id).get();
    }

    @Override
    @Cacheable("claimer")
    public ClaimerEntity getClaimerByEmail(String email) {
        if(claimerEntityRepository.findClaimerEntityByEmailUser(email) == null)
            throw new NoSuchClaimerException("There is no Claimer with email: " + email);
        return claimerEntityRepository.findClaimerEntityByEmailUser(email);
    }

    @Override
    @Cacheable("claimer")
    public ClaimerEntity getClaimerByPhone(String phone) {
        if(claimerEntityRepository.findClaimerEntityByEmailUser(phone) == null)
            throw new NoSuchClaimerException("There is no Claimer with phone: " + phone);
        return claimerEntityRepository.findClaimerEntityByPhoneUser(phone);
    }


    @Override
    @Caching(evict = {
            @CacheEvict("answer"),
            @CacheEvict("claim")
    })
    public void saveOrUpdateClaimer(ClaimerEntity claimer) {
        if(claimerEntityRepository.findClaimerEntityByPhoneUser(claimer.getPhoneUser()) != null)
            throw new ClaimerAlreadyExistException("Claimer with this phone is already exist");
        claimerEntityRepository.save(claimer);
    }


    @Override
    @Caching(evict = {
            @CacheEvict("answer"),
            @CacheEvict("claim")
    })
    public void deleteAllClaimer() {
        claimerEntityRepository.deleteAll();
    }

    @Override
    @Caching(evict = {
            @CacheEvict("answer"),
            @CacheEvict("claim")
    })
    public void deleteClaimerById(Long id) {
        if(claimerEntityRepository.findById(id).isEmpty())
            throw new NoSuchClaimerException("There is no Claimer with id: " + id);
        claimerEntityRepository.deleteById(id);
    }

    @Override
    @Caching(evict = {
            @CacheEvict("answer"),
            @CacheEvict("claim")
    })
    public void deleteClaimerByEmail(String email) {
        if(claimerEntityRepository.findClaimerEntityByEmailUser(email) == null)
            throw new NoSuchClaimerException("There is no Claimer with email: " + email);
        claimerEntityRepository.deleteByEmailUser(email);
    }

    @Override
    @Caching(evict = {
            @CacheEvict("answer"),
            @CacheEvict("claim")
    })
    public void deleteClaimerByPhone(String phone) {
        if(claimerEntityRepository.findClaimerEntityByEmailUser(phone) == null)
            throw new NoSuchClaimerException("There is no Claimer with phone: " + phone);
        claimerEntityRepository.deleteByPhoneUser(phone);
    }
}
