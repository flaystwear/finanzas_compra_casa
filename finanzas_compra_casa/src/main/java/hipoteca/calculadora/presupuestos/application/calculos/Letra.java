package hipoteca.calculadora.presupuestos.application.calculos;

import hipoteca.calculadora.presupuestos.application.implementacion.CalculadoraPrecios;
import hipoteca.calculadora.presupuestos.domain.DatosPresupuestarDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Letra")
public class Letra   implements CalculadoraPrecios {

    @Override
    public Double calcularPresupuesto(DatosPresupuestarDto datos) {
       return null;
    }
}