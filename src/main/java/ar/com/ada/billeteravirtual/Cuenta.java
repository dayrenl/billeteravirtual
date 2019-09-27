package ar.com.ada.billeteravirtual;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    private double saldo;
    private double saldoDisponible;
    private double numeroCuenta;

    @ManyToOne 
    @JoinColumn(name = "idbilletera", referencedColumnName = "idbilletera") 
    private Billetera billetera;

    @OneToMany (mappedBy = "cuenta", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Movimiento> movimientos = new ArrayList<Movimiento>();


    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double d) {
        this.saldo = d;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(double saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public double getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(double numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Billetera getBilletera() {
        return billetera;
    }

    public void setBilletera(Billetera billetera) {
        this.billetera = billetera;
    }
    

    public void agregarMovimiento(Movimiento m) {
        m.setCuenta(this);
        this.movimientos.add(m);
        
        this.setSaldo(this.getSaldo() + m.getImporte());
        this.setSaldoDisponible(this.getSaldo());
    }

    public int getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(int idcuenta) {
        this.idcuenta = idcuenta;
    }


}