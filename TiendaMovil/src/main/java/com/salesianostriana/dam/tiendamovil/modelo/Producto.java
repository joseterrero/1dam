package com.salesianostriana.dam.tiendamovil.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String modelo;
	private String color;
	private long capacidad;
	private long ram;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha;
	private double precio;
	private long cantidad;

	private String fileUrl;

	@OneToMany(mappedBy = "producto")
	private List<LineaPedido> lista;
	@ManyToOne
	private Usuario usuario;

	public Producto(String modelo, String color, long capacidad, long ram, LocalDate fecha, double precio,
			long cantidad, String fileUrl, List<LineaPedido> lista, Usuario usuario) {
		this.modelo = modelo;
		this.color = color;
		this.capacidad = capacidad;
		this.ram = ram;
		this.fecha = fecha;
		this.precio = precio;
		this.cantidad = cantidad;
		this.fileUrl = fileUrl;
		this.lista = lista;
		this.usuario = usuario;
	}

	public void addLineaPedido(LineaPedido linPed) {
		this.lista.add(linPed);
		linPed.setProducto(this);
	}

	public void removeLineaPedido(LineaPedido linPed) {
		this.lista.remove(linPed);
		linPed.setProducto(null);
	}

}
