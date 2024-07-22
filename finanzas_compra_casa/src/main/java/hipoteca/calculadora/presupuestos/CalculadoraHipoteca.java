package hipoteca.calculadora.presupuestos;

import hipoteca.calculadora.presupuestos.application.AppConfig;
import hipoteca.calculadora.presupuestos.application.service.ElaborarPresupuesto;
import hipoteca.calculadora.presupuestos.domain.DatosPresupuestarDto;
import hipoteca.calculadora.presupuestos.domain.Presupuesto;
import hipoteca.calculadora.presupuestos.domain.constantes.Constantes;
import hipoteca.calculadora.presupuestos.domain.exceptions.PresupuestoDenegadoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;


@Component
public class CalculadoraHipoteca {
    private final ElaborarPresupuesto elaborarPresupuesto;

    @Autowired
    public CalculadoraHipoteca(ElaborarPresupuesto elaborarPresupuesto) {
        this.elaborarPresupuesto = elaborarPresupuesto;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        CalculadoraHipoteca calculadoraHipoteca = context.getBean(CalculadoraHipoteca.class);
        JFrame pantalla = calculadoraHipoteca.crearPanel();
        calculadoraHipoteca.showWelcomeMessage(pantalla);
    }


    public void showWelcomeMessage(JFrame frame){
        // Crear una ventana emergente
        JOptionPane.showMessageDialog(frame, "Bienvenido a la presupuestadora de hipotecas:\n" +
                "Rellene los datos para simular el presupuesto de compra", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    }
    public void showMissingFieldElem(JFrame frame){
        // Crear una ventana emergente
        JOptionPane.showMessageDialog(frame, "Error al pedir el presupuesto:\n" +
                "Hay algún campo sin rellenar", "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
    }
    public void showNotEnoughMoney(JFrame frame, PresupuestoDenegadoException e){
        // Crear una ventana emergente
        JOptionPane.showMessageDialog(frame, "La letra mensual ("+e.getLetra()+") superaría el límite\n " +
                "impuesto por los bancos en función del salario mensual\n ("+e.getMaxNeto()+"). \nNo se concedería la hipoteca", "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
    }

    public JFrame crearPanel(){
        // Crear el JFrame (ventana principal)
        JFrame frame = new JFrame("Calculadora presupuestos hipotecas");
        frame.setSize(1100, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(13, 2)); // 5 filas y 2 columnas

        // Configurar NumberFormat para decimales
        NumberFormat decimalFormat = NumberFormat.getNumberInstance();
        decimalFormat.setMaximumFractionDigits(2); // Número máximo de decimales

        //Creación de los campos:

        JLabel precioCasaLabel = new JLabel("Precio de la casa");
        JFormattedTextField precioCasaField = new JFormattedTextField(decimalFormat);


        JLabel inmobiliariaLabel = new JLabel("Inmobiliaria");
        JComboBox<String> inmobiliariaComboBox = new JComboBox<>(Constantes.INMOBILIARIAS);


        JLabel porctenajeEntradaLabel = new JLabel("Porcentaje a financiar");
        JComboBox<String> entradaComboBox = new JComboBox<>(Constantes.ENTRADAS);


        JLabel tipoInteresLabel = new JLabel("Tipo de interés (con ,)");
        JFormattedTextField tipoInteresField = new JFormattedTextField(decimalFormat);


        JLabel tiempoHipotecaLabel = new JLabel("Años hipoteca");
        JFormattedTextField tiempoHipotecaField = new JFormattedTextField(decimalFormat);


        JLabel financieroLabel = new JLabel("Va a usar un financiero?");
        JCheckBox financieroCheckBox = new JCheckBox();

        JLabel propiedadLabel = new JLabel("Tiene usted alguna otra propiedad a su nombre?");
        JCheckBox propiedadCheckBox = new JCheckBox();

        JLabel tipoCasaLabel = new JLabel("Que tipo de casa tienes en mente?");
        JComboBox<String> tipoCasaComboBox = new JComboBox<>(Constantes.TIPO_CASA);


        JLabel salarioLabel = new JLabel("Salario neto mensual (combinado de los compradores)");
        JFormattedTextField salarioField = new JFormattedTextField(decimalFormat);

        JLabel deudasLabel = new JLabel("Importe total de las deudas mensuales");
        JFormattedTextField deudasField = new JFormattedTextField(decimalFormat);
        deudasField.setValue(0);


        JLabel labelInformativo = new JLabel("No sabes el precio? Si tienes presupuesto:");


        JButton submitButton = new JButton("Calcular presupuestos");
        JButton casaParaComprarButton = new JButton("Que casa me compro?");

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
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel()); // Espacio vacío
        panel.add(submitButton);



        panel.add(new JLabel()); // Espacio vacío        panel.add(new JLabel()); // Espacio vacío
        panel.add(new JLabel()); // Espacio vacío
        panel.add(labelInformativo);
        panel.add(casaParaComprarButton);

        // Añadir el panel al frame
        frame.add(panel);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String tipoInteres=tipoInteresField.getText().replace(",",".");
                    String precioCasa=precioCasaField.getText().replace(".","");
                    String salarioNeto=salarioField.getText().replace(".","");
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
                                    .salarioNeto(Double.parseDouble(salarioNeto))
                                    .deudas(Double.parseDouble(deudasField.getText()))
                            .build();
                    executeSimulation(datosPresupuestarDto,frame);
                    // Mostrar un mensaje con los datos introducidos
                    // JOptionPane.showMessageDialog(frame, "Nombre: " + nombre + "\nApellido: " + apellido + "\nEmail: " + email + "\nTeléfono: " + telefono);
                }catch (NullPointerException | ClassCastException | NumberFormatException  exception){
                    System.out.println(exception.getMessage());
                    showMissingFieldElem(frame);
                } catch (PresupuestoDenegadoException exception){
                    System.out.println(exception.getMessage());
                    showNotEnoughMoney(frame, exception);
                }

            }
        });

        casaParaComprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    //Creamos un frame extra para poner el campo adicional que necesitamos:
                    JFrame dialogoExtra = new JFrame("Calculadora presupuestos hipotecas");
                    dialogoExtra.setSize(450, 150);
                    dialogoExtra.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    dialogoExtra.setLocationRelativeTo(null); // Centrar la ventana en la pantalla

                    JPanel panelExtra = new JPanel();
                    panelExtra.setLayout(new GridLayout(3, 2));

                    // Configurar NumberFormat para decimales
                    NumberFormat decimalFormat = NumberFormat.getNumberInstance();
                    decimalFormat.setMaximumFractionDigits(2); // Número máximo de decimales

                    //Creación de los campos:
                    JLabel presupuestoCasalabel = new JLabel("Cuanto dinero total quieres gastar");
                    JFormattedTextField presupuestoCasaField = new JFormattedTextField(decimalFormat);

                    JButton cuantoPuedeCostar = new JButton("Cuanto puede costar la casa?");

                    panelExtra.add(presupuestoCasalabel);
                    panelExtra.add(presupuestoCasaField);
                    panelExtra.add(Box.createVerticalStrut(10));

                    panelExtra.add(Box.createVerticalStrut(10));
                    panelExtra.add(cuantoPuedeCostar);

                    dialogoExtra.add(panelExtra);

                    // Mostrar la ventana
                    dialogoExtra.setVisible(true);


                cuantoPuedeCostar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try{
                            String tipoInteres=tipoInteresField.getText().replace(",",".");
                            String cuantoGastar=presupuestoCasaField.getText().replace(".","");
                            String salarioNeto=salarioField.getText().replace(".","");
                            DatosPresupuestarDto datosPresupuestarDto=
                                    DatosPresupuestarDto.builder()
                                            .cuantoQuieroGastar(Double.parseDouble(cuantoGastar))
                                            .inmobiliaria((String) inmobiliariaComboBox.getSelectedItem())
                                            .porcentajeFinanciar(Double.parseDouble(entradaComboBox.getSelectedItem().toString()))
                                            .tipoInteres(Double.parseDouble(tipoInteres))
                                            .financiero(financieroCheckBox.isSelected())
                                            .propiedades(propiedadCheckBox.isSelected())
                                            .tiempoHipoteca(Integer.parseInt(tiempoHipotecaField.getText()))
                                            .tipoVivienda(tipoCasaComboBox.getSelectedItem().toString())
                                            .salarioNeto(Double.parseDouble(salarioNeto))
                                            .deudas(Double.parseDouble(deudasField.getText()))
                                            .build();
                            calcularCasa(datosPresupuestarDto,frame);
                            // Mostrar un mensaje con los datos introducidos
                            // JOptionPane.showMessageDialog(frame, "Nombre: " + nombre + "\nApellido: " + apellido + "\nEmail: " + email + "\nTeléfono: " + telefono);
                        }catch (NullPointerException | ClassCastException | NumberFormatException  exception){
                            System.out.println(exception.getMessage());
                            showMissingFieldElem(frame);
                        } catch (PresupuestoDenegadoException exception){
                            System.out.println(exception.getMessage());
                            showNotEnoughMoney(frame, exception);
                        }
                    }
                });

            }
        });




        // Mostrar la ventana
        frame.setVisible(true);
        return frame;
    }
    public void executeSimulation(DatosPresupuestarDto datosPresupuestarDto, JFrame frame) throws PresupuestoDenegadoException {
        Presupuesto presupuesto=elaborarPresupuesto.execute(datosPresupuestarDto);
        JOptionPane.showMessageDialog(frame, presupuesto.toString());
    }
    public void calcularCasa(DatosPresupuestarDto datosPresupuestarDto, JFrame frame) throws PresupuestoDenegadoException {
        Presupuesto presupuesto=elaborarPresupuesto.executeFindAHome(datosPresupuestarDto);
        if(presupuesto.getDineroSolicitadoAlBanco()>99999999.99){
            JOptionPane.showMessageDialog(frame, presupuesto.toStringConWarning());
        } else {
            JOptionPane.showMessageDialog(frame, presupuesto.toString());
        }

    }


}
