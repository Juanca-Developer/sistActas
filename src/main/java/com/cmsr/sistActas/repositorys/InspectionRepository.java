package com.cmsr.sistActas.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmsr.sistActas.entitys.InspectionEntity;

@Repository
public interface InspectionRepository extends JpaRepository<InspectionEntity, String>{
    
}
