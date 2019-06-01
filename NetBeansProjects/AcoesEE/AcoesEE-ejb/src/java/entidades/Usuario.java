package entidades;

import java.io.Serializable;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@NamedQuery(
    name = "getUsuarios",
    query = "SELECT u FROM Usuario u"
)

@Named (value = "Usuario")
@Entity
public class Usuario implements Serializable {
    
    @Named (value = "Rol")
    public enum Rol {
      ADMINISTRADOR,
      NORMAL
    };
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String usuario;
    private String contrasenia;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    
    public Usuario() {
        
    }
    
    public Usuario (String usuario, String contrasenia, Rol rol) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Rol getRol() {
        return rol;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Usuario)) return false;
        return this.id.equals(((Usuario)obj).id);
    }

    @Override
    public String toString() {
        return id.toString();
    }
    
}
