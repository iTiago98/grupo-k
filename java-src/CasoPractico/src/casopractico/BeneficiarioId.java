package casopractico;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class BeneficiarioId implements Serializable {
    private Long beca;
    private Long nino;

    public Long getBeca() {
        return beca;
    }

    public void setBeca(Long beca) {
        this.beca = beca;
    }

    public Long getNino() {
        return nino;
    }

    public void setNino(Long nino) {
        this.nino = nino;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.beca);
        hash = 79 * hash + Objects.hashCode(this.nino);
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
        final BeneficiarioId other = (BeneficiarioId) obj;
        if (!Objects.equals(this.beca, other.beca)) {
            return false;
        }
        if (!Objects.equals(this.nino, other.nino)) {
            return false;
        }
        return true;
    }
    
}