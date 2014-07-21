/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.BitacoraNotificacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoNotificacion;
import com.utez.integracion.entity.NotificacionCalendario;
import com.utez.secretariaAcademica.entity.Grupo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class BitacoraNotificacionJpaController implements Serializable {

    public BitacoraNotificacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BitacoraNotificacion bitacoraNotificacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoNotificacion idTiponotificacion = bitacoraNotificacion.getIdTiponotificacion();
            if (idTiponotificacion != null) {
                idTiponotificacion = em.getReference(idTiponotificacion.getClass(), idTiponotificacion.getIdTiponotificacion());
                bitacoraNotificacion.setIdTiponotificacion(idTiponotificacion);
            }
            NotificacionCalendario idNotificacioncalendario = bitacoraNotificacion.getIdNotificacioncalendario();
            if (idNotificacioncalendario != null) {
                idNotificacioncalendario = em.getReference(idNotificacioncalendario.getClass(), idNotificacioncalendario.getIdNotificacioncalendario());
                bitacoraNotificacion.setIdNotificacioncalendario(idNotificacioncalendario);
            }
            Grupo idGrupo = bitacoraNotificacion.getIdGrupo();
            if (idGrupo != null) {
                idGrupo = em.getReference(idGrupo.getClass(), idGrupo.getIdgrupo());
                bitacoraNotificacion.setIdGrupo(idGrupo);
            }
            em.persist(bitacoraNotificacion);
            if (idTiponotificacion != null) {
                idTiponotificacion.getBitacoraNotificacionList().add(bitacoraNotificacion);
                idTiponotificacion = em.merge(idTiponotificacion);
            }
            if (idNotificacioncalendario != null) {
                idNotificacioncalendario.getBitacoraNotificacionList().add(bitacoraNotificacion);
                idNotificacioncalendario = em.merge(idNotificacioncalendario);
            }
            if (idGrupo != null) {
                idGrupo.getBitacoraNotificacionList().add(bitacoraNotificacion);
                idGrupo = em.merge(idGrupo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BitacoraNotificacion bitacoraNotificacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BitacoraNotificacion persistentBitacoraNotificacion = em.find(BitacoraNotificacion.class, bitacoraNotificacion.getIdBitacoranotificacion());
            TipoNotificacion idTiponotificacionOld = persistentBitacoraNotificacion.getIdTiponotificacion();
            TipoNotificacion idTiponotificacionNew = bitacoraNotificacion.getIdTiponotificacion();
            NotificacionCalendario idNotificacioncalendarioOld = persistentBitacoraNotificacion.getIdNotificacioncalendario();
            NotificacionCalendario idNotificacioncalendarioNew = bitacoraNotificacion.getIdNotificacioncalendario();
            Grupo idGrupoOld = persistentBitacoraNotificacion.getIdGrupo();
            Grupo idGrupoNew = bitacoraNotificacion.getIdGrupo();
            if (idTiponotificacionNew != null) {
                idTiponotificacionNew = em.getReference(idTiponotificacionNew.getClass(), idTiponotificacionNew.getIdTiponotificacion());
                bitacoraNotificacion.setIdTiponotificacion(idTiponotificacionNew);
            }
            if (idNotificacioncalendarioNew != null) {
                idNotificacioncalendarioNew = em.getReference(idNotificacioncalendarioNew.getClass(), idNotificacioncalendarioNew.getIdNotificacioncalendario());
                bitacoraNotificacion.setIdNotificacioncalendario(idNotificacioncalendarioNew);
            }
            if (idGrupoNew != null) {
                idGrupoNew = em.getReference(idGrupoNew.getClass(), idGrupoNew.getIdgrupo());
                bitacoraNotificacion.setIdGrupo(idGrupoNew);
            }
            bitacoraNotificacion = em.merge(bitacoraNotificacion);
            if (idTiponotificacionOld != null && !idTiponotificacionOld.equals(idTiponotificacionNew)) {
                idTiponotificacionOld.getBitacoraNotificacionList().remove(bitacoraNotificacion);
                idTiponotificacionOld = em.merge(idTiponotificacionOld);
            }
            if (idTiponotificacionNew != null && !idTiponotificacionNew.equals(idTiponotificacionOld)) {
                idTiponotificacionNew.getBitacoraNotificacionList().add(bitacoraNotificacion);
                idTiponotificacionNew = em.merge(idTiponotificacionNew);
            }
            if (idNotificacioncalendarioOld != null && !idNotificacioncalendarioOld.equals(idNotificacioncalendarioNew)) {
                idNotificacioncalendarioOld.getBitacoraNotificacionList().remove(bitacoraNotificacion);
                idNotificacioncalendarioOld = em.merge(idNotificacioncalendarioOld);
            }
            if (idNotificacioncalendarioNew != null && !idNotificacioncalendarioNew.equals(idNotificacioncalendarioOld)) {
                idNotificacioncalendarioNew.getBitacoraNotificacionList().add(bitacoraNotificacion);
                idNotificacioncalendarioNew = em.merge(idNotificacioncalendarioNew);
            }
            if (idGrupoOld != null && !idGrupoOld.equals(idGrupoNew)) {
                idGrupoOld.getBitacoraNotificacionList().remove(bitacoraNotificacion);
                idGrupoOld = em.merge(idGrupoOld);
            }
            if (idGrupoNew != null && !idGrupoNew.equals(idGrupoOld)) {
                idGrupoNew.getBitacoraNotificacionList().add(bitacoraNotificacion);
                idGrupoNew = em.merge(idGrupoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = bitacoraNotificacion.getIdBitacoranotificacion();
                if (findBitacoraNotificacion(id) == null) {
                    throw new NonexistentEntityException("The bitacoraNotificacion with id " + id + " no longer exists.");
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
            BitacoraNotificacion bitacoraNotificacion;
            try {
                bitacoraNotificacion = em.getReference(BitacoraNotificacion.class, id);
                bitacoraNotificacion.getIdBitacoranotificacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bitacoraNotificacion with id " + id + " no longer exists.", enfe);
            }
            TipoNotificacion idTiponotificacion = bitacoraNotificacion.getIdTiponotificacion();
            if (idTiponotificacion != null) {
                idTiponotificacion.getBitacoraNotificacionList().remove(bitacoraNotificacion);
                idTiponotificacion = em.merge(idTiponotificacion);
            }
            NotificacionCalendario idNotificacioncalendario = bitacoraNotificacion.getIdNotificacioncalendario();
            if (idNotificacioncalendario != null) {
                idNotificacioncalendario.getBitacoraNotificacionList().remove(bitacoraNotificacion);
                idNotificacioncalendario = em.merge(idNotificacioncalendario);
            }
            Grupo idGrupo = bitacoraNotificacion.getIdGrupo();
            if (idGrupo != null) {
                idGrupo.getBitacoraNotificacionList().remove(bitacoraNotificacion);
                idGrupo = em.merge(idGrupo);
            }
            em.remove(bitacoraNotificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BitacoraNotificacion> findBitacoraNotificacionEntities() {
        return findBitacoraNotificacionEntities(true, -1, -1);
    }

    public List<BitacoraNotificacion> findBitacoraNotificacionEntities(int maxResults, int firstResult) {
        return findBitacoraNotificacionEntities(false, maxResults, firstResult);
    }

    private List<BitacoraNotificacion> findBitacoraNotificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from BitacoraNotificacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public BitacoraNotificacion findBitacoraNotificacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BitacoraNotificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getBitacoraNotificacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from BitacoraNotificacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
