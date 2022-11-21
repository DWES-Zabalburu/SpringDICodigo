package org.zabalburu.springdi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zabalburu.springdi.config.Config;
import org.zabalburu.springdi.dao.ProductoDAO;
import org.zabalburu.springdi.dao.ProductoList;
import org.zabalburu.springdi.modelo.Producto;
import org.zabalburu.springdi.servicio.ProductoServicio;

public class ProductoApp {

	public static void main(String[] args) {
		//ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(Config.class);
		ctx.refresh();
		ProductoServicio servicio = (ProductoServicio) ctx.getBean("servicio");
		for(Producto p : servicio.getProductos()) {
			System.out.println(p);
		}
		ctx.close();
	}

}
