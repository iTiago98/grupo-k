package casopractico;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String apodo;
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    private TipoUsuario tipoUsuario;
    
    // TODO: Poner las relaciones con otras entidades y los getters y setters
    // Todavia no se pone nada, puede que se prescinda de esta entidad
}