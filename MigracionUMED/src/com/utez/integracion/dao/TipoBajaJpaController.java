/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.SolicitudBaja;
import com.utez.integracion.entity.TipoBaja;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoBajaJpaController implements Serializable {

    public TipoBajaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoBaja tipoBaja) {
        if (tipoBaja.getSolicitudBajaList() == null) {
            tipoBaja.setSolicitudBajaList(new ArrayList<SolicitudBaja>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SolicitudBaja> attachedSolicitudBajaList = new ArrayList<SolicitudBaja>();
            for (SolicitudBaja solicitudBajaListSolicitudBajaToAttach : tipoBaja.getSolicitudBajaList()) {
                solicitudBajaListSolicitudBajaToAttach = em.getReference(solicitudBajaListSolicitudBajaToAttach.getClass(), solicitudBajaListSolicitudBajaToAttach.getIdSolicitudbaja());
                attachedSolicitudBajaList.add(solicitudBajaListSolicitudBajaToAttach);
            }
            tipoBaja.setSolicitudBajaList(attachedSolicitudBajaList);
            em.persist(tipoBaja);
            for (SolicitudBaja solicitudBajaListSolicitudBaja : tipoBaja.getSolicitudBajaList()) {
                TipoBaja oldIdTipobajaOfSolicitudBajaListSolicitudBaja = solicitudBajaListSolicitudBaja.getIdTipobaja();
                solicitudBajaListSolicitudBaja.setIdTipobaja(tipoBaja);
                solicitudBajaListSolicitudBaja = em.merge(solicitudBajaListSolicitudBaja);
                if (oldIdTipobajaOfSolicitudBajaListSolicitudBaja != null) {
                    oldIdTipobajaOfSolicitudBajaListSolicitudBaja.getSolicitudBajaList().remove(solicitudBajaListSolicitudBaja);
                    oldIdTipobajaOfSolicitudBajaListSolicitudBaja = em.merge(oldIdTipobajaOfSolicitudBajaListSolicitudBaja);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoBaja tipoBaja) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoBaja persistentTipoBaja = em.find(TipoBaja.class, tipoBaja.getIdTipobaja());
            List<SolicitudBaja> solicitudBajaListOld = persistentTipoBaja.getSolicitudBajaList();
            List<SolicitudBaja> solicitudBajaListNew = tipoBaja.getSolicitudBajaList();
            List<SolicitudBaja> attachedSolicitudBajaListNew = new ArrayList<SolicitudBaja>();
            for (SolicitudBaja solicitudBajaListNewSolicitudBajaToAttach : solicitudBajaListNew) {
                solicitudBajaListNewSolicitudBajaToAttach = em.getReference(solicitudBajaListNewSolicitudBajaToAttach.getClass(), solicitudBajaListNewSolicitudBajaToAttach.getIdSolicitudbaja());
                attachedSolicitudBajaListNew.add(solicitudBajaListNewSolicitudBajaToAttach);
            }
            solicitudBajaListNew = attachedSolicitudBajaListNew;
            tipoBaja.setSolicitudBajaList(solicitudBajaListNew);
            tipoBaja = em.merge(tipoBaja);
            for (SolicitudBaja solicitudBajaListOldSolicitudBaja : solicitudBajaListOld) {
                if (!solicitudBajaListNew.contains(solicitudBajaListOldSolicitudBaja)) {
                    solicitudBajaListOldSolicitudBaja.setIdTipobaja(null);
                    solicitudBajaListOldSolicitudBaja = em.merge(solicitudBajaListOldSolicitudBaja);
                }
            }
            for (SolicitudBaja solicitudBajaListNewSolicitudBaja : solicitudBajaListNew) {
                if (!solicitudBajaListOld.contains(solicitudBajaListNewSolicitudBaja)) {
                    TipoBaja oldIdTipobajaOfSolicitudBajaListNewSolicitudBaja = solicitudBajaListNewSolicitudBaja.getIdTipobaja();
                    solicitudBajaListNewSolicitudBaja.setIdTipobaja(tipoBaja);
                    solicitudBajaListNewSolicitudBaja = em.merge(solicitudBajaListNewSolicitudBaja);
                    if (oldIdTipobajaOfSolicitudBajaListNewSolicitudBaja != null && !oldIdTipobajaOfSolicitudBajaListNewSolicitudBaja.equals(tipoBaja)) {
                        oldIdTipobajaOfSolicitudBajaListNewSolicitudBaja.getSolicitudBajaList().remove(solicitudBajaListNewSolicitudBaja);
                        oldIdTipobajaOfSolicitudBajaListNewSolicitudBaja = em.merge(oldIdTipobajaOfSolicitudBajaListNewSolicitudBaja);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoBaja.getIdTipobaja();
                if (findTipoBaja(id) == null) {
                    throw new NonexistentEntityException("The tipoBaja with id " + id + " no longer exists.");
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
            TipoBaja tipoBaja;
            try {
                tipoBaja = em.getReference(TipoBaja.class, id);
                tipoBaja.getIdTipobaja();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoBaja with id " + id + " no longer exists.", enfe);
            }
            List<SolicitudBaja> solicitudBajaList = tipoBaja.getSolicitudBajaList();
            for (SolicitudBaja solicitudBajaListSolicitudBaja : solicitudBajaList) {
                solicitudBajaListSolicitudBaja.setIdTipobaja(null);
                solicitudBajaListSolicitudBaja = em.merge(solicitudBajaListSolicitudBaja);
            }
            em.remove(tipoBaja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoBaja> findTipoBajaEntities() {
        return findTipoBajaEntities(true, -1, -1);
    }

    public List<TipoBaja> findTipoBajaEntities(int maxResults, int firstResult) {
        return findTipoBajaEntities(false, maxResults, firstResult);
    }

    private List<TipoBaja> findTipoBajaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoBaja as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoBaja findTipoBaja(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoBaja.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoBajaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoBaja as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
