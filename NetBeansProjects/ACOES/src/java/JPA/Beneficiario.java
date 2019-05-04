package JPA;

import java.io.Serializable;
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

}
