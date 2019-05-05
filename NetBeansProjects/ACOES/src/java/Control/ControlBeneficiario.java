/* Todo metido en la misma clase */
package Control;

import JPA.Beca;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import JPA.Beneficiario;
import JPA.Nino;

@Named(value = "ControlBeneficiario")
@SessionScoped
public class ControlBeneficiario implements Serializable {
     private Beneficiario beneficiario;
     private ArrayList<Beneficiario> beneficiarios;
     
     public ControlBeneficiario() {
        beneficiario = new Beneficiario();
        
        beneficiarios = new ArrayList<>();
        
        /****SANTIAGO, TERMINA DE IMPLEMENTAR DONDE PONGA BECA****/
        beneficiarios.add(new Beneficiario(new Beca(), new Nino()));
    }
    
    public String addBeneficiario() {
        beneficiarios.add(this.beneficiario);
        this.beneficiario = new Beneficiario(); // cleanup
        return null;
    }
    
    public String removeBeneficiario(Beneficiario bene) {
        beneficiarios.remove(bene);
        
        return null; // la misma página
    }
    
    public String goModifyBeneficiario(Beneficiario bene) {
        this.beneficiario = bene;
        return "beneficiarioModificar.xhtml";
    }
    
    public String modifyBeneficiario() {
        for(Beneficiario bene: beneficiarios) {
            if(bene.equals(this.beneficiario)) bene = this.beneficiario;
        }
        
        this.beneficiario = new Beneficiario(); // cleanup
        
        return "beneficiario.xhtml"; // volvemos a la página de usuarios para visualizar los cambios
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
     
     
     
     
}