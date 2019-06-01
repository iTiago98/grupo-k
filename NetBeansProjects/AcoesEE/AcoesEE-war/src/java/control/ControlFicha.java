package control;

import entidades.Asignatura;
import entidades.FichaAcademica;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import negocio.NegocioGenerico;

@Named(value = "ControlFicha")
@SessionScoped
public class ControlFicha implements Serializable{
    
    @EJB
    private NegocioGenerico neg;
    
    private FichaAcademica ficha;
    private final ArrayList<String> asignaturasOfertadas = new ArrayList<>();
    
    public ControlFicha() throws ParseException{
        ficha = new FichaAcademica();
        asignaturasOfertadas.add("Matemáticas");
        asignaturasOfertadas.add("Lenguaje");
        asignaturasOfertadas.add("Educación Física");
        asignaturasOfertadas.add("Biología");
        asignaturasOfertadas.add("Geografía");
        asignaturasOfertadas.add("Física");
        asignaturasOfertadas.add("Historia");
        asignaturasOfertadas.add("Cultura Clásica");
    }
    
    public String addFicha(){
        neg.add(ficha);
        ficha.getAsignaturas().clear();
        return "ficha.xhtml";
    }
    
    public String addAsig(Asignatura asignatura){
        FacesContext ctx = FacesContext.getCurrentInstance();
        if(asignaturasOfertadas.contains(asignatura.getObservaciones())){
            ficha.getAsignaturas().add(asignatura);
            neg.modify(ficha);
            return "ficha.xhtml";
        }else{
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Introduzca una de las asignaturas ofertadas", null));
            return null;
        }
    }
    
    public String goModifyFicha(FichaAcademica f) {
        this.ficha = f;
        return "fichasModificar.xhtml";
    }
    
    public String removeFicha(FichaAcademica ficha){
        ficha.getAsignaturas().clear();
        neg.modify(ficha);
        neg.remove(ficha);
        return null;
    }

    public FichaAcademica getFicha() {
        return ficha;
    }

    public List<Serializable> getFichas() {
        return neg.getRows("getFichas");
    }  
}
