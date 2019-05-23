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
	private boolean isAdmin;

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
		this.isAdmin = admin;
		this.lista = lista;
		this.listaPed = listaPed;
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
