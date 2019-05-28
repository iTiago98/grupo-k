package control;

import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import entidades.Socio;
import java.util.List;
import negocio.NegocioSocio;
import javax.ejb.EJB;

@Named(value = "ControlSocio")
@SessionScoped
public class ControlSocio implements Serializable {
    private Socio socio;
     
    @EJB
    private NegocioSocio negSocio;
     
    public ControlSocio() {
        socio = new Socio();
    }
    
    public String addSocio() {
        negSocio.addSocio(socio);
        socio = new Socio();
        return null;
    }
    
    public String removeSocio(Socio soc) {
        negSocio.removeSocio(soc);
        return null;
    }
    
    public String goModifySocio(Socio soc) {
        this.socio = soc;
        return "sociosModificar.xhtml";
    }
    
    public String modifySocio() {
        for(Socio soc: negSocio.getSocios()) {
            if(soc.equals(this.socio)) negSocio.modifySocio(this.socio);
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
    
    public List<Socio> getSocios() {
        return negSocio.getSocios();
    }
     
}
