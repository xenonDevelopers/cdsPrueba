/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import com.utez.integracion.entity.FolioCambioscalendario;
import com.utez.integracion.entity.FolioCambioscalendarioPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoMovimiento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class FolioCambioscalendarioJpaController implements Serializable {

    public FolioCambioscalendarioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FolioCambioscalendario folioCambioscalendario) throws PreexistingEntityException, Exception {
        if (folioCambioscalendario.getFolioCambioscalendarioPK() == null) {
            folioCambioscalendario.setFolioCambioscalendarioPK(new FolioCambioscalendarioPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoMovimiento idMovimiento = folioCambioscalendario.getIdMovimiento();
            if (idMovimiento != null) {
                idMovimiento = em.getReference(idMovimiento.getClass(), idMovimiento.getIdTipomovimiento());
                folioCambioscalendario.setIdMovimiento(idMovimiento);
            }
            em.persist(folioCambioscalendario);
            if (idMovimiento != null) {
                idMovimiento.getFolioCambioscalendarioList().add(folioCambioscalendario);
                idMovimiento = em.merge(idMovimiento);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFolioCambioscalendario(folioCambioscalendario.getFolioCambioscalendarioPK()) != null) {
                throw new PreexistingEntityException("FolioCambioscalendario " + folioCambioscalendario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FolioCambioscalendario folioCambioscalendario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FolioCambioscalendario persistentFolioCambioscalendario = em.find(FolioCambioscalendario.class, folioCambioscalendario.getFolioCambioscalendarioPK());
            TipoMovimiento idMovimientoOld = persistentFolioCambioscalendario.getIdMovimiento();
            TipoMovimiento idMovimientoNew = folioCambioscalendario.getIdMovimiento();
            if (idMovimientoNew != null) {
                idMovimientoNew = em.getReference(idMovimientoNew.getClass(), idMovimientoNew.getIdTipomovimiento());
                folioCambioscalendario.setIdMovimiento(idMovimientoNew);
            }
            folioCambioscalendario = em.merge(folioCambioscalendario);
            if (idMovimientoOld != null && !idMovimientoOld.equals(idMovimientoNew)) {
                idMovimientoOld.getFolioCambioscalendarioList().remove(folioCambioscalendario);
                idMovimientoOld = em.merge(idMovimientoOld);
            }
            if (idMovimientoNew != null && !idMovimientoNew.equals(idMovimientoOld)) {
                idMovimientoNew.getFolioCambioscalendarioList().add(folioCambioscalendario);
                idMovimientoNew = em.merge(idMovimientoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                FolioCambioscalendarioPK id = folioCambioscalendario.getFolioCambioscalendarioPK();
                if (findFolioCambioscalendario(id) == null) {
                    throw new NonexistentEntityException("The folioCambioscalendario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(FolioCambioscalendarioPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FolioCambioscalendario folioCambioscalendario;
            try {
                folioCambioscalendario = em.getReference(FolioCambioscalendario.class, id);
                folioCambioscalendario.getFolioCambioscalendarioPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The folioCambioscalendario with id " + id + " no longer exists.", enfe);
            }
            TipoMovimiento idMovimiento = folioCambioscalendario.getIdMovimiento();
            if (idMovimiento != null) {
                idMovimiento.getFolioCambioscalendarioList().remove(folioCambioscalendario);
                idMovimiento = em.merge(idMovimiento);
            }
            em.remove(folioCambioscalendario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FolioCambioscalendario> findFolioCambioscalendarioEntities() {
        return findFolioCambioscalendarioEntities(true, -1, -1);
    }

    public List<FolioCambioscalendario> findFolioCambioscalendarioEntities(int maxResults, int firstResult) {
        return findFolioCambioscalendarioEntities(false, maxResults, firstResult);
    }

    private List<FolioCambioscalendario> findFolioCambioscalendarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from FolioCambioscalendario as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public FolioCambioscalendario findFolioCambioscalendario(FolioCambioscalendarioPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FolioCambioscalendario.class, id);
        } finally {
            em.close();
        }
    }

    public int getFolioCambioscalendarioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from FolioCambioscalendario as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
