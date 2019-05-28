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
public class Proyecto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //idCentro

    @Column(nullable = false)
    private String nombre;
    private String localizacion;
    @Column(nullable = false)
    private Integer capacidad;

    @OneToMany(mappedBy = "proyecto")
    private Set<Nino> ninos;

   
    public Proyecto() {}
    
    public Proyecto(String nombre, String localizacion, int capacidad) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.capacidad = capacidad;
    }
    
    public Proyecto(String nombre, String localizacion, int capacidad, Set<Nino> ninos) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.capacidad = capacidad;
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

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Set<Nino> getNinos() {
        return ninos;
    }

    public void setNinos(Set<Nino> ninos) {
        this.ninos = ninos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.nombre);
        hash = 71 * hash + Objects.hashCode(this.localizacion);
        hash = 71 * hash + Objects.hashCode(this.capacidad);
        hash = 71 * hash + Objects.hashCode(this.ninos);
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
        final Proyecto other = (Proyecto) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.localizacion, other.localizacion)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.capacidad, other.capacidad)) {
            return false;
        }
        if (!Objects.equals(this.ninos, other.ninos)) {
            return false;
        }
        return true;
    }
    
    

}
