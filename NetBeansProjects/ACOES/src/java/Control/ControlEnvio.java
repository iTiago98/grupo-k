// XXX la fecha del envío no se usa porque la entidad envío no tiene fecha.
// Quizá debamos cambiar eso en el futuro y añadir un atributo para la JPA porque
// envío debería tener una fecha.

package Control;

import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import JPA.Envio;
import JPA.Nino;
import JPA.Socio;
import java.util.Date;

@Named(value = "ControlEnvio")
@SessionScoped
public class ControlEnvio implements Serializable {
    private Envio envio;
    private ArrayList<Envio> envios;
    
    private Nino nino;
    private Socio socio;
    
    private int year, month, day;
    
    public ControlEnvio() {
        envio = new Envio();
        
        envios = new ArrayList<>();
        envios.add(new Envio("Camiseta y pantalon", new Nino("Ramón", "Yuzo", 'H', new Date(1999 - 1900, 10 - 1, 20)), new Socio("123456789A", "Joaquin","Cortes")));
        envios.add(new Envio("Material escolar", new Nino("Pedro", "Aguilar", 'H', new Date(2001 - 1900, 8 - 1, 18)), new Socio("123454321B", "Carlos","Leroy")));
        envios.add(new Envio("Ropa interior", new Nino("Lucía", "Matamoros", 'M', new Date(2003 - 1900, 3 - 1, 5)), new Socio("123123123D", "Mercedes","Millán")));

        nino = new Nino();
        socio = new Socio();
        
        year = 1;
        month = 1;
        day = 1;
        
    }
    
    public String addEnvio() {
        this.envio.setNino(this.nino);
        this.envio.setSocio(this.socio);
        envios.add(this.envio);
        
        this.envio = new Envio();
        this.nino = new Nino();
        this.socio = new Socio();
        
        this.year = 1;
        this.month = 1;
        this.day = 1;
        
        return null;
    }
    
    public String removeEnvio(Envio env) {
        envios.remove(env);
        
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
        for(Envio env: envios) {
            if(env.equals(this.envio)) env = this.envio;
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
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }
    
    public ArrayList<Envio> getEnvios() {
        return envios;
    }

    public void setEnvio(ArrayList<Envio> envios) {
        this.envios = envios;
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