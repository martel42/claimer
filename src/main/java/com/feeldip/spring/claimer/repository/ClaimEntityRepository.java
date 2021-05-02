package com.feeldip.spring.claimer.repository;

import com.feeldip.spring.claimer.entity.ClaimEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimEntityRepository extends JpaRepository<ClaimEntity, Long> {

    @Query("select c from ClaimEntity c where c.isAnswered = false")
    List<ClaimEntity> findAllUnansweredClaim();

    @Query("select c from ClaimEntity c where c.claimerEntity.idUser = :idClaimer")
    List<ClaimEntity> findAllClaimerClaim(@Param("idClaimer") Long idClaimer);

    @Query("select c from ClaimEntity c where c.claimerEntity.idUser = :idClaimer and c.isAnswered = false")
    List<ClaimEntity> findUnansweredClaimerClaim(@Param("idClaimer") Long idClaimer);

    @Query("delete from ClaimEntity c where c.claimerEntity.idUser= :idUser")
    void deleteAllClaimerClaims(@Param("idUser") Long idUser);

}
