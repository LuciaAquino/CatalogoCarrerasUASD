package catalogocarrerasuasd;

import java.sql.Connection;
import java.sql.Statement;

public class CrearBaseDatos {
    public static void crear() {
        String sqlEscuelas = "CREATE TABLE IF NOT EXISTS escuelas ("
                + "id_escuela INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nombre_escuela TEXT NOT NULL UNIQUE)";

        String sqlCarreras = "CREATE TABLE IF NOT EXISTS carreras ("
                + "id_carrera INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nombre_carrera TEXT NOT NULL, "
                + "id_escuela INTEGER NOT NULL, "
                + "FOREIGN KEY (id_escuela) REFERENCES escuelas(id_escuela))";

        try (Connection conexion = Conexion.conectar();
             Statement stmt = conexion.createStatement()) {
            stmt.execute(sqlEscuelas);
            stmt.execute(sqlCarreras);
            System.out.println("Base de datos y tablas creadas correctamente");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}