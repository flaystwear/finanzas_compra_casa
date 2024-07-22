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
                "\n\n\n impuestos a pagar por la compra = " + impuestosCompra +
                ",\n\n\n comisón de la inmobiliaria = " + gastosComisionesInmobiliaria +
                ",\n\n\n comisión al financiero = " + gastosComisionesFinanciero +
                ",\n\n\n gastos de notaria= " + gastosNotaria +
                ",\n\n\n gastos de tasacion= " + gastosTasacion +
                ",\n \n\n entrada=" + entrada +
                ",\n\n\n préstamo solicitado al banco = " + dineroSolicitadoAlBanco +
                ",\n\n\n PRESUPUESTO (DINERO AHORRADO NECESARIO) = " + dineroTotalNecesario +
                ",\n\n\n letra (pago mensual) = " + letraMensual +
                '}';
    }
}
