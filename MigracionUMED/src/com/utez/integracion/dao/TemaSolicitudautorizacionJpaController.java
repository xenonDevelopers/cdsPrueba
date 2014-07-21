/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.SolicitudTitulacion;
import com.utez.integracion.entity.TemaSolicitudautorizacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TemaSolicitudautorizacionJpaController implements Serializable {

    public TemaSolicitudautorizacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TemaSolicitudautorizacion temaSolicitudautorizacion) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        SolicitudTitulacion solicitudTitulacionOrphanCheck = temaSolicitudautorizacion.getSolicitudTitulacion();
        if (solicitudTitulacionOrphanCheck != null) {
            TemaSolicitudautorizacion oldTemaSolicitudautorizacionOfSolicitudTitulacion = solicitudTitulacionOrphanCheck.getTemaSolicitudautorizacion();
            if (oldTemaSolicitudautorizacionOfSolicitudTitulacion != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The SolicitudTitulacion " + solicitudTitulacionOrphanCheck + " already has an item of type TemaSolicitudautorizacion whose solicitudTitulacion column cannot be null. Please make another selection for the solicitudTitulacion field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SolicitudTitulacion solicitudTitulacion = temaSolicitudautorizacion.getSolicitudTitulacion();
            if (solicitudTitulacion != null) {
                solicitudTitulacion = em.getReference(solicitudTitulacion.getClass(), solicitudTitulacion.getIdSolicitudtitulacion());
                temaSolicitudautorizacion.setSolicitudTitulacion(solicitudTitulacion);
            }
            em.persist(temaSolicitudautorizacion);
            if (solicitudTitulacion != null) {
                solicitudTitulacion.setTemaSolicitudautorizacion(temaSolicitudautorizacion);
                solicitudTitulacion = em.merge(solicitudTitulacion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTemaSolicitudautorizacion(temaSolicitudautorizacion.getIdSolicitudtitulacion()) != null) {
                throw new PreexistingEntityException("TemaSolicitudautorizacion " + temaSolicitudautorizacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TemaSolicitudautorizacion temaSolicitudautorizacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TemaSolicitudautorizacion persistentTemaSolicitudautorizacion = em.find(TemaSolicitudautorizacion.class, temaSolicitudautorizacion.getIdSolicitudtitulacion());
            SolicitudTitulacion solicitudTitulacionOld = persistentTemaSolicitudautorizacion.getSolicitudTitulacion();
            SolicitudTitulacion solicitudTitulacionNew = temaSolicitudautorizacion.getSolicitudTitulacion();
            List<String> illegalOrphanMessages = null;
            if (solicitudTitulacionNew != null && !solicitudTitulacionNew.equals(solicitudTitulacionOld)) {
                TemaSolicitudautorizacion oldTemaSolicitudautorizacionOfSolicitudTitulacion = solicitudTitulacionNew.getTemaSolicitudautorizacion();
                if (oldTemaSolicitudautorizacionOfSolicitudTitulacion != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The SolicitudTitulacion " + solicitudTitulacionNew + " already has an item of type TemaSolicitudautorizacion whose solicitudTitulacion column cannot be null. Please make another selection for the solicitudTitulacion field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (solicitudTitulacionNew != null) {
                solicitudTitulacionNew = em.getReference(solicitudTitulacionNew.getClass(), solicitudTitulacionNew.getIdSolicitudtitulacion());
                temaSolicitudautorizacion.setSolicitudTitulacion(solicitudTitulacionNew);
            }
            temaSolicitudautorizacion = em.merge(temaSolicitudautorizacion);
            if (solicitudTitulacionOld != null && !solicitudTitulacionOld.equals(solicitudTitulacionNew)) {
                solicitudTitulacionOld.setTemaSolicitudautorizacion(null);
                solicitudTitulacionOld = em.merge(solicitudTitulacionOld);
            }
            if (solicitudTitulacionNew != null && !solicitudTitulacionNew.equals(solicitudTitulacionOld)) {
                solicitudTitulacionNew.setTemaSolicitudautorizacion(temaSolicitudautorizacion);
                solicitudTitulacionNew = em.merge(solicitudTitulacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = temaSolicitudautorizacion.getIdSolicitudtitulacion();
                if (findTemaSolicitudautorizacion(id) == null) {
                    throw new NonexistentEntityException("The temaSolicitudautorizacion with id " + id + " no longer exists.");
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
            TemaSolicitudautorizacion temaSolicitudautorizacion;
            try {
                temaSolicitudautorizacion = em.getReference(TemaSolicitudautorizacion.class, id);
                temaSolicitudautorizacion.getIdSolicitudtitulacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The temaSolicitudautorizacion with id " + id + " no longer exists.", enfe);
            }
            SolicitudTitulacion solicitudTitulacion = temaSolicitudautorizacion.getSolicitudTitulacion();
            if (solicitudTitulacion != null) {
                solicitudTitulacion.setTemaSolicitudautorizacion(null);
                solicitudTitulacion = em.merge(solicitudTitulacion);
            }
            em.remove(temaSolicitudautorizacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TemaSolicitudautorizacion> findTemaSolicitudautorizacionEntities() {
        return findTemaSolicitudautorizacionEntities(true, -1, -1);
    }

    public List<TemaSolicitudautorizacion> findTemaSolicitudautorizacionEntities(int maxResults, int firstResult) {
        return findTemaSolicitudautorizacionEntities(false, maxResults, firstResult);
    }

    private List<TemaSolicitudautorizacion> findTemaSolicitudautorizacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TemaSolicitudautorizacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TemaSolicitudautorizacion findTemaSolicitudautorizacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TemaSolicitudautorizacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTemaSolicitudautorizacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TemaSolicitudautorizacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
