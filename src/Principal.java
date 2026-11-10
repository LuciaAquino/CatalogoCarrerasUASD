package catalogocarrerasuasd;

import javax.swing.*;
import java.sql.*;

public class Principal extends JFrame {

    private JTable tabla;
    private JComboBox<String> comboEscuelas;

    public Principal() {
        setTitle("Catálogo De Carreras UASD");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lbl = new JLabel("Seleccione Escuela:");
        lbl.setBounds(20, 20, 150, 30);
        add(lbl);

        comboEscuelas = new JComboBox<>();
        comboEscuelas.setBounds(170, 20, 300, 30);
        add(comboEscuelas);

        tabla = new JTable();
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 70, 540, 270);
        add(scroll);

        cargarEscuelas();
        comboEscuelas.addActionListener(e -> cargarCarreras());

        if (comboEscuelas.getItemCount() > 0) {
            cargarCarreras();
        }
    }

    private void cargarEscuelas() {
        try (Connection con = Conexion.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT nombre_escuela FROM escuelas")) {
            while (rs.next()) {
                comboEscuelas.addItem(rs.getString("nombre_escuela"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void cargarCarreras() {
        String escuela = (String) comboEscuelas.getSelectedItem();
        if (escuela == null) return;
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(
                "SELECT c.nombre_carrera FROM carreras c " +
                "JOIN escuelas e ON c.id_escuela = e.id_escuela " +
                "WHERE e.nombre_escuela = ?")) {
            ps.setString(1, escuela);
            ResultSet rs = ps.executeQuery();
            String[] columnas = {"Carreras de " + escuela};
            java.util.ArrayList<String[]> datos = new java.util.ArrayList<>();
            while (rs.next()) {
                datos.add(new String[]{rs.getString(1)});
            }
            String[][] datosArr = datos.toArray(new String[0][]);
            tabla.setModel(new javax.swing.table.DefaultTableModel(datosArr, columnas));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
