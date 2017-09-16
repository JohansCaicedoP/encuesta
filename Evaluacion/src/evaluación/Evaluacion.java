/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluación;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author Angie Castañeda
 */
public class Evaluacion extends JFrame implements ActionListener {

    private JPanel panel;
    private JButton JBavanza, JBfinaliza;
    private JLabel LPregunta, LNumPreg;
    private JRadioButton JRopc1, JRopc2, JRopc3, JRopc4;
    private ButtonGroup CBGroupResp;
    private String BTN_FIN = "BTN_FIN";

    Respuestas1 r = new Respuestas1();
    Preguntas1 p = new Preguntas1();

    public Evaluacion() {

        setSize(700, 400);
        setTitle("NUEVA EVALUACION");
        setLocation(500, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        LNumPreg = new JLabel("<html>1. SEGURIDAD ORGANIZATIVA DE LA UNIDAD (este item se califica general por la Unidad)");
        LNumPreg.setForeground(Color.black);
        LNumPreg.setFont(new Font("Arial", 1, 16));
        LNumPreg.setBounds(10, 10, 640, 50);
        panel.add(LNumPreg);

        LPregunta = new JLabel("Pregunta");

        LPregunta.setForeground(Color.red);
        LPregunta.setFont(new Font("Arial", 0, 14));
        LPregunta.setBounds(10, 45, 640, 80);
        panel.add(LPregunta);

        JRopc1 = new JRadioButton();
        JRopc1.setBounds(20, 110, 630, 30);
        panel.add(JRopc1);

        JRopc2 = new JRadioButton();
        JRopc2.setBounds(20, 140, 630, 30);
        panel.add(JRopc2);

        JRopc3 = new JRadioButton();
        JRopc3.setBounds(20, 170, 630, 30);
        panel.add(JRopc3);

        JRopc4 = new JRadioButton();
        JRopc4.setBounds(20, 200, 630, 30);
        panel.add(JRopc4);

        CBGroupResp = new ButtonGroup();
        CBGroupResp.add(JRopc1);
        CBGroupResp.add(JRopc2);
        CBGroupResp.add(JRopc3);
        CBGroupResp.add(JRopc4);

        JBavanza = new JButton("Continuar >>");
        JBavanza.setBounds(390, 280, 150, 20);
        JBavanza.addActionListener((ActionListener) this);
        panel.add(JBavanza);

        JBfinaliza = new JButton("Finalizar");
        JBfinaliza.setBounds(390, 300, 150, 20);
        JBfinaliza.addActionListener((ActionListener) this);
        JBfinaliza.setActionCommand(BTN_FIN);
        panel.add(JBfinaliza);

        LPregunta.setText(p.getPregunta(0));

        CBGroupResp.clearSelection();
        String[] aux = r.setRespuesta(0);

        LPregunta.setText(p.getPregunta(0));
        JRopc1.setText(aux[0]);
        JRopc2.setText(aux[1]);
        JRopc3.setText(aux[2]);
        JRopc4.setText(aux[3]);
        JRopc1.requestFocus();

        add(panel);
        setVisible(true);

    }

    public void showGui(int posi) {
        if (posi <= 48) {
            if (posi == 9) {
                LNumPreg.setText("<html>2. SEGURIDAD LÓGICA (A partir de este item se califica por sección)");
            }
            if (posi == 17) {
                LNumPreg.setText("<html>3. SEGURIDAD LIGADA A LOS RECURSOS HUMANOS");
            }
            if (posi == 22) {
                LNumPreg.setText("<html>4. GESTIÓN DE ACTIVOS DE INFORMACIÓN");
            }
            if (posi == 28) {
                LNumPreg.setText("<html>5. CONTROL DE ACCESOS");
            }
            if (posi == 33) {
                LNumPreg.setText("<html>6. CIFRADO DE INFORMACIÓN");
            }
            if (posi == 36) {
                LNumPreg.setText("<html>7. SEGURIDAD EN LAS TELECOMUNICACIONES");
            }
            if (posi == 40) {
                LNumPreg.setText("<html>8. SEGURIDAD FISICA Y DEL ENTORNO");
            }
            if (posi == 44) {
                LNumPreg.setText("<html>9. SEGURIDAD EN LOS DOCUMENTOS");
            }
            if (posi == 47) {
                LNumPreg.setText("<html>10. SEGURIDAD LEGAL");
            }

            LPregunta.setText(p.getPregunta(posi));
            String[] aux = r.setRespuesta(posi);

            CBGroupResp.clearSelection();
            JRopc1.setText(aux[0]);
            JRopc2.setText(aux[1]);
            JRopc3.setText(aux[2]);
            JRopc4.setText(aux[3]);
            JRopc1.requestFocus();
        } else if (posi > 48) {
            JBavanza.setEnabled(false);
            JBfinaliza.setEnabled(true);
        }
        /*if (BTN_FIN.equals(e.getActionCommand())) {
            JOptionPane.showMessageDialog(this, "Evaluación finalizada.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
        }*/
    }

    public void showQuetions(int posicion) {
        String[] aux = r.setRespuesta(posicion);

        LPregunta.setText(p.getPregunta(posicion));

        CBGroupResp.clearSelection();

        JRopc1.setText(aux[0]);
        JRopc2.setText(aux[1]);
        JRopc3.setText(aux[2]);
        JRopc4.setText(aux[3]);
        JRopc1.requestFocus();

        JBfinaliza.setEnabled(false);
    }

    int index = 0;
    int[] response = new int[50];

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int ii = 0; ii < 49; ii++) {
            //System.out.print(", resp"+ii);
            response[ii] = 3;
        }

