package casopractico;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Nino implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nombre;
    private String apellido;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    private String observaciones;
    private Integer prioridad; // TODO: Ver que es esto en realidad
    @OneToMany(mappedBy = "nino")
    private Set<Envio> envio;
    @ManyToOne
    private Colonia colonia;
    @ManyToOne
    private Proyecto proyecto;
    
    /*
        @ManyToOne
        private Socio socio;
        @OneToOne
        private Socio apadrinadoPor;
    */
    
    // TODO: Poner las relaciones con otras entidades y los getters y setters
}
