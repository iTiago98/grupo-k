package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@NamedQuery(
        name = "getEnvios",
        query = "SELECT e FROM Envio e"
)

@Entity
public class Envio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String observaciones;
    @ManyToOne
    private Nino nino;
    @ManyToOne
    private Socio socio;
    
    public Envio() {}
    
    public Envio(String observaciones, Nino nino, Socio socio) {
        this.observaciones = observaciones;
        this.nino = nino;
        this.socio = socio;
    }
    
    public Envio(Nino nino, Socio socio) {
        this.nino = nino;
        this.socio = socio;
    }
    
    public Envio(String observaciones, String NombreN, String ApellidoN, char sexo, Date fechaNacimiento, String DNI, String NombreS, String ApellidoS) {
        this.nino = new Nino(NombreN, ApellidoN, sexo, fechaNacimiento);
        this.socio = new Socio(DNI, NombreS, ApellidoS);
        this.observaciones = observaciones;
    }
    
    public Envio(String NombreN, String ApellidoN, char sexo, Date fechaNacimiento, String DNI, String NombreS, String ApellidoS) {
        this.nino = new Nino(NombreN, ApellidoN, sexo, fechaNacimiento);
        this.socio = new Socio(DNI, NombreS, ApellidoS);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Nino getNino() {
        return nino;
    }

    public void setNino(Nino nino) {
        this.nino = nino;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Envio)) return false;
        return this.id.equals(((Envio)obj).id);
    }
    
    

}

