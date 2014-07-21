/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoExamen;
import com.utez.integracion.entity.EsquemaAlumnoasignatura;
import com.utez.integracion.entity.HistoricoCalificacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class HistoricoCalificacionJpaController implements Serializable {

    public HistoricoCalificacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HistoricoCalificacion historicoCalificacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoExamen idTipoexamen = historicoCalificacion.getIdTipoexamen();
            if (idTipoexamen != null) {
                idTipoexamen = em.getReference(idTipoexamen.getClass(), idTipoexamen.getIdTipoexamen());
                historicoCalificacion.setIdTipoexamen(idTipoexamen);
            }
            EsquemaAlumnoasignatura idEsquemaalumnoasignatura = historicoCalificacion.getIdEsquemaalumnoasignatura();
            if (idEsquemaalumnoasignatura != null) {
                idEsquemaalumnoasignatura = em.getReference(idEsquemaalumnoasignatura.getClass(), idEsquemaalumnoasignatura.getIdEsquemaalumnoasignatura());
                historicoCalificacion.setIdEsquemaalumnoasignatura(idEsquemaalumnoasignatura);
            }
            em.persist(historicoCalificacion);
            if (idTipoexamen != null) {
                idTipoexamen.getHistoricoCalificacionList().add(historicoCalificacion);
                idTipoexamen = em.merge(idTipoexamen);
            }
            if (idEsquemaalumnoasignatura != null) {
                idEsquemaalumnoasignatura.getHistoricoCalificacionList().add(historicoCalificacion);
                idEsquemaalumnoasignatura = em.merge(idEsquemaalumnoasignatura);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HistoricoCalificacion historicoCalificacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HistoricoCalificacion persistentHistoricoCalificacion = em.find(HistoricoCalificacion.class, historicoCalificacion.getIdHistoricocalificacion());
            TipoExamen idTipoexamenOld = persistentHistoricoCalificacion.getIdTipoexamen();
            TipoExamen idTipoexamenNew = historicoCalificacion.getIdTipoexamen();
            EsquemaAlumnoasignatura idEsquemaalumnoasignaturaOld = persistentHistoricoCalificacion.getIdEsquemaalumnoasignatura();
            EsquemaAlumnoasignatura idEsquemaalumnoasignaturaNew = historicoCalificacion.getIdEsquemaalumnoasignatura();
            if (idTipoexamenNew != null) {
                idTipoexamenNew = em.getReference(idTipoexamenNew.getClass(), idTipoexamenNew.getIdTipoexamen());
                historicoCalificacion.setIdTipoexamen(idTipoexamenNew);
            }
            if (idEsquemaalumnoasignaturaNew != null) {
                idEsquemaalumnoasignaturaNew = em.getReference(idEsquemaalumnoasignaturaNew.getClass(), idEsquemaalumnoasignaturaNew.getIdEsquemaalumnoasignatura());
                historicoCalificacion.setIdEsquemaalumnoasignatura(idEsquemaalumnoasignaturaNew);
            }
            historicoCalificacion = em.merge(historicoCalificacion);
            if (idTipoexamenOld != null && !idTipoexamenOld.equals(idTipoexamenNew)) {
                idTipoexamenOld.getHistoricoCalificacionList().remove(historicoCalificacion);
                idTipoexamenOld = em.merge(idTipoexamenOld);
            }
            if (idTipoexamenNew != null && !idTipoexamenNew.equals(idTipoexamenOld)) {
                idTipoexamenNew.getHistoricoCalificacionList().add(historicoCalificacion);
                idTipoexamenNew = em.merge(idTipoexamenNew);
            }
            if (idEsquemaalumnoasignaturaOld != null && !idEsquemaalumnoasignaturaOld.equals(idEsquemaalumnoasignaturaNew)) {
                idEsquemaalumnoasignaturaOld.getHistoricoCalificacionList().remove(historicoCalificacion);
                idEsquemaalumnoasignaturaOld = em.merge(idEsquemaalumnoasignaturaOld);
            }
            if (idEsquemaalumnoasignaturaNew != null && !idEsquemaalumnoasignaturaNew.equals(idEsquemaalumnoasignaturaOld)) {
                idEsquemaalumnoasignaturaNew.getHistoricoCalificacionList().add(historicoCalificacion);
                idEsquemaalumnoasignaturaNew = em.merge(idEsquemaalumnoasignaturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = historicoCalificacion.getIdHistoricocalificacion();
                if (findHistoricoCalificacion(id) == null) {
                    throw new NonexistentEntityException("The historicoCalificacion with id " + id + " no longer exists.");
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
            HistoricoCalificacion historicoCalificacion;
            try {
                historicoCalificacion = em.getReference(HistoricoCalificacion.class, id);
                historicoCalificacion.getIdHistoricocalificacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historicoCalificacion with id " + id + " no longer exists.", enfe);
            }
            TipoExamen idTipoexamen = historicoCalificacion.getIdTipoexamen();
            if (idTipoexamen != null) {
                idTipoexamen.getHistoricoCalificacionList().remove(historicoCalificacion);
                idTipoexamen = em.merge(idTipoexamen);
            }
            EsquemaAlumnoasignatura idEsquemaalumnoasignatura = historicoCalificacion.getIdEsquemaalumnoasignatura();
            if (idEsquemaalumnoasignatura != null) {
                idEsquemaalumnoasignatura.getHistoricoCalificacionList().remove(historicoCalificacion);
                idEsquemaalumnoasignatura = em.merge(idEsquemaalumnoasignatura);
            }
            em.remove(historicoCalificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HistoricoCalificacion> findHistoricoCalificacionEntities() {
        return findHistoricoCalificacionEntities(true, -1, -1);
    }

    public List<HistoricoCalificacion> findHistoricoCalificacionEntities(int maxResults, int firstResult) {
        return findHistoricoCalificacionEntities(false, maxResults, firstResult);
    }

    private List<HistoricoCalificacion> findHistoricoCalificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from HistoricoCalificacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public HistoricoCalificacion findHistoricoCalificacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HistoricoCalificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistoricoCalificacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from HistoricoCalificacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
