package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLDBConexion {

	public static Connection getConexion() {
		Connection cn = null;
		try {
			// Class.forName para MySQL 8.0
			// Class.forName("com.mysql.cj.jdbc.Driver");
			// Class.forName para MySQL 6.2
			Class.forName("com.mysql.jdbc.Driver");
			// DriverManager.getConnection("jdbc:mysql://192.168.1.x/latam", "root", "mysql"); Si tienes la IP del Servidor
			cn = DriverManager.getConnection("jdbc:mysql://localhost/librosAlmacen", "root", "mysql");
			// Aquí simplemente cambié el "latam" por el nuevo base de datos llamado "librosAlmacen"
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
		return cn;
	}	
}
