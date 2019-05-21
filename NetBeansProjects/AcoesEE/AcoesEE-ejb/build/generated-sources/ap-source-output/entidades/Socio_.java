package entidades;

import entidades.Donacion;
import entidades.Envio;
import entidades.Nino;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-21T12:27:31")
@StaticMetamodel(Socio.class)
public class Socio_ { 

    public static volatile SingularAttribute<Socio, String> apellidos;
    public static volatile SetAttribute<Socio, Donacion> donaciones;
    public static volatile SingularAttribute<Socio, String> correo;
    public static volatile SingularAttribute<Socio, String> direccion;
    public static volatile SingularAttribute<Socio, String> observaciones;
    public static volatile SetAttribute<Socio, Nino> ninos;
    public static volatile SingularAttribute<Socio, Long> id;
    public static volatile SingularAttribute<Socio, String> telefono;
    public static volatile SingularAttribute<Socio, String> nombre;
    public static volatile SingularAttribute<Socio, String> DNI;
    public static volatile SetAttribute<Socio, Envio> envios;

}