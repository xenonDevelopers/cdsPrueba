/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.AsignacionEvaluacion;
import com.utez.secretariaAcademica.entity.Alumno;
import com.utez.integracion.entity.RespuestaEvaluacion;
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
public class ResultadoEvaluacionJpaController implements Serializable {

    public ResultadoEvaluacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ResultadoEvaluacion resultadoEvaluacion) {
        if (resultadoEvaluacion.getRespuestaEvaluacionList() == null) {
            resultadoEvaluacion.setRespuestaEvaluacionList(new ArrayList<RespuestaEvaluacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionEvaluacion idAsignacionevaluacion = resultadoEvaluacion.getIdAsignacionevaluacion();
            if (idAsignacionevaluacion != null) {
                idAsignacionevaluacion = em.getReference(idAsignacionevaluacion.getClass(), idAsignacionevaluacion.getIdAsignacionevaluacion());
                resultadoEvaluacion.setIdAsignacionevaluacion(idAsignacionevaluacion);
            }
            Alumno matricula = resultadoEvaluacion.getMatricula();
            if (matricula != null) {
                matricula = em.getReference(matricula.getClass(), matricula.getMatricula());
                resultadoEvaluacion.setMatricula(matricula);
            }
            List<RespuestaEvaluacion> attachedRespuestaEvaluacionList = new ArrayList<RespuestaEvaluacion>();
            for (RespuestaEvaluacion respuestaEvaluacionListRespuestaEvaluacionToAttach : resultadoEvaluacion.getRespuestaEvaluacionList()) {
                respuestaEvaluacionListRespuestaEvaluacionToAttach = em.getReference(respuestaEvaluacionListRespuestaEvaluacionToAttach.getClass(), respuestaEvaluacionListRespuestaEvaluacionToAttach.getIdRespuestaevaluacion());
                attachedRespuestaEvaluacionList.add(respuestaEvaluacionListRespuestaEvaluacionToAttach);
            }
            resultadoEvaluacion.setRespuestaEvaluacionList(attachedRespuestaEvaluacionList);
            em.persist(resultadoEvaluacion);
            if (idAsignacionevaluacion != null) {
                idAsignacionevaluacion.getResultadoEvaluacionList().add(resultadoEvaluacion);
                idAsignacionevaluacion = em.merge(idAsignacionevaluacion);
            }
            if (matricula != null) {
                matricula.getResultadoEvaluacionList().add(resultadoEvaluacion);
                matricula = em.merge(matricula);
            }
            for (RespuestaEvaluacion respuestaEvaluacionListRespuestaEvaluacion : resultadoEvaluacion.getRespuestaEvaluacionList()) {
                ResultadoEvaluacion oldIdResultadoevaluacionOfRespuestaEvaluacionListRespuestaEvaluacion = respuestaEvaluacionListRespuestaEvaluacion.getIdResultadoevaluacion();
                respuestaEvaluacionListRespuestaEvaluacion.setIdResultadoevaluacion(resultadoEvaluacion);
                respuestaEvaluacionListRespuestaEvaluacion = em.merge(respuestaEvaluacionListRespuestaEvaluacion);
                if (oldIdResultadoevaluacionOfRespuestaEvaluacionListRespuestaEvaluacion != null) {
                    oldIdResultadoevaluacionOfRespuestaEvaluacionListRespuestaEvaluacion.getRespuestaEvaluacionList().remove(respuestaEvaluacionListRespuestaEvaluacion);
                    oldIdResultadoevaluacionOfRespuestaEvaluacionListRespuestaEvaluacion = em.merge(oldIdResultadoevaluacionOfRespuestaEvaluacionListRespuestaEvaluacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ResultadoEvaluacion resultadoEvaluacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ResultadoEvaluacion persistentResultadoEvaluacion = em.find(ResultadoEvaluacion.class, resultadoEvaluacion.getIdResultadoevaluacion());
            AsignacionEvaluacion idAsignacionevaluacionOld = persistentResultadoEvaluacion.getIdAsignacionevaluacion();
            AsignacionEvaluacion idAsignacionevaluacionNew = resultadoEvaluacion.getIdAsignacionevaluacion();
            Alumno matriculaOld = persistentResultadoEvaluacion.getMatricula();
            Alumno matriculaNew = resultadoEvaluacion.getMatricula();
            List<RespuestaEvaluacion> respuestaEvaluacionListOld = persistentResultadoEvaluacion.getRespuestaEvaluacionList();
            List<RespuestaEvaluacion> respuestaEvaluacionListNew = resultadoEvaluacion.getRespuestaEvaluacionList();
            if (idAsignacionevaluacionNew != null) {
                idAsignacionevaluacionNew = em.getReference(idAsignacionevaluacionNew.getClass(), idAsignacionevaluacionNew.getIdAsignacionevaluacion());
                resultadoEvaluacion.setIdAsignacionevaluacion(idAsignacionevaluacionNew);
            }
            if (matriculaNew != null) {
                matriculaNew = em.getReference(matriculaNew.getClass(), matriculaNew.getMatricula());
                resultadoEvaluacion.setMatricula(matriculaNew);
            }
            List<RespuestaEvaluacion> attachedRespuestaEvaluacionListNew = new ArrayList<RespuestaEvaluacion>();
            for (RespuestaEvaluacion respuestaEvaluacionListNewRespuestaEvaluacionToAttach : respuestaEvaluacionListNew) {
                respuestaEvaluacionListNewRespuestaEvaluacionToAttach = em.getReference(respuestaEvaluacionListNewRespuestaEvaluacionToAttach.getClass(), respuestaEvaluacionListNewRespuestaEvaluacionToAttach.getIdRespuestaevaluacion());
                attachedRespuestaEvaluacionListNew.add(respuestaEvaluacionListNewRespuestaEvaluacionToAttach);
            }
            respuestaEvaluacionListNew = attachedRespuestaEvaluacionListNew;
            resultadoEvaluacion.setRespuestaEvaluacionList(respuestaEvaluacionListNew);
            resultadoEvaluacion = em.merge(resultadoEvaluacion);
            if (idAsignacionevaluacionOld != null && !idAsignacionevaluacionOld.equals(idAsignacionevaluacionNew)) {
                idAsignacionevaluacionOld.getResultadoEvaluacionList().remove(resultadoEvaluacion);
                idAsignacionevaluacionOld = em.merge(idAsignacionevaluacionOld);
            }
            if (idAsignacionevaluacionNew != null && !idAsignacionevaluacionNew.equals(idAsignacionevaluacionOld)) {
                idAsignacionevaluacionNew.getResultadoEvaluacionList().add(resultadoEvaluacion);
                idAsignacionevaluacionNew = em.merge(idAsignacionevaluacionNew);
            }
            if (matriculaOld != null && !matriculaOld.equals(matriculaNew)) {
                matriculaOld.getResultadoEvaluacionList().remove(resultadoEvaluacion);
                matriculaOld = em.merge(matriculaOld);
            }
            if (matriculaNew != null && !matriculaNew.equals(matriculaOld)) {
                matriculaNew.getResultadoEvaluacionList().add(resultadoEvaluacion);
                matriculaNew = em.merge(matriculaNew);
            }
            for (RespuestaEvaluacion respuestaEvaluacionListOldRespuestaEvaluacion : respuestaEvaluacionListOld) {
                if (!respuestaEvaluacionListNew.contains(respuestaEvaluacionListOldRespuestaEvaluacion)) {
                    respuestaEvaluacionListOldRespuestaEvaluacion.setIdResultadoevaluacion(null);
                    respuestaEvaluacionListOldRespuestaEvaluacion = em.merge(respuestaEvaluacionListOldRespuestaEvaluacion);
                }
            }
            for (RespuestaEvaluacion respuestaEvaluacionListNewRespuestaEvaluacion : respuestaEvaluacionListNew) {
                if (!respuestaEvaluacionListOld.contains(respuestaEvaluacionListNewRespuestaEvaluacion)) {
                    ResultadoEvaluacion oldIdResultadoevaluacionOfRespuestaEvaluacionListNewRespuestaEvaluacion = respuestaEvaluacionListNewRespuestaEvaluacion.getIdResultadoevaluacion();
                    respuestaEvaluacionListNewRespuestaEvaluacion.setIdResultadoevaluacion(resultadoEvaluacion);
                    respuestaEvaluacionListNewRespuestaEvaluacion = em.merge(respuestaEvaluacionListNewRespuestaEvaluacion);
                    if (oldIdResultadoevaluacionOfRespuestaEvaluacionListNewRespuestaEvaluacion != null && !oldIdResultadoevaluacionOfRespuestaEvaluacionListNewRespuestaEvaluacion.equals(resultadoEvaluacion)) {
                        oldIdResultadoevaluacionOfRespuestaEvaluacionListNewRespuestaEvaluacion.getRespuestaEvaluacionList().remove(respuestaEvaluacionListNewRespuestaEvaluacion);
                        oldIdResultadoevaluacionOfRespuestaEvaluacionListNewRespuestaEvaluacion = em.merge(oldIdResultadoevaluacionOfRespuestaEvaluacionListNewRespuestaEvaluacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = resultadoEvaluacion.getIdResultadoevaluacion();
                if (findResultadoEvaluacion(id) == null) {
                    throw new NonexistentEntityException("The resultadoEvaluacion with id " + id + " no longer exists.");
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
            ResultadoEvaluacion resultadoEvaluacion;
            try {
                resultadoEvaluacion = em.getReference(ResultadoEvaluacion.class, id);
                resultadoEvaluacion.getIdResultadoevaluacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resultadoEvaluacion with id " + id + " no longer exists.", enfe);
            }
            AsignacionEvaluacion idAsignacionevaluacion = resultadoEvaluacion.getIdAsignacionevaluacion();
            if (idAsignacionevaluacion != null) {
                idAsignacionevaluacion.getResultadoEvaluacionList().remove(resultadoEvaluacion);
                idAsignacionevaluacion = em.merge(idAsignacionevaluacion);
            }
            Alumno matricula = resultadoEvaluacion.getMatricula();
            if (matricula != null) {
                matricula.getResultadoEvaluacionList().remove(resultadoEvaluacion);
                matricula = em.merge(matricula);
            }
            List<RespuestaEvaluacion> respuestaEvaluacionList = resultadoEvaluacion.getRespuestaEvaluacionList();
            for (RespuestaEvaluacion respuestaEvaluacionListRespuestaEvaluacion : respuestaEvaluacionList) {
                respuestaEvaluacionListRespuestaEvaluacion.setIdResultadoevaluacion(null);
                respuestaEvaluacionListRespuestaEvaluacion = em.merge(respuestaEvaluacionListRespuestaEvaluacion);
            }
            em.remove(resultadoEvaluacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ResultadoEvaluacion> findResultadoEvaluacionEntities() {
        return findResultadoEvaluacionEntities(true, -1, -1);
    }

    public List<ResultadoEvaluacion> findResultadoEvaluacionEntities(int maxResults, int firstResult) {
        return findResultadoEvaluacionEntities(false, maxResults, firstResult);
    }

    private List<ResultadoEvaluacion> findResultadoEvaluacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ResultadoEvaluacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ResultadoEvaluacion findResultadoEvaluacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ResultadoEvaluacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getResultadoEvaluacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ResultadoEvaluacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
