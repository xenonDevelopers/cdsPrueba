/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.AsignacionRecursoplantel;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.RecursoHumano;
import com.utez.secretariaAcademica.entity.Plantel;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionRecursoplantelJpaController implements Serializable {

    public AsignacionRecursoplantelJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionRecursoplantel asignacionRecursoplantel) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RecursoHumano idRecursohumano = asignacionRecursoplantel.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano = em.getReference(idRecursohumano.getClass(), idRecursohumano.getIdRecursohumano());
                asignacionRecursoplantel.setIdRecursohumano(idRecursohumano);
            }
            Plantel idPlantel = asignacionRecursoplantel.getIdPlantel();
            if (idPlantel != null) {
                idPlantel = em.getReference(idPlantel.getClass(), idPlantel.getIdplantel());
                asignacionRecursoplantel.setIdPlantel(idPlantel);
            }
            em.persist(asignacionRecursoplantel);
            if (idRecursohumano != null) {
                idRecursohumano.getAsignacionRecursoplantelList().add(asignacionRecursoplantel);
                idRecursohumano = em.merge(idRecursohumano);
            }
            if (idPlantel != null) {
                idPlantel.getAsignacionRecursoplantelList().add(asignacionRecursoplantel);
                idPlantel = em.merge(idPlantel);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionRecursoplantel asignacionRecursoplantel) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionRecursoplantel persistentAsignacionRecursoplantel = em.find(AsignacionRecursoplantel.class, asignacionRecursoplantel.getIdAsignacionrecursoplantel());
            RecursoHumano idRecursohumanoOld = persistentAsignacionRecursoplantel.getIdRecursohumano();
            RecursoHumano idRecursohumanoNew = asignacionRecursoplantel.getIdRecursohumano();
            Plantel idPlantelOld = persistentAsignacionRecursoplantel.getIdPlantel();
            Plantel idPlantelNew = asignacionRecursoplantel.getIdPlantel();
            if (idRecursohumanoNew != null) {
                idRecursohumanoNew = em.getReference(idRecursohumanoNew.getClass(), idRecursohumanoNew.getIdRecursohumano());
                asignacionRecursoplantel.setIdRecursohumano(idRecursohumanoNew);
            }
            if (idPlantelNew != null) {
                idPlantelNew = em.getReference(idPlantelNew.getClass(), idPlantelNew.getIdplantel());
                asignacionRecursoplantel.setIdPlantel(idPlantelNew);
            }
            asignacionRecursoplantel = em.merge(asignacionRecursoplantel);
            if (idRecursohumanoOld != null && !idRecursohumanoOld.equals(idRecursohumanoNew)) {
                idRecursohumanoOld.getAsignacionRecursoplantelList().remove(asignacionRecursoplantel);
                idRecursohumanoOld = em.merge(idRecursohumanoOld);
            }
            if (idRecursohumanoNew != null && !idRecursohumanoNew.equals(idRecursohumanoOld)) {
                idRecursohumanoNew.getAsignacionRecursoplantelList().add(asignacionRecursoplantel);
                idRecursohumanoNew = em.merge(idRecursohumanoNew);
            }
            if (idPlantelOld != null && !idPlantelOld.equals(idPlantelNew)) {
                idPlantelOld.getAsignacionRecursoplantelList().remove(asignacionRecursoplantel);
                idPlantelOld = em.merge(idPlantelOld);
            }
            if (idPlantelNew != null && !idPlantelNew.equals(idPlantelOld)) {
                idPlantelNew.getAsignacionRecursoplantelList().add(asignacionRecursoplantel);
                idPlantelNew = em.merge(idPlantelNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionRecursoplantel.getIdAsignacionrecursoplantel();
                if (findAsignacionRecursoplantel(id) == null) {
                    throw new NonexistentEntityException("The asignacionRecursoplantel with id " + id + " no longer exists.");
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
            AsignacionRecursoplantel asignacionRecursoplantel;
            try {
                asignacionRecursoplantel = em.getReference(AsignacionRecursoplantel.class, id);
                asignacionRecursoplantel.getIdAsignacionrecursoplantel();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionRecursoplantel with id " + id + " no longer exists.", enfe);
            }
            RecursoHumano idRecursohumano = asignacionRecursoplantel.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano.getAsignacionRecursoplantelList().remove(asignacionRecursoplantel);
                idRecursohumano = em.merge(idRecursohumano);
            }
            Plantel idPlantel = asignacionRecursoplantel.getIdPlantel();
            if (idPlantel != null) {
                idPlantel.getAsignacionRecursoplantelList().remove(asignacionRecursoplantel);
                idPlantel = em.merge(idPlantel);
            }
            em.remove(asignacionRecursoplantel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionRecursoplantel> findAsignacionRecursoplantelEntities() {
        return findAsignacionRecursoplantelEntities(true, -1, -1);
    }

    public List<AsignacionRecursoplantel> findAsignacionRecursoplantelEntities(int maxResults, int firstResult) {
        return findAsignacionRecursoplantelEntities(false, maxResults, firstResult);
    }

    private List<AsignacionRecursoplantel> findAsignacionRecursoplantelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionRecursoplantel as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionRecursoplantel findAsignacionRecursoplantel(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionRecursoplantel.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionRecursoplantelCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionRecursoplantel as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
