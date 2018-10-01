/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

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

/**
 *
 * @author Jahir
 */
public class RemateJpaController implements Serializable {

    public RemateJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Remate remate) {
        if (remate.getArticuloventaList() == null) {
            remate.setArticuloventaList(new ArrayList<Articuloventa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente clienteIdcliente = remate.getClienteIdcliente();
            if (clienteIdcliente != null) {
                clienteIdcliente = em.getReference(clienteIdcliente.getClass(), clienteIdcliente.getIdcliente());
                remate.setClienteIdcliente(clienteIdcliente);
            }
            Empleado empleadoIdempleado = remate.getEmpleadoIdempleado();
            if (empleadoIdempleado != null) {
                empleadoIdempleado = em.getReference(empleadoIdempleado.getClass(), empleadoIdempleado.getIdempleado());
                remate.setEmpleadoIdempleado(empleadoIdempleado);
            }
            List<Articuloventa> attachedArticuloventaList = new ArrayList<Articuloventa>();
            for (Articuloventa articuloventaListArticuloventaToAttach : remate.getArticuloventaList()) {
                articuloventaListArticuloventaToAttach = em.getReference(articuloventaListArticuloventaToAttach.getClass(), articuloventaListArticuloventaToAttach.getIdarticuloventa());
                attachedArticuloventaList.add(articuloventaListArticuloventaToAttach);
            }
            remate.setArticuloventaList(attachedArticuloventaList);
            em.persist(remate);
            if (clienteIdcliente != null) {
                clienteIdcliente.getRemateList().add(remate);
                clienteIdcliente = em.merge(clienteIdcliente);
            }
            if (empleadoIdempleado != null) {
                empleadoIdempleado.getRemateList().add(remate);
                empleadoIdempleado = em.merge(empleadoIdempleado);
            }
            for (Articuloventa articuloventaListArticuloventa : remate.getArticuloventaList()) {
                articuloventaListArticuloventa.getRemateList().add(remate);
                articuloventaListArticuloventa = em.merge(articuloventaListArticuloventa);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Remate remate) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Remate persistentRemate = em.find(Remate.class, remate.getIdremate());
            Cliente clienteIdclienteOld = persistentRemate.getClienteIdcliente();
            Cliente clienteIdclienteNew = remate.getClienteIdcliente();
            Empleado empleadoIdempleadoOld = persistentRemate.getEmpleadoIdempleado();
            Empleado empleadoIdempleadoNew = remate.getEmpleadoIdempleado();
            List<Articuloventa> articuloventaListOld = persistentRemate.getArticuloventaList();
            List<Articuloventa> articuloventaListNew = remate.getArticuloventaList();
            if (clienteIdclienteNew != null) {
                clienteIdclienteNew = em.getReference(clienteIdclienteNew.getClass(), clienteIdclienteNew.getIdcliente());
                remate.setClienteIdcliente(clienteIdclienteNew);
            }
            if (empleadoIdempleadoNew != null) {
                empleadoIdempleadoNew = em.getReference(empleadoIdempleadoNew.getClass(), empleadoIdempleadoNew.getIdempleado());
                remate.setEmpleadoIdempleado(empleadoIdempleadoNew);
            }
            List<Articuloventa> attachedArticuloventaListNew = new ArrayList<Articuloventa>();
            for (Articuloventa articuloventaListNewArticuloventaToAttach : articuloventaListNew) {
                articuloventaListNewArticuloventaToAttach = em.getReference(articuloventaListNewArticuloventaToAttach.getClass(), articuloventaListNewArticuloventaToAttach.getIdarticuloventa());
                attachedArticuloventaListNew.add(articuloventaListNewArticuloventaToAttach);
            }
            articuloventaListNew = attachedArticuloventaListNew;
            remate.setArticuloventaList(articuloventaListNew);
            remate = em.merge(remate);
            if (clienteIdclienteOld != null && !clienteIdclienteOld.equals(clienteIdclienteNew)) {
                clienteIdclienteOld.getRemateList().remove(remate);
                clienteIdclienteOld = em.merge(clienteIdclienteOld);
            }
            if (clienteIdclienteNew != null && !clienteIdclienteNew.equals(clienteIdclienteOld)) {
                clienteIdclienteNew.getRemateList().add(remate);
                clienteIdclienteNew = em.merge(clienteIdclienteNew);
            }
            if (empleadoIdempleadoOld != null && !empleadoIdempleadoOld.equals(empleadoIdempleadoNew)) {
                empleadoIdempleadoOld.getRemateList().remove(remate);
                empleadoIdempleadoOld = em.merge(empleadoIdempleadoOld);
            }
            if (empleadoIdempleadoNew != null && !empleadoIdempleadoNew.equals(empleadoIdempleadoOld)) {
                empleadoIdempleadoNew.getRemateList().add(remate);
                empleadoIdempleadoNew = em.merge(empleadoIdempleadoNew);
            }
            for (Articuloventa articuloventaListOldArticuloventa : articuloventaListOld) {
                if (!articuloventaListNew.contains(articuloventaListOldArticuloventa)) {
                    articuloventaListOldArticuloventa.getRemateList().remove(remate);
                    articuloventaListOldArticuloventa = em.merge(articuloventaListOldArticuloventa);
                }
            }
            for (Articuloventa articuloventaListNewArticuloventa : articuloventaListNew) {
                if (!articuloventaListOld.contains(articuloventaListNewArticuloventa)) {
                    articuloventaListNewArticuloventa.getRemateList().add(remate);
                    articuloventaListNewArticuloventa = em.merge(articuloventaListNewArticuloventa);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = remate.getIdremate();
                if (findRemate(id) == null) {
                    throw new NonexistentEntityException("The remate with id " + id + " no longer exists.");
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
            Remate remate;
            try {
                remate = em.getReference(Remate.class, id);
                remate.getIdremate();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The remate with id " + id + " no longer exists.", enfe);
            }
            Cliente clienteIdcliente = remate.getClienteIdcliente();
            if (clienteIdcliente != null) {
                clienteIdcliente.getRemateList().remove(remate);
                clienteIdcliente = em.merge(clienteIdcliente);
            }
            Empleado empleadoIdempleado = remate.getEmpleadoIdempleado();
            if (empleadoIdempleado != null) {
                empleadoIdempleado.getRemateList().remove(remate);
                empleadoIdempleado = em.merge(empleadoIdempleado);
            }
            List<Articuloventa> articuloventaList = remate.getArticuloventaList();
            for (Articuloventa articuloventaListArticuloventa : articuloventaList) {
                articuloventaListArticuloventa.getRemateList().remove(remate);
                articuloventaListArticuloventa = em.merge(articuloventaListArticuloventa);
            }
            em.remove(remate);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Remate> findRemateEntities() {
        return findRemateEntities(true, -1, -1);
    }

    public List<Remate> findRemateEntities(int maxResults, int firstResult) {
        return findRemateEntities(false, maxResults, firstResult);
    }

    private List<Remate> findRemateEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Remate.class));
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

    public Remate findRemate(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Remate.class, id);
        } finally {
            em.close();
        }
    }

    public int getRemateCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Remate> rt = cq.from(Remate.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
