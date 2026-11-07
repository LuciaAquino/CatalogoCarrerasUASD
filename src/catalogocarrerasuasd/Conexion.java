package catalogocarrerasuasd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    public static Connection conectar() {
        
        Connection conexion = null;
        
        try {
            String url = "jdbc:sqlite:catalogo_uasd.db";
            
            conexion = DriverManager.getConnection(url);
            
            System.out.println("Conexion exitosa con SQLite");
        } catch (SQLException e) {
          
            System.out.println("Error de conexion: " + e.getMessage() );
        }
        return conexion;
    }   
}
