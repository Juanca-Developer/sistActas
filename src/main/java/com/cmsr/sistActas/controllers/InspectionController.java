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
import com.cmsr.sistActas.entitys.InspectionEntity;
import com.cmsr.sistActas.entitys.InspectorEntity;
import com.cmsr.sistActas.repositorys.CompanyRepository;
import com.cmsr.sistActas.repositorys.InspectorRepository;
import com.cmsr.sistActas.services.InspectionService;

@Controller
@RequestMapping("/inspection")
public class InspectionController {

    @Autowired
    InspectionService inspectionService;

    @Autowired
    CompanyRepository companyRepository;
    
    @Autowired
    InspectorRepository inspectorRepository;    

         
  @GetMapping("/register") 
  public String inspection(ModelMap modelo){ 

    List<CompanyEntity> companys = companyRepository.findAll();
    modelo.put("companys", companys);
    List<InspectorEntity> inspectors = inspectorRepository.findAll();
    modelo.put("inspectors", inspectors);
      return "form-inspection"; 
  
  }       
  
  @PostMapping("/register") 
  public String guardar(ModelMap modelo, @RequestParam(name = "dateInspection", defaultValue = "2000-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd")   Date dateInspection,@RequestParam("nroActa")Integer nroActa, @RequestParam("idCompany")String idCompany, @RequestParam(name = "periodStarter", defaultValue = "01-2000") @DateTimeFormat(pattern = "MM-yyyy")  Date periodStarter, @RequestParam(name = "periodFinal", defaultValue = "01-2000") @DateTimeFormat(pattern = "MM-yyyy")  Date periodFinal, @RequestParam Integer amount,@RequestParam("idInspector")String idInspector){ 
   
    try{
           inspectionService.register(dateInspection,nroActa, idCompany,periodStarter,periodFinal,amount,idInspector);
           
           modelo.put("exito", "El acta de inspeccion se creo exitosamente");
      
      return "form-inspection.html";
      
      } catch (Exception e) {
          
          modelo.put("error", e.getMessage());
          modelo.put("dateInspection",dateInspection);
          modelo.put("company",idCompany);
          modelo.put("periodStarter", periodStarter);
          modelo.put("periodFinal", periodFinal);
          modelo.put("amount", amount);
          modelo.put("inspector", idInspector);
          
  
  
  
          return "form-inspection.html";
      }
     
       } 
       @GetMapping("/list")
       public String lista(ModelMap modelo) {
           
           List<InspectionEntity> todos = inspectionService.listarTodos();
           
           modelo.addAttribute("inspections", todos);
           
           return "list-inspections";
       }      
  }
    

