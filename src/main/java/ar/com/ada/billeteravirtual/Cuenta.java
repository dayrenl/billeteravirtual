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
    private int idcuenta;
    private String moneda;
    private int saldo;
    private int saldoDisponible;
    private int numeroCuenta;

    @ManyToOne 
    @JoinColumn(name = "idbilletera", referencedColumnName = "idbilletera") 
    private Billetera billetera;

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(int saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Billetera getBilletera() {
        return billetera;
    }

    public void setBilletera(Billetera billetera) {
        this.billetera = billetera;
    }
    
  //  @OneToMany (mappedBy = "cuenta", cascade = CascadeType.ALL)
   // private Movimiento movimiento;


}