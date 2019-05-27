package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
    
      /**** NUEVO ****/
    @Override
    public boolean equals(Object o){
        if(this==o)
            return true;
        if(!(o instanceof Envio))
            return false;
        
        Envio s = (Envio)o;
        return super.equals(s)
            && this.id.equals(s.id);
    } 
    
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

}

