package com.salesianostriana.dam.tiendamovil.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.tiendamovil.baseservice.BaseService;
import com.salesianostriana.dam.tiendamovil.modelo.Producto;
import com.salesianostriana.dam.tiendamovil.repository.ProductoRepository;

@Service
public class ProductoService extends BaseService<Producto, Long, ProductoRepository> {

}
