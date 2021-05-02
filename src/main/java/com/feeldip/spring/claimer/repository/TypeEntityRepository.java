package com.feeldip.spring.claimer.repository;

import com.feeldip.spring.claimer.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeEntityRepository extends JpaRepository<TypeEntity, Long> {

    TypeEntity findTypeEntityByNameType(String name);

    void deleteByNameType(@Param("name") String name);

}
