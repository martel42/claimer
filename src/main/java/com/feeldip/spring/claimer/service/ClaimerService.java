package com.feeldip.spring.claimer.service;

import com.feeldip.spring.claimer.entity.ClaimerEntity;

import java.util.List;

public interface ClaimerService {
    List<ClaimerEntity> getAllClaimers();
    ClaimerEntity getClaimerID(Long id);
    ClaimerEntity getClaimerByEmail(String email);
    ClaimerEntity getClaimerByPhone(String phone);

    void saveOrUpdateClaimer(ClaimerEntity claimer);

    void deleteAllClaimer();
    void deleteClaimerById(Long id);
    void deleteClaimerByEmail(String email);
    void deleteClaimerByPhone(String phone);
}
