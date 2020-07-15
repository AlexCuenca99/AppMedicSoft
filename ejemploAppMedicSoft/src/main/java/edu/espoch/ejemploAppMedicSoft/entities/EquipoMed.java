package edu.espoch.ejemploAppMedicSoft.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "equipoMedico")

public class EquipoMed {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
	private Long id;
	
	private String nombre;
	
	private String codigo;
	
	private float precio;
	
	private String fechaComp;
	
	private String areaUso;
	
	private int cantidad;
	
	private int inventarioDisp;

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

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getFechaComp() {
		return fechaComp;
	}

	public void setFechaComp(String fechaComp) {
		this.fechaComp = fechaComp;
	}

	public String getAreaUso() {
		return areaUso;
	}

	public void setAreaUso(String areaUso) {
		this.areaUso = areaUso;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getInventarioDisp() {
		return inventarioDisp;
	}

	public void setInventarioDisp(int inventarioDisp) {
		this.inventarioDisp = inventarioDisp;
	}
	
}
