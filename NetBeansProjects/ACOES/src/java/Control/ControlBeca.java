package Control;

import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import JPA.Beca;

@Named(value = "ControlBeca")
@SessionScoped
public class ControlBeca implements Serializable
{
    private Beca beca;
    private ArrayList<Beca> becas;
    
    public ControlBeca()
    {
        this.beca = new Beca();
        this.becas = new ArrayList<>();
        
        this.becas.add(new Beca("BAB", "Beca para alumnos con mayor rendimiento que la media."));
        this.becas.add(new Beca("AMEB", "Beca para cubrir los costes de los materiales escolares básicos."));
    }
    
    public String addBeca()
    {
        this.becas.add(this.beca);
        this.beca = new Beca();
        return null;
    }
    
    public String removeBeca(Beca beca)
    {
        this.becas.remove(beca);
        return null;
    }
    
    public String goModifyBeca(Beca beca)
    {
        this.beca = beca;
        return "becasModificar.xhtml";
    }
    
    public String modifyBeca()
    {
        for(Beca beca : this.becas){
            if(beca.equals(this.beca)) beca = this.beca;
        }            
        
        this.beca = new Beca();
        
        return "becas.xhtml";
    }
    
    /**************************************************/
    // GETTERS Y SETTERS
    /**************************************************/
    
    public Beca getBeca()
    {
        return this.beca;
    }
    
    public void setBeca(Beca beca)
    {
        this.beca = beca;
    }
    
    public ArrayList<Beca> getBecas()
    {
        return this.becas;
    }
    
    public void setBecas(ArrayList<Beca> becas)
    {
        this.becas = becas;
    }
}
