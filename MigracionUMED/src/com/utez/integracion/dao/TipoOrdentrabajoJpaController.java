/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.OrdenTrabajo;
import com.utez.integracion.entity.TipoOrdentrabajo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoOrdentrabajoJpaController implements Serializable {

    public TipoOrdentrabajoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoOrdentrabajo tipoOrdentrabajo) {
        if (tipoOrdentrabajo.getOrdenTrabajoList() == null) {
            tipoOrdentrabajo.setOrdenTrabajoList(new ArrayList<OrdenTrabajo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<OrdenTrabajo> attachedOrdenTrabajoList = new ArrayList<OrdenTrabajo>();
            for (OrdenTrabajo ordenTrabajoListOrdenTrabajoToAttach : tipoOrdentrabajo.getOrdenTrabajoList()) {
                ordenTrabajoListOrdenTrabajoToAttach = em.getReference(ordenTrabajoListOrdenTrabajoToAttach.getClass(), ordenTrabajoListOrdenTrabajoToAttach.getIdOrdentrabajo());
                attachedOrdenTrabajoList.add(ordenTrabajoListOrdenTrabajoToAttach);
            }
            tipoOrdentrabajo.setOrdenTrabajoList(attachedOrdenTrabajoList);
            em.persist(tipoOrdentrabajo);
            for (OrdenTrabajo ordenTrabajoListOrdenTrabajo : tipoOrdentrabajo.getOrdenTrabajoList()) {
                TipoOrdentrabajo oldIdTipoordentrabajoOfOrdenTrabajoListOrdenTrabajo = ordenTrabajoListOrdenTrabajo.getIdTipoordentrabajo();
                ordenTrabajoListOrdenTrabajo.setIdTipoordentrabajo(tipoOrdentrabajo);
                ordenTrabajoListOrdenTrabajo = em.merge(ordenTrabajoListOrdenTrabajo);
                if (oldIdTipoordentrabajoOfOrdenTrabajoListOrdenTrabajo != null) {
                    oldIdTipoordentrabajoOfOrdenTrabajoListOrdenTrabajo.getOrdenTrabajoList().remove(ordenTrabajoListOrdenTrabajo);
                    oldIdTipoordentrabajoOfOrdenTrabajoListOrdenTrabajo = em.merge(oldIdTipoordentrabajoOfOrdenTrabajoListOrdenTrabajo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoOrdentrabajo tipoOrdentrabajo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoOrdentrabajo persistentTipoOrdentrabajo = em.find(TipoOrdentrabajo.class, tipoOrdentrabajo.getIdTipoordentrabajo());
            List<OrdenTrabajo> ordenTrabajoListOld = persistentTipoOrdentrabajo.getOrdenTrabajoList();
            List<OrdenTrabajo> ordenTrabajoListNew = tipoOrdentrabajo.getOrdenTrabajoList();
            List<OrdenTrabajo> attachedOrdenTrabajoListNew = new ArrayList<OrdenTrabajo>();
            for (OrdenTrabajo ordenTrabajoListNewOrdenTrabajoToAttach : ordenTrabajoListNew) {
                ordenTrabajoListNewOrdenTrabajoToAttach = em.getReference(ordenTrabajoListNewOrdenTrabajoToAttach.getClass(), ordenTrabajoListNewOrdenTrabajoToAttach.getIdOrdentrabajo());
                attachedOrdenTrabajoListNew.add(ordenTrabajoListNewOrdenTrabajoToAttach);
            }
            ordenTrabajoListNew = attachedOrdenTrabajoListNew;
            tipoOrdentrabajo.setOrdenTrabajoList(ordenTrabajoListNew);
            tipoOrdentrabajo = em.merge(tipoOrdentrabajo);
            for (OrdenTrabajo ordenTrabajoListOldOrdenTrabajo : ordenTrabajoListOld) {
                if (!ordenTrabajoListNew.contains(ordenTrabajoListOldOrdenTrabajo)) {
                    ordenTrabajoListOldOrdenTrabajo.setIdTipoordentrabajo(null);
                    ordenTrabajoListOldOrdenTrabajo = em.merge(ordenTrabajoListOldOrdenTrabajo);
                }
            }
            for (OrdenTrabajo ordenTrabajoListNewOrdenTrabajo : ordenTrabajoListNew) {
                if (!ordenTrabajoListOld.contains(ordenTrabajoListNewOrdenTrabajo)) {
                    TipoOrdentrabajo oldIdTipoordentrabajoOfOrdenTrabajoListNewOrdenTrabajo = ordenTrabajoListNewOrdenTrabajo.getIdTipoordentrabajo();
                    ordenTrabajoListNewOrdenTrabajo.setIdTipoordentrabajo(tipoOrdentrabajo);
                    ordenTrabajoListNewOrdenTrabajo = em.merge(ordenTrabajoListNewOrdenTrabajo);
                    if (oldIdTipoordentrabajoOfOrdenTrabajoListNewOrdenTrabajo != null && !oldIdTipoordentrabajoOfOrdenTrabajoListNewOrdenTrabajo.equals(tipoOrdentrabajo)) {
                        oldIdTipoordentrabajoOfOrdenTrabajoListNewOrdenTrabajo.getOrdenTrabajoList().remove(ordenTrabajoListNewOrdenTrabajo);
                        oldIdTipoordentrabajoOfOrdenTrabajoListNewOrdenTrabajo = em.merge(oldIdTipoordentrabajoOfOrdenTrabajoListNewOrdenTrabajo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoOrdentrabajo.getIdTipoordentrabajo();
                if (findTipoOrdentrabajo(id) == null) {
                    throw new NonexistentEntityException("The tipoOrdentrabajo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoOrdentrabajo tipoOrdentrabajo;
            try {
                tipoOrdentrabajo = em.getReference(TipoOrdentrabajo.class, id);
                tipoOrdentrabajo.getIdTipoordentrabajo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoOrdentrabajo with id " + id + " no longer exists.", enfe);
            }
            List<OrdenTrabajo> ordenTrabajoList = tipoOrdentrabajo.getOrdenTrabajoList();
            for (OrdenTrabajo ordenTrabajoListOrdenTrabajo : ordenTrabajoList) {
                ordenTrabajoListOrdenTrabajo.setIdTipoordentrabajo(null);
                ordenTrabajoListOrdenTrabajo = em.merge(ordenTrabajoListOrdenTrabajo);
            }
            em.remove(tipoOrdentrabajo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoOrdentrabajo> findTipoOrdentrabajoEntities() {
        return findTipoOrdentrabajoEntities(true, -1, -1);
    }

    public List<TipoOrdentrabajo> findTipoOrdentrabajoEntities(int maxResults, int firstResult) {
        return findTipoOrdentrabajoEntities(false, maxResults, firstResult);
    }

    private List<TipoOrdentrabajo> findTipoOrdentrabajoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoOrdentrabajo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoOrdentrabajo findTipoOrdentrabajo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoOrdentrabajo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoOrdentrabajoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoOrdentrabajo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
