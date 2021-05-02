package com.feeldip.spring.claimer.service;

import com.feeldip.spring.claimer.entity.TypeEntity;
import com.feeldip.spring.claimer.exception.alreadyexist.AnswerAlreadyExistException;
import com.feeldip.spring.claimer.exception.nosuch.NoSuchEntityException;
import com.feeldip.spring.claimer.exception.nosuch.NoSuchTypeException;
import com.feeldip.spring.claimer.repository.TypeEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeEntityRepository typeEntityRepository;


    @Override
    @Cacheable("type")
    public List<TypeEntity> getAllTypes() {
        return typeEntityRepository.findAll();
    }

     @Override
     @Cacheable("type")
    public TypeEntity getTypeByID(Long id) {
        if(typeEntityRepository.findById(id).isEmpty())
            throw new NoSuchTypeException("There is no Type with id: " + id);
        return typeEntityRepository.findById(id).get();
    }

    @Override
    @Cacheable("type")
    public TypeEntity getTypeByName(String name) {
        if(typeEntityRepository.findTypeEntityByNameType(name) == null)
            throw new NoSuchTypeException("There is no Type with name: " + name);
        return typeEntityRepository.findTypeEntityByNameType(name);
    }


    @Override
    @Caching(evict = {
            @CacheEvict("type"),
            @CacheEvict("answer")
    })
    public void saveOrUpdateType(TypeEntity type) {
        if(typeEntityRepository.findTypeEntityByNameType(type.getNameType()) != null)
            throw new NoSuchTypeException("Type with this name is already exist");
        typeEntityRepository.save(type);
    }


    @Override
    @Caching(evict = {
            @CacheEvict("type"),
            @CacheEvict("answer")
    })
    public void deleteAllTypes() {
        typeEntityRepository.deleteAll();
    }

    @Override
    @Caching(evict = {
            @CacheEvict("type"),
            @CacheEvict("answer")
    })
    public void deleteTypeById(Long id) {
        if(typeEntityRepository.findById(id).isEmpty())
            throw new NoSuchTypeException("There is no Type with id: " + id);
        typeEntityRepository.deleteById(id);
    }

    @Override
    @Caching(evict = {
            @CacheEvict("type"),
            @CacheEvict("answer")
    })
    public void deleteTypeByName(String name) {
        if(typeEntityRepository.findTypeEntityByNameType(name) == null)
            throw new NoSuchTypeException("There is no Type with name: " + name);
        typeEntityRepository.deleteByNameType(name);
    }
}
