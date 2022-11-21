package org.zabalburu.springdi.dao;

import java.util.ArrayList;
import java.util.List;

import org.zabalburu.springdi.modelo.Categoria;
import org.zabalburu.springdi.modelo.Producto;

public class ProductoList implements ProductoDAO {

	private List<Producto> productos = new ArrayList<Producto>();
	private List<Categoria> categorias = new ArrayList<Categoria>();
	
	public ProductoList() {
		System.out.println("En el constructor de ProductoList");
		categorias.add(new Categoria(1,"Categoría 1"));
		categorias.add(new Categoria(2,"Categoría 2"));
		categorias.add(new Categoria(3,"Categoría 3"));
		productos.add(new Producto(1, "ProductoList 1", 120.3,categorias.get(0)));
		productos.add(new Producto(2, "ProductoList 2", 30.5,categorias.get(1)));
		productos.add(new Producto(3, "ProductoList 3", 12.9,categorias.get(1)));
		productos.add(new Producto(4, "ProductoList 4", 75.0,categorias.get(0)));
		productos.add(new Producto(5, "ProductoList 5", 25.0,categorias.get(2)));
		productos.add(new Producto(6, "ProductoList 6", 60.7,categorias.get(2)));
		productos.add(new Producto(7, "ProductoList 7", 87.5,categorias.get(0)));
	}
	
	public List<Producto> getProductos() {
		return productos;
	}

	public Producto nuevoProducto(Producto p) {
		Integer id = 1;
		if (!productos.isEmpty()) {
			id = productos.get(productos.size()-1).getId()+1;
		}
		p.setId(id);
		productos.add(p);
		return p;
	}

	public Producto getProducto( Integer id) {
		return productos.stream()
				.filter(p -> p.getId().equals(id))
				.findAny()
				.orElse(null);
	}

	public void eliminarProducto(Integer id) {
		productos.remove(getProducto(id));
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public Categoria getCategoria(Integer id) {
		return categorias.stream()
				.filter(c -> c.getId().equals(id))
				.findAny()
				.orElse(null);
	}

}
