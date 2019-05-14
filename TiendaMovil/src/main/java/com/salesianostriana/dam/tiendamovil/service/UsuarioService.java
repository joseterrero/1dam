package com.salesianostriana.dam.tiendamovil.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.tiendamovil.baseservice.BaseService;
import com.salesianostriana.dam.tiendamovil.modelo.Usuario;
import com.salesianostriana.dam.tiendamovil.repository.UsuarioRepository;

@Service
public class UsuarioService extends BaseService<Usuario, Long, UsuarioRepository> {
	
	public Usuario findOneByNomUsuario(String nomUsuario) {
		return repositorio.findFirstByNomUsuario(nomUsuario);
	}
	
	public Usuario findOneByCorreo(String correo) {
		return repositorio.findFirstByCorreo(correo);
	}

}
