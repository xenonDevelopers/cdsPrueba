/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.DestinatarioNotificacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.RecursoHumano;
import com.utez.integracion.entity.NotificacionCalendario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class DestinatarioNotificacionJpaController implements Serializable {

    public DestinatarioNotificacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DestinatarioNotificacion destinatarioNotificacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RecursoHumano idRecursohumano = destinatarioNotificacion.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano = em.getReference(idRecursohumano.getClass(), idRecursohumano.getIdRecursohumano());
                destinatarioNotificacion.setIdRecursohumano(idRecursohumano);
            }
            NotificacionCalendario idNotificacioncalendario = destinatarioNotificacion.getIdNotificacioncalendario();
            if (idNotificacioncalendario != null) {
                idNotificacioncalendario = em.getReference(idNotificacioncalendario.getClass(), idNotificacioncalendario.getIdNotificacioncalendario());
                destinatarioNotificacion.setIdNotificacioncalendario(idNotificacioncalendario);
            }
            em.persist(destinatarioNotificacion);
            if (idRecursohumano != null) {
                idRecursohumano.getDestinatarioNotificacionList().add(destinatarioNotificacion);
                idRecursohumano = em.merge(idRecursohumano);
            }
            if (idNotificacioncalendario != null) {
                idNotificacioncalendario.getDestinatarioNotificacionList().add(destinatarioNotificacion);
                idNotificacioncalendario = em.merge(idNotificacioncalendario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DestinatarioNotificacion destinatarioNotificacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DestinatarioNotificacion persistentDestinatarioNotificacion = em.find(DestinatarioNotificacion.class, destinatarioNotificacion.getIdDestinatarionotificacion());
            RecursoHumano idRecursohumanoOld = persistentDestinatarioNotificacion.getIdRecursohumano();
            RecursoHumano idRecursohumanoNew = destinatarioNotificacion.getIdRecursohumano();
            NotificacionCalendario idNotificacioncalendarioOld = persistentDestinatarioNotificacion.getIdNotificacioncalendario();
            NotificacionCalendario idNotificacioncalendarioNew = destinatarioNotificacion.getIdNotificacioncalendario();
            if (idRecursohumanoNew != null) {
                idRecursohumanoNew = em.getReference(idRecursohumanoNew.getClass(), idRecursohumanoNew.getIdRecursohumano());
                destinatarioNotificacion.setIdRecursohumano(idRecursohumanoNew);
            }
            if (idNotificacioncalendarioNew != null) {
                idNotificacioncalendarioNew = em.getReference(idNotificacioncalendarioNew.getClass(), idNotificacioncalendarioNew.getIdNotificacioncalendario());
                destinatarioNotificacion.setIdNotificacioncalendario(idNotificacioncalendarioNew);
            }
            destinatarioNotificacion = em.merge(destinatarioNotificacion);
            if (idRecursohumanoOld != null && !idRecursohumanoOld.equals(idRecursohumanoNew)) {
                idRecursohumanoOld.getDestinatarioNotificacionList().remove(destinatarioNotificacion);
                idRecursohumanoOld = em.merge(idRecursohumanoOld);
            }
            if (idRecursohumanoNew != null && !idRecursohumanoNew.equals(idRecursohumanoOld)) {
                idRecursohumanoNew.getDestinatarioNotificacionList().add(destinatarioNotificacion);
                idRecursohumanoNew = em.merge(idRecursohumanoNew);
            }
            if (idNotificacioncalendarioOld != null && !idNotificacioncalendarioOld.equals(idNotificacioncalendarioNew)) {
                idNotificacioncalendarioOld.getDestinatarioNotificacionList().remove(destinatarioNotificacion);
                idNotificacioncalendarioOld = em.merge(idNotificacioncalendarioOld);
            }
            if (idNotificacioncalendarioNew != null && !idNotificacioncalendarioNew.equals(idNotificacioncalendarioOld)) {
                idNotificacioncalendarioNew.getDestinatarioNotificacionList().add(destinatarioNotificacion);
                idNotificacioncalendarioNew = em.merge(idNotificacioncalendarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = destinatarioNotificacion.getIdDestinatarionotificacion();
                if (findDestinatarioNotificacion(id) == null) {
                    throw new NonexistentEntityException("The destinatarioNotificacion with id " + id + " no longer exists.");
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
            DestinatarioNotificacion destinatarioNotificacion;
            try {
                destinatarioNotificacion = em.getReference(DestinatarioNotificacion.class, id);
                destinatarioNotificacion.getIdDestinatarionotificacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The destinatarioNotificacion with id " + id + " no longer exists.", enfe);
            }
            RecursoHumano idRecursohumano = destinatarioNotificacion.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano.getDestinatarioNotificacionList().remove(destinatarioNotificacion);
                idRecursohumano = em.merge(idRecursohumano);
            }
            NotificacionCalendario idNotificacioncalendario = destinatarioNotificacion.getIdNotificacioncalendario();
            if (idNotificacioncalendario != null) {
                idNotificacioncalendario.getDestinatarioNotificacionList().remove(destinatarioNotificacion);
                idNotificacioncalendario = em.merge(idNotificacioncalendario);
            }
            em.remove(destinatarioNotificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DestinatarioNotificacion> findDestinatarioNotificacionEntities() {
        return findDestinatarioNotificacionEntities(true, -1, -1);
    }

    public List<DestinatarioNotificacion> findDestinatarioNotificacionEntities(int maxResults, int firstResult) {
        return findDestinatarioNotificacionEntities(false, maxResults, firstResult);
    }

    private List<DestinatarioNotificacion> findDestinatarioNotificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from DestinatarioNotificacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public DestinatarioNotificacion findDestinatarioNotificacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DestinatarioNotificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDestinatarioNotificacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from DestinatarioNotificacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
