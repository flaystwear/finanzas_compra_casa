package hipoteca.calculadora.presupuestos;

import hipoteca.calculadora.presupuestos.application.service.ElaborarPresupuesto;
import hipoteca.calculadora.presupuestos.domain.DatosPresupuestarDto;
import hipoteca.calculadora.presupuestos.domain.Presupuesto;
import hipoteca.calculadora.presupuestos.domain.constantes.Constantes;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

public class CalculadoraHipoteca {


    public static <JPanel> void main(String[] args) {

        JFrame pantalla= crearPanel();
        showWelcomeMessage(pantalla);
    }


    public static void showWelcomeMessage(JFrame frame){
        // Crear una ventana emergente
        JOptionPane.showMessageDialog(frame, "Bienvenido a la presupuestadora de hipotecas:\n" +
                "Rellene los datos para simular el presupuesto de compra", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void showMissingFieldElem(JFrame frame){
        // Crear una ventana emergente
        JOptionPane.showMessageDialog(frame, "Error al pedir el presupuesto:\n" +
                "Hay algún campo sin rellenar", "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
    }

    public static JFrame crearPanel(){
        // Crear el JFrame (ventana principal)
        JFrame frame = new JFrame("Calculadora presupuestos hipotecas");
        frame.setSize(1100, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        Dimension fieldSize = new Dimension(50, 10);//tamaño de los campos de texto a rellenar

        javax.swing.JPanel panel = new javax.swing.JPanel();
        panel.setLayout(new GridLayout(12, 1)); // 5 filas y 2 columnas

        // Configurar NumberFormat para decimales
        NumberFormat decimalFormat = NumberFormat.getNumberInstance();
        decimalFormat.setMaximumFractionDigits(2); // Número máximo de decimales

        //Creación de los campos:

        JLabel precioCasaLabel = new JLabel("Precio de la casa");
        JFormattedTextField precioCasaField = new JFormattedTextField(decimalFormat);
        precioCasaField.setMaximumSize(fieldSize);

        JLabel inmobiliariaLabel = new JLabel("Inmobiliaria");
        JComboBox<String> inmobiliariaComboBox = new JComboBox<>(Constantes.INMOBILIARIAS);
        inmobiliariaComboBox.setMaximumSize(fieldSize);

        JLabel porctenajeEntradaLabel = new JLabel("Porcentaje a financiar");
        JComboBox<String> entradaComboBox = new JComboBox<>(Constantes.ENTRADAS);
        entradaComboBox.setMaximumSize(fieldSize);

        JLabel tipoInteresLabel = new JLabel("Tipo de interés (con ,)");
        JFormattedTextField tipoInteresField = new JFormattedTextField(decimalFormat);
        tipoInteresField.setMaximumSize(fieldSize);

        JLabel tiempoHipotecaLabel = new JLabel("Años hipoteca");
        JFormattedTextField tiempoHipotecaField = new JFormattedTextField(decimalFormat);
        tiempoHipotecaField.setMaximumSize(fieldSize);

        JLabel financieroLabel = new JLabel("Va a usar un financiero?");
        JCheckBox financieroCheckBox = new JCheckBox();

        JLabel propiedadLabel = new JLabel("Tiene usted alguna otra propiedad a su nombre?");
        JCheckBox propiedadCheckBox = new JCheckBox();

        JLabel tipoCasaLabel = new JLabel("Que tipo de casa tienes en mente?");
        JComboBox<String> tipoCasaComboBox = new JComboBox<>(Constantes.TIPO_CASA);
        tiempoHipotecaField.setMaximumSize(fieldSize);

        JLabel salarioLabel = new JLabel("Salario neto mensual (combinado de los compradores)");
        JFormattedTextField salarioField = new JFormattedTextField(decimalFormat);
        salarioField.setMaximumSize(fieldSize);

        JLabel deudasLabel = new JLabel("Importe total de las deudas mensuales");
        JFormattedTextField deudasField = new JFormattedTextField(decimalFormat);
        deudasField.setValue(0);
        salarioField.setMaximumSize(fieldSize);

        JButton submitButton = new JButton("Calcular presupuestos");

        // Añadir los componentes al panel
        panel.add(precioCasaLabel);
        panel.add(precioCasaField);
        panel.add(Box.createVerticalStrut(10));

        panel.add(inmobiliariaLabel);
        panel.add(inmobiliariaComboBox);
        panel.add(Box.createVerticalStrut(10));

        panel.add(porctenajeEntradaLabel);
        panel.add(entradaComboBox);
        panel.add(Box.createVerticalStrut(10));

        panel.add(tipoInteresLabel);
        panel.add(tipoInteresField);

        panel.add(tiempoHipotecaLabel);
        panel.add(tiempoHipotecaField);
        panel.add(Box.createVerticalStrut(10));

        panel.add(financieroLabel);
        panel.add(financieroCheckBox);
        panel.add(Box.createVerticalStrut(10));

        panel.add(propiedadLabel);
        panel.add(propiedadCheckBox);
        panel.add(Box.createVerticalStrut(10));

        panel.add(tiempoHipotecaLabel);
        panel.add(tiempoHipotecaField);
        panel.add(Box.createVerticalStrut(10));

        panel.add(tipoCasaLabel);
        panel.add(tipoCasaComboBox);
        panel.add(Box.createVerticalStrut(10));

        panel.add(salarioLabel);
        panel.add(salarioField);
        panel.add(Box.createVerticalStrut(10));

        panel.add(deudasLabel);
        panel.add(deudasField);


        panel.add(new JLabel()); // Espacio vacío        panel.add(new JLabel()); // Espacio vacío
        panel.add(new JLabel()); // Espacio vacío
        panel.add(submitButton);

        // Añadir el panel al frame
        frame.add(panel);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String tipoInteres=tipoInteresField.getText().replace(",",".");
                    String precioCasa=precioCasaField.getText().replace(".","");
                    DatosPresupuestarDto datosPresupuestarDto=
                            DatosPresupuestarDto.builder()
                                    .precioCasa(Double.parseDouble(precioCasa))
                                    .inmobiliaria((String) inmobiliariaComboBox.getSelectedItem())
                                    .porcentajeFinanciar(Double.parseDouble(entradaComboBox.getSelectedItem().toString()))
                                    .tipoInteres(Double.parseDouble(tipoInteres))
                                    .financiero(financieroCheckBox.isSelected())
                                    .propiedades(propiedadCheckBox.isSelected())
                                    .tiempoHipoteca(Integer.parseInt(tiempoHipotecaField.getText()))
                                    .tipoVivienda(tipoCasaComboBox.getSelectedItem().toString())
                                    .salarioNeto(Double.parseDouble(salarioField.getText()))
                                    .deudas(Double.parseDouble(deudasField.getText()))
                            .build();
                    executeSimulation(datosPresupuestarDto,frame);
                    // Mostrar un mensaje con los datos introducidos
                    // JOptionPane.showMessageDialog(frame, "Nombre: " + nombre + "\nApellido: " + apellido + "\nEmail: " + email + "\nTeléfono: " + telefono);
                }catch (NullPointerException | ClassCastException | NumberFormatException exception){
                    System.out.println(exception.getMessage());
                    showMissingFieldElem(frame);
                }

            }
        });


        // Mostrar la ventana
        frame.setVisible(true);
        return frame;
    }
    public static void executeSimulation(DatosPresupuestarDto datosPresupuestarDto, JFrame frame){
        Presupuesto presupuesto=ElaborarPresupuesto.execute(datosPresupuestarDto);
        JOptionPane.showMessageDialog(frame, presupuesto.toString());
    }


}
