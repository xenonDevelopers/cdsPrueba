/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.DestinatarioNotificacion;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.BitacoraNotificacion;
import com.utez.integracion.entity.NotificacionCalendario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class NotificacionCalendarioJpaController implements Serializable {

    public NotificacionCalendarioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NotificacionCalendario notificacionCalendario) {
        if (notificacionCalendario.getDestinatarioNotificacionList() == null) {
            notificacionCalendario.setDestinatarioNotificacionList(new ArrayList<DestinatarioNotificacion>());
        }
        if (notificacionCalendario.getBitacoraNotificacionList() == null) {
            notificacionCalendario.setBitacoraNotificacionList(new ArrayList<BitacoraNotificacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<DestinatarioNotificacion> attachedDestinatarioNotificacionList = new ArrayList<DestinatarioNotificacion>();
            for (DestinatarioNotificacion destinatarioNotificacionListDestinatarioNotificacionToAttach : notificacionCalendario.getDestinatarioNotificacionList()) {
                destinatarioNotificacionListDestinatarioNotificacionToAttach = em.getReference(destinatarioNotificacionListDestinatarioNotificacionToAttach.getClass(), destinatarioNotificacionListDestinatarioNotificacionToAttach.getIdDestinatarionotificacion());
                attachedDestinatarioNotificacionList.add(destinatarioNotificacionListDestinatarioNotificacionToAttach);
            }
            notificacionCalendario.setDestinatarioNotificacionList(attachedDestinatarioNotificacionList);
            List<BitacoraNotificacion> attachedBitacoraNotificacionList = new ArrayList<BitacoraNotificacion>();
            for (BitacoraNotificacion bitacoraNotificacionListBitacoraNotificacionToAttach : notificacionCalendario.getBitacoraNotificacionList()) {
                bitacoraNotificacionListBitacoraNotificacionToAttach = em.getReference(bitacoraNotificacionListBitacoraNotificacionToAttach.getClass(), bitacoraNotificacionListBitacoraNotificacionToAttach.getIdBitacoranotificacion());
                attachedBitacoraNotificacionList.add(bitacoraNotificacionListBitacoraNotificacionToAttach);
            }
            notificacionCalendario.setBitacoraNotificacionList(attachedBitacoraNotificacionList);
            em.persist(notificacionCalendario);
            for (DestinatarioNotificacion destinatarioNotificacionListDestinatarioNotificacion : notificacionCalendario.getDestinatarioNotificacionList()) {
                NotificacionCalendario oldIdNotificacioncalendarioOfDestinatarioNotificacionListDestinatarioNotificacion = destinatarioNotificacionListDestinatarioNotificacion.getIdNotificacioncalendario();
                destinatarioNotificacionListDestinatarioNotificacion.setIdNotificacioncalendario(notificacionCalendario);
                destinatarioNotificacionListDestinatarioNotificacion = em.merge(destinatarioNotificacionListDestinatarioNotificacion);
                if (oldIdNotificacioncalendarioOfDestinatarioNotificacionListDestinatarioNotificacion != null) {
                    oldIdNotificacioncalendarioOfDestinatarioNotificacionListDestinatarioNotificacion.getDestinatarioNotificacionList().remove(destinatarioNotificacionListDestinatarioNotificacion);
                    oldIdNotificacioncalendarioOfDestinatarioNotificacionListDestinatarioNotificacion = em.merge(oldIdNotificacioncalendarioOfDestinatarioNotificacionListDestinatarioNotificacion);
                }
            }
            for (BitacoraNotificacion bitacoraNotificacionListBitacoraNotificacion : notificacionCalendario.getBitacoraNotificacionList()) {
                NotificacionCalendario oldIdNotificacioncalendarioOfBitacoraNotificacionListBitacoraNotificacion = bitacoraNotificacionListBitacoraNotificacion.getIdNotificacioncalendario();
                bitacoraNotificacionListBitacoraNotificacion.setIdNotificacioncalendario(notificacionCalendario);
                bitacoraNotificacionListBitacoraNotificacion = em.merge(bitacoraNotificacionListBitacoraNotificacion);
                if (oldIdNotificacioncalendarioOfBitacoraNotificacionListBitacoraNotificacion != null) {
                    oldIdNotificacioncalendarioOfBitacoraNotificacionListBitacoraNotificacion.getBitacoraNotificacionList().remove(bitacoraNotificacionListBitacoraNotificacion);
                    oldIdNotificacioncalendarioOfBitacoraNotificacionListBitacoraNotificacion = em.merge(oldIdNotificacioncalendarioOfBitacoraNotificacionListBitacoraNotificacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NotificacionCalendario notificacionCalendario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NotificacionCalendario persistentNotificacionCalendario = em.find(NotificacionCalendario.class, notificacionCalendario.getIdNotificacioncalendario());
            List<DestinatarioNotificacion> destinatarioNotificacionListOld = persistentNotificacionCalendario.getDestinatarioNotificacionList();
            List<DestinatarioNotificacion> destinatarioNotificacionListNew = notificacionCalendario.getDestinatarioNotificacionList();
            List<BitacoraNotificacion> bitacoraNotificacionListOld = persistentNotificacionCalendario.getBitacoraNotificacionList();
            List<BitacoraNotificacion> bitacoraNotificacionListNew = notificacionCalendario.getBitacoraNotificacionList();
            List<DestinatarioNotificacion> attachedDestinatarioNotificacionListNew = new ArrayList<DestinatarioNotificacion>();
            for (DestinatarioNotificacion destinatarioNotificacionListNewDestinatarioNotificacionToAttach : destinatarioNotificacionListNew) {
                destinatarioNotificacionListNewDestinatarioNotificacionToAttach = em.getReference(destinatarioNotificacionListNewDestinatarioNotificacionToAttach.getClass(), destinatarioNotificacionListNewDestinatarioNotificacionToAttach.getIdDestinatarionotificacion());
                attachedDestinatarioNotificacionListNew.add(destinatarioNotificacionListNewDestinatarioNotificacionToAttach);
            }
            destinatarioNotificacionListNew = attachedDestinatarioNotificacionListNew;
            notificacionCalendario.setDestinatarioNotificacionList(destinatarioNotificacionListNew);
            List<BitacoraNotificacion> attachedBitacoraNotificacionListNew = new ArrayList<BitacoraNotificacion>();
            for (BitacoraNotificacion bitacoraNotificacionListNewBitacoraNotificacionToAttach : bitacoraNotificacionListNew) {
                bitacoraNotificacionListNewBitacoraNotificacionToAttach = em.getReference(bitacoraNotificacionListNewBitacoraNotificacionToAttach.getClass(), bitacoraNotificacionListNewBitacoraNotificacionToAttach.getIdBitacoranotificacion());
                attachedBitacoraNotificacionListNew.add(bitacoraNotificacionListNewBitacoraNotificacionToAttach);
            }
            bitacoraNotificacionListNew = attachedBitacoraNotificacionListNew;
            notificacionCalendario.setBitacoraNotificacionList(bitacoraNotificacionListNew);
            notificacionCalendario = em.merge(notificacionCalendario);
            for (DestinatarioNotificacion destinatarioNotificacionListOldDestinatarioNotificacion : destinatarioNotificacionListOld) {
                if (!destinatarioNotificacionListNew.contains(destinatarioNotificacionListOldDestinatarioNotificacion)) {
                    destinatarioNotificacionListOldDestinatarioNotificacion.setIdNotificacioncalendario(null);
                    destinatarioNotificacionListOldDestinatarioNotificacion = em.merge(destinatarioNotificacionListOldDestinatarioNotificacion);
                }
            }
            for (DestinatarioNotificacion destinatarioNotificacionListNewDestinatarioNotificacion : destinatarioNotificacionListNew) {
                if (!destinatarioNotificacionListOld.contains(destinatarioNotificacionListNewDestinatarioNotificacion)) {
                    NotificacionCalendario oldIdNotificacioncalendarioOfDestinatarioNotificacionListNewDestinatarioNotificacion = destinatarioNotificacionListNewDestinatarioNotificacion.getIdNotificacioncalendario();
                    destinatarioNotificacionListNewDestinatarioNotificacion.setIdNotificacioncalendario(notificacionCalendario);
                    destinatarioNotificacionListNewDestinatarioNotificacion = em.merge(destinatarioNotificacionListNewDestinatarioNotificacion);
                    if (oldIdNotificacioncalendarioOfDestinatarioNotificacionListNewDestinatarioNotificacion != null && !oldIdNotificacioncalendarioOfDestinatarioNotificacionListNewDestinatarioNotificacion.equals(notificacionCalendario)) {
                        oldIdNotificacioncalendarioOfDestinatarioNotificacionListNewDestinatarioNotificacion.getDestinatarioNotificacionList().remove(destinatarioNotificacionListNewDestinatarioNotificacion);
                        oldIdNotificacioncalendarioOfDestinatarioNotificacionListNewDestinatarioNotificacion = em.merge(oldIdNotificacioncalendarioOfDestinatarioNotificacionListNewDestinatarioNotificacion);
                    }
                }
            }
            for (BitacoraNotificacion bitacoraNotificacionListOldBitacoraNotificacion : bitacoraNotificacionListOld) {
                if (!bitacoraNotificacionListNew.contains(bitacoraNotificacionListOldBitacoraNotificacion)) {
                    bitacoraNotificacionListOldBitacoraNotificacion.setIdNotificacioncalendario(null);
                    bitacoraNotificacionListOldBitacoraNotificacion = em.merge(bitacoraNotificacionListOldBitacoraNotificacion);
                }
            }
            for (BitacoraNotificacion bitacoraNotificacionListNewBitacoraNotificacion : bitacoraNotificacionListNew) {
                if (!bitacoraNotificacionListOld.contains(bitacoraNotificacionListNewBitacoraNotificacion)) {
                    NotificacionCalendario oldIdNotificacioncalendarioOfBitacoraNotificacionListNewBitacoraNotificacion = bitacoraNotificacionListNewBitacoraNotificacion.getIdNotificacioncalendario();
                    bitacoraNotificacionListNewBitacoraNotificacion.setIdNotificacioncalendario(notificacionCalendario);
                    bitacoraNotificacionListNewBitacoraNotificacion = em.merge(bitacoraNotificacionListNewBitacoraNotificacion);
                    if (oldIdNotificacioncalendarioOfBitacoraNotificacionListNewBitacoraNotificacion != null && !oldIdNotificacioncalendarioOfBitacoraNotificacionListNewBitacoraNotificacion.equals(notificacionCalendario)) {
                        oldIdNotificacioncalendarioOfBitacoraNotificacionListNewBitacoraNotificacion.getBitacoraNotificacionList().remove(bitacoraNotificacionListNewBitacoraNotificacion);
                        oldIdNotificacioncalendarioOfBitacoraNotificacionListNewBitacoraNotificacion = em.merge(oldIdNotificacioncalendarioOfBitacoraNotificacionListNewBitacoraNotificacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = notificacionCalendario.getIdNotificacioncalendario();
                if (findNotificacionCalendario(id) == null) {
                    throw new NonexistentEntityException("The notificacionCalendario with id " + id + " no longer exists.");
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
            NotificacionCalendario notificacionCalendario;
            try {
                notificacionCalendario = em.getReference(NotificacionCalendario.class, id);
                notificacionCalendario.getIdNotificacioncalendario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notificacionCalendario with id " + id + " no longer exists.", enfe);
            }
            List<DestinatarioNotificacion> destinatarioNotificacionList = notificacionCalendario.getDestinatarioNotificacionList();
            for (DestinatarioNotificacion destinatarioNotificacionListDestinatarioNotificacion : destinatarioNotificacionList) {
                destinatarioNotificacionListDestinatarioNotificacion.setIdNotificacioncalendario(null);
                destinatarioNotificacionListDestinatarioNotificacion = em.merge(destinatarioNotificacionListDestinatarioNotificacion);
            }
            List<BitacoraNotificacion> bitacoraNotificacionList = notificacionCalendario.getBitacoraNotificacionList();
            for (BitacoraNotificacion bitacoraNotificacionListBitacoraNotificacion : bitacoraNotificacionList) {
                bitacoraNotificacionListBitacoraNotificacion.setIdNotificacioncalendario(null);
                bitacoraNotificacionListBitacoraNotificacion = em.merge(bitacoraNotificacionListBitacoraNotificacion);
            }
            em.remove(notificacionCalendario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NotificacionCalendario> findNotificacionCalendarioEntities() {
        return findNotificacionCalendarioEntities(true, -1, -1);
    }

    public List<NotificacionCalendario> findNotificacionCalendarioEntities(int maxResults, int firstResult) {
        return findNotificacionCalendarioEntities(false, maxResults, firstResult);
    }

    private List<NotificacionCalendario> findNotificacionCalendarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from NotificacionCalendario as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public NotificacionCalendario findNotificacionCalendario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NotificacionCalendario.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotificacionCalendarioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from NotificacionCalendario as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
