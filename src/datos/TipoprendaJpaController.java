/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import datos.exceptions.IllegalOrphanException;
import datos.exceptions.NonexistentEntityException;
import datos.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Jahir
 */
public class TipoprendaJpaController implements Serializable {

    public TipoprendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipoprenda tipoprenda) throws PreexistingEntityException, Exception {
        if (tipoprenda.getPrendaList() == null) {
            tipoprenda.setPrendaList(new ArrayList<Prenda>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Prenda> attachedPrendaList = new ArrayList<Prenda>();
            for (Prenda prendaListPrendaToAttach : tipoprenda.getPrendaList()) {
                prendaListPrendaToAttach = em.getReference(prendaListPrendaToAttach.getClass(), prendaListPrendaToAttach.getIdprenda());
                attachedPrendaList.add(prendaListPrendaToAttach);
            }
            tipoprenda.setPrendaList(attachedPrendaList);
            em.persist(tipoprenda);
            for (Prenda prendaListPrenda : tipoprenda.getPrendaList()) {
                Tipoprenda oldTipoprendaIdtipoprendaOfPrendaListPrenda = prendaListPrenda.getTipoprendaIdtipoprenda();
                prendaListPrenda.setTipoprendaIdtipoprenda(tipoprenda);
                prendaListPrenda = em.merge(prendaListPrenda);
                if (oldTipoprendaIdtipoprendaOfPrendaListPrenda != null) {
                    oldTipoprendaIdtipoprendaOfPrendaListPrenda.getPrendaList().remove(prendaListPrenda);
                    oldTipoprendaIdtipoprendaOfPrendaListPrenda = em.merge(oldTipoprendaIdtipoprendaOfPrendaListPrenda);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoprenda(tipoprenda.getIdtipoprenda()) != null) {
                throw new PreexistingEntityException("Tipoprenda " + tipoprenda + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipoprenda tipoprenda) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoprenda persistentTipoprenda = em.find(Tipoprenda.class, tipoprenda.getIdtipoprenda());
            List<Prenda> prendaListOld = persistentTipoprenda.getPrendaList();
            List<Prenda> prendaListNew = tipoprenda.getPrendaList();
            List<String> illegalOrphanMessages = null;
            for (Prenda prendaListOldPrenda : prendaListOld) {
                if (!prendaListNew.contains(prendaListOldPrenda)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Prenda " + prendaListOldPrenda + " since its tipoprendaIdtipoprenda field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Prenda> attachedPrendaListNew = new ArrayList<Prenda>();
            for (Prenda prendaListNewPrendaToAttach : prendaListNew) {
                prendaListNewPrendaToAttach = em.getReference(prendaListNewPrendaToAttach.getClass(), prendaListNewPrendaToAttach.getIdprenda());
                attachedPrendaListNew.add(prendaListNewPrendaToAttach);
            }
            prendaListNew = attachedPrendaListNew;
            tipoprenda.setPrendaList(prendaListNew);
            tipoprenda = em.merge(tipoprenda);
            for (Prenda prendaListNewPrenda : prendaListNew) {
                if (!prendaListOld.contains(prendaListNewPrenda)) {
                    Tipoprenda oldTipoprendaIdtipoprendaOfPrendaListNewPrenda = prendaListNewPrenda.getTipoprendaIdtipoprenda();
                    prendaListNewPrenda.setTipoprendaIdtipoprenda(tipoprenda);
                    prendaListNewPrenda = em.merge(prendaListNewPrenda);
                    if (oldTipoprendaIdtipoprendaOfPrendaListNewPrenda != null && !oldTipoprendaIdtipoprendaOfPrendaListNewPrenda.equals(tipoprenda)) {
                        oldTipoprendaIdtipoprendaOfPrendaListNewPrenda.getPrendaList().remove(prendaListNewPrenda);
                        oldTipoprendaIdtipoprendaOfPrendaListNewPrenda = em.merge(oldTipoprendaIdtipoprendaOfPrendaListNewPrenda);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoprenda.getIdtipoprenda();
                if (findTipoprenda(id) == null) {
                    throw new NonexistentEntityException("The tipoprenda with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoprenda tipoprenda;
            try {
                tipoprenda = em.getReference(Tipoprenda.class, id);
                tipoprenda.getIdtipoprenda();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoprenda with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Prenda> prendaListOrphanCheck = tipoprenda.getPrendaList();
            for (Prenda prendaListOrphanCheckPrenda : prendaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tipoprenda (" + tipoprenda + ") cannot be destroyed since the Prenda " + prendaListOrphanCheckPrenda + " in its prendaList field has a non-nullable tipoprendaIdtipoprenda field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoprenda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipoprenda> findTipoprendaEntities() {
        return findTipoprendaEntities(true, -1, -1);
    }

    public List<Tipoprenda> findTipoprendaEntities(int maxResults, int firstResult) {
        return findTipoprendaEntities(false, maxResults, firstResult);
    }

    private List<Tipoprenda> findTipoprendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipoprenda.class));
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

    public Tipoprenda findTipoprenda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipoprenda.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoprendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipoprenda> rt = cq.from(Tipoprenda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
