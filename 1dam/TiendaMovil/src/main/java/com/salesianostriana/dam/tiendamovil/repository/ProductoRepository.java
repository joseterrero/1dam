package com.salesianostriana.dam.tiendamovil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.tiendamovil.modelo.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
