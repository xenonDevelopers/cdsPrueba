/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Aspirante;
import com.utez.integracion.entity.SeguimientoAspirante;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class SeguimientoAspiranteJpaController implements Serializable {

    public SeguimientoAspiranteJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeguimientoAspirante seguimientoAspirante) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aspirante idAspirante = seguimientoAspirante.getIdAspirante();
            if (idAspirante != null) {
                idAspirante = em.getReference(idAspirante.getClass(), idAspirante.getIdAspirante());
                seguimientoAspirante.setIdAspirante(idAspirante);
            }
            em.persist(seguimientoAspirante);
            if (idAspirante != null) {
                idAspirante.getSeguimientoAspiranteList().add(seguimientoAspirante);
                idAspirante = em.merge(idAspirante);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeguimientoAspirante seguimientoAspirante) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeguimientoAspirante persistentSeguimientoAspirante = em.find(SeguimientoAspirante.class, seguimientoAspirante.getIdSeguimiento());
            Aspirante idAspiranteOld = persistentSeguimientoAspirante.getIdAspirante();
            Aspirante idAspiranteNew = seguimientoAspirante.getIdAspirante();
            if (idAspiranteNew != null) {
                idAspiranteNew = em.getReference(idAspiranteNew.getClass(), idAspiranteNew.getIdAspirante());
                seguimientoAspirante.setIdAspirante(idAspiranteNew);
            }
            seguimientoAspirante = em.merge(seguimientoAspirante);
            if (idAspiranteOld != null && !idAspiranteOld.equals(idAspiranteNew)) {
                idAspiranteOld.getSeguimientoAspiranteList().remove(seguimientoAspirante);
                idAspiranteOld = em.merge(idAspiranteOld);
            }
            if (idAspiranteNew != null && !idAspiranteNew.equals(idAspiranteOld)) {
                idAspiranteNew.getSeguimientoAspiranteList().add(seguimientoAspirante);
                idAspiranteNew = em.merge(idAspiranteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = seguimientoAspirante.getIdSeguimiento();
                if (findSeguimientoAspirante(id) == null) {
                    throw new NonexistentEntityException("The seguimientoAspirante with id " + id + " no longer exists.");
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
            SeguimientoAspirante seguimientoAspirante;
            try {
                seguimientoAspirante = em.getReference(SeguimientoAspirante.class, id);
                seguimientoAspirante.getIdSeguimiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seguimientoAspirante with id " + id + " no longer exists.", enfe);
            }
            Aspirante idAspirante = seguimientoAspirante.getIdAspirante();
            if (idAspirante != null) {
                idAspirante.getSeguimientoAspiranteList().remove(seguimientoAspirante);
                idAspirante = em.merge(idAspirante);
            }
            em.remove(seguimientoAspirante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeguimientoAspirante> findSeguimientoAspiranteEntities() {
        return findSeguimientoAspiranteEntities(true, -1, -1);
    }

    public List<SeguimientoAspirante> findSeguimientoAspiranteEntities(int maxResults, int firstResult) {
        return findSeguimientoAspiranteEntities(false, maxResults, firstResult);
    }

    private List<SeguimientoAspirante> findSeguimientoAspiranteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from SeguimientoAspirante as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public SeguimientoAspirante findSeguimientoAspirante(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeguimientoAspirante.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeguimientoAspiranteCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from SeguimientoAspirante as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
