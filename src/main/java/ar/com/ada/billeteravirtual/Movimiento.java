package ar.com.ada.billeteravirtual;

import java.util.Date;

/**
 * Movimiento
 */
public class Movimiento {

    private Date fechaMovimiento;
    private float ubicacion;
    private boolean tipoOperacion;
    private String conceptoOperacion;
    private String detalle;
    private String estado;
    private int movimientoId;


    public Movimiento(Date fechaMovimiento, float ubicacion, boolean tipoOperacion, String conceptoOperacion,
            String detalle, String estado, int movimientoId) {
        this.fechaMovimiento = fechaMovimiento;
        this.ubicacion = ubicacion;
        this.tipoOperacion = tipoOperacion;
        this.conceptoOperacion = conceptoOperacion;
        this.detalle = detalle;
        this.estado = estado;
        this.movimientoId = movimientoId;
    }
    
    public Movimiento() {}

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public float getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(float ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean isTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(boolean tipoOperacion) {
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

    
}