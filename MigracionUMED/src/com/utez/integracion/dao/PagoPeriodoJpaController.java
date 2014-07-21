/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.PagoPeriodo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoPago;
import com.utez.secretariaAcademica.entity.Periodo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class PagoPeriodoJpaController implements Serializable {

    public PagoPeriodoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PagoPeriodo pagoPeriodo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoPago idTipopago = pagoPeriodo.getIdTipopago();
            if (idTipopago != null) {
                idTipopago = em.getReference(idTipopago.getClass(), idTipopago.getIdTipopago());
                pagoPeriodo.setIdTipopago(idTipopago);
            }
            Periodo idPeriodo = pagoPeriodo.getIdPeriodo();
            if (idPeriodo != null) {
                idPeriodo = em.getReference(idPeriodo.getClass(), idPeriodo.getPeriodo());
                pagoPeriodo.setIdPeriodo(idPeriodo);
            }
            em.persist(pagoPeriodo);
            if (idTipopago != null) {
                idTipopago.getPagoPeriodoList().add(pagoPeriodo);
                idTipopago = em.merge(idTipopago);
            }
            if (idPeriodo != null) {
                idPeriodo.getPagoPeriodoList().add(pagoPeriodo);
                idPeriodo = em.merge(idPeriodo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PagoPeriodo pagoPeriodo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PagoPeriodo persistentPagoPeriodo = em.find(PagoPeriodo.class, pagoPeriodo.getIdPagoperiodo());
            TipoPago idTipopagoOld = persistentPagoPeriodo.getIdTipopago();
            TipoPago idTipopagoNew = pagoPeriodo.getIdTipopago();
            Periodo idPeriodoOld = persistentPagoPeriodo.getIdPeriodo();
            Periodo idPeriodoNew = pagoPeriodo.getIdPeriodo();
            if (idTipopagoNew != null) {
                idTipopagoNew = em.getReference(idTipopagoNew.getClass(), idTipopagoNew.getIdTipopago());
                pagoPeriodo.setIdTipopago(idTipopagoNew);
            }
            if (idPeriodoNew != null) {
                idPeriodoNew = em.getReference(idPeriodoNew.getClass(), idPeriodoNew.getPeriodo());
                pagoPeriodo.setIdPeriodo(idPeriodoNew);
            }
            pagoPeriodo = em.merge(pagoPeriodo);
            if (idTipopagoOld != null && !idTipopagoOld.equals(idTipopagoNew)) {
                idTipopagoOld.getPagoPeriodoList().remove(pagoPeriodo);
                idTipopagoOld = em.merge(idTipopagoOld);
            }
            if (idTipopagoNew != null && !idTipopagoNew.equals(idTipopagoOld)) {
                idTipopagoNew.getPagoPeriodoList().add(pagoPeriodo);
                idTipopagoNew = em.merge(idTipopagoNew);
            }
            if (idPeriodoOld != null && !idPeriodoOld.equals(idPeriodoNew)) {
                idPeriodoOld.getPagoPeriodoList().remove(pagoPeriodo);
                idPeriodoOld = em.merge(idPeriodoOld);
            }
            if (idPeriodoNew != null && !idPeriodoNew.equals(idPeriodoOld)) {
                idPeriodoNew.getPagoPeriodoList().add(pagoPeriodo);
                idPeriodoNew = em.merge(idPeriodoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pagoPeriodo.getIdPagoperiodo();
                if (findPagoPeriodo(id) == null) {
                    throw new NonexistentEntityException("The pagoPeriodo with id " + id + " no longer exists.");
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
            PagoPeriodo pagoPeriodo;
            try {
                pagoPeriodo = em.getReference(PagoPeriodo.class, id);
                pagoPeriodo.getIdPagoperiodo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagoPeriodo with id " + id + " no longer exists.", enfe);
            }
            TipoPago idTipopago = pagoPeriodo.getIdTipopago();
            if (idTipopago != null) {
                idTipopago.getPagoPeriodoList().remove(pagoPeriodo);
                idTipopago = em.merge(idTipopago);
            }
            Periodo idPeriodo = pagoPeriodo.getIdPeriodo();
            if (idPeriodo != null) {
                idPeriodo.getPagoPeriodoList().remove(pagoPeriodo);
                idPeriodo = em.merge(idPeriodo);
            }
            em.remove(pagoPeriodo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PagoPeriodo> findPagoPeriodoEntities() {
        return findPagoPeriodoEntities(true, -1, -1);
    }

    public List<PagoPeriodo> findPagoPeriodoEntities(int maxResults, int firstResult) {
        return findPagoPeriodoEntities(false, maxResults, firstResult);
    }

    private List<PagoPeriodo> findPagoPeriodoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from PagoPeriodo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PagoPeriodo findPagoPeriodo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PagoPeriodo.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagoPeriodoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from PagoPeriodo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
