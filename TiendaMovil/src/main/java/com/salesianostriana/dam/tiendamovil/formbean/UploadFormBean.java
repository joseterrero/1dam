package com.salesianostriana.dam.tiendamovil.formbean;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que modela el Modal Object que se recogerá del formulario menos la
 * imagen o fichero a subir, en este caso sencillo, solo la propiedadCualquiera
 * pero podrían ser más (nombre, fecha de caducidad...)
 */

@Data
@NoArgsConstructor
public class UploadFormBean {

	private String modelo;
	private String color;
	private long capacidad;
	private long ram;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha;
	private double precio;
	private long cantidad;
	private String fileUrl;

	/*
	public UploadFormBean(String modelo, String color, long capacidad, long ram, LocalDate fecha, double precio,
			long cantidad, String fileUrl) {
		super();
		this.modelo = modelo;
		this.color = color;
		this.capacidad = capacidad;
		this.ram = ram;
		this.fecha = fecha;
		this.precio = precio;
		this.cantidad = cantidad;
		this.fileUrl = fileUrl;
	}
*/

}
