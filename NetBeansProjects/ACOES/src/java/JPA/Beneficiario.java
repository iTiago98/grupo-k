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

}
