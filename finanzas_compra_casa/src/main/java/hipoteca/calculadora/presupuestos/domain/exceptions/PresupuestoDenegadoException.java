package hipoteca.calculadora.presupuestos.domain.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PresupuestoDenegadoException extends Exception{
    private double letra;
    private double maxNeto;
    public PresupuestoDenegadoException(String s, double letra, double maxNeto) {
        this.letra=letra;
        this.maxNeto=maxNeto;
    }
}