        /*###########################################----PEQUEÑO LOG-----##############################################
                Uncomment to test the app!!!!!
        //int temp=0;
        System.out.println("Checking the list of response...");
        for (int i = 0; i < 48; i++) {
            System.err.println("index:" + i + "--->[" + response[i] + "]");
            //System.err.print("index:"+i+".["+temp+"->"+response[i]+"]");      
            temp = temp+1;
            if(temp > 5 ){temp=0;}    /
        }
        ###########################################----PEQUEÑO LOG-----##############################################*/
 /*###########################################----PEQUEÑO LOG-----##############################################*/
        System.out.println("Checking the list of response...");
        for (int i = 0; i < 49; i++) {
            System.err.println("index:" + i + "--->[" + response[i] + "]");
        }
        /*###########################################----PEQUEÑO LOG-----##############################################*/

        int flag = 0;
        for (int ii = 0; ii < 49; ii++) {                        
            if (response[ii] != 0) {
                flag++;
            }
            if (flag == 49) {
                build(response);
            }
        }

        index++;
        System.out.println("index: " + index);
    }

    public void build(int table[]) {

        try {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            try (Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/evaluacion", "johans", "")) {

                Statement s = conexion.createStatement();

                String query = "insert into respuestas (fecha, resp1,resp2, resp3, resp4, resp5, resp6, resp7, resp8, resp9, resp10, resp11, resp12, resp13, resp14, resp15, resp16, resp17, resp18, resp19, resp20, resp21, resp22, resp23, resp24, resp25, resp26, resp27, resp28, resp29, resp30, resp31, resp32, resp33, resp34, resp35, resp36, resp37, resp38, resp39, resp40, resp41, resp42, resp43, resp44, resp45, resp46, resp47, resp48)"                
                      + " values (?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                       

                PreparedStatement preparedStmt = conexion.prepareStatement(query);
                preparedStmt.setString(1, "fecha");//<<1 registro fecha>>
                int cont =  0;
                for (int i = 2; i < 50; i++) {
                    System.out.println("preparedStmt.setInt("+i+", table["+cont+"]);");
                    preparedStmt.setInt(i, table[cont]);
                    cont++;
                }
                /*
                preparedStmt.setInt(2, table[cont]);
                preparedStmt.setInt(3, table[1]);
                preparedStmt.setInt(4, table[2]);
                preparedStmt.setInt(5, table[3]);
                preparedStmt.setInt(6, table[4]);
                preparedStmt.setInt(7, table[5]);
                preparedStmt.setInt(8, table[6]);
                preparedStmt.setInt(9, table[7]);
                preparedStmt.setInt(10, table[8]);
                preparedStmt.setInt(11, table[9]);
                preparedStmt.setInt(12, table[10]);
                preparedStmt.setInt(13, table[11]);
                preparedStmt.setInt(14, table[12]);
                preparedStmt.setInt(15, table[13]);
                preparedStmt.setInt(16, table[14]);
                preparedStmt.setInt(17, table[15]);
                preparedStmt.setInt(18, table[16]);
                preparedStmt.setInt(19, table[17]);
                preparedStmt.setInt(20, table[18]);
                preparedStmt.setInt(21, table[19]);
                preparedStmt.setInt(22, table[20]);
                preparedStmt.setInt(23, table[21]);
                preparedStmt.setInt(24, table[22]);
                preparedStmt.setInt(25, table[23]);
                preparedStmt.setInt(26, table[24]);
                preparedStmt.setInt(27, table[25]);
                preparedStmt.setInt(28, table[26]);
                preparedStmt.setInt(29, table[27]);
                preparedStmt.setInt(30, table[28]);
                preparedStmt.setInt(31, table[29]);
                preparedStmt.setInt(32, table[30]);
                preparedStmt.setInt(33, table[31]);
                preparedStmt.setInt(34, table[32]);
                preparedStmt.setInt(35, table[33]);
                preparedStmt.setInt(36, table[34]);
                preparedStmt.setInt(37, table[35]);
                preparedStmt.setInt(38, table[36]);
                preparedStmt.setInt(39, table[37]);
                preparedStmt.setInt(40, table[38]);
                preparedStmt.setInt(41, table[39]);
                preparedStmt.setInt(42, table[40]);
                preparedStmt.setInt(43, table[41]);
                preparedStmt.setInt(44, table[42]);
                preparedStmt.setInt(45, table[43]);
                preparedStmt.setInt(46, table[44]);
                preparedStmt.setInt(47, table[45]);
                preparedStmt.setInt(48, table[46]);
                preparedStmt.setInt(49, table[47]);//<<48 registros de las respuestas>>
                preparedStmt.setInt(50, table[48]);
                    */
                preparedStmt.execute();

                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
        }
    }
}
