package com.salesianostriana.dam.tiendamovil.formbean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUser {
	
	private String username;
	private String contrasenya;
	
	public LoginUser(String username, String contrasenya) {
		super();
		this.username = username;
		this.contrasenya = contrasenya;
	}

	

}
