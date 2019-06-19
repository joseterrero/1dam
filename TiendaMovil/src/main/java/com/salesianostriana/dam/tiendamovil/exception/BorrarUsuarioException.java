package com.salesianostriana.dam.tiendamovil.exception;

public class BorrarUsuarioException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public BorrarUsuarioException() {
		
	}
	
	public BorrarUsuarioException(String mensaje) {
		super ("No puede ser eliminado porque es el usuario actual.");
	}

}
