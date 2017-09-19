package evaluación;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;
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

    String name;

    public void setName(String nombre) {
        this.name = nombre;
    }

    private JPanel panel;
    private JButton JBavanza, JBfinaliza;
    private JLabel LPregunta, LNumPreg;
    private JRadioButton JRopc1, JRopc2, JRopc3, JRopc4;
    private ButtonGroup CBGroupResp;
    private String BTN_FIN = "BTN_FIN";

    Respuestas1 r = new Respuestas1();
    Preguntas1 p = new Preguntas1();
    int[] response = new int[49];

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
        //JRopc1.requestFocus();                       

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
        /*Avanzar rapido->>*/ JRopc1.setSelected(true);

        JRopc1.setText(aux[0]);
        JRopc2.setText(aux[1]);
        JRopc3.setText(aux[2]);
        JRopc4.setText(aux[3]);
        JRopc1.requestFocus();

        JBfinaliza.setEnabled(false);
    }

    int index = 0;

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(this.name);
        if (e.getSource() == JBavanza) {
            if (JRopc1.isSelected()) {
                response[index] = 1;
                showGui(index);
                showQuetions(index);
            } else if (JRopc2.isSelected()) {
                response[index] = 2;
                showGui(index);
                showQuetions(index);
            } else if (JRopc3.isSelected()) {
                response[index] = 3;
                showGui(index);
                showQuetions(index);
            } else if (JRopc4.isSelected()) {
                response[index] = 4;
                showGui(index);
                showQuetions(index);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una opcion.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        //for (int ii = 0; ii < 49; ii++) {
        //System.out.print(", resp"+ii);
        //response[ii] = 3;
        //}

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
            if (flag == 49) {//49
                saveData(response);
            }
        }

        index++;
        if (index == 49) {//Control del numero de preguntas a mostrar -> 49 
            JOptionPane.showMessageDialog(this, "Evaluación finalizada.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            Principal principal = new Principal();
            principal.setVisible(true);
        }
    }

    public void saveData(int table[]) {

        try {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            try (Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/evaluacion", "johans", "")) {

                String query = "insert into respuestas (fecha, resp1,resp2, resp3, resp4, resp5, resp6, "
                        + "resp7, resp8, resp9, resp10, resp11, resp12, resp13, resp14, resp15, resp16,"
                        + " resp17, resp18, resp19, resp20, resp21, resp22, resp23, resp24, resp25, resp26, "
                        + "resp27, resp28, resp29, resp30, resp31, resp32, resp33, resp34, resp35, resp36, "
                        + "resp37, resp38, resp39, resp40, resp41, resp42, resp43, resp44, resp45, resp46, resp47, resp48)"
                        + " values (?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                PreparedStatement preparedStmt = conexion.prepareStatement(query);
                preparedStmt.setString(1, this.name);

                int cont = 0;
                for (int i = 2; i < 50; i++) {// Este bucle guarda las respuestas
                    System.out.println("preparedStmt.setInt(" + i + ", table[" + cont + "]);");
                    preparedStmt.setInt(i, table[cont]);
                    cont++;
                }

                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
        }
    }
}
