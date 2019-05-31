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
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
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
        
        int a=0;
        int b=0;
        int c=0;
        
        for(Object n: neg.getRows("getNinos")) {
            if(((Nino) n).getId().equals(this.nino.getId())){
                this.envio.setNino(((Nino) n));
                a=1;
                break;
            }else if(((Nino) n).getNombre().equalsIgnoreCase(this.nino.getNombre()) && 
            ((Nino) n).getApellidos().equalsIgnoreCase(this.nino.getApellidos())) {
               this.envio.setNino(((Nino) n));
               if(c==0) a=0;
               a++;
               c=1;
            }else if(c!=1 && (((Nino) n).getNombre().equalsIgnoreCase(this.nino.getNombre()) || 
            ((Nino) n).getApellidos().equalsIgnoreCase(this.nino.getApellidos()))) {
               this.envio.setNino(((Nino) n));
                a++;
            }
        }
        c=0;
        for(Object s: neg.getRows("getSocios")) {
            if(((Socio) s).getId().equals(this.socio.getId()) || 
            ((Socio) s).getDNI().equalsIgnoreCase(this.socio.getDNI())){
                this.envio.setSocio(((Socio) s));
                b=1;
                break;
            }else if(((Socio) s).getNombre().equalsIgnoreCase(this.socio.getNombre()) && 
            ((Socio) s).getApellidos().equalsIgnoreCase(this.socio.getApellidos())) {
                this.envio.setSocio(((Socio) s));
                if(c==0) b=0;
                b++;
                c=1;
            }else if(c!=1 && (((Socio) s).getNombre().equalsIgnoreCase(this.socio.getNombre()) || 
            ((Socio) s).getApellidos().equalsIgnoreCase(this.socio.getApellidos()))) {
                this.envio.setSocio(((Socio) s));
                b++;
            }
        }
        
        try {
            if(a==1&&b==1){
                neg.add(this.envio);
            }else{
                throw new EJBException();
            }
            //neg.add(this.nino);
            //neg.add(this.socio); 
        } catch(EJBException e) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            if(a==0) ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe ningún niño con esos datos", null));
            else if(a>1) ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existen varios niños con esos datos, sea más específico", null));
                
            if(b==0) ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe ningún socio con esos datos", null));
            else if(b>1) ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existen varios socios con esos datos, sea más específico", null));
            
            //ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
            //ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de creacion", null));
        }
        
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
        
        neg.modify(this.envio);
        
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
    
    public ArrayList<Nino> getNinos(){
        return ninos;
    }
    
    public ArrayList<Socio> getSocios(){
        return socios;
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