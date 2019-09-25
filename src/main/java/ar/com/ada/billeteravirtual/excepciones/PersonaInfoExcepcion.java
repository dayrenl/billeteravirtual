package ar.com.ada.billeteravirtual.excepciones;

import ar.com.ada.billeteravirtual.Persona;

/**
 * PersonaInfoExcepcion
 */
public class PersonaInfoExcepcion extends Exception {

    private Persona persona;
    public PersonaInfoExcepcion(Persona p, String mensaje) {
        
        super(mensaje);
        this.persona = p;
    }
}