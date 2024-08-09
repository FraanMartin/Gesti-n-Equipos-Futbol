package paquete;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSet;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Liga {
	Connection conexion;
	Statement st;
	String Query;
	private final String IP = "192.168.1.42:3306";
	private final String USER = "daw";
	private final String PASSWORD = "daw";
	Clip clip;
	private FloatControl volumeControl;


	
	
	public Liga() {
		Conexion();
	}
	
	public void Conexion() {
		try {
	        Class.forName("com.mysql.jdbc.Driver"); //registra el driver de conexión para la base de datos. Cada base de datos usa uno distinto, en este caso es mysql
	        
	        conexion = DriverManager.getConnection("jdbc:mysql://"+IP, USER, PASSWORD); // establecemos una conexión con el SGBD, usuario root
	        System.out.println("Se ha iniciado la conexión con el servidor de forma exitosa");

	        st = conexion.createStatement(); // Crea un objeto que se usa para ejecutar sentencias SQL. Lleva asociada una conexión que sirvió como origen para su creación.
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public Connection getConexion() {
		return conexion;
	}
	public void inicializarTablas() {
		try {
			
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void usarDB() {
		try {
			
		       
		       // INDICAMOS AL SGBD QUE USE LA BASE DE DATOS liga

		       Query = "USE Liga;";
		       st.executeUpdate(Query);
		        
		       
			     System.out.println("Usando base de datos");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertarEquipo(String equipo,int puntos) {
		try {
			
			Query = "  INSERT INTO EQUIPOS (id_equipo,puntos) VALUES"
		       		+ " ('"+equipo+"',"+puntos+");";
		       
		       st.executeUpdate(Query);
			       
			   System.out.println("Se ha añadido el equipo "+equipo+" con "+puntos+" puntos");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void actualizarEquipo(String equipo,int puntos_nuevos) {
		try {
			Query = " UPDATE EQUIPO SET puntos="+puntos_nuevos+" WHERE id_equipo = "+equipo+";";
			   		
			       
			       st.executeUpdate(Query);
				       
				   System.out.println("TABLA EQUIPO actualizada con éxito");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void actualizarJugador(String jugador,String equipo,int edad,String pais,String posicion) {
		try {
			System.out.println(jugador+" "+equipo+" "+edad+" "+pais+" "+posicion);
			Query = "USE Liga;";
		    st.executeUpdate(Query);
			String condiciones = "";
			if(edad  > 0) {
				condiciones += "edad = '"+edad+"',";
			}
			
			if(pais != "") {
				condiciones += "pais = '"+pais+"',";
			}
			if(posicion != "") {
				condiciones += "posicion = '"+posicion+"',";
			}
			condiciones = condiciones.substring(0,condiciones.length() - 1);
			System.out.println("UPDATE JUGADORES SET "+condiciones+" WHERE id_jugador = '"+jugador+"' AND id_equipo = '"+equipo+"' ");
			
			Query = "UPDATE JUGADORES SET "+condiciones+" WHERE id_jugador = '"+jugador+"' AND id_equipo = '"+equipo+"' ";
			st.executeUpdate(Query);
			System.out.println("TABLA JUGADORES actualizada con éxito");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertarJugador(String jugador,String equipo,int edad,String pais,String posicion) {
		try {
			Query = "USE Liga;";
		    st.executeUpdate(Query);
			
			Query = "  INSERT INTO JUGADORES (id_jugador,id_equipo,edad,pais,posicion) VALUES"
			   		+ "('"+jugador+"','"+equipo+"',"+edad+",'"+pais+"','"+posicion+"');";
			       
			       st.executeUpdate(Query);
				       
				   System.out.println("Se ha añadido al equipo "+equipo+" el jugador "+jugador+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void borrarJugador(String jugador,String equipo,int edad,String pais,String posicion) {
		try {
			Query = "USE Liga;";
		    st.executeUpdate(Query);
			Query = "DELETE FROM JUGADORES WHERE id_jugador = '"+jugador+"' AND id_equipo = '"+equipo+"' ";
			st.executeUpdate(Query);
			System.out.println("JUGADOR borrado con éxito");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void borrarEquipo(String equipo) {
		try {
			Query = "DELETE FROM JUGDAORES WHERE id_equipo = "+equipo+"";
			st.executeUpdate(Query);
			Query = "DELETE FROM EQUIPO WHERE id_equipo = "+equipo+" ";
			st.executeUpdate(Query);
			System.out.println("EQUIPO borrado con éxito");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarEquipos(DefaultTableModel modelo) {
		Query = "USE Liga;";
		Query = "SELECT * FROM EQUIPOS ORDER BY puntos DESC";
		try {
			
            java.sql.ResultSet resultSet = st.executeQuery(Query);
            
            int c = resultSet.getMetaData().getColumnCount();

            for (int i = 1; i <= c; i++){
            	modelo.addColumn(resultSet.getMetaData().getColumnName(i));

            }
            			            
            Vector<String> fila = null;

            while(resultSet.next()) {
            	fila = new Vector<String>();
                for (int i = 1; i <= c; i++)
                	fila.add(resultSet.getString(i));
          
                modelo.insertRow(modelo.getRowCount(),fila);
        	}
            
         
    	} catch (Exception e) {  
			e.printStackTrace();

    	}
	}
	
	public void mostrarJugadores(DefaultTableModel modelo,String equipo) {
		
		

		Query = "USE Liga;";
		Query = "SELECT * FROM JUGADORES WHERE id_equipo = '"+equipo+"'";
		try {
			
            java.sql.ResultSet resultSet = st.executeQuery(Query);
            
            int c = resultSet.getMetaData().getColumnCount();

            for (int i = 1; i <= c; i++){
            	modelo.addColumn(resultSet.getMetaData().getColumnName(i));

            }
            			            
            Vector<String> fila = null;

            while(resultSet.next()) {
            	fila = new Vector<String>();
                for (int i = 1; i <= c; i++)
                	fila.add(resultSet.getString(i));
          
                modelo.insertRow(modelo.getRowCount(),fila);
        	}
            
         
    	} catch (Exception e) {  
			e.printStackTrace();

    	}
	}
	
	public void playMusic(URL resourceURL) {
	    try {
	        AudioInputStream audioStream = AudioSystem.getAudioInputStream(resourceURL);

	        clip = AudioSystem.getClip();
	        clip.open(audioStream);

	        // Obtener el control de volumen del Clip
	        volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

	        clip.start();
	    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
	        e.printStackTrace();
	    }
	}

	public void stopMusic() {
	    if (clip != null && clip.isRunning()) {
	        clip.stop();
	    }
	}

	public void resumeMusic() {
	    if (clip != null && !clip.isRunning()) {
	        clip.start();
	    }
	}
	
	public void increaseVolume(float amount) {
	    if (volumeControl != null) {
	        float currentVolume = volumeControl.getValue();
	        float maxVolume = volumeControl.getMaximum();

	        // Calcular el nuevo volumen teniendo en cuenta los límites
	        float newVolume = Math.min(currentVolume + amount, maxVolume);

	        volumeControl.setValue(newVolume);
	    }
	}

	public void decreaseVolume(float amount) {
	    if (volumeControl != null) {
	        float currentVolume = volumeControl.getValue();
	        float minVolume = volumeControl.getMinimum();

	        // Calcular el nuevo volumen teniendo en cuenta los límites
	        float newVolume = Math.max(currentVolume - amount, minVolume);

	        volumeControl.setValue(newVolume);
	    }
	
}
}
