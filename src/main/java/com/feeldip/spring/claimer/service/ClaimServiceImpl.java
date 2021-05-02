package com.feeldip.spring.claimer.service;

import com.feeldip.spring.claimer.entity.ClaimEntity;
import com.feeldip.spring.claimer.entity.ClaimerEntity;
import com.feeldip.spring.claimer.exception.nosuch.NoSuchClaimException;
import com.feeldip.spring.claimer.exception.nosuch.NoSuchClaimerException;
import com.feeldip.spring.claimer.exception.nosuch.NoSuchTypeException;
import com.feeldip.spring.claimer.repository.ClaimEntityRepository;
import com.feeldip.spring.claimer.repository.ClaimerEntityRepository;
import com.feeldip.spring.claimer.repository.TypeEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimServiceImpl implements  ClaimService {

    @Autowired
    private ClaimEntityRepository claimEntityRepository;
    @Autowired
    private ClaimerEntityRepository claimerEntityRepository;
    @Autowired
    private TypeEntityRepository typeEntityRepository;


    @Override
    @Cacheable("claim")
    public List<ClaimEntity> getAllClaims() {
        return claimEntityRepository.findAll();
    }

    @Override
    @Cacheable("claim")
    public List<ClaimEntity> getAllUnansweredClaims() {
        return claimEntityRepository.findAllUnansweredClaim();
    }

    @Override
    @Cacheable("claim")
    public List<ClaimEntity> getClaimerClaims(Long idClaimer) {
        if(claimerEntityRepository.findById(idClaimer).isEmpty())
            throw new NoSuchClaimerException("There is no Claimer with id: " + idClaimer);
        return claimEntityRepository.findAllClaimerClaim(idClaimer);
    }

    @Override
    @Cacheable("claim")
    public List<ClaimEntity> getClaimerUnansweredClaim(Long idClaimer) {
        if(claimerEntityRepository.findById(idClaimer).isEmpty())
            throw new NoSuchClaimerException("There is no Claimer with id: " + idClaimer);
        return claimEntityRepository.findUnansweredClaimerClaim(idClaimer);
    }


    @Override
    public ClaimEntity getClaimByID(Long idClaim) {
        if(claimEntityRepository.findById(idClaim).isEmpty())
            throw new NoSuchClaimException("There is no Claim with id: " + idClaim);
        return claimEntityRepository.findById(idClaim).get();
    }

    @Override
    @Cacheable("claim")
    public String getTextClaimById(Long idClaim) {
        if(claimEntityRepository.findById(idClaim).isEmpty())
            throw new NoSuchClaimException("There is no Claim with id: " + idClaim);
        return claimEntityRepository.findById(idClaim).get().getTextClaim();
    }


    @Override
    @Caching(evict = {
            @CacheEvict("answer"),
            @CacheEvict("claim")
    })
    public void saveOrUpdateClaim(ClaimEntity claim, Long idClaimer, Long idType) {
        if(claimerEntityRepository.findById(idClaimer).isEmpty())
            throw new NoSuchClaimerException("There is no Claimer with id: " + idClaimer);
        claim.setClaimerEntity(claimerEntityRepository.findById(idClaimer).get());
        if(typeEntityRepository.findById(idType).isEmpty())
            throw new NoSuchTypeException("There is no Type with id: " + idType);
        claim.setTypeEntity(typeEntityRepository.findById(idType).get());
        claimEntityRepository.save(claim);
    }


    @Override
    @Caching(evict = {
            @CacheEvict("answer"),
            @CacheEvict("claim")
    })
    public void updateAnsweredState(Long idClaim) {
        if(claimEntityRepository.findById(idClaim).isEmpty())
            throw new NoSuchClaimException("There is no Claim with id: " + idClaim);
        ClaimEntity claimEntity = claimEntityRepository.findById(idClaim).get();
        claimEntity.setAnswered(!claimEntity.isAnswered());
        claimEntityRepository.save(claimEntity);
    }


    @Override
    @Caching(evict = {
            @CacheEvict("answer"),
            @CacheEvict("claim")
    })
    public void deleteAllClaims() {
        claimEntityRepository.deleteAll();
    }

    @Override
    @Caching(evict = {
            @CacheEvict("answer"),
            @CacheEvict("claim")
    })
    public void deleteClaimerClaims(Long idClaimer) {
        if(claimerEntityRepository.findById(idClaimer).isEmpty())
            throw new NoSuchClaimerException("There is no Claimer with id: " + idClaimer);
        claimEntityRepository.deleteAllClaimerClaims(idClaimer);
    }

    @Override
    @Caching(evict = {
            @CacheEvict("answer"),
            @CacheEvict("claim")
    })
    public void deleteClaimById(Long idClaim) {
        if(claimEntityRepository.findById(idClaim).isEmpty())
            throw new NoSuchClaimException("There is no Claim with id: " + idClaim);
        claimEntityRepository.deleteById(idClaim);
    }
}
