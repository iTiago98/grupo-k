package entidades;

import entidades.Nino;
import entidades.Socio;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-21T12:27:31")
@StaticMetamodel(Envio.class)
public class Envio_ { 

    public static volatile SingularAttribute<Envio, Socio> socio;
    public static volatile SingularAttribute<Envio, String> observaciones;
    public static volatile SingularAttribute<Envio, Long> id;
    public static volatile SingularAttribute<Envio, Nino> nino;

}