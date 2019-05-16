package com.salesianostriana.dam.tiendamovil.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	public Usuario validateUser(String nomUsuario, String contrasenya) {
		final Usuario currentUser = repositorio.findFirstByNomUsuarioIgnoreCase(nomUsuario);
		final BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		if (repositorio.findFirstByNomUsuarioIgnoreCase(nomUsuario) != null) {
			if (pwEncoder.matches(contrasenya, currentUser.getContrasenya())) {
				return repositorio.findFirstByNomUsuarioIgnoreCaseAndContrasenya(nomUsuario, contrasenya);
			}
		}
		return null;
	}

}
