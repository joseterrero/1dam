package com.salesianostriana.dam.tiendamovil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
