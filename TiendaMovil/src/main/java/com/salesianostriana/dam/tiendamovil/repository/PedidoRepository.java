package com.salesianostriana.dam.tiendamovil.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.tiendamovil.modelo.Pedido;
import com.salesianostriana.dam.tiendamovil.modelo.Usuario;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	public List<Pedido> findByUsuario(Usuario usuario);

	public Page<Pedido> findByUsuario(Usuario usuario, Pageable pageable);

	public List<Pedido> findById(long id);

	public Page<Pedido> findById(long id, Pageable pageable);
	
//	public Page<Pedido> findAll(Usuario usuario, Pageable pageable);

}
