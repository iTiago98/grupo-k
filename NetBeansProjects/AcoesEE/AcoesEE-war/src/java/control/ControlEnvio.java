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
        envio.setFecha(new Date(this.year - 1900, this.month - 1, this.day));
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        List<Nino> ln = new ArrayList();
        List<Socio> ls = new ArrayList();
        
        // Scripts de JPQL para buscar por nombres y apellidos
        // también por DNI en caso del socio
        String ninoQuery = "SELECT n FROM Nino n WHERE upper(n.nombre) = upper(\'" + this.nino.getNombre() + "\') and upper(n.apellidos) = upper(\'" + this.nino.getApellidos() + "\')";
        String socioQuery = "SELECT s FROM Socio s WHERE upper(s.nombre) = upper(\'" + this.socio.getNombre() + "\') and upper(s.apellidos) = upper(\'" + this.socio.getApellidos() + "\')";
        String socioQueryDNI = "SELECT s FROM Socio s WHERE upper(s.DNI) = upper(\'" + this.socio.getDNI() + "\')";
        
        // Si el usuario introdujo un ID, entonces se busca por ID
        if(this.nino.getId() != null) ln = neg.getRowById("Nino", this.nino.getId());
        if(this.socio.getId() != null) ls = neg.getRowById("Socio", this.socio.getId());  
        
        try {
            // 10x
            
            // Se hace uso de los cortocircuitos con el operator || 
            // Primero se comprueba el resultado de buscar por ID, si se devuelve 0 o más de una fila, entonces se considera
            // que está mal y se realiza la búsqueda secundaria (por nombre, apellidos, dni...)
            if(ln.size() == 1 || (ln = neg.getRowsCustomQuery(ninoQuery)).size() == 1) this.envio.setNino(ln.get(0));
            else throw new EJBException("La búsqueda del niño en la base de datos ha devuelto un número de resultados distinto del que se esperaba (!= 1)");

            if(ls.size() == 1 || (ls = neg.getRowsCustomQuery(socioQuery)).size() == 1 || (ls = neg.getRowsCustomQuery(socioQueryDNI)).size() == 1) this.envio.setSocio(ls.get(0));
            else throw new EJBException("La búsqueda del socio en la base de datos ha devuelto un número de resultados distinto del que se esperaba (!= 1)");

            neg.add(this.envio);

        } catch(EJBException e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pruebe a ser más preciso con la búsqueda, rellenando el campo id", null));
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
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        try {
            neg.remove(env);
        }catch (EJBException e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este elemento está siendo utilizado por otras entidades, pruebe a eliminar esas referencias primero.", null));
        }
        return null;
    }
    
    public String goModifyEnvio(Envio env) {
        this.year = env.getFecha().getYear();
        this.month = env.getFecha().getMonth();
        this.day = env.getFecha().getDay();
        
        this.nino = env.getNino();
        this.socio = env.getSocio();
        this.envio = env;
        
        
        return "enviosModificar.xhtml";
    }
    
    public String modifyEnvio() {
        try {
            this.envio.setFecha(new Date(this.year - 1900, this.month - 1, this.day));
            neg.modify(this.envio);
        } catch(EJBException e) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Probablemente hay algún campo de tipo numérico incorrecto o algún campo está incompleto", null));
        }
        
        this.envio = new Envio();
        this.nino = new Nino();
        this.socio = new Socio();
        
        this.year = 1;
        this.month = 1;
        this.day = 1;
        
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