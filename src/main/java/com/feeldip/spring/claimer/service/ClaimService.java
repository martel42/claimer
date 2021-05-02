package com.feeldip.spring.claimer.service;


import com.feeldip.spring.claimer.entity.AnswerEntity;
import com.feeldip.spring.claimer.entity.ClaimEntity;

import java.util.List;

public interface ClaimService {
    List<ClaimEntity> getAllClaims();
    List<ClaimEntity>getAllUnansweredClaims();
    ClaimEntity getClaimByID(Long idClaim);
    String getTextClaimById(Long idClaim);
    List<ClaimEntity> getClaimerClaims (Long idClaimer);
    List<ClaimEntity> getClaimerUnansweredClaim(Long idClaimer);

    void saveOrUpdateClaim(ClaimEntity claim, Long idClaimer, Long idType);

    void updateAnsweredState(Long idClaim);

    void deleteAllClaims();
    void deleteClaimerClaims(Long idClaimer);
    void deleteClaimById(Long idClaim);
}
