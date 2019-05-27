package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Asignatura implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer calificacion;
    private String observaciones;

    public Asignatura(String obs, int i) {
        this.calificacion = i;
        this.observaciones = obs;
    }
    
    public Asignatura(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
      /**** NUEVO ****/
    @Override
    public boolean equals(Object o){
        if(this==o)
            return true;
        if(!(o instanceof Asignatura))
            return false;
        
        Asignatura s = (Asignatura)o;
        return super.equals(s)
            && this.id.equals(s.id);
    } 
    
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

}
