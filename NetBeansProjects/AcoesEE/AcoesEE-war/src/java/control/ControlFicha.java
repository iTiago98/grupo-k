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
import javax.inject.Named;
import javax.persistence.NamedQuery;
import negocio.NegocioGenerico;

@Named(value = "ControlFicha")
@SessionScoped
public class ControlFicha implements Serializable{
    
    @EJB
    private NegocioGenerico neg;
    
    private FichaAcademica ficha;
    
    public ControlFicha() throws ParseException{
        ficha = new FichaAcademica();
    }
    
    public String addFicha(){
        neg.add(ficha);
        ficha.getAsignaturas().clear();
        return "ficha.xhtml";
    }
    
    public String addAsig(Asignatura asignatura){
        ficha.getAsignaturas().add(asignatura);
        neg.modify(ficha);
        return "ficha.xhtml";
    }
    
    public String goModifyFicha(FichaAcademica f) {
        this.ficha = f;
        return "fichasModificar.xhtml";
    }
    
    public String removeFicha(FichaAcademica ficha){
        neg.remove(this.ficha);
        ficha.getAsignaturas().clear();
        return null;
    }

    public FichaAcademica getFicha() {
        return ficha;
    }

    public List<Serializable> getFichas() {
        return neg.getRows("getFichas");
    }
    
}
