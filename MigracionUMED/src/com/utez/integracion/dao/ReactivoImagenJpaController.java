/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Reactivo;
import com.utez.integracion.entity.ReactivoImagen;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ReactivoImagenJpaController implements Serializable {

    public ReactivoImagenJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ReactivoImagen reactivoImagen) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reactivo idReactivo = reactivoImagen.getIdReactivo();
            if (idReactivo != null) {
                idReactivo = em.getReference(idReactivo.getClass(), idReactivo.getIdReactivo());
                reactivoImagen.setIdReactivo(idReactivo);
            }
            em.persist(reactivoImagen);
            if (idReactivo != null) {
                idReactivo.getReactivoImagenList().add(reactivoImagen);
                idReactivo = em.merge(idReactivo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ReactivoImagen reactivoImagen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReactivoImagen persistentReactivoImagen = em.find(ReactivoImagen.class, reactivoImagen.getIdReactivoimagen());
            Reactivo idReactivoOld = persistentReactivoImagen.getIdReactivo();
            Reactivo idReactivoNew = reactivoImagen.getIdReactivo();
            if (idReactivoNew != null) {
                idReactivoNew = em.getReference(idReactivoNew.getClass(), idReactivoNew.getIdReactivo());
                reactivoImagen.setIdReactivo(idReactivoNew);
            }
            reactivoImagen = em.merge(reactivoImagen);
            if (idReactivoOld != null && !idReactivoOld.equals(idReactivoNew)) {
                idReactivoOld.getReactivoImagenList().remove(reactivoImagen);
                idReactivoOld = em.merge(idReactivoOld);
            }
            if (idReactivoNew != null && !idReactivoNew.equals(idReactivoOld)) {
                idReactivoNew.getReactivoImagenList().add(reactivoImagen);
                idReactivoNew = em.merge(idReactivoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = reactivoImagen.getIdReactivoimagen();
                if (findReactivoImagen(id) == null) {
                    throw new NonexistentEntityException("The reactivoImagen with id " + id + " no longer exists.");
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
            ReactivoImagen reactivoImagen;
            try {
                reactivoImagen = em.getReference(ReactivoImagen.class, id);
                reactivoImagen.getIdReactivoimagen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reactivoImagen with id " + id + " no longer exists.", enfe);
            }
            Reactivo idReactivo = reactivoImagen.getIdReactivo();
            if (idReactivo != null) {
                idReactivo.getReactivoImagenList().remove(reactivoImagen);
                idReactivo = em.merge(idReactivo);
            }
            em.remove(reactivoImagen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ReactivoImagen> findReactivoImagenEntities() {
        return findReactivoImagenEntities(true, -1, -1);
    }

    public List<ReactivoImagen> findReactivoImagenEntities(int maxResults, int firstResult) {
        return findReactivoImagenEntities(false, maxResults, firstResult);
    }

    private List<ReactivoImagen> findReactivoImagenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ReactivoImagen as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ReactivoImagen findReactivoImagen(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReactivoImagen.class, id);
        } finally {
            em.close();
        }
    }

    public int getReactivoImagenCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ReactivoImagen as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
