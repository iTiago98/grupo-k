package entidades;

import entidades.Asignatura;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-01T00:16:14")
@StaticMetamodel(FichaAcademica.class)
public class FichaAcademica_ { 

    public static volatile SingularAttribute<FichaAcademica, String> fechaMatriculacion;
    public static volatile SingularAttribute<FichaAcademica, Integer> curso;
    public static volatile SingularAttribute<FichaAcademica, Long> id;
    public static volatile SetAttribute<FichaAcademica, Asignatura> asignaturas;

}