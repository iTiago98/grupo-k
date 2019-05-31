package entidades;

import entidades.Nino;
import entidades.Socio;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-01T00:16:14")
@StaticMetamodel(Envio.class)
public class Envio_ { 

    public static volatile SingularAttribute<Envio, Date> fecha;
    public static volatile SingularAttribute<Envio, Socio> socio;
    public static volatile SingularAttribute<Envio, String> observaciones;
    public static volatile SingularAttribute<Envio, Long> id;
    public static volatile SingularAttribute<Envio, Nino> nino;

}