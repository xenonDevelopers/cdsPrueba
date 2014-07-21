/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.ArchivoSolicitudprogramacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.SolicitudProgramacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ArchivoSolicitudprogramacionJpaController implements Serializable {

    public ArchivoSolicitudprogramacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ArchivoSolicitudprogramacion archivoSolicitudprogramacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SolicitudProgramacion idSolicitudprogramacion = archivoSolicitudprogramacion.getIdSolicitudprogramacion();
            if (idSolicitudprogramacion != null) {
                idSolicitudprogramacion = em.getReference(idSolicitudprogramacion.getClass(), idSolicitudprogramacion.getIdSolicitudprogramacion());
                archivoSolicitudprogramacion.setIdSolicitudprogramacion(idSolicitudprogramacion);
            }
            em.persist(archivoSolicitudprogramacion);
            if (idSolicitudprogramacion != null) {
                idSolicitudprogramacion.getArchivoSolicitudprogramacionList().add(archivoSolicitudprogramacion);
                idSolicitudprogramacion = em.merge(idSolicitudprogramacion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ArchivoSolicitudprogramacion archivoSolicitudprogramacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArchivoSolicitudprogramacion persistentArchivoSolicitudprogramacion = em.find(ArchivoSolicitudprogramacion.class, archivoSolicitudprogramacion.getIdArchivosolicitudprogramacion());
            SolicitudProgramacion idSolicitudprogramacionOld = persistentArchivoSolicitudprogramacion.getIdSolicitudprogramacion();
            SolicitudProgramacion idSolicitudprogramacionNew = archivoSolicitudprogramacion.getIdSolicitudprogramacion();
            if (idSolicitudprogramacionNew != null) {
                idSolicitudprogramacionNew = em.getReference(idSolicitudprogramacionNew.getClass(), idSolicitudprogramacionNew.getIdSolicitudprogramacion());
                archivoSolicitudprogramacion.setIdSolicitudprogramacion(idSolicitudprogramacionNew);
            }
            archivoSolicitudprogramacion = em.merge(archivoSolicitudprogramacion);
            if (idSolicitudprogramacionOld != null && !idSolicitudprogramacionOld.equals(idSolicitudprogramacionNew)) {
                idSolicitudprogramacionOld.getArchivoSolicitudprogramacionList().remove(archivoSolicitudprogramacion);
                idSolicitudprogramacionOld = em.merge(idSolicitudprogramacionOld);
            }
            if (idSolicitudprogramacionNew != null && !idSolicitudprogramacionNew.equals(idSolicitudprogramacionOld)) {
                idSolicitudprogramacionNew.getArchivoSolicitudprogramacionList().add(archivoSolicitudprogramacion);
                idSolicitudprogramacionNew = em.merge(idSolicitudprogramacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = archivoSolicitudprogramacion.getIdArchivosolicitudprogramacion();
                if (findArchivoSolicitudprogramacion(id) == null) {
                    throw new NonexistentEntityException("The archivoSolicitudprogramacion with id " + id + " no longer exists.");
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
            ArchivoSolicitudprogramacion archivoSolicitudprogramacion;
            try {
                archivoSolicitudprogramacion = em.getReference(ArchivoSolicitudprogramacion.class, id);
                archivoSolicitudprogramacion.getIdArchivosolicitudprogramacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The archivoSolicitudprogramacion with id " + id + " no longer exists.", enfe);
            }
            SolicitudProgramacion idSolicitudprogramacion = archivoSolicitudprogramacion.getIdSolicitudprogramacion();
            if (idSolicitudprogramacion != null) {
                idSolicitudprogramacion.getArchivoSolicitudprogramacionList().remove(archivoSolicitudprogramacion);
                idSolicitudprogramacion = em.merge(idSolicitudprogramacion);
            }
            em.remove(archivoSolicitudprogramacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ArchivoSolicitudprogramacion> findArchivoSolicitudprogramacionEntities() {
        return findArchivoSolicitudprogramacionEntities(true, -1, -1);
    }

    public List<ArchivoSolicitudprogramacion> findArchivoSolicitudprogramacionEntities(int maxResults, int firstResult) {
        return findArchivoSolicitudprogramacionEntities(false, maxResults, firstResult);
    }

    private List<ArchivoSolicitudprogramacion> findArchivoSolicitudprogramacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ArchivoSolicitudprogramacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ArchivoSolicitudprogramacion findArchivoSolicitudprogramacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ArchivoSolicitudprogramacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getArchivoSolicitudprogramacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ArchivoSolicitudprogramacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
