package com.cmsr.sistActas.repositorys;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmsr.sistActas.entitys.InspectorEntity;

@Repository
public interface InspectorRepository extends JpaRepository<InspectorEntity,String> {
    
}
