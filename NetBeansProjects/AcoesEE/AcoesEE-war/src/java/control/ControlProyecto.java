package control;

import entidades.Nino;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import entidades.Proyecto;
import java.util.ArrayList;
import java.util.HashSet;
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
    //private ninos_en_proyecto;
    //private ArrayList<Proyecto> proyectos;
    
    @EJB
    private NegocioGenerico neg;
    
    
    public ControlProyecto() {
        this.proyecto = new Proyecto();
        //this.ninos_en_proyecto = new ArrayList();
        /*
        proyectos = new ArrayList<>();
        proyectos.add(new Proyecto("Construcción de instituto", "Comayagua", 100));
        proyectos.add(new Proyecto("Reparación de carretera", "La Paz - Comayagua", 20));
        */

    }
    
    public String addProyecto() {
       // for(String n: ninos_en_proyecto) System.out.println(n);
        //this.proyecto.setNinos(new HashSet(this.ninos_en_proyecto));
        try {
            neg.add(this.proyecto);
        } catch(EJBException e) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Probablemente hay algún campo de tipo numérico incorrecto o algún campo está incompleto", null));
        }
        
        this.proyecto = new Proyecto();
        //this.ninos_en_proyecto = new ArrayList();
        return null;
    }
    
    public String removeProyecto(Proyecto pro) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        try {
            neg.remove(pro);
        }catch (EJBException e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este proyecto está siendo utilizada por otras entidades, pruebe a eliminar esas referencias primero.", null));
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
/*
    public List<String> getNinos_en_proyecto() {
        return ninos_en_proyecto;
    }
    
    public void setNinos_en_proyecto(List<String> ninos_en_proyecto) {
        this.ninos_en_proyecto = ninos_en_proyecto;
    }
  */  
    
}
