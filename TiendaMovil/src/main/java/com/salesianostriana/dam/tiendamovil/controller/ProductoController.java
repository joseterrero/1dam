package com.salesianostriana.dam.tiendamovil.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.salesianostriana.dam.tiendamovil.formbean.SearchBean;
import com.salesianostriana.dam.tiendamovil.formbean.UploadFormBean;
import com.salesianostriana.dam.tiendamovil.modelo.Pager;
import com.salesianostriana.dam.tiendamovil.modelo.Producto;
import com.salesianostriana.dam.tiendamovil.modelo.Usuario;
import com.salesianostriana.dam.tiendamovil.repository.ProductoRepository;
import com.salesianostriana.dam.tiendamovil.service.ProductoService;
import com.salesianostriana.dam.tiendamovil.service.UsuarioService;

@Controller
public class ProductoController {

	@Autowired
	private ProductoRepository productRepo;
	@Autowired
	private ProductoService productService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private HttpSession session;

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 4;
	private static final int[] PAGE_SIZES = { 4, 10, 20, 40, 50 };

	// Añadir
	@GetMapping("/admin/addProducto")
	public String mostrarFormRegistroProductoAdmin(Model model) {
		model.addAttribute("formProductoAdmin", new Producto());
		return "admin/anadirProductoAdmin";
	}

	@PostMapping("/admin/newProductoAdmin")
	public String nuevoProductoAdmin(@ModelAttribute("formProductoAdmin") UploadFormBean uploadBean,
			@RequestParam("file") MultipartFile file, Model model) {
		/*
		 * Creamos un objeto PojoConFichero, pero debemos tener en cuenta, que en este
		 * caso no es como en anteriores donde era el objeto modal completo recogido en
		 * el formulario, sino que le falta la parte del archivo
		 */

		Producto p = new Producto();
		// Seteamos a ese p el nombre recogido del campo de la propiedad cualquiera,
		// en este caso solo un text.
		p.setModelo(uploadBean.getModelo());
		p.setColor(uploadBean.getColor());
		p.setCapacidad(uploadBean.getCapacidad());
		p.setRam(uploadBean.getRam());
		p.setFecha(uploadBean.getFecha());
		p.setPrecio(uploadBean.getPrecio());
		p.setCantidad(uploadBean.getCantidad());
		// Usamos el servicio de subida para guardar en la BD el objeto POJO por un lado
		// y lo recogido del archivo por otro. Eso es lo que se le da a add y este a su
		// vez,
		// si se mira en el UploadConPojoService usa los dos parámteros para guardar
		// "todo el pojo completo, propiedad y archivo).
		productService.add(p, file);
		return "redirect:/admin/listProductos";
	}

	// Listar
	@GetMapping("/admin/listProductos")
	public String listarProductos(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = (Usuario) usuarioService.findOneByUsername(user.getUsername());
		session.setAttribute("usuarioActual", u);
		model.addAttribute("usuario", u);

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
		model.addAttribute("usuario", session.getAttribute("usuarioActual"));
		model.addAttribute("listaProd", productService.findByModelo(s.getSearch()));
		return "admin/productosAdmin";
	}

	// vista producto
	@GetMapping("/list")
	public String showProductList(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @RequestParam("modelo") Optional<String> modelo,
			Model model) {

		// Evalúa el tamaño de página. Si el parámetro es "nulo", devuelve
		// el tamaño de página inicial.
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);

		// Calcula qué página se va a mostrar. Si el parámetro es "nulo" o menor
		// que 0, se devuelve el valor inicial. De otro modo, se devuelve el valor
		// del parámetro decrementado en 1.
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		String evalModelo = modelo.orElse(null);

		Page<Producto> productos = null;

		if (evalModelo == null) {
			productos = productService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		} else {
			productos = productService.findByModeloContainingIgnoreCasePageable(evalModelo,
					PageRequest.of(evalPage, evalPageSize));
		}
		// Creamos el objeto Pager (paginador) indicando los valores correspondientes.
		// Este sirve para que la plantilla sepa cuantas páginas hay en total, cuantos
		// botones
		// debe mostrar y cuál es el número de objetos a dibujar.
		Pager pager = new Pager(productos.getTotalPages(), productos.getNumber(), BUTTONS_TO_SHOW);

		model.addAttribute("usuario", session.getAttribute("usuarioActual"));
		model.addAttribute("productos", productos);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("pager", pager);

		/*
		 * La siguiente línea viene del último método, que se dedica a buscar, para que
		 * este método, muestre también el listado de productos cuando se han buscado,
		 * añadimos al model el objeto tipo bean de búsqueda cuando se está buscando
		 * algún producto
		 */
		model.addAttribute("inputBuscar", new SearchBean());
		return "productos";
	}

	/* Método para buscar productos */
	@PostMapping("/search")
	public String searchProducto(@ModelAttribute("inputBuscar") SearchBean searchBean, Model model) {
		model.addAttribute("usuario", session.getAttribute("usuarioActual"));
		model.addAttribute("productos", productService.findByModelo(searchBean.getSearch()));
		return "productos";
	}

}
