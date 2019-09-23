package ar.com.ada.billeteravirtual.excepciones;

import ar.com.ada.billeteravirtual.Persona;

/**
 * PersonaInfoExcepcion
 */
public class PersonaInfoExcepcion {

    private Persona persona;
    public PersonaInfoExcepcion(Persona p, String mensaje) {
        
        super();
        this.persona = p;
    }
}