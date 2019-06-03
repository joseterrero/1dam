package com.salesianostriana.dam.tiendamovil.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.tiendamovil.modelo.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

	public List<Producto> findByModeloContainingIgnoreCase(String modelo);

//	public Page<Producto> findAll(Pageable pageable);

	public Page<Producto> findByModeloContainingIgnoreCase(String modelo, Pageable pageable);

}
