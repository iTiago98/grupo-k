package control;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import entidades.Colonia;
import java.util.List;
import javax.ejb.EJB;
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
        neg.remove(col);
        
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