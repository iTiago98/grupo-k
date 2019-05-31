package entidades;

import entidades.Colonia;
import entidades.Donacion;
import entidades.Envio;
import entidades.FichaAcademica;
import entidades.Proyecto;
import entidades.Socio;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-01T00:16:14")
@StaticMetamodel(Nino.class)
public class Nino_ { 

    public static volatile SingularAttribute<Nino, String> apellidos;
    public static volatile SetAttribute<Nino, FichaAcademica> fichasAcademicas;
    public static volatile SingularAttribute<Nino, Date> fechaNacimiento;
    public static volatile SingularAttribute<Nino, Socio> socio;
    public static volatile SingularAttribute<Nino, Proyecto> proyecto;
    public static volatile SingularAttribute<Nino, String> nombre;
    public static volatile SingularAttribute<Nino, Integer> prioridad;
    public static volatile SingularAttribute<Nino, Colonia> colonia;
    public static volatile SetAttribute<Nino, Envio> envio;
    public static volatile SetAttribute<Nino, Donacion> donaciones;
    public static volatile SingularAttribute<Nino, String> observaciones;
    public static volatile SingularAttribute<Nino, Long> id;
    public static volatile SingularAttribute<Nino, Character> sexo;

}