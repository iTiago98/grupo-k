// XXX la fecha del envío no se usa porque la entidad envío no tiene fecha.
// Quizá debamos cambiar eso en el futuro y añadir un atributo para la JPA porque
// envío debería tener una fecha.

package control;

import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import entidades.Envio;
import entidades.Nino;
import entidades.Socio;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import negocio.NegocioGenerico;

@Named(value = "ControlEnvio")
@SessionScoped
public class ControlEnvio implements Serializable {
    
    @EJB
    private NegocioGenerico neg;
    private Envio envio;
    private Nino nino;
    private Socio socio;
    
    private int year, month, day;
    
    public ControlEnvio() {
        envio = new Envio();
        nino = new Nino();
        socio = new Socio();
        
        year = 1;
        month = 1;
        day = 1; 
    }
    
    public String addEnvio() {
        
        this.nino.setFechaNacimiento(new Date(this.year - 1900, this.month - 1, this.day));
        this.nino.setSexo('i');
        this.envio.setNino(this.nino);
        this.envio.setSocio(this.socio);
        
        //try {
            neg.add(this.nino);
            neg.add(this.socio);
            neg.add(this.envio);
            
        /*} catch(EJBException e) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
            //ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de creacion", null));
        }*/
        
        this.envio = new Envio();
        this.nino = new Nino();
        this.socio = new Socio();
        
        this.year = 1;
        this.month = 1;
        this.day = 1;
        
        return null;
    }
    
    public String removeEnvio(Envio env) {
        neg.remove(env);
        
        return null;
    }
    
    public String goModifyEnvio(Envio env) {
        this.nino = env.getNino();
        this.socio = env.getSocio();
        this.envio = env;
        
        this.year = this.nino.getFechaNacimiento().getYear();
        this.month = this.nino.getFechaNacimiento().getMonth();
        this.day = this.nino.getFechaNacimiento().getDay();
        
        return "enviosModificar.xhtml";
    }
    
    public String modifyEnvio() {
        for(Object b : neg.getRows("getEnvios")){
            if(b.equals(this.envio)) neg.modify(this.envio);
        }
        
        this.envio = new Envio();
        this.nino = new Nino();
        this.socio = new Socio();
        
        return "envios.xhtml";
    }
    
    
    /**************************************************/
    // GETTERS Y SETTERS
    /***************************************************/

    public Envio getEnvio() {
        return this.envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }
    
    public List<Envio> getEnvios() {
        return neg.getRows("getEnvios");
    }
    /*
    public void setEnvio(ArrayList<Envio> envios) {
        this.envios = envios;
    }
    */
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

}