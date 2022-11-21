package org.zabalburu.springdi;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zabalburu.springdi.dao.ProductoDAO;
import org.zabalburu.springdi.dao.ProductoList;
import org.zabalburu.springdi.modelo.Producto;
import org.zabalburu.springdi.servicio.ProductoServicio;

public class ProductoApp {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		/*Producto p1 = (Producto) ctx.getBean("p1");
		Producto p2 = (Producto) ctx.getBean("p2");
		Producto p3 = (Producto) ctx.getBean("p3");
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		p3.setNombre("Cambiado!");
		Producto p4 = (Producto) ctx.getBean("p3");
		System.out.println(p4);*/
		/*ProductoServicio servicio = (ProductoServicio) ctx.getBean(ProductoServicio.class);
		for(Producto p : servicio.getProductos()) {
			System.out.println(p);
		}*/
		ProductoServicio servicio = (ProductoServicio) ctx.getBean(ProductoServicio.class);
		for(Producto p : servicio.getProductos()) {
			System.out.println(p);
		}
		ctx.close();
	}

}
