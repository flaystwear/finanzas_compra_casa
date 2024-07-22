package hipoteca.calculadora.presupuestos.application.implementacion;

import hipoteca.calculadora.presupuestos.domain.DatosPresupuestarDto;
import hipoteca.calculadora.presupuestos.domain.Presupuesto;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public interface CalculadoraPrecios {
    public Double calcularPresupuesto(DatosPresupuestarDto datosPresupuestarDto);
}
