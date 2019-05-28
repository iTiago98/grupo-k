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
    private Integer curso;
    private Date matriculacion;
    private Asignatura asignatura;
    private ArrayList<FichaAcademica> fichas;
    
    public ControlFicha() throws ParseException{
        
        ficha = new FichaAcademica();
        curso = -1;
        matriculacion = new Date();
        asignatura = new Asignatura();
        fichas = new ArrayList<>();
        
        String date = "07-12-2015";
        Set<Asignatura> asignaturas = new HashSet<Asignatura>();
        asignaturas.add(new Asignatura("Matematicas",10));
        asignaturas.add(new Asignatura("Ciencias",8));
        asignaturas.add(new Asignatura("Lengua",7));
        FichaAcademica Luis = new FichaAcademica(1,date,asignaturas);
        Luis.setId(Long.MAX_VALUE);
        fichas.add(Luis);
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

    public Integer getCurso() {
        return curso;
    }

    public Date getMatriculacion() {
        return matriculacion;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public List<Serializable> getFichas() {
        return neg.getRows("getFichas");
    }

    public void setFicha(FichaAcademica ficha) {
        this.ficha = ficha;
    }

    public void setCurso(Integer curso) {
        this.curso = curso;
    }

    public void setMatriculacion(Date matriculacion) {
        this.matriculacion = matriculacion;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public void setFichas(ArrayList<FichaAcademica> fichas) {
        this.fichas = fichas;
    }
    
}
