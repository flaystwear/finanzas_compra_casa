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
        double C = datos.getDineroSolicitado();//cantidad del préstamo
        double i=(datos.getTipoInteres()/12.0)/100;// TIN dividido entre 12
        double n=datos.getTiempoHipoteca()*12.0;//meses de duración

       return (
                ((Math.pow((1.0+i),n))*i)
                /  ((Math.pow((1.0+i),n))-1)
        )*C;
    }
}