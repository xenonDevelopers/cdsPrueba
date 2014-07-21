/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.integracion.entity.InstitucionRegistro;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.PlanEstudio;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class InstitucionRegistroJpaController implements Serializable {

    public InstitucionRegistroJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InstitucionRegistro institucionRegistro) {
        if (institucionRegistro.getPlanEstudioList() == null) {
            institucionRegistro.setPlanEstudioList(new ArrayList<PlanEstudio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PlanEstudio> attachedPlanEstudioList = new ArrayList<PlanEstudio>();
            for (PlanEstudio planEstudioListPlanEstudioToAttach : institucionRegistro.getPlanEstudioList()) {
                planEstudioListPlanEstudioToAttach = em.getReference(planEstudioListPlanEstudioToAttach.getClass(), planEstudioListPlanEstudioToAttach.getIdPlanestudio());
                attachedPlanEstudioList.add(planEstudioListPlanEstudioToAttach);
            }
            institucionRegistro.setPlanEstudioList(attachedPlanEstudioList);
            em.persist(institucionRegistro);
            for (PlanEstudio planEstudioListPlanEstudio : institucionRegistro.getPlanEstudioList()) {
                InstitucionRegistro oldIdInstitucionOfPlanEstudioListPlanEstudio = planEstudioListPlanEstudio.getIdInstitucion();
                planEstudioListPlanEstudio.setIdInstitucion(institucionRegistro);
                planEstudioListPlanEstudio = em.merge(planEstudioListPlanEstudio);
                if (oldIdInstitucionOfPlanEstudioListPlanEstudio != null) {
                    oldIdInstitucionOfPlanEstudioListPlanEstudio.getPlanEstudioList().remove(planEstudioListPlanEstudio);
                    oldIdInstitucionOfPlanEstudioListPlanEstudio = em.merge(oldIdInstitucionOfPlanEstudioListPlanEstudio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InstitucionRegistro institucionRegistro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InstitucionRegistro persistentInstitucionRegistro = em.find(InstitucionRegistro.class, institucionRegistro.getIdInstitucion());
            List<PlanEstudio> planEstudioListOld = persistentInstitucionRegistro.getPlanEstudioList();
            List<PlanEstudio> planEstudioListNew = institucionRegistro.getPlanEstudioList();
            List<PlanEstudio> attachedPlanEstudioListNew = new ArrayList<PlanEstudio>();
            for (PlanEstudio planEstudioListNewPlanEstudioToAttach : planEstudioListNew) {
                planEstudioListNewPlanEstudioToAttach = em.getReference(planEstudioListNewPlanEstudioToAttach.getClass(), planEstudioListNewPlanEstudioToAttach.getIdPlanestudio());
                attachedPlanEstudioListNew.add(planEstudioListNewPlanEstudioToAttach);
            }
            planEstudioListNew = attachedPlanEstudioListNew;
            institucionRegistro.setPlanEstudioList(planEstudioListNew);
            institucionRegistro = em.merge(institucionRegistro);
            for (PlanEstudio planEstudioListOldPlanEstudio : planEstudioListOld) {
                if (!planEstudioListNew.contains(planEstudioListOldPlanEstudio)) {
                    planEstudioListOldPlanEstudio.setIdInstitucion(null);
                    planEstudioListOldPlanEstudio = em.merge(planEstudioListOldPlanEstudio);
                }
            }
            for (PlanEstudio planEstudioListNewPlanEstudio : planEstudioListNew) {
                if (!planEstudioListOld.contains(planEstudioListNewPlanEstudio)) {
                    InstitucionRegistro oldIdInstitucionOfPlanEstudioListNewPlanEstudio = planEstudioListNewPlanEstudio.getIdInstitucion();
                    planEstudioListNewPlanEstudio.setIdInstitucion(institucionRegistro);
                    planEstudioListNewPlanEstudio = em.merge(planEstudioListNewPlanEstudio);
                    if (oldIdInstitucionOfPlanEstudioListNewPlanEstudio != null && !oldIdInstitucionOfPlanEstudioListNewPlanEstudio.equals(institucionRegistro)) {
                        oldIdInstitucionOfPlanEstudioListNewPlanEstudio.getPlanEstudioList().remove(planEstudioListNewPlanEstudio);
                        oldIdInstitucionOfPlanEstudioListNewPlanEstudio = em.merge(oldIdInstitucionOfPlanEstudioListNewPlanEstudio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = institucionRegistro.getIdInstitucion();
                if (findInstitucionRegistro(id) == null) {
                    throw new NonexistentEntityException("The institucionRegistro with id " + id + " no longer exists.");
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
            InstitucionRegistro institucionRegistro;
            try {
                institucionRegistro = em.getReference(InstitucionRegistro.class, id);
                institucionRegistro.getIdInstitucion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The institucionRegistro with id " + id + " no longer exists.", enfe);
            }
            List<PlanEstudio> planEstudioList = institucionRegistro.getPlanEstudioList();
            for (PlanEstudio planEstudioListPlanEstudio : planEstudioList) {
                planEstudioListPlanEstudio.setIdInstitucion(null);
                planEstudioListPlanEstudio = em.merge(planEstudioListPlanEstudio);
            }
            em.remove(institucionRegistro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InstitucionRegistro> findInstitucionRegistroEntities() {
        return findInstitucionRegistroEntities(true, -1, -1);
    }

    public List<InstitucionRegistro> findInstitucionRegistroEntities(int maxResults, int firstResult) {
        return findInstitucionRegistroEntities(false, maxResults, firstResult);
    }

    private List<InstitucionRegistro> findInstitucionRegistroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from InstitucionRegistro as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public InstitucionRegistro findInstitucionRegistro(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InstitucionRegistro.class, id);
        } finally {
            em.close();
        }
    }

    public int getInstitucionRegistroCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from InstitucionRegistro as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
