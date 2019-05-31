package entidades;

import entidades.Beca;
import entidades.Nino;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-01T00:16:14")
@StaticMetamodel(Beneficiario.class)
public class Beneficiario_ { 

    public static volatile SingularAttribute<Beneficiario, Integer> ano;
    public static volatile SingularAttribute<Beneficiario, String> observaciones;
    public static volatile SingularAttribute<Beneficiario, Beca> beca;
    public static volatile SingularAttribute<Beneficiario, Nino> nino;

}