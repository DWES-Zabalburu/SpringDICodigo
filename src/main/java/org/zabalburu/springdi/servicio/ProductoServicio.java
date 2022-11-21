package org.zabalburu.springdi.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zabalburu.springdi.dao.ProductoDAO;
import org.zabalburu.springdi.modelo.Categoria;
import org.zabalburu.springdi.modelo.Producto;

public class ProductoServicio {
	
	private ProductoDAO dao;
	
	public ProductoServicio(ProductoDAO dao) {
		System.out.println("En el constructor de ProductoServicio");
		this.dao = dao;
	}

	public ProductoServicio() {
		super();
		System.out.println("En el constructor de ProductoServicio");
	}

	public ProductoDAO getDao() {
		return dao;
	}

	public void setDao(ProductoDAO dao) {
		this.dao = dao;
	}
	
	public List<Producto> getProductos(){
		return dao.getProductos();
	}
	
	public Producto nuevoProducto(Producto p) {
		return dao.nuevoProducto(p);
	}
	
	public Producto getProducto(Integer id) {
		return dao.getProducto(id);
	}
	
	public void eliminarProducto(Integer id) {
		dao.eliminarProducto(id);
	}
	
	public List<Categoria> getCategorias(){
		return dao.getCategorias();
	}
	
	public Categoria getCategoria(Integer id) {
		return dao.getCategoria(id);
	}
	
}
