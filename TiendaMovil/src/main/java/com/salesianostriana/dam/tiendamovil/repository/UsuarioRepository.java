package com.salesianostriana.dam.tiendamovil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.tiendamovil.modelo.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Usuario findFirstByUsernameAndPass(String username, String pass);

	List<Usuario> findByNombreContainingIgnoreCase(String nombre);

	public Usuario findFirstByUsername(String nombre);

	public Usuario findFirstByEmail(String email);

}
