/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.AsignacionAsesorplan;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.PlanEstudio;
import com.utez.integracion.entity.FormacionAcademica;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionAsesorplanJpaController implements Serializable {

    public AsignacionAsesorplanJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionAsesorplan asignacionAsesorplan) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PlanEstudio idPlanestudio = asignacionAsesorplan.getIdPlanestudio();
            if (idPlanestudio != null) {
                idPlanestudio = em.getReference(idPlanestudio.getClass(), idPlanestudio.getIdPlanestudio());
                asignacionAsesorplan.setIdPlanestudio(idPlanestudio);
            }
            FormacionAcademica idFormacionacademica = asignacionAsesorplan.getIdFormacionacademica();
            if (idFormacionacademica != null) {
                idFormacionacademica = em.getReference(idFormacionacademica.getClass(), idFormacionacademica.getIdFormacionacademica());
                asignacionAsesorplan.setIdFormacionacademica(idFormacionacademica);
            }
            em.persist(asignacionAsesorplan);
            if (idPlanestudio != null) {
                idPlanestudio.getAsignacionAsesorplanList().add(asignacionAsesorplan);
                idPlanestudio = em.merge(idPlanestudio);
            }
            if (idFormacionacademica != null) {
                idFormacionacademica.getAsignacionAsesorplanList().add(asignacionAsesorplan);
                idFormacionacademica = em.merge(idFormacionacademica);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionAsesorplan asignacionAsesorplan) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionAsesorplan persistentAsignacionAsesorplan = em.find(AsignacionAsesorplan.class, asignacionAsesorplan.getIdAsignacionasesorplan());
            PlanEstudio idPlanestudioOld = persistentAsignacionAsesorplan.getIdPlanestudio();
            PlanEstudio idPlanestudioNew = asignacionAsesorplan.getIdPlanestudio();
            FormacionAcademica idFormacionacademicaOld = persistentAsignacionAsesorplan.getIdFormacionacademica();
            FormacionAcademica idFormacionacademicaNew = asignacionAsesorplan.getIdFormacionacademica();
            if (idPlanestudioNew != null) {
                idPlanestudioNew = em.getReference(idPlanestudioNew.getClass(), idPlanestudioNew.getIdPlanestudio());
                asignacionAsesorplan.setIdPlanestudio(idPlanestudioNew);
            }
            if (idFormacionacademicaNew != null) {
                idFormacionacademicaNew = em.getReference(idFormacionacademicaNew.getClass(), idFormacionacademicaNew.getIdFormacionacademica());
                asignacionAsesorplan.setIdFormacionacademica(idFormacionacademicaNew);
            }
            asignacionAsesorplan = em.merge(asignacionAsesorplan);
            if (idPlanestudioOld != null && !idPlanestudioOld.equals(idPlanestudioNew)) {
                idPlanestudioOld.getAsignacionAsesorplanList().remove(asignacionAsesorplan);
                idPlanestudioOld = em.merge(idPlanestudioOld);
            }
            if (idPlanestudioNew != null && !idPlanestudioNew.equals(idPlanestudioOld)) {
                idPlanestudioNew.getAsignacionAsesorplanList().add(asignacionAsesorplan);
                idPlanestudioNew = em.merge(idPlanestudioNew);
            }
            if (idFormacionacademicaOld != null && !idFormacionacademicaOld.equals(idFormacionacademicaNew)) {
                idFormacionacademicaOld.getAsignacionAsesorplanList().remove(asignacionAsesorplan);
                idFormacionacademicaOld = em.merge(idFormacionacademicaOld);
            }
            if (idFormacionacademicaNew != null && !idFormacionacademicaNew.equals(idFormacionacademicaOld)) {
                idFormacionacademicaNew.getAsignacionAsesorplanList().add(asignacionAsesorplan);
                idFormacionacademicaNew = em.merge(idFormacionacademicaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionAsesorplan.getIdAsignacionasesorplan();
                if (findAsignacionAsesorplan(id) == null) {
                    throw new NonexistentEntityException("The asignacionAsesorplan with id " + id + " no longer exists.");
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
            AsignacionAsesorplan asignacionAsesorplan;
            try {
                asignacionAsesorplan = em.getReference(AsignacionAsesorplan.class, id);
                asignacionAsesorplan.getIdAsignacionasesorplan();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionAsesorplan with id " + id + " no longer exists.", enfe);
            }
            PlanEstudio idPlanestudio = asignacionAsesorplan.getIdPlanestudio();
            if (idPlanestudio != null) {
                idPlanestudio.getAsignacionAsesorplanList().remove(asignacionAsesorplan);
                idPlanestudio = em.merge(idPlanestudio);
            }
            FormacionAcademica idFormacionacademica = asignacionAsesorplan.getIdFormacionacademica();
            if (idFormacionacademica != null) {
                idFormacionacademica.getAsignacionAsesorplanList().remove(asignacionAsesorplan);
                idFormacionacademica = em.merge(idFormacionacademica);
            }
            em.remove(asignacionAsesorplan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionAsesorplan> findAsignacionAsesorplanEntities() {
        return findAsignacionAsesorplanEntities(true, -1, -1);
    }

    public List<AsignacionAsesorplan> findAsignacionAsesorplanEntities(int maxResults, int firstResult) {
        return findAsignacionAsesorplanEntities(false, maxResults, firstResult);
    }

    private List<AsignacionAsesorplan> findAsignacionAsesorplanEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionAsesorplan as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionAsesorplan findAsignacionAsesorplan(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionAsesorplan.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionAsesorplanCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionAsesorplan as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
