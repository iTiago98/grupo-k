package casopractico;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Socio implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String DNI;
    private String nombre;
    private String apellidos;
    private String dirección;
    private String telefono; // ¿Integer? ... No se van a realizar operaciones sobre el numero de todas formas
    private String correo;
    private String observaciones;
    
    // TODO: Poner las relaciones con otras entidades y los getters y setters
    // Todavia no se pone nada, puede que se prescinda de esta entidad
}