package Control;

import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import JPA.Nino;
import java.util.Date;

@Named(value = "ControlNino")
@SessionScoped
public class ControlNino implements Serializable {
    private Nino nino;
    private int year, month, day;
    private ArrayList<Nino> ninos;

    
    public ControlNino() {
        nino = new Nino();
        this.year = 1;
        this.month = 1;
        this.day = 1;
        
        ninos = new ArrayList<>();
        // Por alguna razón Java decide que añadir 1900 años a las fechas es algo
        // completamente comprensible. Muchas gracias JAVA.
        ninos.add(new Nino("Ramón", "Yuzo", 'H', new Date(1999 - 1900, 10 - 1, 20)));
        ninos.add(new Nino("Aquiles", "Rodrigez", 'H', new Date(1999 - 1900, 8 - 1, 9)));
        ninos.add(new Nino("Marina", "Balmén", 'M', new Date(2003 - 1900, 1 - 1, 20)));
    }
    
    public String addNino() {
        this.nino.setFechaNacimiento(new Date(this.year - 1900, this.month - 1, this.day));
        ninos.add(this.nino);
        this.nino = new Nino();
        this.year = 1;
        this.month = 1;
        this.day = 1;
        
        return null;
    }
    
    public String removeNino(Nino n) {
        ninos.remove(n);
        
        return null; // la misma página
    }
    
    public String goModifyNino(Nino n) {
        this.year = n.getFechaNacimiento().getYear();
        this.month = n.getFechaNacimiento().getMonth();
        this.day = n.getFechaNacimiento().getDay();
        this.nino = n;
        return "ninosModificar.xhtml";
    }
    
    public String modifyNino() {
        for(Nino n: ninos) {
            if(n.equals(this.nino)) {
                n = this.nino;
                n.setFechaNacimiento(new Date(this.year - 1900, this.month - 1, this.day));
            }
        }
        
        this.nino = new Nino(); // cleanup
        this.year = 1;
        this.month = 1;
        this.day = 1;
        
        
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

    public ArrayList<Nino> getNinos() {
        return ninos;
    }

    public void setNinos(ArrayList<Nino> ninos) {
        this.ninos = ninos;
    }

    
}
