package com.salesianostriana.dam.tiendamovil.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.tiendamovil.modelo.Usuario;
import com.salesianostriana.dam.tiendamovil.service.UsuarioService;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	UsuarioService usuarioService;

	public UserDetailsServiceImpl(UsuarioService servicio) {
		this.usuarioService = servicio;
	}

	@Override
	public UserDetails loadUserByUsername(String nomUsuario) throws UsernameNotFoundException {

		Usuario usuario = usuarioService.findOneByNomUsuario(nomUsuario);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}

		return usuario;

	}

}