package com.feeldip.spring.claimer.service;


import com.feeldip.spring.claimer.entity.StatusEntity;

import java.util.List;

public interface StatusService {

    List<StatusEntity> getAllStatuses();
    StatusEntity getStatusByID(Long id);

    void saveOrUpdateStatus(StatusEntity status);

    void deleteAllStatuses();
    void deleteStatusById(Long id);
}
