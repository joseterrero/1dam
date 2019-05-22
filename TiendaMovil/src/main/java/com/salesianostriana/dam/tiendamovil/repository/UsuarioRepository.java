package com.salesianostriana.dam.tiendamovil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.tiendamovil.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Usuario findFirstByNomUsuarioAndContrasenya(String nomUsuario, String contrasenya);

	public List<Usuario> findByNombreContainingIgnoreCase(String nombre);

	public Usuario findFirstByNomUsuario(String nomUsuario);

	public Usuario findFirstByCorreo(String correo);

	public Usuario findFirstByNomUsuarioIgnoreCase(String nomUsuario);

	public Usuario findFirstByNomUsuarioIgnoreCaseAndContrasenya(String nomUsuario, String contrasenya);

}
