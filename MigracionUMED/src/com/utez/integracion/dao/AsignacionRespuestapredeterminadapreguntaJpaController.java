/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.AsignacionRespuestapredeterminadapregunta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.RespuestaPredeterminada;
import com.utez.integracion.entity.Pregunta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionRespuestapredeterminadapreguntaJpaController implements Serializable {

    public AsignacionRespuestapredeterminadapreguntaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionRespuestapredeterminadapregunta asignacionRespuestapredeterminadapregunta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RespuestaPredeterminada idRespuestapredeterminada = asignacionRespuestapredeterminadapregunta.getIdRespuestapredeterminada();
            if (idRespuestapredeterminada != null) {
                idRespuestapredeterminada = em.getReference(idRespuestapredeterminada.getClass(), idRespuestapredeterminada.getIdRespuestapredeterminada());
                asignacionRespuestapredeterminadapregunta.setIdRespuestapredeterminada(idRespuestapredeterminada);
            }
            Pregunta idPregunta = asignacionRespuestapredeterminadapregunta.getIdPregunta();
            if (idPregunta != null) {
                idPregunta = em.getReference(idPregunta.getClass(), idPregunta.getIdPregunta());
                asignacionRespuestapredeterminadapregunta.setIdPregunta(idPregunta);
            }
            em.persist(asignacionRespuestapredeterminadapregunta);
            if (idRespuestapredeterminada != null) {
                idRespuestapredeterminada.getAsignacionRespuestapredeterminadapreguntaList().add(asignacionRespuestapredeterminadapregunta);
                idRespuestapredeterminada = em.merge(idRespuestapredeterminada);
            }
            if (idPregunta != null) {
                idPregunta.getAsignacionRespuestapredeterminadapreguntaList().add(asignacionRespuestapredeterminadapregunta);
                idPregunta = em.merge(idPregunta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionRespuestapredeterminadapregunta asignacionRespuestapredeterminadapregunta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionRespuestapredeterminadapregunta persistentAsignacionRespuestapredeterminadapregunta = em.find(AsignacionRespuestapredeterminadapregunta.class, asignacionRespuestapredeterminadapregunta.getIdAsignacionrespuestapredeterminadapregunta());
            RespuestaPredeterminada idRespuestapredeterminadaOld = persistentAsignacionRespuestapredeterminadapregunta.getIdRespuestapredeterminada();
            RespuestaPredeterminada idRespuestapredeterminadaNew = asignacionRespuestapredeterminadapregunta.getIdRespuestapredeterminada();
            Pregunta idPreguntaOld = persistentAsignacionRespuestapredeterminadapregunta.getIdPregunta();
            Pregunta idPreguntaNew = asignacionRespuestapredeterminadapregunta.getIdPregunta();
            if (idRespuestapredeterminadaNew != null) {
                idRespuestapredeterminadaNew = em.getReference(idRespuestapredeterminadaNew.getClass(), idRespuestapredeterminadaNew.getIdRespuestapredeterminada());
                asignacionRespuestapredeterminadapregunta.setIdRespuestapredeterminada(idRespuestapredeterminadaNew);
            }
            if (idPreguntaNew != null) {
                idPreguntaNew = em.getReference(idPreguntaNew.getClass(), idPreguntaNew.getIdPregunta());
                asignacionRespuestapredeterminadapregunta.setIdPregunta(idPreguntaNew);
            }
            asignacionRespuestapredeterminadapregunta = em.merge(asignacionRespuestapredeterminadapregunta);
            if (idRespuestapredeterminadaOld != null && !idRespuestapredeterminadaOld.equals(idRespuestapredeterminadaNew)) {
                idRespuestapredeterminadaOld.getAsignacionRespuestapredeterminadapreguntaList().remove(asignacionRespuestapredeterminadapregunta);
                idRespuestapredeterminadaOld = em.merge(idRespuestapredeterminadaOld);
            }
            if (idRespuestapredeterminadaNew != null && !idRespuestapredeterminadaNew.equals(idRespuestapredeterminadaOld)) {
                idRespuestapredeterminadaNew.getAsignacionRespuestapredeterminadapreguntaList().add(asignacionRespuestapredeterminadapregunta);
                idRespuestapredeterminadaNew = em.merge(idRespuestapredeterminadaNew);
            }
            if (idPreguntaOld != null && !idPreguntaOld.equals(idPreguntaNew)) {
                idPreguntaOld.getAsignacionRespuestapredeterminadapreguntaList().remove(asignacionRespuestapredeterminadapregunta);
                idPreguntaOld = em.merge(idPreguntaOld);
            }
            if (idPreguntaNew != null && !idPreguntaNew.equals(idPreguntaOld)) {
                idPreguntaNew.getAsignacionRespuestapredeterminadapreguntaList().add(asignacionRespuestapredeterminadapregunta);
                idPreguntaNew = em.merge(idPreguntaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionRespuestapredeterminadapregunta.getIdAsignacionrespuestapredeterminadapregunta();
                if (findAsignacionRespuestapredeterminadapregunta(id) == null) {
                    throw new NonexistentEntityException("The asignacionRespuestapredeterminadapregunta with id " + id + " no longer exists.");
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
            AsignacionRespuestapredeterminadapregunta asignacionRespuestapredeterminadapregunta;
            try {
                asignacionRespuestapredeterminadapregunta = em.getReference(AsignacionRespuestapredeterminadapregunta.class, id);
                asignacionRespuestapredeterminadapregunta.getIdAsignacionrespuestapredeterminadapregunta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionRespuestapredeterminadapregunta with id " + id + " no longer exists.", enfe);
            }
            RespuestaPredeterminada idRespuestapredeterminada = asignacionRespuestapredeterminadapregunta.getIdRespuestapredeterminada();
            if (idRespuestapredeterminada != null) {
                idRespuestapredeterminada.getAsignacionRespuestapredeterminadapreguntaList().remove(asignacionRespuestapredeterminadapregunta);
                idRespuestapredeterminada = em.merge(idRespuestapredeterminada);
            }
            Pregunta idPregunta = asignacionRespuestapredeterminadapregunta.getIdPregunta();
            if (idPregunta != null) {
                idPregunta.getAsignacionRespuestapredeterminadapreguntaList().remove(asignacionRespuestapredeterminadapregunta);
                idPregunta = em.merge(idPregunta);
            }
            em.remove(asignacionRespuestapredeterminadapregunta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionRespuestapredeterminadapregunta> findAsignacionRespuestapredeterminadapreguntaEntities() {
        return findAsignacionRespuestapredeterminadapreguntaEntities(true, -1, -1);
    }

    public List<AsignacionRespuestapredeterminadapregunta> findAsignacionRespuestapredeterminadapreguntaEntities(int maxResults, int firstResult) {
        return findAsignacionRespuestapredeterminadapreguntaEntities(false, maxResults, firstResult);
    }

    private List<AsignacionRespuestapredeterminadapregunta> findAsignacionRespuestapredeterminadapreguntaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionRespuestapredeterminadapregunta as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionRespuestapredeterminadapregunta findAsignacionRespuestapredeterminadapregunta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionRespuestapredeterminadapregunta.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionRespuestapredeterminadapreguntaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionRespuestapredeterminadapregunta as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
