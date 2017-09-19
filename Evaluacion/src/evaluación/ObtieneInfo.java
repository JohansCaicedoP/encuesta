/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluación;

import com.mysql.jdbc.MysqlIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author johans
 */
public class ObtieneInfo extends JFrame implements ActionListener {

    /**
     * Creates new form ObtieneInfo
     *
     * @throws java.io.IOException
     */
    public ObtieneInfo() {
        initComponents();
        loadCombobox();
        //
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LISTAR__ = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LISTAR__.setText("Mostrar todos los registros");
        LISTAR__.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LISTAR__ActionPerformed(evt);
            }
        });

        jButton2.setText("Consultar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Id");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Inicio");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LISTAR__)
                        .addGap(73, 73, 73)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116)
                        .addComponent(jButton1)
                        .addGap(0, 389, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LISTAR__)
                    .addComponent(jButton2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LISTAR__ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LISTAR__ActionPerformed
        try {
            // Se registra el Driver de MySQL       
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            // Se crea un Statement, para realizar la consulta
            try ( // Se obtiene una conexión con la base de datos. Hay que
                    // cambiar el usuario "root" y la clave "la_clave" por las
                    // adecuadas a la base de datos que estemos usando.
                    Connection conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost/evaluacion", "johans", "")) {
                // Se crea un Statement, para realizar la consulta
                Statement s = conexion.createStatement();
                // Se realiza la consulta. Los resultados se guardan en el
                // ResultSet rs
                ResultSet rs = s.executeQuery("select * from respuestas");
                // Se recorre el ResultSet, mostrando por pantalla los resultados.
                DefaultTableModel modelo = new DefaultTableModel();
                jTable1.setModel(modelo);
                modelo.addColumn("id");
                modelo.addColumn("fecha");
                for (int x = 1; x <= 48; x++) {
                    modelo.addColumn("respuesta: " + x);
                }
                while (rs.next()) {
                    Object[] fila = new Object[50];
                    for (int i = 1; i < 50; i++) {
                        fila[i] = rs.getObject(i + 1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.
                    }
                    // Se añade al modelo la fila completa.
                    modelo.addRow(fila);
                }
                // Se cierra la conexión con la base de datos...
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
        }
    }//GEN-LAST:event_LISTAR__ActionPerformed
    /**
     *
     */
    public void loadCombobox() {
        try {
            // Se registra el Driver de MySQL       
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            // Se crea un Statement, para realizar la consulta
            try ( // Se obtiene una conexión con la base de datos. Hay que
                    // cambiar el usuario "root" y la clave "la_clave" por las
                    // adecuadas a la base de datos que estemos usando.
                    Connection conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost/evaluacion", "johans", "")) {
                // Se crea un Statement, para realizar la consulta
                Statement s = conexion.createStatement();
                // Se realiza la consulta. Los resultados se guardan en el
                // ResultSet rs
                ResultSet rs = s.executeQuery("select * from respuestas");
                // Se recorre el ResultSet, mostrando por pantalla los resultados.              
                jComboBox1.addItem("");
                while (rs.next()) {
                    /*System.out.println(rs.getInt("Id") + " " + rs.getString(2)
                            + " " + rs.getDate(3));*/
                    String response = "Fecha: " + rs.getString("fecha");
                    jComboBox1.addItem(response);
                }
                // Se cierra la conexión con la base de datos...
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
        }
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //if (!"".equals(evt.getItemSelectable().toString())) {
            try {
                // Se registra el Driver de MySQL       
                DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
                // Se crea un Statement, para realizar la consulta
                try ( // Se obtiene una conexión con la base de datos. Hay que
                        // cambiar el usuario "root" y la clave "la_clave" por las
                        // adecuadas a la base de datos que estemos usando.
                        Connection conexion = DriverManager.getConnection(
                                "jdbc:mysql://localhost/evaluacion", "johans", "")) {
                    // Se crea un Statement, para realizar la consulta
                    Statement s = conexion.createStatement();
                    // Se realiza la consulta. Los resultados se guardan en el
                    // ResultSet rs

                    String request = "SELECT `id`, `fecha`, `resp1`, `resp2`, `resp3`, `resp4`, `resp5`, `resp6`, `resp7`, `resp8`, `resp9`, `resp10`, `resp11`, `resp12`, `resp13`, `resp14`, `resp15`, `resp16`, `resp17`, `resp18`, `resp19`, `resp20`, `resp21`, `resp22`, `resp23`, `resp24`, `resp25`, `resp26`, `resp27`, `resp28`, `resp29`, `resp30`, `resp31`, `resp32`, `resp33`, `resp34`, `resp35`, `resp36`, `resp37`, `resp38`, `resp39`, `resp40`, `resp41`, `resp42`, `resp43`, `resp44`, `resp45`, `resp46`, `resp47`, `resp48` FROM respuestas WHERE  fecha=" + this.jComboBox1.getSelectedItem().toString();
                    ResultSet rs = s.executeQuery("select * from respuestas ");
                    // Se recorre el ResultSet, mostrando por pantalla los resultados.
                    DefaultTableModel modelo = new DefaultTableModel();
                    jTable1.setModel(modelo);
                    modelo.addColumn("id");
                    modelo.addColumn("fecha");
                    for (int x = 1; x <= 48; x++) {
                        modelo.addColumn("respuesta: " + x);
                    }
                    while (rs.next()) {
                        Object[] fila = new Object[50];
                        for (int i = 1; i < 50; i++) {
                            fila[i] = rs.getObject(i + 1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.
                        }
                        // Se añade al modelo la fila completa.
                        modelo.addRow(fila);
                        System.out.println(fila);
                    }
                    // Se cierra la conexión con la base de datos...
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println(e.getSQLState());
            }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        Principal principal = new Principal();
        principal.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

    }//GEN-LAST:event_jComboBox1ItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ObtieneInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ObtieneInfo().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LISTAR__;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

}
