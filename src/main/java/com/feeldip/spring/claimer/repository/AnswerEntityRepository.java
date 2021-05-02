package com.feeldip.spring.claimer.repository;

import com.feeldip.spring.claimer.entity.AnswerEntity;
import com.feeldip.spring.claimer.entity.ClaimEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerEntityRepository extends JpaRepository<AnswerEntity, Long> {

    @Query("select a from AnswerEntity a where a.adminEntity.idAdmin = :idAdmin")
    List<AnswerEntity> findAllAdminAnswer(@Param("idAdmin") Long idAdmin);

    @Query("delete from AnswerEntity a where a.adminEntity.idAdmin = :idAdmin")
    void deleteAllAdminAnswers(@Param("idAdmin") Long idAdmin);

}
