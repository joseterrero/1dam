package com.salesianostriana.dam.tiendamovil.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.salesianostriana.dam.tiendamovil.baseservice.BaseService;
import com.salesianostriana.dam.tiendamovil.modelo.Producto;
import com.salesianostriana.dam.tiendamovil.repository.ProductoRepository;
// import com.salesianostriana.dam.tiendamovil.storage.StorageService;

@Service
public class ProductoService extends BaseService<Producto, Long, ProductoRepository> {
	
	public void delete(long id) {
		repositorio.deleteById(id);
	}
	
	public Producto edit(Producto prod) { 
		return repositorio.save(prod); 
	}

//	@Autowired
//	StorageService storageService;
//
//	/**
//	 * 
//	 * @param p Debe contener el atributo fileUrl a nulo; si no, su valor será
//	 *          ignorado
//	 */
//	public void add(Producto p, MultipartFile file) {
//
//		String fileName = storageService.store(file);// Guarda la imagen
//		// Guardamos nombre de la imagen almacenada en el atributo de la entidad
//		p.setFileUrl(fileName);
//		// Guardamos la entidad en la base de datos y en ella ya irá el nombre del archivo
//		// en la correspondiente propiedad (fileUrl)
//		repositorio.save(p);
//	}
//
//	/**
//	 * Método que devuelve una lista de entidades con sus ficheros a los que se les
//	 * ha guardado dentro de su atributo URL el nombre de la URL
//	 * images/nombredelarchivo. Este /images/ debe ser el mismo que el que hayamos
//	 * dado en la clase MvcConfig del paquete Hello
//	 */
//
//	public List<Producto> list() {
//
//		List<Producto> partialResult = repositorio.findAll();
//		List<Producto> result = new LinkedList<Producto>(partialResult);
//
//		for (int i = 0; i < partialResult.size(); i++) {
//			String fileName = partialResult.get(i).getFileUrl();
//			result.get(i).setFileUrl("/images/" + fileName);
//		}
//
//		return result;
//	}

}
