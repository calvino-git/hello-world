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
public class GeneralInfoJpaController implements Serializable {

    public GeneralInfoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    private int id = 0;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GeneralInfo generalInfo) throws PreexistingEntityException, Exception {
        if (generalInfo.getBillOfLandingCollection() == null) {
            generalInfo.setBillOfLandingCollection(new ArrayList<BillOfLanding>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<BillOfLanding> attachedBillOfLandingCollection = new ArrayList<BillOfLanding>();
            for (BillOfLanding billOfLandingCollectionBillOfLandingToAttach : generalInfo.getBillOfLandingCollection()) {
                billOfLandingCollectionBillOfLandingToAttach = em.getReference(billOfLandingCollectionBillOfLandingToAttach.getClass(), billOfLandingCollectionBillOfLandingToAttach.getIdBol());
                attachedBillOfLandingCollection.add(billOfLandingCollectionBillOfLandingToAttach);
            }
            generalInfo.setBillOfLandingCollection(attachedBillOfLandingCollection);
            em.persist(generalInfo);
            for (BillOfLanding billOfLandingCollectionBillOfLanding : generalInfo.getBillOfLandingCollection()) {
                GeneralInfo oldIdGeneralOfBillOfLandingCollectionBillOfLanding = billOfLandingCollectionBillOfLanding.getIdGeneral();
                billOfLandingCollectionBillOfLanding.setIdGeneral(generalInfo);
                billOfLandingCollectionBillOfLanding = em.merge(billOfLandingCollectionBillOfLanding);
                if (oldIdGeneralOfBillOfLandingCollectionBillOfLanding != null) {
                    oldIdGeneralOfBillOfLandingCollectionBillOfLanding.getBillOfLandingCollection().remove(billOfLandingCollectionBillOfLanding);
                    oldIdGeneralOfBillOfLandingCollectionBillOfLanding = em.merge(oldIdGeneralOfBillOfLandingCollectionBillOfLanding);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGeneralInfo(generalInfo.getId()) != null) {
                throw new PreexistingEntityException("GeneralInfo " + generalInfo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GeneralInfo generalInfo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GeneralInfo persistentGeneralInfo = em.find(GeneralInfo.class, generalInfo.getId());
            Collection<BillOfLanding> billOfLandingCollectionOld = persistentGeneralInfo.getBillOfLandingCollection();
            Collection<BillOfLanding> billOfLandingCollectionNew = generalInfo.getBillOfLandingCollection();
            List<String> illegalOrphanMessages = null;
            for (BillOfLanding billOfLandingCollectionOldBillOfLanding : billOfLandingCollectionOld) {
                if (!billOfLandingCollectionNew.contains(billOfLandingCollectionOldBillOfLanding)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain BillOfLanding " + billOfLandingCollectionOldBillOfLanding + " since its idGeneral field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<BillOfLanding> attachedBillOfLandingCollectionNew = new ArrayList<BillOfLanding>();
            for (BillOfLanding billOfLandingCollectionNewBillOfLandingToAttach : billOfLandingCollectionNew) {
                billOfLandingCollectionNewBillOfLandingToAttach = em.getReference(billOfLandingCollectionNewBillOfLandingToAttach.getClass(), billOfLandingCollectionNewBillOfLandingToAttach.getIdBol());
                attachedBillOfLandingCollectionNew.add(billOfLandingCollectionNewBillOfLandingToAttach);
            }
            billOfLandingCollectionNew = attachedBillOfLandingCollectionNew;
            generalInfo.setBillOfLandingCollection(billOfLandingCollectionNew);
            generalInfo = em.merge(generalInfo);
            for (BillOfLanding billOfLandingCollectionNewBillOfLanding : billOfLandingCollectionNew) {
                if (!billOfLandingCollectionOld.contains(billOfLandingCollectionNewBillOfLanding)) {
                    GeneralInfo oldIdGeneralOfBillOfLandingCollectionNewBillOfLanding = billOfLandingCollectionNewBillOfLanding.getIdGeneral();
                    billOfLandingCollectionNewBillOfLanding.setIdGeneral(generalInfo);
                    billOfLandingCollectionNewBillOfLanding = em.merge(billOfLandingCollectionNewBillOfLanding);
                    if (oldIdGeneralOfBillOfLandingCollectionNewBillOfLanding != null && !oldIdGeneralOfBillOfLandingCollectionNewBillOfLanding.equals(generalInfo)) {
                        oldIdGeneralOfBillOfLandingCollectionNewBillOfLanding.getBillOfLandingCollection().remove(billOfLandingCollectionNewBillOfLanding);
                        oldIdGeneralOfBillOfLandingCollectionNewBillOfLanding = em.merge(oldIdGeneralOfBillOfLandingCollectionNewBillOfLanding);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = generalInfo.getId();
                if (findGeneralInfo(id) == null) {
                    throw new NonexistentEntityException("The generalInfo with id " + id + " no longer exists.");
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
            GeneralInfo generalInfo;
            try {
                generalInfo = em.getReference(GeneralInfo.class, id);
                generalInfo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The generalInfo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<BillOfLanding> billOfLandingCollectionOrphanCheck = generalInfo.getBillOfLandingCollection();
            for (BillOfLanding billOfLandingCollectionOrphanCheckBillOfLanding : billOfLandingCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This GeneralInfo (" + generalInfo + ") cannot be destroyed since the BillOfLanding " + billOfLandingCollectionOrphanCheckBillOfLanding + " in its billOfLandingCollection field has a non-nullable idGeneral field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(generalInfo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GeneralInfo> findGeneralInfoEntities() {
        return findGeneralInfoEntities(true, -1, -1);
    }

    public List<GeneralInfo> findGeneralInfoEntities(int maxResults, int firstResult) {
        return findGeneralInfoEntities(false, maxResults, firstResult);
    }

    private List<GeneralInfo> findGeneralInfoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GeneralInfo.class));
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

    public GeneralInfo findGeneralInfo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GeneralInfo.class, id);
        } finally {
            em.close();
        }
    }
    
    public int findLastId(){
        EntityManager em = getEntityManager();
        try{
            if(findGeneralInfoEntities().isEmpty()){
                return 1;
            }else{
                findGeneralInfoEntities().forEach(gen -> {
                    if(id < gen.getId()){
                        id = gen.getId();
                    }
                });
            }
            System.out.println("Entity = " + id);
            return id+1;
        }finally{
            em.close();
        }
    }
    
    public int getGeneralInfoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GeneralInfo> rt = cq.from(GeneralInfo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
