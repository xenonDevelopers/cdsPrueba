/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.OrdenTrabajo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoOrdentrabajo;
import com.utez.integracion.entity.TramiteTitulacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class OrdenTrabajoJpaController implements Serializable {

    public OrdenTrabajoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrdenTrabajo ordenTrabajo) {
        if (ordenTrabajo.getTramiteTitulacionList() == null) {
            ordenTrabajo.setTramiteTitulacionList(new ArrayList<TramiteTitulacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoOrdentrabajo idTipoordentrabajo = ordenTrabajo.getIdTipoordentrabajo();
            if (idTipoordentrabajo != null) {
                idTipoordentrabajo = em.getReference(idTipoordentrabajo.getClass(), idTipoordentrabajo.getIdTipoordentrabajo());
                ordenTrabajo.setIdTipoordentrabajo(idTipoordentrabajo);
            }
            List<TramiteTitulacion> attachedTramiteTitulacionList = new ArrayList<TramiteTitulacion>();
            for (TramiteTitulacion tramiteTitulacionListTramiteTitulacionToAttach : ordenTrabajo.getTramiteTitulacionList()) {
                tramiteTitulacionListTramiteTitulacionToAttach = em.getReference(tramiteTitulacionListTramiteTitulacionToAttach.getClass(), tramiteTitulacionListTramiteTitulacionToAttach.getIdTramitetitulacion());
                attachedTramiteTitulacionList.add(tramiteTitulacionListTramiteTitulacionToAttach);
            }
            ordenTrabajo.setTramiteTitulacionList(attachedTramiteTitulacionList);
            em.persist(ordenTrabajo);
            if (idTipoordentrabajo != null) {
                idTipoordentrabajo.getOrdenTrabajoList().add(ordenTrabajo);
                idTipoordentrabajo = em.merge(idTipoordentrabajo);
            }
            for (TramiteTitulacion tramiteTitulacionListTramiteTitulacion : ordenTrabajo.getTramiteTitulacionList()) {
                tramiteTitulacionListTramiteTitulacion.getOrdenTrabajoList().add(ordenTrabajo);
                tramiteTitulacionListTramiteTitulacion = em.merge(tramiteTitulacionListTramiteTitulacion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrdenTrabajo ordenTrabajo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrdenTrabajo persistentOrdenTrabajo = em.find(OrdenTrabajo.class, ordenTrabajo.getIdOrdentrabajo());
            TipoOrdentrabajo idTipoordentrabajoOld = persistentOrdenTrabajo.getIdTipoordentrabajo();
            TipoOrdentrabajo idTipoordentrabajoNew = ordenTrabajo.getIdTipoordentrabajo();
            List<TramiteTitulacion> tramiteTitulacionListOld = persistentOrdenTrabajo.getTramiteTitulacionList();
            List<TramiteTitulacion> tramiteTitulacionListNew = ordenTrabajo.getTramiteTitulacionList();
            if (idTipoordentrabajoNew != null) {
                idTipoordentrabajoNew = em.getReference(idTipoordentrabajoNew.getClass(), idTipoordentrabajoNew.getIdTipoordentrabajo());
                ordenTrabajo.setIdTipoordentrabajo(idTipoordentrabajoNew);
            }
            List<TramiteTitulacion> attachedTramiteTitulacionListNew = new ArrayList<TramiteTitulacion>();
            for (TramiteTitulacion tramiteTitulacionListNewTramiteTitulacionToAttach : tramiteTitulacionListNew) {
                tramiteTitulacionListNewTramiteTitulacionToAttach = em.getReference(tramiteTitulacionListNewTramiteTitulacionToAttach.getClass(), tramiteTitulacionListNewTramiteTitulacionToAttach.getIdTramitetitulacion());
                attachedTramiteTitulacionListNew.add(tramiteTitulacionListNewTramiteTitulacionToAttach);
            }
            tramiteTitulacionListNew = attachedTramiteTitulacionListNew;
            ordenTrabajo.setTramiteTitulacionList(tramiteTitulacionListNew);
            ordenTrabajo = em.merge(ordenTrabajo);
            if (idTipoordentrabajoOld != null && !idTipoordentrabajoOld.equals(idTipoordentrabajoNew)) {
                idTipoordentrabajoOld.getOrdenTrabajoList().remove(ordenTrabajo);
                idTipoordentrabajoOld = em.merge(idTipoordentrabajoOld);
            }
            if (idTipoordentrabajoNew != null && !idTipoordentrabajoNew.equals(idTipoordentrabajoOld)) {
                idTipoordentrabajoNew.getOrdenTrabajoList().add(ordenTrabajo);
                idTipoordentrabajoNew = em.merge(idTipoordentrabajoNew);
            }
            for (TramiteTitulacion tramiteTitulacionListOldTramiteTitulacion : tramiteTitulacionListOld) {
                if (!tramiteTitulacionListNew.contains(tramiteTitulacionListOldTramiteTitulacion)) {
                    tramiteTitulacionListOldTramiteTitulacion.getOrdenTrabajoList().remove(ordenTrabajo);
                    tramiteTitulacionListOldTramiteTitulacion = em.merge(tramiteTitulacionListOldTramiteTitulacion);
                }
            }
            for (TramiteTitulacion tramiteTitulacionListNewTramiteTitulacion : tramiteTitulacionListNew) {
                if (!tramiteTitulacionListOld.contains(tramiteTitulacionListNewTramiteTitulacion)) {
                    tramiteTitulacionListNewTramiteTitulacion.getOrdenTrabajoList().add(ordenTrabajo);
                    tramiteTitulacionListNewTramiteTitulacion = em.merge(tramiteTitulacionListNewTramiteTitulacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ordenTrabajo.getIdOrdentrabajo();
                if (findOrdenTrabajo(id) == null) {
                    throw new NonexistentEntityException("The ordenTrabajo with id " + id + " no longer exists.");
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
            OrdenTrabajo ordenTrabajo;
            try {
                ordenTrabajo = em.getReference(OrdenTrabajo.class, id);
                ordenTrabajo.getIdOrdentrabajo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ordenTrabajo with id " + id + " no longer exists.", enfe);
            }
            TipoOrdentrabajo idTipoordentrabajo = ordenTrabajo.getIdTipoordentrabajo();
            if (idTipoordentrabajo != null) {
                idTipoordentrabajo.getOrdenTrabajoList().remove(ordenTrabajo);
                idTipoordentrabajo = em.merge(idTipoordentrabajo);
            }
            List<TramiteTitulacion> tramiteTitulacionList = ordenTrabajo.getTramiteTitulacionList();
            for (TramiteTitulacion tramiteTitulacionListTramiteTitulacion : tramiteTitulacionList) {
                tramiteTitulacionListTramiteTitulacion.getOrdenTrabajoList().remove(ordenTrabajo);
                tramiteTitulacionListTramiteTitulacion = em.merge(tramiteTitulacionListTramiteTitulacion);
            }
            em.remove(ordenTrabajo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrdenTrabajo> findOrdenTrabajoEntities() {
        return findOrdenTrabajoEntities(true, -1, -1);
    }

    public List<OrdenTrabajo> findOrdenTrabajoEntities(int maxResults, int firstResult) {
        return findOrdenTrabajoEntities(false, maxResults, firstResult);
    }

    private List<OrdenTrabajo> findOrdenTrabajoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from OrdenTrabajo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public OrdenTrabajo findOrdenTrabajo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrdenTrabajo.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdenTrabajoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from OrdenTrabajo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
