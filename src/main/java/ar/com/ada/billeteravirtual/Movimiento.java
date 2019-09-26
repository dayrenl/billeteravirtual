package ar.com.ada.billeteravirtual;

import java.util.Date;

import javax.persistence.*;

/**
 * Movimiento
 */
@Entity
@Table(name = "movimiento")

public class Movimiento {
    @Id
    @Column(name = "idmovimiento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idmovimiento;
    private Date fechaMovimiento;
    private double importe;
   // private float ubicacion;
    private String tipoOperacion;
    private String conceptoOperacion;
    private String detalle;
    private int estado;
    private int cuentaDestino;
    private int cuentaOrigen;
    private int deUsuario;
    private int aUsuario;
    
   

    @ManyToOne 
    @JoinColumn (name = "idcuenta", referencedColumnName = "idcuenta")
    private Cuenta cuenta;

    


    public Movimiento(Date fechaMovimiento, String tipoOperacion, String conceptoOperacion,
            String detalle, int estado, int movimientoId, int cuentaDestino, int cuentaOrigen, int deUsuario, int aUsuario) {
        this.fechaMovimiento = fechaMovimiento;
        this.tipoOperacion = tipoOperacion;
        this.conceptoOperacion = conceptoOperacion;
        this.detalle = detalle;
        this.estado = estado;
        this.cuentaDestino = cuentaDestino;
        this.cuentaOrigen = cuentaOrigen;
        this.deUsuario = deUsuario;
        this.aUsuario = aUsuario;
    }
    
    public Movimiento() {}

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date i) {
        this.fechaMovimiento = i;
    }


    public String isTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getConceptoOperacion() {
        return conceptoOperacion;
    }

    public void setConceptoOperacion(String conceptoOperacion) {
        this.conceptoOperacion = conceptoOperacion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public int getIdmovimiento() {
        return idmovimiento;
    }

    public void setIdmovimiento(int idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(int cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public int getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(int cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public int getDeUsuario() {
        return deUsuario;
    }

    public void setDeUsuario(int deUsuario) {
        this.deUsuario = deUsuario;
    }

    public int getaUsuario() {
        return aUsuario;
    }

    public void setaUsuario(int aUsuario) {
        this.aUsuario = aUsuario;
    }

    
}