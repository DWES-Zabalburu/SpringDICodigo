package org.zabalburu.springdi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.zabalburu.springdi.dao.ProductoBBDD;
import org.zabalburu.springdi.dao.ProductoList;
import org.zabalburu.springdi.servicio.ProductoServicio;
import org.zabalburu.springdi.util.Conexion;

@Configuration
@PropertySource({"org/zabalburu/springdi/props/conexion.properties"})
public class Config {
	
	@Value("${clase}") 
	private String clase;
	@Value("${url}")
	private String url;
	@Value("${usuario}")
	private String usuario;
	@Value("${password}")
	private String password;
	
	@Bean
	public Conexion getConn() {
		Conexion c = new Conexion(this.clase); // Value
		c.setUrl(this.url);
		c.setUsuario(this.usuario);
		c.setPassword(this.password);
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
