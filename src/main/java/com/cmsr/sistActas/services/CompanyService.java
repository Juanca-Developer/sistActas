package com.cmsr.sistActas.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmsr.sistActas.entitys.CompanyEntity;
import com.cmsr.sistActas.exceptions.ExceptionService;
import com.cmsr.sistActas.repositorys.CompanyRepository;

@Service
public class CompanyService {
    

@Autowired
CompanyRepository companyRepository;

@Transactional
public void register(String companyName, Long cuit, String adress, String phone, String email, String contact, Date starterDate) throws ExceptionService{

    validate(companyName, cuit,adress,phone,email,contact);

    CompanyEntity company = new CompanyEntity();

   company.setCompanyName(companyName);
   company.setCuit(cuit);
   company.setAdress(adress);
   company.setPhone(phone);
   company.setEmail(email);
   company.setContact(contact);
   company.setStarterDate(starterDate);



    companyRepository.save(company);

}



@Transactional
public void modify(String id, String companyName, Long cuit, String adress, String phone, String email, String contact, Date starterDate) throws ExceptionService {

    validate(companyName, cuit,adress,phone,email,contact);

    Optional<CompanyEntity> response = companyRepository.findById(id);

    if (response.isPresent()) {

        CompanyEntity company = response.get();
        company.setCompanyName(companyName);
        company.setCuit(cuit);
        company.setAdress(adress);
        company.setPhone(phone);
        company.setEmail(email);
        company.setContact(contact);
        company.setStarterDate(starterDate);
        
            
       companyRepository.save(company);
       
   } else {
       throw new ExceptionService ("No se encontró la empresa buscada");
   }
    
    
}

@Transactional(readOnly=true)
public CompanyEntity buscarPorId(String id) throws ExceptionService {

    Optional<CompanyEntity> response = companyRepository.findById(id);
    if (response.isPresent()) {

        CompanyEntity company= response.get();
        return company;
    } else {

        throw new ExceptionService("No se encontró la empresa buscada");
    }

}

@Transactional(readOnly = true)
public List <CompanyEntity> listarTodos(){
    
    return companyRepository.findAll();
}





private void validate(String companyName, Long cuit, String adress, String phone, String email, String contact) throws ExceptionService{
    
    if (companyName == null ||companyName.isEmpty()) {

        throw new ExceptionService("El nombre no puede ser nulo o estar vacío");
    }
    if (cuit == null || cuit.toString().isEmpty()) {

        throw new ExceptionService("El CUIT no puede ser nulo o estar vacío");
    }
    if (adress== null || adress.isEmpty()) {

        throw new ExceptionService("La dirección no puede ser nula o estar vacía");

    }
    if (phone == null || phone.isEmpty()) {

        throw new ExceptionService("El numero telefonico no puede estar vacío");
    }
    if (email== null || email.isEmpty()) {

        throw new ExceptionService("El e-mail no puede ser nulo o vacío");
    }
    if (contact== null || contact.isEmpty()) {

        throw new ExceptionService("El contacto no puede ser nulo o vacío");
    }
}




}
