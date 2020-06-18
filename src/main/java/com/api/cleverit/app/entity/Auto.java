package com.api.cleverit.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "autos")
public class Auto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@Column
	private String color;
	@Column
	private String patente;
	@Column
	private String tipoAuto;

}
