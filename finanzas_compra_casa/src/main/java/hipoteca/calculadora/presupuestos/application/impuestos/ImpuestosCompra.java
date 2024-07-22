package hipoteca.calculadora.presupuestos.application.impuestos;

import hipoteca.calculadora.presupuestos.application.implementacion.CalculadoraImpuestos;
import hipoteca.calculadora.presupuestos.domain.DatosPresupuestarDto;
import hipoteca.calculadora.presupuestos.domain.constantes.Constantes;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("impuestosCompra")
public class ImpuestosCompra implements CalculadoraImpuestos {


    @Override
    public Double calcularImpuesto(DatosPresupuestarDto datos) {
        boolean propiedad=datos.isPropiedades();
        if(datos.getTipoVivienda().equalsIgnoreCase("OBRA NUEVA")){
            return datos.getPrecioCasa()* Constantes.INPUESTOS_OBRA_NUEVA;
        }
        if(propiedad){
            return datos.getPrecioCasa()* Constantes.INPUESTOS_CON_PROPIEDAD;
        }else {
            return datos.getPrecioCasa()* Constantes.IMPUESTOS_SIN_PROPIEDAD;
        }
    }
}
