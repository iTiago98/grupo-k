package control;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import entidades.Socio;
import java.util.List;
//import negocio.NegocioSocio;
import negocio.NegocioGenerico;
import javax.ejb.EJB;


@Named(value = "ControlSocio")
@SessionScoped
public class ControlSocio implements Serializable {
    private Socio socio;
     
    //@EJB
    //private NegocioSocio negSocio;
    
    @EJB
    private NegocioGenerico neg;
     
    public ControlSocio() {
        socio = new Socio();
    }
    
    public String addSocio() {
        //negSocio.addSocio(socio);
        neg.add(this.socio);

        this.socio = new Socio();
        return null;
    }
    
    public String removeSocio(Socio soc) {
        //negSocio.removeSocio(soc);
        neg.remove(soc);
        return null;
    }
    
    public String goModifySocio(Socio soc) {
        this.socio = soc;
        return "sociosModificar.xhtml";
    }
    
    public String modifySocio() {
        for(Object soc: /*negSocio.getSocios()*/ neg.getRows("getSocios")) {
            if(soc.equals(this.socio)) neg.modify(this.socio);
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
        //return negSocio.getSocios();
        return neg.getRows("getSocios");
    }
     
}
