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
import javax.persistence.Persistence;

/**
 *
 * @author Jahir
 */
public class TipoempleadoJpaController implements Serializable {

    public TipoempleadoJpaController() {
         this.emf = Persistence.createEntityManagerFactory("EmpenoFacilPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipoempleado tipoempleado) throws PreexistingEntityException, Exception {
        if (tipoempleado.getEmpleadoList() == null) {
            tipoempleado.setEmpleadoList(new ArrayList<Empleado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Empleado> attachedEmpleadoList = new ArrayList<Empleado>();
            for (Empleado empleadoListEmpleadoToAttach : tipoempleado.getEmpleadoList()) {
                empleadoListEmpleadoToAttach = em.getReference(empleadoListEmpleadoToAttach.getClass(), empleadoListEmpleadoToAttach.getIdempleado());
                attachedEmpleadoList.add(empleadoListEmpleadoToAttach);
            }
            tipoempleado.setEmpleadoList(attachedEmpleadoList);
            em.persist(tipoempleado);
            for (Empleado empleadoListEmpleado : tipoempleado.getEmpleadoList()) {
                Tipoempleado oldTipoempleadoIdtipoempleadoOfEmpleadoListEmpleado = empleadoListEmpleado.getTipoempleadoIdtipoempleado();
                empleadoListEmpleado.setTipoempleadoIdtipoempleado(tipoempleado);
                empleadoListEmpleado = em.merge(empleadoListEmpleado);
                if (oldTipoempleadoIdtipoempleadoOfEmpleadoListEmpleado != null) {
                    oldTipoempleadoIdtipoempleadoOfEmpleadoListEmpleado.getEmpleadoList().remove(empleadoListEmpleado);
                    oldTipoempleadoIdtipoempleadoOfEmpleadoListEmpleado = em.merge(oldTipoempleadoIdtipoempleadoOfEmpleadoListEmpleado);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoempleado(tipoempleado.getIdtipoempleado()) != null) {
                throw new PreexistingEntityException("Tipoempleado " + tipoempleado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipoempleado tipoempleado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoempleado persistentTipoempleado = em.find(Tipoempleado.class, tipoempleado.getIdtipoempleado());
            List<Empleado> empleadoListOld = persistentTipoempleado.getEmpleadoList();
            List<Empleado> empleadoListNew = tipoempleado.getEmpleadoList();
            List<String> illegalOrphanMessages = null;
            for (Empleado empleadoListOldEmpleado : empleadoListOld) {
                if (!empleadoListNew.contains(empleadoListOldEmpleado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empleado " + empleadoListOldEmpleado + " since its tipoempleadoIdtipoempleado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Empleado> attachedEmpleadoListNew = new ArrayList<Empleado>();
            for (Empleado empleadoListNewEmpleadoToAttach : empleadoListNew) {
                empleadoListNewEmpleadoToAttach = em.getReference(empleadoListNewEmpleadoToAttach.getClass(), empleadoListNewEmpleadoToAttach.getIdempleado());
                attachedEmpleadoListNew.add(empleadoListNewEmpleadoToAttach);
            }
            empleadoListNew = attachedEmpleadoListNew;
            tipoempleado.setEmpleadoList(empleadoListNew);
            tipoempleado = em.merge(tipoempleado);
            for (Empleado empleadoListNewEmpleado : empleadoListNew) {
                if (!empleadoListOld.contains(empleadoListNewEmpleado)) {
                    Tipoempleado oldTipoempleadoIdtipoempleadoOfEmpleadoListNewEmpleado = empleadoListNewEmpleado.getTipoempleadoIdtipoempleado();
                    empleadoListNewEmpleado.setTipoempleadoIdtipoempleado(tipoempleado);
                    empleadoListNewEmpleado = em.merge(empleadoListNewEmpleado);
                    if (oldTipoempleadoIdtipoempleadoOfEmpleadoListNewEmpleado != null && !oldTipoempleadoIdtipoempleadoOfEmpleadoListNewEmpleado.equals(tipoempleado)) {
                        oldTipoempleadoIdtipoempleadoOfEmpleadoListNewEmpleado.getEmpleadoList().remove(empleadoListNewEmpleado);
                        oldTipoempleadoIdtipoempleadoOfEmpleadoListNewEmpleado = em.merge(oldTipoempleadoIdtipoempleadoOfEmpleadoListNewEmpleado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoempleado.getIdtipoempleado();
                if (findTipoempleado(id) == null) {
                    throw new NonexistentEntityException("The tipoempleado with id " + id + " no longer exists.");
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
            Tipoempleado tipoempleado;
            try {
                tipoempleado = em.getReference(Tipoempleado.class, id);
                tipoempleado.getIdtipoempleado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoempleado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Empleado> empleadoListOrphanCheck = tipoempleado.getEmpleadoList();
            for (Empleado empleadoListOrphanCheckEmpleado : empleadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tipoempleado (" + tipoempleado + ") cannot be destroyed since the Empleado " + empleadoListOrphanCheckEmpleado + " in its empleadoList field has a non-nullable tipoempleadoIdtipoempleado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoempleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipoempleado> findTipoempleadoEntities() {
        return findTipoempleadoEntities(true, -1, -1);
    }

    public List<Tipoempleado> findTipoempleadoEntities(int maxResults, int firstResult) {
        return findTipoempleadoEntities(false, maxResults, firstResult);
    }

    private List<Tipoempleado> findTipoempleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipoempleado.class));
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

    public Tipoempleado findTipoempleado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipoempleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoempleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipoempleado> rt = cq.from(Tipoempleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
