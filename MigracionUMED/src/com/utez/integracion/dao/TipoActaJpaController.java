/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Acta;
import com.utez.integracion.entity.TipoActa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoActaJpaController implements Serializable {

    public TipoActaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoActa tipoActa) {
        if (tipoActa.getActaList() == null) {
            tipoActa.setActaList(new ArrayList<Acta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Acta> attachedActaList = new ArrayList<Acta>();
            for (Acta actaListActaToAttach : tipoActa.getActaList()) {
                actaListActaToAttach = em.getReference(actaListActaToAttach.getClass(), actaListActaToAttach.getIdActa());
                attachedActaList.add(actaListActaToAttach);
            }
            tipoActa.setActaList(attachedActaList);
            em.persist(tipoActa);
            for (Acta actaListActa : tipoActa.getActaList()) {
                TipoActa oldIdTipoactaOfActaListActa = actaListActa.getIdTipoacta();
                actaListActa.setIdTipoacta(tipoActa);
                actaListActa = em.merge(actaListActa);
                if (oldIdTipoactaOfActaListActa != null) {
                    oldIdTipoactaOfActaListActa.getActaList().remove(actaListActa);
                    oldIdTipoactaOfActaListActa = em.merge(oldIdTipoactaOfActaListActa);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoActa tipoActa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoActa persistentTipoActa = em.find(TipoActa.class, tipoActa.getIdTipoacta());
            List<Acta> actaListOld = persistentTipoActa.getActaList();
            List<Acta> actaListNew = tipoActa.getActaList();
            List<Acta> attachedActaListNew = new ArrayList<Acta>();
            for (Acta actaListNewActaToAttach : actaListNew) {
                actaListNewActaToAttach = em.getReference(actaListNewActaToAttach.getClass(), actaListNewActaToAttach.getIdActa());
                attachedActaListNew.add(actaListNewActaToAttach);
            }
            actaListNew = attachedActaListNew;
            tipoActa.setActaList(actaListNew);
            tipoActa = em.merge(tipoActa);
            for (Acta actaListOldActa : actaListOld) {
                if (!actaListNew.contains(actaListOldActa)) {
                    actaListOldActa.setIdTipoacta(null);
                    actaListOldActa = em.merge(actaListOldActa);
                }
            }
            for (Acta actaListNewActa : actaListNew) {
                if (!actaListOld.contains(actaListNewActa)) {
                    TipoActa oldIdTipoactaOfActaListNewActa = actaListNewActa.getIdTipoacta();
                    actaListNewActa.setIdTipoacta(tipoActa);
                    actaListNewActa = em.merge(actaListNewActa);
                    if (oldIdTipoactaOfActaListNewActa != null && !oldIdTipoactaOfActaListNewActa.equals(tipoActa)) {
                        oldIdTipoactaOfActaListNewActa.getActaList().remove(actaListNewActa);
                        oldIdTipoactaOfActaListNewActa = em.merge(oldIdTipoactaOfActaListNewActa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoActa.getIdTipoacta();
                if (findTipoActa(id) == null) {
                    throw new NonexistentEntityException("The tipoActa with id " + id + " no longer exists.");
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
            TipoActa tipoActa;
            try {
                tipoActa = em.getReference(TipoActa.class, id);
                tipoActa.getIdTipoacta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoActa with id " + id + " no longer exists.", enfe);
            }
            List<Acta> actaList = tipoActa.getActaList();
            for (Acta actaListActa : actaList) {
                actaListActa.setIdTipoacta(null);
                actaListActa = em.merge(actaListActa);
            }
            em.remove(tipoActa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoActa> findTipoActaEntities() {
        return findTipoActaEntities(true, -1, -1);
    }

    public List<TipoActa> findTipoActaEntities(int maxResults, int firstResult) {
        return findTipoActaEntities(false, maxResults, firstResult);
    }

    private List<TipoActa> findTipoActaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoActa as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoActa findTipoActa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoActa.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoActaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoActa as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
