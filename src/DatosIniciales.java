package catalogocarrerasuasd;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DatosIniciales {
    public static void insertar() {
        String[] escuelas = {"Ciencias", "Humanidades", "Artes", "Ciencias Economicas y Sociales"};

        String[][] carreras = {
            {"Biologia", "Ciencias"},
            {"Psicologia", "Humanidades"},
            {"Filosofia", "Humanidades"},
            {"Diseño Grafico", "Artes"},
            {"Publicidad", "Artes"},
            {"Economia", "Ciencias Economicas y Sociales"}
        };

        String sqlEscuela = "INSERT OR IGNORE INTO escuelas(nombre_escuela) VALUES(?)";
        String sqlCarrera = "INSERT INTO carreras(nombre_carrera, id_escuela) "
                + "SELECT ?, id_escuela FROM escuelas "
                + "WHERE nombre_escuela = ? "
                + "AND NOT EXISTS (SELECT 1 FROM carreras WHERE nombre_carrera = ? AND id_escuela = (SELECT id_escuela FROM escuelas WHERE nombre_escuela = ?))";

        try (Connection conexion = Conexion.conectar()) {
            try (PreparedStatement ps = conexion.prepareStatement(sqlEscuela)) {
                for (String e : escuelas) {
                    ps.setString(1, e);
                    ps.executeUpdate();
                }
            }
            try (PreparedStatement ps = conexion.prepareStatement(sqlCarrera)) {
                for (String[] carrera : carreras) {
                    ps.setString(1, carrera[0]);
                    ps.setString(2, carrera[1]);
                    ps.setString(3, carrera[0]);
                    ps.setString(4, carrera[1]);
                    ps.executeUpdate();
                }
            }
            System.out.println("Escuelas y Carreras insertadas");
        } catch (Exception e) {
            System.out.println("Error al insertar datos: " + e.getMessage());
        }
    }
}    
                    
                
                    
            
        
    

