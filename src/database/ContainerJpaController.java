/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.exceptions.NonexistentEntityException;
import database.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author itsystem
 */
public class ContainerJpaController implements Serializable {

    public ContainerJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    private int id = 0;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Container container) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BillOfLanding idBol = container.getIdBol();
            if (idBol != null) {
                idBol = em.getReference(idBol.getClass(), idBol.getIdBol());
                container.setIdBol(idBol);
            }
            em.persist(container);
            if (idBol != null) {
                idBol.getContainerCollection().add(container);
                idBol = em.merge(idBol);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findContainer(container.getIdCtn()) != null) {
                throw new PreexistingEntityException("Container " + container + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Container container) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Container persistentContainer = em.find(Container.class, container.getIdCtn());
            BillOfLanding idBolOld = persistentContainer.getIdBol();
            BillOfLanding idBolNew = container.getIdBol();
            if (idBolNew != null) {
                idBolNew = em.getReference(idBolNew.getClass(), idBolNew.getIdBol());
                container.setIdBol(idBolNew);
            }
            container = em.merge(container);
            if (idBolOld != null && !idBolOld.equals(idBolNew)) {
                idBolOld.getContainerCollection().remove(container);
                idBolOld = em.merge(idBolOld);
            }
            if (idBolNew != null && !idBolNew.equals(idBolOld)) {
                idBolNew.getContainerCollection().add(container);
                idBolNew = em.merge(idBolNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = container.getIdCtn();
                if (findContainer(id) == null) {
                    throw new NonexistentEntityException("The container with id " + id + " no longer exists.");
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
            Container container;
            try {
                container = em.getReference(Container.class, id);
                container.getIdCtn();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The container with id " + id + " no longer exists.", enfe);
            }
            BillOfLanding idBol = container.getIdBol();
            if (idBol != null) {
                idBol.getContainerCollection().remove(container);
                idBol = em.merge(idBol);
            }
            em.remove(container);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Container> findContainerEntities() {
        return findContainerEntities(true, -1, -1);
    }

    public List<Container> findContainerEntities(int maxResults, int firstResult) {
        return findContainerEntities(false, maxResults, firstResult);
    }

    private List<Container> findContainerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Container.class));
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
    public int findLastId(){
        EntityManager em = getEntityManager();
        try{
            if(findContainerEntities().isEmpty()){
                return 1;
            }else{
                findContainerEntities().forEach((Container ctn) -> {
                    if(id < ctn.getIdCtn()){
                        id = ctn.getIdCtn();
                    }
                });
            }
            return (id + 1);
        }finally{
            em.close();
        }
    }
    public Container findContainer(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Container.class, id);
        } finally {
            em.close();
        }
    }

    public int getContainerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Container> rt = cq.from(Container.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
