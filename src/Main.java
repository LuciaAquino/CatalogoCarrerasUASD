package catalogocarrerasuasd;

public class Main {

    public static void main(String[] args) {

        CrearBaseDatos.crear();
        DatosIniciales.insertar();

        Principal ventana = new Principal();
        ventana.setVisible(true);

    }

}