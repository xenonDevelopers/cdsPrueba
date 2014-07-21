/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.TipoAmbitomovimiento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoMovimiento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoAmbitomovimientoJpaController implements Serializable {

    public TipoAmbitomovimientoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoAmbitomovimiento tipoAmbitomovimiento) {
        if (tipoAmbitomovimiento.getTipoMovimientoList() == null) {
            tipoAmbitomovimiento.setTipoMovimientoList(new ArrayList<TipoMovimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<TipoMovimiento> attachedTipoMovimientoList = new ArrayList<TipoMovimiento>();
            for (TipoMovimiento tipoMovimientoListTipoMovimientoToAttach : tipoAmbitomovimiento.getTipoMovimientoList()) {
                tipoMovimientoListTipoMovimientoToAttach = em.getReference(tipoMovimientoListTipoMovimientoToAttach.getClass(), tipoMovimientoListTipoMovimientoToAttach.getIdTipomovimiento());
                attachedTipoMovimientoList.add(tipoMovimientoListTipoMovimientoToAttach);
            }
            tipoAmbitomovimiento.setTipoMovimientoList(attachedTipoMovimientoList);
            em.persist(tipoAmbitomovimiento);
            for (TipoMovimiento tipoMovimientoListTipoMovimiento : tipoAmbitomovimiento.getTipoMovimientoList()) {
                tipoMovimientoListTipoMovimiento.getTipoAmbitomovimientoList().add(tipoAmbitomovimiento);
                tipoMovimientoListTipoMovimiento = em.merge(tipoMovimientoListTipoMovimiento);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoAmbitomovimiento tipoAmbitomovimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoAmbitomovimiento persistentTipoAmbitomovimiento = em.find(TipoAmbitomovimiento.class, tipoAmbitomovimiento.getIdTipoambitomovimiento());
            List<TipoMovimiento> tipoMovimientoListOld = persistentTipoAmbitomovimiento.getTipoMovimientoList();
            List<TipoMovimiento> tipoMovimientoListNew = tipoAmbitomovimiento.getTipoMovimientoList();
            List<TipoMovimiento> attachedTipoMovimientoListNew = new ArrayList<TipoMovimiento>();
            for (TipoMovimiento tipoMovimientoListNewTipoMovimientoToAttach : tipoMovimientoListNew) {
                tipoMovimientoListNewTipoMovimientoToAttach = em.getReference(tipoMovimientoListNewTipoMovimientoToAttach.getClass(), tipoMovimientoListNewTipoMovimientoToAttach.getIdTipomovimiento());
                attachedTipoMovimientoListNew.add(tipoMovimientoListNewTipoMovimientoToAttach);
            }
            tipoMovimientoListNew = attachedTipoMovimientoListNew;
            tipoAmbitomovimiento.setTipoMovimientoList(tipoMovimientoListNew);
            tipoAmbitomovimiento = em.merge(tipoAmbitomovimiento);
            for (TipoMovimiento tipoMovimientoListOldTipoMovimiento : tipoMovimientoListOld) {
                if (!tipoMovimientoListNew.contains(tipoMovimientoListOldTipoMovimiento)) {
                    tipoMovimientoListOldTipoMovimiento.getTipoAmbitomovimientoList().remove(tipoAmbitomovimiento);
                    tipoMovimientoListOldTipoMovimiento = em.merge(tipoMovimientoListOldTipoMovimiento);
                }
            }
            for (TipoMovimiento tipoMovimientoListNewTipoMovimiento : tipoMovimientoListNew) {
                if (!tipoMovimientoListOld.contains(tipoMovimientoListNewTipoMovimiento)) {
                    tipoMovimientoListNewTipoMovimiento.getTipoAmbitomovimientoList().add(tipoAmbitomovimiento);
                    tipoMovimientoListNewTipoMovimiento = em.merge(tipoMovimientoListNewTipoMovimiento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoAmbitomovimiento.getIdTipoambitomovimiento();
                if (findTipoAmbitomovimiento(id) == null) {
                    throw new NonexistentEntityException("The tipoAmbitomovimiento with id " + id + " no longer exists.");
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
            TipoAmbitomovimiento tipoAmbitomovimiento;
            try {
                tipoAmbitomovimiento = em.getReference(TipoAmbitomovimiento.class, id);
                tipoAmbitomovimiento.getIdTipoambitomovimiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoAmbitomovimiento with id " + id + " no longer exists.", enfe);
            }
            List<TipoMovimiento> tipoMovimientoList = tipoAmbitomovimiento.getTipoMovimientoList();
            for (TipoMovimiento tipoMovimientoListTipoMovimiento : tipoMovimientoList) {
                tipoMovimientoListTipoMovimiento.getTipoAmbitomovimientoList().remove(tipoAmbitomovimiento);
                tipoMovimientoListTipoMovimiento = em.merge(tipoMovimientoListTipoMovimiento);
            }
            em.remove(tipoAmbitomovimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoAmbitomovimiento> findTipoAmbitomovimientoEntities() {
        return findTipoAmbitomovimientoEntities(true, -1, -1);
    }

    public List<TipoAmbitomovimiento> findTipoAmbitomovimientoEntities(int maxResults, int firstResult) {
        return findTipoAmbitomovimientoEntities(false, maxResults, firstResult);
    }

    private List<TipoAmbitomovimiento> findTipoAmbitomovimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoAmbitomovimiento as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoAmbitomovimiento findTipoAmbitomovimiento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoAmbitomovimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoAmbitomovimientoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoAmbitomovimiento as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
