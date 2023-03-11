package com.cmsr.sistActas.entitys;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company")
@Data

@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE company SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete = false")
public class CompanyEntity {
    
@Id
@GeneratedValue(generator = "uuid")
@GenericGenerator(name = "uuid", strategy = "uuid2")
	    
private String idCompany;
private Long cuit;
private String companyName;
private String adress;
private String email;
private String phone;
private String contact;
	    
@Temporal(TemporalType.TIMESTAMP)
private Date starterDate;

private Boolean softDelete = Boolean.FALSE;
}
