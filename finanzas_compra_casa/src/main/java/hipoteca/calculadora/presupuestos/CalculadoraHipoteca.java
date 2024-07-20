package hipoteca.calculadora.presupuestos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraHipoteca {
    public static <JPanel> void main(String[] args) {
        // Crear el JFrame (ventana principal)
        JFrame frame = new JFrame("Mi Aplicación");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        javax.swing.JPanel panel = new javax.swing.JPanel();
        panel.setLayout(new GridLayout(5, 2)); // 5 filas y 2 columnas


        //Creación de los campos:
        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreField = new JTextField();

        JLabel apellidoLabel = new JLabel("Apellido:");
        JTextField apellidoField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel telefonoLabel = new JLabel("Teléfono:");
        JTextField telefonoField = new JTextField();

        JButton submitButton = new JButton("Enviar");

        // Añadir los componentes al panel
        panel.add(nombreLabel);
        panel.add(nombreField);
        panel.add(apellidoLabel);
        panel.add(apellidoField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(telefonoLabel);
        panel.add(telefonoField);
        panel.add(new JLabel()); // Espacio vacío
        panel.add(submitButton);

        // Añadir el panel al frame
        frame.add(panel);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               executeSimulation();
                // Mostrar un mensaje con los datos introducidos
               // JOptionPane.showMessageDialog(frame, "Nombre: " + nombre + "\nApellido: " + apellido + "\nEmail: " + email + "\nTeléfono: " + telefono);
            }
        });


        // Mostrar la ventana
        frame.setVisible(true);
        // Crear una ventana emergente
        JOptionPane.showMessageDialog(frame, "Bienvenido a la presupuestadora de hipotecas:\n" +
                "Rellene los datos de los que disponga", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void executeSimulation(){

    }


}
