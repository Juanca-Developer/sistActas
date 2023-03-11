package com.cmsr.sistActas.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmsr.sistActas.entitys.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity,String> {
    
}
