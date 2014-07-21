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
import com.utez.integracion.entity.AsignacionEncuestadocente;
import com.utez.integracion.entity.RespuestaEncuestadocente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class RespuestaEncuestadocenteJpaController implements Serializable {

    public RespuestaEncuestadocenteJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RespuestaEncuestadocente respuestaEncuestadocente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoRespuesta idTiporespuesta = respuestaEncuestadocente.getIdTiporespuesta();
            if (idTiporespuesta != null) {
                idTiporespuesta = em.getReference(idTiporespuesta.getClass(), idTiporespuesta.getIdTiporespuesta());
                respuestaEncuestadocente.setIdTiporespuesta(idTiporespuesta);
            }
            Pregunta idPregunta = respuestaEncuestadocente.getIdPregunta();
            if (idPregunta != null) {
                idPregunta = em.getReference(idPregunta.getClass(), idPregunta.getIdPregunta());
                respuestaEncuestadocente.setIdPregunta(idPregunta);
            }
            AsignacionEncuestadocente idAsignacionencuestadocente = respuestaEncuestadocente.getIdAsignacionencuestadocente();
            if (idAsignacionencuestadocente != null) {
                idAsignacionencuestadocente = em.getReference(idAsignacionencuestadocente.getClass(), idAsignacionencuestadocente.getIdAsignacionencuestadocente());
                respuestaEncuestadocente.setIdAsignacionencuestadocente(idAsignacionencuestadocente);
            }
            em.persist(respuestaEncuestadocente);
            if (idTiporespuesta != null) {
                idTiporespuesta.getRespuestaEncuestadocenteList().add(respuestaEncuestadocente);
                idTiporespuesta = em.merge(idTiporespuesta);
            }
            if (idPregunta != null) {
                idPregunta.getRespuestaEncuestadocenteList().add(respuestaEncuestadocente);
                idPregunta = em.merge(idPregunta);
            }
            if (idAsignacionencuestadocente != null) {
                idAsignacionencuestadocente.getRespuestaEncuestadocenteList().add(respuestaEncuestadocente);
                idAsignacionencuestadocente = em.merge(idAsignacionencuestadocente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RespuestaEncuestadocente respuestaEncuestadocente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RespuestaEncuestadocente persistentRespuestaEncuestadocente = em.find(RespuestaEncuestadocente.class, respuestaEncuestadocente.getIdRespuestaencuestadocente());
            TipoRespuesta idTiporespuestaOld = persistentRespuestaEncuestadocente.getIdTiporespuesta();
            TipoRespuesta idTiporespuestaNew = respuestaEncuestadocente.getIdTiporespuesta();
            Pregunta idPreguntaOld = persistentRespuestaEncuestadocente.getIdPregunta();
            Pregunta idPreguntaNew = respuestaEncuestadocente.getIdPregunta();
            AsignacionEncuestadocente idAsignacionencuestadocenteOld = persistentRespuestaEncuestadocente.getIdAsignacionencuestadocente();
            AsignacionEncuestadocente idAsignacionencuestadocenteNew = respuestaEncuestadocente.getIdAsignacionencuestadocente();
            if (idTiporespuestaNew != null) {
                idTiporespuestaNew = em.getReference(idTiporespuestaNew.getClass(), idTiporespuestaNew.getIdTiporespuesta());
                respuestaEncuestadocente.setIdTiporespuesta(idTiporespuestaNew);
            }
            if (idPreguntaNew != null) {
                idPreguntaNew = em.getReference(idPreguntaNew.getClass(), idPreguntaNew.getIdPregunta());
                respuestaEncuestadocente.setIdPregunta(idPreguntaNew);
            }
            if (idAsignacionencuestadocenteNew != null) {
                idAsignacionencuestadocenteNew = em.getReference(idAsignacionencuestadocenteNew.getClass(), idAsignacionencuestadocenteNew.getIdAsignacionencuestadocente());
                respuestaEncuestadocente.setIdAsignacionencuestadocente(idAsignacionencuestadocenteNew);
            }
            respuestaEncuestadocente = em.merge(respuestaEncuestadocente);
            if (idTiporespuestaOld != null && !idTiporespuestaOld.equals(idTiporespuestaNew)) {
                idTiporespuestaOld.getRespuestaEncuestadocenteList().remove(respuestaEncuestadocente);
                idTiporespuestaOld = em.merge(idTiporespuestaOld);
            }
            if (idTiporespuestaNew != null && !idTiporespuestaNew.equals(idTiporespuestaOld)) {
                idTiporespuestaNew.getRespuestaEncuestadocenteList().add(respuestaEncuestadocente);
                idTiporespuestaNew = em.merge(idTiporespuestaNew);
            }
            if (idPreguntaOld != null && !idPreguntaOld.equals(idPreguntaNew)) {
                idPreguntaOld.getRespuestaEncuestadocenteList().remove(respuestaEncuestadocente);
                idPreguntaOld = em.merge(idPreguntaOld);
            }
            if (idPreguntaNew != null && !idPreguntaNew.equals(idPreguntaOld)) {
                idPreguntaNew.getRespuestaEncuestadocenteList().add(respuestaEncuestadocente);
                idPreguntaNew = em.merge(idPreguntaNew);
            }
            if (idAsignacionencuestadocenteOld != null && !idAsignacionencuestadocenteOld.equals(idAsignacionencuestadocenteNew)) {
                idAsignacionencuestadocenteOld.getRespuestaEncuestadocenteList().remove(respuestaEncuestadocente);
                idAsignacionencuestadocenteOld = em.merge(idAsignacionencuestadocenteOld);
            }
            if (idAsignacionencuestadocenteNew != null && !idAsignacionencuestadocenteNew.equals(idAsignacionencuestadocenteOld)) {
                idAsignacionencuestadocenteNew.getRespuestaEncuestadocenteList().add(respuestaEncuestadocente);
                idAsignacionencuestadocenteNew = em.merge(idAsignacionencuestadocenteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = respuestaEncuestadocente.getIdRespuestaencuestadocente();
                if (findRespuestaEncuestadocente(id) == null) {
                    throw new NonexistentEntityException("The respuestaEncuestadocente with id " + id + " no longer exists.");
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
            RespuestaEncuestadocente respuestaEncuestadocente;
            try {
                respuestaEncuestadocente = em.getReference(RespuestaEncuestadocente.class, id);
                respuestaEncuestadocente.getIdRespuestaencuestadocente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The respuestaEncuestadocente with id " + id + " no longer exists.", enfe);
            }
            TipoRespuesta idTiporespuesta = respuestaEncuestadocente.getIdTiporespuesta();
            if (idTiporespuesta != null) {
                idTiporespuesta.getRespuestaEncuestadocenteList().remove(respuestaEncuestadocente);
                idTiporespuesta = em.merge(idTiporespuesta);
            }
            Pregunta idPregunta = respuestaEncuestadocente.getIdPregunta();
            if (idPregunta != null) {
                idPregunta.getRespuestaEncuestadocenteList().remove(respuestaEncuestadocente);
                idPregunta = em.merge(idPregunta);
            }
            AsignacionEncuestadocente idAsignacionencuestadocente = respuestaEncuestadocente.getIdAsignacionencuestadocente();
            if (idAsignacionencuestadocente != null) {
                idAsignacionencuestadocente.getRespuestaEncuestadocenteList().remove(respuestaEncuestadocente);
                idAsignacionencuestadocente = em.merge(idAsignacionencuestadocente);
            }
            em.remove(respuestaEncuestadocente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RespuestaEncuestadocente> findRespuestaEncuestadocenteEntities() {
        return findRespuestaEncuestadocenteEntities(true, -1, -1);
    }

    public List<RespuestaEncuestadocente> findRespuestaEncuestadocenteEntities(int maxResults, int firstResult) {
        return findRespuestaEncuestadocenteEntities(false, maxResults, firstResult);
    }

    private List<RespuestaEncuestadocente> findRespuestaEncuestadocenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from RespuestaEncuestadocente as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public RespuestaEncuestadocente findRespuestaEncuestadocente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RespuestaEncuestadocente.class, id);
        } finally {
            em.close();
        }
    }

    public int getRespuestaEncuestadocenteCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from RespuestaEncuestadocente as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
