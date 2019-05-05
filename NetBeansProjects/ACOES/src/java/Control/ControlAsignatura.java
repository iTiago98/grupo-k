package Control;

import JPA.Asignatura;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named(value = "ControlAsignatura")
@SessionScoped
public class ControlAsignatura implements Serializable{
    private String observaciones;
    private Integer calificacion;
    
    public ControlAsignatura(){
        observaciones = "Ninguna";
        calificacion = 0;
    }
    
    public Asignatura getAsignatura(){
        return new Asignatura(observaciones,calificacion);
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }
    
    
}
