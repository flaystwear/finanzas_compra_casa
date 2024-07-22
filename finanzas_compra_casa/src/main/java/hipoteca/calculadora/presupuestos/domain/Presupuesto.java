package hipoteca.calculadora.presupuestos.domain;

import hipoteca.calculadora.presupuestos.domain.constantes.Constantes;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class Presupuesto {
    private double precioCasa;
    private double impuestosCompra;
    private double gastosComisionesInmobiliaria;
    private double gastosComisionesFinanciero;
    private double gastosNotaria;
    private double gastosTasacion;
    private double entrada;
    private double dineroTotalNecesario;
    private double dineroSolicitadoAlBanco;
    private double letraMensual;

    @Override
    public String toString() {
        return "Presupuesto{" +
                "\n\n\n Precio de la casa = " + precioCasa +
                "\n\n\n impuestos a pagar por la compra = " + impuestosCompra +
                ",\n\n\n comison de la inmobiliaria = " + gastosComisionesInmobiliaria +
                ",\n\n\n comision al financiero = " + gastosComisionesFinanciero +
                ",\n\n\n gastos de notaria= " + gastosNotaria +
                ",\n\n\n gastos de tasacion= " + gastosTasacion +
                ",\n \n\n entrada=" + entrada +
                ",\n\n\n prestamo solicitado al banco = " + dineroSolicitadoAlBanco +
                ",\n\n\n PRESUPUESTO (DINERO AHORRADO NECESARIO) = " + dineroTotalNecesario +
                ",\n\n\n letra (pago mensual) = " + letraMensual +
                '}';
    }
    public String toStringConWarning() {
        return "Presupuesto{" +
                "\n\n\n Precio de la casa = " + precioCasa +
                "\n\n\n impuestos a pagar por la compra = " + impuestosCompra +
                ",\n\n\n comison de la inmobiliaria = " + gastosComisionesInmobiliaria +
                ",\n\n\n comision al financiero = " + gastosComisionesFinanciero +
                ",\n\n\n gastos de notaria= " + gastosNotaria +
                ",\n\n\n gastos de tasacion= " + gastosTasacion +
                ",\n \n\n entrada=" + entrada +
                ",\n\n\n prestamo solicitado al banco = " + dineroSolicitadoAlBanco +
                ",\n\n\n PRESUPUESTO (DINERO AHORRADO NECESARIO) = " + dineroTotalNecesario +
                ",\n\n\n letra (pago mensual) = " + letraMensual +
                "}\n\n\n ALERTA: LA LETRA O PAGO MENSUAL ES \n" +
                "DEMASIADO ELEVADO PARA LOS INGRESOS NETOS MENSUALES \n" +
                "ESTA HIPOTECA NO PODRiA SER CONCEDIDA";
    }
}
