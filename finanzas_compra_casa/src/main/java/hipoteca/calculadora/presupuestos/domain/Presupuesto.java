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

}
