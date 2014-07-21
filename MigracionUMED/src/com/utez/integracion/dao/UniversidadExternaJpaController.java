/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.PlanEstudiosexterno;
import com.utez.integracion.entity.UniversidadExterna;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class UniversidadExternaJpaController implements Serializable {

    public UniversidadExternaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UniversidadExterna universidadExterna) {
        if (universidadExterna.getPlanEstudiosexternoList() == null) {
            universidadExterna.setPlanEstudiosexternoList(new ArrayList<PlanEstudiosexterno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PlanEstudiosexterno> attachedPlanEstudiosexternoList = new ArrayList<PlanEstudiosexterno>();
            for (PlanEstudiosexterno planEstudiosexternoListPlanEstudiosexternoToAttach : universidadExterna.getPlanEstudiosexternoList()) {
                planEstudiosexternoListPlanEstudiosexternoToAttach = em.getReference(planEstudiosexternoListPlanEstudiosexternoToAttach.getClass(), planEstudiosexternoListPlanEstudiosexternoToAttach.getIdPlanestudiosexterno());
                attachedPlanEstudiosexternoList.add(planEstudiosexternoListPlanEstudiosexternoToAttach);
            }
            universidadExterna.setPlanEstudiosexternoList(attachedPlanEstudiosexternoList);
            em.persist(universidadExterna);
            for (PlanEstudiosexterno planEstudiosexternoListPlanEstudiosexterno : universidadExterna.getPlanEstudiosexternoList()) {
                UniversidadExterna oldIdUniversidadexternaOfPlanEstudiosexternoListPlanEstudiosexterno = planEstudiosexternoListPlanEstudiosexterno.getIdUniversidadexterna();
                planEstudiosexternoListPlanEstudiosexterno.setIdUniversidadexterna(universidadExterna);
                planEstudiosexternoListPlanEstudiosexterno = em.merge(planEstudiosexternoListPlanEstudiosexterno);
                if (oldIdUniversidadexternaOfPlanEstudiosexternoListPlanEstudiosexterno != null) {
                    oldIdUniversidadexternaOfPlanEstudiosexternoListPlanEstudiosexterno.getPlanEstudiosexternoList().remove(planEstudiosexternoListPlanEstudiosexterno);
                    oldIdUniversidadexternaOfPlanEstudiosexternoListPlanEstudiosexterno = em.merge(oldIdUniversidadexternaOfPlanEstudiosexternoListPlanEstudiosexterno);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UniversidadExterna universidadExterna) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UniversidadExterna persistentUniversidadExterna = em.find(UniversidadExterna.class, universidadExterna.getIdUniversidadexterna());
            List<PlanEstudiosexterno> planEstudiosexternoListOld = persistentUniversidadExterna.getPlanEstudiosexternoList();
            List<PlanEstudiosexterno> planEstudiosexternoListNew = universidadExterna.getPlanEstudiosexternoList();
            List<PlanEstudiosexterno> attachedPlanEstudiosexternoListNew = new ArrayList<PlanEstudiosexterno>();
            for (PlanEstudiosexterno planEstudiosexternoListNewPlanEstudiosexternoToAttach : planEstudiosexternoListNew) {
                planEstudiosexternoListNewPlanEstudiosexternoToAttach = em.getReference(planEstudiosexternoListNewPlanEstudiosexternoToAttach.getClass(), planEstudiosexternoListNewPlanEstudiosexternoToAttach.getIdPlanestudiosexterno());
                attachedPlanEstudiosexternoListNew.add(planEstudiosexternoListNewPlanEstudiosexternoToAttach);
            }
            planEstudiosexternoListNew = attachedPlanEstudiosexternoListNew;
            universidadExterna.setPlanEstudiosexternoList(planEstudiosexternoListNew);
            universidadExterna = em.merge(universidadExterna);
            for (PlanEstudiosexterno planEstudiosexternoListOldPlanEstudiosexterno : planEstudiosexternoListOld) {
                if (!planEstudiosexternoListNew.contains(planEstudiosexternoListOldPlanEstudiosexterno)) {
                    planEstudiosexternoListOldPlanEstudiosexterno.setIdUniversidadexterna(null);
                    planEstudiosexternoListOldPlanEstudiosexterno = em.merge(planEstudiosexternoListOldPlanEstudiosexterno);
                }
            }
            for (PlanEstudiosexterno planEstudiosexternoListNewPlanEstudiosexterno : planEstudiosexternoListNew) {
                if (!planEstudiosexternoListOld.contains(planEstudiosexternoListNewPlanEstudiosexterno)) {
                    UniversidadExterna oldIdUniversidadexternaOfPlanEstudiosexternoListNewPlanEstudiosexterno = planEstudiosexternoListNewPlanEstudiosexterno.getIdUniversidadexterna();
                    planEstudiosexternoListNewPlanEstudiosexterno.setIdUniversidadexterna(universidadExterna);
                    planEstudiosexternoListNewPlanEstudiosexterno = em.merge(planEstudiosexternoListNewPlanEstudiosexterno);
                    if (oldIdUniversidadexternaOfPlanEstudiosexternoListNewPlanEstudiosexterno != null && !oldIdUniversidadexternaOfPlanEstudiosexternoListNewPlanEstudiosexterno.equals(universidadExterna)) {
                        oldIdUniversidadexternaOfPlanEstudiosexternoListNewPlanEstudiosexterno.getPlanEstudiosexternoList().remove(planEstudiosexternoListNewPlanEstudiosexterno);
                        oldIdUniversidadexternaOfPlanEstudiosexternoListNewPlanEstudiosexterno = em.merge(oldIdUniversidadexternaOfPlanEstudiosexternoListNewPlanEstudiosexterno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = universidadExterna.getIdUniversidadexterna();
                if (findUniversidadExterna(id) == null) {
                    throw new NonexistentEntityException("The universidadExterna with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UniversidadExterna universidadExterna;
            try {
                universidadExterna = em.getReference(UniversidadExterna.class, id);
                universidadExterna.getIdUniversidadexterna();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The universidadExterna with id " + id + " no longer exists.", enfe);
            }
            List<PlanEstudiosexterno> planEstudiosexternoList = universidadExterna.getPlanEstudiosexternoList();
            for (PlanEstudiosexterno planEstudiosexternoListPlanEstudiosexterno : planEstudiosexternoList) {
                planEstudiosexternoListPlanEstudiosexterno.setIdUniversidadexterna(null);
                planEstudiosexternoListPlanEstudiosexterno = em.merge(planEstudiosexternoListPlanEstudiosexterno);
            }
            em.remove(universidadExterna);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UniversidadExterna> findUniversidadExternaEntities() {
        return findUniversidadExternaEntities(true, -1, -1);
    }

    public List<UniversidadExterna> findUniversidadExternaEntities(int maxResults, int firstResult) {
        return findUniversidadExternaEntities(false, maxResults, firstResult);
    }

    private List<UniversidadExterna> findUniversidadExternaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from UniversidadExterna as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public UniversidadExterna findUniversidadExterna(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UniversidadExterna.class, id);
        } finally {
            em.close();
        }
    }

    public int getUniversidadExternaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from UniversidadExterna as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
