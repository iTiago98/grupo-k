package Control;

import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import JPA.Colonia;

@Named(value = "ControlColonia")
@SessionScoped
public class ControlColonia implements Serializable {
    private Colonia colonia;
    private ArrayList<Colonia> colonias;

    
    public ControlColonia() {
        colonia = new Colonia();
        
        colonias = new ArrayList<>();
        colonias.add(new Colonia("Calpules", "Honduras", "Condiciones muy precarias"));
        colonias.add(new Colonia("San Jorge de Sula", "Honduras", "Segunda ciudad en población"));
        colonias.add(new Colonia("Sandoval", "Honduras", "Zona conflictiva"));

    }
    
    public String addColonia() {
        colonias.add(this.colonia);
        this.colonia = new Colonia();
        return null;
    }
    
    public String removeColonia(Colonia col) {
        colonias.remove(col);
        
        return null; // la misma página
    }
    
    public String goModifyColonia(Colonia col) {
        this.colonia = col;
        return "coloniasModificar.xhtml";
    }
    
    public String modifyColonia() {
        for(Colonia col: colonias) {
            if(col.equals(this.colonia)) col = this.colonia;
        }
        
        this.colonia = new Colonia();
        
        return "colonias.xhtml";
    }
    
    
    /**************************************************/
    // GETTERS Y SETTERS
    /**
     * @return ************************************************/

    public Colonia getColonia() {
        return colonia;
    }

    public void setColonia(Colonia colonia) {
        this.colonia = colonia;
    }
    
    public ArrayList<Colonia> getColonias() {
        return colonias;
    }

    public void setColonias(ArrayList<Colonia> colonias) {
        this.colonias = colonias;
    }
    
}