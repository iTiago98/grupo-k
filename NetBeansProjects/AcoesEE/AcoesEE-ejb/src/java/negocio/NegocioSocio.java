package negocio;

import entidades.Socio;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class NegocioSocio {
    @PersistenceContext(unitName = "AcoesEE-entidadesPU")
    private EntityManager em;

    public void addSocio(Socio s) /*throws DatosDuplicadosException*/ {
        //Usuario user = this.em.find(Usuario.class, u.getId());
        //if(user != null) throw new DatosDuplicadosException("Ya existe un usuario con el mismo ID");
        
        em.persist(s);
    }
    
    public void removeSocio(Socio s) {
        em.remove(em.merge(s));
    }
    
    public void modifySocio(Socio s) {
        em.merge(s);
    }
    
    public List<Socio> getSocios() {
        return em.createNamedQuery("getSocios").getResultList();
    }
}
