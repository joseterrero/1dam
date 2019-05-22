package com.salesianostriana.dam.tiendamovil.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.tiendamovil.formbean.SearchBean;
import com.salesianostriana.dam.tiendamovil.modelo.Producto;
import com.salesianostriana.dam.tiendamovil.repository.ProductoRepository;
import com.salesianostriana.dam.tiendamovil.service.ProductoService;

@Controller
public class ProductoController {

	@Autowired
	private ProductoRepository productRepo;
	@Autowired
	private ProductoService productService;
	@Autowired
	private HttpSession session;

//	private static final int BUTTONS_TO_SHOW = 5;
//	private static final int INITIAL_PAGE = 0;
//	private static final int INITIAL_PAGE_SIZE = 5;
//	private static final int[] PAGE_SIZES = { 5, 10, 20, 50 };

	@GetMapping("/admin/addProducto")
	public String mostrarFormRegistroProductoAdmin(Model model) {
		model.addAttribute("formProductoAdmin", new Producto());
		return "admin/anadirProductoAdmin";
	}

	@PostMapping("/admin/newProductoAdmin")
	public String nuevoProductoAdmin(@ModelAttribute("formProductoAdmin") Producto producto,
			BindingResult bindingResult, Model model) {
		productRepo.save(producto);
		return "redirect:/admin/listProductos";
	}

	// Listar
	@GetMapping("/admin/listProductos")
	public String listarTodos(Model model) {
		model.addAttribute("inputBuscar", new SearchBean());
		model.addAttribute("listaProd", productService.findAll());
		return "admin/productosAdmin";

	}

	// Editar
	@GetMapping("/admin/editarProd/{id}")
	public String mostrarFormularioEdit(@PathVariable("id") long id, Model model) {

		Producto aEditar = productService.findById(id);

		if (aEditar != null) {
			model.addAttribute("listaProd", aEditar);
			return "admin/editProductoAdmin";
		} else {
			return "redirect:/admin/listProductos";
		}
	}

	@PostMapping("/admin/editarProd/submit")
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
	
	// Buscar
	@PostMapping("/admin/buscarProducto")
	public String buscarProducto(@ModelAttribute("inputBuscar") SearchBean s, Model model) {
		model.addAttribute("listaProd", productService.findByModelo(s.getSearch()));
		return "admin/productosAdmin";
	}

	// vista producto
	@GetMapping("/list")
	public String productList(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, Model model) {

//			// Evalúa el tamaño de página. Si el parámetro es "nulo", devuelve
//			// el tamaño de página inicial.
//			int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		//
//			// Calcula qué página se va a mostrar. Si el parámetro es "nulo" o menor
//			// que 0, se devuelve el valor inicial. De otro modo, se devuelve el valor
//			// del parámetro decrementado en 1.
//			int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		//
//			// Obtenemos la página definida por evalPage y evalPageSize de objetos de
//			// nuestro modelo
//			Page<Producto> productos = prodService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
//			// Creamos el objeto Pager (paginador) indicando los valores correspondientes.
//			// Este sirve para que la plantilla sepa cuantas páginas hay en total, cuantos
//			// botones
//			// debe mostrar y cuál es el número de objetos a dibujar.
//			Pager pager = new Pager(persons.getTotalPages(), persons.getNumber(), BUTTONS_TO_SHOW);
//
//			model.addAttribute("productos", productos);
//			model.addAttribute("selectedPageSize", evalPageSize);
//			model.addAttribute("pageSizes", PAGE_SIZES);
//			model.addAttribute("pager", pager);

		model.addAttribute("productos", productService.findAllProducts());

		/*
		 * La siguiente línea viene del último método, que se dedica a buscar, para que
		 * este método, muestre también el listado de productos cuando se han buscado,
		 * añadimos al model el objeto tipo bean de búsqueda cuando se está buscando
		 * algún producto
		 */
		model.addAttribute("searchForm", new SearchBean());
		return "productos";
	}

	/* Método para buscar productos */
	@PostMapping("/search")
	public String searchProducto(@ModelAttribute("inputBuscar") SearchBean searchBean, Model model) {
		model.addAttribute("listaProd", productService.findByModelo(searchBean.getSearch()));
		return "productos";
	}

//	// BUSCAR
//	@GetMapping("/list")
//	public String productList(Model model) {
//
//		model.addAttribute("productos", productService.findAllProducts());
//
//		/*
//		 * La siguiente línea viene del último método, que se dedica a buscar, para que
//		 * este método, muestre también el listado de productos cuando se han buscado,
//		 * añadimos al model el objeto tipo bean de búsqueda cuando se está buscando
//		 * algún producto
//		 */
//		model.addAttribute("searchForm", new SearchBean());
//		return "productos";
//	}

}
