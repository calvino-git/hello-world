/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.exceptions.IllegalOrphanException;
import database.exceptions.NonexistentEntityException;
import database.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author itsystem
 */
public class BillOfLandingJpaController implements Serializable {

    public BillOfLandingJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    private int id;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BillOfLanding billOfLanding) throws PreexistingEntityException, Exception {
        if (billOfLanding.getContainerCollection() == null) {
            billOfLanding.setContainerCollection(new ArrayList<Container>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GeneralInfo idGeneral = billOfLanding.getIdGeneral();
            if (idGeneral != null) {
                idGeneral = em.getReference(idGeneral.getClass(), idGeneral.getId());
                billOfLanding.setIdGeneral(idGeneral);
            }
            Collection<Container> attachedContainerCollection = new ArrayList<Container>();
            for (Container containerCollectionContainerToAttach : billOfLanding.getContainerCollection()) {
                containerCollectionContainerToAttach = em.getReference(containerCollectionContainerToAttach.getClass(), containerCollectionContainerToAttach.getIdCtn());
                attachedContainerCollection.add(containerCollectionContainerToAttach);
            }
            billOfLanding.setContainerCollection(attachedContainerCollection);
            em.persist(billOfLanding);
            if (idGeneral != null) {
                idGeneral.getBillOfLandingCollection().add(billOfLanding);
                idGeneral = em.merge(idGeneral);
            }
            for (Container containerCollectionContainer : billOfLanding.getContainerCollection()) {
                BillOfLanding oldIdBolOfContainerCollectionContainer = containerCollectionContainer.getIdBol();
                containerCollectionContainer.setIdBol(billOfLanding);
                containerCollectionContainer = em.merge(containerCollectionContainer);
                if (oldIdBolOfContainerCollectionContainer != null) {
                    oldIdBolOfContainerCollectionContainer.getContainerCollection().remove(containerCollectionContainer);
                    oldIdBolOfContainerCollectionContainer = em.merge(oldIdBolOfContainerCollectionContainer);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBillOfLanding(billOfLanding.getIdBol()) != null) {
                throw new PreexistingEntityException("BillOfLanding " + billOfLanding + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BillOfLanding billOfLanding) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BillOfLanding persistentBillOfLanding = em.find(BillOfLanding.class, billOfLanding.getIdBol());
            GeneralInfo idGeneralOld = persistentBillOfLanding.getIdGeneral();
            GeneralInfo idGeneralNew = billOfLanding.getIdGeneral();
            Collection<Container> containerCollectionOld = persistentBillOfLanding.getContainerCollection();
            Collection<Container> containerCollectionNew = billOfLanding.getContainerCollection();
            List<String> illegalOrphanMessages = null;
            for (Container containerCollectionOldContainer : containerCollectionOld) {
                if (!containerCollectionNew.contains(containerCollectionOldContainer)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Container " + containerCollectionOldContainer + " since its idBol field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idGeneralNew != null) {
                idGeneralNew = em.getReference(idGeneralNew.getClass(), idGeneralNew.getId());
                billOfLanding.setIdGeneral(idGeneralNew);
            }
            Collection<Container> attachedContainerCollectionNew = new ArrayList<Container>();
            for (Container containerCollectionNewContainerToAttach : containerCollectionNew) {
                containerCollectionNewContainerToAttach = em.getReference(containerCollectionNewContainerToAttach.getClass(), containerCollectionNewContainerToAttach.getIdCtn());
                attachedContainerCollectionNew.add(containerCollectionNewContainerToAttach);
            }
            containerCollectionNew = attachedContainerCollectionNew;
            billOfLanding.setContainerCollection(containerCollectionNew);
            billOfLanding = em.merge(billOfLanding);
            if (idGeneralOld != null && !idGeneralOld.equals(idGeneralNew)) {
                idGeneralOld.getBillOfLandingCollection().remove(billOfLanding);
                idGeneralOld = em.merge(idGeneralOld);
            }
            if (idGeneralNew != null && !idGeneralNew.equals(idGeneralOld)) {
                idGeneralNew.getBillOfLandingCollection().add(billOfLanding);
                idGeneralNew = em.merge(idGeneralNew);
            }
            for (Container containerCollectionNewContainer : containerCollectionNew) {
                if (!containerCollectionOld.contains(containerCollectionNewContainer)) {
                    BillOfLanding oldIdBolOfContainerCollectionNewContainer = containerCollectionNewContainer.getIdBol();
                    containerCollectionNewContainer.setIdBol(billOfLanding);
                    containerCollectionNewContainer = em.merge(containerCollectionNewContainer);
                    if (oldIdBolOfContainerCollectionNewContainer != null && !oldIdBolOfContainerCollectionNewContainer.equals(billOfLanding)) {
                        oldIdBolOfContainerCollectionNewContainer.getContainerCollection().remove(containerCollectionNewContainer);
                        oldIdBolOfContainerCollectionNewContainer = em.merge(oldIdBolOfContainerCollectionNewContainer);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = billOfLanding.getIdBol();
                if (findBillOfLanding(id) == null) {
                    throw new NonexistentEntityException("The billOfLanding with id " + id + " no longer exists.");
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
            BillOfLanding billOfLanding;
            try {
                billOfLanding = em.getReference(BillOfLanding.class, id);
                billOfLanding.getIdBol();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The billOfLanding with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Container> containerCollectionOrphanCheck = billOfLanding.getContainerCollection();
            for (Container containerCollectionOrphanCheckContainer : containerCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This BillOfLanding (" + billOfLanding + ") cannot be destroyed since the Container " + containerCollectionOrphanCheckContainer + " in its containerCollection field has a non-nullable idBol field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            GeneralInfo idGeneral = billOfLanding.getIdGeneral();
            if (idGeneral != null) {
                idGeneral.getBillOfLandingCollection().remove(billOfLanding);
                idGeneral = em.merge(idGeneral);
            }
            em.remove(billOfLanding);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BillOfLanding> findBillOfLandingEntities() {
        return findBillOfLandingEntities(true, -1, -1);
    }

    public List<BillOfLanding> findBillOfLandingEntities(int maxResults, int firstResult) {
        return findBillOfLandingEntities(false, maxResults, firstResult);
    }

    private List<BillOfLanding> findBillOfLandingEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BillOfLanding.class));
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

    public BillOfLanding findBillOfLanding(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BillOfLanding.class, id);
        } finally {
            em.close();
        }
    }
    
    public int findLastId(){
        EntityManager em = getEntityManager();
        try{
            if(findBillOfLandingEntities().isEmpty()){
                return 1;
            }else{
                findBillOfLandingEntities().forEach((BillOfLanding ctn) -> {
                    if(id < ctn.getIdBol()){
                        id = ctn.getIdBol();
                    }
                });
            }
            return (id + 1);
        }finally{
            em.close();
        }
    }
    
    public int getBillOfLandingCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BillOfLanding> rt = cq.from(BillOfLanding.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
