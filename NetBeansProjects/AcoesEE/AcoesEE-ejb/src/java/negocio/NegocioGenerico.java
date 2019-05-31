package negocio;

import java.util.List;
import java.io.Serializable;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class NegocioGenerico {
    @PersistenceContext(unitName = "AcoesEE-entidadesPU")
    private EntityManager em;

    // lol generics
    
    public <T extends Serializable> void add(T r) {
        em.persist(r);
    }
    
    public <T extends Serializable> void remove(T r) {
        em.remove(em.merge(r));
    }
    
    public <T extends Serializable> void modify(T r) {
        em.merge(r);
    }
    
    public <T extends Serializable> List<T> getRows(String query) {
        return em.createNamedQuery(query).getResultList();
    }
    
    public <T extends Serializable> List<T> getRowsCustomQuery(String query) {
        return em.createQuery(query).getResultList();
    }
    
    public <T extends Serializable> List<T> getRowById(String res, int id) {
        return em.createQuery("SELECT o FROM " + res + " o WHERE o.id = " + id).getResultList();
    }
}
