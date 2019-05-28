package entidades;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Colonia implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String localizacion;
    private String descripcion;
    @OneToMany(mappedBy = "colonia")
    private Set<Nino> ninos;
    
    
    public Colonia() {}
    
    public Colonia(String nombre, String localizacion, String descripcion) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.descripcion = descripcion;
    }
    
    public Colonia(String nombre, String localizacion, String descripcion, Set<Nino> ninos) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.descripcion = descripcion;
        this.ninos = ninos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Nino> getNinos() {
        return ninos;
    }

    public void setNinos(Set<Nino> ninos) {
        this.ninos = ninos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.nombre);
        hash = 11 * hash + Objects.hashCode(this.localizacion);
        hash = 11 * hash + Objects.hashCode(this.descripcion);
        hash = 11 * hash + Objects.hashCode(this.ninos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Colonia other = (Colonia) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.localizacion, other.localizacion)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.ninos, other.ninos)) {
            return false;
        }
        return true;
    }
    
    

}
