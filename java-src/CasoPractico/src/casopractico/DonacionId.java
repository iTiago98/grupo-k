package casopractico;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class DonacionId implements Serializable {
    private Long socio;
    private Long nino;
    private Date fecha;

    public Long getSocio() {
        return socio;
    }

    public void setSocio(Long socio) {
        this.socio = socio;
    }

    public Long getNino() {
        return nino;
    }

    public void setNino(Long nino) {
        this.nino = nino;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.socio);
        hash = 89 * hash + Objects.hashCode(this.nino);
        hash = 89 * hash + Objects.hashCode(this.fecha);
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
        final DonacionId other = (DonacionId) obj;
        if (!Objects.equals(this.socio, other.socio)) {
            return false;
        }
        if (!Objects.equals(this.nino, other.nino)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        return true;
    }

}
