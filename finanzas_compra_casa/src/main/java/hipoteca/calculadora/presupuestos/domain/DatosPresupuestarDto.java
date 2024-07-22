package hipoteca.calculadora.presupuestos.domain;

import lombok.*;

@Data
@Builder
@Getter
@Setter
public class DatosPresupuestarDto {
    private double precioCasa;
    private String inmobiliaria;
    private double porcentajeFinanciar;
    private double tipoInteres;
    private boolean financiero;
    private boolean propiedades;
    private int tiempoHipoteca;
    private String tipoVivienda;
    private double salarioNeto;
    private double deudas;
    private double dineroSolicitado;
    private double cuantoQuieroGastar;


    @Override
    public String toString() {
        return "DatosPresupuestarDto{" +
                "precioCasa=" + precioCasa +
                ", inmobiliaria='" + inmobiliaria + '\'' +
                ", porcentajeFinanciar=" + porcentajeFinanciar +
                ", tipoInteres=" + tipoInteres +
                ", financiero=" + financiero +
                ", propiedades=" + propiedades +
                ", añosHipoteca=" + tiempoHipoteca +
                ", tipoVivienda='" + tipoVivienda + '\'' +
                ", salarioNeto=" + salarioNeto +
                ", deudas=" + deudas +
                '}';
    }
}
