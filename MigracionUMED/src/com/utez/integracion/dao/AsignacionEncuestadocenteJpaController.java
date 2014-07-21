/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.AsignacionEncuestadocente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.FechaExamenprogramado;
import com.utez.integracion.entity.Encuesta;
import com.utez.secretariaAcademica.entity.Alumno;
import com.utez.integracion.entity.RespuestaEncuestadocente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionEncuestadocenteJpaController implements Serializable {

    public AsignacionEncuestadocenteJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionEncuestadocente asignacionEncuestadocente) {
        if (asignacionEncuestadocente.getRespuestaEncuestadocenteList() == null) {
            asignacionEncuestadocente.setRespuestaEncuestadocenteList(new ArrayList<RespuestaEncuestadocente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FechaExamenprogramado idFechaexamen = asignacionEncuestadocente.getIdFechaexamen();
            if (idFechaexamen != null) {
                idFechaexamen = em.getReference(idFechaexamen.getClass(), idFechaexamen.getIdFechaExamen());
                asignacionEncuestadocente.setIdFechaexamen(idFechaexamen);
            }
            Encuesta idEncuesta = asignacionEncuestadocente.getIdEncuesta();
            if (idEncuesta != null) {
                idEncuesta = em.getReference(idEncuesta.getClass(), idEncuesta.getIdEncuesta());
                asignacionEncuestadocente.setIdEncuesta(idEncuesta);
            }
            Alumno matricula = asignacionEncuestadocente.getMatricula();
            if (matricula != null) {
                matricula = em.getReference(matricula.getClass(), matricula.getMatricula());
                asignacionEncuestadocente.setMatricula(matricula);
            }
            List<RespuestaEncuestadocente> attachedRespuestaEncuestadocenteList = new ArrayList<RespuestaEncuestadocente>();
            for (RespuestaEncuestadocente respuestaEncuestadocenteListRespuestaEncuestadocenteToAttach : asignacionEncuestadocente.getRespuestaEncuestadocenteList()) {
                respuestaEncuestadocenteListRespuestaEncuestadocenteToAttach = em.getReference(respuestaEncuestadocenteListRespuestaEncuestadocenteToAttach.getClass(), respuestaEncuestadocenteListRespuestaEncuestadocenteToAttach.getIdRespuestaencuestadocente());
                attachedRespuestaEncuestadocenteList.add(respuestaEncuestadocenteListRespuestaEncuestadocenteToAttach);
            }
            asignacionEncuestadocente.setRespuestaEncuestadocenteList(attachedRespuestaEncuestadocenteList);
            em.persist(asignacionEncuestadocente);
            if (idFechaexamen != null) {
                idFechaexamen.getAsignacionEncuestadocenteList().add(asignacionEncuestadocente);
                idFechaexamen = em.merge(idFechaexamen);
            }
            if (idEncuesta != null) {
                idEncuesta.getAsignacionEncuestadocenteList().add(asignacionEncuestadocente);
                idEncuesta = em.merge(idEncuesta);
            }
            if (matricula != null) {
                matricula.getAsignacionEncuestadocenteList().add(asignacionEncuestadocente);
                matricula = em.merge(matricula);
            }
            for (RespuestaEncuestadocente respuestaEncuestadocenteListRespuestaEncuestadocente : asignacionEncuestadocente.getRespuestaEncuestadocenteList()) {
                AsignacionEncuestadocente oldIdAsignacionencuestadocenteOfRespuestaEncuestadocenteListRespuestaEncuestadocente = respuestaEncuestadocenteListRespuestaEncuestadocente.getIdAsignacionencuestadocente();
                respuestaEncuestadocenteListRespuestaEncuestadocente.setIdAsignacionencuestadocente(asignacionEncuestadocente);
                respuestaEncuestadocenteListRespuestaEncuestadocente = em.merge(respuestaEncuestadocenteListRespuestaEncuestadocente);
                if (oldIdAsignacionencuestadocenteOfRespuestaEncuestadocenteListRespuestaEncuestadocente != null) {
                    oldIdAsignacionencuestadocenteOfRespuestaEncuestadocenteListRespuestaEncuestadocente.getRespuestaEncuestadocenteList().remove(respuestaEncuestadocenteListRespuestaEncuestadocente);
                    oldIdAsignacionencuestadocenteOfRespuestaEncuestadocenteListRespuestaEncuestadocente = em.merge(oldIdAsignacionencuestadocenteOfRespuestaEncuestadocenteListRespuestaEncuestadocente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionEncuestadocente asignacionEncuestadocente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionEncuestadocente persistentAsignacionEncuestadocente = em.find(AsignacionEncuestadocente.class, asignacionEncuestadocente.getIdAsignacionencuestadocente());
            FechaExamenprogramado idFechaexamenOld = persistentAsignacionEncuestadocente.getIdFechaexamen();
            FechaExamenprogramado idFechaexamenNew = asignacionEncuestadocente.getIdFechaexamen();
            Encuesta idEncuestaOld = persistentAsignacionEncuestadocente.getIdEncuesta();
            Encuesta idEncuestaNew = asignacionEncuestadocente.getIdEncuesta();
            Alumno matriculaOld = persistentAsignacionEncuestadocente.getMatricula();
            Alumno matriculaNew = asignacionEncuestadocente.getMatricula();
            List<RespuestaEncuestadocente> respuestaEncuestadocenteListOld = persistentAsignacionEncuestadocente.getRespuestaEncuestadocenteList();
            List<RespuestaEncuestadocente> respuestaEncuestadocenteListNew = asignacionEncuestadocente.getRespuestaEncuestadocenteList();
            if (idFechaexamenNew != null) {
                idFechaexamenNew = em.getReference(idFechaexamenNew.getClass(), idFechaexamenNew.getIdFechaExamen());
                asignacionEncuestadocente.setIdFechaexamen(idFechaexamenNew);
            }
            if (idEncuestaNew != null) {
                idEncuestaNew = em.getReference(idEncuestaNew.getClass(), idEncuestaNew.getIdEncuesta());
                asignacionEncuestadocente.setIdEncuesta(idEncuestaNew);
            }
            if (matriculaNew != null) {
                matriculaNew = em.getReference(matriculaNew.getClass(), matriculaNew.getMatricula());
                asignacionEncuestadocente.setMatricula(matriculaNew);
            }
            List<RespuestaEncuestadocente> attachedRespuestaEncuestadocenteListNew = new ArrayList<RespuestaEncuestadocente>();
            for (RespuestaEncuestadocente respuestaEncuestadocenteListNewRespuestaEncuestadocenteToAttach : respuestaEncuestadocenteListNew) {
                respuestaEncuestadocenteListNewRespuestaEncuestadocenteToAttach = em.getReference(respuestaEncuestadocenteListNewRespuestaEncuestadocenteToAttach.getClass(), respuestaEncuestadocenteListNewRespuestaEncuestadocenteToAttach.getIdRespuestaencuestadocente());
                attachedRespuestaEncuestadocenteListNew.add(respuestaEncuestadocenteListNewRespuestaEncuestadocenteToAttach);
            }
            respuestaEncuestadocenteListNew = attachedRespuestaEncuestadocenteListNew;
            asignacionEncuestadocente.setRespuestaEncuestadocenteList(respuestaEncuestadocenteListNew);
            asignacionEncuestadocente = em.merge(asignacionEncuestadocente);
            if (idFechaexamenOld != null && !idFechaexamenOld.equals(idFechaexamenNew)) {
                idFechaexamenOld.getAsignacionEncuestadocenteList().remove(asignacionEncuestadocente);
                idFechaexamenOld = em.merge(idFechaexamenOld);
            }
            if (idFechaexamenNew != null && !idFechaexamenNew.equals(idFechaexamenOld)) {
                idFechaexamenNew.getAsignacionEncuestadocenteList().add(asignacionEncuestadocente);
                idFechaexamenNew = em.merge(idFechaexamenNew);
            }
            if (idEncuestaOld != null && !idEncuestaOld.equals(idEncuestaNew)) {
                idEncuestaOld.getAsignacionEncuestadocenteList().remove(asignacionEncuestadocente);
                idEncuestaOld = em.merge(idEncuestaOld);
            }
            if (idEncuestaNew != null && !idEncuestaNew.equals(idEncuestaOld)) {
                idEncuestaNew.getAsignacionEncuestadocenteList().add(asignacionEncuestadocente);
                idEncuestaNew = em.merge(idEncuestaNew);
            }
            if (matriculaOld != null && !matriculaOld.equals(matriculaNew)) {
                matriculaOld.getAsignacionEncuestadocenteList().remove(asignacionEncuestadocente);
                matriculaOld = em.merge(matriculaOld);
            }
            if (matriculaNew != null && !matriculaNew.equals(matriculaOld)) {
                matriculaNew.getAsignacionEncuestadocenteList().add(asignacionEncuestadocente);
                matriculaNew = em.merge(matriculaNew);
            }
            for (RespuestaEncuestadocente respuestaEncuestadocenteListOldRespuestaEncuestadocente : respuestaEncuestadocenteListOld) {
                if (!respuestaEncuestadocenteListNew.contains(respuestaEncuestadocenteListOldRespuestaEncuestadocente)) {
                    respuestaEncuestadocenteListOldRespuestaEncuestadocente.setIdAsignacionencuestadocente(null);
                    respuestaEncuestadocenteListOldRespuestaEncuestadocente = em.merge(respuestaEncuestadocenteListOldRespuestaEncuestadocente);
                }
            }
            for (RespuestaEncuestadocente respuestaEncuestadocenteListNewRespuestaEncuestadocente : respuestaEncuestadocenteListNew) {
                if (!respuestaEncuestadocenteListOld.contains(respuestaEncuestadocenteListNewRespuestaEncuestadocente)) {
                    AsignacionEncuestadocente oldIdAsignacionencuestadocenteOfRespuestaEncuestadocenteListNewRespuestaEncuestadocente = respuestaEncuestadocenteListNewRespuestaEncuestadocente.getIdAsignacionencuestadocente();
                    respuestaEncuestadocenteListNewRespuestaEncuestadocente.setIdAsignacionencuestadocente(asignacionEncuestadocente);
                    respuestaEncuestadocenteListNewRespuestaEncuestadocente = em.merge(respuestaEncuestadocenteListNewRespuestaEncuestadocente);
                    if (oldIdAsignacionencuestadocenteOfRespuestaEncuestadocenteListNewRespuestaEncuestadocente != null && !oldIdAsignacionencuestadocenteOfRespuestaEncuestadocenteListNewRespuestaEncuestadocente.equals(asignacionEncuestadocente)) {
                        oldIdAsignacionencuestadocenteOfRespuestaEncuestadocenteListNewRespuestaEncuestadocente.getRespuestaEncuestadocenteList().remove(respuestaEncuestadocenteListNewRespuestaEncuestadocente);
                        oldIdAsignacionencuestadocenteOfRespuestaEncuestadocenteListNewRespuestaEncuestadocente = em.merge(oldIdAsignacionencuestadocenteOfRespuestaEncuestadocenteListNewRespuestaEncuestadocente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionEncuestadocente.getIdAsignacionencuestadocente();
                if (findAsignacionEncuestadocente(id) == null) {
                    throw new NonexistentEntityException("The asignacionEncuestadocente with id " + id + " no longer exists.");
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
            AsignacionEncuestadocente asignacionEncuestadocente;
            try {
                asignacionEncuestadocente = em.getReference(AsignacionEncuestadocente.class, id);
                asignacionEncuestadocente.getIdAsignacionencuestadocente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionEncuestadocente with id " + id + " no longer exists.", enfe);
            }
            FechaExamenprogramado idFechaexamen = asignacionEncuestadocente.getIdFechaexamen();
            if (idFechaexamen != null) {
                idFechaexamen.getAsignacionEncuestadocenteList().remove(asignacionEncuestadocente);
                idFechaexamen = em.merge(idFechaexamen);
            }
            Encuesta idEncuesta = asignacionEncuestadocente.getIdEncuesta();
            if (idEncuesta != null) {
                idEncuesta.getAsignacionEncuestadocenteList().remove(asignacionEncuestadocente);
                idEncuesta = em.merge(idEncuesta);
            }
            Alumno matricula = asignacionEncuestadocente.getMatricula();
            if (matricula != null) {
                matricula.getAsignacionEncuestadocenteList().remove(asignacionEncuestadocente);
                matricula = em.merge(matricula);
            }
            List<RespuestaEncuestadocente> respuestaEncuestadocenteList = asignacionEncuestadocente.getRespuestaEncuestadocenteList();
            for (RespuestaEncuestadocente respuestaEncuestadocenteListRespuestaEncuestadocente : respuestaEncuestadocenteList) {
                respuestaEncuestadocenteListRespuestaEncuestadocente.setIdAsignacionencuestadocente(null);
                respuestaEncuestadocenteListRespuestaEncuestadocente = em.merge(respuestaEncuestadocenteListRespuestaEncuestadocente);
            }
            em.remove(asignacionEncuestadocente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionEncuestadocente> findAsignacionEncuestadocenteEntities() {
        return findAsignacionEncuestadocenteEntities(true, -1, -1);
    }

    public List<AsignacionEncuestadocente> findAsignacionEncuestadocenteEntities(int maxResults, int firstResult) {
        return findAsignacionEncuestadocenteEntities(false, maxResults, firstResult);
    }

    private List<AsignacionEncuestadocente> findAsignacionEncuestadocenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionEncuestadocente as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionEncuestadocente findAsignacionEncuestadocente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionEncuestadocente.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionEncuestadocenteCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionEncuestadocente as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
