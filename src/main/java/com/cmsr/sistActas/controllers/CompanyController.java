package com.cmsr.sistActas.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmsr.sistActas.entitys.CompanyEntity;
import com.cmsr.sistActas.exceptions.ExceptionService;
import com.cmsr.sistActas.services.CompanyService;

@Controller
@RequestMapping("/")
public class CompanyController {
    
    @Autowired
    CompanyService companyService;
        
    
    
          
    @GetMapping("/company")
    public String business(ModelMap modelo) {
        List<CompanyEntity> empresasActivos = companyService.listarTodos();
        // Recordar que utilizo el modelo,para viajar con la llave usuarios al HTML la
        // lista usuariosactivos
        modelo.addAttribute("companys", empresasActivos);
        return "company.html";
    }
    
    @GetMapping("/company-register")
    public String business() {
       
        return "form-company.html";
    }
    
    @PostMapping("/company-register") 
    public String register(ModelMap model,  @RequestParam Long cuit,@RequestParam String companyName,  @RequestParam String adress, @RequestParam String email,@RequestParam String phone, @RequestParam  String contact, @RequestParam(name = "starterDate", defaultValue = "2000-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date starterDate){ 
        
        try {
             companyService.register(companyName,  cuit,  adress, phone,  email, contact,starterDate);
            
        
        model.put("exito", "La empresa se registro exitosamente");
        
        return "form-company.html";
        
        } catch (ExceptionService e) {
            
            model.put("error", e.getMessage());
            
            return "form-company.html";
        }
       
         }  
         @GetMapping("/company-list")
         public String lista(ModelMap modelo) {
             
             List<CompanyEntity> everybody =companyService.listarTodos();
             
             modelo.addAttribute("companys", everybody);
             
             return "company-list.html";
         }
    }
    


