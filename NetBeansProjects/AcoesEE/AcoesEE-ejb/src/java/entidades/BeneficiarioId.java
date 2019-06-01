package entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class BeneficiarioId implements Serializable {
    private Long beca;
    private Long nino;
    private Integer ano;

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

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.beca);
        hash = 37 * hash + Objects.hashCode(this.nino);
        hash = 37 * hash + Objects.hashCode(this.ano);
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
        if (!Objects.equals(this.ano, other.ano)) {
            return false;
        }
        return true;
    }

}
