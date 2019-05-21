package negocio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class NegocioImpl implements Negocio {

    @PersistenceContext(unitName = "AcoesEE-entidadesPU")
    private EntityManager em;

}
