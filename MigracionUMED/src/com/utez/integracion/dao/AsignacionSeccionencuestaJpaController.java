/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.AsignacionSeccionencuesta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Seccion;
import com.utez.integracion.entity.Encuesta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionSeccionencuestaJpaController implements Serializable {

    public AsignacionSeccionencuestaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionSeccionencuesta asignacionSeccionencuesta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Seccion idSeccion = asignacionSeccionencuesta.getIdSeccion();
            if (idSeccion != null) {
                idSeccion = em.getReference(idSeccion.getClass(), idSeccion.getIdSeccion());
                asignacionSeccionencuesta.setIdSeccion(idSeccion);
            }
            Encuesta idEncuesta = asignacionSeccionencuesta.getIdEncuesta();
            if (idEncuesta != null) {
                idEncuesta = em.getReference(idEncuesta.getClass(), idEncuesta.getIdEncuesta());
                asignacionSeccionencuesta.setIdEncuesta(idEncuesta);
            }
            em.persist(asignacionSeccionencuesta);
            if (idSeccion != null) {
                idSeccion.getAsignacionSeccionencuestaList().add(asignacionSeccionencuesta);
                idSeccion = em.merge(idSeccion);
            }
            if (idEncuesta != null) {
                idEncuesta.getAsignacionSeccionencuestaList().add(asignacionSeccionencuesta);
                idEncuesta = em.merge(idEncuesta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionSeccionencuesta asignacionSeccionencuesta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionSeccionencuesta persistentAsignacionSeccionencuesta = em.find(AsignacionSeccionencuesta.class, asignacionSeccionencuesta.getIdAsignacionseccionencuesta());
            Seccion idSeccionOld = persistentAsignacionSeccionencuesta.getIdSeccion();
            Seccion idSeccionNew = asignacionSeccionencuesta.getIdSeccion();
            Encuesta idEncuestaOld = persistentAsignacionSeccionencuesta.getIdEncuesta();
            Encuesta idEncuestaNew = asignacionSeccionencuesta.getIdEncuesta();
            if (idSeccionNew != null) {
                idSeccionNew = em.getReference(idSeccionNew.getClass(), idSeccionNew.getIdSeccion());
                asignacionSeccionencuesta.setIdSeccion(idSeccionNew);
            }
            if (idEncuestaNew != null) {
                idEncuestaNew = em.getReference(idEncuestaNew.getClass(), idEncuestaNew.getIdEncuesta());
                asignacionSeccionencuesta.setIdEncuesta(idEncuestaNew);
            }
            asignacionSeccionencuesta = em.merge(asignacionSeccionencuesta);
            if (idSeccionOld != null && !idSeccionOld.equals(idSeccionNew)) {
                idSeccionOld.getAsignacionSeccionencuestaList().remove(asignacionSeccionencuesta);
                idSeccionOld = em.merge(idSeccionOld);
            }
            if (idSeccionNew != null && !idSeccionNew.equals(idSeccionOld)) {
                idSeccionNew.getAsignacionSeccionencuestaList().add(asignacionSeccionencuesta);
                idSeccionNew = em.merge(idSeccionNew);
            }
            if (idEncuestaOld != null && !idEncuestaOld.equals(idEncuestaNew)) {
                idEncuestaOld.getAsignacionSeccionencuestaList().remove(asignacionSeccionencuesta);
                idEncuestaOld = em.merge(idEncuestaOld);
            }
            if (idEncuestaNew != null && !idEncuestaNew.equals(idEncuestaOld)) {
                idEncuestaNew.getAsignacionSeccionencuestaList().add(asignacionSeccionencuesta);
                idEncuestaNew = em.merge(idEncuestaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionSeccionencuesta.getIdAsignacionseccionencuesta();
                if (findAsignacionSeccionencuesta(id) == null) {
                    throw new NonexistentEntityException("The asignacionSeccionencuesta with id " + id + " no longer exists.");
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
            AsignacionSeccionencuesta asignacionSeccionencuesta;
            try {
                asignacionSeccionencuesta = em.getReference(AsignacionSeccionencuesta.class, id);
                asignacionSeccionencuesta.getIdAsignacionseccionencuesta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionSeccionencuesta with id " + id + " no longer exists.", enfe);
            }
            Seccion idSeccion = asignacionSeccionencuesta.getIdSeccion();
            if (idSeccion != null) {
                idSeccion.getAsignacionSeccionencuestaList().remove(asignacionSeccionencuesta);
                idSeccion = em.merge(idSeccion);
            }
            Encuesta idEncuesta = asignacionSeccionencuesta.getIdEncuesta();
            if (idEncuesta != null) {
                idEncuesta.getAsignacionSeccionencuestaList().remove(asignacionSeccionencuesta);
                idEncuesta = em.merge(idEncuesta);
            }
            em.remove(asignacionSeccionencuesta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionSeccionencuesta> findAsignacionSeccionencuestaEntities() {
        return findAsignacionSeccionencuestaEntities(true, -1, -1);
    }

    public List<AsignacionSeccionencuesta> findAsignacionSeccionencuestaEntities(int maxResults, int firstResult) {
        return findAsignacionSeccionencuestaEntities(false, maxResults, firstResult);
    }

    private List<AsignacionSeccionencuesta> findAsignacionSeccionencuestaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionSeccionencuesta as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionSeccionencuesta findAsignacionSeccionencuesta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionSeccionencuesta.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionSeccionencuestaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionSeccionencuesta as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
