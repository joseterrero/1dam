package com.salesianostriana.dam.tiendamovil.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.tiendamovil.baseservice.BaseService;
import com.salesianostriana.dam.tiendamovil.modelo.LineaPedido;
import com.salesianostriana.dam.tiendamovil.modelo.Pedido;
import com.salesianostriana.dam.tiendamovil.repository.LineaPedidoRepository;

@Service
public class LineaPedidoService extends BaseService<LineaPedido, Long, LineaPedidoRepository> {
	
	public LineaPedido findById(Long id) {
		return repositorio.findById(id).orElse(null);
	}
	
	public Page<LineaPedido> findByPedido(Pedido pedido, Pageable pageable){
		return repositorio.findByPedido(pedido, pageable);
	}

}
