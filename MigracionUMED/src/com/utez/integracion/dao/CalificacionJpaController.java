/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import com.utez.integracion.entity.Calificacion;
import com.utez.integracion.entity.CalificacionPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoExamen;
import com.utez.integracion.entity.EsquemaAlumnoasignatura;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class CalificacionJpaController implements Serializable {

    public CalificacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Calificacion calificacion) throws PreexistingEntityException, Exception {
        if (calificacion.getCalificacionPK() == null) {
            calificacion.setCalificacionPK(new CalificacionPK());
        }
        calificacion.getCalificacionPK().setIdEsquemaalumnoasignatura(calificacion.getEsquemaAlumnoasignatura().getIdEsquemaalumnoasignatura());
        calificacion.getCalificacionPK().setIdTipoexamen(calificacion.getTipoExamen().getIdTipoexamen());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoExamen tipoExamen = calificacion.getTipoExamen();
            if (tipoExamen != null) {
                tipoExamen = em.getReference(tipoExamen.getClass(), tipoExamen.getIdTipoexamen());
                calificacion.setTipoExamen(tipoExamen);
            }
            EsquemaAlumnoasignatura esquemaAlumnoasignatura = calificacion.getEsquemaAlumnoasignatura();
            if (esquemaAlumnoasignatura != null) {
                esquemaAlumnoasignatura = em.getReference(esquemaAlumnoasignatura.getClass(), esquemaAlumnoasignatura.getIdEsquemaalumnoasignatura());
                calificacion.setEsquemaAlumnoasignatura(esquemaAlumnoasignatura);
            }
            em.persist(calificacion);
            if (tipoExamen != null) {
                tipoExamen.getCalificacionList().add(calificacion);
                tipoExamen = em.merge(tipoExamen);
            }
            if (esquemaAlumnoasignatura != null) {
                esquemaAlumnoasignatura.getCalificacionList().add(calificacion);
                esquemaAlumnoasignatura = em.merge(esquemaAlumnoasignatura);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCalificacion(calificacion.getCalificacionPK()) != null) {
                throw new PreexistingEntityException("Calificacion " + calificacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Calificacion calificacion) throws NonexistentEntityException, Exception {
        calificacion.getCalificacionPK().setIdEsquemaalumnoasignatura(calificacion.getEsquemaAlumnoasignatura().getIdEsquemaalumnoasignatura());
        calificacion.getCalificacionPK().setIdTipoexamen(calificacion.getTipoExamen().getIdTipoexamen());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calificacion persistentCalificacion = em.find(Calificacion.class, calificacion.getCalificacionPK());
            TipoExamen tipoExamenOld = persistentCalificacion.getTipoExamen();
            TipoExamen tipoExamenNew = calificacion.getTipoExamen();
            EsquemaAlumnoasignatura esquemaAlumnoasignaturaOld = persistentCalificacion.getEsquemaAlumnoasignatura();
            EsquemaAlumnoasignatura esquemaAlumnoasignaturaNew = calificacion.getEsquemaAlumnoasignatura();
            if (tipoExamenNew != null) {
                tipoExamenNew = em.getReference(tipoExamenNew.getClass(), tipoExamenNew.getIdTipoexamen());
                calificacion.setTipoExamen(tipoExamenNew);
            }
            if (esquemaAlumnoasignaturaNew != null) {
                esquemaAlumnoasignaturaNew = em.getReference(esquemaAlumnoasignaturaNew.getClass(), esquemaAlumnoasignaturaNew.getIdEsquemaalumnoasignatura());
                calificacion.setEsquemaAlumnoasignatura(esquemaAlumnoasignaturaNew);
            }
            calificacion = em.merge(calificacion);
            if (tipoExamenOld != null && !tipoExamenOld.equals(tipoExamenNew)) {
                tipoExamenOld.getCalificacionList().remove(calificacion);
                tipoExamenOld = em.merge(tipoExamenOld);
            }
            if (tipoExamenNew != null && !tipoExamenNew.equals(tipoExamenOld)) {
                tipoExamenNew.getCalificacionList().add(calificacion);
                tipoExamenNew = em.merge(tipoExamenNew);
            }
            if (esquemaAlumnoasignaturaOld != null && !esquemaAlumnoasignaturaOld.equals(esquemaAlumnoasignaturaNew)) {
                esquemaAlumnoasignaturaOld.getCalificacionList().remove(calificacion);
                esquemaAlumnoasignaturaOld = em.merge(esquemaAlumnoasignaturaOld);
            }
            if (esquemaAlumnoasignaturaNew != null && !esquemaAlumnoasignaturaNew.equals(esquemaAlumnoasignaturaOld)) {
                esquemaAlumnoasignaturaNew.getCalificacionList().add(calificacion);
                esquemaAlumnoasignaturaNew = em.merge(esquemaAlumnoasignaturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CalificacionPK id = calificacion.getCalificacionPK();
                if (findCalificacion(id) == null) {
                    throw new NonexistentEntityException("The calificacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CalificacionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calificacion calificacion;
            try {
                calificacion = em.getReference(Calificacion.class, id);
                calificacion.getCalificacionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The calificacion with id " + id + " no longer exists.", enfe);
            }
            TipoExamen tipoExamen = calificacion.getTipoExamen();
            if (tipoExamen != null) {
                tipoExamen.getCalificacionList().remove(calificacion);
                tipoExamen = em.merge(tipoExamen);
            }
            EsquemaAlumnoasignatura esquemaAlumnoasignatura = calificacion.getEsquemaAlumnoasignatura();
            if (esquemaAlumnoasignatura != null) {
                esquemaAlumnoasignatura.getCalificacionList().remove(calificacion);
                esquemaAlumnoasignatura = em.merge(esquemaAlumnoasignatura);
            }
            em.remove(calificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Calificacion> findCalificacionEntities() {
        return findCalificacionEntities(true, -1, -1);
    }

    public List<Calificacion> findCalificacionEntities(int maxResults, int firstResult) {
        return findCalificacionEntities(false, maxResults, firstResult);
    }

    private List<Calificacion> findCalificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Calificacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Calificacion findCalificacion(CalificacionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Calificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCalificacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Calificacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
