package com.salesianostriana.dam.tiendamovil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.tiendamovil.baseservice.BaseService;
import com.salesianostriana.dam.tiendamovil.modelo.Pedido;
import com.salesianostriana.dam.tiendamovil.modelo.Usuario;
import com.salesianostriana.dam.tiendamovil.repository.PedidoRepository;

@Service
public class PedidoService extends BaseService<Pedido, Long, PedidoRepository> {

	@Autowired
	private PedidoRepository pedidoRepo;

	public Pedido findByIdd(Long id) {
		return pedidoRepo.findById(id).orElse(null);
	}

	public Page<Pedido> findById(long id, Pageable pageable) {
		return pedidoRepo.findById(id, pageable);
	}

	public Page<Pedido> findAllPageable(Pageable pageable) {
		return pedidoRepo.findAll(pageable);
	}

	public Page<Pedido> findByUsuario(Usuario usuario, Pageable pageable) {
		return pedidoRepo.findByUsuario(usuario, pageable);
	}
	
//	public Page<Pedido> findAll(Usuario usuario, Pageable pageable) {
//		return pedidoRepo.findAll(usuario, pageable);
//	}

}
