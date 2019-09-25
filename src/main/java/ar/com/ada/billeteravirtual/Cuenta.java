package ar.com.ada.billeteravirtual;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Cuenta
 */
@Entity
@Table(name = "cuenta")

public class Cuenta {
    @Id
    @Column(name = "idcuenta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String moneda;
    private int saldo;
    private int saldoDisponible;
    private int numeroCuenta;

    @ManyToOne 
    @JoinColumn(name = "idbilletera", referencedColumnName = "idbilletera") 
    private Billetera billetera;
    
  //  @OneToMany (mappedBy = "cuenta", cascade = CascadeType.ALL)
   // private Movimiento movimiento;


}