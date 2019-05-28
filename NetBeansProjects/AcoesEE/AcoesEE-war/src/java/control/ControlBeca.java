package control;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import negocio.NegocioGenerico;
import entidades.Beca;
import javax.ejb.EJB;

@Named(value = "ControlBeca")
@SessionScoped
public class ControlBeca implements Serializable
{
    @EJB
    private NegocioGenerico neg;
    
    private Beca beca;
    
    public ControlBeca()
    {
        this.beca = new Beca();
    }
    public String addBeca()
    {
        neg.add(beca);
        beca = new Beca();
        return null;
    }
    
    public String removeBeca(Beca b)
    {
        neg.remove(b);
        return null;
    }
    
    public String goModifyBeca(Beca b)
    {
        this.beca = b;
        return "becasModificar.xhtml";
    }
    
    public String modifyBeca()
    {
        for(Object b : neg.getRows("getBecas")){
            if(b.equals(this.beca)) neg.modify(this.beca);
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
    
    public List<Beca> getBecas()
    {
        return neg.getRows("getBecas");
    }
}
