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
                double comisionTecno=datos.getPrecioCasa()*Constantes.MODIFICADOR_TECNOCASA;
                return comisionTecno*Constantes.MODIFICADOR_IVA+comisionTecno;
            case "Redpiso":
                double comisionRedpi=datos.getPrecioCasa()*Constantes.MODIFICADOR_REDPISO;
                return comisionRedpi*Constantes.MODIFICADOR_IVA+comisionRedpi;
            case "Otra":
                double comision=datos.getPrecioCasa()*Constantes.MODIFICADOR_BASE;
                return comision*Constantes.MODIFICADOR_IVA+comision;
            default:
                return 0.0;
        }
    }

}
