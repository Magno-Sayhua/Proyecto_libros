package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Almacen;
import utils.MySQLDBConexion;

public class ModeloAlmacen {

	
	
	public List<Almacen> listar() {
		Almacen alm = null;
		List<Almacen> data = new ArrayList<Almacen>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MySQLDBConexion.getConexion();
			String sql = "select * from almacen_libro";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				alm = new Almacen();
				alm.setCod_almacen(rs.getInt(1));
				alm.setTitulo(rs.getString(2));
				alm.setAutor(rs.getString(3));
				alm.setFecha_ingreso(rs.getDate(4).toLocalDate()); //Esto es necesario para que pueda identificar y buscar la fecha (Date)
				alm.setStock(rs.getInt(5));
				alm.setCategoria(rs.getString(6));
				data.add(alm);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return data;
	}
	
	//BUSCAR 
	public Almacen buscarAlmacen(int cod) {
		Almacen alm = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MySQLDBConexion.getConexion();
			String sql = "select * from almacen_libro where cod_libro=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			rs = pstm.executeQuery();
			if (rs.next()) {
				alm = new Almacen();
				alm.setCod_almacen(rs.getInt(1));
				alm.setTitulo(rs.getString(2));
				alm.setAutor(rs.getString(3));
				alm.setFecha_ingreso(rs.getDate(4).toLocalDate()); //Esto es necesario para que pueda identificar y buscar la fecha (Date)
				alm.setStock(rs.getInt(5));
				alm.setCategoria(rs.getString(6));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return alm;
	}
	
	//REGISTRAR
	public int registrarAlmacen(Almacen obj) {
		int estado = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MySQLDBConexion.getConexion();
			String sql = "insert into almacen_libro (titulo, autor, fecha_ingreso, stock, categoria) values(?, ?, ?, ?, ?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, obj.getTitulo());
			pstm.setString(2, obj.getAutor());
			pstm.setDate(3, java.sql.Date.valueOf(obj.getFecha_ingreso())); //Esto es necesario para que pueda identificar y buscar cualquier dato con fecha (Date)
			pstm.setInt(4, obj.getStock());
			pstm.setString(5, obj.getCategoria());
			estado = pstm.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return estado;
	}
	
	
	//ACTUALIZAR
	public int actualizarAlmacen(Almacen obj) {
		int estado = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MySQLDBConexion.getConexion();
			String sql = "update almacen_libro set titulo=?, autor=?, fecha_ingreso=?, stock=?, categoria=? where cod_libro=?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, obj.getTitulo());
			pstm.setString(2, obj.getAutor());
			pstm.setDate(3, java.sql.Date.valueOf(obj.getFecha_ingreso())); //Esto es necesario para que pueda identificar y buscar cualquier dato con fecha (Date)
			pstm.setInt(4, obj.getStock());
			pstm.setString(5, obj.getCategoria());
			pstm.setInt(6, obj.getCod_almacen());
			estado = pstm.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return estado;
	}
	
	//ELIMINAR
	public int eliminarAlmacen(int cod) {
		int estado = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MySQLDBConexion.getConexion();
			String sql = "delete from almacen_libro where cod_libro=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			estado = pstm.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return estado;
	}
	
	
	//LISTAR
	public List<Almacen> buscarAlmacenxcod(int cod) {
		Almacen alm = null;
		List<Almacen> data = new ArrayList<Almacen>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MySQLDBConexion.getConexion();
			String sql = "select * from almacen_libro where cod_libro=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			rs = pstm.executeQuery();
			if (rs.next()) {
				alm = new Almacen();
				alm.setCod_almacen(rs.getInt(1));
				alm.setTitulo(rs.getString(2));
				alm.setAutor(rs.getString(3));
				alm.setFecha_ingreso(rs.getDate(4).toLocalDate()); //Esto es necesario para que pueda identificar y buscar cualquier dato con fecha (Date)
				alm.setStock(rs.getInt(5));
				alm.setCategoria(rs.getString(6));
				data.add(alm);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return data;
	}
	
}