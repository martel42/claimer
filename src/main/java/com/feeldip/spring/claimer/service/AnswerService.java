package com.feeldip.spring.claimer.service;



import com.feeldip.spring.claimer.entity.AnswerEntity;

import java.util.List;

public interface AnswerService {
    List<AnswerEntity> getAllAnswers();
    List<AnswerEntity> getAdminAnswers (Long idAdmin);
    AnswerEntity getAnswerByID(Long idAnswer);
    String getTextAnswerById(Long idAnswer);

    void saveOrUpdateAnswer(AnswerEntity answer, Long idAdmin, Long idStatus, Long idClaim);

    void deleteAllAnswers();
    void deleteAnswerById(Long id);
    void deleteAdminAnswers(Long idAdmin);

    void setUnanswered(Long idClaim);
}
