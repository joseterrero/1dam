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

	@OneToMany(mappedBy = "producto")
	private List<LineaPedido> lista;
	@ManyToOne
	private Usuario usuario;

	public Producto(String modelo, String color, long capacidad, long ram, LocalDate fecha, double precio,
			long cantidad, List<LineaPedido> lista, Usuario usuario) {
		this.modelo = modelo;
		this.color = color;
		this.capacidad = capacidad;
		this.ram = ram;
		this.fecha = fecha;
		this.precio = precio;
		this.cantidad = cantidad;
		this.lista = lista;
		this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public long getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(long capacidad) {
		this.capacidad = capacidad;
	}

	public long getRam() {
		return ram;
	}

	public void setRam(long ram) {
		this.ram = ram;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public long getCantidad() {
		return cantidad;
	}

	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}

	public List<LineaPedido> getLista() {
		return lista;
	}

	public void setLista(List<LineaPedido> lista) {
		this.lista = lista;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cantidad ^ (cantidad >>> 32));
		result = prime * result + (int) (capacidad ^ (capacidad >>> 32));
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (ram ^ (ram >>> 32));
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (cantidad != other.cantidad)
			return false;
		if (capacidad != other.capacidad)
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (id != other.id)
			return false;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
			return false;
		if (ram != other.ram)
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", modelo=" + modelo + ", color=" + color + ", capacidad=" + capacidad + ", ram="
				+ ram + ", fecha=" + fecha + ", precio=" + precio + ", cantidad=" + cantidad + ", lista=" + lista
				+ ", usuario=" + usuario + "]";
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
