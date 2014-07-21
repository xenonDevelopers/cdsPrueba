/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.ExperienciaLaboral;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoTrabajo;
import com.utez.integracion.entity.TipoEmpresa;
import com.utez.integracion.entity.RecursoHumano;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ExperienciaLaboralJpaController implements Serializable {

    public ExperienciaLaboralJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ExperienciaLaboral experienciaLaboral) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoTrabajo idTipotrabajo = experienciaLaboral.getIdTipotrabajo();
            if (idTipotrabajo != null) {
                idTipotrabajo = em.getReference(idTipotrabajo.getClass(), idTipotrabajo.getIdTipotrabajo());
                experienciaLaboral.setIdTipotrabajo(idTipotrabajo);
            }
            TipoEmpresa idTipoinstitucion = experienciaLaboral.getIdTipoinstitucion();
            if (idTipoinstitucion != null) {
                idTipoinstitucion = em.getReference(idTipoinstitucion.getClass(), idTipoinstitucion.getIdTipoempresa());
                experienciaLaboral.setIdTipoinstitucion(idTipoinstitucion);
            }
            RecursoHumano idRecursohumano = experienciaLaboral.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano = em.getReference(idRecursohumano.getClass(), idRecursohumano.getIdRecursohumano());
                experienciaLaboral.setIdRecursohumano(idRecursohumano);
            }
            em.persist(experienciaLaboral);
            if (idTipotrabajo != null) {
                idTipotrabajo.getExperienciaLaboralList().add(experienciaLaboral);
                idTipotrabajo = em.merge(idTipotrabajo);
            }
            if (idTipoinstitucion != null) {
                idTipoinstitucion.getExperienciaLaboralList().add(experienciaLaboral);
                idTipoinstitucion = em.merge(idTipoinstitucion);
            }
            if (idRecursohumano != null) {
                idRecursohumano.getExperienciaLaboralList().add(experienciaLaboral);
                idRecursohumano = em.merge(idRecursohumano);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ExperienciaLaboral experienciaLaboral) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ExperienciaLaboral persistentExperienciaLaboral = em.find(ExperienciaLaboral.class, experienciaLaboral.getIdExperiencialaboral());
            TipoTrabajo idTipotrabajoOld = persistentExperienciaLaboral.getIdTipotrabajo();
            TipoTrabajo idTipotrabajoNew = experienciaLaboral.getIdTipotrabajo();
            TipoEmpresa idTipoinstitucionOld = persistentExperienciaLaboral.getIdTipoinstitucion();
            TipoEmpresa idTipoinstitucionNew = experienciaLaboral.getIdTipoinstitucion();
            RecursoHumano idRecursohumanoOld = persistentExperienciaLaboral.getIdRecursohumano();
            RecursoHumano idRecursohumanoNew = experienciaLaboral.getIdRecursohumano();
            if (idTipotrabajoNew != null) {
                idTipotrabajoNew = em.getReference(idTipotrabajoNew.getClass(), idTipotrabajoNew.getIdTipotrabajo());
                experienciaLaboral.setIdTipotrabajo(idTipotrabajoNew);
            }
            if (idTipoinstitucionNew != null) {
                idTipoinstitucionNew = em.getReference(idTipoinstitucionNew.getClass(), idTipoinstitucionNew.getIdTipoempresa());
                experienciaLaboral.setIdTipoinstitucion(idTipoinstitucionNew);
            }
            if (idRecursohumanoNew != null) {
                idRecursohumanoNew = em.getReference(idRecursohumanoNew.getClass(), idRecursohumanoNew.getIdRecursohumano());
                experienciaLaboral.setIdRecursohumano(idRecursohumanoNew);
            }
            experienciaLaboral = em.merge(experienciaLaboral);
            if (idTipotrabajoOld != null && !idTipotrabajoOld.equals(idTipotrabajoNew)) {
                idTipotrabajoOld.getExperienciaLaboralList().remove(experienciaLaboral);
                idTipotrabajoOld = em.merge(idTipotrabajoOld);
            }
            if (idTipotrabajoNew != null && !idTipotrabajoNew.equals(idTipotrabajoOld)) {
                idTipotrabajoNew.getExperienciaLaboralList().add(experienciaLaboral);
                idTipotrabajoNew = em.merge(idTipotrabajoNew);
            }
            if (idTipoinstitucionOld != null && !idTipoinstitucionOld.equals(idTipoinstitucionNew)) {
                idTipoinstitucionOld.getExperienciaLaboralList().remove(experienciaLaboral);
                idTipoinstitucionOld = em.merge(idTipoinstitucionOld);
            }
            if (idTipoinstitucionNew != null && !idTipoinstitucionNew.equals(idTipoinstitucionOld)) {
                idTipoinstitucionNew.getExperienciaLaboralList().add(experienciaLaboral);
                idTipoinstitucionNew = em.merge(idTipoinstitucionNew);
            }
            if (idRecursohumanoOld != null && !idRecursohumanoOld.equals(idRecursohumanoNew)) {
                idRecursohumanoOld.getExperienciaLaboralList().remove(experienciaLaboral);
                idRecursohumanoOld = em.merge(idRecursohumanoOld);
            }
            if (idRecursohumanoNew != null && !idRecursohumanoNew.equals(idRecursohumanoOld)) {
                idRecursohumanoNew.getExperienciaLaboralList().add(experienciaLaboral);
                idRecursohumanoNew = em.merge(idRecursohumanoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = experienciaLaboral.getIdExperiencialaboral();
                if (findExperienciaLaboral(id) == null) {
                    throw new NonexistentEntityException("The experienciaLaboral with id " + id + " no longer exists.");
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
            ExperienciaLaboral experienciaLaboral;
            try {
                experienciaLaboral = em.getReference(ExperienciaLaboral.class, id);
                experienciaLaboral.getIdExperiencialaboral();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The experienciaLaboral with id " + id + " no longer exists.", enfe);
            }
            TipoTrabajo idTipotrabajo = experienciaLaboral.getIdTipotrabajo();
            if (idTipotrabajo != null) {
                idTipotrabajo.getExperienciaLaboralList().remove(experienciaLaboral);
                idTipotrabajo = em.merge(idTipotrabajo);
            }
            TipoEmpresa idTipoinstitucion = experienciaLaboral.getIdTipoinstitucion();
            if (idTipoinstitucion != null) {
                idTipoinstitucion.getExperienciaLaboralList().remove(experienciaLaboral);
                idTipoinstitucion = em.merge(idTipoinstitucion);
            }
            RecursoHumano idRecursohumano = experienciaLaboral.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano.getExperienciaLaboralList().remove(experienciaLaboral);
                idRecursohumano = em.merge(idRecursohumano);
            }
            em.remove(experienciaLaboral);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ExperienciaLaboral> findExperienciaLaboralEntities() {
        return findExperienciaLaboralEntities(true, -1, -1);
    }

    public List<ExperienciaLaboral> findExperienciaLaboralEntities(int maxResults, int firstResult) {
        return findExperienciaLaboralEntities(false, maxResults, firstResult);
    }

    private List<ExperienciaLaboral> findExperienciaLaboralEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ExperienciaLaboral as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ExperienciaLaboral findExperienciaLaboral(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ExperienciaLaboral.class, id);
        } finally {
            em.close();
        }
    }

    public int getExperienciaLaboralCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ExperienciaLaboral as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
