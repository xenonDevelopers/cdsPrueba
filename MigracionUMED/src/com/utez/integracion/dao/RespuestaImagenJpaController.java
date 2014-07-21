/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.RespuestaImagen;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.RespuestaReactivo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class RespuestaImagenJpaController implements Serializable {

    public RespuestaImagenJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RespuestaImagen respuestaImagen) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RespuestaReactivo idRespuestareactivo = respuestaImagen.getIdRespuestareactivo();
            if (idRespuestareactivo != null) {
                idRespuestareactivo = em.getReference(idRespuestareactivo.getClass(), idRespuestareactivo.getIdRespuestareactivo());
                respuestaImagen.setIdRespuestareactivo(idRespuestareactivo);
            }
            em.persist(respuestaImagen);
            if (idRespuestareactivo != null) {
                idRespuestareactivo.getRespuestaImagenList().add(respuestaImagen);
                idRespuestareactivo = em.merge(idRespuestareactivo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RespuestaImagen respuestaImagen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RespuestaImagen persistentRespuestaImagen = em.find(RespuestaImagen.class, respuestaImagen.getIdRespuestaimagen());
            RespuestaReactivo idRespuestareactivoOld = persistentRespuestaImagen.getIdRespuestareactivo();
            RespuestaReactivo idRespuestareactivoNew = respuestaImagen.getIdRespuestareactivo();
            if (idRespuestareactivoNew != null) {
                idRespuestareactivoNew = em.getReference(idRespuestareactivoNew.getClass(), idRespuestareactivoNew.getIdRespuestareactivo());
                respuestaImagen.setIdRespuestareactivo(idRespuestareactivoNew);
            }
            respuestaImagen = em.merge(respuestaImagen);
            if (idRespuestareactivoOld != null && !idRespuestareactivoOld.equals(idRespuestareactivoNew)) {
                idRespuestareactivoOld.getRespuestaImagenList().remove(respuestaImagen);
                idRespuestareactivoOld = em.merge(idRespuestareactivoOld);
            }
            if (idRespuestareactivoNew != null && !idRespuestareactivoNew.equals(idRespuestareactivoOld)) {
                idRespuestareactivoNew.getRespuestaImagenList().add(respuestaImagen);
                idRespuestareactivoNew = em.merge(idRespuestareactivoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = respuestaImagen.getIdRespuestaimagen();
                if (findRespuestaImagen(id) == null) {
                    throw new NonexistentEntityException("The respuestaImagen with id " + id + " no longer exists.");
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
            RespuestaImagen respuestaImagen;
            try {
                respuestaImagen = em.getReference(RespuestaImagen.class, id);
                respuestaImagen.getIdRespuestaimagen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The respuestaImagen with id " + id + " no longer exists.", enfe);
            }
            RespuestaReactivo idRespuestareactivo = respuestaImagen.getIdRespuestareactivo();
            if (idRespuestareactivo != null) {
                idRespuestareactivo.getRespuestaImagenList().remove(respuestaImagen);
                idRespuestareactivo = em.merge(idRespuestareactivo);
            }
            em.remove(respuestaImagen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RespuestaImagen> findRespuestaImagenEntities() {
        return findRespuestaImagenEntities(true, -1, -1);
    }

    public List<RespuestaImagen> findRespuestaImagenEntities(int maxResults, int firstResult) {
        return findRespuestaImagenEntities(false, maxResults, firstResult);
    }

    private List<RespuestaImagen> findRespuestaImagenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from RespuestaImagen as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public RespuestaImagen findRespuestaImagen(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RespuestaImagen.class, id);
        } finally {
            em.close();
        }
    }

    public int getRespuestaImagenCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from RespuestaImagen as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
