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
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha;
	private double precioFinal;

	@OneToMany(mappedBy = "pedido")
	private List<LineaPedido> lista;
	@ManyToOne
	private Usuario usuario;

	public Pedido(LocalDate fecha, double precioFinal, List<LineaPedido> lista, Usuario usuario) {
		super();
		this.fecha = fecha;
		this.precioFinal = calcularPrecioFinal();
		this.lista = lista;
		this.usuario = usuario;
	}

	public void addLineaPedido(LineaPedido linPed) {
		this.lista.add(linPed);
		linPed.setPedido(this);
	}

	public void removeLineaPedido(LineaPedido linPed) {
		this.lista.remove(linPed);
		linPed.setPedido(null);
	}

	public double calcularPrecioFinal() {
		double total = 0;
		for (LineaPedido linPed : lista) {
			total += linPed.getPrecioFinal();
		}
		return total;
	}

}
