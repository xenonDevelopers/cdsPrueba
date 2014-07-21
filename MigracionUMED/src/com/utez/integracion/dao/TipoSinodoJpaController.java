/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.SinodoExamen;
import com.utez.integracion.entity.TipoSinodo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoSinodoJpaController implements Serializable {

    public TipoSinodoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoSinodo tipoSinodo) {
        if (tipoSinodo.getSinodoExamenList() == null) {
            tipoSinodo.setSinodoExamenList(new ArrayList<SinodoExamen>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SinodoExamen> attachedSinodoExamenList = new ArrayList<SinodoExamen>();
            for (SinodoExamen sinodoExamenListSinodoExamenToAttach : tipoSinodo.getSinodoExamenList()) {
                sinodoExamenListSinodoExamenToAttach = em.getReference(sinodoExamenListSinodoExamenToAttach.getClass(), sinodoExamenListSinodoExamenToAttach.getIdSinodo());
                attachedSinodoExamenList.add(sinodoExamenListSinodoExamenToAttach);
            }
            tipoSinodo.setSinodoExamenList(attachedSinodoExamenList);
            em.persist(tipoSinodo);
            for (SinodoExamen sinodoExamenListSinodoExamen : tipoSinodo.getSinodoExamenList()) {
                TipoSinodo oldIdTiposinodoOfSinodoExamenListSinodoExamen = sinodoExamenListSinodoExamen.getIdTiposinodo();
                sinodoExamenListSinodoExamen.setIdTiposinodo(tipoSinodo);
                sinodoExamenListSinodoExamen = em.merge(sinodoExamenListSinodoExamen);
                if (oldIdTiposinodoOfSinodoExamenListSinodoExamen != null) {
                    oldIdTiposinodoOfSinodoExamenListSinodoExamen.getSinodoExamenList().remove(sinodoExamenListSinodoExamen);
                    oldIdTiposinodoOfSinodoExamenListSinodoExamen = em.merge(oldIdTiposinodoOfSinodoExamenListSinodoExamen);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoSinodo tipoSinodo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoSinodo persistentTipoSinodo = em.find(TipoSinodo.class, tipoSinodo.getIdTiposinodo());
            List<SinodoExamen> sinodoExamenListOld = persistentTipoSinodo.getSinodoExamenList();
            List<SinodoExamen> sinodoExamenListNew = tipoSinodo.getSinodoExamenList();
            List<SinodoExamen> attachedSinodoExamenListNew = new ArrayList<SinodoExamen>();
            for (SinodoExamen sinodoExamenListNewSinodoExamenToAttach : sinodoExamenListNew) {
                sinodoExamenListNewSinodoExamenToAttach = em.getReference(sinodoExamenListNewSinodoExamenToAttach.getClass(), sinodoExamenListNewSinodoExamenToAttach.getIdSinodo());
                attachedSinodoExamenListNew.add(sinodoExamenListNewSinodoExamenToAttach);
            }
            sinodoExamenListNew = attachedSinodoExamenListNew;
            tipoSinodo.setSinodoExamenList(sinodoExamenListNew);
            tipoSinodo = em.merge(tipoSinodo);
            for (SinodoExamen sinodoExamenListOldSinodoExamen : sinodoExamenListOld) {
                if (!sinodoExamenListNew.contains(sinodoExamenListOldSinodoExamen)) {
                    sinodoExamenListOldSinodoExamen.setIdTiposinodo(null);
                    sinodoExamenListOldSinodoExamen = em.merge(sinodoExamenListOldSinodoExamen);
                }
            }
            for (SinodoExamen sinodoExamenListNewSinodoExamen : sinodoExamenListNew) {
                if (!sinodoExamenListOld.contains(sinodoExamenListNewSinodoExamen)) {
                    TipoSinodo oldIdTiposinodoOfSinodoExamenListNewSinodoExamen = sinodoExamenListNewSinodoExamen.getIdTiposinodo();
                    sinodoExamenListNewSinodoExamen.setIdTiposinodo(tipoSinodo);
                    sinodoExamenListNewSinodoExamen = em.merge(sinodoExamenListNewSinodoExamen);
                    if (oldIdTiposinodoOfSinodoExamenListNewSinodoExamen != null && !oldIdTiposinodoOfSinodoExamenListNewSinodoExamen.equals(tipoSinodo)) {
                        oldIdTiposinodoOfSinodoExamenListNewSinodoExamen.getSinodoExamenList().remove(sinodoExamenListNewSinodoExamen);
                        oldIdTiposinodoOfSinodoExamenListNewSinodoExamen = em.merge(oldIdTiposinodoOfSinodoExamenListNewSinodoExamen);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoSinodo.getIdTiposinodo();
                if (findTipoSinodo(id) == null) {
                    throw new NonexistentEntityException("The tipoSinodo with id " + id + " no longer exists.");
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
            TipoSinodo tipoSinodo;
            try {
                tipoSinodo = em.getReference(TipoSinodo.class, id);
                tipoSinodo.getIdTiposinodo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoSinodo with id " + id + " no longer exists.", enfe);
            }
            List<SinodoExamen> sinodoExamenList = tipoSinodo.getSinodoExamenList();
            for (SinodoExamen sinodoExamenListSinodoExamen : sinodoExamenList) {
                sinodoExamenListSinodoExamen.setIdTiposinodo(null);
                sinodoExamenListSinodoExamen = em.merge(sinodoExamenListSinodoExamen);
            }
            em.remove(tipoSinodo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoSinodo> findTipoSinodoEntities() {
        return findTipoSinodoEntities(true, -1, -1);
    }

    public List<TipoSinodo> findTipoSinodoEntities(int maxResults, int firstResult) {
        return findTipoSinodoEntities(false, maxResults, firstResult);
    }

    private List<TipoSinodo> findTipoSinodoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoSinodo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoSinodo findTipoSinodo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoSinodo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoSinodoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoSinodo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
