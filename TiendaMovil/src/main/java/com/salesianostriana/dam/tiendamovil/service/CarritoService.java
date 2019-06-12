package com.salesianostriana.dam.tiendamovil.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.tiendamovil.modelo.LineaPedido;
import com.salesianostriana.dam.tiendamovil.modelo.Producto;
import com.salesianostriana.dam.tiendamovil.repository.LineaPedidoRepository;

@Service
public class CarritoService {

	private LineaPedidoRepository linPedRepo;
	@Autowired
	private ProductoService productService;

	private List<LineaPedido> lineasPedido = new ArrayList<>();

	@Autowired
	LineaPedidoService linPedService;

	public CarritoService(LineaPedidoRepository linPedRepo) {
		this.linPedRepo = linPedRepo;
	}

	// Agregar lineas de pedido
	public void addLineaPedido(Producto p) {

		Boolean agregarAldv = false;

		for (LineaPedido linPed : lineasPedido) {
			if (linPed.getProducto().getId() == p.getId()) {
				if (productService.existenciaProductos(linPed.getProducto(), linPed.getCantidad() + 1)) {
					linPed.setCantidad(linPed.getCantidad() + 1);
					linPed.setPrecioFinal(p.getPrecio() * linPed.getCantidad());
					agregarAldv = true;
				} else {
					linPed.setCantidad(linPed.getProducto().getCantidad());
					agregarAldv = true;
				}
			}
		}
		if (agregarAldv == false) {
			if (p.getCantidad() - 1 >= 0) {
				lineasPedido.add(new LineaPedido(p, 1));
			}
		}
	}

	// Borrar linea de pedido
	public void borrarLineaPedido(LineaPedido lineaPedido) {
		lineasPedido.remove(lineaPedido);
	}

	// Calcular precio final
	public double calculoPrecioFinal() {
		double precioTotal = 0;
		for (LineaPedido lineaPed : lineasPedido) {
			precioTotal += lineaPed.getPrecioFinal();
		}
		return precioTotal;
	}

	// Limpiar carrito
	public void limpiarCarrito() {
		lineasPedido.clear();
	}

	// Copia del carrito
	public List<LineaPedido> getProductsInCart() {
		return Collections.unmodifiableList(lineasPedido);
	}

}
