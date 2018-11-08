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
public class PagoJpaController implements Serializable {

    public PagoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EmpenoFacilPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pago pago) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empeno empenoIdempeno = pago.getEmpenoIdempeno();
//            if (empenoIdempeno != null) {
//                empenoIdempeno = em.getReference(empenoIdempeno.getClass(), empenoIdempeno.getIdempeno());
//                pago.setEmpenoIdempeno(empenoIdempeno);
//            }
//            em.persist(pago);
//            if (empenoIdempeno != null) {
//                empenoIdempeno.getPagoList().add(pago);
//                empenoIdempeno = em.merge(empenoIdempeno);
//            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pago pago) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pago persistentPago = em.find(Pago.class, pago.getIdpago());
            Empeno empenoIdempenoOld = persistentPago.getEmpenoIdempeno();
            Empeno empenoIdempenoNew = pago.getEmpenoIdempeno();
            if (empenoIdempenoNew != null) {
                empenoIdempenoNew = em.getReference(empenoIdempenoNew.getClass(), empenoIdempenoNew.getIdempeno());
                pago.setEmpenoIdempeno(empenoIdempenoNew);
            }
            pago = em.merge(pago);
            if (empenoIdempenoOld != null && !empenoIdempenoOld.equals(empenoIdempenoNew)) {
                empenoIdempenoOld.getPagoList().remove(pago);
                empenoIdempenoOld = em.merge(empenoIdempenoOld);
            }
            if (empenoIdempenoNew != null && !empenoIdempenoNew.equals(empenoIdempenoOld)) {
                empenoIdempenoNew.getPagoList().add(pago);
                empenoIdempenoNew = em.merge(empenoIdempenoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pago.getIdpago();
                if (findPago(id) == null) {
                    throw new NonexistentEntityException("The pago with id " + id + " no longer exists.");
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
            Pago pago;
            try {
                pago = em.getReference(Pago.class, id);
                pago.getIdpago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pago with id " + id + " no longer exists.", enfe);
            }
            Empeno empenoIdempeno = pago.getEmpenoIdempeno();
            if (empenoIdempeno != null) {
                empenoIdempeno.getPagoList().remove(pago);
                empenoIdempeno = em.merge(empenoIdempeno);
            }
            em.remove(pago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pago> findPagoEntities() {
        return findPagoEntities(true, -1, -1);
    }

    public List<Pago> findPagoEntities(int maxResults, int firstResult) {
        return findPagoEntities(false, maxResults, firstResult);
    }

    private List<Pago> findPagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pago.class));
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

    public Pago findPago(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pago.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pago> rt = cq.from(Pago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
