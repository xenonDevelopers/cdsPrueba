/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secretariaAcademica.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Movimientocie;
import com.utez.secretariaAcademica.entity.Tipopagos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipopagosJpaController implements Serializable {

    public TipopagosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipopagos tipopagos) throws PreexistingEntityException, Exception {
        if (tipopagos.getMovimientocieList() == null) {
            tipopagos.setMovimientocieList(new ArrayList<Movimientocie>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Movimientocie> attachedMovimientocieList = new ArrayList<Movimientocie>();
            for (Movimientocie movimientocieListMovimientocieToAttach : tipopagos.getMovimientocieList()) {
                movimientocieListMovimientocieToAttach = em.getReference(movimientocieListMovimientocieToAttach.getClass(), movimientocieListMovimientocieToAttach.getIdmovimientocie());
                attachedMovimientocieList.add(movimientocieListMovimientocieToAttach);
            }
            tipopagos.setMovimientocieList(attachedMovimientocieList);
            em.persist(tipopagos);
            for (Movimientocie movimientocieListMovimientocie : tipopagos.getMovimientocieList()) {
                Tipopagos oldIdtipopagosOfMovimientocieListMovimientocie = movimientocieListMovimientocie.getIdtipopagos();
                movimientocieListMovimientocie.setIdtipopagos(tipopagos);
                movimientocieListMovimientocie = em.merge(movimientocieListMovimientocie);
                if (oldIdtipopagosOfMovimientocieListMovimientocie != null) {
                    oldIdtipopagosOfMovimientocieListMovimientocie.getMovimientocieList().remove(movimientocieListMovimientocie);
                    oldIdtipopagosOfMovimientocieListMovimientocie = em.merge(oldIdtipopagosOfMovimientocieListMovimientocie);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipopagos(tipopagos.getIdtipopagos()) != null) {
                throw new PreexistingEntityException("Tipopagos " + tipopagos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipopagos tipopagos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipopagos persistentTipopagos = em.find(Tipopagos.class, tipopagos.getIdtipopagos());
            List<Movimientocie> movimientocieListOld = persistentTipopagos.getMovimientocieList();
            List<Movimientocie> movimientocieListNew = tipopagos.getMovimientocieList();
            List<Movimientocie> attachedMovimientocieListNew = new ArrayList<Movimientocie>();
            for (Movimientocie movimientocieListNewMovimientocieToAttach : movimientocieListNew) {
                movimientocieListNewMovimientocieToAttach = em.getReference(movimientocieListNewMovimientocieToAttach.getClass(), movimientocieListNewMovimientocieToAttach.getIdmovimientocie());
                attachedMovimientocieListNew.add(movimientocieListNewMovimientocieToAttach);
            }
            movimientocieListNew = attachedMovimientocieListNew;
            tipopagos.setMovimientocieList(movimientocieListNew);
            tipopagos = em.merge(tipopagos);
            for (Movimientocie movimientocieListOldMovimientocie : movimientocieListOld) {
                if (!movimientocieListNew.contains(movimientocieListOldMovimientocie)) {
                    movimientocieListOldMovimientocie.setIdtipopagos(null);
                    movimientocieListOldMovimientocie = em.merge(movimientocieListOldMovimientocie);
                }
            }
            for (Movimientocie movimientocieListNewMovimientocie : movimientocieListNew) {
                if (!movimientocieListOld.contains(movimientocieListNewMovimientocie)) {
                    Tipopagos oldIdtipopagosOfMovimientocieListNewMovimientocie = movimientocieListNewMovimientocie.getIdtipopagos();
                    movimientocieListNewMovimientocie.setIdtipopagos(tipopagos);
                    movimientocieListNewMovimientocie = em.merge(movimientocieListNewMovimientocie);
                    if (oldIdtipopagosOfMovimientocieListNewMovimientocie != null && !oldIdtipopagosOfMovimientocieListNewMovimientocie.equals(tipopagos)) {
                        oldIdtipopagosOfMovimientocieListNewMovimientocie.getMovimientocieList().remove(movimientocieListNewMovimientocie);
                        oldIdtipopagosOfMovimientocieListNewMovimientocie = em.merge(oldIdtipopagosOfMovimientocieListNewMovimientocie);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipopagos.getIdtipopagos();
                if (findTipopagos(id) == null) {
                    throw new NonexistentEntityException("The tipopagos with id " + id + " no longer exists.");
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
            Tipopagos tipopagos;
            try {
                tipopagos = em.getReference(Tipopagos.class, id);
                tipopagos.getIdtipopagos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipopagos with id " + id + " no longer exists.", enfe);
            }
            List<Movimientocie> movimientocieList = tipopagos.getMovimientocieList();
            for (Movimientocie movimientocieListMovimientocie : movimientocieList) {
                movimientocieListMovimientocie.setIdtipopagos(null);
                movimientocieListMovimientocie = em.merge(movimientocieListMovimientocie);
            }
            em.remove(tipopagos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipopagos> findTipopagosEntities() {
        return findTipopagosEntities(true, -1, -1);
    }

    public List<Tipopagos> findTipopagosEntities(int maxResults, int firstResult) {
        return findTipopagosEntities(false, maxResults, firstResult);
    }

    private List<Tipopagos> findTipopagosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Tipopagos as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tipopagos findTipopagos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipopagos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipopagosCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Tipopagos as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
