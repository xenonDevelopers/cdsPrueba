/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoExamen;
import com.utez.integracion.entity.AsignacionAsignaturabanco;
import com.utez.integracion.entity.ExamenImpreso;
import com.utez.integracion.entity.FechaExamen;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.ReactivoExamenimpreso;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ExamenImpresoJpaController implements Serializable {

    public ExamenImpresoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ExamenImpreso examenImpreso) {
        if (examenImpreso.getFechaExamenList() == null) {
            examenImpreso.setFechaExamenList(new ArrayList<FechaExamen>());
        }
        if (examenImpreso.getReactivoExamenimpresoList() == null) {
            examenImpreso.setReactivoExamenimpresoList(new ArrayList<ReactivoExamenimpreso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoExamen idTipoexamen = examenImpreso.getIdTipoexamen();
            if (idTipoexamen != null) {
                idTipoexamen = em.getReference(idTipoexamen.getClass(), idTipoexamen.getIdTipoexamen());
                examenImpreso.setIdTipoexamen(idTipoexamen);
            }
            AsignacionAsignaturabanco idAsignacionasignaturabanco = examenImpreso.getIdAsignacionasignaturabanco();
            if (idAsignacionasignaturabanco != null) {
                idAsignacionasignaturabanco = em.getReference(idAsignacionasignaturabanco.getClass(), idAsignacionasignaturabanco.getIdAsignacionasignaturabanco());
                examenImpreso.setIdAsignacionasignaturabanco(idAsignacionasignaturabanco);
            }
            List<FechaExamen> attachedFechaExamenList = new ArrayList<FechaExamen>();
            for (FechaExamen fechaExamenListFechaExamenToAttach : examenImpreso.getFechaExamenList()) {
                fechaExamenListFechaExamenToAttach = em.getReference(fechaExamenListFechaExamenToAttach.getClass(), fechaExamenListFechaExamenToAttach.getIdFechaexamen());
                attachedFechaExamenList.add(fechaExamenListFechaExamenToAttach);
            }
            examenImpreso.setFechaExamenList(attachedFechaExamenList);
            List<ReactivoExamenimpreso> attachedReactivoExamenimpresoList = new ArrayList<ReactivoExamenimpreso>();
            for (ReactivoExamenimpreso reactivoExamenimpresoListReactivoExamenimpresoToAttach : examenImpreso.getReactivoExamenimpresoList()) {
                reactivoExamenimpresoListReactivoExamenimpresoToAttach = em.getReference(reactivoExamenimpresoListReactivoExamenimpresoToAttach.getClass(), reactivoExamenimpresoListReactivoExamenimpresoToAttach.getReactivoExamenimpresoPK());
                attachedReactivoExamenimpresoList.add(reactivoExamenimpresoListReactivoExamenimpresoToAttach);
            }
            examenImpreso.setReactivoExamenimpresoList(attachedReactivoExamenimpresoList);
            em.persist(examenImpreso);
            if (idTipoexamen != null) {
                idTipoexamen.getExamenImpresoList().add(examenImpreso);
                idTipoexamen = em.merge(idTipoexamen);
            }
            if (idAsignacionasignaturabanco != null) {
                idAsignacionasignaturabanco.getExamenImpresoList().add(examenImpreso);
                idAsignacionasignaturabanco = em.merge(idAsignacionasignaturabanco);
            }
            for (FechaExamen fechaExamenListFechaExamen : examenImpreso.getFechaExamenList()) {
                fechaExamenListFechaExamen.getExamenImpresoList().add(examenImpreso);
                fechaExamenListFechaExamen = em.merge(fechaExamenListFechaExamen);
            }
            for (ReactivoExamenimpreso reactivoExamenimpresoListReactivoExamenimpreso : examenImpreso.getReactivoExamenimpresoList()) {
                ExamenImpreso oldExamenImpresoOfReactivoExamenimpresoListReactivoExamenimpreso = reactivoExamenimpresoListReactivoExamenimpreso.getExamenImpreso();
                reactivoExamenimpresoListReactivoExamenimpreso.setExamenImpreso(examenImpreso);
                reactivoExamenimpresoListReactivoExamenimpreso = em.merge(reactivoExamenimpresoListReactivoExamenimpreso);
                if (oldExamenImpresoOfReactivoExamenimpresoListReactivoExamenimpreso != null) {
                    oldExamenImpresoOfReactivoExamenimpresoListReactivoExamenimpreso.getReactivoExamenimpresoList().remove(reactivoExamenimpresoListReactivoExamenimpreso);
                    oldExamenImpresoOfReactivoExamenimpresoListReactivoExamenimpreso = em.merge(oldExamenImpresoOfReactivoExamenimpresoListReactivoExamenimpreso);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ExamenImpreso examenImpreso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ExamenImpreso persistentExamenImpreso = em.find(ExamenImpreso.class, examenImpreso.getIdExamenimpreso());
            TipoExamen idTipoexamenOld = persistentExamenImpreso.getIdTipoexamen();
            TipoExamen idTipoexamenNew = examenImpreso.getIdTipoexamen();
            AsignacionAsignaturabanco idAsignacionasignaturabancoOld = persistentExamenImpreso.getIdAsignacionasignaturabanco();
            AsignacionAsignaturabanco idAsignacionasignaturabancoNew = examenImpreso.getIdAsignacionasignaturabanco();
            List<FechaExamen> fechaExamenListOld = persistentExamenImpreso.getFechaExamenList();
            List<FechaExamen> fechaExamenListNew = examenImpreso.getFechaExamenList();
            List<ReactivoExamenimpreso> reactivoExamenimpresoListOld = persistentExamenImpreso.getReactivoExamenimpresoList();
            List<ReactivoExamenimpreso> reactivoExamenimpresoListNew = examenImpreso.getReactivoExamenimpresoList();
            List<String> illegalOrphanMessages = null;
            for (ReactivoExamenimpreso reactivoExamenimpresoListOldReactivoExamenimpreso : reactivoExamenimpresoListOld) {
                if (!reactivoExamenimpresoListNew.contains(reactivoExamenimpresoListOldReactivoExamenimpreso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ReactivoExamenimpreso " + reactivoExamenimpresoListOldReactivoExamenimpreso + " since its examenImpreso field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTipoexamenNew != null) {
                idTipoexamenNew = em.getReference(idTipoexamenNew.getClass(), idTipoexamenNew.getIdTipoexamen());
                examenImpreso.setIdTipoexamen(idTipoexamenNew);
            }
            if (idAsignacionasignaturabancoNew != null) {
                idAsignacionasignaturabancoNew = em.getReference(idAsignacionasignaturabancoNew.getClass(), idAsignacionasignaturabancoNew.getIdAsignacionasignaturabanco());
                examenImpreso.setIdAsignacionasignaturabanco(idAsignacionasignaturabancoNew);
            }
            List<FechaExamen> attachedFechaExamenListNew = new ArrayList<FechaExamen>();
            for (FechaExamen fechaExamenListNewFechaExamenToAttach : fechaExamenListNew) {
                fechaExamenListNewFechaExamenToAttach = em.getReference(fechaExamenListNewFechaExamenToAttach.getClass(), fechaExamenListNewFechaExamenToAttach.getIdFechaexamen());
                attachedFechaExamenListNew.add(fechaExamenListNewFechaExamenToAttach);
            }
            fechaExamenListNew = attachedFechaExamenListNew;
            examenImpreso.setFechaExamenList(fechaExamenListNew);
            List<ReactivoExamenimpreso> attachedReactivoExamenimpresoListNew = new ArrayList<ReactivoExamenimpreso>();
            for (ReactivoExamenimpreso reactivoExamenimpresoListNewReactivoExamenimpresoToAttach : reactivoExamenimpresoListNew) {
                reactivoExamenimpresoListNewReactivoExamenimpresoToAttach = em.getReference(reactivoExamenimpresoListNewReactivoExamenimpresoToAttach.getClass(), reactivoExamenimpresoListNewReactivoExamenimpresoToAttach.getReactivoExamenimpresoPK());
                attachedReactivoExamenimpresoListNew.add(reactivoExamenimpresoListNewReactivoExamenimpresoToAttach);
            }
            reactivoExamenimpresoListNew = attachedReactivoExamenimpresoListNew;
            examenImpreso.setReactivoExamenimpresoList(reactivoExamenimpresoListNew);
            examenImpreso = em.merge(examenImpreso);
            if (idTipoexamenOld != null && !idTipoexamenOld.equals(idTipoexamenNew)) {
                idTipoexamenOld.getExamenImpresoList().remove(examenImpreso);
                idTipoexamenOld = em.merge(idTipoexamenOld);
            }
            if (idTipoexamenNew != null && !idTipoexamenNew.equals(idTipoexamenOld)) {
                idTipoexamenNew.getExamenImpresoList().add(examenImpreso);
                idTipoexamenNew = em.merge(idTipoexamenNew);
            }
            if (idAsignacionasignaturabancoOld != null && !idAsignacionasignaturabancoOld.equals(idAsignacionasignaturabancoNew)) {
                idAsignacionasignaturabancoOld.getExamenImpresoList().remove(examenImpreso);
                idAsignacionasignaturabancoOld = em.merge(idAsignacionasignaturabancoOld);
            }
            if (idAsignacionasignaturabancoNew != null && !idAsignacionasignaturabancoNew.equals(idAsignacionasignaturabancoOld)) {
                idAsignacionasignaturabancoNew.getExamenImpresoList().add(examenImpreso);
                idAsignacionasignaturabancoNew = em.merge(idAsignacionasignaturabancoNew);
            }
            for (FechaExamen fechaExamenListOldFechaExamen : fechaExamenListOld) {
                if (!fechaExamenListNew.contains(fechaExamenListOldFechaExamen)) {
                    fechaExamenListOldFechaExamen.getExamenImpresoList().remove(examenImpreso);
                    fechaExamenListOldFechaExamen = em.merge(fechaExamenListOldFechaExamen);
                }
            }
            for (FechaExamen fechaExamenListNewFechaExamen : fechaExamenListNew) {
                if (!fechaExamenListOld.contains(fechaExamenListNewFechaExamen)) {
                    fechaExamenListNewFechaExamen.getExamenImpresoList().add(examenImpreso);
                    fechaExamenListNewFechaExamen = em.merge(fechaExamenListNewFechaExamen);
                }
            }
            for (ReactivoExamenimpreso reactivoExamenimpresoListNewReactivoExamenimpreso : reactivoExamenimpresoListNew) {
                if (!reactivoExamenimpresoListOld.contains(reactivoExamenimpresoListNewReactivoExamenimpreso)) {
                    ExamenImpreso oldExamenImpresoOfReactivoExamenimpresoListNewReactivoExamenimpreso = reactivoExamenimpresoListNewReactivoExamenimpreso.getExamenImpreso();
                    reactivoExamenimpresoListNewReactivoExamenimpreso.setExamenImpreso(examenImpreso);
                    reactivoExamenimpresoListNewReactivoExamenimpreso = em.merge(reactivoExamenimpresoListNewReactivoExamenimpreso);
                    if (oldExamenImpresoOfReactivoExamenimpresoListNewReactivoExamenimpreso != null && !oldExamenImpresoOfReactivoExamenimpresoListNewReactivoExamenimpreso.equals(examenImpreso)) {
                        oldExamenImpresoOfReactivoExamenimpresoListNewReactivoExamenimpreso.getReactivoExamenimpresoList().remove(reactivoExamenimpresoListNewReactivoExamenimpreso);
                        oldExamenImpresoOfReactivoExamenimpresoListNewReactivoExamenimpreso = em.merge(oldExamenImpresoOfReactivoExamenimpresoListNewReactivoExamenimpreso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = examenImpreso.getIdExamenimpreso();
                if (findExamenImpreso(id) == null) {
                    throw new NonexistentEntityException("The examenImpreso with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ExamenImpreso examenImpreso;
            try {
                examenImpreso = em.getReference(ExamenImpreso.class, id);
                examenImpreso.getIdExamenimpreso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The examenImpreso with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ReactivoExamenimpreso> reactivoExamenimpresoListOrphanCheck = examenImpreso.getReactivoExamenimpresoList();
            for (ReactivoExamenimpreso reactivoExamenimpresoListOrphanCheckReactivoExamenimpreso : reactivoExamenimpresoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ExamenImpreso (" + examenImpreso + ") cannot be destroyed since the ReactivoExamenimpreso " + reactivoExamenimpresoListOrphanCheckReactivoExamenimpreso + " in its reactivoExamenimpresoList field has a non-nullable examenImpreso field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoExamen idTipoexamen = examenImpreso.getIdTipoexamen();
            if (idTipoexamen != null) {
                idTipoexamen.getExamenImpresoList().remove(examenImpreso);
                idTipoexamen = em.merge(idTipoexamen);
            }
            AsignacionAsignaturabanco idAsignacionasignaturabanco = examenImpreso.getIdAsignacionasignaturabanco();
            if (idAsignacionasignaturabanco != null) {
                idAsignacionasignaturabanco.getExamenImpresoList().remove(examenImpreso);
                idAsignacionasignaturabanco = em.merge(idAsignacionasignaturabanco);
            }
            List<FechaExamen> fechaExamenList = examenImpreso.getFechaExamenList();
            for (FechaExamen fechaExamenListFechaExamen : fechaExamenList) {
                fechaExamenListFechaExamen.getExamenImpresoList().remove(examenImpreso);
                fechaExamenListFechaExamen = em.merge(fechaExamenListFechaExamen);
            }
            em.remove(examenImpreso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ExamenImpreso> findExamenImpresoEntities() {
        return findExamenImpresoEntities(true, -1, -1);
    }

    public List<ExamenImpreso> findExamenImpresoEntities(int maxResults, int firstResult) {
        return findExamenImpresoEntities(false, maxResults, firstResult);
    }

    private List<ExamenImpreso> findExamenImpresoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ExamenImpreso as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ExamenImpreso findExamenImpreso(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ExamenImpreso.class, id);
        } finally {
            em.close();
        }
    }

    public int getExamenImpresoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ExamenImpreso as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
