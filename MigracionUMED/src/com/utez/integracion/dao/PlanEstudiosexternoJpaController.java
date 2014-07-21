/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.PlanEstudiosexterno;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.UniversidadExterna;
import com.utez.integracion.entity.PosgradoSolicitudautorizacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class PlanEstudiosexternoJpaController implements Serializable {

    public PlanEstudiosexternoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PlanEstudiosexterno planEstudiosexterno) {
        if (planEstudiosexterno.getPosgradoSolicitudautorizacionList() == null) {
            planEstudiosexterno.setPosgradoSolicitudautorizacionList(new ArrayList<PosgradoSolicitudautorizacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UniversidadExterna idUniversidadexterna = planEstudiosexterno.getIdUniversidadexterna();
            if (idUniversidadexterna != null) {
                idUniversidadexterna = em.getReference(idUniversidadexterna.getClass(), idUniversidadexterna.getIdUniversidadexterna());
                planEstudiosexterno.setIdUniversidadexterna(idUniversidadexterna);
            }
            List<PosgradoSolicitudautorizacion> attachedPosgradoSolicitudautorizacionList = new ArrayList<PosgradoSolicitudautorizacion>();
            for (PosgradoSolicitudautorizacion posgradoSolicitudautorizacionListPosgradoSolicitudautorizacionToAttach : planEstudiosexterno.getPosgradoSolicitudautorizacionList()) {
                posgradoSolicitudautorizacionListPosgradoSolicitudautorizacionToAttach = em.getReference(posgradoSolicitudautorizacionListPosgradoSolicitudautorizacionToAttach.getClass(), posgradoSolicitudautorizacionListPosgradoSolicitudautorizacionToAttach.getIdSolicitudtitulacion());
                attachedPosgradoSolicitudautorizacionList.add(posgradoSolicitudautorizacionListPosgradoSolicitudautorizacionToAttach);
            }
            planEstudiosexterno.setPosgradoSolicitudautorizacionList(attachedPosgradoSolicitudautorizacionList);
            em.persist(planEstudiosexterno);
            if (idUniversidadexterna != null) {
                idUniversidadexterna.getPlanEstudiosexternoList().add(planEstudiosexterno);
                idUniversidadexterna = em.merge(idUniversidadexterna);
            }
            for (PosgradoSolicitudautorizacion posgradoSolicitudautorizacionListPosgradoSolicitudautorizacion : planEstudiosexterno.getPosgradoSolicitudautorizacionList()) {
                PlanEstudiosexterno oldIdPlanestudiosexternoOfPosgradoSolicitudautorizacionListPosgradoSolicitudautorizacion = posgradoSolicitudautorizacionListPosgradoSolicitudautorizacion.getIdPlanestudiosexterno();
                posgradoSolicitudautorizacionListPosgradoSolicitudautorizacion.setIdPlanestudiosexterno(planEstudiosexterno);
                posgradoSolicitudautorizacionListPosgradoSolicitudautorizacion = em.merge(posgradoSolicitudautorizacionListPosgradoSolicitudautorizacion);
                if (oldIdPlanestudiosexternoOfPosgradoSolicitudautorizacionListPosgradoSolicitudautorizacion != null) {
                    oldIdPlanestudiosexternoOfPosgradoSolicitudautorizacionListPosgradoSolicitudautorizacion.getPosgradoSolicitudautorizacionList().remove(posgradoSolicitudautorizacionListPosgradoSolicitudautorizacion);
                    oldIdPlanestudiosexternoOfPosgradoSolicitudautorizacionListPosgradoSolicitudautorizacion = em.merge(oldIdPlanestudiosexternoOfPosgradoSolicitudautorizacionListPosgradoSolicitudautorizacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PlanEstudiosexterno planEstudiosexterno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PlanEstudiosexterno persistentPlanEstudiosexterno = em.find(PlanEstudiosexterno.class, planEstudiosexterno.getIdPlanestudiosexterno());
            UniversidadExterna idUniversidadexternaOld = persistentPlanEstudiosexterno.getIdUniversidadexterna();
            UniversidadExterna idUniversidadexternaNew = planEstudiosexterno.getIdUniversidadexterna();
            List<PosgradoSolicitudautorizacion> posgradoSolicitudautorizacionListOld = persistentPlanEstudiosexterno.getPosgradoSolicitudautorizacionList();
            List<PosgradoSolicitudautorizacion> posgradoSolicitudautorizacionListNew = planEstudiosexterno.getPosgradoSolicitudautorizacionList();
            if (idUniversidadexternaNew != null) {
                idUniversidadexternaNew = em.getReference(idUniversidadexternaNew.getClass(), idUniversidadexternaNew.getIdUniversidadexterna());
                planEstudiosexterno.setIdUniversidadexterna(idUniversidadexternaNew);
            }
            List<PosgradoSolicitudautorizacion> attachedPosgradoSolicitudautorizacionListNew = new ArrayList<PosgradoSolicitudautorizacion>();
            for (PosgradoSolicitudautorizacion posgradoSolicitudautorizacionListNewPosgradoSolicitudautorizacionToAttach : posgradoSolicitudautorizacionListNew) {
                posgradoSolicitudautorizacionListNewPosgradoSolicitudautorizacionToAttach = em.getReference(posgradoSolicitudautorizacionListNewPosgradoSolicitudautorizacionToAttach.getClass(), posgradoSolicitudautorizacionListNewPosgradoSolicitudautorizacionToAttach.getIdSolicitudtitulacion());
                attachedPosgradoSolicitudautorizacionListNew.add(posgradoSolicitudautorizacionListNewPosgradoSolicitudautorizacionToAttach);
            }
            posgradoSolicitudautorizacionListNew = attachedPosgradoSolicitudautorizacionListNew;
            planEstudiosexterno.setPosgradoSolicitudautorizacionList(posgradoSolicitudautorizacionListNew);
            planEstudiosexterno = em.merge(planEstudiosexterno);
            if (idUniversidadexternaOld != null && !idUniversidadexternaOld.equals(idUniversidadexternaNew)) {
                idUniversidadexternaOld.getPlanEstudiosexternoList().remove(planEstudiosexterno);
                idUniversidadexternaOld = em.merge(idUniversidadexternaOld);
            }
            if (idUniversidadexternaNew != null && !idUniversidadexternaNew.equals(idUniversidadexternaOld)) {
                idUniversidadexternaNew.getPlanEstudiosexternoList().add(planEstudiosexterno);
                idUniversidadexternaNew = em.merge(idUniversidadexternaNew);
            }
            for (PosgradoSolicitudautorizacion posgradoSolicitudautorizacionListOldPosgradoSolicitudautorizacion : posgradoSolicitudautorizacionListOld) {
                if (!posgradoSolicitudautorizacionListNew.contains(posgradoSolicitudautorizacionListOldPosgradoSolicitudautorizacion)) {
                    posgradoSolicitudautorizacionListOldPosgradoSolicitudautorizacion.setIdPlanestudiosexterno(null);
                    posgradoSolicitudautorizacionListOldPosgradoSolicitudautorizacion = em.merge(posgradoSolicitudautorizacionListOldPosgradoSolicitudautorizacion);
                }
            }
            for (PosgradoSolicitudautorizacion posgradoSolicitudautorizacionListNewPosgradoSolicitudautorizacion : posgradoSolicitudautorizacionListNew) {
                if (!posgradoSolicitudautorizacionListOld.contains(posgradoSolicitudautorizacionListNewPosgradoSolicitudautorizacion)) {
                    PlanEstudiosexterno oldIdPlanestudiosexternoOfPosgradoSolicitudautorizacionListNewPosgradoSolicitudautorizacion = posgradoSolicitudautorizacionListNewPosgradoSolicitudautorizacion.getIdPlanestudiosexterno();
                    posgradoSolicitudautorizacionListNewPosgradoSolicitudautorizacion.setIdPlanestudiosexterno(planEstudiosexterno);
                    posgradoSolicitudautorizacionListNewPosgradoSolicitudautorizacion = em.merge(posgradoSolicitudautorizacionListNewPosgradoSolicitudautorizacion);
                    if (oldIdPlanestudiosexternoOfPosgradoSolicitudautorizacionListNewPosgradoSolicitudautorizacion != null && !oldIdPlanestudiosexternoOfPosgradoSolicitudautorizacionListNewPosgradoSolicitudautorizacion.equals(planEstudiosexterno)) {
                        oldIdPlanestudiosexternoOfPosgradoSolicitudautorizacionListNewPosgradoSolicitudautorizacion.getPosgradoSolicitudautorizacionList().remove(posgradoSolicitudautorizacionListNewPosgradoSolicitudautorizacion);
                        oldIdPlanestudiosexternoOfPosgradoSolicitudautorizacionListNewPosgradoSolicitudautorizacion = em.merge(oldIdPlanestudiosexternoOfPosgradoSolicitudautorizacionListNewPosgradoSolicitudautorizacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = planEstudiosexterno.getIdPlanestudiosexterno();
                if (findPlanEstudiosexterno(id) == null) {
                    throw new NonexistentEntityException("The planEstudiosexterno with id " + id + " no longer exists.");
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
            PlanEstudiosexterno planEstudiosexterno;
            try {
                planEstudiosexterno = em.getReference(PlanEstudiosexterno.class, id);
                planEstudiosexterno.getIdPlanestudiosexterno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planEstudiosexterno with id " + id + " no longer exists.", enfe);
            }
            UniversidadExterna idUniversidadexterna = planEstudiosexterno.getIdUniversidadexterna();
            if (idUniversidadexterna != null) {
                idUniversidadexterna.getPlanEstudiosexternoList().remove(planEstudiosexterno);
                idUniversidadexterna = em.merge(idUniversidadexterna);
            }
            List<PosgradoSolicitudautorizacion> posgradoSolicitudautorizacionList = planEstudiosexterno.getPosgradoSolicitudautorizacionList();
            for (PosgradoSolicitudautorizacion posgradoSolicitudautorizacionListPosgradoSolicitudautorizacion : posgradoSolicitudautorizacionList) {
                posgradoSolicitudautorizacionListPosgradoSolicitudautorizacion.setIdPlanestudiosexterno(null);
                posgradoSolicitudautorizacionListPosgradoSolicitudautorizacion = em.merge(posgradoSolicitudautorizacionListPosgradoSolicitudautorizacion);
            }
            em.remove(planEstudiosexterno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PlanEstudiosexterno> findPlanEstudiosexternoEntities() {
        return findPlanEstudiosexternoEntities(true, -1, -1);
    }

    public List<PlanEstudiosexterno> findPlanEstudiosexternoEntities(int maxResults, int firstResult) {
        return findPlanEstudiosexternoEntities(false, maxResults, firstResult);
    }

    private List<PlanEstudiosexterno> findPlanEstudiosexternoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from PlanEstudiosexterno as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PlanEstudiosexterno findPlanEstudiosexterno(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PlanEstudiosexterno.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanEstudiosexternoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from PlanEstudiosexterno as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
