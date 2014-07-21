/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.AsignacionAsignaturabanco;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.BancoReactivo;
import com.utez.secretariaAcademica.entity.Asignatura;
import com.utez.integracion.entity.AsignacionEvaluacion;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.ExamenImpreso;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionAsignaturabancoJpaController implements Serializable {

    public AsignacionAsignaturabancoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionAsignaturabanco asignacionAsignaturabanco) {
        if (asignacionAsignaturabanco.getAsignacionEvaluacionList() == null) {
            asignacionAsignaturabanco.setAsignacionEvaluacionList(new ArrayList<AsignacionEvaluacion>());
        }
        if (asignacionAsignaturabanco.getExamenImpresoList() == null) {
            asignacionAsignaturabanco.setExamenImpresoList(new ArrayList<ExamenImpreso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BancoReactivo idBancoreactivo = asignacionAsignaturabanco.getIdBancoreactivo();
            if (idBancoreactivo != null) {
                idBancoreactivo = em.getReference(idBancoreactivo.getClass(), idBancoreactivo.getIdBancoreactivo());
                asignacionAsignaturabanco.setIdBancoreactivo(idBancoreactivo);
            }
            Asignatura idAsignatura = asignacionAsignaturabanco.getIdAsignatura();
            if (idAsignatura != null) {
                idAsignatura = em.getReference(idAsignatura.getClass(), idAsignatura.getIdasignatura());
                asignacionAsignaturabanco.setIdAsignatura(idAsignatura);
            }
            List<AsignacionEvaluacion> attachedAsignacionEvaluacionList = new ArrayList<AsignacionEvaluacion>();
            for (AsignacionEvaluacion asignacionEvaluacionListAsignacionEvaluacionToAttach : asignacionAsignaturabanco.getAsignacionEvaluacionList()) {
                asignacionEvaluacionListAsignacionEvaluacionToAttach = em.getReference(asignacionEvaluacionListAsignacionEvaluacionToAttach.getClass(), asignacionEvaluacionListAsignacionEvaluacionToAttach.getIdAsignacionevaluacion());
                attachedAsignacionEvaluacionList.add(asignacionEvaluacionListAsignacionEvaluacionToAttach);
            }
            asignacionAsignaturabanco.setAsignacionEvaluacionList(attachedAsignacionEvaluacionList);
            List<ExamenImpreso> attachedExamenImpresoList = new ArrayList<ExamenImpreso>();
            for (ExamenImpreso examenImpresoListExamenImpresoToAttach : asignacionAsignaturabanco.getExamenImpresoList()) {
                examenImpresoListExamenImpresoToAttach = em.getReference(examenImpresoListExamenImpresoToAttach.getClass(), examenImpresoListExamenImpresoToAttach.getIdExamenimpreso());
                attachedExamenImpresoList.add(examenImpresoListExamenImpresoToAttach);
            }
            asignacionAsignaturabanco.setExamenImpresoList(attachedExamenImpresoList);
            em.persist(asignacionAsignaturabanco);
            if (idBancoreactivo != null) {
                idBancoreactivo.getAsignacionAsignaturabancoList().add(asignacionAsignaturabanco);
                idBancoreactivo = em.merge(idBancoreactivo);
            }
            if (idAsignatura != null) {
                idAsignatura.getAsignacionAsignaturabancoList().add(asignacionAsignaturabanco);
                idAsignatura = em.merge(idAsignatura);
            }
            for (AsignacionEvaluacion asignacionEvaluacionListAsignacionEvaluacion : asignacionAsignaturabanco.getAsignacionEvaluacionList()) {
                AsignacionAsignaturabanco oldIdAsignacionasignaturabancoOfAsignacionEvaluacionListAsignacionEvaluacion = asignacionEvaluacionListAsignacionEvaluacion.getIdAsignacionasignaturabanco();
                asignacionEvaluacionListAsignacionEvaluacion.setIdAsignacionasignaturabanco(asignacionAsignaturabanco);
                asignacionEvaluacionListAsignacionEvaluacion = em.merge(asignacionEvaluacionListAsignacionEvaluacion);
                if (oldIdAsignacionasignaturabancoOfAsignacionEvaluacionListAsignacionEvaluacion != null) {
                    oldIdAsignacionasignaturabancoOfAsignacionEvaluacionListAsignacionEvaluacion.getAsignacionEvaluacionList().remove(asignacionEvaluacionListAsignacionEvaluacion);
                    oldIdAsignacionasignaturabancoOfAsignacionEvaluacionListAsignacionEvaluacion = em.merge(oldIdAsignacionasignaturabancoOfAsignacionEvaluacionListAsignacionEvaluacion);
                }
            }
            for (ExamenImpreso examenImpresoListExamenImpreso : asignacionAsignaturabanco.getExamenImpresoList()) {
                AsignacionAsignaturabanco oldIdAsignacionasignaturabancoOfExamenImpresoListExamenImpreso = examenImpresoListExamenImpreso.getIdAsignacionasignaturabanco();
                examenImpresoListExamenImpreso.setIdAsignacionasignaturabanco(asignacionAsignaturabanco);
                examenImpresoListExamenImpreso = em.merge(examenImpresoListExamenImpreso);
                if (oldIdAsignacionasignaturabancoOfExamenImpresoListExamenImpreso != null) {
                    oldIdAsignacionasignaturabancoOfExamenImpresoListExamenImpreso.getExamenImpresoList().remove(examenImpresoListExamenImpreso);
                    oldIdAsignacionasignaturabancoOfExamenImpresoListExamenImpreso = em.merge(oldIdAsignacionasignaturabancoOfExamenImpresoListExamenImpreso);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionAsignaturabanco asignacionAsignaturabanco) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionAsignaturabanco persistentAsignacionAsignaturabanco = em.find(AsignacionAsignaturabanco.class, asignacionAsignaturabanco.getIdAsignacionasignaturabanco());
            BancoReactivo idBancoreactivoOld = persistentAsignacionAsignaturabanco.getIdBancoreactivo();
            BancoReactivo idBancoreactivoNew = asignacionAsignaturabanco.getIdBancoreactivo();
            Asignatura idAsignaturaOld = persistentAsignacionAsignaturabanco.getIdAsignatura();
            Asignatura idAsignaturaNew = asignacionAsignaturabanco.getIdAsignatura();
            List<AsignacionEvaluacion> asignacionEvaluacionListOld = persistentAsignacionAsignaturabanco.getAsignacionEvaluacionList();
            List<AsignacionEvaluacion> asignacionEvaluacionListNew = asignacionAsignaturabanco.getAsignacionEvaluacionList();
            List<ExamenImpreso> examenImpresoListOld = persistentAsignacionAsignaturabanco.getExamenImpresoList();
            List<ExamenImpreso> examenImpresoListNew = asignacionAsignaturabanco.getExamenImpresoList();
            if (idBancoreactivoNew != null) {
                idBancoreactivoNew = em.getReference(idBancoreactivoNew.getClass(), idBancoreactivoNew.getIdBancoreactivo());
                asignacionAsignaturabanco.setIdBancoreactivo(idBancoreactivoNew);
            }
            if (idAsignaturaNew != null) {
                idAsignaturaNew = em.getReference(idAsignaturaNew.getClass(), idAsignaturaNew.getIdasignatura());
                asignacionAsignaturabanco.setIdAsignatura(idAsignaturaNew);
            }
            List<AsignacionEvaluacion> attachedAsignacionEvaluacionListNew = new ArrayList<AsignacionEvaluacion>();
            for (AsignacionEvaluacion asignacionEvaluacionListNewAsignacionEvaluacionToAttach : asignacionEvaluacionListNew) {
                asignacionEvaluacionListNewAsignacionEvaluacionToAttach = em.getReference(asignacionEvaluacionListNewAsignacionEvaluacionToAttach.getClass(), asignacionEvaluacionListNewAsignacionEvaluacionToAttach.getIdAsignacionevaluacion());
                attachedAsignacionEvaluacionListNew.add(asignacionEvaluacionListNewAsignacionEvaluacionToAttach);
            }
            asignacionEvaluacionListNew = attachedAsignacionEvaluacionListNew;
            asignacionAsignaturabanco.setAsignacionEvaluacionList(asignacionEvaluacionListNew);
            List<ExamenImpreso> attachedExamenImpresoListNew = new ArrayList<ExamenImpreso>();
            for (ExamenImpreso examenImpresoListNewExamenImpresoToAttach : examenImpresoListNew) {
                examenImpresoListNewExamenImpresoToAttach = em.getReference(examenImpresoListNewExamenImpresoToAttach.getClass(), examenImpresoListNewExamenImpresoToAttach.getIdExamenimpreso());
                attachedExamenImpresoListNew.add(examenImpresoListNewExamenImpresoToAttach);
            }
            examenImpresoListNew = attachedExamenImpresoListNew;
            asignacionAsignaturabanco.setExamenImpresoList(examenImpresoListNew);
            asignacionAsignaturabanco = em.merge(asignacionAsignaturabanco);
            if (idBancoreactivoOld != null && !idBancoreactivoOld.equals(idBancoreactivoNew)) {
                idBancoreactivoOld.getAsignacionAsignaturabancoList().remove(asignacionAsignaturabanco);
                idBancoreactivoOld = em.merge(idBancoreactivoOld);
            }
            if (idBancoreactivoNew != null && !idBancoreactivoNew.equals(idBancoreactivoOld)) {
                idBancoreactivoNew.getAsignacionAsignaturabancoList().add(asignacionAsignaturabanco);
                idBancoreactivoNew = em.merge(idBancoreactivoNew);
            }
            if (idAsignaturaOld != null && !idAsignaturaOld.equals(idAsignaturaNew)) {
                idAsignaturaOld.getAsignacionAsignaturabancoList().remove(asignacionAsignaturabanco);
                idAsignaturaOld = em.merge(idAsignaturaOld);
            }
            if (idAsignaturaNew != null && !idAsignaturaNew.equals(idAsignaturaOld)) {
                idAsignaturaNew.getAsignacionAsignaturabancoList().add(asignacionAsignaturabanco);
                idAsignaturaNew = em.merge(idAsignaturaNew);
            }
            for (AsignacionEvaluacion asignacionEvaluacionListOldAsignacionEvaluacion : asignacionEvaluacionListOld) {
                if (!asignacionEvaluacionListNew.contains(asignacionEvaluacionListOldAsignacionEvaluacion)) {
                    asignacionEvaluacionListOldAsignacionEvaluacion.setIdAsignacionasignaturabanco(null);
                    asignacionEvaluacionListOldAsignacionEvaluacion = em.merge(asignacionEvaluacionListOldAsignacionEvaluacion);
                }
            }
            for (AsignacionEvaluacion asignacionEvaluacionListNewAsignacionEvaluacion : asignacionEvaluacionListNew) {
                if (!asignacionEvaluacionListOld.contains(asignacionEvaluacionListNewAsignacionEvaluacion)) {
                    AsignacionAsignaturabanco oldIdAsignacionasignaturabancoOfAsignacionEvaluacionListNewAsignacionEvaluacion = asignacionEvaluacionListNewAsignacionEvaluacion.getIdAsignacionasignaturabanco();
                    asignacionEvaluacionListNewAsignacionEvaluacion.setIdAsignacionasignaturabanco(asignacionAsignaturabanco);
                    asignacionEvaluacionListNewAsignacionEvaluacion = em.merge(asignacionEvaluacionListNewAsignacionEvaluacion);
                    if (oldIdAsignacionasignaturabancoOfAsignacionEvaluacionListNewAsignacionEvaluacion != null && !oldIdAsignacionasignaturabancoOfAsignacionEvaluacionListNewAsignacionEvaluacion.equals(asignacionAsignaturabanco)) {
                        oldIdAsignacionasignaturabancoOfAsignacionEvaluacionListNewAsignacionEvaluacion.getAsignacionEvaluacionList().remove(asignacionEvaluacionListNewAsignacionEvaluacion);
                        oldIdAsignacionasignaturabancoOfAsignacionEvaluacionListNewAsignacionEvaluacion = em.merge(oldIdAsignacionasignaturabancoOfAsignacionEvaluacionListNewAsignacionEvaluacion);
                    }
                }
            }
            for (ExamenImpreso examenImpresoListOldExamenImpreso : examenImpresoListOld) {
                if (!examenImpresoListNew.contains(examenImpresoListOldExamenImpreso)) {
                    examenImpresoListOldExamenImpreso.setIdAsignacionasignaturabanco(null);
                    examenImpresoListOldExamenImpreso = em.merge(examenImpresoListOldExamenImpreso);
                }
            }
            for (ExamenImpreso examenImpresoListNewExamenImpreso : examenImpresoListNew) {
                if (!examenImpresoListOld.contains(examenImpresoListNewExamenImpreso)) {
                    AsignacionAsignaturabanco oldIdAsignacionasignaturabancoOfExamenImpresoListNewExamenImpreso = examenImpresoListNewExamenImpreso.getIdAsignacionasignaturabanco();
                    examenImpresoListNewExamenImpreso.setIdAsignacionasignaturabanco(asignacionAsignaturabanco);
                    examenImpresoListNewExamenImpreso = em.merge(examenImpresoListNewExamenImpreso);
                    if (oldIdAsignacionasignaturabancoOfExamenImpresoListNewExamenImpreso != null && !oldIdAsignacionasignaturabancoOfExamenImpresoListNewExamenImpreso.equals(asignacionAsignaturabanco)) {
                        oldIdAsignacionasignaturabancoOfExamenImpresoListNewExamenImpreso.getExamenImpresoList().remove(examenImpresoListNewExamenImpreso);
                        oldIdAsignacionasignaturabancoOfExamenImpresoListNewExamenImpreso = em.merge(oldIdAsignacionasignaturabancoOfExamenImpresoListNewExamenImpreso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionAsignaturabanco.getIdAsignacionasignaturabanco();
                if (findAsignacionAsignaturabanco(id) == null) {
                    throw new NonexistentEntityException("The asignacionAsignaturabanco with id " + id + " no longer exists.");
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
            AsignacionAsignaturabanco asignacionAsignaturabanco;
            try {
                asignacionAsignaturabanco = em.getReference(AsignacionAsignaturabanco.class, id);
                asignacionAsignaturabanco.getIdAsignacionasignaturabanco();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionAsignaturabanco with id " + id + " no longer exists.", enfe);
            }
            BancoReactivo idBancoreactivo = asignacionAsignaturabanco.getIdBancoreactivo();
            if (idBancoreactivo != null) {
                idBancoreactivo.getAsignacionAsignaturabancoList().remove(asignacionAsignaturabanco);
                idBancoreactivo = em.merge(idBancoreactivo);
            }
            Asignatura idAsignatura = asignacionAsignaturabanco.getIdAsignatura();
            if (idAsignatura != null) {
                idAsignatura.getAsignacionAsignaturabancoList().remove(asignacionAsignaturabanco);
                idAsignatura = em.merge(idAsignatura);
            }
            List<AsignacionEvaluacion> asignacionEvaluacionList = asignacionAsignaturabanco.getAsignacionEvaluacionList();
            for (AsignacionEvaluacion asignacionEvaluacionListAsignacionEvaluacion : asignacionEvaluacionList) {
                asignacionEvaluacionListAsignacionEvaluacion.setIdAsignacionasignaturabanco(null);
                asignacionEvaluacionListAsignacionEvaluacion = em.merge(asignacionEvaluacionListAsignacionEvaluacion);
            }
            List<ExamenImpreso> examenImpresoList = asignacionAsignaturabanco.getExamenImpresoList();
            for (ExamenImpreso examenImpresoListExamenImpreso : examenImpresoList) {
                examenImpresoListExamenImpreso.setIdAsignacionasignaturabanco(null);
                examenImpresoListExamenImpreso = em.merge(examenImpresoListExamenImpreso);
            }
            em.remove(asignacionAsignaturabanco);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionAsignaturabanco> findAsignacionAsignaturabancoEntities() {
        return findAsignacionAsignaturabancoEntities(true, -1, -1);
    }

    public List<AsignacionAsignaturabanco> findAsignacionAsignaturabancoEntities(int maxResults, int firstResult) {
        return findAsignacionAsignaturabancoEntities(false, maxResults, firstResult);
    }

    private List<AsignacionAsignaturabanco> findAsignacionAsignaturabancoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionAsignaturabanco as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionAsignaturabanco findAsignacionAsignaturabanco(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionAsignaturabanco.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionAsignaturabancoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionAsignaturabanco as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
