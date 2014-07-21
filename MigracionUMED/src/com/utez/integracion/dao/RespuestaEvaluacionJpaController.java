/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.ResultadoEvaluacion;
import com.utez.integracion.entity.ContenidoReactivo;
import com.utez.integracion.entity.RespuestaEvaluacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class RespuestaEvaluacionJpaController implements Serializable {

    public RespuestaEvaluacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RespuestaEvaluacion respuestaEvaluacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ResultadoEvaluacion idResultadoevaluacion = respuestaEvaluacion.getIdResultadoevaluacion();
            if (idResultadoevaluacion != null) {
                idResultadoevaluacion = em.getReference(idResultadoevaluacion.getClass(), idResultadoevaluacion.getIdResultadoevaluacion());
                respuestaEvaluacion.setIdResultadoevaluacion(idResultadoevaluacion);
            }
            ContenidoReactivo idContenido = respuestaEvaluacion.getIdContenido();
            if (idContenido != null) {
                idContenido = em.getReference(idContenido.getClass(), idContenido.getIdContenidoreactivo());
                respuestaEvaluacion.setIdContenido(idContenido);
            }
            em.persist(respuestaEvaluacion);
            if (idResultadoevaluacion != null) {
                idResultadoevaluacion.getRespuestaEvaluacionList().add(respuestaEvaluacion);
                idResultadoevaluacion = em.merge(idResultadoevaluacion);
            }
            if (idContenido != null) {
                idContenido.getRespuestaEvaluacionList().add(respuestaEvaluacion);
                idContenido = em.merge(idContenido);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RespuestaEvaluacion respuestaEvaluacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RespuestaEvaluacion persistentRespuestaEvaluacion = em.find(RespuestaEvaluacion.class, respuestaEvaluacion.getIdRespuestaevaluacion());
            ResultadoEvaluacion idResultadoevaluacionOld = persistentRespuestaEvaluacion.getIdResultadoevaluacion();
            ResultadoEvaluacion idResultadoevaluacionNew = respuestaEvaluacion.getIdResultadoevaluacion();
            ContenidoReactivo idContenidoOld = persistentRespuestaEvaluacion.getIdContenido();
            ContenidoReactivo idContenidoNew = respuestaEvaluacion.getIdContenido();
            if (idResultadoevaluacionNew != null) {
                idResultadoevaluacionNew = em.getReference(idResultadoevaluacionNew.getClass(), idResultadoevaluacionNew.getIdResultadoevaluacion());
                respuestaEvaluacion.setIdResultadoevaluacion(idResultadoevaluacionNew);
            }
            if (idContenidoNew != null) {
                idContenidoNew = em.getReference(idContenidoNew.getClass(), idContenidoNew.getIdContenidoreactivo());
                respuestaEvaluacion.setIdContenido(idContenidoNew);
            }
            respuestaEvaluacion = em.merge(respuestaEvaluacion);
            if (idResultadoevaluacionOld != null && !idResultadoevaluacionOld.equals(idResultadoevaluacionNew)) {
                idResultadoevaluacionOld.getRespuestaEvaluacionList().remove(respuestaEvaluacion);
                idResultadoevaluacionOld = em.merge(idResultadoevaluacionOld);
            }
            if (idResultadoevaluacionNew != null && !idResultadoevaluacionNew.equals(idResultadoevaluacionOld)) {
                idResultadoevaluacionNew.getRespuestaEvaluacionList().add(respuestaEvaluacion);
                idResultadoevaluacionNew = em.merge(idResultadoevaluacionNew);
            }
            if (idContenidoOld != null && !idContenidoOld.equals(idContenidoNew)) {
                idContenidoOld.getRespuestaEvaluacionList().remove(respuestaEvaluacion);
                idContenidoOld = em.merge(idContenidoOld);
            }
            if (idContenidoNew != null && !idContenidoNew.equals(idContenidoOld)) {
                idContenidoNew.getRespuestaEvaluacionList().add(respuestaEvaluacion);
                idContenidoNew = em.merge(idContenidoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = respuestaEvaluacion.getIdRespuestaevaluacion();
                if (findRespuestaEvaluacion(id) == null) {
                    throw new NonexistentEntityException("The respuestaEvaluacion with id " + id + " no longer exists.");
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
            RespuestaEvaluacion respuestaEvaluacion;
            try {
                respuestaEvaluacion = em.getReference(RespuestaEvaluacion.class, id);
                respuestaEvaluacion.getIdRespuestaevaluacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The respuestaEvaluacion with id " + id + " no longer exists.", enfe);
            }
            ResultadoEvaluacion idResultadoevaluacion = respuestaEvaluacion.getIdResultadoevaluacion();
            if (idResultadoevaluacion != null) {
                idResultadoevaluacion.getRespuestaEvaluacionList().remove(respuestaEvaluacion);
                idResultadoevaluacion = em.merge(idResultadoevaluacion);
            }
            ContenidoReactivo idContenido = respuestaEvaluacion.getIdContenido();
            if (idContenido != null) {
                idContenido.getRespuestaEvaluacionList().remove(respuestaEvaluacion);
                idContenido = em.merge(idContenido);
            }
            em.remove(respuestaEvaluacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RespuestaEvaluacion> findRespuestaEvaluacionEntities() {
        return findRespuestaEvaluacionEntities(true, -1, -1);
    }

    public List<RespuestaEvaluacion> findRespuestaEvaluacionEntities(int maxResults, int firstResult) {
        return findRespuestaEvaluacionEntities(false, maxResults, firstResult);
    }

    private List<RespuestaEvaluacion> findRespuestaEvaluacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from RespuestaEvaluacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public RespuestaEvaluacion findRespuestaEvaluacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RespuestaEvaluacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getRespuestaEvaluacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from RespuestaEvaluacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
