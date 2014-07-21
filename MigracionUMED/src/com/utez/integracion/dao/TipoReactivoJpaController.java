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
import com.utez.integracion.entity.TipoReactivo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoReactivoJpaController implements Serializable {

    public TipoReactivoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoReactivo tipoReactivo) {
        if (tipoReactivo.getReactivoList() == null) {
            tipoReactivo.setReactivoList(new ArrayList<Reactivo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reactivo> attachedReactivoList = new ArrayList<Reactivo>();
            for (Reactivo reactivoListReactivoToAttach : tipoReactivo.getReactivoList()) {
                reactivoListReactivoToAttach = em.getReference(reactivoListReactivoToAttach.getClass(), reactivoListReactivoToAttach.getIdReactivo());
                attachedReactivoList.add(reactivoListReactivoToAttach);
            }
            tipoReactivo.setReactivoList(attachedReactivoList);
            em.persist(tipoReactivo);
            for (Reactivo reactivoListReactivo : tipoReactivo.getReactivoList()) {
                TipoReactivo oldIdTiporeactivoOfReactivoListReactivo = reactivoListReactivo.getIdTiporeactivo();
                reactivoListReactivo.setIdTiporeactivo(tipoReactivo);
                reactivoListReactivo = em.merge(reactivoListReactivo);
                if (oldIdTiporeactivoOfReactivoListReactivo != null) {
                    oldIdTiporeactivoOfReactivoListReactivo.getReactivoList().remove(reactivoListReactivo);
                    oldIdTiporeactivoOfReactivoListReactivo = em.merge(oldIdTiporeactivoOfReactivoListReactivo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoReactivo tipoReactivo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoReactivo persistentTipoReactivo = em.find(TipoReactivo.class, tipoReactivo.getIdTiporeactivo());
            List<Reactivo> reactivoListOld = persistentTipoReactivo.getReactivoList();
            List<Reactivo> reactivoListNew = tipoReactivo.getReactivoList();
            List<Reactivo> attachedReactivoListNew = new ArrayList<Reactivo>();
            for (Reactivo reactivoListNewReactivoToAttach : reactivoListNew) {
                reactivoListNewReactivoToAttach = em.getReference(reactivoListNewReactivoToAttach.getClass(), reactivoListNewReactivoToAttach.getIdReactivo());
                attachedReactivoListNew.add(reactivoListNewReactivoToAttach);
            }
            reactivoListNew = attachedReactivoListNew;
            tipoReactivo.setReactivoList(reactivoListNew);
            tipoReactivo = em.merge(tipoReactivo);
            for (Reactivo reactivoListOldReactivo : reactivoListOld) {
                if (!reactivoListNew.contains(reactivoListOldReactivo)) {
                    reactivoListOldReactivo.setIdTiporeactivo(null);
                    reactivoListOldReactivo = em.merge(reactivoListOldReactivo);
                }
            }
            for (Reactivo reactivoListNewReactivo : reactivoListNew) {
                if (!reactivoListOld.contains(reactivoListNewReactivo)) {
                    TipoReactivo oldIdTiporeactivoOfReactivoListNewReactivo = reactivoListNewReactivo.getIdTiporeactivo();
                    reactivoListNewReactivo.setIdTiporeactivo(tipoReactivo);
                    reactivoListNewReactivo = em.merge(reactivoListNewReactivo);
                    if (oldIdTiporeactivoOfReactivoListNewReactivo != null && !oldIdTiporeactivoOfReactivoListNewReactivo.equals(tipoReactivo)) {
                        oldIdTiporeactivoOfReactivoListNewReactivo.getReactivoList().remove(reactivoListNewReactivo);
                        oldIdTiporeactivoOfReactivoListNewReactivo = em.merge(oldIdTiporeactivoOfReactivoListNewReactivo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoReactivo.getIdTiporeactivo();
                if (findTipoReactivo(id) == null) {
                    throw new NonexistentEntityException("The tipoReactivo with id " + id + " no longer exists.");
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
            TipoReactivo tipoReactivo;
            try {
                tipoReactivo = em.getReference(TipoReactivo.class, id);
                tipoReactivo.getIdTiporeactivo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoReactivo with id " + id + " no longer exists.", enfe);
            }
            List<Reactivo> reactivoList = tipoReactivo.getReactivoList();
            for (Reactivo reactivoListReactivo : reactivoList) {
                reactivoListReactivo.setIdTiporeactivo(null);
                reactivoListReactivo = em.merge(reactivoListReactivo);
            }
            em.remove(tipoReactivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoReactivo> findTipoReactivoEntities() {
        return findTipoReactivoEntities(true, -1, -1);
    }

    public List<TipoReactivo> findTipoReactivoEntities(int maxResults, int firstResult) {
        return findTipoReactivoEntities(false, maxResults, firstResult);
    }

    private List<TipoReactivo> findTipoReactivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoReactivo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoReactivo findTipoReactivo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoReactivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoReactivoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoReactivo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
