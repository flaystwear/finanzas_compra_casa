package hipoteca.calculadora.presupuestos.application.implementacion;

import hipoteca.calculadora.presupuestos.domain.DatosPresupuestarDto;
import org.springframework.stereotype.Service;

@Service
public interface CalculadoraImpuestos {
    public Double calcularImpuesto(DatosPresupuestarDto datosPresupuestarDto);
}
