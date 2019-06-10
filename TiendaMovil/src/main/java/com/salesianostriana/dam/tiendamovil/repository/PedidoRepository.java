package com.salesianostriana.dam.tiendamovil.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.tiendamovil.modelo.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	public List<Pedido> findByUsuario(long id);
	public Page<Pedido> findByUsuario(long id, Pageable pageable);
	public List<Pedido> findById(long id);
	public Page<Pedido> findById(long id, Pageable pageable);

}
