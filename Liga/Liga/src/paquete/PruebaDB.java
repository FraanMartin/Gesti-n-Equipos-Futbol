package paquete;

import java.sql.*;

public class PruebaDB {

	public static void main(String[] args) {
		Connection conexion;
		try {
	        Class.forName("com.mysql.jdbc.Driver"); //registra el driver de conexión para la base de datos. Cada base de datos usa uno distinto, en este caso es mysql
	        conexion = DriverManager.getConnection("jdbc:mysql://192.168.1.42:3306/", "daw", "daw"); // establecemos una conexión con el SGBD, usuario root
	        System.out.println("Se ha iniciado la conexión con el servidor de forma exitosa");
	        
	        conexion.close();
	        System.out.println("Se ha finalizado la conexión con el servidor");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
