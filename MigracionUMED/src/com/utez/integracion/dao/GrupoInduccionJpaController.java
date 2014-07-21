/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Plantel;
import com.utez.secretariaAcademica.entity.Periodo;
import com.utez.secretariaAcademica.entity.Asesor;
import com.utez.integracion.entity.AsignacionGrupoinduccion;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.FechaInduccion;
import com.utez.integracion.entity.GrupoInduccion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class GrupoInduccionJpaController implements Serializable {

    public GrupoInduccionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GrupoInduccion grupoInduccion) {
        if (grupoInduccion.getAsignacionGrupoinduccionList() == null) {
            grupoInduccion.setAsignacionGrupoinduccionList(new ArrayList<AsignacionGrupoinduccion>());
        }
        if (grupoInduccion.getFechaInduccionList() == null) {
            grupoInduccion.setFechaInduccionList(new ArrayList<FechaInduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Plantel idPlantel = grupoInduccion.getIdPlantel();
            if (idPlantel != null) {
                idPlantel = em.getReference(idPlantel.getClass(), idPlantel.getIdplantel());
                grupoInduccion.setIdPlantel(idPlantel);
            }
            Periodo idPeriodo = grupoInduccion.getIdPeriodo();
            if (idPeriodo != null) {
                idPeriodo = em.getReference(idPeriodo.getClass(), idPeriodo.getPeriodo());
                grupoInduccion.setIdPeriodo(idPeriodo);
            }
            Asesor responsable = grupoInduccion.getResponsable();
            if (responsable != null) {
                responsable = em.getReference(responsable.getClass(), responsable.getIdasesor());
                grupoInduccion.setResponsable(responsable);
            }
            List<AsignacionGrupoinduccion> attachedAsignacionGrupoinduccionList = new ArrayList<AsignacionGrupoinduccion>();
            for (AsignacionGrupoinduccion asignacionGrupoinduccionListAsignacionGrupoinduccionToAttach : grupoInduccion.getAsignacionGrupoinduccionList()) {
                asignacionGrupoinduccionListAsignacionGrupoinduccionToAttach = em.getReference(asignacionGrupoinduccionListAsignacionGrupoinduccionToAttach.getClass(), asignacionGrupoinduccionListAsignacionGrupoinduccionToAttach.getIdAsignacionGrupoinduccion());
                attachedAsignacionGrupoinduccionList.add(asignacionGrupoinduccionListAsignacionGrupoinduccionToAttach);
            }
            grupoInduccion.setAsignacionGrupoinduccionList(attachedAsignacionGrupoinduccionList);
            List<FechaInduccion> attachedFechaInduccionList = new ArrayList<FechaInduccion>();
            for (FechaInduccion fechaInduccionListFechaInduccionToAttach : grupoInduccion.getFechaInduccionList()) {
                fechaInduccionListFechaInduccionToAttach = em.getReference(fechaInduccionListFechaInduccionToAttach.getClass(), fechaInduccionListFechaInduccionToAttach.getIdFechainduccion());
                attachedFechaInduccionList.add(fechaInduccionListFechaInduccionToAttach);
            }
            grupoInduccion.setFechaInduccionList(attachedFechaInduccionList);
            em.persist(grupoInduccion);
            if (idPlantel != null) {
                idPlantel.getGrupoInduccionList().add(grupoInduccion);
                idPlantel = em.merge(idPlantel);
            }
            if (idPeriodo != null) {
                idPeriodo.getGrupoInduccionList().add(grupoInduccion);
                idPeriodo = em.merge(idPeriodo);
            }
            if (responsable != null) {
                responsable.getGrupoInduccionList().add(grupoInduccion);
                responsable = em.merge(responsable);
            }
            for (AsignacionGrupoinduccion asignacionGrupoinduccionListAsignacionGrupoinduccion : grupoInduccion.getAsignacionGrupoinduccionList()) {
                GrupoInduccion oldIdGrupoinduccionOfAsignacionGrupoinduccionListAsignacionGrupoinduccion = asignacionGrupoinduccionListAsignacionGrupoinduccion.getIdGrupoinduccion();
                asignacionGrupoinduccionListAsignacionGrupoinduccion.setIdGrupoinduccion(grupoInduccion);
                asignacionGrupoinduccionListAsignacionGrupoinduccion = em.merge(asignacionGrupoinduccionListAsignacionGrupoinduccion);
                if (oldIdGrupoinduccionOfAsignacionGrupoinduccionListAsignacionGrupoinduccion != null) {
                    oldIdGrupoinduccionOfAsignacionGrupoinduccionListAsignacionGrupoinduccion.getAsignacionGrupoinduccionList().remove(asignacionGrupoinduccionListAsignacionGrupoinduccion);
                    oldIdGrupoinduccionOfAsignacionGrupoinduccionListAsignacionGrupoinduccion = em.merge(oldIdGrupoinduccionOfAsignacionGrupoinduccionListAsignacionGrupoinduccion);
                }
            }
            for (FechaInduccion fechaInduccionListFechaInduccion : grupoInduccion.getFechaInduccionList()) {
                GrupoInduccion oldIdGrupoinduccionOfFechaInduccionListFechaInduccion = fechaInduccionListFechaInduccion.getIdGrupoinduccion();
                fechaInduccionListFechaInduccion.setIdGrupoinduccion(grupoInduccion);
                fechaInduccionListFechaInduccion = em.merge(fechaInduccionListFechaInduccion);
                if (oldIdGrupoinduccionOfFechaInduccionListFechaInduccion != null) {
                    oldIdGrupoinduccionOfFechaInduccionListFechaInduccion.getFechaInduccionList().remove(fechaInduccionListFechaInduccion);
                    oldIdGrupoinduccionOfFechaInduccionListFechaInduccion = em.merge(oldIdGrupoinduccionOfFechaInduccionListFechaInduccion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GrupoInduccion grupoInduccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GrupoInduccion persistentGrupoInduccion = em.find(GrupoInduccion.class, grupoInduccion.getIdGrupoinduccion());
            Plantel idPlantelOld = persistentGrupoInduccion.getIdPlantel();
            Plantel idPlantelNew = grupoInduccion.getIdPlantel();
            Periodo idPeriodoOld = persistentGrupoInduccion.getIdPeriodo();
            Periodo idPeriodoNew = grupoInduccion.getIdPeriodo();
            Asesor responsableOld = persistentGrupoInduccion.getResponsable();
            Asesor responsableNew = grupoInduccion.getResponsable();
            List<AsignacionGrupoinduccion> asignacionGrupoinduccionListOld = persistentGrupoInduccion.getAsignacionGrupoinduccionList();
            List<AsignacionGrupoinduccion> asignacionGrupoinduccionListNew = grupoInduccion.getAsignacionGrupoinduccionList();
            List<FechaInduccion> fechaInduccionListOld = persistentGrupoInduccion.getFechaInduccionList();
            List<FechaInduccion> fechaInduccionListNew = grupoInduccion.getFechaInduccionList();
            if (idPlantelNew != null) {
                idPlantelNew = em.getReference(idPlantelNew.getClass(), idPlantelNew.getIdplantel());
                grupoInduccion.setIdPlantel(idPlantelNew);
            }
            if (idPeriodoNew != null) {
                idPeriodoNew = em.getReference(idPeriodoNew.getClass(), idPeriodoNew.getPeriodo());
                grupoInduccion.setIdPeriodo(idPeriodoNew);
            }
            if (responsableNew != null) {
                responsableNew = em.getReference(responsableNew.getClass(), responsableNew.getIdasesor());
                grupoInduccion.setResponsable(responsableNew);
            }
            List<AsignacionGrupoinduccion> attachedAsignacionGrupoinduccionListNew = new ArrayList<AsignacionGrupoinduccion>();
            for (AsignacionGrupoinduccion asignacionGrupoinduccionListNewAsignacionGrupoinduccionToAttach : asignacionGrupoinduccionListNew) {
                asignacionGrupoinduccionListNewAsignacionGrupoinduccionToAttach = em.getReference(asignacionGrupoinduccionListNewAsignacionGrupoinduccionToAttach.getClass(), asignacionGrupoinduccionListNewAsignacionGrupoinduccionToAttach.getIdAsignacionGrupoinduccion());
                attachedAsignacionGrupoinduccionListNew.add(asignacionGrupoinduccionListNewAsignacionGrupoinduccionToAttach);
            }
            asignacionGrupoinduccionListNew = attachedAsignacionGrupoinduccionListNew;
            grupoInduccion.setAsignacionGrupoinduccionList(asignacionGrupoinduccionListNew);
            List<FechaInduccion> attachedFechaInduccionListNew = new ArrayList<FechaInduccion>();
            for (FechaInduccion fechaInduccionListNewFechaInduccionToAttach : fechaInduccionListNew) {
                fechaInduccionListNewFechaInduccionToAttach = em.getReference(fechaInduccionListNewFechaInduccionToAttach.getClass(), fechaInduccionListNewFechaInduccionToAttach.getIdFechainduccion());
                attachedFechaInduccionListNew.add(fechaInduccionListNewFechaInduccionToAttach);
            }
            fechaInduccionListNew = attachedFechaInduccionListNew;
            grupoInduccion.setFechaInduccionList(fechaInduccionListNew);
            grupoInduccion = em.merge(grupoInduccion);
            if (idPlantelOld != null && !idPlantelOld.equals(idPlantelNew)) {
                idPlantelOld.getGrupoInduccionList().remove(grupoInduccion);
                idPlantelOld = em.merge(idPlantelOld);
            }
            if (idPlantelNew != null && !idPlantelNew.equals(idPlantelOld)) {
                idPlantelNew.getGrupoInduccionList().add(grupoInduccion);
                idPlantelNew = em.merge(idPlantelNew);
            }
            if (idPeriodoOld != null && !idPeriodoOld.equals(idPeriodoNew)) {
                idPeriodoOld.getGrupoInduccionList().remove(grupoInduccion);
                idPeriodoOld = em.merge(idPeriodoOld);
            }
            if (idPeriodoNew != null && !idPeriodoNew.equals(idPeriodoOld)) {
                idPeriodoNew.getGrupoInduccionList().add(grupoInduccion);
                idPeriodoNew = em.merge(idPeriodoNew);
            }
            if (responsableOld != null && !responsableOld.equals(responsableNew)) {
                responsableOld.getGrupoInduccionList().remove(grupoInduccion);
                responsableOld = em.merge(responsableOld);
            }
            if (responsableNew != null && !responsableNew.equals(responsableOld)) {
                responsableNew.getGrupoInduccionList().add(grupoInduccion);
                responsableNew = em.merge(responsableNew);
            }
            for (AsignacionGrupoinduccion asignacionGrupoinduccionListOldAsignacionGrupoinduccion : asignacionGrupoinduccionListOld) {
                if (!asignacionGrupoinduccionListNew.contains(asignacionGrupoinduccionListOldAsignacionGrupoinduccion)) {
                    asignacionGrupoinduccionListOldAsignacionGrupoinduccion.setIdGrupoinduccion(null);
                    asignacionGrupoinduccionListOldAsignacionGrupoinduccion = em.merge(asignacionGrupoinduccionListOldAsignacionGrupoinduccion);
                }
            }
            for (AsignacionGrupoinduccion asignacionGrupoinduccionListNewAsignacionGrupoinduccion : asignacionGrupoinduccionListNew) {
                if (!asignacionGrupoinduccionListOld.contains(asignacionGrupoinduccionListNewAsignacionGrupoinduccion)) {
                    GrupoInduccion oldIdGrupoinduccionOfAsignacionGrupoinduccionListNewAsignacionGrupoinduccion = asignacionGrupoinduccionListNewAsignacionGrupoinduccion.getIdGrupoinduccion();
                    asignacionGrupoinduccionListNewAsignacionGrupoinduccion.setIdGrupoinduccion(grupoInduccion);
                    asignacionGrupoinduccionListNewAsignacionGrupoinduccion = em.merge(asignacionGrupoinduccionListNewAsignacionGrupoinduccion);
                    if (oldIdGrupoinduccionOfAsignacionGrupoinduccionListNewAsignacionGrupoinduccion != null && !oldIdGrupoinduccionOfAsignacionGrupoinduccionListNewAsignacionGrupoinduccion.equals(grupoInduccion)) {
                        oldIdGrupoinduccionOfAsignacionGrupoinduccionListNewAsignacionGrupoinduccion.getAsignacionGrupoinduccionList().remove(asignacionGrupoinduccionListNewAsignacionGrupoinduccion);
                        oldIdGrupoinduccionOfAsignacionGrupoinduccionListNewAsignacionGrupoinduccion = em.merge(oldIdGrupoinduccionOfAsignacionGrupoinduccionListNewAsignacionGrupoinduccion);
                    }
                }
            }
            for (FechaInduccion fechaInduccionListOldFechaInduccion : fechaInduccionListOld) {
                if (!fechaInduccionListNew.contains(fechaInduccionListOldFechaInduccion)) {
                    fechaInduccionListOldFechaInduccion.setIdGrupoinduccion(null);
                    fechaInduccionListOldFechaInduccion = em.merge(fechaInduccionListOldFechaInduccion);
                }
            }
            for (FechaInduccion fechaInduccionListNewFechaInduccion : fechaInduccionListNew) {
                if (!fechaInduccionListOld.contains(fechaInduccionListNewFechaInduccion)) {
                    GrupoInduccion oldIdGrupoinduccionOfFechaInduccionListNewFechaInduccion = fechaInduccionListNewFechaInduccion.getIdGrupoinduccion();
                    fechaInduccionListNewFechaInduccion.setIdGrupoinduccion(grupoInduccion);
                    fechaInduccionListNewFechaInduccion = em.merge(fechaInduccionListNewFechaInduccion);
                    if (oldIdGrupoinduccionOfFechaInduccionListNewFechaInduccion != null && !oldIdGrupoinduccionOfFechaInduccionListNewFechaInduccion.equals(grupoInduccion)) {
                        oldIdGrupoinduccionOfFechaInduccionListNewFechaInduccion.getFechaInduccionList().remove(fechaInduccionListNewFechaInduccion);
                        oldIdGrupoinduccionOfFechaInduccionListNewFechaInduccion = em.merge(oldIdGrupoinduccionOfFechaInduccionListNewFechaInduccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = grupoInduccion.getIdGrupoinduccion();
                if (findGrupoInduccion(id) == null) {
                    throw new NonexistentEntityException("The grupoInduccion with id " + id + " no longer exists.");
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
            GrupoInduccion grupoInduccion;
            try {
                grupoInduccion = em.getReference(GrupoInduccion.class, id);
                grupoInduccion.getIdGrupoinduccion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupoInduccion with id " + id + " no longer exists.", enfe);
            }
            Plantel idPlantel = grupoInduccion.getIdPlantel();
            if (idPlantel != null) {
                idPlantel.getGrupoInduccionList().remove(grupoInduccion);
                idPlantel = em.merge(idPlantel);
            }
            Periodo idPeriodo = grupoInduccion.getIdPeriodo();
            if (idPeriodo != null) {
                idPeriodo.getGrupoInduccionList().remove(grupoInduccion);
                idPeriodo = em.merge(idPeriodo);
            }
            Asesor responsable = grupoInduccion.getResponsable();
            if (responsable != null) {
                responsable.getGrupoInduccionList().remove(grupoInduccion);
                responsable = em.merge(responsable);
            }
            List<AsignacionGrupoinduccion> asignacionGrupoinduccionList = grupoInduccion.getAsignacionGrupoinduccionList();
            for (AsignacionGrupoinduccion asignacionGrupoinduccionListAsignacionGrupoinduccion : asignacionGrupoinduccionList) {
                asignacionGrupoinduccionListAsignacionGrupoinduccion.setIdGrupoinduccion(null);
                asignacionGrupoinduccionListAsignacionGrupoinduccion = em.merge(asignacionGrupoinduccionListAsignacionGrupoinduccion);
            }
            List<FechaInduccion> fechaInduccionList = grupoInduccion.getFechaInduccionList();
            for (FechaInduccion fechaInduccionListFechaInduccion : fechaInduccionList) {
                fechaInduccionListFechaInduccion.setIdGrupoinduccion(null);
                fechaInduccionListFechaInduccion = em.merge(fechaInduccionListFechaInduccion);
            }
            em.remove(grupoInduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GrupoInduccion> findGrupoInduccionEntities() {
        return findGrupoInduccionEntities(true, -1, -1);
    }

    public List<GrupoInduccion> findGrupoInduccionEntities(int maxResults, int firstResult) {
        return findGrupoInduccionEntities(false, maxResults, firstResult);
    }

    private List<GrupoInduccion> findGrupoInduccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from GrupoInduccion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public GrupoInduccion findGrupoInduccion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GrupoInduccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrupoInduccionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from GrupoInduccion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
