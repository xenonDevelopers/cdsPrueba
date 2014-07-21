/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoRespuesta;
import com.utez.integracion.entity.Pregunta;
import com.utez.integracion.entity.AsignacionEncuestaalumno;
import com.utez.integracion.entity.RespuestaEncuesta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class RespuestaEncuestaJpaController implements Serializable {

    public RespuestaEncuestaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RespuestaEncuesta respuestaEncuesta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoRespuesta idTiporespuesta = respuestaEncuesta.getIdTiporespuesta();
            if (idTiporespuesta != null) {
                idTiporespuesta = em.getReference(idTiporespuesta.getClass(), idTiporespuesta.getIdTiporespuesta());
                respuestaEncuesta.setIdTiporespuesta(idTiporespuesta);
            }
            Pregunta idPregunta = respuestaEncuesta.getIdPregunta();
            if (idPregunta != null) {
                idPregunta = em.getReference(idPregunta.getClass(), idPregunta.getIdPregunta());
                respuestaEncuesta.setIdPregunta(idPregunta);
            }
            AsignacionEncuestaalumno idAsignacionencuesta = respuestaEncuesta.getIdAsignacionencuesta();
            if (idAsignacionencuesta != null) {
                idAsignacionencuesta = em.getReference(idAsignacionencuesta.getClass(), idAsignacionencuesta.getIdAsignacionencuestaalumno());
                respuestaEncuesta.setIdAsignacionencuesta(idAsignacionencuesta);
            }
            em.persist(respuestaEncuesta);
            if (idTiporespuesta != null) {
                idTiporespuesta.getRespuestaEncuestaList().add(respuestaEncuesta);
                idTiporespuesta = em.merge(idTiporespuesta);
            }
            if (idPregunta != null) {
                idPregunta.getRespuestaEncuestaList().add(respuestaEncuesta);
                idPregunta = em.merge(idPregunta);
            }
            if (idAsignacionencuesta != null) {
                idAsignacionencuesta.getRespuestaEncuestaList().add(respuestaEncuesta);
                idAsignacionencuesta = em.merge(idAsignacionencuesta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RespuestaEncuesta respuestaEncuesta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RespuestaEncuesta persistentRespuestaEncuesta = em.find(RespuestaEncuesta.class, respuestaEncuesta.getIdRespuestaencuesta());
            TipoRespuesta idTiporespuestaOld = persistentRespuestaEncuesta.getIdTiporespuesta();
            TipoRespuesta idTiporespuestaNew = respuestaEncuesta.getIdTiporespuesta();
            Pregunta idPreguntaOld = persistentRespuestaEncuesta.getIdPregunta();
            Pregunta idPreguntaNew = respuestaEncuesta.getIdPregunta();
            AsignacionEncuestaalumno idAsignacionencuestaOld = persistentRespuestaEncuesta.getIdAsignacionencuesta();
            AsignacionEncuestaalumno idAsignacionencuestaNew = respuestaEncuesta.getIdAsignacionencuesta();
            if (idTiporespuestaNew != null) {
                idTiporespuestaNew = em.getReference(idTiporespuestaNew.getClass(), idTiporespuestaNew.getIdTiporespuesta());
                respuestaEncuesta.setIdTiporespuesta(idTiporespuestaNew);
            }
            if (idPreguntaNew != null) {
                idPreguntaNew = em.getReference(idPreguntaNew.getClass(), idPreguntaNew.getIdPregunta());
                respuestaEncuesta.setIdPregunta(idPreguntaNew);
            }
            if (idAsignacionencuestaNew != null) {
                idAsignacionencuestaNew = em.getReference(idAsignacionencuestaNew.getClass(), idAsignacionencuestaNew.getIdAsignacionencuestaalumno());
                respuestaEncuesta.setIdAsignacionencuesta(idAsignacionencuestaNew);
            }
            respuestaEncuesta = em.merge(respuestaEncuesta);
            if (idTiporespuestaOld != null && !idTiporespuestaOld.equals(idTiporespuestaNew)) {
                idTiporespuestaOld.getRespuestaEncuestaList().remove(respuestaEncuesta);
                idTiporespuestaOld = em.merge(idTiporespuestaOld);
            }
            if (idTiporespuestaNew != null && !idTiporespuestaNew.equals(idTiporespuestaOld)) {
                idTiporespuestaNew.getRespuestaEncuestaList().add(respuestaEncuesta);
                idTiporespuestaNew = em.merge(idTiporespuestaNew);
            }
            if (idPreguntaOld != null && !idPreguntaOld.equals(idPreguntaNew)) {
                idPreguntaOld.getRespuestaEncuestaList().remove(respuestaEncuesta);
                idPreguntaOld = em.merge(idPreguntaOld);
            }
            if (idPreguntaNew != null && !idPreguntaNew.equals(idPreguntaOld)) {
                idPreguntaNew.getRespuestaEncuestaList().add(respuestaEncuesta);
                idPreguntaNew = em.merge(idPreguntaNew);
            }
            if (idAsignacionencuestaOld != null && !idAsignacionencuestaOld.equals(idAsignacionencuestaNew)) {
                idAsignacionencuestaOld.getRespuestaEncuestaList().remove(respuestaEncuesta);
                idAsignacionencuestaOld = em.merge(idAsignacionencuestaOld);
            }
            if (idAsignacionencuestaNew != null && !idAsignacionencuestaNew.equals(idAsignacionencuestaOld)) {
                idAsignacionencuestaNew.getRespuestaEncuestaList().add(respuestaEncuesta);
                idAsignacionencuestaNew = em.merge(idAsignacionencuestaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = respuestaEncuesta.getIdRespuestaencuesta();
                if (findRespuestaEncuesta(id) == null) {
                    throw new NonexistentEntityException("The respuestaEncuesta with id " + id + " no longer exists.");
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
            RespuestaEncuesta respuestaEncuesta;
            try {
                respuestaEncuesta = em.getReference(RespuestaEncuesta.class, id);
                respuestaEncuesta.getIdRespuestaencuesta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The respuestaEncuesta with id " + id + " no longer exists.", enfe);
            }
            TipoRespuesta idTiporespuesta = respuestaEncuesta.getIdTiporespuesta();
            if (idTiporespuesta != null) {
                idTiporespuesta.getRespuestaEncuestaList().remove(respuestaEncuesta);
                idTiporespuesta = em.merge(idTiporespuesta);
            }
            Pregunta idPregunta = respuestaEncuesta.getIdPregunta();
            if (idPregunta != null) {
                idPregunta.getRespuestaEncuestaList().remove(respuestaEncuesta);
                idPregunta = em.merge(idPregunta);
            }
            AsignacionEncuestaalumno idAsignacionencuesta = respuestaEncuesta.getIdAsignacionencuesta();
            if (idAsignacionencuesta != null) {
                idAsignacionencuesta.getRespuestaEncuestaList().remove(respuestaEncuesta);
                idAsignacionencuesta = em.merge(idAsignacionencuesta);
            }
            em.remove(respuestaEncuesta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RespuestaEncuesta> findRespuestaEncuestaEntities() {
        return findRespuestaEncuestaEntities(true, -1, -1);
    }

    public List<RespuestaEncuesta> findRespuestaEncuestaEntities(int maxResults, int firstResult) {
        return findRespuestaEncuestaEntities(false, maxResults, firstResult);
    }

    private List<RespuestaEncuesta> findRespuestaEncuestaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from RespuestaEncuesta as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public RespuestaEncuesta findRespuestaEncuesta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RespuestaEncuesta.class, id);
        } finally {
            em.close();
        }
    }

    public int getRespuestaEncuestaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from RespuestaEncuesta as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
