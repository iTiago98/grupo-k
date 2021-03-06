package control;

import entidades.Asignatura;
import entidades.FichaAcademica;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import negocio.NegocioGenerico;

@Named(value = "ControlFicha")
@SessionScoped
public class ControlFicha implements Serializable{
    
    @EJB
    private NegocioGenerico neg;
    
    private FichaAcademica ficha;
    private ArrayList<String> asignaturasOfertadas = new ArrayList<>();
    
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
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            if(ficha.getCurso()<=0 || ficha.getCurso() >4) throw new Exception();
            neg.add(ficha);
            this.ficha = new FichaAcademica();
        } catch (EJBException e){
            this.ficha = new FichaAcademica();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ese expediente ya existe", null));
        } catch (Exception ex) {
            this.ficha = new FichaAcademica();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Introduzca un curso válido(de 1 a 4)", null));
        }
        return null;
    }
    
    public String addAsig(Asignatura asignatura){
        FacesContext ctx = FacesContext.getCurrentInstance();
        if(asignaturasOfertadas.contains(asignatura.getObservaciones()) && (asignatura.getCalificacion() <= 10) && (asignatura.getCalificacion() >= 0)){
            ficha.getAsignaturas().add(asignatura);
            neg.modify(ficha);
            this.ficha = new FichaAcademica();
            return "ficha.xhtml";
        }else{
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Introduzca una de las asignaturas ofertadas o una nota válida.", null));
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
        this.ficha = new FichaAcademica();
        return null;
    }

    public FichaAcademica getFicha() {
        return ficha;
    }

    public List<Serializable> getFichas() {
        return neg.getRows("getFichas");
    }  
}
