package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Prestamo;
import utils.MySQLDBConexion;

public class ModeloPrestamo {
	
	
	public List<Prestamo> listar() {
		Prestamo pres = null; // nombre a la variable pres
		List<Prestamo> data = new ArrayList<Prestamo>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MySQLDBConexion.getConexion();
			String sql = "SELECT p.cod_prestamo, p.cod_libro, a.titulo, p.nom_usuario, p.ape_usuario, p.fecha_prestamo, p.fecha_devolucion, p.estado "
					+ "FROM prestamo_libro p " +
                    "JOIN almacen_libro a ON p.cod_libro = a.cod_libro";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				pres = new Prestamo(); // Aqui se explica mejor
				pres.setCod_prestamo(rs.getInt(1));
				pres.setCod_libro(rs.getInt(2));
				pres.setLibro(rs.getString(3));
				pres.setNombre(rs.getString(4));
				pres.setApellido(rs.getString(5));
				pres.setFecha_prestamo(rs.getDate(6).toLocalDate()); //Esto es necesario para que pueda identificar y buscar la fecha (Date)
				pres.setFecha_devolucion(rs.getDate(7).toLocalDate());
				pres.setEstado(rs.getString(8));
				data.add(pres);
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
	
	
	public Prestamo buscarPrestamo(int cod) {
		Prestamo pres = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MySQLDBConexion.getConexion();
			String sql = "SELECT p.cod_prestamo, p.cod_libro, a.titulo, p.nom_usuario, p.ape_usuario, p.fecha_prestamo, p.fecha_devolucion, p.estado "
					+ "FROM prestamo_libro p " +
                    "JOIN almacen_libro a ON p.cod_libro = a.cod_libro"
                    + " WHERE p.cod_prestamo = ?"; //ESTE JOIN WHERE ES PARA BUSQUEDA
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			rs = pstm.executeQuery();
			if (rs.next()) {
				pres = new Prestamo();
				pres.setCod_prestamo(rs.getInt(1));
				pres.setCod_libro(rs.getInt(2));
				pres.setLibro(rs.getString(3));
				pres.setNombre(rs.getString(4));
				pres.setApellido(rs.getString(5));
				pres.setFecha_prestamo(rs.getDate(6).toLocalDate()); //Esto es necesario para que pueda identificar y buscar cualquier dato con fecha (Date)
				pres.setFecha_devolucion(rs.getDate(7).toLocalDate());
				pres.setEstado(rs.getString(8));
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
		return pres;
	}
	
	public int registrarPrestamo(Prestamo obj) {
		int estado = -1;
		Connection cn = null;
		PreparedStatement pstmInsert = null;
	    PreparedStatement pstmUpdate = null;
		
		try {
			cn = MySQLDBConexion.getConexion();
			cn.setAutoCommit(false);
			
			String sqlInsert= "insert into prestamo_libro (cod_libro, nom_usuario, ape_usuario, fecha_prestamo, fecha_devolucion, estado) values(?, ?, ?, ?, ?, ?)";
			pstmInsert = cn.prepareStatement(sqlInsert);
			pstmInsert.setInt(1, obj.getCod_libro());
			pstmInsert.setString(2, obj.getNombre());
			pstmInsert.setString(3, obj.getApellido());
			pstmInsert.setDate(4, java.sql.Date.valueOf(obj.getFecha_prestamo())); //Esto es necesario para que pueda identificar y buscar cualquier dato con fecha (Date)
			pstmInsert.setDate(5, java.sql.Date.valueOf(obj.getFecha_devolucion()));
			pstmInsert.setString(6, obj.getEstado());
			estado = pstmInsert.executeUpdate();
			
			String sqlUpdate = "UPDATE almacen_libro SET stock = stock - 1 WHERE cod_libro = ? AND stock > 0";
	        pstmUpdate = cn.prepareStatement(sqlUpdate);
	        pstmUpdate.setInt(1, obj.getCod_libro());
	        estado = pstmUpdate.executeUpdate();

	        cn.commit();
		}
	    catch (Exception e) {
	        try { if (cn != null) cn.rollback(); 
	        } catch (Exception e1) { e1.printStackTrace(); }
	        e.printStackTrace();
	    }
	    finally {
	        try {
	            if (pstmInsert != null) pstmInsert.close();
	            if (pstmUpdate != null) pstmUpdate.close();
	            if (cn != null) cn.close();
	        } catch (Exception e2) { e2.printStackTrace(); }
	    }
	    return estado;
	}
	
	
	public int actualizarPrestamo(Prestamo obj) {
		int estado = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
	    PreparedStatement pstmStock = null;
	    
	    try {
	        cn = MySQLDBConexion.getConexion();
	        cn.setAutoCommit(false); // Iniciamos transacción

	        
	        String sqlUpdatePres = "update prestamo_libro set cod_libro=?, nom_usuario=?, ape_usuario=?, "
	                + "fecha_prestamo=?, fecha_devolucion=?, estado=? where cod_prestamo=?";
	        pstm = cn.prepareStatement(sqlUpdatePres);
	        pstm.setInt(1, obj.getCod_libro());
	        pstm.setString(2, obj.getNombre());
	        pstm.setString(3, obj.getApellido());
	        pstm.setDate(4, java.sql.Date.valueOf(obj.getFecha_prestamo()));
	        pstm.setDate(5, java.sql.Date.valueOf(obj.getFecha_devolucion()));
	        pstm.setString(6, obj.getEstado());
	        pstm.setInt(7, obj.getCod_prestamo());
	        estado = pstm.executeUpdate();

	        // El de aqui es para que si el estado se marca como "Devuelto" que aumente el stock en el almacen
	        if (obj.getEstado().equalsIgnoreCase("Devuelto")) {
	            String sqlUpdateStock = "UPDATE almacen_libro SET stock = stock + 1 WHERE cod_libro = ?";
	            pstmStock = cn.prepareStatement(sqlUpdateStock);
	            pstmStock.setInt(1, obj.getCod_libro());
	            pstmStock.executeUpdate();
	        }
	        else if (obj.getEstado().equalsIgnoreCase("Activo")) {
	            // Si el combo vuelve a decir "Activo", restamos 1 del almacén
	            String sqlResta = "UPDATE almacen_libro SET stock = stock - 1 WHERE cod_libro = ? AND stock > 0";
	            pstmStock = cn.prepareStatement(sqlResta);
	            pstmStock.setInt(1, obj.getCod_libro());
	            pstmStock.executeUpdate();
	            }

	        cn.commit(); // Guardar los cambios de todo
	    }
	    catch (Exception e) {
	        try { if (cn != null) cn.rollback(); } catch (Exception e1) { }
	        e.printStackTrace();
	    }
	    finally {
	        try {
	            if (pstm != null) pstm.close();
	            if (pstmStock != null) pstmStock.close();
	            if (cn != null) cn.close();
	        } catch (Exception e2) { }
	    }
	    return estado;
	}
	
	public int eliminarPrestamo(int cod) {
		int estado = -1;
		Connection cn = null;
		PreparedStatement pstmDelete = null; //Cambie esto para cada uno
	    PreparedStatement pstmUpdate = null;
	    
	    try {
	        cn = MySQLDBConexion.getConexion();
	        cn.setAutoCommit(false);

	        
	        Prestamo p = buscarPrestamo(cod); 
	        
	        if (p != null) {
	            
	            String sqlDelete = "delete from prestamo_libro where cod_prestamo=?";
	            pstmDelete = cn.prepareStatement(sqlDelete);
	            pstmDelete.setInt(1, cod);
	            pstmDelete.executeUpdate();

	            // Esta hecho para actualizar el stock en almacen_libro
	            String sqlUpdate = "UPDATE almacen_libro SET stock = stock + 1 WHERE cod_libro = ?";
	            pstmUpdate = cn.prepareStatement(sqlUpdate);
	            pstmUpdate.setInt(1, p.getCod_libro());
	            estado = pstmUpdate.executeUpdate();
	            
	            cn.commit();
	        }
	    }
	    catch (Exception e) {
	        try { if (cn != null) cn.rollback(); } catch (Exception e1) { e1.printStackTrace(); }
	        e.printStackTrace();
	    }
	    finally {
	        try {
	            if (pstmDelete != null) pstmDelete.close();
	            if (pstmUpdate != null) pstmUpdate.close();
	            if (cn != null) cn.close();
	        } catch (Exception e2) { e2.printStackTrace(); }
	    }
	    return estado;
	}
	
	//
	public List<Prestamo> buscarPrestamoxcod(int cod) {
		Prestamo pres = null;
		List<Prestamo> data = new ArrayList<Prestamo>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MySQLDBConexion.getConexion();
			String sql = "SELECT p.cod_prestamo, p.cod_libro, a.titulo, p.nom_usuario, p.ape_usuario, p.fecha_prestamo, p.fecha_devolucion, p.estado "
					+ "FROM prestamo_libro p " +
                    "JOIN almacen_libro a ON p.cod_libro = a.cod_libro" 
                    + " WHERE p.cod_prestamo = ?"; //ESTE JOIN WHERE ES PARA BUSQUEDA
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			rs = pstm.executeQuery();
			if (rs.next()) {
				pres = new Prestamo();
				pres.setCod_prestamo(rs.getInt(1));
				pres.setCod_libro(rs.getInt(2));
				pres.setLibro(rs.getString(3));
				pres.setNombre(rs.getString(4));
				pres.setApellido(rs.getString(5));
				pres.setFecha_prestamo(rs.getDate(6).toLocalDate()); //Esto es necesario para que pueda identificar y buscar cualquier dato con fecha (Date)
				pres.setFecha_devolucion(rs.getDate(7).toLocalDate());
				pres.setEstado(rs.getString(8));
				data.add(pres);
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
	
	//BUSCAR NOMBRE
	public String obtenerNombreLibro(int cod) {
		String titulo = "";
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = MySQLDBConexion.getConexion();
			String sql = "SELECT titulo FROM almacen_libro WHERE cod_libro = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			rs = pstm.executeQuery();
			if (rs.next()) {
				titulo = rs.getString(1);
			}
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
		
		finally { 
			try {
				if (rs != null) rs.close();
				if (pstm != null) pstm.close();
				if (cn != null) cn.close();
			}
			catch (Exception e2) { e2.printStackTrace(); }
		}
	    return titulo;
	}
	
}
