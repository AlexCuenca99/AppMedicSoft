package edu.espoch.ejemploAppMedicSoft.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medicamento")

public class Medicamento {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
	private Long id;
	
	private String nombre;
	
	private String codigo;
	
	private String fechaElab;
	
	private String fechaVenc;
	
	private int cantidad;
	
	private float precio;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getFechaElab() {
		return fechaElab;
	}

	public void setFechaElab(String fechaElab) {
		this.fechaElab = fechaElab;
	}

	public String getFechaVenc() {
		return fechaVenc;
	}

	public void setFechaVenc(String fechaVenc) {
		this.fechaVenc = fechaVenc;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	
	
}
