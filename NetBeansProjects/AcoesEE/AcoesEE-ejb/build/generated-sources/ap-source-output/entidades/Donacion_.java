package entidades;

import entidades.Nino;
import entidades.Socio;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-21T12:27:31")
@StaticMetamodel(Donacion.class)
public class Donacion_ { 

    public static volatile SingularAttribute<Donacion, Date> fecha;
    public static volatile SingularAttribute<Donacion, Socio> socio;
    public static volatile SingularAttribute<Donacion, String> observaciones;
    public static volatile SingularAttribute<Donacion, Double> importe;
    public static volatile SingularAttribute<Donacion, Nino> nino;

}