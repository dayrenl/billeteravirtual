package ar.com.ada.billeteravirtual;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Usuario
 */
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @Column(name = "idusuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usuarioId;
    private String username;
    private String password;
    private String email;
  //  @Column(name = "persona_id")
   // private int pesonaId;

    @OneToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "persona_id")
    private Persona persona;

    public Usuario(int usuarioId, String username, String password, String email, int pesonaId) {
        this.usuarioId = usuarioId;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Usuario() {

    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario [User Name=" + username + ", Password=" + password + ", User Email=" + getEmail() + "]";
    }
/**
    public int getPesonaId() {
        return pesonaId;
    }

    public void setPesonaId(int pesonaId) {
        this.pesonaId = pesonaId;
    }
*/        
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}