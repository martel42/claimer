package com.feeldip.spring.claimer.service;


import com.feeldip.spring.claimer.entity.TypeEntity;

import java.util.List;

public interface TypeService {

    List<TypeEntity> getAllTypes();
    TypeEntity getTypeByID(Long id);
    TypeEntity getTypeByName(String name);

    void saveOrUpdateType(TypeEntity type);

    void deleteAllTypes();
    void deleteTypeById(Long id);
    void deleteTypeByName(String name);
}
