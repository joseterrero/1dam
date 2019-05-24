package com.salesianostriana.dam.tiendamovil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.tiendamovil.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Usuario findFirstByUsernameAndPassword(String username, String password);

	public List<Usuario> findByNombreContainingIgnoreCase(String nombre);

	public Usuario findFirstByUsername(String username);

	public Usuario findFirstByCorreo(String correo);

	public Usuario findFirstByUsernameIgnoreCase(String username);

	public Usuario findFirstByUsernameIgnoreCaseAndPassword(String username, String password);

}
