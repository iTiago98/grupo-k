package control;

import entidades.Beca;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import entidades.Beneficiario;
import entidades.Nino;
import negocio.NegocioGenerico;

@Named(value = "ControlBeneficiario")
@SessionScoped
public class ControlBeneficiario implements Serializable {
    private Beneficiario beneficiario;
    private NegocioGenerico neg;
        
    private Beca beca;
    private Nino nino;
       
    public ControlBeneficiario() {
        this.beneficiario = new Beneficiario();
        this.beca = new Beca();
        this.nino = new Nino();    
    }
    
    public String addBeneficiario() {
        this.beneficiario.setNino(this.nino);
        this.beneficiario.setBeca(this.beca);
        neg.add(this.beneficiario);
        
        this.beneficiario = new Beneficiario();
        this.nino = new Nino();
        this.beca = new Beca();

        return null;
    }
    
    public String removeBeneficiario(Beneficiario b) {
        neg.remove(b);
        
        return null;
    }
    
    public String goModifyBeneficiario(Beneficiario b) {
        this.nino = b.getNino();
        this.beca = b.getBeca();
        this.beneficiario = b;
        
        return "beneficiariosModificar.xhtml";
    }
    
    public String modifyBeneficiario() {
        for(Object b: neg.getRows("getBeneficiarios")) {
            if(b.equals(this.beneficiario)) b = this.beneficiario;
        }
        
        this.beneficiario = new Beneficiario();
        this.nino = new Nino();
        this.beca = new Beca();
        
        return "beneficiarios.xhtml";
    }
    
    
    /**************************************************/
    // GETTERS Y SETTERS
    /**************************************************/

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario b) {
        this.beneficiario = b;
    }
    
    public List<Beneficiario> getBeneficiarios() {
        return neg.getRows("getBeneficiarios");
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
    
}