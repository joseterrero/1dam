package com.salesianostriana.dam.tiendamovil.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class LineaPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private long cantidad;
	@ManyToOne
	private Producto producto;
	@ManyToOne
	private Pedido pedido;
	@Column(name = "precio_linea_final")
	private double precioFinal;

	public LineaPedido(long cantidad, Producto producto, Pedido pedido) {
		this.cantidad = cantidad;
		this.producto = producto;
		this.pedido = pedido;
	}

	public LineaPedido(Producto producto, long cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioFinal = calcularLineaFinal();
	}

	public double calcularLineaFinal() {
		return this.getProducto().getPrecio() * this.getCantidad();
	}

}
