package control;

import entidades.Beca;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import entidades.Beneficiario;
import entidades.Nino;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import negocio.NegocioGenerico;

@Named(value = "ControlBeneficiario")
@SessionScoped
public class ControlBeneficiario implements Serializable {
    private Beneficiario beneficiario;
    @EJB
    private NegocioGenerico neg;
        
    private Beca beca;
    private Nino nino;
       
    public ControlBeneficiario() {
        this.beneficiario = new Beneficiario();
        this.beca = new Beca();
        this.nino = new Nino();    
    }
    
    public String addBeneficiario() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        List<Nino> ln = new ArrayList<>();
        List<Beca> lb = new ArrayList<>();
        
        String ninoQuery = "SELECT n FROM Nino n WHERE upper(n.nombre) = upper(\'" + this.nino.getNombre() + "\') and upper(n.apellidos) = upper(\'" + this.nino.getApellidos() + "\')";
        String becaQuery = "SELECT b FROM Beca b WHERE upper(b.nombre) = upper(\'" + this.beca.getNombre() + "\')";
        
        if(this.nino.getId() != null) ln = neg.getRowById("Nino", this.nino.getId());
        if(this.beca.getId() != null) lb = neg.getRowById("Beca", this.beca.getId());
        
        try {
            // 10x (bis)
            if(ln.size() == 1 || (ln = neg.getRowsCustomQuery(ninoQuery)).size() == 1) this.beneficiario.setNino(ln.get(0));
            else throw new EJBException("La búsqueda del niño en la base de datos ha devuelto un número de resultados distinto del que se esperaba (!= 1)");
        
            if(lb.size() == 1 || (lb = neg.getRowsCustomQuery(becaQuery)).size() == 1) this.beneficiario.setBeca(lb.get(0));
            else throw new EJBException("La búsqueda de la beca en la base de datos ha devuelto un número de resultados distinto del que se esperaba (!= 1)");
        
            neg.add(this.beneficiario);
            
        } catch (EJBException e){
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pruebe a ser más preciso con la búsqueda, rellenando el campo id", null));
        }
        
        this.beneficiario = new Beneficiario();
        this.nino = new Nino();
        this.beca = new Beca();
        
        return null;
    }
    
    public String removeBeneficiario(Beneficiario b) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        try {
            neg.remove(b);
        }catch (EJBException e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este elemento está siendo utilizado por otras entidades, pruebe a eliminar esas referencias primero.", null));
        }
        return null;
    }
    
    public String goModifyBeneficiario(Beneficiario b) {
        this.nino = b.getNino();
        this.beca = b.getBeca();
        this.beneficiario = b;
        
        return "beneficiariosModificar.xhtml";
    }
    
    public String modifyBeneficiario() {
        /*for(Object b: neg.getRows("getBeneficiarios")) {
            if(b.equals(this.beneficiario)) neg.modify(this.beneficiario);
        }*/
        neg.modify(this.beneficiario);
        
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