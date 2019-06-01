package control;

import entidades.Colonia;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import entidades.Nino;
import entidades.Proyecto;
import entidades.Socio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import negocio.NegocioGenerico;

@Named(value = "ControlNino")
@SessionScoped
public class ControlNino implements Serializable {
    private Nino nino;
    private int year, month, day;
    private Socio socio;
    private Colonia colonia;
    private Proyecto proyecto;
    
    @EJB
    private NegocioGenerico neg;
    
    public ControlNino() {
        this.nino = new Nino();
        this.socio = new Socio();
        this.colonia= new Colonia();
        this.proyecto = new Proyecto();
        
        this.year = 1;
        this.month = 1;
        this.day = 1;

    }
    
    public String addNino() {
        this.nino.setFechaNacimiento(new Date(this.year - 1900, this.month - 1, this.day));
        //ninos.add(this.nino);
        coloniaNino();
        proyectoNino();
        try {
            neg.add(this.nino);
        } catch(EJBException e) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Probablemente hay algún campo de tipo numérico incorrecto o algún campo está incompleto", null));
        }
        
        this.nino = new Nino();
        this.socio = new Socio();
        this.colonia = new Colonia();
        this.proyecto = new Proyecto();
        this.year = 1;
        this.month = 1;
        this.day = 1;
        
        return null;
    }
    
    public String removeNino(Nino n) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        try {
            neg.remove(n);
        }catch (EJBException e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este elemento está siendo utilizado por otras entidades, pruebe a eliminar esas referencias primero.", null));
        }
        return null;
    }
    
    public String goModifyNino(Nino n) {
        this.year = n.getFechaNacimiento().getYear();
        this.month = n.getFechaNacimiento().getMonth();
        this.day = n.getFechaNacimiento().getDay();
        this.nino = n; 
        this.colonia = (this.nino.getColonia() != null)?this.nino.getColonia():new Colonia();
        this.socio = (this.nino.getSocio() != null)?this.nino.getSocio():new Socio();
        this.proyecto = (this.nino.getProyecto() != null)?this.nino.getProyecto():new Proyecto();
        return "ninosModificar.xhtml";
    }
    
    
    public String modifyNino() {
        coloniaNino();
        proyectoNino();
        
        for(Object n: neg.getRows("getNinos")) {
            if(n.equals(this.nino)) {
                //n = this.nino;
                //n.setFechaNacimiento(new Date(this.year - 1900, this.month - 1, this.day));
                this.nino.setFechaNacimiento(new Date(this.year - 1900, this.month - 1, this.day));
                neg.modify(this.nino);
            }
        }
        
        this.nino = new Nino();
        this.socio = new Socio();
        this.colonia= new Colonia();
        this.year = 1;
        this.month = 1;
        this.day = 1;
        
        
        return "ninos.xhtml";
    }
    
    public String apadrinarNino(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        List<Socio> ls = new ArrayList();

        String socioQuery = "SELECT s FROM Socio s WHERE upper(s.nombre) = upper(\'" + this.socio.getNombre() + "\') and upper(s.apellidos) = upper(\'" + this.socio.getApellidos() + "\')";
        String socioQueryDNI = "SELECT s FROM Socio s WHERE upper(s.DNI) = upper(\'" + this.socio.getDNI() + "\')";
        
        if(this.socio.getId() != null) ls = neg.getRowById("Socio", this.socio.getId());  
        
        try {
            if(ls.size() == 1 || (ls = neg.getRowsCustomQuery(socioQuery)).size() == 1 || (ls = neg.getRowsCustomQuery(socioQueryDNI)).size() == 1) this.nino.setSocio(ls.get(0));
            else throw new EJBException("La búsqueda del socio en la base de datos ha devuelto un número de resultados distinto del que se esperaba (!= 1)");
        } catch(EJBException e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pruebe a ser más preciso con la búsqueda, rellenando el campo id", null));
        }
        
        modifyNino();
        
        return "ninos.xhtml";
        
    }
    
    public void coloniaNino(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        List<Colonia> lc = new ArrayList();

        String coloniaQuery = "SELECT c FROM Colonia c WHERE upper(c.nombre) = upper(\'" + this.colonia.getNombre() + "\')";
        if(this.colonia.getId() != null) lc = neg.getRowById("Colonia", this.colonia.getId());  
        
        try {
            if(lc.size() == 1 || (lc = neg.getRowsCustomQuery(coloniaQuery)).size() == 1) this.nino.setColonia(lc.get(0));
            else throw new EJBException("La búsqueda de la colonia en la base de datos ha devuelto un número de resultados distinto del que se esperaba (!= 1)");
        } catch(EJBException e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pruebe a ser más preciso con la búsqueda, rellenando el campo id", null));
        }
        
    }
    
    public void proyectoNino() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        List<Proyecto> lp = new ArrayList();

        String proyectoQuery = "SELECT p FROM Proyecto p WHERE upper(p.nombre) = upper(\'" + this.proyecto.getNombre() + "\')";
        if(this.proyecto.getId() != null) lp = neg.getRowById("Proyecto", this.proyecto.getId());  
        
        try {
            if(lp.size() == 1 || (lp = neg.getRowsCustomQuery(proyectoQuery)).size() == 1) this.nino.setProyecto(lp.get(0));
            else throw new EJBException("La búsqueda del proyecto en la base de datos ha devuelto un número de resultados distinto del que se esperaba (!= 1)");
        } catch(EJBException e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pruebe a ser más preciso con la búsqueda, rellenando el campo id", null));
        }
    }

    
    /**************************************************/
    // GETTERS Y SETTERS
    /**************************************************/
    
    public Nino getNino() {
        return nino;
    }

    public void setNino(Nino nino) {
        this.nino = nino;
    }
    
    public Socio getSocio() {
        return socio;
    }
    
    public void setSocio(Socio socio) {
        this.socio = socio;
    }
    
    public Colonia getColonia() {
        return colonia;
    }
    
    public void setColonia(Colonia colonia) {
        this.colonia = colonia;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public List<Nino> getNinos() {
        return neg.getRows("getNinos");
    }
/*
    public void setNinos(ArrayList<Nino> ninos) {
        this.ninos = ninos;
    }
*/
    
}
