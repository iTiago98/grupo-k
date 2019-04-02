package casopractico;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class CalificacionId implements Serializable {
    private Long id; //idAsignatura
    private FichaAcademica fichaAcademica;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FichaAcademica getFichaAcademica() {
        return fichaAcademica;
    }

    public void setFichaAcademica(FichaAcademica fichaAcademica) {
        this.fichaAcademica = fichaAcademica;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.fichaAcademica);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CalificacionId other = (CalificacionId) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.fichaAcademica, other.fichaAcademica)) {
            return false;
        }
        return true;
    }
    
}
