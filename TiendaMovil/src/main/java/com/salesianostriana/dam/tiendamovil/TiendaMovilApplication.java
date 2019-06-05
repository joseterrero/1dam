package com.salesianostriana.dam.tiendamovil;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.salesianostriana.dam.tiendamovil.modelo.Usuario;
import com.salesianostriana.dam.tiendamovil.service.LineaPedidoService;
import com.salesianostriana.dam.tiendamovil.service.PedidoService;
import com.salesianostriana.dam.tiendamovil.service.ProductoService;
import com.salesianostriana.dam.tiendamovil.service.UsuarioService;

@SpringBootApplication
public class TiendaMovilApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaMovilApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner init(UsuarioService usuService, ProductoService prodService, PedidoService pedService,
//			LineaPedidoService linPedService, BCryptPasswordEncoder passwordEncoder) {
//		return args -> {
//
//			/**
//			 * Bucle que encripta todas las contraseñas de los usuarios al iniciar el
//			 * programa
//			 */
//			for (Usuario usu : usuService.findAll()) {
//
//				usu.setPassword(passwordEncoder.encode(usu.getPassword()));
//				usuService.edit(usu);
//
//			}
//
//		};

	/*
	 * @Bean public CommandLineRunner init(UsuarioService servicio,
	 * BCryptPasswordEncoder passwordEncoder) { return args -> {
	 * 
	 * Usuario u = new Usuario(); u.setAdmin(true); u.setNombre("admin");
	 * u.setApellidos("admin"); u.setUsername("admin");
	 * u.setCorreo("admin@admin.com");
	 * u.setPassword(passwordEncoder.encode("admin"));
	 * 
	 * servicio.save(u);
	 * 
	 * Usuario a = new Usuario(); a.setAdmin(false); a.setNombre("usuario");
	 * a.setApellidos("usuario"); a.setUsername("usuario");
	 * a.setCorreo("usuario@usuario.com");
	 * a.setPassword(passwordEncoder.encode("1234"));
	 * 
	 * servicio.save(a);
	 * 
	 * };
	 */

}
