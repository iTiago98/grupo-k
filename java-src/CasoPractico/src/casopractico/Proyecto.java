package casopractico;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Proyecto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //idCentro
    
    private String nombre;
    @Embedded
    private Localizacion localizacion;
    private Integer capacidad;
            
    @OneToMany(mappedBy = "proyecto")
    private Set<Nino> ninos;
}