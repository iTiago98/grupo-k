/* Todo metido en la misma clase */
package Control;

import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import JPA.Proyecto;

@Named(value = "ControlProyecto")
@SessionScoped
public class ControlProyecto implements Serializable {
    private Proyecto proyecto;
    private ArrayList<Proyecto> proyectos;

    
    public ControlProyecto() {
        proyecto = new Proyecto();
        
        proyectos = new ArrayList<>();
        proyectos.add(new Proyecto("Construcción de instituto", "Comayagua", 100));
        proyectos.add(new Proyecto("Reparación de carretera", "La Paz - Comayagua", 20));
        proyectos.add(new Proyecto("Viva el betis", "Santiago de Honduras", 69));

    }
    
    public String addProyecto() {
        proyectos.add(this.proyecto);
        this.proyecto = new Proyecto(); // cleanup
        return null;
    }
    
    public String removeProyecto(Proyecto pro) {
        proyectos.remove(pro);
        
        return null; // la misma página
    }
    
    public String goModifyProyecto(Proyecto pro) {
        this.proyecto = pro;
        return "proyectosModificar.xhtml";
    }
    
    public String modifyProyecto() {
        for(Proyecto pro: proyectos) {
            if(pro.equals(this.proyecto)) pro = this.proyecto;
        }
        
        this.proyecto = new Proyecto(); // cleanup
        
        return "proyectos.xhtml"; // volvemos a la página de usuarios para visualizar los cambios
    }
    
    
    /**************************************************/
    // GETTERS Y SETTERS
    /**************************************************/

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
    
    public ArrayList<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(ArrayList<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }
    
}
