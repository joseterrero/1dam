package com.salesianostriana.dam.tiendamovil.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nombre;
	private String apellidos;
	private String correo;
	private String contrasenya;
	private String nomUsuario;
	private boolean admin;

	@OneToMany(mappedBy = "usuario")
	private List<Producto> lista;
	@OneToMany(mappedBy = "usuario")
	private List<Pedido> listaPed;

	public Usuario(String nombre, String apellidos, String correo, String contrasenya, String nomUsuario, boolean admin,
			List<Producto> lista, List<Pedido> listaPed) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.contrasenya = contrasenya;
		this.nomUsuario = nomUsuario;
		this.admin = admin;
		this.lista = lista;
		this.listaPed = listaPed;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public String getNomUsuario() {
		return nomUsuario;
	}

	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}

	public boolean admin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public List<Producto> getLista() {
		return lista;
	}

	public void setLista(List<Producto> lista) {
		this.lista = lista;
	}

	public List<Pedido> getListaPed() {
		return listaPed;
	}

	public void setListaPed(List<Pedido> listaPed) {
		this.listaPed = listaPed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((contrasenya == null) ? 0 : contrasenya.hashCode());
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (admin ? 1231 : 1237);
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result + ((listaPed == null) ? 0 : listaPed.hashCode());
		result = prime * result + ((nomUsuario == null) ? 0 : nomUsuario.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Usuario other = (Usuario) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (contrasenya == null) {
			if (other.contrasenya != null)
				return false;
		} else if (!contrasenya.equals(other.contrasenya))
			return false;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
			return false;
		if (id != other.id)
			return false;
		if (admin != other.admin)
			return false;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (listaPed == null) {
			if (other.listaPed != null)
				return false;
		} else if (!listaPed.equals(other.listaPed))
			return false;
		if (nomUsuario == null) {
			if (other.nomUsuario != null)
				return false;
		} else if (!nomUsuario.equals(other.nomUsuario))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", correo=" + correo
				+ ", contrasenya=" + contrasenya + ", nomUsuario=" + nomUsuario + ", admin=" + admin + ", lista="
				+ lista + ", listaPed=" + listaPed + "]";
	}

	public void addProducto(Producto prod) {
		this.lista.add(prod);
		prod.setUsuario(this);
	}

	public void removeProducto(Producto prod) {
		this.lista.remove(prod);
		prod.setUsuario(null);
	}

	public void addPedido(Pedido pedido) {
		this.listaPed.add(pedido);
		pedido.setUsuario(this);
	}

	public void removePedido(Pedido pedido) {
		this.listaPed.remove(pedido);
		pedido.setUsuario(null);
	}

}
