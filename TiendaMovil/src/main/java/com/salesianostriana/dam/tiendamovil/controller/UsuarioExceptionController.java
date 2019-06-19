package com.salesianostriana.dam.tiendamovil.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.salesianostriana.dam.tiendamovil.exception.BorrarUsuarioException;

@ControllerAdvice
public class UsuarioExceptionController {
	
	@ExceptionHandler (BorrarUsuarioException.class)
	public String usuarioException(Model model, BorrarUsuarioException usuExcep) {
		model.addAttribute("excep", usuExcep);
		return "errorBorrarUsuario";
	}
}
