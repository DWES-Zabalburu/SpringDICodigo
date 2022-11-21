package org.zabalburu.springdi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zabalburu.springdi.dao.ProductoBBDD;
import org.zabalburu.springdi.dao.ProductoList;
import org.zabalburu.springdi.servicio.ProductoServicio;
import org.zabalburu.springdi.util.Conexion;

@Configuration
public class Config {
	@Bean
	public Conexion getConn() {
		Conexion c = new Conexion("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // Value
		c.setUrl("jdbc:sqlserver://localhost:2000;databaseName=Northwind;TrustServerCertificate=True;");
		c.setUsuario("sa");
		c.setPassword("tiger");
		return c;
	}
	
	@Bean
	public ProductoList getProductoList() {
		return new ProductoList();
	}
	
	@Bean
	public ProductoBBDD getProductoBBDD() {
		ProductoBBDD productoBBDD = new ProductoBBDD(getConn()); // Ref
	    return productoBBDD;
	}
	
	@Bean
	public ProductoServicio getServicio() {
		ProductoServicio servicio = new ProductoServicio();
		servicio.setDao(getProductoBBDD());
		return servicio;
	}
}
