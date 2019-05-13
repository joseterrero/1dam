package com.salesianostriana.dam.tiendamovil.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.tiendamovil.baseservice.BaseService;
import com.salesianostriana.dam.tiendamovil.modelo.Pedido;
import com.salesianostriana.dam.tiendamovil.repository.PedidoRepository;

@Service
public class PedidoService extends BaseService<Pedido, Long, PedidoRepository> {

}
