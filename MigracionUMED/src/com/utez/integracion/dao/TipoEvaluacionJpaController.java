/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.AsignacionEvaluacion;
import com.utez.integracion.entity.TipoEvaluacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoEvaluacionJpaController implements Serializable {

    public TipoEvaluacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoEvaluacion tipoEvaluacion) {
        if (tipoEvaluacion.getAsignacionEvaluacionList() == null) {
            tipoEvaluacion.setAsignacionEvaluacionList(new ArrayList<AsignacionEvaluacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<AsignacionEvaluacion> attachedAsignacionEvaluacionList = new ArrayList<AsignacionEvaluacion>();
            for (AsignacionEvaluacion asignacionEvaluacionListAsignacionEvaluacionToAttach : tipoEvaluacion.getAsignacionEvaluacionList()) {
                asignacionEvaluacionListAsignacionEvaluacionToAttach = em.getReference(asignacionEvaluacionListAsignacionEvaluacionToAttach.getClass(), asignacionEvaluacionListAsignacionEvaluacionToAttach.getIdAsignacionevaluacion());
                attachedAsignacionEvaluacionList.add(asignacionEvaluacionListAsignacionEvaluacionToAttach);
            }
            tipoEvaluacion.setAsignacionEvaluacionList(attachedAsignacionEvaluacionList);
            em.persist(tipoEvaluacion);
            for (AsignacionEvaluacion asignacionEvaluacionListAsignacionEvaluacion : tipoEvaluacion.getAsignacionEvaluacionList()) {
                TipoEvaluacion oldIdTipoevaluacionOfAsignacionEvaluacionListAsignacionEvaluacion = asignacionEvaluacionListAsignacionEvaluacion.getIdTipoevaluacion();
                asignacionEvaluacionListAsignacionEvaluacion.setIdTipoevaluacion(tipoEvaluacion);
                asignacionEvaluacionListAsignacionEvaluacion = em.merge(asignacionEvaluacionListAsignacionEvaluacion);
                if (oldIdTipoevaluacionOfAsignacionEvaluacionListAsignacionEvaluacion != null) {
                    oldIdTipoevaluacionOfAsignacionEvaluacionListAsignacionEvaluacion.getAsignacionEvaluacionList().remove(asignacionEvaluacionListAsignacionEvaluacion);
                    oldIdTipoevaluacionOfAsignacionEvaluacionListAsignacionEvaluacion = em.merge(oldIdTipoevaluacionOfAsignacionEvaluacionListAsignacionEvaluacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoEvaluacion tipoEvaluacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoEvaluacion persistentTipoEvaluacion = em.find(TipoEvaluacion.class, tipoEvaluacion.getIdTipoevaluacion());
            List<AsignacionEvaluacion> asignacionEvaluacionListOld = persistentTipoEvaluacion.getAsignacionEvaluacionList();
            List<AsignacionEvaluacion> asignacionEvaluacionListNew = tipoEvaluacion.getAsignacionEvaluacionList();
            List<AsignacionEvaluacion> attachedAsignacionEvaluacionListNew = new ArrayList<AsignacionEvaluacion>();
            for (AsignacionEvaluacion asignacionEvaluacionListNewAsignacionEvaluacionToAttach : asignacionEvaluacionListNew) {
                asignacionEvaluacionListNewAsignacionEvaluacionToAttach = em.getReference(asignacionEvaluacionListNewAsignacionEvaluacionToAttach.getClass(), asignacionEvaluacionListNewAsignacionEvaluacionToAttach.getIdAsignacionevaluacion());
                attachedAsignacionEvaluacionListNew.add(asignacionEvaluacionListNewAsignacionEvaluacionToAttach);
            }
            asignacionEvaluacionListNew = attachedAsignacionEvaluacionListNew;
            tipoEvaluacion.setAsignacionEvaluacionList(asignacionEvaluacionListNew);
            tipoEvaluacion = em.merge(tipoEvaluacion);
            for (AsignacionEvaluacion asignacionEvaluacionListOldAsignacionEvaluacion : asignacionEvaluacionListOld) {
                if (!asignacionEvaluacionListNew.contains(asignacionEvaluacionListOldAsignacionEvaluacion)) {
                    asignacionEvaluacionListOldAsignacionEvaluacion.setIdTipoevaluacion(null);
                    asignacionEvaluacionListOldAsignacionEvaluacion = em.merge(asignacionEvaluacionListOldAsignacionEvaluacion);
                }
            }
            for (AsignacionEvaluacion asignacionEvaluacionListNewAsignacionEvaluacion : asignacionEvaluacionListNew) {
                if (!asignacionEvaluacionListOld.contains(asignacionEvaluacionListNewAsignacionEvaluacion)) {
                    TipoEvaluacion oldIdTipoevaluacionOfAsignacionEvaluacionListNewAsignacionEvaluacion = asignacionEvaluacionListNewAsignacionEvaluacion.getIdTipoevaluacion();
                    asignacionEvaluacionListNewAsignacionEvaluacion.setIdTipoevaluacion(tipoEvaluacion);
                    asignacionEvaluacionListNewAsignacionEvaluacion = em.merge(asignacionEvaluacionListNewAsignacionEvaluacion);
                    if (oldIdTipoevaluacionOfAsignacionEvaluacionListNewAsignacionEvaluacion != null && !oldIdTipoevaluacionOfAsignacionEvaluacionListNewAsignacionEvaluacion.equals(tipoEvaluacion)) {
                        oldIdTipoevaluacionOfAsignacionEvaluacionListNewAsignacionEvaluacion.getAsignacionEvaluacionList().remove(asignacionEvaluacionListNewAsignacionEvaluacion);
                        oldIdTipoevaluacionOfAsignacionEvaluacionListNewAsignacionEvaluacion = em.merge(oldIdTipoevaluacionOfAsignacionEvaluacionListNewAsignacionEvaluacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoEvaluacion.getIdTipoevaluacion();
                if (findTipoEvaluacion(id) == null) {
                    throw new NonexistentEntityException("The tipoEvaluacion with id " + id + " no longer exists.");
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
            TipoEvaluacion tipoEvaluacion;
            try {
                tipoEvaluacion = em.getReference(TipoEvaluacion.class, id);
                tipoEvaluacion.getIdTipoevaluacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoEvaluacion with id " + id + " no longer exists.", enfe);
            }
            List<AsignacionEvaluacion> asignacionEvaluacionList = tipoEvaluacion.getAsignacionEvaluacionList();
            for (AsignacionEvaluacion asignacionEvaluacionListAsignacionEvaluacion : asignacionEvaluacionList) {
                asignacionEvaluacionListAsignacionEvaluacion.setIdTipoevaluacion(null);
                asignacionEvaluacionListAsignacionEvaluacion = em.merge(asignacionEvaluacionListAsignacionEvaluacion);
            }
            em.remove(tipoEvaluacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoEvaluacion> findTipoEvaluacionEntities() {
        return findTipoEvaluacionEntities(true, -1, -1);
    }

    public List<TipoEvaluacion> findTipoEvaluacionEntities(int maxResults, int firstResult) {
        return findTipoEvaluacionEntities(false, maxResults, firstResult);
    }

    private List<TipoEvaluacion> findTipoEvaluacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoEvaluacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoEvaluacion findTipoEvaluacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoEvaluacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoEvaluacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoEvaluacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
