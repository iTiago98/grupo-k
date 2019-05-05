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

    
    public ControlEnvio() {
        envio = new Envio();
        
        envios = new ArrayList<>();
        envios.add(new Envio("Camiseta y pantalon", new Nino("Ramón", "Yuzo", 'H', new Date(1999 - 1900, 10 - 1, 20)), new Socio("123456789A", "Joaquin","Cortes")));
        envios.add(new Envio("Material escolar", new Nino("Pedro", "Aguilar", 'H', new Date(2001 - 1900, 8 - 1, 18)), new Socio("123454321B", "Carlos","Leroy")));
        envios.add(new Envio("Ropa interior", new Nino("Lucía", "Matamoros", 'M', new Date(2003 - 1900, 3 - 1, 5)), new Socio("123123123D", "Mercedes","Millán")));

    }
    
    public String addEnvio() {
        envios.add(this.envio);
        this.envio = new Envio();
        return null;
    }
    
    public String removeEnvio(Envio env) {
        envios.remove(env);
        
        return null; // la misma página
    }
    
    public String goModifyEnvio(Envio env) {
        this.envio = env;
        return "enviosModificar.xhtml";
    }
    
    public String modifyEnvio() {
        for(Envio env: envios) {
            if(env.equals(this.envio)) env = this.envio;
        }
        
        this.envio = new Envio();
        
        return "envios.xhtml";
    }
    
    
    /**************************************************/
    // GETTERS Y SETTERS
    /**
     * @return ************************************************/

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
    
}