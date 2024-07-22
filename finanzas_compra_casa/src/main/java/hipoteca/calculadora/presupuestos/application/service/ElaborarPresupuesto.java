package hipoteca.calculadora.presupuestos.application.service;

import hipoteca.calculadora.presupuestos.application.implementacion.CalculadoraImpuestos;
import hipoteca.calculadora.presupuestos.application.implementacion.CalculadoraPrecios;
import hipoteca.calculadora.presupuestos.domain.DatosPresupuestarDto;
import hipoteca.calculadora.presupuestos.domain.Presupuesto;
import hipoteca.calculadora.presupuestos.domain.constantes.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ElaborarPresupuesto {

    @Autowired
    @Qualifier("impuestosInmobiliaria")
    private static CalculadoraImpuestos calculadoraImpuestosInmobiliaria;

    @Autowired
    @Qualifier("impuestosCompra")
    private static CalculadoraImpuestos calculadoraImpuestosCompra;

    @Autowired
    @Qualifier("Entrada")
    private static CalculadoraPrecios calculadoraPreciosEntrada;

    @Autowired
    @Qualifier("Letra")
    private static CalculadoraPrecios calculadoraPreciosLetra;

    public static Presupuesto execute(DatosPresupuestarDto datos){
      /*


    private double letraMensual;*/
        double impuestosCompra=calculadoraImpuestosCompra.calcularImpuesto(datos);
        double gastosInmobiliaria=calculadoraImpuestosInmobiliaria.calcularImpuesto(datos);
        double financiero= Constantes.PRECIO_FINANCIERO;
        double tasacion=Constantes.PRECIO_TASACION;
        double notario= Constantes.PRECIO_NOTARIO;
        double entrada = calculadoraPreciosEntrada.calcularPresupuesto(datos);
        double dineroTotal= impuestosCompra
                + gastosInmobiliaria
                + financiero
                + tasacion
                + notario
                + entrada;
        double dineroSolicitado= (datos.getPorcentajeFinanciar()/100.0)*datos.getPrecioCasa();
        double letra= calculadoraPreciosLetra.calcularPresupuesto(datos);

        return null;
    }
}
