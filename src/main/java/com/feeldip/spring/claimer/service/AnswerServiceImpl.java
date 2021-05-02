package com.feeldip.spring.claimer.service;

import com.feeldip.spring.claimer.entity.AdminEntity;
import com.feeldip.spring.claimer.entity.AnswerEntity;
import com.feeldip.spring.claimer.entity.ClaimEntity;
import com.feeldip.spring.claimer.exception.nosuch.*;
import com.feeldip.spring.claimer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements AnswerService{

    @Autowired
    private AnswerEntityRepository answerEntityRepository;
    @Autowired
    private AdminEntityRepository adminEntityRepository;
    @Autowired
    private StatusEntityRepository statusEntityRepository;
    @Autowired
    private ClaimEntityRepository claimEntityRepository;


    @Override
    @Cacheable("answer")
    public List<AnswerEntity> getAllAnswers() {
        return answerEntityRepository.findAll();
    }

    @Override
    @Cacheable("answer")
    public List<AnswerEntity> getAdminAnswers(Long idAdmin) {
        if(adminEntityRepository.findById(idAdmin).isEmpty())
            throw new NoSuchAdminException("There is no Admin with id: " + idAdmin);
        return answerEntityRepository.findAllAdminAnswer(idAdmin);
    }

    @Override
    @Cacheable("answer")
    public AnswerEntity getAnswerByID(Long idAnswer) {
        if(answerEntityRepository.findById(idAnswer).isEmpty())
            throw new NoSuchAnswerException("There is no Answer with id: " + idAnswer);
        return answerEntityRepository.findById(idAnswer).get();
    }

    @Override
    @Cacheable("answer")
    public String getTextAnswerById(Long idAnswer) {
        if(answerEntityRepository.findById(idAnswer).isEmpty())
            throw new NoSuchAnswerException("There is no Answer with id: " + idAnswer);
        return answerEntityRepository.findById(idAnswer).get().getTextAnswer();
    }


    @Override
    @Caching(evict = {
            @CacheEvict("answer"),
            @CacheEvict("claim")
    })
    public void saveOrUpdateAnswer(AnswerEntity answer, Long idAdmin, Long idStatus, Long idClaim) {
        if(adminEntityRepository.findById(idAdmin).isEmpty())
            throw new NoSuchAdminException("There is no Admin with id: " + idAdmin);
        answer.setAdminEntity(adminEntityRepository.findById(idAdmin).get());

        if(claimEntityRepository.findById(idClaim).isEmpty())
            throw new NoSuchClaimException("There is no Claim with id: " + idClaim);
        claimEntityRepository.findById(idClaim).get().setAnswered(true);
        answer.setClaimEntity(claimEntityRepository.findById(idClaim).get());

        if(statusEntityRepository.findById(idStatus).isEmpty())
            throw new NoSuchStatusException("There is no Status with id: " + idStatus);
        answer.setStatusEntity(statusEntityRepository.findById(idStatus).get());
        answerEntityRepository.save(answer);
    }


    @Override
    @Caching(evict = {
            @CacheEvict("answer"),
            @CacheEvict("claim")
    })
    public void deleteAllAnswers() {
        claimEntityRepository.findAll().forEach(p -> setUnanswered(p.getIdClaim()));
        answerEntityRepository.deleteAll();
    }

    @Override
    @Caching(evict = {
            @CacheEvict("answer"),
            @CacheEvict("claim")
    })
    public void deleteAnswerById(Long id) {
        if(answerEntityRepository.findById(id).isEmpty())
            throw new NoSuchAnswerException("There is no Answer with id: " + id);
        setUnanswered(answerEntityRepository.findById(id).get().getClaimEntity().getIdClaim());
        answerEntityRepository.deleteById(id);
    }

    @Override
    @Caching(evict = {
            @CacheEvict("answer"),
            @CacheEvict("claim")
    })
    public void deleteAdminAnswers(Long idAdmin) {
        if(adminEntityRepository.findById(idAdmin).isEmpty())
            throw new NoSuchAdminException("There is no Admin with id: " + idAdmin);
        answerEntityRepository.findAllAdminAnswer(idAdmin).forEach(p ->
                    setUnanswered(p.getClaimEntity().getIdClaim()));
        answerEntityRepository.deleteAllAdminAnswers(idAdmin);
    }

    //Used when deleting an answer. Specify that the claim was unanswered again
    @Override
    public void setUnanswered(Long idClaim) {
        if(claimEntityRepository.findById(idClaim).isPresent()) {
            ClaimEntity claimEntity = claimEntityRepository.findById(idClaim).get();
            claimEntity.setAnswered(false);
            claimEntityRepository.save(claimEntity);
        }
    }


}
