package control;

import entidades.Nino;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import entidades.Proyecto;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import negocio.NegocioGenerico;

@Named(value = "ControlProyecto")
@SessionScoped
public class ControlProyecto implements Serializable {  
    private Proyecto proyecto;
    
    @EJB
    private NegocioGenerico neg;
    
    
    public ControlProyecto() {
        this.proyecto = new Proyecto();
    }
    
    public String addProyecto() {
        try {
            neg.add(this.proyecto);
        } catch(EJBException e) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Probablemente hay algún campo de tipo numérico incorrecto o algún campo está incompleto", null));
        }
        
        this.proyecto = new Proyecto();
        return null;
    }
    
    public String removeProyecto(Proyecto pro) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        try {
            neg.remove(pro);
        }catch (EJBException e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este elemento está siendo utilizado por otras entidades, pruebe a eliminar esas referencias primero.", null));
        }
        return null;
    }
    
    public String goModifyProyecto(Proyecto pro) {
        this.proyecto = pro;
        return "proyectosModificar.xhtml";
    }
    
    public String modifyProyecto() {
        try {
            for(Object pro: neg.getRows("getProyectos")) {
                if(pro.equals(this.proyecto)) neg.modify(this.proyecto);
            }
        } catch(EJBException e) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Probablemente hay algún campo de tipo numérico incorrecto o algún campo está incompleto", null));
        }
            
        this.proyecto = new Proyecto();
        
        return "proyectos.xhtml";
    }

    /**************************************************/
    // GETTERS Y SETTERS
    /**************************************************/

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
    
    public List<Proyecto> getProyectos() {
        return neg.getRows("getProyectos");
    }

    public List<Nino> getNinos() {
        return neg.getRows("getNinos");
    }

}
