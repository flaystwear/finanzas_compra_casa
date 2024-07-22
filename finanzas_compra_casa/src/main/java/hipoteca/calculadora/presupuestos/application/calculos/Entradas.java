package hipoteca.calculadora.presupuestos.application.calculos;

import hipoteca.calculadora.presupuestos.application.implementacion.CalculadoraPrecios;
import hipoteca.calculadora.presupuestos.domain.DatosPresupuestarDto;
import hipoteca.calculadora.presupuestos.domain.Presupuesto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Entrada")
public class Entradas  implements CalculadoraPrecios {

    @Override
    public Double calcularPresupuesto(DatosPresupuestarDto datos) {
        double entrada=100.0- datos.getPorcentajeFinanciar();
        entrada= entrada/100.0;
        return entrada*datos.getPrecioCasa();
    }
}
