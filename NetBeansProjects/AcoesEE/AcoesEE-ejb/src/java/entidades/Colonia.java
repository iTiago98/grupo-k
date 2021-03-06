package entidades;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQuery (
        name = "getColonias",
        query = "SELECT c FROM Colonia c"
)

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
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Colonia)) return false;
        return this.id.equals(((Colonia)obj).id);
    }
    
    

}
