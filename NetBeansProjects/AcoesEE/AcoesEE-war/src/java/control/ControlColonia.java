package control;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import entidades.Colonia;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import negocio.NegocioGenerico;

@Named(value = "ControlColonia")
@SessionScoped
public class ControlColonia implements Serializable {
    
    @EJB
    private NegocioGenerico neg;
    private Colonia colonia;

    
    public ControlColonia() {
        this.colonia = new Colonia();
    }
    
    public String addColonia() {
        try {
            neg.add(this.colonia);
        } catch(EJBException e) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Probablemente hay algún campo de tipo numérico incorrecto o algún campo está incompleto", null));
        }
        this.colonia = new Colonia();
        return null;
    }
    
    public String removeColonia(Colonia col) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        try {
            neg.remove(col);
        }catch (EJBException e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este elemento está siendo utilizado por otras entidades, pruebe a eliminar esas referencias primero.", null));
        }
        return null;
    }
    
    public String goModifyColonia(Colonia col) {
        this.colonia = col;
        return "coloniasModificar.xhtml";
    }
    
    public String modifyColonia() {
        for(Object b : neg.getRows("getColonias")){
            if(b.equals(this.colonia)) {
                try {
                    neg.modify(this.colonia);
                } catch(EJBException e) {
                    FacesContext ctx = FacesContext.getCurrentInstance();
                    ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
                    ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Probablemente hay algún campo de tipo numérico incorrecto o algún campo está incompleto", null));
                }
            }
        }
        
        this.colonia = new Colonia();
        
        return "colonias.xhtml";
    }
    
    
    /**************************************************/
    // GETTERS Y SETTERS
    /**
     * @return ************************************************/

    public Colonia getColonia() {
        return this.colonia;
    }

    public void setColonia(Colonia colonia) {
        this.colonia = colonia;
    }
    
    public List<Colonia> getColonias() {
        return neg.getRows("getColonias");
    }

   /* public void setColonias(ArrayList<Colonia> colonias) {
        this.colonias = colonias;
    }
    */
}