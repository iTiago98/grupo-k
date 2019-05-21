package control;

import entidades.Beca;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import entidades.Beneficiario;
import entidades.Nino;
import java.util.Date;

@Named(value = "ControlBeneficiario")
@SessionScoped
public class ControlBeneficiario implements Serializable {
    private Beneficiario beneficiario;
    private ArrayList<Beneficiario> beneficiarios;
     
    private Beca beca;
    private Nino nino;
       
    public ControlBeneficiario() {
        beneficiario = new Beneficiario();
        
        this.beca = new Beca();
        this.nino = new Nino();
        
        beneficiarios = new ArrayList<>();    
        beneficiarios.add(new Beneficiario(new Beca("BAB", "Beca para alumnos con mayor rendimiento que la media."), new Nino("Ramón", "Yuzo", 'H', new Date(1999 - 1900, 10 - 1, 20)), 2005, null));
    }
    
    public String addBeneficiario() {
        this.beneficiario.setNino(this.nino);
        this.beneficiario.setBeca(this.beca);
        beneficiarios.add(this.beneficiario);
        
        this.beneficiario = new Beneficiario();
        this.nino = new Nino();
        this.beca = new Beca();

        return null;
    }
    
    public String removeBeneficiario(Beneficiario bene) {
        beneficiarios.remove(bene);
        
        return null;
    }
    
    public String goModifyBeneficiario(Beneficiario bene) {
        this.nino = bene.getNino();
        this.beca = bene.getBeca();
        this.beneficiario = bene;
        
        return "beneficiariosModificar.xhtml";
    }
    
    public String modifyBeneficiario() {
        for(Beneficiario bene: beneficiarios) {
            if(bene.equals(this.beneficiario)) bene = this.beneficiario;
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

    public void setBeneficiario(Beneficiario bene) {
        this.beneficiario = bene;
    }
    
    public ArrayList<Beneficiario> getBeneficiarios() {
        return beneficiarios;
    }

    public void setSocios(ArrayList<Beneficiario> benes) {
        this.beneficiarios = benes;
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