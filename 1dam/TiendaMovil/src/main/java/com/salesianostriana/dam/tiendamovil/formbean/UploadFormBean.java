package com.salesianostriana.dam.tiendamovil.formbean;


/** Clase que modela el Modal Object que se recogerá del formulario menos la imagen o 
 * fichero a subir, en este caso sencillo, solo la propiedadCualquiera pero
 * podrían ser más (nombre, fecha de caducidad...)*/

public class UploadFormBean {
	
	private String propiedadCualquiera;
	
	public UploadFormBean() {}

	public String getPropiedadCualquiera() {
		return propiedadCualquiera;
	}

	public void setPropiedadCualquiera(String propiedadCualquiera) {
		this.propiedadCualquiera = propiedadCualquiera;
	}
	
	

}
