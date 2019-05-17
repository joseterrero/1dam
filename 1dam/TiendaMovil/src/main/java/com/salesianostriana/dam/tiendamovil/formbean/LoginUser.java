package com.salesianostriana.dam.tiendamovil.formbean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUser {
	
	private String nomUsuario;
	private String contrasenya;
	
	public LoginUser(String nomUsuario, String contrasenya) {
		super();
		this.nomUsuario = nomUsuario;
		this.contrasenya = contrasenya;
	}

	

}
