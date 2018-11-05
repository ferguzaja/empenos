/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import datos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Jahir
 */
public class VariblesempenoJpaController implements Serializable {

    public VariblesempenoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EmpenoFacilPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Variblesempeno variblesempeno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empeno empenoIdempeno = variblesempeno.getEmpenoIdempeno();
            if (empenoIdempeno != null) {
                empenoIdempeno = em.getReference(empenoIdempeno.getClass(), empenoIdempeno.getIdempeno());
                variblesempeno.setEmpenoIdempeno(empenoIdempeno);
            }
            em.persist(variblesempeno);
            if (empenoIdempeno != null) {
                empenoIdempeno.getVariblesempenoList().add(variblesempeno);
                empenoIdempeno = em.merge(empenoIdempeno);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Variblesempeno variblesempeno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Variblesempeno persistentVariblesempeno = em.find(Variblesempeno.class, variblesempeno.getIdvariblesempeno());
            Empeno empenoIdempenoOld = persistentVariblesempeno.getEmpenoIdempeno();
            Empeno empenoIdempenoNew = variblesempeno.getEmpenoIdempeno();
            if (empenoIdempenoNew != null) {
                empenoIdempenoNew = em.getReference(empenoIdempenoNew.getClass(), empenoIdempenoNew.getIdempeno());
                variblesempeno.setEmpenoIdempeno(empenoIdempenoNew);
            }
            variblesempeno = em.merge(variblesempeno);
            if (empenoIdempenoOld != null && !empenoIdempenoOld.equals(empenoIdempenoNew)) {
                empenoIdempenoOld.getVariblesempenoList().remove(variblesempeno);
                empenoIdempenoOld = em.merge(empenoIdempenoOld);
            }
            if (empenoIdempenoNew != null && !empenoIdempenoNew.equals(empenoIdempenoOld)) {
                empenoIdempenoNew.getVariblesempenoList().add(variblesempeno);
                empenoIdempenoNew = em.merge(empenoIdempenoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = variblesempeno.getIdvariblesempeno();
                if (findVariblesempeno(id) == null) {
                    throw new NonexistentEntityException("The variblesempeno with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Variblesempeno variblesempeno;
            try {
                variblesempeno = em.getReference(Variblesempeno.class, id);
                variblesempeno.getIdvariblesempeno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The variblesempeno with id " + id + " no longer exists.", enfe);
            }
            Empeno empenoIdempeno = variblesempeno.getEmpenoIdempeno();
            if (empenoIdempeno != null) {
                empenoIdempeno.getVariblesempenoList().remove(variblesempeno);
                empenoIdempeno = em.merge(empenoIdempeno);
            }
            em.remove(variblesempeno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Variblesempeno> findVariblesempenoEntities() {
        return findVariblesempenoEntities(true, -1, -1);
    }

    public List<Variblesempeno> findVariblesempenoEntities(int maxResults, int firstResult) {
        return findVariblesempenoEntities(false, maxResults, firstResult);
    }

    private List<Variblesempeno> findVariblesempenoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Variblesempeno.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Variblesempeno findVariblesempeno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Variblesempeno.class, id);
        } finally {
            em.close();
        }
    }

    public int getVariblesempenoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Variblesempeno> rt = cq.from(Variblesempeno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
