package control;

//import java.io.Serializable;
//import javax.enterprise.context.SessionScoped;
//import javax.inject.Named;

import entidades.Donacion;
import java.util.List;
import entidades.Nino;
import entidades.Socio;
import java.io.Serializable;
//import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import negocio.NegocioGenerico;

@Named(value = "ControlDonacion")
@SessionScoped
public class ControlDonacion implements Serializable {
    private Donacion donacion;
    //private ArrayList<Donacion> donaciones;
    
    @EJB
    private NegocioGenerico neg;
    private Socio socio;
    private Nino nino;
    
    int year, month, day;
    
    public ControlDonacion() {
        this.donacion = new Donacion();
        this.socio = new Socio();
        this.nino = new Nino();
        
        this.year = 1;
        this.month = 1;
        this.day = 1;
        
        /*  
        donaciones = new ArrayList<>();
        donaciones.add(new Donacion(new Socio("898928W", "Álvaro", "Ramírez"), new Nino("Marina", "Balmén", 'M', new Date(2003 - 1900, 1 - 1, 20)), new Date(2018-1900, 10, 3), 120.0, "Todo bien"));
        */
    }
    
    public String addDonacion() {
        this.donacion.setSocio(this.socio);
        this.donacion.setNino(this.nino);
        this.donacion.setFecha(new Date(this.year - 1900, this.month - 1, this.day));
        
        try {
            neg.add(this.donacion);
        } catch(EJBException e) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),e.getMessage()));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Probablemente hay algún campo de tipo numérico incorrecto o algún campo está incompleto", null));
        }
        
        this.donacion = new Donacion();
        this.socio = new Socio();
        this.nino = new Nino();
        
        this.year = 1;
        this.month = 1;
        this.day = 1;
        
        
        return null;
    }
    
    public String removeDonacion(Donacion don) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        try {
            neg.remove(don);
        }catch (EJBException e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Esta donación está siendo utilizada por otras entidades, pruebe a eliminar esas referencias primero.", null));
        }
        return null;
    }
    
    public String goModifyDonacion(Donacion don) {
        this.donacion = don;
        
        return "donacionesModificar.xhtml";
    }
    
    public String modifyDonacion() {
        try {
            for(Object don: neg.getRows("getDonaciones"))
                if(don.equals(this.donacion)) neg.modify(this.donacion);
        } catch(EJBException e) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Probablemente hay algún campo de tipo numérico incorrecto o algún campo está incompleto", null));
        } 
        
        this.donacion = new Donacion();
        return "donaciones.xhtml";
    }
    
    
    /**************************************************/
    // GETTERS Y SETTERS
    /**************************************************/

    public Donacion getDonacion() {
        return donacion;
    }

    public void setDonacion(Donacion donacion) {
        this.donacion = donacion;
    }

    public List<Donacion> getDonaciones() {
        return neg.getRows("getDonaciones");
    }
    /*
    public void setDonaciones(ArrayList<Donacion> donaciones) {
        this.donaciones = donaciones;
    }
    */
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

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Nino getNino() {
        return nino;
    }

    public void setNino(Nino nino) {
        this.nino = nino;
    }
    
}

