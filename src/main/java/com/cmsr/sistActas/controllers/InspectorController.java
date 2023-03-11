package com.cmsr.sistActas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmsr.sistActas.entitys.InspectorEntity;
import com.cmsr.sistActas.services.InspectorService;

@Controller
@RequestMapping("/inspector")
public class InspectorController {


    @Autowired
   private InspectorService inspectorService;

    @GetMapping("/registro") 
    public String inspector(){ 
        return "form-inspector.html"; 
    
    }       
    
    @PostMapping("/registro") 
  public String guardar(ModelMap modelo, @RequestParam String name, @RequestParam String lastName, @RequestParam String adress, @RequestParam String mail, @RequestParam String telephone){ 
      
      try {

           inspectorService.register(name, lastName, adress, mail, telephone);
          
           
           modelo.put("exito", "El inspector se registro exitosamente");
      
      return "form-inspector.html";
      
      } catch (Exception e) {
          
          modelo.put("error", e.getMessage());
          modelo.put("name", name);
          modelo.put("apellido", lastName);
          modelo.put("direccion", adress);
          modelo.put("telefono", telephone);
          modelo.put("mail", mail);
          
  
  
  
          return "form-inspector.html";
      }
     
       } 
       @GetMapping("/lista")
       public String lista(ModelMap modelo) {
           
           List<InspectorEntity> todos = inspectorService.listarTodos();
           
           modelo.addAttribute("inspectors", todos);
           
           return "list-inspectors";
       }      
  }
