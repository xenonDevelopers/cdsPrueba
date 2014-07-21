/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.PagoPeriodo;
import com.utez.integracion.entity.TipoPago;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoPagoJpaController implements Serializable {

    public TipoPagoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoPago tipoPago) {
        if (tipoPago.getPagoPeriodoList() == null) {
            tipoPago.setPagoPeriodoList(new ArrayList<PagoPeriodo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PagoPeriodo> attachedPagoPeriodoList = new ArrayList<PagoPeriodo>();
            for (PagoPeriodo pagoPeriodoListPagoPeriodoToAttach : tipoPago.getPagoPeriodoList()) {
                pagoPeriodoListPagoPeriodoToAttach = em.getReference(pagoPeriodoListPagoPeriodoToAttach.getClass(), pagoPeriodoListPagoPeriodoToAttach.getIdPagoperiodo());
                attachedPagoPeriodoList.add(pagoPeriodoListPagoPeriodoToAttach);
            }
            tipoPago.setPagoPeriodoList(attachedPagoPeriodoList);
            em.persist(tipoPago);
            for (PagoPeriodo pagoPeriodoListPagoPeriodo : tipoPago.getPagoPeriodoList()) {
                TipoPago oldIdTipopagoOfPagoPeriodoListPagoPeriodo = pagoPeriodoListPagoPeriodo.getIdTipopago();
                pagoPeriodoListPagoPeriodo.setIdTipopago(tipoPago);
                pagoPeriodoListPagoPeriodo = em.merge(pagoPeriodoListPagoPeriodo);
                if (oldIdTipopagoOfPagoPeriodoListPagoPeriodo != null) {
                    oldIdTipopagoOfPagoPeriodoListPagoPeriodo.getPagoPeriodoList().remove(pagoPeriodoListPagoPeriodo);
                    oldIdTipopagoOfPagoPeriodoListPagoPeriodo = em.merge(oldIdTipopagoOfPagoPeriodoListPagoPeriodo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoPago tipoPago) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoPago persistentTipoPago = em.find(TipoPago.class, tipoPago.getIdTipopago());
            List<PagoPeriodo> pagoPeriodoListOld = persistentTipoPago.getPagoPeriodoList();
            List<PagoPeriodo> pagoPeriodoListNew = tipoPago.getPagoPeriodoList();
            List<PagoPeriodo> attachedPagoPeriodoListNew = new ArrayList<PagoPeriodo>();
            for (PagoPeriodo pagoPeriodoListNewPagoPeriodoToAttach : pagoPeriodoListNew) {
                pagoPeriodoListNewPagoPeriodoToAttach = em.getReference(pagoPeriodoListNewPagoPeriodoToAttach.getClass(), pagoPeriodoListNewPagoPeriodoToAttach.getIdPagoperiodo());
                attachedPagoPeriodoListNew.add(pagoPeriodoListNewPagoPeriodoToAttach);
            }
            pagoPeriodoListNew = attachedPagoPeriodoListNew;
            tipoPago.setPagoPeriodoList(pagoPeriodoListNew);
            tipoPago = em.merge(tipoPago);
            for (PagoPeriodo pagoPeriodoListOldPagoPeriodo : pagoPeriodoListOld) {
                if (!pagoPeriodoListNew.contains(pagoPeriodoListOldPagoPeriodo)) {
                    pagoPeriodoListOldPagoPeriodo.setIdTipopago(null);
                    pagoPeriodoListOldPagoPeriodo = em.merge(pagoPeriodoListOldPagoPeriodo);
                }
            }
            for (PagoPeriodo pagoPeriodoListNewPagoPeriodo : pagoPeriodoListNew) {
                if (!pagoPeriodoListOld.contains(pagoPeriodoListNewPagoPeriodo)) {
                    TipoPago oldIdTipopagoOfPagoPeriodoListNewPagoPeriodo = pagoPeriodoListNewPagoPeriodo.getIdTipopago();
                    pagoPeriodoListNewPagoPeriodo.setIdTipopago(tipoPago);
                    pagoPeriodoListNewPagoPeriodo = em.merge(pagoPeriodoListNewPagoPeriodo);
                    if (oldIdTipopagoOfPagoPeriodoListNewPagoPeriodo != null && !oldIdTipopagoOfPagoPeriodoListNewPagoPeriodo.equals(tipoPago)) {
                        oldIdTipopagoOfPagoPeriodoListNewPagoPeriodo.getPagoPeriodoList().remove(pagoPeriodoListNewPagoPeriodo);
                        oldIdTipopagoOfPagoPeriodoListNewPagoPeriodo = em.merge(oldIdTipopagoOfPagoPeriodoListNewPagoPeriodo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoPago.getIdTipopago();
                if (findTipoPago(id) == null) {
                    throw new NonexistentEntityException("The tipoPago with id " + id + " no longer exists.");
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
            TipoPago tipoPago;
            try {
                tipoPago = em.getReference(TipoPago.class, id);
                tipoPago.getIdTipopago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoPago with id " + id + " no longer exists.", enfe);
            }
            List<PagoPeriodo> pagoPeriodoList = tipoPago.getPagoPeriodoList();
            for (PagoPeriodo pagoPeriodoListPagoPeriodo : pagoPeriodoList) {
                pagoPeriodoListPagoPeriodo.setIdTipopago(null);
                pagoPeriodoListPagoPeriodo = em.merge(pagoPeriodoListPagoPeriodo);
            }
            em.remove(tipoPago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoPago> findTipoPagoEntities() {
        return findTipoPagoEntities(true, -1, -1);
    }

    public List<TipoPago> findTipoPagoEntities(int maxResults, int firstResult) {
        return findTipoPagoEntities(false, maxResults, firstResult);
    }

    private List<TipoPago> findTipoPagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoPago as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoPago findTipoPago(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoPago.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoPagoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoPago as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
