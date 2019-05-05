package Control;

import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import JPA.Socio;

@Named(value = "ControlSocio")
@SessionScoped
public class ControlSocio implements Serializable {
     private Socio socio;
     private ArrayList<Socio> socios;
     
     public ControlSocio() {
        socio = new Socio();
        
        socios = new ArrayList<>();
        socios.add(new Socio("123456789A", "Joaquin","Cortes"));
    }
    
    public String addSocio() {
        socios.add(this.socio);
        this.socio = new Socio();
        return null;
    }
    
    public String removeSocio(Socio soc) {
        socios.remove(soc);
        
        return null;
    }
    
    public String goModifySocio(Socio soc) {
        this.socio = soc;
        return "sociosModificar.xhtml";
    }
    
    public String modifySocio() {
        for(Socio soc: socios) {
            if(soc.equals(this.socio)) soc = this.socio;
        }
        
        this.socio = new Socio();
        
        return "socios.xhtml";
    }
    
    
    /**************************************************/
    // GETTERS Y SETTERS
    /**************************************************/

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }
    
    public ArrayList<Socio> getSocios() {
        return socios;
    }

    public void setProyectos(ArrayList<Socio> socios) {
        this.socios = socios;
    }
     
}
