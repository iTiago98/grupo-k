package control;

import entidades.Donacion;
import entidades.Nino;
import entidades.Socio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "ControlDonacion")
@SessionScoped
public class ControlDonacion implements Serializable {
    private Donacion donacion;
    private ArrayList<Donacion> donaciones;

    private Socio socio;
    private Nino nino;
    
    int year, month, day;
    
    public ControlDonacion() {
        donacion = new Donacion();
        
        this.year = 1;
        this.month = 1;
        this.day = 1;
        
        this.socio = new Socio();
        this.nino = new Nino();
        
        donaciones = new ArrayList<>();
        donaciones.add(new Donacion(new Socio("898928W", "Álvaro", "Ramírez"), new Nino("Marina", "Balmén", 'M', new Date(2003 - 1900, 1 - 1, 20)), new Date(2018-1900, 10, 3), 120.0, "Todo bien"));
    }
    
    public String addDonacion() {
        this.donacion.setSocio(this.socio);
        this.donacion.setNino(this.nino);
        this.donacion.setFecha(new Date(this.year - 1900, this.month - 1, this.day));
        
        donaciones.add(this.donacion);
        
        this.donacion = new Donacion();
        this.socio = new Socio();
        this.nino = new Nino();
        
        this.year = 1;
        this.month = 1;
        this.day = 1;
        
        return null;
    }
    
    public String removeDonacion(Donacion don) {
        donaciones.remove(don);
        
        return null;
    }
    
    public String goModifyDonacion(Donacion don) {
        this.nino = don.getNino();
        this.socio = don.getSocio();
        
        this.year = don.getFecha().getYear();
        this.month = don.getFecha().getMonth();
        this.day = don.getFecha().getDay();
        
        this.donacion = don;
        
        return "donacionesModificar.xhtml";
    }
    
    public String modifyDonacion() {
        for(Donacion don: donaciones) {
            if(don.equals(this.donacion)) {
                don = this.donacion;
                don.setFecha(new Date(this.year - 1900, this.month - 1, this.day));
            }
        }
        
        this.donacion = new Donacion();
        this.socio = new Socio();
        this.nino = new Nino();
        this.year = 1;
        this.month = 1;
        this.day = 1;
        
        
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

    public ArrayList<Donacion> getDonaciones() {
        return donaciones;
    }

    public void setDonaciones(ArrayList<Donacion> donaciones) {
        this.donaciones = donaciones;
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
