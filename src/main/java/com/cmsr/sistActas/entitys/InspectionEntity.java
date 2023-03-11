package com.cmsr.sistActas.entitys;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Data
@Table(name = "inspection")
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE inspection SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete = false")
public class InspectionEntity {
	
@Id
@GeneratedValue(generator = "uuid")
@GenericGenerator(name = "uuid", strategy = "uuid2")
private String idInspection;

@Temporal(TemporalType.TIMESTAMP)
private Date dateInspection;

private Integer nroActa;

@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
private CompanyEntity company;

@Temporal(TemporalType.TIMESTAMP)
private Date periodStarter;

@Temporal(TemporalType.TIMESTAMP)
private Date periodFinal;

private Integer amount;

@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
private InspectorEntity inspector;

private Boolean softDelete = Boolean.FALSE;
}