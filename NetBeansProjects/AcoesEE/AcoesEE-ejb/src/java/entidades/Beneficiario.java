package entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;

@Entity
@IdClass(BeneficiarioId.class)
public class Beneficiario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @OneToOne
    private Beca beca;
    @Id
    @OneToOne
    private Nino nino;
    @Id
    private Integer ano;
    private String observaciones;

    public Beneficiario(){}
    
    public Beneficiario(Beca beca, Nino nino){
        this.beca = beca;
        this.nino = nino;
    }
    
    public Beneficiario(Beca beca, Nino nino, Integer ano, String observaciones){
        this.beca = beca;
        this.nino = nino;
        this.ano = ano;
        this.observaciones = observaciones;
    }
    
    
    public Beca getBeca() {
        return beca;
    }

    public void setBeca(Beca beca) {
        this.beca = beca;
    }

    public Nino getNino() {
        return nino;
    }

    public void setNino(Nino nino) {
        this.nino = nino;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.beca);
        hash = 97 * hash + Objects.hashCode(this.nino);
        hash = 97 * hash + Objects.hashCode(this.ano);
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
        final Beneficiario other = (Beneficiario) obj;
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
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
