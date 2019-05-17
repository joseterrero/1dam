package com.salesianostriana.dam.tiendamovil.modelo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private LocalDateTime fecha;

	@OneToMany(mappedBy = "pedido")
	private List<LineaPedido> lista;
	@ManyToOne
	private Usuario usuario;

	public Pedido(LocalDateTime fecha, List<LineaPedido> lista, Usuario usuario) {
		super();
		this.fecha = fecha;
		this.lista = lista;
		this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
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
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
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
		Pedido other = (Pedido) obj;
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
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", fecha=" + fecha + ", lista=" + lista + ", usuario=" + usuario + "]";
	}

	public void addLineaPedido(LineaPedido linPed) {
		this.lista.add(linPed);
		linPed.setPedido(this);
	}

	public void removeLineaPedido(LineaPedido linPed) {
		this.lista.remove(linPed);
		linPed.setPedido(null);
	}

}
