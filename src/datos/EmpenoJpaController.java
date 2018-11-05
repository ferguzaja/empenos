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
public class EmpenoJpaController implements Serializable {

    public EmpenoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EmpenoFacilPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empeno empeno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleadoidEmpleado = empeno.getEmpleadoidEmpleado();
            if (empleadoidEmpleado != null) {
                empleadoidEmpleado = em.getReference(empleadoidEmpleado.getClass(), empleadoidEmpleado.getIdempleado());
                empeno.setEmpleadoidEmpleado(empleadoidEmpleado);
            }
            Cliente clienteIdcliente = empeno.getClienteIdcliente();
            if (clienteIdcliente != null) {
                clienteIdcliente = em.getReference(clienteIdcliente.getClass(), clienteIdcliente.getIdcliente());
                empeno.setClienteIdcliente(clienteIdcliente);
            }
            em.persist(empeno);
            if (empleadoidEmpleado != null) {
                empleadoidEmpleado.getEmpenoList().add(empeno);
                empleadoidEmpleado = em.merge(empleadoidEmpleado);
            }
            if (clienteIdcliente != null) {
                clienteIdcliente.getEmpenoList().add(empeno);
                clienteIdcliente = em.merge(clienteIdcliente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empeno empeno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empeno persistentEmpeno = em.find(Empeno.class, empeno.getIdempeno());
            Empleado empleadoidEmpleadoOld = persistentEmpeno.getEmpleadoidEmpleado();
            Empleado empleadoidEmpleadoNew = empeno.getEmpleadoidEmpleado();
            Cliente clienteIdclienteOld = persistentEmpeno.getClienteIdcliente();
            Cliente clienteIdclienteNew = empeno.getClienteIdcliente();
            if (empleadoidEmpleadoNew != null) {
                empleadoidEmpleadoNew = em.getReference(empleadoidEmpleadoNew.getClass(), empleadoidEmpleadoNew.getIdempleado());
                empeno.setEmpleadoidEmpleado(empleadoidEmpleadoNew);
            }
            if (clienteIdclienteNew != null) {
                clienteIdclienteNew = em.getReference(clienteIdclienteNew.getClass(), clienteIdclienteNew.getIdcliente());
                empeno.setClienteIdcliente(clienteIdclienteNew);
            }
            empeno = em.merge(empeno);
            if (empleadoidEmpleadoOld != null && !empleadoidEmpleadoOld.equals(empleadoidEmpleadoNew)) {
                empleadoidEmpleadoOld.getEmpenoList().remove(empeno);
                empleadoidEmpleadoOld = em.merge(empleadoidEmpleadoOld);
            }
            if (empleadoidEmpleadoNew != null && !empleadoidEmpleadoNew.equals(empleadoidEmpleadoOld)) {
                empleadoidEmpleadoNew.getEmpenoList().add(empeno);
                empleadoidEmpleadoNew = em.merge(empleadoidEmpleadoNew);
            }
            if (clienteIdclienteOld != null && !clienteIdclienteOld.equals(clienteIdclienteNew)) {
                clienteIdclienteOld.getEmpenoList().remove(empeno);
                clienteIdclienteOld = em.merge(clienteIdclienteOld);
            }
            if (clienteIdclienteNew != null && !clienteIdclienteNew.equals(clienteIdclienteOld)) {
                clienteIdclienteNew.getEmpenoList().add(empeno);
                clienteIdclienteNew = em.merge(clienteIdclienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empeno.getIdempeno();
                if (findEmpeno(id) == null) {
                    throw new NonexistentEntityException("The empeno with id " + id + " no longer exists.");
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
            Empeno empeno;
            try {
                empeno = em.getReference(Empeno.class, id);
                empeno.getIdempeno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empeno with id " + id + " no longer exists.", enfe);
            }
            Empleado empleadoidEmpleado = empeno.getEmpleadoidEmpleado();
            if (empleadoidEmpleado != null) {
                empleadoidEmpleado.getEmpenoList().remove(empeno);
                empleadoidEmpleado = em.merge(empleadoidEmpleado);
            }
            Cliente clienteIdcliente = empeno.getClienteIdcliente();
            if (clienteIdcliente != null) {
                clienteIdcliente.getEmpenoList().remove(empeno);
                clienteIdcliente = em.merge(clienteIdcliente);
            }
            em.remove(empeno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empeno> findEmpenoEntities() {
        return findEmpenoEntities(true, -1, -1);
    }

    public List<Empeno> findEmpenoEntities(int maxResults, int firstResult) {
        return findEmpenoEntities(false, maxResults, firstResult);
    }

    private List<Empeno> findEmpenoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empeno.class));
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

    public Empeno findEmpeno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empeno.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpenoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empeno> rt = cq.from(Empeno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
