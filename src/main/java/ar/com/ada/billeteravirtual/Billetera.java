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



}