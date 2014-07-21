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
import com.utez.integracion.entity.TipoAmbito;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoAmbitoJpaController implements Serializable {

    public TipoAmbitoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoAmbito tipoAmbito) {
        if (tipoAmbito.getReactivoList() == null) {
            tipoAmbito.setReactivoList(new ArrayList<Reactivo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reactivo> attachedReactivoList = new ArrayList<Reactivo>();
            for (Reactivo reactivoListReactivoToAttach : tipoAmbito.getReactivoList()) {
                reactivoListReactivoToAttach = em.getReference(reactivoListReactivoToAttach.getClass(), reactivoListReactivoToAttach.getIdReactivo());
                attachedReactivoList.add(reactivoListReactivoToAttach);
            }
            tipoAmbito.setReactivoList(attachedReactivoList);
            em.persist(tipoAmbito);
            for (Reactivo reactivoListReactivo : tipoAmbito.getReactivoList()) {
                TipoAmbito oldIdTipoambitoOfReactivoListReactivo = reactivoListReactivo.getIdTipoambito();
                reactivoListReactivo.setIdTipoambito(tipoAmbito);
                reactivoListReactivo = em.merge(reactivoListReactivo);
                if (oldIdTipoambitoOfReactivoListReactivo != null) {
                    oldIdTipoambitoOfReactivoListReactivo.getReactivoList().remove(reactivoListReactivo);
                    oldIdTipoambitoOfReactivoListReactivo = em.merge(oldIdTipoambitoOfReactivoListReactivo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoAmbito tipoAmbito) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoAmbito persistentTipoAmbito = em.find(TipoAmbito.class, tipoAmbito.getIdTipoambito());
            List<Reactivo> reactivoListOld = persistentTipoAmbito.getReactivoList();
            List<Reactivo> reactivoListNew = tipoAmbito.getReactivoList();
            List<Reactivo> attachedReactivoListNew = new ArrayList<Reactivo>();
            for (Reactivo reactivoListNewReactivoToAttach : reactivoListNew) {
                reactivoListNewReactivoToAttach = em.getReference(reactivoListNewReactivoToAttach.getClass(), reactivoListNewReactivoToAttach.getIdReactivo());
                attachedReactivoListNew.add(reactivoListNewReactivoToAttach);
            }
            reactivoListNew = attachedReactivoListNew;
            tipoAmbito.setReactivoList(reactivoListNew);
            tipoAmbito = em.merge(tipoAmbito);
            for (Reactivo reactivoListOldReactivo : reactivoListOld) {
                if (!reactivoListNew.contains(reactivoListOldReactivo)) {
                    reactivoListOldReactivo.setIdTipoambito(null);
                    reactivoListOldReactivo = em.merge(reactivoListOldReactivo);
                }
            }
            for (Reactivo reactivoListNewReactivo : reactivoListNew) {
                if (!reactivoListOld.contains(reactivoListNewReactivo)) {
                    TipoAmbito oldIdTipoambitoOfReactivoListNewReactivo = reactivoListNewReactivo.getIdTipoambito();
                    reactivoListNewReactivo.setIdTipoambito(tipoAmbito);
                    reactivoListNewReactivo = em.merge(reactivoListNewReactivo);
                    if (oldIdTipoambitoOfReactivoListNewReactivo != null && !oldIdTipoambitoOfReactivoListNewReactivo.equals(tipoAmbito)) {
                        oldIdTipoambitoOfReactivoListNewReactivo.getReactivoList().remove(reactivoListNewReactivo);
                        oldIdTipoambitoOfReactivoListNewReactivo = em.merge(oldIdTipoambitoOfReactivoListNewReactivo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoAmbito.getIdTipoambito();
                if (findTipoAmbito(id) == null) {
                    throw new NonexistentEntityException("The tipoAmbito with id " + id + " no longer exists.");
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
            TipoAmbito tipoAmbito;
            try {
                tipoAmbito = em.getReference(TipoAmbito.class, id);
                tipoAmbito.getIdTipoambito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoAmbito with id " + id + " no longer exists.", enfe);
            }
            List<Reactivo> reactivoList = tipoAmbito.getReactivoList();
            for (Reactivo reactivoListReactivo : reactivoList) {
                reactivoListReactivo.setIdTipoambito(null);
                reactivoListReactivo = em.merge(reactivoListReactivo);
            }
            em.remove(tipoAmbito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoAmbito> findTipoAmbitoEntities() {
        return findTipoAmbitoEntities(true, -1, -1);
    }

    public List<TipoAmbito> findTipoAmbitoEntities(int maxResults, int firstResult) {
        return findTipoAmbitoEntities(false, maxResults, firstResult);
    }

    private List<TipoAmbito> findTipoAmbitoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoAmbito as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoAmbito findTipoAmbito(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoAmbito.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoAmbitoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoAmbito as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
