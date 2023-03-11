package com.cmsr.sistActas.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmsr.sistActas.entitys.CompanyEntity;
import com.cmsr.sistActas.entitys.InspectionEntity;
import com.cmsr.sistActas.entitys.InspectorEntity;
import com.cmsr.sistActas.exceptions.ExceptionService;
import com.cmsr.sistActas.repositorys.CompanyRepository;
import com.cmsr.sistActas.repositorys.InspectionRepository;
import com.cmsr.sistActas.repositorys.InspectorRepository;

@Service
public class InspectionService {

    @Autowired
    InspectionRepository inspectionRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    InspectorRepository inspectorRepository;
    

    @Transactional
    public void register(Date dateInspection, Integer nroActa, String idCompany, Date periodStarter, Date periodFinal ,Integer amount, String idInspector) throws ExceptionService{
        
	   InspectionEntity inspection = new InspectionEntity();

       inspection.setDateInspection(dateInspection);
       inspection.setNroActa(nroActa);
       inspection.setCompany(companyRepository.getById(idCompany));
       inspection.setPeriodStarter(periodStarter);
       inspection.setPeriodFinal(periodFinal);
       inspection.setAmount(amount);
       inspection.setInspector(inspectorRepository.getById(idInspector));
       
             
       inspectionRepository.save(inspection);
       
    
       
   }
   
 
    
    @Transactional
    public void modify (String idInspection, Date dateInspection, CompanyEntity company, Date periodStarter, Date periodFinal ,Integer amount, InspectorEntity inspector) throws ExceptionService {
        
        validate(dateInspection,company,periodStarter,periodFinal,amount,inspector); 
        
        Optional<InspectionEntity> response = inspectionRepository.findById(idInspection);
        
       if (response.isPresent()){
           
           InspectionEntity inspection = response.get();
           inspection.setDateInspection(dateInspection);
           inspection.setCompany(company);
           inspection.setPeriodStarter(periodStarter);
           inspection.setPeriodFinal(periodFinal);
           inspection.setAmount(amount);
           inspection.setInspector(inspector);
                
           inspectionRepository.save(inspection);
           
       } else {
           throw new ExceptionService ("No se encontro la inspeccion buscada");
       }
        
       
        
    }
    
    @Transactional(readOnly = true)
    public List <InspectionEntity> listarTodos(){
        
        return inspectionRepository.findAll();
    }
    
    
    
    private void validate (Date dateInspection, CompanyEntity company, Date periodStarter, Date periodFinal ,Integer amount, InspectorEntity inspector) throws ExceptionService{
        
        if (dateInspection == null || dateInspection.toString().isEmpty()) {

            throw new ExceptionService("La Fecha no puede ser nula o estar vacía");
        }
        if (company == null || company.toString().isEmpty()) {

            throw new ExceptionService("La empresa no puede ser nula o estar vacía");
        }
        if (periodStarter == null || periodStarter.toString().isEmpty()) {

            throw new ExceptionService("El periodo de inicio no puede estar vacío");

        }
        if (periodFinal== null || periodFinal.toString().isEmpty()) {

            throw new ExceptionService("El periodo final no puede estar vacío");
        }
        if (amount == null ||amount.toString().isEmpty()) {

            throw new ExceptionService("Debe completar el importe con un valor valido");
        }
        if (inspector == null || inspector.toString().isEmpty()) {

            throw new ExceptionService("El inspector no puede ser nulo o estar vacío");
    }
    
} 
}