/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import com.utez.integracion.entity.ObservacionSolicitudtitulacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.SolicitudTitulacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ObservacionSolicitudtitulacionJpaController implements Serializable {

    public ObservacionSolicitudtitulacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ObservacionSolicitudtitulacion observacionSolicitudtitulacion) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        SolicitudTitulacion solicitudTitulacionOrphanCheck = observacionSolicitudtitulacion.getSolicitudTitulacion();
        if (solicitudTitulacionOrphanCheck != null) {
            ObservacionSolicitudtitulacion oldObservacionSolicitudtitulacionOfSolicitudTitulacion = solicitudTitulacionOrphanCheck.getObservacionSolicitudtitulacion();
            if (oldObservacionSolicitudtitulacionOfSolicitudTitulacion != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The SolicitudTitulacion " + solicitudTitulacionOrphanCheck + " already has an item of type ObservacionSolicitudtitulacion whose solicitudTitulacion column cannot be null. Please make another selection for the solicitudTitulacion field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SolicitudTitulacion solicitudTitulacion = observacionSolicitudtitulacion.getSolicitudTitulacion();
            if (solicitudTitulacion != null) {
                solicitudTitulacion = em.getReference(solicitudTitulacion.getClass(), solicitudTitulacion.getIdSolicitudtitulacion());
                observacionSolicitudtitulacion.setSolicitudTitulacion(solicitudTitulacion);
            }
            em.persist(observacionSolicitudtitulacion);
            if (solicitudTitulacion != null) {
                solicitudTitulacion.setObservacionSolicitudtitulacion(observacionSolicitudtitulacion);
                solicitudTitulacion = em.merge(solicitudTitulacion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findObservacionSolicitudtitulacion(observacionSolicitudtitulacion.getIdSolicitudtitulacion()) != null) {
                throw new PreexistingEntityException("ObservacionSolicitudtitulacion " + observacionSolicitudtitulacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ObservacionSolicitudtitulacion observacionSolicitudtitulacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ObservacionSolicitudtitulacion persistentObservacionSolicitudtitulacion = em.find(ObservacionSolicitudtitulacion.class, observacionSolicitudtitulacion.getIdSolicitudtitulacion());
            SolicitudTitulacion solicitudTitulacionOld = persistentObservacionSolicitudtitulacion.getSolicitudTitulacion();
            SolicitudTitulacion solicitudTitulacionNew = observacionSolicitudtitulacion.getSolicitudTitulacion();
            List<String> illegalOrphanMessages = null;
            if (solicitudTitulacionNew != null && !solicitudTitulacionNew.equals(solicitudTitulacionOld)) {
                ObservacionSolicitudtitulacion oldObservacionSolicitudtitulacionOfSolicitudTitulacion = solicitudTitulacionNew.getObservacionSolicitudtitulacion();
                if (oldObservacionSolicitudtitulacionOfSolicitudTitulacion != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The SolicitudTitulacion " + solicitudTitulacionNew + " already has an item of type ObservacionSolicitudtitulacion whose solicitudTitulacion column cannot be null. Please make another selection for the solicitudTitulacion field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (solicitudTitulacionNew != null) {
                solicitudTitulacionNew = em.getReference(solicitudTitulacionNew.getClass(), solicitudTitulacionNew.getIdSolicitudtitulacion());
                observacionSolicitudtitulacion.setSolicitudTitulacion(solicitudTitulacionNew);
            }
            observacionSolicitudtitulacion = em.merge(observacionSolicitudtitulacion);
            if (solicitudTitulacionOld != null && !solicitudTitulacionOld.equals(solicitudTitulacionNew)) {
                solicitudTitulacionOld.setObservacionSolicitudtitulacion(null);
                solicitudTitulacionOld = em.merge(solicitudTitulacionOld);
            }
            if (solicitudTitulacionNew != null && !solicitudTitulacionNew.equals(solicitudTitulacionOld)) {
                solicitudTitulacionNew.setObservacionSolicitudtitulacion(observacionSolicitudtitulacion);
                solicitudTitulacionNew = em.merge(solicitudTitulacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = observacionSolicitudtitulacion.getIdSolicitudtitulacion();
                if (findObservacionSolicitudtitulacion(id) == null) {
                    throw new NonexistentEntityException("The observacionSolicitudtitulacion with id " + id + " no longer exists.");
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
            ObservacionSolicitudtitulacion observacionSolicitudtitulacion;
            try {
                observacionSolicitudtitulacion = em.getReference(ObservacionSolicitudtitulacion.class, id);
                observacionSolicitudtitulacion.getIdSolicitudtitulacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The observacionSolicitudtitulacion with id " + id + " no longer exists.", enfe);
            }
            SolicitudTitulacion solicitudTitulacion = observacionSolicitudtitulacion.getSolicitudTitulacion();
            if (solicitudTitulacion != null) {
                solicitudTitulacion.setObservacionSolicitudtitulacion(null);
                solicitudTitulacion = em.merge(solicitudTitulacion);
            }
            em.remove(observacionSolicitudtitulacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ObservacionSolicitudtitulacion> findObservacionSolicitudtitulacionEntities() {
        return findObservacionSolicitudtitulacionEntities(true, -1, -1);
    }

    public List<ObservacionSolicitudtitulacion> findObservacionSolicitudtitulacionEntities(int maxResults, int firstResult) {
        return findObservacionSolicitudtitulacionEntities(false, maxResults, firstResult);
    }

    private List<ObservacionSolicitudtitulacion> findObservacionSolicitudtitulacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ObservacionSolicitudtitulacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ObservacionSolicitudtitulacion findObservacionSolicitudtitulacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ObservacionSolicitudtitulacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getObservacionSolicitudtitulacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ObservacionSolicitudtitulacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
