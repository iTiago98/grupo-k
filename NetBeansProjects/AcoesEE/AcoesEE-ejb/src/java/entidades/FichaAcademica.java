package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class FichaAcademica implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer curso;
    @Column(nullable = false)
    //@Temporal(TemporalType.DATE)
    private String fechaMatriculacion;
    @OneToMany
    private Set<Asignatura> asignaturas;

    public FichaAcademica(int i, String date, Set<Asignatura> asignaturas) {
        this.curso = i;
        this.fechaMatriculacion = date;
        this.asignaturas = new HashSet<>();
        this.asignaturas.addAll(asignaturas);
    }
    
    public FichaAcademica() {
        this.asignaturas = new HashSet<>();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCurso() {
        return curso;
    }

    public void setCurso(Integer curso) {
        this.curso = curso;
    }

    public String getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(String fechaMatriculacion) {
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public Set<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(Set<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }
    
    public String asignaturas(){
        String msg = "";
        if(!asignaturas.isEmpty()){
            for(Asignatura asig : asignaturas){
                msg += "Asignatura: " + asig.getObservaciones() + " - Nota: " + asig.getCalificacion()+" || ";
            }
            msg = msg.substring(0,msg.length()-3);
        }
        return msg;
    }
    
      /**** NUEVO ****/
    @Override
    public boolean equals(Object o){
        if(this==o)
            return true;
        if(!(o instanceof FichaAcademica))
            return false;
        
        FichaAcademica s = (FichaAcademica)o;
        return super.equals(s)
            && this.id.equals(s.id);
    } 
    
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

}
