/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import datos.exceptions.IllegalOrphanException;
import datos.exceptions.NonexistentEntityException;
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
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController() {
         this.emf = Persistence.createEntityManagerFactory("EmpenoFacilPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) {
        if (empleado.getVentaList() == null) {
            empleado.setVentaList(new ArrayList<Venta>());
        }
        if (empleado.getRemateList() == null) {
            empleado.setRemateList(new ArrayList<Remate>());
        }
        if (empleado.getEmpenoList() == null) {
            empleado.setEmpenoList(new ArrayList<Empeno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoempleado tipoempleadoIdtipoempleado = empleado.getTipoempleadoIdtipoempleado();
            if (tipoempleadoIdtipoempleado != null) {
                tipoempleadoIdtipoempleado = em.getReference(tipoempleadoIdtipoempleado.getClass(), tipoempleadoIdtipoempleado.getIdtipoempleado());
                empleado.setTipoempleadoIdtipoempleado(tipoempleadoIdtipoempleado);
            }
            List<Venta> attachedVentaList = new ArrayList<Venta>();
            for (Venta ventaListVentaToAttach : empleado.getVentaList()) {
                ventaListVentaToAttach = em.getReference(ventaListVentaToAttach.getClass(), ventaListVentaToAttach.getIdventa());
                attachedVentaList.add(ventaListVentaToAttach);
            }
            empleado.setVentaList(attachedVentaList);
            List<Remate> attachedRemateList = new ArrayList<Remate>();
            for (Remate remateListRemateToAttach : empleado.getRemateList()) {
                remateListRemateToAttach = em.getReference(remateListRemateToAttach.getClass(), remateListRemateToAttach.getIdremate());
                attachedRemateList.add(remateListRemateToAttach);
            }
            empleado.setRemateList(attachedRemateList);
            List<Empeno> attachedEmpenoList = new ArrayList<Empeno>();
            for (Empeno empenoListEmpenoToAttach : empleado.getEmpenoList()) {
                empenoListEmpenoToAttach = em.getReference(empenoListEmpenoToAttach.getClass(), empenoListEmpenoToAttach.getIdempeno());
                attachedEmpenoList.add(empenoListEmpenoToAttach);
            }
            empleado.setEmpenoList(attachedEmpenoList);
            em.persist(empleado);
            if (tipoempleadoIdtipoempleado != null) {
                tipoempleadoIdtipoempleado.getEmpleadoList().add(empleado);
                tipoempleadoIdtipoempleado = em.merge(tipoempleadoIdtipoempleado);
            }
            for (Venta ventaListVenta : empleado.getVentaList()) {
                Empleado oldEmpleadoidEmpleadoOfVentaListVenta = ventaListVenta.getEmpleadoidEmpleado();
                ventaListVenta.setEmpleadoidEmpleado(empleado);
                ventaListVenta = em.merge(ventaListVenta);
                if (oldEmpleadoidEmpleadoOfVentaListVenta != null) {
                    oldEmpleadoidEmpleadoOfVentaListVenta.getVentaList().remove(ventaListVenta);
                    oldEmpleadoidEmpleadoOfVentaListVenta = em.merge(oldEmpleadoidEmpleadoOfVentaListVenta);
                }
            }
            for (Remate remateListRemate : empleado.getRemateList()) {
                Empleado oldEmpleadoIdempleadoOfRemateListRemate = remateListRemate.getEmpleadoIdempleado();
                remateListRemate.setEmpleadoIdempleado(empleado);
                remateListRemate = em.merge(remateListRemate);
                if (oldEmpleadoIdempleadoOfRemateListRemate != null) {
                    oldEmpleadoIdempleadoOfRemateListRemate.getRemateList().remove(remateListRemate);
                    oldEmpleadoIdempleadoOfRemateListRemate = em.merge(oldEmpleadoIdempleadoOfRemateListRemate);
                }
            }
            for (Empeno empenoListEmpeno : empleado.getEmpenoList()) {
                Empleado oldEmpleadoidEmpleadoOfEmpenoListEmpeno = empenoListEmpeno.getEmpleadoidEmpleado();
                empenoListEmpeno.setEmpleadoidEmpleado(empleado);
                empenoListEmpeno = em.merge(empenoListEmpeno);
                if (oldEmpleadoidEmpleadoOfEmpenoListEmpeno != null) {
                    oldEmpleadoidEmpleadoOfEmpenoListEmpeno.getEmpenoList().remove(empenoListEmpeno);
                    oldEmpleadoidEmpleadoOfEmpenoListEmpeno = em.merge(oldEmpleadoidEmpleadoOfEmpenoListEmpeno);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getIdempleado());
            Tipoempleado tipoempleadoIdtipoempleadoOld = persistentEmpleado.getTipoempleadoIdtipoempleado();
            Tipoempleado tipoempleadoIdtipoempleadoNew = empleado.getTipoempleadoIdtipoempleado();
            List<Venta> ventaListOld = persistentEmpleado.getVentaList();
            List<Venta> ventaListNew = empleado.getVentaList();
            List<Remate> remateListOld = persistentEmpleado.getRemateList();
            List<Remate> remateListNew = empleado.getRemateList();
            List<Empeno> empenoListOld = persistentEmpleado.getEmpenoList();
            List<Empeno> empenoListNew = empleado.getEmpenoList();
            List<String> illegalOrphanMessages = null;
            for (Venta ventaListOldVenta : ventaListOld) {
                if (!ventaListNew.contains(ventaListOldVenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Venta " + ventaListOldVenta + " since its empleadoidEmpleado field is not nullable.");
                }
            }
            for (Remate remateListOldRemate : remateListOld) {
                if (!remateListNew.contains(remateListOldRemate)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Remate " + remateListOldRemate + " since its empleadoIdempleado field is not nullable.");
                }
            }
            for (Empeno empenoListOldEmpeno : empenoListOld) {
                if (!empenoListNew.contains(empenoListOldEmpeno)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empeno " + empenoListOldEmpeno + " since its empleadoidEmpleado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tipoempleadoIdtipoempleadoNew != null) {
                tipoempleadoIdtipoempleadoNew = em.getReference(tipoempleadoIdtipoempleadoNew.getClass(), tipoempleadoIdtipoempleadoNew.getIdtipoempleado());
                empleado.setTipoempleadoIdtipoempleado(tipoempleadoIdtipoempleadoNew);
            }
            List<Venta> attachedVentaListNew = new ArrayList<Venta>();
            for (Venta ventaListNewVentaToAttach : ventaListNew) {
                ventaListNewVentaToAttach = em.getReference(ventaListNewVentaToAttach.getClass(), ventaListNewVentaToAttach.getIdventa());
                attachedVentaListNew.add(ventaListNewVentaToAttach);
            }
            ventaListNew = attachedVentaListNew;
            empleado.setVentaList(ventaListNew);
            List<Remate> attachedRemateListNew = new ArrayList<Remate>();
            for (Remate remateListNewRemateToAttach : remateListNew) {
                remateListNewRemateToAttach = em.getReference(remateListNewRemateToAttach.getClass(), remateListNewRemateToAttach.getIdremate());
                attachedRemateListNew.add(remateListNewRemateToAttach);
            }
            remateListNew = attachedRemateListNew;
            empleado.setRemateList(remateListNew);
            List<Empeno> attachedEmpenoListNew = new ArrayList<Empeno>();
            for (Empeno empenoListNewEmpenoToAttach : empenoListNew) {
                empenoListNewEmpenoToAttach = em.getReference(empenoListNewEmpenoToAttach.getClass(), empenoListNewEmpenoToAttach.getIdempeno());
                attachedEmpenoListNew.add(empenoListNewEmpenoToAttach);
            }
            empenoListNew = attachedEmpenoListNew;
            empleado.setEmpenoList(empenoListNew);
            empleado = em.merge(empleado);
            if (tipoempleadoIdtipoempleadoOld != null && !tipoempleadoIdtipoempleadoOld.equals(tipoempleadoIdtipoempleadoNew)) {
                tipoempleadoIdtipoempleadoOld.getEmpleadoList().remove(empleado);
                tipoempleadoIdtipoempleadoOld = em.merge(tipoempleadoIdtipoempleadoOld);
            }
            if (tipoempleadoIdtipoempleadoNew != null && !tipoempleadoIdtipoempleadoNew.equals(tipoempleadoIdtipoempleadoOld)) {
                tipoempleadoIdtipoempleadoNew.getEmpleadoList().add(empleado);
                tipoempleadoIdtipoempleadoNew = em.merge(tipoempleadoIdtipoempleadoNew);
            }
            for (Venta ventaListNewVenta : ventaListNew) {
                if (!ventaListOld.contains(ventaListNewVenta)) {
                    Empleado oldEmpleadoidEmpleadoOfVentaListNewVenta = ventaListNewVenta.getEmpleadoidEmpleado();
                    ventaListNewVenta.setEmpleadoidEmpleado(empleado);
                    ventaListNewVenta = em.merge(ventaListNewVenta);
                    if (oldEmpleadoidEmpleadoOfVentaListNewVenta != null && !oldEmpleadoidEmpleadoOfVentaListNewVenta.equals(empleado)) {
                        oldEmpleadoidEmpleadoOfVentaListNewVenta.getVentaList().remove(ventaListNewVenta);
                        oldEmpleadoidEmpleadoOfVentaListNewVenta = em.merge(oldEmpleadoidEmpleadoOfVentaListNewVenta);
                    }
                }
            }
            for (Remate remateListNewRemate : remateListNew) {
                if (!remateListOld.contains(remateListNewRemate)) {
                    Empleado oldEmpleadoIdempleadoOfRemateListNewRemate = remateListNewRemate.getEmpleadoIdempleado();
                    remateListNewRemate.setEmpleadoIdempleado(empleado);
                    remateListNewRemate = em.merge(remateListNewRemate);
                    if (oldEmpleadoIdempleadoOfRemateListNewRemate != null && !oldEmpleadoIdempleadoOfRemateListNewRemate.equals(empleado)) {
                        oldEmpleadoIdempleadoOfRemateListNewRemate.getRemateList().remove(remateListNewRemate);
                        oldEmpleadoIdempleadoOfRemateListNewRemate = em.merge(oldEmpleadoIdempleadoOfRemateListNewRemate);
                    }
                }
            }
            for (Empeno empenoListNewEmpeno : empenoListNew) {
                if (!empenoListOld.contains(empenoListNewEmpeno)) {
                    Empleado oldEmpleadoidEmpleadoOfEmpenoListNewEmpeno = empenoListNewEmpeno.getEmpleadoidEmpleado();
                    empenoListNewEmpeno.setEmpleadoidEmpleado(empleado);
                    empenoListNewEmpeno = em.merge(empenoListNewEmpeno);
                    if (oldEmpleadoidEmpleadoOfEmpenoListNewEmpeno != null && !oldEmpleadoidEmpleadoOfEmpenoListNewEmpeno.equals(empleado)) {
                        oldEmpleadoidEmpleadoOfEmpenoListNewEmpeno.getEmpenoList().remove(empenoListNewEmpeno);
                        oldEmpleadoidEmpleadoOfEmpenoListNewEmpeno = em.merge(oldEmpleadoidEmpleadoOfEmpenoListNewEmpeno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empleado.getIdempleado();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
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
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getIdempleado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Venta> ventaListOrphanCheck = empleado.getVentaList();
            for (Venta ventaListOrphanCheckVenta : ventaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Venta " + ventaListOrphanCheckVenta + " in its ventaList field has a non-nullable empleadoidEmpleado field.");
            }
            List<Remate> remateListOrphanCheck = empleado.getRemateList();
            for (Remate remateListOrphanCheckRemate : remateListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Remate " + remateListOrphanCheckRemate + " in its remateList field has a non-nullable empleadoIdempleado field.");
            }
            List<Empeno> empenoListOrphanCheck = empleado.getEmpenoList();
            for (Empeno empenoListOrphanCheckEmpeno : empenoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Empeno " + empenoListOrphanCheckEmpeno + " in its empenoList field has a non-nullable empleadoidEmpleado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tipoempleado tipoempleadoIdtipoempleado = empleado.getTipoempleadoIdtipoempleado();
            if (tipoempleadoIdtipoempleado != null) {
                tipoempleadoIdtipoempleado.getEmpleadoList().remove(empleado);
                tipoempleadoIdtipoempleado = em.merge(tipoempleadoIdtipoempleado);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
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

    public Empleado findEmpleado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
