/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Evento;
import com.utez.integracion.entity.TipoEvento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoEventoJpaController implements Serializable {

    public TipoEventoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoEvento tipoEvento) {
        if (tipoEvento.getEventoList() == null) {
            tipoEvento.setEventoList(new ArrayList<Evento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Evento> attachedEventoList = new ArrayList<Evento>();
            for (Evento eventoListEventoToAttach : tipoEvento.getEventoList()) {
                eventoListEventoToAttach = em.getReference(eventoListEventoToAttach.getClass(), eventoListEventoToAttach.getIdEvento());
                attachedEventoList.add(eventoListEventoToAttach);
            }
            tipoEvento.setEventoList(attachedEventoList);
            em.persist(tipoEvento);
            for (Evento eventoListEvento : tipoEvento.getEventoList()) {
                TipoEvento oldIdTipoeventoOfEventoListEvento = eventoListEvento.getIdTipoevento();
                eventoListEvento.setIdTipoevento(tipoEvento);
                eventoListEvento = em.merge(eventoListEvento);
                if (oldIdTipoeventoOfEventoListEvento != null) {
                    oldIdTipoeventoOfEventoListEvento.getEventoList().remove(eventoListEvento);
                    oldIdTipoeventoOfEventoListEvento = em.merge(oldIdTipoeventoOfEventoListEvento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoEvento tipoEvento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoEvento persistentTipoEvento = em.find(TipoEvento.class, tipoEvento.getIdTipoevento());
            List<Evento> eventoListOld = persistentTipoEvento.getEventoList();
            List<Evento> eventoListNew = tipoEvento.getEventoList();
            List<Evento> attachedEventoListNew = new ArrayList<Evento>();
            for (Evento eventoListNewEventoToAttach : eventoListNew) {
                eventoListNewEventoToAttach = em.getReference(eventoListNewEventoToAttach.getClass(), eventoListNewEventoToAttach.getIdEvento());
                attachedEventoListNew.add(eventoListNewEventoToAttach);
            }
            eventoListNew = attachedEventoListNew;
            tipoEvento.setEventoList(eventoListNew);
            tipoEvento = em.merge(tipoEvento);
            for (Evento eventoListOldEvento : eventoListOld) {
                if (!eventoListNew.contains(eventoListOldEvento)) {
                    eventoListOldEvento.setIdTipoevento(null);
                    eventoListOldEvento = em.merge(eventoListOldEvento);
                }
            }
            for (Evento eventoListNewEvento : eventoListNew) {
                if (!eventoListOld.contains(eventoListNewEvento)) {
                    TipoEvento oldIdTipoeventoOfEventoListNewEvento = eventoListNewEvento.getIdTipoevento();
                    eventoListNewEvento.setIdTipoevento(tipoEvento);
                    eventoListNewEvento = em.merge(eventoListNewEvento);
                    if (oldIdTipoeventoOfEventoListNewEvento != null && !oldIdTipoeventoOfEventoListNewEvento.equals(tipoEvento)) {
                        oldIdTipoeventoOfEventoListNewEvento.getEventoList().remove(eventoListNewEvento);
                        oldIdTipoeventoOfEventoListNewEvento = em.merge(oldIdTipoeventoOfEventoListNewEvento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoEvento.getIdTipoevento();
                if (findTipoEvento(id) == null) {
                    throw new NonexistentEntityException("The tipoEvento with id " + id + " no longer exists.");
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
            TipoEvento tipoEvento;
            try {
                tipoEvento = em.getReference(TipoEvento.class, id);
                tipoEvento.getIdTipoevento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoEvento with id " + id + " no longer exists.", enfe);
            }
            List<Evento> eventoList = tipoEvento.getEventoList();
            for (Evento eventoListEvento : eventoList) {
                eventoListEvento.setIdTipoevento(null);
                eventoListEvento = em.merge(eventoListEvento);
            }
            em.remove(tipoEvento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoEvento> findTipoEventoEntities() {
        return findTipoEventoEntities(true, -1, -1);
    }

    public List<TipoEvento> findTipoEventoEntities(int maxResults, int firstResult) {
        return findTipoEventoEntities(false, maxResults, firstResult);
    }

    private List<TipoEvento> findTipoEventoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoEvento as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoEvento findTipoEvento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoEvento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoEventoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoEvento as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
