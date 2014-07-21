/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.BitacoraNotificacion;
import com.utez.integracion.entity.TipoNotificacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoNotificacionJpaController implements Serializable {

    public TipoNotificacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoNotificacion tipoNotificacion) {
        if (tipoNotificacion.getBitacoraNotificacionList() == null) {
            tipoNotificacion.setBitacoraNotificacionList(new ArrayList<BitacoraNotificacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<BitacoraNotificacion> attachedBitacoraNotificacionList = new ArrayList<BitacoraNotificacion>();
            for (BitacoraNotificacion bitacoraNotificacionListBitacoraNotificacionToAttach : tipoNotificacion.getBitacoraNotificacionList()) {
                bitacoraNotificacionListBitacoraNotificacionToAttach = em.getReference(bitacoraNotificacionListBitacoraNotificacionToAttach.getClass(), bitacoraNotificacionListBitacoraNotificacionToAttach.getIdBitacoranotificacion());
                attachedBitacoraNotificacionList.add(bitacoraNotificacionListBitacoraNotificacionToAttach);
            }
            tipoNotificacion.setBitacoraNotificacionList(attachedBitacoraNotificacionList);
            em.persist(tipoNotificacion);
            for (BitacoraNotificacion bitacoraNotificacionListBitacoraNotificacion : tipoNotificacion.getBitacoraNotificacionList()) {
                TipoNotificacion oldIdTiponotificacionOfBitacoraNotificacionListBitacoraNotificacion = bitacoraNotificacionListBitacoraNotificacion.getIdTiponotificacion();
                bitacoraNotificacionListBitacoraNotificacion.setIdTiponotificacion(tipoNotificacion);
                bitacoraNotificacionListBitacoraNotificacion = em.merge(bitacoraNotificacionListBitacoraNotificacion);
                if (oldIdTiponotificacionOfBitacoraNotificacionListBitacoraNotificacion != null) {
                    oldIdTiponotificacionOfBitacoraNotificacionListBitacoraNotificacion.getBitacoraNotificacionList().remove(bitacoraNotificacionListBitacoraNotificacion);
                    oldIdTiponotificacionOfBitacoraNotificacionListBitacoraNotificacion = em.merge(oldIdTiponotificacionOfBitacoraNotificacionListBitacoraNotificacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoNotificacion tipoNotificacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoNotificacion persistentTipoNotificacion = em.find(TipoNotificacion.class, tipoNotificacion.getIdTiponotificacion());
            List<BitacoraNotificacion> bitacoraNotificacionListOld = persistentTipoNotificacion.getBitacoraNotificacionList();
            List<BitacoraNotificacion> bitacoraNotificacionListNew = tipoNotificacion.getBitacoraNotificacionList();
            List<BitacoraNotificacion> attachedBitacoraNotificacionListNew = new ArrayList<BitacoraNotificacion>();
            for (BitacoraNotificacion bitacoraNotificacionListNewBitacoraNotificacionToAttach : bitacoraNotificacionListNew) {
                bitacoraNotificacionListNewBitacoraNotificacionToAttach = em.getReference(bitacoraNotificacionListNewBitacoraNotificacionToAttach.getClass(), bitacoraNotificacionListNewBitacoraNotificacionToAttach.getIdBitacoranotificacion());
                attachedBitacoraNotificacionListNew.add(bitacoraNotificacionListNewBitacoraNotificacionToAttach);
            }
            bitacoraNotificacionListNew = attachedBitacoraNotificacionListNew;
            tipoNotificacion.setBitacoraNotificacionList(bitacoraNotificacionListNew);
            tipoNotificacion = em.merge(tipoNotificacion);
            for (BitacoraNotificacion bitacoraNotificacionListOldBitacoraNotificacion : bitacoraNotificacionListOld) {
                if (!bitacoraNotificacionListNew.contains(bitacoraNotificacionListOldBitacoraNotificacion)) {
                    bitacoraNotificacionListOldBitacoraNotificacion.setIdTiponotificacion(null);
                    bitacoraNotificacionListOldBitacoraNotificacion = em.merge(bitacoraNotificacionListOldBitacoraNotificacion);
                }
            }
            for (BitacoraNotificacion bitacoraNotificacionListNewBitacoraNotificacion : bitacoraNotificacionListNew) {
                if (!bitacoraNotificacionListOld.contains(bitacoraNotificacionListNewBitacoraNotificacion)) {
                    TipoNotificacion oldIdTiponotificacionOfBitacoraNotificacionListNewBitacoraNotificacion = bitacoraNotificacionListNewBitacoraNotificacion.getIdTiponotificacion();
                    bitacoraNotificacionListNewBitacoraNotificacion.setIdTiponotificacion(tipoNotificacion);
                    bitacoraNotificacionListNewBitacoraNotificacion = em.merge(bitacoraNotificacionListNewBitacoraNotificacion);
                    if (oldIdTiponotificacionOfBitacoraNotificacionListNewBitacoraNotificacion != null && !oldIdTiponotificacionOfBitacoraNotificacionListNewBitacoraNotificacion.equals(tipoNotificacion)) {
                        oldIdTiponotificacionOfBitacoraNotificacionListNewBitacoraNotificacion.getBitacoraNotificacionList().remove(bitacoraNotificacionListNewBitacoraNotificacion);
                        oldIdTiponotificacionOfBitacoraNotificacionListNewBitacoraNotificacion = em.merge(oldIdTiponotificacionOfBitacoraNotificacionListNewBitacoraNotificacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoNotificacion.getIdTiponotificacion();
                if (findTipoNotificacion(id) == null) {
                    throw new NonexistentEntityException("The tipoNotificacion with id " + id + " no longer exists.");
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
            TipoNotificacion tipoNotificacion;
            try {
                tipoNotificacion = em.getReference(TipoNotificacion.class, id);
                tipoNotificacion.getIdTiponotificacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoNotificacion with id " + id + " no longer exists.", enfe);
            }
            List<BitacoraNotificacion> bitacoraNotificacionList = tipoNotificacion.getBitacoraNotificacionList();
            for (BitacoraNotificacion bitacoraNotificacionListBitacoraNotificacion : bitacoraNotificacionList) {
                bitacoraNotificacionListBitacoraNotificacion.setIdTiponotificacion(null);
                bitacoraNotificacionListBitacoraNotificacion = em.merge(bitacoraNotificacionListBitacoraNotificacion);
            }
            em.remove(tipoNotificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoNotificacion> findTipoNotificacionEntities() {
        return findTipoNotificacionEntities(true, -1, -1);
    }

    public List<TipoNotificacion> findTipoNotificacionEntities(int maxResults, int firstResult) {
        return findTipoNotificacionEntities(false, maxResults, firstResult);
    }

    private List<TipoNotificacion> findTipoNotificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoNotificacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoNotificacion findTipoNotificacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoNotificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoNotificacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoNotificacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
