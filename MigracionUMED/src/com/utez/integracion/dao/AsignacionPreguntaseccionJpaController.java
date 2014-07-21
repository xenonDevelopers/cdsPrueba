/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.AsignacionPreguntaseccion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Seccion;
import com.utez.integracion.entity.Pregunta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionPreguntaseccionJpaController implements Serializable {

    public AsignacionPreguntaseccionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionPreguntaseccion asignacionPreguntaseccion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Seccion idSeccion = asignacionPreguntaseccion.getIdSeccion();
            if (idSeccion != null) {
                idSeccion = em.getReference(idSeccion.getClass(), idSeccion.getIdSeccion());
                asignacionPreguntaseccion.setIdSeccion(idSeccion);
            }
            Pregunta idPregunta = asignacionPreguntaseccion.getIdPregunta();
            if (idPregunta != null) {
                idPregunta = em.getReference(idPregunta.getClass(), idPregunta.getIdPregunta());
                asignacionPreguntaseccion.setIdPregunta(idPregunta);
            }
            em.persist(asignacionPreguntaseccion);
            if (idSeccion != null) {
                idSeccion.getAsignacionPreguntaseccionList().add(asignacionPreguntaseccion);
                idSeccion = em.merge(idSeccion);
            }
            if (idPregunta != null) {
                idPregunta.getAsignacionPreguntaseccionList().add(asignacionPreguntaseccion);
                idPregunta = em.merge(idPregunta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionPreguntaseccion asignacionPreguntaseccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionPreguntaseccion persistentAsignacionPreguntaseccion = em.find(AsignacionPreguntaseccion.class, asignacionPreguntaseccion.getIdAsignacionpreguntaseccion());
            Seccion idSeccionOld = persistentAsignacionPreguntaseccion.getIdSeccion();
            Seccion idSeccionNew = asignacionPreguntaseccion.getIdSeccion();
            Pregunta idPreguntaOld = persistentAsignacionPreguntaseccion.getIdPregunta();
            Pregunta idPreguntaNew = asignacionPreguntaseccion.getIdPregunta();
            if (idSeccionNew != null) {
                idSeccionNew = em.getReference(idSeccionNew.getClass(), idSeccionNew.getIdSeccion());
                asignacionPreguntaseccion.setIdSeccion(idSeccionNew);
            }
            if (idPreguntaNew != null) {
                idPreguntaNew = em.getReference(idPreguntaNew.getClass(), idPreguntaNew.getIdPregunta());
                asignacionPreguntaseccion.setIdPregunta(idPreguntaNew);
            }
            asignacionPreguntaseccion = em.merge(asignacionPreguntaseccion);
            if (idSeccionOld != null && !idSeccionOld.equals(idSeccionNew)) {
                idSeccionOld.getAsignacionPreguntaseccionList().remove(asignacionPreguntaseccion);
                idSeccionOld = em.merge(idSeccionOld);
            }
            if (idSeccionNew != null && !idSeccionNew.equals(idSeccionOld)) {
                idSeccionNew.getAsignacionPreguntaseccionList().add(asignacionPreguntaseccion);
                idSeccionNew = em.merge(idSeccionNew);
            }
            if (idPreguntaOld != null && !idPreguntaOld.equals(idPreguntaNew)) {
                idPreguntaOld.getAsignacionPreguntaseccionList().remove(asignacionPreguntaseccion);
                idPreguntaOld = em.merge(idPreguntaOld);
            }
            if (idPreguntaNew != null && !idPreguntaNew.equals(idPreguntaOld)) {
                idPreguntaNew.getAsignacionPreguntaseccionList().add(asignacionPreguntaseccion);
                idPreguntaNew = em.merge(idPreguntaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionPreguntaseccion.getIdAsignacionpreguntaseccion();
                if (findAsignacionPreguntaseccion(id) == null) {
                    throw new NonexistentEntityException("The asignacionPreguntaseccion with id " + id + " no longer exists.");
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
            AsignacionPreguntaseccion asignacionPreguntaseccion;
            try {
                asignacionPreguntaseccion = em.getReference(AsignacionPreguntaseccion.class, id);
                asignacionPreguntaseccion.getIdAsignacionpreguntaseccion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionPreguntaseccion with id " + id + " no longer exists.", enfe);
            }
            Seccion idSeccion = asignacionPreguntaseccion.getIdSeccion();
            if (idSeccion != null) {
                idSeccion.getAsignacionPreguntaseccionList().remove(asignacionPreguntaseccion);
                idSeccion = em.merge(idSeccion);
            }
            Pregunta idPregunta = asignacionPreguntaseccion.getIdPregunta();
            if (idPregunta != null) {
                idPregunta.getAsignacionPreguntaseccionList().remove(asignacionPreguntaseccion);
                idPregunta = em.merge(idPregunta);
            }
            em.remove(asignacionPreguntaseccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionPreguntaseccion> findAsignacionPreguntaseccionEntities() {
        return findAsignacionPreguntaseccionEntities(true, -1, -1);
    }

    public List<AsignacionPreguntaseccion> findAsignacionPreguntaseccionEntities(int maxResults, int firstResult) {
        return findAsignacionPreguntaseccionEntities(false, maxResults, firstResult);
    }

    private List<AsignacionPreguntaseccion> findAsignacionPreguntaseccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionPreguntaseccion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionPreguntaseccion findAsignacionPreguntaseccion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionPreguntaseccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionPreguntaseccionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionPreguntaseccion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
