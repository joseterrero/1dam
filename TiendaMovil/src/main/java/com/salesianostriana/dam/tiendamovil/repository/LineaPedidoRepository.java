package com.salesianostriana.dam.tiendamovil.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.tiendamovil.modelo.LineaPedido;
import com.salesianostriana.dam.tiendamovil.modelo.Pedido;

@Repository
public interface LineaPedidoRepository extends JpaRepository<LineaPedido, Long> {	
	
	public List<LineaPedido> findByPedido(Pedido pedido);
	public Page<LineaPedido> findByPedido(Pedido pedido, Pageable pageable);
	
	public List<LineaPedido> findById(long id);
	public Page<LineaPedido> findById(long id, Pageable pageable);

}