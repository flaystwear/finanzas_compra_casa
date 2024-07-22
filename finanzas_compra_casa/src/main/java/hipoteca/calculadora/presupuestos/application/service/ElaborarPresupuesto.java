package hipoteca.calculadora.presupuestos.application.service;

import hipoteca.calculadora.presupuestos.application.implementacion.CalculadoraImpuestos;
import hipoteca.calculadora.presupuestos.application.implementacion.CalculadoraPrecios;
import hipoteca.calculadora.presupuestos.domain.DatosPresupuestarDto;
import hipoteca.calculadora.presupuestos.domain.Presupuesto;
import hipoteca.calculadora.presupuestos.domain.constantes.Constantes;
import hipoteca.calculadora.presupuestos.domain.exceptions.PresupuestoDenegadoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ElaborarPresupuesto {

    private final CalculadoraImpuestos calculadoraImpuestosInmobiliaria;
    private final CalculadoraImpuestos calculadoraImpuestosCompra;
    private final CalculadoraPrecios calculadoraPreciosEntrada;
    private final CalculadoraPrecios calculadoraPreciosLetra;

    @Autowired
    public ElaborarPresupuesto(
            @Qualifier("impuestosInmobiliaria") CalculadoraImpuestos calculadoraImpuestosInmobiliaria,
            @Qualifier("impuestosCompra") CalculadoraImpuestos calculadoraImpuestosCompra,
            @Qualifier("Entrada") CalculadoraPrecios calculadoraPreciosEntrada,
            @Qualifier("Letra") CalculadoraPrecios calculadoraPreciosLetra) {
        this.calculadoraImpuestosInmobiliaria = calculadoraImpuestosInmobiliaria;
        this.calculadoraImpuestosCompra = calculadoraImpuestosCompra;
        this.calculadoraPreciosEntrada = calculadoraPreciosEntrada;
        this.calculadoraPreciosLetra = calculadoraPreciosLetra;
    }

    public Presupuesto execute(DatosPresupuestarDto datos) throws PresupuestoDenegadoException {

        double impuestosCompra=calculadoraImpuestosCompra.calcularImpuesto(datos);
        double gastosInmobiliaria=calculadoraImpuestosInmobiliaria.calcularImpuesto(datos);
        double financiero= 0.0;
        if(datos.isFinanciero()){
            financiero= Constantes.PRECIO_FINANCIERO;
        }
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
        datos.setDineroSolicitado(dineroSolicitado);
        double letra= calculadoraPreciosLetra.calcularPresupuesto(datos);
        if(letra>(datos.getSalarioNeto()*Constantes.MODIFICADOR_SALARIO)){
            throw new PresupuestoDenegadoException("La letra resultante de esta operación" +
                    " es superior al 33% de los ingresos netos, \n de modo que no se podría" +
                    " aprobar la operación", letra, datos.getSalarioNeto()*Constantes.MODIFICADOR_SALARIO);
        }
        return Presupuesto.builder()
                .impuestosCompra(impuestosCompra)
                .gastosComisionesInmobiliaria(gastosInmobiliaria)
                .gastosComisionesFinanciero(financiero)
                .gastosTasacion(tasacion)
                .gastosNotaria(notario)
                .entrada(entrada)
                .dineroTotalNecesario(dineroTotal)
                .dineroSolicitadoAlBanco(dineroSolicitado)
                .letraMensual(letra)
                .build();
    }
}
