package entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQuery(
    name = "getDonaciones",
    query = "SELECT d FROM Donacion d"
)

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

    // TODO: A ver como hacemos esto 
    @Override
    public int hashCode() {
        return this.nino.hashCode() + this.socio.hashCode() + this.fecha.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Donacion)) return false;
        return this.nino.equals(((Donacion)obj).nino) &&
               this.socio.equals(((Donacion)obj).socio) &&
               this.fecha.equals(((Donacion)obj).fecha);
    }

}


