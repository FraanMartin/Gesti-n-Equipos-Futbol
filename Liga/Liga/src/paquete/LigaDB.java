package paquete;

import java.sql.*;

public class LigaDB {
	public static void main(String[] args) {
		Connection conexion;
		try {
	        Class.forName("com.mysql.jdbc.Driver"); //registra el driver de conexión para la base de datos. Cada base de datos usa uno distinto, en este caso es mysql
	        
	        conexion = DriverManager.getConnection("jdbc:mysql://192.168.1.42:3306/", "daw", "daw"); // establecemos una conexión con el SGBD, usuario root
	        System.out.println("Se ha iniciado la conexión con el servidor de forma exitosa");

	        Statement st = conexion.createStatement(); // Crea un objeto que se usa para ejecutar sentencias SQL. Lleva asociada una conexión que sirvió como origen para su creación.
	        String Query; // Cadena donde vamos a almacenar las consultas
	        
	        // CREAMOS UNA BASE DE DATOS

	       Query = "CREATE DATABASE IF NOT EXISTS Liga;";
	       st.executeUpdate(Query);
	       System.out.println("DB creada con éxito");
	       
	       // INDICAMOS AL SGBD QUE USE LA BASE DE DATOS liga

	       Query = "USE Liga;";
	       st.executeUpdate(Query);
	        
	        // CREAMOS LA TABLA EQUIPOS
	       Query = "CREATE TABLE EQUIPOS("
	       		+ "		id_equipo VARCHAR(50),"
	       		+ "		puntos INT(3),"
	       		+ "		PRIMARY KEY (id_equipo)"
	       		+ "	)ENGINE=InnoDB;";
	       
	       st.executeUpdate(Query);
	       System.out.println("TABLA EQUIPOS creada con éxito");
	        
	       // CREAMOS LA TABLA JUGADORES
	       Query = "CREATE TABLE JUGADORES ("
	        		+ "		id_jugador VARCHAR(100) NOT NULL,"
	        		+ "		id_equipo VARCHAR(50) NOT NULL,"
	        		+ "		edad INT(2) NOT NULL,"
	        		+ "		pais VARCHAR(30) NOT NULL,"
	        		+ "		posicion VARCHAR(3) NOT NULL,"
	        		+ "		PRIMARY KEY (id_jugador),"
	        		+ "		FOREIGN KEY (id_equipo) REFERENCES EQUIPOS(id_equipo)"
	        		+ "	)"
	        		+ "	ENGINE=InnoDB;";
	       
	        st.executeUpdate(Query);
		     System.out.println("TABLA JUGADORES creada con éxito");

      
	        // AÑADIMOS INFORMACIÓN EN LAS TABLAS
		       Query = "  INSERT INTO EQUIPOS (id_equipo,puntos) VALUES"
		       		+ " ('Almeria','36'),('Sevilla','44');";
		       
		       st.executeUpdate(Query);
			       
			   System.out.println("TABLA EQUIPOS actualizada con éxito");
				      
			   Query = "  INSERT INTO JUGADORES (id_jugador,id_equipo,edad,pais,posicion) VALUES"
			   		+ "('Samu Costas','Almeria','23','Portugal','MCD'),('Alejandro Pozo','Almeria','24','España','LD');";
			       
			       st.executeUpdate(Query);
				       
				   System.out.println("TABLA JUGADORES actualizada con éxito");
				      
	        // AÑADIMOS UN USUARIO DE CONSULTA
			
				   Query = "GRANT SELECT ON facturacion.* TO 'manu'@'%' IDENTIFIED BY '1234';";
			       st.executeUpdate(Query);
				   
	        conexion.close();
	        System.out.println("Se ha finalizado la conexión con el servidor");
	    } catch (Exception ex) {  
	    	System.out.println("Error en la conexión con el servidor MySQL"); 
	      }
	}
}
