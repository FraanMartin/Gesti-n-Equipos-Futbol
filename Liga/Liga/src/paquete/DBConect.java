package paquete;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConect {
	Connection conexion = null;
	
	public DBConect(String db, String user, String pass) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conexion = DriverManager.getConnection("jdbc:mysql://192.168.24.156:3306/" + db, user, pass);
			System.out.println("\nSe ha iniciado la conexión con el servidor");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}
		
	}
	public Connection getConexion() {
		return conexion;
	}
	public Statement getStatement() {
		Statement st = null;
		try {
				st = conexion.createStatement();
			} catch (SQLException e) {
		
				e.printStackTrace();
			}
		return st;
	}
	public void cerrar() {
		try {
			conexion.close();
			System.out.println("Se ha finalizado la conexión con el servidor");
			} catch (SQLException e) {
			
			System.out.println("Error cerrando la base de datos.");
		}
		
	}
	public static void main(String[] args) {
		DBConect d = new DBConect("", "daw", "daw");
	}
}
