package com.salesianostriana.dam.tiendamovil.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.tiendamovil.baseservice.BaseService;
import com.salesianostriana.dam.tiendamovil.modelo.Usuario;
import com.salesianostriana.dam.tiendamovil.repository.UsuarioRepository;

@Service
public class UsuarioService extends BaseService<Usuario, Long, UsuarioRepository> {

	public Usuario findOneByUsername(String username) {
		return repositorio.findFirstByUsername(username);
	}

	public List<Usuario> findByNombre(String nombre) {
		return repositorio.findByNombreContainingIgnoreCase(nombre);
	}

	public Usuario findOneByCorreo(String correo) {
		return repositorio.findFirstByCorreo(correo);
	}

	public void delete(long id) {
		repositorio.deleteById(id);
	}

}
