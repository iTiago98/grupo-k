package control;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import entidades.Nino;
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
    //private ArrayList<Nino> ninos;
    @EJB
    private NegocioGenerico neg;
    
    public ControlNino() {
        nino = new Nino();
        socio = new Socio();
        this.year = 1;
        this.month = 1;
        this.day = 1;
        /*
        ninos = new ArrayList<>();
        // Por alguna razón Java decide que añadir 1900 años a las fechas es algo
        // completamente comprensible. Muchas gracias JAVA.
        ninos.add(new Nino("Ramón", "Yuzo", 'H', new Date(1999 - 1900, 10 - 1, 20)));
        ninos.add(new Nino("Aquiles", "Rodrigez", 'H', new Date(1999 - 1900, 8 - 1, 9)));
        ninos.add(new Nino("Marina", "Balmén", 'M', new Date(2003 - 1900, 1 - 1, 20)));
        */
    }
    
    public String addNino() {
        this.nino.setFechaNacimiento(new Date(this.year - 1900, this.month - 1, this.day));
        //ninos.add(this.nino);
        try {
            neg.add(this.nino);
        } catch(EJBException e) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Probablemente hay algún campo de tipo numérico incorrecto o algún campo está incompleto", null));
        }
        
        this.nino = new Nino();
        this.socio = new Socio();
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
       return "ninosModificar.xhtml";
    }
    
    
    public String modifyNino() {
        for(Object n: neg.getRows("getNinos")) {
            if(n.equals(this.nino)) {
                //n = this.nino;
                //n.setFechaNacimiento(new Date(this.year - 1900, this.month - 1, this.day));
                this.nino.setFechaNacimiento(new Date(this.year - 1900, this.month - 1, this.day));
                neg.modify(this.nino);
            }
        }
        
        this.nino = new Nino(); // cleanup
        this.socio = new Socio();
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
            // 10x
            if(ls.size() == 1 || (ls = neg.getRowsCustomQuery(socioQuery)).size() == 1 || (ls = neg.getRowsCustomQuery(socioQueryDNI)).size() == 1) this.nino.setSocio(ls.get(0));
            else throw new EJBException("La búsqueda del socio en la base de datos ha devuelto un número de resultados distinto del que se esperaba (!= 1)");

            //neg.add(this.envio);

        } catch(EJBException e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pruebe a ser más preciso con la búsqueda, rellenando el campo id", null));
        }
        
        modifyNino();
        
        return "ninos.xhtml";
        
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
