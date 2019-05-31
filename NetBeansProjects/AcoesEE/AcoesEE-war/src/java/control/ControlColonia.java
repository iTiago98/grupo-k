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
        neg.add(this.colonia);
        this.colonia = new Colonia();
        return null;
    }
    
    public String removeColonia(Colonia col) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        try {
            neg.remove(col);
        }catch (EJBException e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Esta colonia está siendo utilizada por otras entidades, pruebe a eliminar esas referencias primero.", null));
        }
        return null;
    }
    
    public String goModifyColonia(Colonia col) {
        this.colonia = col;
        return "coloniasModificar.xhtml";
    }
    
    public String modifyColonia() {
        for(Object b : neg.getRows("getColonias")){
            if(b.equals(this.colonia)) neg.modify(this.colonia);
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