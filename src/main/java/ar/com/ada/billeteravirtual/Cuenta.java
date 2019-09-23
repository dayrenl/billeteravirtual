package ar.com.ada.billeteravirtual;

import java.util.ArrayList;
import java.util.List;

/**
 * Cuenta
 */
public class Cuenta {

    private String moneda;
    private int saldo;
    private int dineroDisponible;
    private List<Movimiento> movimientos = new ArrayList<Movimiento>();
    
}