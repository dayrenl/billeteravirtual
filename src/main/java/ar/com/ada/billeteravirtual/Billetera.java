package ar.com.ada.billeteravirtual;

import java.util.*;

import javax.persistence.*;
/**
 * Billetera
 */
@Entity
@Table(name = "billetera")

public class Billetera {
    @Id
    @Column(name = "idbilletera")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBilletera;


    @OneToOne 
    @JoinColumn (name= "persona_id", referencedColumnName = "persona_id")
    private Persona persona;

    @OneToMany (mappedBy = "billetera", cascade = CascadeType.ALL)
    private List<Cuenta> cuentas = new ArrayList<Cuenta>();

	public void setPersona(Persona p) {
        this.persona = p;
        this.persona.setBilletera(this);
	}

    public void agregarCuenta(Cuenta c) {
        c.setBilletera(this);
        this.cuentas.add(c);
    }

    public int getIdBilletera() {
        return idBilletera;
    }

    public void setIdBilletera(int idBilletera) {
        this.idBilletera = idBilletera;
    }

    public void agregarMovimiento(Movimiento m) {

        this.cuentas.get(0).agregarMovimiento(m);
    }

        public void detallesDeMovimiento(Movimiento m, double importe, Cuenta c, Usuario u) {
        m.setImporte(importe);
        m.setFechaMovimiento(new Date());   
        m.setConceptoOperacion("Pagos"); 
        m.setEstado(1);
        m.setTipoOperacion("Deposito");
        m.setDetalle("Pago");
        m.setDeUsuario(u.getUsuarioId());
        m.setaUsuario(u.getUsuarioId());
        m.setCuentaDestino(this.cuentas.get(0).getIdcuenta());
        m.setCuentaOrigen(this.cuentas.get(0).getIdcuenta());
        
        
    } 

}