package entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@IdClass(DonacionId.class)
public class Donacion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @OneToOne
    private Socio socio;
    @Id
    @OneToOne
    private Nino nino;
    @Id
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(nullable = false)
    private Double importe;

    private String observaciones;

    
    public Donacion() {}
    
    public Donacion(Date fecha, Double importe, String observaciones) {
        this.fecha = fecha;
        this.importe = importe;
        this.observaciones = observaciones;
    }
    
    public Donacion(Socio socio, Nino nino, Date fecha, Double importe, String observaciones) {
        this.socio = socio;
        this.nino = nino;
        this.fecha = fecha;
        this.importe = importe;
        this.observaciones = observaciones;
    }
    
    public String getFechaFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
        return sdf.format(this.fecha);
    }
    
    
    
    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Nino getNino() {
        return nino;
    }

    public void setNino(Nino nino) {
        this.nino = nino;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.socio);
        hash = 97 * hash + Objects.hashCode(this.nino);
        hash = 97 * hash + Objects.hashCode(this.fecha);
        hash = 97 * hash + Objects.hashCode(this.importe);
        hash = 97 * hash + Objects.hashCode(this.observaciones);
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
        final Donacion other = (Donacion) obj;
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        if (!Objects.equals(this.socio, other.socio)) {
            return false;
        }
        if (!Objects.equals(this.nino, other.nino)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.importe, other.importe)) {
            return false;
        }
        return true;
    }

}


