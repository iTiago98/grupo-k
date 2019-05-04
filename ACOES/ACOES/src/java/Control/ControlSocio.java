/* Todo metido en la misma clase */
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
        this.socio = new Socio(); // cleanup
        return null;
    }
    
    public String removeSocio(Socio soc) {
        socios.remove(soc);
        
        return null; // la misma página
    }
    
    public String goModifySocio(Socio soc) {
        this.socio = soc;
        return "sociossModificar.xhtml";
    }
    
    public String modifySocio() {
        for(Socio soc: socios) {
            if(soc.equals(this.socio)) soc = this.socio;
        }
        
        this.socio = new Socio(); // cleanup
        
        return "socios.xhtml"; // volvemos a la página de usuarios para visualizar los cambios
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
