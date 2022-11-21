package org.zabalburu.springdi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Conexion {
	@Value("com.microsoft.sqlserver.jdbc.SQLServerDriver") 
	private String clase;
	@Value("jdbc:sqlserver://localhost:2000;databaseName=Northwind;TrustServerCertificate=True;")
	private String url;
	@Value("sa")
	private String usuario;
	@Value("tiger")
	private String password;
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Conexion() {
		
	}
	
	public Conexion(String clase) {
		super();
		this.clase = clase;
	}
	
	public Connection getConexion() {
		Connection cnn = null;
		
		try {
			Class.forName(this.clase);
			cnn = DriverManager.getConnection(this.url, this.usuario,this.password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnn;
	}
	
}
