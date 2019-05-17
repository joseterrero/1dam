package com.salesianostriana.dam.tiendamovil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.tiendamovil.modelo.Producto;
import com.salesianostriana.dam.tiendamovil.repository.ProductoRepository;
import com.salesianostriana.dam.tiendamovil.service.ProductoService;

@Controller
public class ProductoController {

	@Autowired
	private ProductoRepository productRepo;

	@Autowired
	private ProductoService productService;

	@GetMapping("/admin/addProducto")
	public String mostrarFormRegistroProductoAdmin(Model model) {
		model.addAttribute("formProductoAdmin", new Producto());
		return "admin/anadirProductoAdmin";
	}

	@PostMapping("/newProductoAdmin")
	public String nuevoProductoAdmin(@ModelAttribute("formProductoAdmin") Producto producto,
			BindingResult bindingResult, Model model) {
		productRepo.save(producto);
		return "redirect:/admin/productosAdmin";
	}

	// Listar
	@GetMapping("/admin/listProductos")
	public String listarTodos(Model model) {
		model.addAttribute("listaProd", productService.findAll());
		return "admin/productosAdmin";

	}

	// Editar
	@GetMapping("/admin/editarProd/{id}")
	public String mostrarFormularioEdit(@PathVariable("id") long id, Model model) {

		Producto aEditar = productService.findById(id);

		if (aEditar != null) {
			model.addAttribute("listaProd", aEditar);
			return "admin/anadirProductoAdmin";
		} else {
			return "redirect:/admin/listProductos";
		}
	}

	@PostMapping("/editar/submit")
	public String editProducto(@ModelAttribute("listaProd") Producto producto) {
		productService.edit(producto);
		return "redirect:/admin/listProductos";
	}

	// Borrar
	@GetMapping("admin/borrarProd/{id}")
	public String borrar(@PathVariable("id") long id) {
		productService.delete(id);
		return "redirect:/admin/listProductos";
	}

}
