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
            throw new PresupuestoDenegadoException("La letra resultante de esta operacion" +
                    " es superior al 33% de los ingresos netos, \n de modo que no se podria" +
                    " aprobar la operacion", letra, datos.getSalarioNeto()*Constantes.MODIFICADOR_SALARIO);
        }
        return Presupuesto.builder()
                .precioCasa(datos.getPrecioCasa())
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

    public Presupuesto executeFindAHome(DatosPresupuestarDto datos) throws PresupuestoDenegadoException {
        boolean calculado=false;
        //HHay que setearlo al acabar
        double impuestos=0;
        double inmobiliaria=0;
        double precioEntrada=0;
        double presupuestoFinal=0;
        double mensualidad=0;
        double prestamo=0;

        //Valores fijos
        double dineroDisponible=datos.getCuantoQuieroGastar();
        double tasacion=Constantes.PRECIO_TASACION;
        double notario= Constantes.PRECIO_NOTARIO;
        double financiero= 0.0;
        if(datos.isFinanciero()){
            financiero= Constantes.PRECIO_FINANCIERO;
        }
        double gastosFijos= financiero + tasacion + notario;

        double precioCasaHipotetico= 100000.00;
        double precioPrevioViable=0;
        while (!calculado){
            System.out.println("Calculando casa por valor de : "+precioCasaHipotetico);
            datos.setPrecioCasa(precioCasaHipotetico);
            double impuestosCompra=calculadoraImpuestosCompra.calcularImpuesto(datos);
            double gastosInmobiliaria=calculadoraImpuestosInmobiliaria.calcularImpuesto(datos);
            double entrada = calculadoraPreciosEntrada.calcularPresupuesto(datos);
            double dineroNecesario= gastosFijos
                    + impuestosCompra
                    + gastosInmobiliaria
                    + entrada;
            if(dineroNecesario<dineroDisponible){
                //El presupuesto da para mas, subimos el precio. Guardamos el anterior por si nos pasamos
                precioPrevioViable=precioCasaHipotetico;
                precioCasaHipotetico=precioCasaHipotetico*Constantes.SUBIR_PRECIO;

            } else if (dineroNecesario>dineroDisponible) {
                //El precio se ha pasado, el correcto es el anterior, vamos a recalcularlo:
                datos.setPrecioCasa(precioPrevioViable);
                impuestosCompra=calculadoraImpuestosCompra.calcularImpuesto(datos);
                gastosInmobiliaria=calculadoraImpuestosInmobiliaria.calcularImpuesto(datos);
                entrada = calculadoraPreciosEntrada.calcularPresupuesto(datos);
                dineroNecesario= gastosFijos
                        + impuestosCompra
                        + gastosInmobiliaria
                        + entrada;
                double dineroSolicitado= (datos.getPorcentajeFinanciar()/100.0)*datos.getPrecioCasa();
                datos.setDineroSolicitado(dineroSolicitado);
                double letra= calculadoraPreciosLetra.calcularPresupuesto(datos);
                calculado=true;
                impuestos=impuestosCompra;
                inmobiliaria=gastosInmobiliaria;
                precioEntrada=entrada;
                presupuestoFinal=dineroNecesario;
                mensualidad=letra;
                prestamo=dineroSolicitado;


                /////
            }else{
                //Hemos clavado el precio, vamos a ver si la letra que sale cuadra
                double dineroSolicitado= (datos.getPorcentajeFinanciar()/100.0)*datos.getPrecioCasa();
                datos.setDineroSolicitado(dineroSolicitado);
                double letra= calculadoraPreciosLetra.calcularPresupuesto(datos);
                calculado=true;
                impuestos=impuestosCompra;
                inmobiliaria=gastosInmobiliaria;
                precioEntrada=entrada;
                presupuestoFinal=dineroNecesario;
                mensualidad=letra;
                prestamo=dineroSolicitado;
            }

        }
        if(mensualidad>datos.getSalarioNeto()*Constantes.MODIFICADOR_SALARIO){
            System.out.println("La letra mensual ( "+mensualidad+" ) es demasiado alta para ese salario ( "+datos.getSalarioNeto()+ ")\n" +
                    "La letra maxima permitida seria "+datos.getSalarioNeto()*Constantes.MODIFICADOR_SALARIO);
            prestamo=9999999999.99;
        }
        return Presupuesto.builder()
                .precioCasa(datos.getPrecioCasa())
                .impuestosCompra(impuestos)
                .gastosComisionesInmobiliaria(inmobiliaria)
                .gastosComisionesFinanciero(financiero)
                .gastosTasacion(tasacion)
                .gastosNotaria(notario)
                .entrada(precioEntrada)
                .dineroTotalNecesario(presupuestoFinal)
                .dineroSolicitadoAlBanco(prestamo)
                .letraMensual(mensualidad)
                .build();

    }

}
