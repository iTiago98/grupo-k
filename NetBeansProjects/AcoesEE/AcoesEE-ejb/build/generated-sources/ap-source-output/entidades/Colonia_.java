package entidades;

import entidades.Nino;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-21T12:27:31")
@StaticMetamodel(Colonia.class)
public class Colonia_ { 

    public static volatile SingularAttribute<Colonia, String> descripcion;
    public static volatile SingularAttribute<Colonia, String> localizacion;
    public static volatile SetAttribute<Colonia, Nino> ninos;
    public static volatile SingularAttribute<Colonia, Long> id;
    public static volatile SingularAttribute<Colonia, String> nombre;

}