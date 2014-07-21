/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoNivelacademico;
import com.utez.integracion.entity.TipoModalidad;
import com.utez.integracion.entity.RecursoHumano;
import com.utez.integracion.entity.Asentamiento;
import com.utez.integracion.entity.AsignacionAsesorplan;
import com.utez.integracion.entity.FormacionAcademica;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class FormacionAcademicaJpaController implements Serializable {

    public FormacionAcademicaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FormacionAcademica formacionAcademica) {
        if (formacionAcademica.getAsignacionAsesorplanList() == null) {
            formacionAcademica.setAsignacionAsesorplanList(new ArrayList<AsignacionAsesorplan>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoNivelacademico idTiponivelacademico = formacionAcademica.getIdTiponivelacademico();
            if (idTiponivelacademico != null) {
                idTiponivelacademico = em.getReference(idTiponivelacademico.getClass(), idTiponivelacademico.getIdTiponivelacademico());
                formacionAcademica.setIdTiponivelacademico(idTiponivelacademico);
            }
            TipoModalidad idTipomodalidad = formacionAcademica.getIdTipomodalidad();
            if (idTipomodalidad != null) {
                idTipomodalidad = em.getReference(idTipomodalidad.getClass(), idTipomodalidad.getIdTipomodalidad());
                formacionAcademica.setIdTipomodalidad(idTipomodalidad);
            }
            RecursoHumano idRecursohumano = formacionAcademica.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano = em.getReference(idRecursohumano.getClass(), idRecursohumano.getIdRecursohumano());
                formacionAcademica.setIdRecursohumano(idRecursohumano);
            }
            Asentamiento idAsentamiento = formacionAcademica.getIdAsentamiento();
            if (idAsentamiento != null) {
                idAsentamiento = em.getReference(idAsentamiento.getClass(), idAsentamiento.getIdAsentamiento());
                formacionAcademica.setIdAsentamiento(idAsentamiento);
            }
            List<AsignacionAsesorplan> attachedAsignacionAsesorplanList = new ArrayList<AsignacionAsesorplan>();
            for (AsignacionAsesorplan asignacionAsesorplanListAsignacionAsesorplanToAttach : formacionAcademica.getAsignacionAsesorplanList()) {
                asignacionAsesorplanListAsignacionAsesorplanToAttach = em.getReference(asignacionAsesorplanListAsignacionAsesorplanToAttach.getClass(), asignacionAsesorplanListAsignacionAsesorplanToAttach.getIdAsignacionasesorplan());
                attachedAsignacionAsesorplanList.add(asignacionAsesorplanListAsignacionAsesorplanToAttach);
            }
            formacionAcademica.setAsignacionAsesorplanList(attachedAsignacionAsesorplanList);
            em.persist(formacionAcademica);
            if (idTiponivelacademico != null) {
                idTiponivelacademico.getFormacionAcademicaList().add(formacionAcademica);
                idTiponivelacademico = em.merge(idTiponivelacademico);
            }
            if (idTipomodalidad != null) {
                idTipomodalidad.getFormacionAcademicaList().add(formacionAcademica);
                idTipomodalidad = em.merge(idTipomodalidad);
            }
            if (idRecursohumano != null) {
                idRecursohumano.getFormacionAcademicaList().add(formacionAcademica);
                idRecursohumano = em.merge(idRecursohumano);
            }
            if (idAsentamiento != null) {
                idAsentamiento.getFormacionAcademicaList().add(formacionAcademica);
                idAsentamiento = em.merge(idAsentamiento);
            }
            for (AsignacionAsesorplan asignacionAsesorplanListAsignacionAsesorplan : formacionAcademica.getAsignacionAsesorplanList()) {
                FormacionAcademica oldIdFormacionacademicaOfAsignacionAsesorplanListAsignacionAsesorplan = asignacionAsesorplanListAsignacionAsesorplan.getIdFormacionacademica();
                asignacionAsesorplanListAsignacionAsesorplan.setIdFormacionacademica(formacionAcademica);
                asignacionAsesorplanListAsignacionAsesorplan = em.merge(asignacionAsesorplanListAsignacionAsesorplan);
                if (oldIdFormacionacademicaOfAsignacionAsesorplanListAsignacionAsesorplan != null) {
                    oldIdFormacionacademicaOfAsignacionAsesorplanListAsignacionAsesorplan.getAsignacionAsesorplanList().remove(asignacionAsesorplanListAsignacionAsesorplan);
                    oldIdFormacionacademicaOfAsignacionAsesorplanListAsignacionAsesorplan = em.merge(oldIdFormacionacademicaOfAsignacionAsesorplanListAsignacionAsesorplan);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FormacionAcademica formacionAcademica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FormacionAcademica persistentFormacionAcademica = em.find(FormacionAcademica.class, formacionAcademica.getIdFormacionacademica());
            TipoNivelacademico idTiponivelacademicoOld = persistentFormacionAcademica.getIdTiponivelacademico();
            TipoNivelacademico idTiponivelacademicoNew = formacionAcademica.getIdTiponivelacademico();
            TipoModalidad idTipomodalidadOld = persistentFormacionAcademica.getIdTipomodalidad();
            TipoModalidad idTipomodalidadNew = formacionAcademica.getIdTipomodalidad();
            RecursoHumano idRecursohumanoOld = persistentFormacionAcademica.getIdRecursohumano();
            RecursoHumano idRecursohumanoNew = formacionAcademica.getIdRecursohumano();
            Asentamiento idAsentamientoOld = persistentFormacionAcademica.getIdAsentamiento();
            Asentamiento idAsentamientoNew = formacionAcademica.getIdAsentamiento();
            List<AsignacionAsesorplan> asignacionAsesorplanListOld = persistentFormacionAcademica.getAsignacionAsesorplanList();
            List<AsignacionAsesorplan> asignacionAsesorplanListNew = formacionAcademica.getAsignacionAsesorplanList();
            if (idTiponivelacademicoNew != null) {
                idTiponivelacademicoNew = em.getReference(idTiponivelacademicoNew.getClass(), idTiponivelacademicoNew.getIdTiponivelacademico());
                formacionAcademica.setIdTiponivelacademico(idTiponivelacademicoNew);
            }
            if (idTipomodalidadNew != null) {
                idTipomodalidadNew = em.getReference(idTipomodalidadNew.getClass(), idTipomodalidadNew.getIdTipomodalidad());
                formacionAcademica.setIdTipomodalidad(idTipomodalidadNew);
            }
            if (idRecursohumanoNew != null) {
                idRecursohumanoNew = em.getReference(idRecursohumanoNew.getClass(), idRecursohumanoNew.getIdRecursohumano());
                formacionAcademica.setIdRecursohumano(idRecursohumanoNew);
            }
            if (idAsentamientoNew != null) {
                idAsentamientoNew = em.getReference(idAsentamientoNew.getClass(), idAsentamientoNew.getIdAsentamiento());
                formacionAcademica.setIdAsentamiento(idAsentamientoNew);
            }
            List<AsignacionAsesorplan> attachedAsignacionAsesorplanListNew = new ArrayList<AsignacionAsesorplan>();
            for (AsignacionAsesorplan asignacionAsesorplanListNewAsignacionAsesorplanToAttach : asignacionAsesorplanListNew) {
                asignacionAsesorplanListNewAsignacionAsesorplanToAttach = em.getReference(asignacionAsesorplanListNewAsignacionAsesorplanToAttach.getClass(), asignacionAsesorplanListNewAsignacionAsesorplanToAttach.getIdAsignacionasesorplan());
                attachedAsignacionAsesorplanListNew.add(asignacionAsesorplanListNewAsignacionAsesorplanToAttach);
            }
            asignacionAsesorplanListNew = attachedAsignacionAsesorplanListNew;
            formacionAcademica.setAsignacionAsesorplanList(asignacionAsesorplanListNew);
            formacionAcademica = em.merge(formacionAcademica);
            if (idTiponivelacademicoOld != null && !idTiponivelacademicoOld.equals(idTiponivelacademicoNew)) {
                idTiponivelacademicoOld.getFormacionAcademicaList().remove(formacionAcademica);
                idTiponivelacademicoOld = em.merge(idTiponivelacademicoOld);
            }
            if (idTiponivelacademicoNew != null && !idTiponivelacademicoNew.equals(idTiponivelacademicoOld)) {
                idTiponivelacademicoNew.getFormacionAcademicaList().add(formacionAcademica);
                idTiponivelacademicoNew = em.merge(idTiponivelacademicoNew);
            }
            if (idTipomodalidadOld != null && !idTipomodalidadOld.equals(idTipomodalidadNew)) {
                idTipomodalidadOld.getFormacionAcademicaList().remove(formacionAcademica);
                idTipomodalidadOld = em.merge(idTipomodalidadOld);
            }
            if (idTipomodalidadNew != null && !idTipomodalidadNew.equals(idTipomodalidadOld)) {
                idTipomodalidadNew.getFormacionAcademicaList().add(formacionAcademica);
                idTipomodalidadNew = em.merge(idTipomodalidadNew);
            }
            if (idRecursohumanoOld != null && !idRecursohumanoOld.equals(idRecursohumanoNew)) {
                idRecursohumanoOld.getFormacionAcademicaList().remove(formacionAcademica);
                idRecursohumanoOld = em.merge(idRecursohumanoOld);
            }
            if (idRecursohumanoNew != null && !idRecursohumanoNew.equals(idRecursohumanoOld)) {
                idRecursohumanoNew.getFormacionAcademicaList().add(formacionAcademica);
                idRecursohumanoNew = em.merge(idRecursohumanoNew);
            }
            if (idAsentamientoOld != null && !idAsentamientoOld.equals(idAsentamientoNew)) {
                idAsentamientoOld.getFormacionAcademicaList().remove(formacionAcademica);
                idAsentamientoOld = em.merge(idAsentamientoOld);
            }
            if (idAsentamientoNew != null && !idAsentamientoNew.equals(idAsentamientoOld)) {
                idAsentamientoNew.getFormacionAcademicaList().add(formacionAcademica);
                idAsentamientoNew = em.merge(idAsentamientoNew);
            }
            for (AsignacionAsesorplan asignacionAsesorplanListOldAsignacionAsesorplan : asignacionAsesorplanListOld) {
                if (!asignacionAsesorplanListNew.contains(asignacionAsesorplanListOldAsignacionAsesorplan)) {
                    asignacionAsesorplanListOldAsignacionAsesorplan.setIdFormacionacademica(null);
                    asignacionAsesorplanListOldAsignacionAsesorplan = em.merge(asignacionAsesorplanListOldAsignacionAsesorplan);
                }
            }
            for (AsignacionAsesorplan asignacionAsesorplanListNewAsignacionAsesorplan : asignacionAsesorplanListNew) {
                if (!asignacionAsesorplanListOld.contains(asignacionAsesorplanListNewAsignacionAsesorplan)) {
                    FormacionAcademica oldIdFormacionacademicaOfAsignacionAsesorplanListNewAsignacionAsesorplan = asignacionAsesorplanListNewAsignacionAsesorplan.getIdFormacionacademica();
                    asignacionAsesorplanListNewAsignacionAsesorplan.setIdFormacionacademica(formacionAcademica);
                    asignacionAsesorplanListNewAsignacionAsesorplan = em.merge(asignacionAsesorplanListNewAsignacionAsesorplan);
                    if (oldIdFormacionacademicaOfAsignacionAsesorplanListNewAsignacionAsesorplan != null && !oldIdFormacionacademicaOfAsignacionAsesorplanListNewAsignacionAsesorplan.equals(formacionAcademica)) {
                        oldIdFormacionacademicaOfAsignacionAsesorplanListNewAsignacionAsesorplan.getAsignacionAsesorplanList().remove(asignacionAsesorplanListNewAsignacionAsesorplan);
                        oldIdFormacionacademicaOfAsignacionAsesorplanListNewAsignacionAsesorplan = em.merge(oldIdFormacionacademicaOfAsignacionAsesorplanListNewAsignacionAsesorplan);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = formacionAcademica.getIdFormacionacademica();
                if (findFormacionAcademica(id) == null) {
                    throw new NonexistentEntityException("The formacionAcademica with id " + id + " no longer exists.");
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
            FormacionAcademica formacionAcademica;
            try {
                formacionAcademica = em.getReference(FormacionAcademica.class, id);
                formacionAcademica.getIdFormacionacademica();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formacionAcademica with id " + id + " no longer exists.", enfe);
            }
            TipoNivelacademico idTiponivelacademico = formacionAcademica.getIdTiponivelacademico();
            if (idTiponivelacademico != null) {
                idTiponivelacademico.getFormacionAcademicaList().remove(formacionAcademica);
                idTiponivelacademico = em.merge(idTiponivelacademico);
            }
            TipoModalidad idTipomodalidad = formacionAcademica.getIdTipomodalidad();
            if (idTipomodalidad != null) {
                idTipomodalidad.getFormacionAcademicaList().remove(formacionAcademica);
                idTipomodalidad = em.merge(idTipomodalidad);
            }
            RecursoHumano idRecursohumano = formacionAcademica.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano.getFormacionAcademicaList().remove(formacionAcademica);
                idRecursohumano = em.merge(idRecursohumano);
            }
            Asentamiento idAsentamiento = formacionAcademica.getIdAsentamiento();
            if (idAsentamiento != null) {
                idAsentamiento.getFormacionAcademicaList().remove(formacionAcademica);
                idAsentamiento = em.merge(idAsentamiento);
            }
            List<AsignacionAsesorplan> asignacionAsesorplanList = formacionAcademica.getAsignacionAsesorplanList();
            for (AsignacionAsesorplan asignacionAsesorplanListAsignacionAsesorplan : asignacionAsesorplanList) {
                asignacionAsesorplanListAsignacionAsesorplan.setIdFormacionacademica(null);
                asignacionAsesorplanListAsignacionAsesorplan = em.merge(asignacionAsesorplanListAsignacionAsesorplan);
            }
            em.remove(formacionAcademica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FormacionAcademica> findFormacionAcademicaEntities() {
        return findFormacionAcademicaEntities(true, -1, -1);
    }

    public List<FormacionAcademica> findFormacionAcademicaEntities(int maxResults, int firstResult) {
        return findFormacionAcademicaEntities(false, maxResults, firstResult);
    }

    private List<FormacionAcademica> findFormacionAcademicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from FormacionAcademica as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public FormacionAcademica findFormacionAcademica(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FormacionAcademica.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormacionAcademicaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from FormacionAcademica as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
