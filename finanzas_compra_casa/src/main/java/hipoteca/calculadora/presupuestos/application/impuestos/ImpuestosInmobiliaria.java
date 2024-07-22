package hipoteca.calculadora.presupuestos.application.impuestos;

import hipoteca.calculadora.presupuestos.application.implementacion.CalculadoraImpuestos;
import hipoteca.calculadora.presupuestos.domain.DatosPresupuestarDto;
import hipoteca.calculadora.presupuestos.domain.constantes.Constantes;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("impuestosInmobiliaria")
public class ImpuestosInmobiliaria implements CalculadoraImpuestos {

    @Override
    public Double calcularImpuesto(DatosPresupuestarDto datos) {

        switch (datos.getInmobiliaria()){
            case "TecnoCasa":
                return datos.getPrecioCasa()*Constantes.MODIFICADOR_TECNOCASA*Constantes.MODIFICADOR_IVA;
            case "Redpiso":
                return datos.getPrecioCasa()*Constantes.MODIFICADOR_REDPISO*Constantes.MODIFICADOR_IVA;
            case "Otra":
                return datos.getPrecioCasa()*Constantes.MODIFICADOR_BASE*Constantes.MODIFICADOR_IVA;
            default:
                return 0.0;
        }
    }

}
