package com.cmsr.sistActas.services;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmsr.sistActas.entitys.InspectorEntity;
import com.cmsr.sistActas.exceptions.ExceptionService;
import com.cmsr.sistActas.repositorys.InspectorRepository;

@Service
public class InspectorService {

@Autowired
 InspectorRepository inspectorRepository;
    

    
    @Transactional
    public void register(String name, String lastName, String adress,String mail, String telephone) throws ExceptionService{
        
        validate(name,lastName,adress,mail,telephone);
              
        InspectorEntity inspector = new InspectorEntity();
        
        
        inspector.setName(name);
        inspector.setLastName(lastName);
        inspector.setAdress(adress);
        inspector.setMail(mail);
        inspector.setTelephone(telephone);
        
            
       
        
        inspectorRepository.save(inspector);
        
     
        
    }
    
    @Transactional
    public void modify (String idInspector,String name, String lastName, String adress,String mail, String telephone) throws ExceptionService {
        
        validate(name,lastName,adress,mail,telephone); 
        
        Optional<InspectorEntity> response = inspectorRepository.findById(idInspector);
        
       if (response.isPresent()){

          InspectorEntity inspector = response.get();

          inspector.setName(name);
          inspector.setLastName(lastName);
          inspector.setAdress(adress);
          inspector.setMail(mail);
          inspector.setTelephone(telephone);


           inspectorRepository.save(inspector);                
          
           
       } else {
           throw new ExceptionService ("No se encontro el inspector solicitado");
       }
        
       
        
       
        
    }
    
    @Transactional(readOnly = true)
    public List <InspectorEntity> listarTodos(){
        
        return inspectorRepository.findAll();
    }
    
    
    
    private void validate(String name, String lastName, String adress,String mail, String telephone) throws ExceptionService{
        
        if (name == null || name.isEmpty()) {

            throw new ExceptionService("El nombre no puede ser nulo");
        }
        if (lastName == null || lastName.isEmpty()) {

            throw new ExceptionService("El apellido no puede ser nulo");
        }
        if (adress == null || adress.isEmpty()) {

            throw new ExceptionService("La dirección no puede ser nula");

        }
        if (mail == null || mail.isEmpty()) {

            throw new ExceptionService("El mail no puede ser nulo");
        }
        if (telephone == null || telephone.isEmpty()) {

            throw new ExceptionService("El telefono no puede ser nulo");
        }
    }   
    
}
