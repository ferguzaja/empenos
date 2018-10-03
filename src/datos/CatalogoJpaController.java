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

/**
 *
 * @author Jahir
 */
public class CatalogoJpaController implements Serializable {

    public CatalogoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Catalogo catalogo) {
        if (catalogo.getClienteList() == null) {
            catalogo.setClienteList(new ArrayList<Cliente>());
        }
        if (catalogo.getClienteList1() == null) {
            catalogo.setClienteList1(new ArrayList<Cliente>());
        }
        if (catalogo.getEmpleadoList() == null) {
            catalogo.setEmpleadoList(new ArrayList<Empleado>());
        }
        if (catalogo.getCatalogoList() == null) {
            catalogo.setCatalogoList(new ArrayList<Catalogo>());
        }
        if (catalogo.getPrendaList() == null) {
            catalogo.setPrendaList(new ArrayList<Prenda>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catalogo catalogoIdcatalogo = catalogo.getCatalogoIdcatalogo();
            if (catalogoIdcatalogo != null) {
                catalogoIdcatalogo = em.getReference(catalogoIdcatalogo.getClass(), catalogoIdcatalogo.getIdcatalogo());
                catalogo.setCatalogoIdcatalogo(catalogoIdcatalogo);
            }
            List<Cliente> attachedClienteList = new ArrayList<Cliente>();
            for (Cliente clienteListClienteToAttach : catalogo.getClienteList()) {
                clienteListClienteToAttach = em.getReference(clienteListClienteToAttach.getClass(), clienteListClienteToAttach.getIdcliente());
                attachedClienteList.add(clienteListClienteToAttach);
            }
            catalogo.setClienteList(attachedClienteList);
            List<Cliente> attachedClienteList1 = new ArrayList<Cliente>();
            for (Cliente clienteList1ClienteToAttach : catalogo.getClienteList1()) {
                clienteList1ClienteToAttach = em.getReference(clienteList1ClienteToAttach.getClass(), clienteList1ClienteToAttach.getIdcliente());
                attachedClienteList1.add(clienteList1ClienteToAttach);
            }
            catalogo.setClienteList1(attachedClienteList1);
            List<Empleado> attachedEmpleadoList = new ArrayList<Empleado>();
            for (Empleado empleadoListEmpleadoToAttach : catalogo.getEmpleadoList()) {
                empleadoListEmpleadoToAttach = em.getReference(empleadoListEmpleadoToAttach.getClass(), empleadoListEmpleadoToAttach.getIdempleado());
                attachedEmpleadoList.add(empleadoListEmpleadoToAttach);
            }
            catalogo.setEmpleadoList(attachedEmpleadoList);
            List<Catalogo> attachedCatalogoList = new ArrayList<Catalogo>();
            for (Catalogo catalogoListCatalogoToAttach : catalogo.getCatalogoList()) {
                catalogoListCatalogoToAttach = em.getReference(catalogoListCatalogoToAttach.getClass(), catalogoListCatalogoToAttach.getIdcatalogo());
                attachedCatalogoList.add(catalogoListCatalogoToAttach);
            }
            catalogo.setCatalogoList(attachedCatalogoList);
            List<Prenda> attachedPrendaList = new ArrayList<Prenda>();
            for (Prenda prendaListPrendaToAttach : catalogo.getPrendaList()) {
                prendaListPrendaToAttach = em.getReference(prendaListPrendaToAttach.getClass(), prendaListPrendaToAttach.getIdprenda());
                attachedPrendaList.add(prendaListPrendaToAttach);
            }
            catalogo.setPrendaList(attachedPrendaList);
            em.persist(catalogo);
            if (catalogoIdcatalogo != null) {
                catalogoIdcatalogo.getCatalogoList().add(catalogo);
                catalogoIdcatalogo = em.merge(catalogoIdcatalogo);
            }
            for (Cliente clienteListCliente : catalogo.getClienteList()) {
                Catalogo oldCatalogoIdcatalogoOfClienteListCliente = clienteListCliente.getCatalogoIdcatalogo();
                clienteListCliente.setCatalogoIdcatalogo(catalogo);
                clienteListCliente = em.merge(clienteListCliente);
                if (oldCatalogoIdcatalogoOfClienteListCliente != null) {
                    oldCatalogoIdcatalogoOfClienteListCliente.getClienteList().remove(clienteListCliente);
                    oldCatalogoIdcatalogoOfClienteListCliente = em.merge(oldCatalogoIdcatalogoOfClienteListCliente);
                }
            }
            for (Cliente clienteList1Cliente : catalogo.getClienteList1()) {
                Catalogo oldCatalogoIdcatalogo1OfClienteList1Cliente = clienteList1Cliente.getCatalogoIdcatalogo1();
                clienteList1Cliente.setCatalogoIdcatalogo1(catalogo);
                clienteList1Cliente = em.merge(clienteList1Cliente);
                if (oldCatalogoIdcatalogo1OfClienteList1Cliente != null) {
                    oldCatalogoIdcatalogo1OfClienteList1Cliente.getClienteList1().remove(clienteList1Cliente);
                    oldCatalogoIdcatalogo1OfClienteList1Cliente = em.merge(oldCatalogoIdcatalogo1OfClienteList1Cliente);
                }
            }
            for (Empleado empleadoListEmpleado : catalogo.getEmpleadoList()) {
                Catalogo oldCatalogoIdcatalogoOfEmpleadoListEmpleado = empleadoListEmpleado.getCatalogoIdcatalogo();
                empleadoListEmpleado.setCatalogoIdcatalogo(catalogo);
                empleadoListEmpleado = em.merge(empleadoListEmpleado);
                if (oldCatalogoIdcatalogoOfEmpleadoListEmpleado != null) {
                    oldCatalogoIdcatalogoOfEmpleadoListEmpleado.getEmpleadoList().remove(empleadoListEmpleado);
                    oldCatalogoIdcatalogoOfEmpleadoListEmpleado = em.merge(oldCatalogoIdcatalogoOfEmpleadoListEmpleado);
                }
            }
            for (Catalogo catalogoListCatalogo : catalogo.getCatalogoList()) {
                Catalogo oldCatalogoIdcatalogoOfCatalogoListCatalogo = catalogoListCatalogo.getCatalogoIdcatalogo();
                catalogoListCatalogo.setCatalogoIdcatalogo(catalogo);
                catalogoListCatalogo = em.merge(catalogoListCatalogo);
                if (oldCatalogoIdcatalogoOfCatalogoListCatalogo != null) {
                    oldCatalogoIdcatalogoOfCatalogoListCatalogo.getCatalogoList().remove(catalogoListCatalogo);
                    oldCatalogoIdcatalogoOfCatalogoListCatalogo = em.merge(oldCatalogoIdcatalogoOfCatalogoListCatalogo);
                }
            }
            for (Prenda prendaListPrenda : catalogo.getPrendaList()) {
                Catalogo oldCatalogoidCatalogoOfPrendaListPrenda = prendaListPrenda.getCatalogoidCatalogo();
                prendaListPrenda.setCatalogoidCatalogo(catalogo);
                prendaListPrenda = em.merge(prendaListPrenda);
                if (oldCatalogoidCatalogoOfPrendaListPrenda != null) {
                    oldCatalogoidCatalogoOfPrendaListPrenda.getPrendaList().remove(prendaListPrenda);
                    oldCatalogoidCatalogoOfPrendaListPrenda = em.merge(oldCatalogoidCatalogoOfPrendaListPrenda);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Catalogo catalogo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catalogo persistentCatalogo = em.find(Catalogo.class, catalogo.getIdcatalogo());
            Catalogo catalogoIdcatalogoOld = persistentCatalogo.getCatalogoIdcatalogo();
            Catalogo catalogoIdcatalogoNew = catalogo.getCatalogoIdcatalogo();
            List<Cliente> clienteListOld = persistentCatalogo.getClienteList();
            List<Cliente> clienteListNew = catalogo.getClienteList();
            List<Cliente> clienteList1Old = persistentCatalogo.getClienteList1();
            List<Cliente> clienteList1New = catalogo.getClienteList1();
            List<Empleado> empleadoListOld = persistentCatalogo.getEmpleadoList();
            List<Empleado> empleadoListNew = catalogo.getEmpleadoList();
            List<Catalogo> catalogoListOld = persistentCatalogo.getCatalogoList();
            List<Catalogo> catalogoListNew = catalogo.getCatalogoList();
            List<Prenda> prendaListOld = persistentCatalogo.getPrendaList();
            List<Prenda> prendaListNew = catalogo.getPrendaList();
            List<String> illegalOrphanMessages = null;
            for (Cliente clienteListOldCliente : clienteListOld) {
                if (!clienteListNew.contains(clienteListOldCliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cliente " + clienteListOldCliente + " since its catalogoIdcatalogo field is not nullable.");
                }
            }
            for (Cliente clienteList1OldCliente : clienteList1Old) {
                if (!clienteList1New.contains(clienteList1OldCliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cliente " + clienteList1OldCliente + " since its catalogoIdcatalogo1 field is not nullable.");
                }
            }
            for (Empleado empleadoListOldEmpleado : empleadoListOld) {
                if (!empleadoListNew.contains(empleadoListOldEmpleado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empleado " + empleadoListOldEmpleado + " since its catalogoIdcatalogo field is not nullable.");
                }
            }
            for (Catalogo catalogoListOldCatalogo : catalogoListOld) {
                if (!catalogoListNew.contains(catalogoListOldCatalogo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Catalogo " + catalogoListOldCatalogo + " since its catalogoIdcatalogo field is not nullable.");
                }
            }
            for (Prenda prendaListOldPrenda : prendaListOld) {
                if (!prendaListNew.contains(prendaListOldPrenda)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Prenda " + prendaListOldPrenda + " since its catalogoidCatalogo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (catalogoIdcatalogoNew != null) {
                catalogoIdcatalogoNew = em.getReference(catalogoIdcatalogoNew.getClass(), catalogoIdcatalogoNew.getIdcatalogo());
                catalogo.setCatalogoIdcatalogo(catalogoIdcatalogoNew);
            }
            List<Cliente> attachedClienteListNew = new ArrayList<Cliente>();
            for (Cliente clienteListNewClienteToAttach : clienteListNew) {
                clienteListNewClienteToAttach = em.getReference(clienteListNewClienteToAttach.getClass(), clienteListNewClienteToAttach.getIdcliente());
                attachedClienteListNew.add(clienteListNewClienteToAttach);
            }
            clienteListNew = attachedClienteListNew;
            catalogo.setClienteList(clienteListNew);
            List<Cliente> attachedClienteList1New = new ArrayList<Cliente>();
            for (Cliente clienteList1NewClienteToAttach : clienteList1New) {
                clienteList1NewClienteToAttach = em.getReference(clienteList1NewClienteToAttach.getClass(), clienteList1NewClienteToAttach.getIdcliente());
                attachedClienteList1New.add(clienteList1NewClienteToAttach);
            }
            clienteList1New = attachedClienteList1New;
            catalogo.setClienteList1(clienteList1New);
            List<Empleado> attachedEmpleadoListNew = new ArrayList<Empleado>();
            for (Empleado empleadoListNewEmpleadoToAttach : empleadoListNew) {
                empleadoListNewEmpleadoToAttach = em.getReference(empleadoListNewEmpleadoToAttach.getClass(), empleadoListNewEmpleadoToAttach.getIdempleado());
                attachedEmpleadoListNew.add(empleadoListNewEmpleadoToAttach);
            }
            empleadoListNew = attachedEmpleadoListNew;
            catalogo.setEmpleadoList(empleadoListNew);
            List<Catalogo> attachedCatalogoListNew = new ArrayList<Catalogo>();
            for (Catalogo catalogoListNewCatalogoToAttach : catalogoListNew) {
                catalogoListNewCatalogoToAttach = em.getReference(catalogoListNewCatalogoToAttach.getClass(), catalogoListNewCatalogoToAttach.getIdcatalogo());
                attachedCatalogoListNew.add(catalogoListNewCatalogoToAttach);
            }
            catalogoListNew = attachedCatalogoListNew;
            catalogo.setCatalogoList(catalogoListNew);
            List<Prenda> attachedPrendaListNew = new ArrayList<Prenda>();
            for (Prenda prendaListNewPrendaToAttach : prendaListNew) {
                prendaListNewPrendaToAttach = em.getReference(prendaListNewPrendaToAttach.getClass(), prendaListNewPrendaToAttach.getIdprenda());
                attachedPrendaListNew.add(prendaListNewPrendaToAttach);
            }
            prendaListNew = attachedPrendaListNew;
            catalogo.setPrendaList(prendaListNew);
            catalogo = em.merge(catalogo);
            if (catalogoIdcatalogoOld != null && !catalogoIdcatalogoOld.equals(catalogoIdcatalogoNew)) {
                catalogoIdcatalogoOld.getCatalogoList().remove(catalogo);
                catalogoIdcatalogoOld = em.merge(catalogoIdcatalogoOld);
            }
            if (catalogoIdcatalogoNew != null && !catalogoIdcatalogoNew.equals(catalogoIdcatalogoOld)) {
                catalogoIdcatalogoNew.getCatalogoList().add(catalogo);
                catalogoIdcatalogoNew = em.merge(catalogoIdcatalogoNew);
            }
            for (Cliente clienteListNewCliente : clienteListNew) {
                if (!clienteListOld.contains(clienteListNewCliente)) {
                    Catalogo oldCatalogoIdcatalogoOfClienteListNewCliente = clienteListNewCliente.getCatalogoIdcatalogo();
                    clienteListNewCliente.setCatalogoIdcatalogo(catalogo);
                    clienteListNewCliente = em.merge(clienteListNewCliente);
                    if (oldCatalogoIdcatalogoOfClienteListNewCliente != null && !oldCatalogoIdcatalogoOfClienteListNewCliente.equals(catalogo)) {
                        oldCatalogoIdcatalogoOfClienteListNewCliente.getClienteList().remove(clienteListNewCliente);
                        oldCatalogoIdcatalogoOfClienteListNewCliente = em.merge(oldCatalogoIdcatalogoOfClienteListNewCliente);
                    }
                }
            }
            for (Cliente clienteList1NewCliente : clienteList1New) {
                if (!clienteList1Old.contains(clienteList1NewCliente)) {
                    Catalogo oldCatalogoIdcatalogo1OfClienteList1NewCliente = clienteList1NewCliente.getCatalogoIdcatalogo1();
                    clienteList1NewCliente.setCatalogoIdcatalogo1(catalogo);
                    clienteList1NewCliente = em.merge(clienteList1NewCliente);
                    if (oldCatalogoIdcatalogo1OfClienteList1NewCliente != null && !oldCatalogoIdcatalogo1OfClienteList1NewCliente.equals(catalogo)) {
                        oldCatalogoIdcatalogo1OfClienteList1NewCliente.getClienteList1().remove(clienteList1NewCliente);
                        oldCatalogoIdcatalogo1OfClienteList1NewCliente = em.merge(oldCatalogoIdcatalogo1OfClienteList1NewCliente);
                    }
                }
            }
            for (Empleado empleadoListNewEmpleado : empleadoListNew) {
                if (!empleadoListOld.contains(empleadoListNewEmpleado)) {
                    Catalogo oldCatalogoIdcatalogoOfEmpleadoListNewEmpleado = empleadoListNewEmpleado.getCatalogoIdcatalogo();
                    empleadoListNewEmpleado.setCatalogoIdcatalogo(catalogo);
                    empleadoListNewEmpleado = em.merge(empleadoListNewEmpleado);
                    if (oldCatalogoIdcatalogoOfEmpleadoListNewEmpleado != null && !oldCatalogoIdcatalogoOfEmpleadoListNewEmpleado.equals(catalogo)) {
                        oldCatalogoIdcatalogoOfEmpleadoListNewEmpleado.getEmpleadoList().remove(empleadoListNewEmpleado);
                        oldCatalogoIdcatalogoOfEmpleadoListNewEmpleado = em.merge(oldCatalogoIdcatalogoOfEmpleadoListNewEmpleado);
                    }
                }
            }
            for (Catalogo catalogoListNewCatalogo : catalogoListNew) {
                if (!catalogoListOld.contains(catalogoListNewCatalogo)) {
                    Catalogo oldCatalogoIdcatalogoOfCatalogoListNewCatalogo = catalogoListNewCatalogo.getCatalogoIdcatalogo();
                    catalogoListNewCatalogo.setCatalogoIdcatalogo(catalogo);
                    catalogoListNewCatalogo = em.merge(catalogoListNewCatalogo);
                    if (oldCatalogoIdcatalogoOfCatalogoListNewCatalogo != null && !oldCatalogoIdcatalogoOfCatalogoListNewCatalogo.equals(catalogo)) {
                        oldCatalogoIdcatalogoOfCatalogoListNewCatalogo.getCatalogoList().remove(catalogoListNewCatalogo);
                        oldCatalogoIdcatalogoOfCatalogoListNewCatalogo = em.merge(oldCatalogoIdcatalogoOfCatalogoListNewCatalogo);
                    }
                }
            }
            for (Prenda prendaListNewPrenda : prendaListNew) {
                if (!prendaListOld.contains(prendaListNewPrenda)) {
                    Catalogo oldCatalogoidCatalogoOfPrendaListNewPrenda = prendaListNewPrenda.getCatalogoidCatalogo();
                    prendaListNewPrenda.setCatalogoidCatalogo(catalogo);
                    prendaListNewPrenda = em.merge(prendaListNewPrenda);
                    if (oldCatalogoidCatalogoOfPrendaListNewPrenda != null && !oldCatalogoidCatalogoOfPrendaListNewPrenda.equals(catalogo)) {
                        oldCatalogoidCatalogoOfPrendaListNewPrenda.getPrendaList().remove(prendaListNewPrenda);
                        oldCatalogoidCatalogoOfPrendaListNewPrenda = em.merge(oldCatalogoidCatalogoOfPrendaListNewPrenda);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = catalogo.getIdcatalogo();
                if (findCatalogo(id) == null) {
                    throw new NonexistentEntityException("The catalogo with id " + id + " no longer exists.");
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
            Catalogo catalogo;
            try {
                catalogo = em.getReference(Catalogo.class, id);
                catalogo.getIdcatalogo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catalogo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cliente> clienteListOrphanCheck = catalogo.getClienteList();
            for (Cliente clienteListOrphanCheckCliente : clienteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Catalogo (" + catalogo + ") cannot be destroyed since the Cliente " + clienteListOrphanCheckCliente + " in its clienteList field has a non-nullable catalogoIdcatalogo field.");
            }
            List<Cliente> clienteList1OrphanCheck = catalogo.getClienteList1();
            for (Cliente clienteList1OrphanCheckCliente : clienteList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Catalogo (" + catalogo + ") cannot be destroyed since the Cliente " + clienteList1OrphanCheckCliente + " in its clienteList1 field has a non-nullable catalogoIdcatalogo1 field.");
            }
            List<Empleado> empleadoListOrphanCheck = catalogo.getEmpleadoList();
            for (Empleado empleadoListOrphanCheckEmpleado : empleadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Catalogo (" + catalogo + ") cannot be destroyed since the Empleado " + empleadoListOrphanCheckEmpleado + " in its empleadoList field has a non-nullable catalogoIdcatalogo field.");
            }
            List<Catalogo> catalogoListOrphanCheck = catalogo.getCatalogoList();
            for (Catalogo catalogoListOrphanCheckCatalogo : catalogoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Catalogo (" + catalogo + ") cannot be destroyed since the Catalogo " + catalogoListOrphanCheckCatalogo + " in its catalogoList field has a non-nullable catalogoIdcatalogo field.");
            }
            List<Prenda> prendaListOrphanCheck = catalogo.getPrendaList();
            for (Prenda prendaListOrphanCheckPrenda : prendaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Catalogo (" + catalogo + ") cannot be destroyed since the Prenda " + prendaListOrphanCheckPrenda + " in its prendaList field has a non-nullable catalogoidCatalogo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Catalogo catalogoIdcatalogo = catalogo.getCatalogoIdcatalogo();
            if (catalogoIdcatalogo != null) {
                catalogoIdcatalogo.getCatalogoList().remove(catalogo);
                catalogoIdcatalogo = em.merge(catalogoIdcatalogo);
            }
            em.remove(catalogo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Catalogo> findCatalogoEntities() {
        return findCatalogoEntities(true, -1, -1);
    }

    public List<Catalogo> findCatalogoEntities(int maxResults, int firstResult) {
        return findCatalogoEntities(false, maxResults, firstResult);
    }

    private List<Catalogo> findCatalogoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Catalogo.class));
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

    public Catalogo findCatalogo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Catalogo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCatalogoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Catalogo> rt = cq.from(Catalogo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
