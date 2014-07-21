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
import com.utez.integracion.entity.TipoEvaluacion;
import com.utez.secretariaAcademica.entity.Grupo;
import com.utez.integracion.entity.AsignacionAsignaturabanco;
import com.utez.integracion.entity.AsignacionEvaluacion;
import com.utez.integracion.entity.ResultadoEvaluacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionEvaluacionJpaController implements Serializable {

    public AsignacionEvaluacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionEvaluacion asignacionEvaluacion) {
        if (asignacionEvaluacion.getResultadoEvaluacionList() == null) {
            asignacionEvaluacion.setResultadoEvaluacionList(new ArrayList<ResultadoEvaluacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoExamen idTipoexamen = asignacionEvaluacion.getIdTipoexamen();
            if (idTipoexamen != null) {
                idTipoexamen = em.getReference(idTipoexamen.getClass(), idTipoexamen.getIdTipoexamen());
                asignacionEvaluacion.setIdTipoexamen(idTipoexamen);
            }
            TipoEvaluacion idTipoevaluacion = asignacionEvaluacion.getIdTipoevaluacion();
            if (idTipoevaluacion != null) {
                idTipoevaluacion = em.getReference(idTipoevaluacion.getClass(), idTipoevaluacion.getIdTipoevaluacion());
                asignacionEvaluacion.setIdTipoevaluacion(idTipoevaluacion);
            }
            Grupo idGrupo = asignacionEvaluacion.getIdGrupo();
            if (idGrupo != null) {
                idGrupo = em.getReference(idGrupo.getClass(), idGrupo.getIdgrupo());
                asignacionEvaluacion.setIdGrupo(idGrupo);
            }
            AsignacionAsignaturabanco idAsignacionasignaturabanco = asignacionEvaluacion.getIdAsignacionasignaturabanco();
            if (idAsignacionasignaturabanco != null) {
                idAsignacionasignaturabanco = em.getReference(idAsignacionasignaturabanco.getClass(), idAsignacionasignaturabanco.getIdAsignacionasignaturabanco());
                asignacionEvaluacion.setIdAsignacionasignaturabanco(idAsignacionasignaturabanco);
            }
            List<ResultadoEvaluacion> attachedResultadoEvaluacionList = new ArrayList<ResultadoEvaluacion>();
            for (ResultadoEvaluacion resultadoEvaluacionListResultadoEvaluacionToAttach : asignacionEvaluacion.getResultadoEvaluacionList()) {
                resultadoEvaluacionListResultadoEvaluacionToAttach = em.getReference(resultadoEvaluacionListResultadoEvaluacionToAttach.getClass(), resultadoEvaluacionListResultadoEvaluacionToAttach.getIdResultadoevaluacion());
                attachedResultadoEvaluacionList.add(resultadoEvaluacionListResultadoEvaluacionToAttach);
            }
            asignacionEvaluacion.setResultadoEvaluacionList(attachedResultadoEvaluacionList);
            em.persist(asignacionEvaluacion);
            if (idTipoexamen != null) {
                idTipoexamen.getAsignacionEvaluacionList().add(asignacionEvaluacion);
                idTipoexamen = em.merge(idTipoexamen);
            }
            if (idTipoevaluacion != null) {
                idTipoevaluacion.getAsignacionEvaluacionList().add(asignacionEvaluacion);
                idTipoevaluacion = em.merge(idTipoevaluacion);
            }
            if (idGrupo != null) {
                idGrupo.getAsignacionEvaluacionList().add(asignacionEvaluacion);
                idGrupo = em.merge(idGrupo);
            }
            if (idAsignacionasignaturabanco != null) {
                idAsignacionasignaturabanco.getAsignacionEvaluacionList().add(asignacionEvaluacion);
                idAsignacionasignaturabanco = em.merge(idAsignacionasignaturabanco);
            }
            for (ResultadoEvaluacion resultadoEvaluacionListResultadoEvaluacion : asignacionEvaluacion.getResultadoEvaluacionList()) {
                AsignacionEvaluacion oldIdAsignacionevaluacionOfResultadoEvaluacionListResultadoEvaluacion = resultadoEvaluacionListResultadoEvaluacion.getIdAsignacionevaluacion();
                resultadoEvaluacionListResultadoEvaluacion.setIdAsignacionevaluacion(asignacionEvaluacion);
                resultadoEvaluacionListResultadoEvaluacion = em.merge(resultadoEvaluacionListResultadoEvaluacion);
                if (oldIdAsignacionevaluacionOfResultadoEvaluacionListResultadoEvaluacion != null) {
                    oldIdAsignacionevaluacionOfResultadoEvaluacionListResultadoEvaluacion.getResultadoEvaluacionList().remove(resultadoEvaluacionListResultadoEvaluacion);
                    oldIdAsignacionevaluacionOfResultadoEvaluacionListResultadoEvaluacion = em.merge(oldIdAsignacionevaluacionOfResultadoEvaluacionListResultadoEvaluacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionEvaluacion asignacionEvaluacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionEvaluacion persistentAsignacionEvaluacion = em.find(AsignacionEvaluacion.class, asignacionEvaluacion.getIdAsignacionevaluacion());
            TipoExamen idTipoexamenOld = persistentAsignacionEvaluacion.getIdTipoexamen();
            TipoExamen idTipoexamenNew = asignacionEvaluacion.getIdTipoexamen();
            TipoEvaluacion idTipoevaluacionOld = persistentAsignacionEvaluacion.getIdTipoevaluacion();
            TipoEvaluacion idTipoevaluacionNew = asignacionEvaluacion.getIdTipoevaluacion();
            Grupo idGrupoOld = persistentAsignacionEvaluacion.getIdGrupo();
            Grupo idGrupoNew = asignacionEvaluacion.getIdGrupo();
            AsignacionAsignaturabanco idAsignacionasignaturabancoOld = persistentAsignacionEvaluacion.getIdAsignacionasignaturabanco();
            AsignacionAsignaturabanco idAsignacionasignaturabancoNew = asignacionEvaluacion.getIdAsignacionasignaturabanco();
            List<ResultadoEvaluacion> resultadoEvaluacionListOld = persistentAsignacionEvaluacion.getResultadoEvaluacionList();
            List<ResultadoEvaluacion> resultadoEvaluacionListNew = asignacionEvaluacion.getResultadoEvaluacionList();
            if (idTipoexamenNew != null) {
                idTipoexamenNew = em.getReference(idTipoexamenNew.getClass(), idTipoexamenNew.getIdTipoexamen());
                asignacionEvaluacion.setIdTipoexamen(idTipoexamenNew);
            }
            if (idTipoevaluacionNew != null) {
                idTipoevaluacionNew = em.getReference(idTipoevaluacionNew.getClass(), idTipoevaluacionNew.getIdTipoevaluacion());
                asignacionEvaluacion.setIdTipoevaluacion(idTipoevaluacionNew);
            }
            if (idGrupoNew != null) {
                idGrupoNew = em.getReference(idGrupoNew.getClass(), idGrupoNew.getIdgrupo());
                asignacionEvaluacion.setIdGrupo(idGrupoNew);
            }
            if (idAsignacionasignaturabancoNew != null) {
                idAsignacionasignaturabancoNew = em.getReference(idAsignacionasignaturabancoNew.getClass(), idAsignacionasignaturabancoNew.getIdAsignacionasignaturabanco());
                asignacionEvaluacion.setIdAsignacionasignaturabanco(idAsignacionasignaturabancoNew);
            }
            List<ResultadoEvaluacion> attachedResultadoEvaluacionListNew = new ArrayList<ResultadoEvaluacion>();
            for (ResultadoEvaluacion resultadoEvaluacionListNewResultadoEvaluacionToAttach : resultadoEvaluacionListNew) {
                resultadoEvaluacionListNewResultadoEvaluacionToAttach = em.getReference(resultadoEvaluacionListNewResultadoEvaluacionToAttach.getClass(), resultadoEvaluacionListNewResultadoEvaluacionToAttach.getIdResultadoevaluacion());
                attachedResultadoEvaluacionListNew.add(resultadoEvaluacionListNewResultadoEvaluacionToAttach);
            }
            resultadoEvaluacionListNew = attachedResultadoEvaluacionListNew;
            asignacionEvaluacion.setResultadoEvaluacionList(resultadoEvaluacionListNew);
            asignacionEvaluacion = em.merge(asignacionEvaluacion);
            if (idTipoexamenOld != null && !idTipoexamenOld.equals(idTipoexamenNew)) {
                idTipoexamenOld.getAsignacionEvaluacionList().remove(asignacionEvaluacion);
                idTipoexamenOld = em.merge(idTipoexamenOld);
            }
            if (idTipoexamenNew != null && !idTipoexamenNew.equals(idTipoexamenOld)) {
                idTipoexamenNew.getAsignacionEvaluacionList().add(asignacionEvaluacion);
                idTipoexamenNew = em.merge(idTipoexamenNew);
            }
            if (idTipoevaluacionOld != null && !idTipoevaluacionOld.equals(idTipoevaluacionNew)) {
                idTipoevaluacionOld.getAsignacionEvaluacionList().remove(asignacionEvaluacion);
                idTipoevaluacionOld = em.merge(idTipoevaluacionOld);
            }
            if (idTipoevaluacionNew != null && !idTipoevaluacionNew.equals(idTipoevaluacionOld)) {
                idTipoevaluacionNew.getAsignacionEvaluacionList().add(asignacionEvaluacion);
                idTipoevaluacionNew = em.merge(idTipoevaluacionNew);
            }
            if (idGrupoOld != null && !idGrupoOld.equals(idGrupoNew)) {
                idGrupoOld.getAsignacionEvaluacionList().remove(asignacionEvaluacion);
                idGrupoOld = em.merge(idGrupoOld);
            }
            if (idGrupoNew != null && !idGrupoNew.equals(idGrupoOld)) {
                idGrupoNew.getAsignacionEvaluacionList().add(asignacionEvaluacion);
                idGrupoNew = em.merge(idGrupoNew);
            }
            if (idAsignacionasignaturabancoOld != null && !idAsignacionasignaturabancoOld.equals(idAsignacionasignaturabancoNew)) {
                idAsignacionasignaturabancoOld.getAsignacionEvaluacionList().remove(asignacionEvaluacion);
                idAsignacionasignaturabancoOld = em.merge(idAsignacionasignaturabancoOld);
            }
            if (idAsignacionasignaturabancoNew != null && !idAsignacionasignaturabancoNew.equals(idAsignacionasignaturabancoOld)) {
                idAsignacionasignaturabancoNew.getAsignacionEvaluacionList().add(asignacionEvaluacion);
                idAsignacionasignaturabancoNew = em.merge(idAsignacionasignaturabancoNew);
            }
            for (ResultadoEvaluacion resultadoEvaluacionListOldResultadoEvaluacion : resultadoEvaluacionListOld) {
                if (!resultadoEvaluacionListNew.contains(resultadoEvaluacionListOldResultadoEvaluacion)) {
                    resultadoEvaluacionListOldResultadoEvaluacion.setIdAsignacionevaluacion(null);
                    resultadoEvaluacionListOldResultadoEvaluacion = em.merge(resultadoEvaluacionListOldResultadoEvaluacion);
                }
            }
            for (ResultadoEvaluacion resultadoEvaluacionListNewResultadoEvaluacion : resultadoEvaluacionListNew) {
                if (!resultadoEvaluacionListOld.contains(resultadoEvaluacionListNewResultadoEvaluacion)) {
                    AsignacionEvaluacion oldIdAsignacionevaluacionOfResultadoEvaluacionListNewResultadoEvaluacion = resultadoEvaluacionListNewResultadoEvaluacion.getIdAsignacionevaluacion();
                    resultadoEvaluacionListNewResultadoEvaluacion.setIdAsignacionevaluacion(asignacionEvaluacion);
                    resultadoEvaluacionListNewResultadoEvaluacion = em.merge(resultadoEvaluacionListNewResultadoEvaluacion);
                    if (oldIdAsignacionevaluacionOfResultadoEvaluacionListNewResultadoEvaluacion != null && !oldIdAsignacionevaluacionOfResultadoEvaluacionListNewResultadoEvaluacion.equals(asignacionEvaluacion)) {
                        oldIdAsignacionevaluacionOfResultadoEvaluacionListNewResultadoEvaluacion.getResultadoEvaluacionList().remove(resultadoEvaluacionListNewResultadoEvaluacion);
                        oldIdAsignacionevaluacionOfResultadoEvaluacionListNewResultadoEvaluacion = em.merge(oldIdAsignacionevaluacionOfResultadoEvaluacionListNewResultadoEvaluacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionEvaluacion.getIdAsignacionevaluacion();
                if (findAsignacionEvaluacion(id) == null) {
                    throw new NonexistentEntityException("The asignacionEvaluacion with id " + id + " no longer exists.");
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
            AsignacionEvaluacion asignacionEvaluacion;
            try {
                asignacionEvaluacion = em.getReference(AsignacionEvaluacion.class, id);
                asignacionEvaluacion.getIdAsignacionevaluacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionEvaluacion with id " + id + " no longer exists.", enfe);
            }
            TipoExamen idTipoexamen = asignacionEvaluacion.getIdTipoexamen();
            if (idTipoexamen != null) {
                idTipoexamen.getAsignacionEvaluacionList().remove(asignacionEvaluacion);
                idTipoexamen = em.merge(idTipoexamen);
            }
            TipoEvaluacion idTipoevaluacion = asignacionEvaluacion.getIdTipoevaluacion();
            if (idTipoevaluacion != null) {
                idTipoevaluacion.getAsignacionEvaluacionList().remove(asignacionEvaluacion);
                idTipoevaluacion = em.merge(idTipoevaluacion);
            }
            Grupo idGrupo = asignacionEvaluacion.getIdGrupo();
            if (idGrupo != null) {
                idGrupo.getAsignacionEvaluacionList().remove(asignacionEvaluacion);
                idGrupo = em.merge(idGrupo);
            }
            AsignacionAsignaturabanco idAsignacionasignaturabanco = asignacionEvaluacion.getIdAsignacionasignaturabanco();
            if (idAsignacionasignaturabanco != null) {
                idAsignacionasignaturabanco.getAsignacionEvaluacionList().remove(asignacionEvaluacion);
                idAsignacionasignaturabanco = em.merge(idAsignacionasignaturabanco);
            }
            List<ResultadoEvaluacion> resultadoEvaluacionList = asignacionEvaluacion.getResultadoEvaluacionList();
            for (ResultadoEvaluacion resultadoEvaluacionListResultadoEvaluacion : resultadoEvaluacionList) {
                resultadoEvaluacionListResultadoEvaluacion.setIdAsignacionevaluacion(null);
                resultadoEvaluacionListResultadoEvaluacion = em.merge(resultadoEvaluacionListResultadoEvaluacion);
            }
            em.remove(asignacionEvaluacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionEvaluacion> findAsignacionEvaluacionEntities() {
        return findAsignacionEvaluacionEntities(true, -1, -1);
    }

    public List<AsignacionEvaluacion> findAsignacionEvaluacionEntities(int maxResults, int firstResult) {
        return findAsignacionEvaluacionEntities(false, maxResults, firstResult);
    }

    private List<AsignacionEvaluacion> findAsignacionEvaluacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionEvaluacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionEvaluacion findAsignacionEvaluacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionEvaluacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionEvaluacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionEvaluacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
