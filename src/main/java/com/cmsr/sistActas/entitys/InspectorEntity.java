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
@Table(name = "inspectors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE inspectors SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete = false")
public class InspectorEntity {

@Id
@GeneratedValue(generator = "uuid")
@GenericGenerator(name = "uuid", strategy = "uuid2")
private String idInspector;
private String name;
private String lastName;
private String adress;
private String telephone;
private String mail;
private Long numberFile;


@Temporal(TemporalType.TIMESTAMP)
private Date startDate;

private Boolean softDelete = Boolean.FALSE;


    
}
