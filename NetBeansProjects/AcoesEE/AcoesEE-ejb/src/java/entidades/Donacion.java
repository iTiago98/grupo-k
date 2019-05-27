package entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    
      /**** NUEVO ****/
    @Override
    public boolean equals(Object o){
        if(this==o)
            return true;
        if(!(o instanceof Donacion))
            return false;
        
        Donacion s = (Donacion)o;
        return super.equals(s)
            && this.socio.equals(s.socio)
            && this.nino.equals(s.nino)
            && this.fecha.equals(s.fecha);
    } 
    
    @Override
    public int hashCode(){
        return Objects.hash(socio,nino,fecha);
    }

}


