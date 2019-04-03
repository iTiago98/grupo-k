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
@IdClass(BeneficiarioId.class)
public class Beneficiario implements Serializable {
    private static final long serialVersionUID = 1L;
   
    @Id
    @OneToOne
    private Beca beca;
    @Id
    @OneToOne
    private Nino nino;
    
    private String observaciones;
    @Temporal(TemporalType.DATE)
    private Date ano;

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

    public Date getAno() {
        return ano;
    }

    public void setAno(Date ano) {
        this.ano = ano;
    }
    
}