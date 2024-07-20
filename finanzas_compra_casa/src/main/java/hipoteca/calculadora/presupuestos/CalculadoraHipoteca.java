package hipoteca.calculadora.presupuestos;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class CalculadoraHipoteca {
    public static <JPanel> void main(String[] args) {
        // Crear el JFrame (ventana principal)
        JFrame frame = new JFrame("Mi Aplicación");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        // Mostrar la ventana
        frame.setVisible(true);
        // Crear una ventana emergente
        JOptionPane.showMessageDialog(frame, "Bienvenido a la presupuestadora de hipotecas:\n" +
                "Rellene los datos de los que disponga", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    }
}
