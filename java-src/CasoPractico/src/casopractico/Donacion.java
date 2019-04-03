package casopractico;

import java.io.Serializable;
import java.util.Date;
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
    
    private int cantidad;
    @Temporal(TemporalType.DATE)
    private Date fecha;

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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
    
