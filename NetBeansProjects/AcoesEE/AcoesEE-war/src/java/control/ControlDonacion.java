package control;

//import java.io.Serializable;
//import javax.enterprise.context.SessionScoped;
//import javax.inject.Named;

import entidades.Donacion;
import java.util.List;
import entidades.Nino;
import entidades.Socio;
import java.io.Serializable;
import java.util.ArrayList;
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
    private Date fecha;
    
    int year, month, day;
    
    public ControlDonacion() {
        this.donacion = new Donacion();
        this.socio = new Socio();
        this.nino = new Nino();
        this.fecha= new Date();
        
        this.year = 1;
        this.month = 1;
        this.day = 1;
        
        /*  
        donaciones = new ArrayList<>();
        donaciones.add(new Donacion(new Socio("898928W", "Álvaro", "Ramírez"), new Nino("Marina", "Balmén", 'M', new Date(2003 - 1900, 1 - 1, 20)), new Date(2018-1900, 10, 3), 120.0, "Todo bien"));
        */
    }
    
    public String addDonacion() {
        setFecha2();
        this.donacion.setFecha(this.fecha);
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        List<Nino> ln = new ArrayList();
        List<Socio> ls = new ArrayList();
        
        String ninoQuery = "SELECT n FROM Nino n WHERE upper(n.nombre) = upper(\'" + this.nino.getNombre() + "\') and upper(n.apellidos) = upper(\'" + this.nino.getApellidos() + "\')";
        String socioQuery = "SELECT s FROM Socio s WHERE upper(s.nombre) = upper(\'" + this.socio.getNombre() + "\') and upper(s.apellidos) = upper(\'" + this.socio.getApellidos() + "\')";
        String socioQueryDNI = "SELECT s FROM Socio s WHERE upper(s.DNI) = upper(\'" + this.socio.getDNI() + "\')";
        
        if(this.nino.getId() != null) ln = neg.getRowById("Nino", this.nino.getId());
        if(this.socio.getId() != null) ls = neg.getRowById("Socio", this.socio.getId());  
        
        try {
            // 10x
            if(ln.size() == 1 || (ln = neg.getRowsCustomQuery(ninoQuery)).size() == 1) this.donacion.setNino(ln.get(0));
            else throw new EJBException("La búsqueda del niño en la base de datos ha devuelto un número de resultados distinto del que se esperaba (!= 1)");

            if(ls.size() == 1 || (ls = neg.getRowsCustomQuery(socioQuery)).size() == 1 || (ls = neg.getRowsCustomQuery(socioQueryDNI)).size() == 1) this.donacion.setSocio(ls.get(0));
            else throw new EJBException("La búsqueda del socio en la base de datos ha devuelto un número de resultados distinto del que se esperaba (!= 1)");

            neg.add(this.donacion);

        } catch(EJBException e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pruebe a ser más preciso con la búsqueda, rellenando el campo id", null));
        }
        
        this.donacion = new Donacion();
        this.nino = new Nino();
        this.socio = new Socio();
        this.fecha= new Date();
        
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
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este elemento está siendo utilizado por otras entidades, pruebe a eliminar esas referencias primero.", null));
        }
        return null;
    }
    
    public String goModifyDonacion(Donacion don) {
        this.donacion = don;
        setFecha2();
        this.donacion.setFecha(this.fecha);
        
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
        this.fecha= new Date();
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
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public void setFecha2() {
        this.fecha.setDate(this.day);
        this.fecha.setMonth(this.month);
        this.fecha.setYear(this.year);
    }
    
}

