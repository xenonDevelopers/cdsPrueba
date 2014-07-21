/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Reactivo;
import com.utez.integracion.entity.ExamenImpreso;
import com.utez.integracion.entity.ReactivoExamenimpreso;
import com.utez.integracion.entity.ReactivoExamenimpresoPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ReactivoExamenimpresoJpaController implements Serializable {

    public ReactivoExamenimpresoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ReactivoExamenimpreso reactivoExamenimpreso) throws PreexistingEntityException, Exception {
        if (reactivoExamenimpreso.getReactivoExamenimpresoPK() == null) {
            reactivoExamenimpreso.setReactivoExamenimpresoPK(new ReactivoExamenimpresoPK());
        }
        reactivoExamenimpreso.getReactivoExamenimpresoPK().setIdExamenimpreso(reactivoExamenimpreso.getExamenImpreso().getIdExamenimpreso());
        reactivoExamenimpreso.getReactivoExamenimpresoPK().setIdReactivo(reactivoExamenimpreso.getReactivo().getIdReactivo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reactivo reactivo = reactivoExamenimpreso.getReactivo();
            if (reactivo != null) {
                reactivo = em.getReference(reactivo.getClass(), reactivo.getIdReactivo());
                reactivoExamenimpreso.setReactivo(reactivo);
            }
            ExamenImpreso examenImpreso = reactivoExamenimpreso.getExamenImpreso();
            if (examenImpreso != null) {
                examenImpreso = em.getReference(examenImpreso.getClass(), examenImpreso.getIdExamenimpreso());
                reactivoExamenimpreso.setExamenImpreso(examenImpreso);
            }
            em.persist(reactivoExamenimpreso);
            if (reactivo != null) {
                reactivo.getReactivoExamenimpresoList().add(reactivoExamenimpreso);
                reactivo = em.merge(reactivo);
            }
            if (examenImpreso != null) {
                examenImpreso.getReactivoExamenimpresoList().add(reactivoExamenimpreso);
                examenImpreso = em.merge(examenImpreso);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReactivoExamenimpreso(reactivoExamenimpreso.getReactivoExamenimpresoPK()) != null) {
                throw new PreexistingEntityException("ReactivoExamenimpreso " + reactivoExamenimpreso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ReactivoExamenimpreso reactivoExamenimpreso) throws NonexistentEntityException, Exception {
        reactivoExamenimpreso.getReactivoExamenimpresoPK().setIdExamenimpreso(reactivoExamenimpreso.getExamenImpreso().getIdExamenimpreso());
        reactivoExamenimpreso.getReactivoExamenimpresoPK().setIdReactivo(reactivoExamenimpreso.getReactivo().getIdReactivo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReactivoExamenimpreso persistentReactivoExamenimpreso = em.find(ReactivoExamenimpreso.class, reactivoExamenimpreso.getReactivoExamenimpresoPK());
            Reactivo reactivoOld = persistentReactivoExamenimpreso.getReactivo();
            Reactivo reactivoNew = reactivoExamenimpreso.getReactivo();
            ExamenImpreso examenImpresoOld = persistentReactivoExamenimpreso.getExamenImpreso();
            ExamenImpreso examenImpresoNew = reactivoExamenimpreso.getExamenImpreso();
            if (reactivoNew != null) {
                reactivoNew = em.getReference(reactivoNew.getClass(), reactivoNew.getIdReactivo());
                reactivoExamenimpreso.setReactivo(reactivoNew);
            }
            if (examenImpresoNew != null) {
                examenImpresoNew = em.getReference(examenImpresoNew.getClass(), examenImpresoNew.getIdExamenimpreso());
                reactivoExamenimpreso.setExamenImpreso(examenImpresoNew);
            }
            reactivoExamenimpreso = em.merge(reactivoExamenimpreso);
            if (reactivoOld != null && !reactivoOld.equals(reactivoNew)) {
                reactivoOld.getReactivoExamenimpresoList().remove(reactivoExamenimpreso);
                reactivoOld = em.merge(reactivoOld);
            }
            if (reactivoNew != null && !reactivoNew.equals(reactivoOld)) {
                reactivoNew.getReactivoExamenimpresoList().add(reactivoExamenimpreso);
                reactivoNew = em.merge(reactivoNew);
            }
            if (examenImpresoOld != null && !examenImpresoOld.equals(examenImpresoNew)) {
                examenImpresoOld.getReactivoExamenimpresoList().remove(reactivoExamenimpreso);
                examenImpresoOld = em.merge(examenImpresoOld);
            }
            if (examenImpresoNew != null && !examenImpresoNew.equals(examenImpresoOld)) {
                examenImpresoNew.getReactivoExamenimpresoList().add(reactivoExamenimpreso);
                examenImpresoNew = em.merge(examenImpresoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ReactivoExamenimpresoPK id = reactivoExamenimpreso.getReactivoExamenimpresoPK();
                if (findReactivoExamenimpreso(id) == null) {
                    throw new NonexistentEntityException("The reactivoExamenimpreso with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ReactivoExamenimpresoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReactivoExamenimpreso reactivoExamenimpreso;
            try {
                reactivoExamenimpreso = em.getReference(ReactivoExamenimpreso.class, id);
                reactivoExamenimpreso.getReactivoExamenimpresoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reactivoExamenimpreso with id " + id + " no longer exists.", enfe);
            }
            Reactivo reactivo = reactivoExamenimpreso.getReactivo();
            if (reactivo != null) {
                reactivo.getReactivoExamenimpresoList().remove(reactivoExamenimpreso);
                reactivo = em.merge(reactivo);
            }
            ExamenImpreso examenImpreso = reactivoExamenimpreso.getExamenImpreso();
            if (examenImpreso != null) {
                examenImpreso.getReactivoExamenimpresoList().remove(reactivoExamenimpreso);
                examenImpreso = em.merge(examenImpreso);
            }
            em.remove(reactivoExamenimpreso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ReactivoExamenimpreso> findReactivoExamenimpresoEntities() {
        return findReactivoExamenimpresoEntities(true, -1, -1);
    }

    public List<ReactivoExamenimpreso> findReactivoExamenimpresoEntities(int maxResults, int firstResult) {
        return findReactivoExamenimpresoEntities(false, maxResults, firstResult);
    }

    private List<ReactivoExamenimpreso> findReactivoExamenimpresoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ReactivoExamenimpreso as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ReactivoExamenimpreso findReactivoExamenimpreso(ReactivoExamenimpresoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReactivoExamenimpreso.class, id);
        } finally {
            em.close();
        }
    }

    public int getReactivoExamenimpresoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ReactivoExamenimpreso as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
