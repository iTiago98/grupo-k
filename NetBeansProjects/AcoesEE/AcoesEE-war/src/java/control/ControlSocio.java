package control;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import entidades.Socio;
import java.util.List;
//import negocio.NegocioSocio;
import negocio.NegocioGenerico;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


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
        try{
            for(Object s: neg.getRows("getSocios")) {
                if(((Socio) s).getDNI().equalsIgnoreCase(this.socio.getDNI())){
                    throw new EJBException();
                }
            }
            neg.add(this.socio);
            this.socio = new Socio();
        }catch(EJBException e) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya existe un socio con ese DNI", null));
            //ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
        }

        return null;
    }
    
    public String removeSocio(Socio soc) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        try {
            neg.remove(soc);
        }catch (EJBException e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este elemento está siendo utilizado por otras entidades, pruebe a eliminar esas referencias primero.", null));
        }
        return null;
    }
    
    public String goModifySocio(Socio soc) {
        this.socio = soc;
        return "sociosModificar.xhtml";
    }
    
    public String modifySocio() {
        for(Object soc: neg.getRows("getSocios")) {
            if(soc.equals(this.socio)) {
                try { 
                    neg.modify(this.socio);
                } catch(EJBException e) {
                    FacesContext ctx = FacesContext.getCurrentInstance();
                    ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
                    ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Probablemente hay algún campo de tipo numérico incorrecto o algún campo está incompleto", null));
                }
            }
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
