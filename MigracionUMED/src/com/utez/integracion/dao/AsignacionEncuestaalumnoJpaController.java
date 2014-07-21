/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.AsignacionEncuestaalumno;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Encuesta;
import com.utez.secretariaAcademica.entity.Alumno;
import com.utez.integracion.entity.RespuestaEncuesta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionEncuestaalumnoJpaController implements Serializable {

    public AsignacionEncuestaalumnoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionEncuestaalumno asignacionEncuestaalumno) {
        if (asignacionEncuestaalumno.getRespuestaEncuestaList() == null) {
            asignacionEncuestaalumno.setRespuestaEncuestaList(new ArrayList<RespuestaEncuesta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Encuesta idEncuesta = asignacionEncuestaalumno.getIdEncuesta();
            if (idEncuesta != null) {
                idEncuesta = em.getReference(idEncuesta.getClass(), idEncuesta.getIdEncuesta());
                asignacionEncuestaalumno.setIdEncuesta(idEncuesta);
            }
            Alumno matricula = asignacionEncuestaalumno.getMatricula();
            if (matricula != null) {
                matricula = em.getReference(matricula.getClass(), matricula.getMatricula());
                asignacionEncuestaalumno.setMatricula(matricula);
            }
            List<RespuestaEncuesta> attachedRespuestaEncuestaList = new ArrayList<RespuestaEncuesta>();
            for (RespuestaEncuesta respuestaEncuestaListRespuestaEncuestaToAttach : asignacionEncuestaalumno.getRespuestaEncuestaList()) {
                respuestaEncuestaListRespuestaEncuestaToAttach = em.getReference(respuestaEncuestaListRespuestaEncuestaToAttach.getClass(), respuestaEncuestaListRespuestaEncuestaToAttach.getIdRespuestaencuesta());
                attachedRespuestaEncuestaList.add(respuestaEncuestaListRespuestaEncuestaToAttach);
            }
            asignacionEncuestaalumno.setRespuestaEncuestaList(attachedRespuestaEncuestaList);
            em.persist(asignacionEncuestaalumno);
            if (idEncuesta != null) {
                idEncuesta.getAsignacionEncuestaalumnoList().add(asignacionEncuestaalumno);
                idEncuesta = em.merge(idEncuesta);
            }
            if (matricula != null) {
                matricula.getAsignacionEncuestaalumnoList().add(asignacionEncuestaalumno);
                matricula = em.merge(matricula);
            }
            for (RespuestaEncuesta respuestaEncuestaListRespuestaEncuesta : asignacionEncuestaalumno.getRespuestaEncuestaList()) {
                AsignacionEncuestaalumno oldIdAsignacionencuestaOfRespuestaEncuestaListRespuestaEncuesta = respuestaEncuestaListRespuestaEncuesta.getIdAsignacionencuesta();
                respuestaEncuestaListRespuestaEncuesta.setIdAsignacionencuesta(asignacionEncuestaalumno);
                respuestaEncuestaListRespuestaEncuesta = em.merge(respuestaEncuestaListRespuestaEncuesta);
                if (oldIdAsignacionencuestaOfRespuestaEncuestaListRespuestaEncuesta != null) {
                    oldIdAsignacionencuestaOfRespuestaEncuestaListRespuestaEncuesta.getRespuestaEncuestaList().remove(respuestaEncuestaListRespuestaEncuesta);
                    oldIdAsignacionencuestaOfRespuestaEncuestaListRespuestaEncuesta = em.merge(oldIdAsignacionencuestaOfRespuestaEncuestaListRespuestaEncuesta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionEncuestaalumno asignacionEncuestaalumno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionEncuestaalumno persistentAsignacionEncuestaalumno = em.find(AsignacionEncuestaalumno.class, asignacionEncuestaalumno.getIdAsignacionencuestaalumno());
            Encuesta idEncuestaOld = persistentAsignacionEncuestaalumno.getIdEncuesta();
            Encuesta idEncuestaNew = asignacionEncuestaalumno.getIdEncuesta();
            Alumno matriculaOld = persistentAsignacionEncuestaalumno.getMatricula();
            Alumno matriculaNew = asignacionEncuestaalumno.getMatricula();
            List<RespuestaEncuesta> respuestaEncuestaListOld = persistentAsignacionEncuestaalumno.getRespuestaEncuestaList();
            List<RespuestaEncuesta> respuestaEncuestaListNew = asignacionEncuestaalumno.getRespuestaEncuestaList();
            if (idEncuestaNew != null) {
                idEncuestaNew = em.getReference(idEncuestaNew.getClass(), idEncuestaNew.getIdEncuesta());
                asignacionEncuestaalumno.setIdEncuesta(idEncuestaNew);
            }
            if (matriculaNew != null) {
                matriculaNew = em.getReference(matriculaNew.getClass(), matriculaNew.getMatricula());
                asignacionEncuestaalumno.setMatricula(matriculaNew);
            }
            List<RespuestaEncuesta> attachedRespuestaEncuestaListNew = new ArrayList<RespuestaEncuesta>();
            for (RespuestaEncuesta respuestaEncuestaListNewRespuestaEncuestaToAttach : respuestaEncuestaListNew) {
                respuestaEncuestaListNewRespuestaEncuestaToAttach = em.getReference(respuestaEncuestaListNewRespuestaEncuestaToAttach.getClass(), respuestaEncuestaListNewRespuestaEncuestaToAttach.getIdRespuestaencuesta());
                attachedRespuestaEncuestaListNew.add(respuestaEncuestaListNewRespuestaEncuestaToAttach);
            }
            respuestaEncuestaListNew = attachedRespuestaEncuestaListNew;
            asignacionEncuestaalumno.setRespuestaEncuestaList(respuestaEncuestaListNew);
            asignacionEncuestaalumno = em.merge(asignacionEncuestaalumno);
            if (idEncuestaOld != null && !idEncuestaOld.equals(idEncuestaNew)) {
                idEncuestaOld.getAsignacionEncuestaalumnoList().remove(asignacionEncuestaalumno);
                idEncuestaOld = em.merge(idEncuestaOld);
            }
            if (idEncuestaNew != null && !idEncuestaNew.equals(idEncuestaOld)) {
                idEncuestaNew.getAsignacionEncuestaalumnoList().add(asignacionEncuestaalumno);
                idEncuestaNew = em.merge(idEncuestaNew);
            }
            if (matriculaOld != null && !matriculaOld.equals(matriculaNew)) {
                matriculaOld.getAsignacionEncuestaalumnoList().remove(asignacionEncuestaalumno);
                matriculaOld = em.merge(matriculaOld);
            }
            if (matriculaNew != null && !matriculaNew.equals(matriculaOld)) {
                matriculaNew.getAsignacionEncuestaalumnoList().add(asignacionEncuestaalumno);
                matriculaNew = em.merge(matriculaNew);
            }
            for (RespuestaEncuesta respuestaEncuestaListOldRespuestaEncuesta : respuestaEncuestaListOld) {
                if (!respuestaEncuestaListNew.contains(respuestaEncuestaListOldRespuestaEncuesta)) {
                    respuestaEncuestaListOldRespuestaEncuesta.setIdAsignacionencuesta(null);
                    respuestaEncuestaListOldRespuestaEncuesta = em.merge(respuestaEncuestaListOldRespuestaEncuesta);
                }
            }
            for (RespuestaEncuesta respuestaEncuestaListNewRespuestaEncuesta : respuestaEncuestaListNew) {
                if (!respuestaEncuestaListOld.contains(respuestaEncuestaListNewRespuestaEncuesta)) {
                    AsignacionEncuestaalumno oldIdAsignacionencuestaOfRespuestaEncuestaListNewRespuestaEncuesta = respuestaEncuestaListNewRespuestaEncuesta.getIdAsignacionencuesta();
                    respuestaEncuestaListNewRespuestaEncuesta.setIdAsignacionencuesta(asignacionEncuestaalumno);
                    respuestaEncuestaListNewRespuestaEncuesta = em.merge(respuestaEncuestaListNewRespuestaEncuesta);
                    if (oldIdAsignacionencuestaOfRespuestaEncuestaListNewRespuestaEncuesta != null && !oldIdAsignacionencuestaOfRespuestaEncuestaListNewRespuestaEncuesta.equals(asignacionEncuestaalumno)) {
                        oldIdAsignacionencuestaOfRespuestaEncuestaListNewRespuestaEncuesta.getRespuestaEncuestaList().remove(respuestaEncuestaListNewRespuestaEncuesta);
                        oldIdAsignacionencuestaOfRespuestaEncuestaListNewRespuestaEncuesta = em.merge(oldIdAsignacionencuestaOfRespuestaEncuestaListNewRespuestaEncuesta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionEncuestaalumno.getIdAsignacionencuestaalumno();
                if (findAsignacionEncuestaalumno(id) == null) {
                    throw new NonexistentEntityException("The asignacionEncuestaalumno with id " + id + " no longer exists.");
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
            AsignacionEncuestaalumno asignacionEncuestaalumno;
            try {
                asignacionEncuestaalumno = em.getReference(AsignacionEncuestaalumno.class, id);
                asignacionEncuestaalumno.getIdAsignacionencuestaalumno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionEncuestaalumno with id " + id + " no longer exists.", enfe);
            }
            Encuesta idEncuesta = asignacionEncuestaalumno.getIdEncuesta();
            if (idEncuesta != null) {
                idEncuesta.getAsignacionEncuestaalumnoList().remove(asignacionEncuestaalumno);
                idEncuesta = em.merge(idEncuesta);
            }
            Alumno matricula = asignacionEncuestaalumno.getMatricula();
            if (matricula != null) {
                matricula.getAsignacionEncuestaalumnoList().remove(asignacionEncuestaalumno);
                matricula = em.merge(matricula);
            }
            List<RespuestaEncuesta> respuestaEncuestaList = asignacionEncuestaalumno.getRespuestaEncuestaList();
            for (RespuestaEncuesta respuestaEncuestaListRespuestaEncuesta : respuestaEncuestaList) {
                respuestaEncuestaListRespuestaEncuesta.setIdAsignacionencuesta(null);
                respuestaEncuestaListRespuestaEncuesta = em.merge(respuestaEncuestaListRespuestaEncuesta);
            }
            em.remove(asignacionEncuestaalumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionEncuestaalumno> findAsignacionEncuestaalumnoEntities() {
        return findAsignacionEncuestaalumnoEntities(true, -1, -1);
    }

    public List<AsignacionEncuestaalumno> findAsignacionEncuestaalumnoEntities(int maxResults, int firstResult) {
        return findAsignacionEncuestaalumnoEntities(false, maxResults, firstResult);
    }

    private List<AsignacionEncuestaalumno> findAsignacionEncuestaalumnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionEncuestaalumno as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionEncuestaalumno findAsignacionEncuestaalumno(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionEncuestaalumno.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionEncuestaalumnoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionEncuestaalumno as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
