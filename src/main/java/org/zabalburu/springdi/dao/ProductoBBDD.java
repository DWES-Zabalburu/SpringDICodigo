package org.zabalburu.springdi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zabalburu.springdi.modelo.Categoria;
import org.zabalburu.springdi.modelo.Producto;
import org.zabalburu.springdi.util.Conexion;

@Repository("productoBBDD")
public class ProductoBBDD implements ProductoDAO {

	@Autowired
	private Conexion conn;
	
	public ProductoBBDD(Conexion conn) {
		this.conn = conn;
	}
	
	@Override
	public List<Producto> getProductos() {
		Statement stmt;
		ResultSet rst;
		List<Producto> productos = new ArrayList<>();
		try {
			stmt = conn.getConexion().createStatement();
			rst = stmt.executeQuery("Select * From Productos");
			while (rst.next()) {
				productos.add(crearProducto(rst));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productos;
	}

	private Producto crearProducto(ResultSet rst) {
		Producto p = new Producto();
		try {
			p.setId(rst.getInt("id"));
			p.setNombre(rst.getString("nombre"));
			p.setPrecio(rst.getDouble("precio"));
			p.setCategoria(this.getCategoria(rst.getInt("idCategoria")));
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return p;
	}

	@Override
	public Producto nuevoProducto(Producto p) {
		try {
			PreparedStatement pst = conn.getConexion().prepareStatement(
					"Insert into Productos(nombre,precio,idCategoria) values(?,?,?)");
			pst.setString(1, p.getNombre());
			pst.setDouble(2, p.getPrecio());
			pst.setInt(3, p.getCategoria().getId());
			pst.executeUpdate();
			ResultSet rst = conn.getConexion().createStatement()
					.executeQuery("Select max(id) maxId From Productos");
			rst.next();
			p.setId(rst.getInt("maxId"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public Producto getProducto(Integer id) {
		PreparedStatement stmt;
		ResultSet rst;
		Producto producto = null;
		try {
			stmt = conn.getConexion().prepareStatement("Select * From Productos Where id=?");
			stmt.setInt(1, id);
			rst = stmt.executeQuery();
			if (rst.next()) {
				producto = crearProducto(rst);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return producto;
	}

	@Override
	public void eliminarProducto(Integer id) {
		try {
			PreparedStatement pst = conn.getConexion().prepareStatement(
					"Delete From Productos where id=?");
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Override
	public List<Categoria> getCategorias() {
		Statement stmt;
		ResultSet rst;
		List<Categoria> categorias = new ArrayList<>();
		try {
			stmt = conn.getConexion().createStatement();
			rst = stmt.executeQuery("Select * From Categorias");
			while (rst.next()) {
				categorias.add(crearCategoria(rst));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categorias;
	}

	private Categoria crearCategoria(ResultSet rst) {
		Categoria c = new Categoria();
		try {
			c.setId(rst.getInt("id"));
			c.setNombre(rst.getString("nombre"));
		} catch (SQLException ex) {}
		return c;
	}

	@Override
	public Categoria getCategoria(Integer id) {
		PreparedStatement stmt;
		ResultSet rst;
		Categoria categoria = null;
		try {
			stmt = conn.getConexion().prepareStatement("Select * From Categorias Where id=?");
			stmt.setInt(1, id);
			rst = stmt.executeQuery();
			if (rst.next()) {
				categoria = crearCategoria(rst);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoria;
	}

}
