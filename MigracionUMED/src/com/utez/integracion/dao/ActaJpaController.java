/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.Acta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoExamen;
import com.utez.integracion.entity.TipoActa;
import com.utez.integracion.entity.RecursoHumano;
import com.utez.secretariaAcademica.entity.Grupo;
import com.utez.integracion.entity.FechaExamen;
import com.utez.secretariaAcademica.entity.Asignatura;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ActaJpaController implements Serializable {

    public ActaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Acta acta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoExamen idTipoexamen = acta.getIdTipoexamen();
            if (idTipoexamen != null) {
                idTipoexamen = em.getReference(idTipoexamen.getClass(), idTipoexamen.getIdTipoexamen());
                acta.setIdTipoexamen(idTipoexamen);
            }
            TipoActa idTipoacta = acta.getIdTipoacta();
            if (idTipoacta != null) {
                idTipoacta = em.getReference(idTipoacta.getClass(), idTipoacta.getIdTipoacta());
                acta.setIdTipoacta(idTipoacta);
            }
            RecursoHumano responsableActa = acta.getResponsableActa();
            if (responsableActa != null) {
                responsableActa = em.getReference(responsableActa.getClass(), responsableActa.getIdRecursohumano());
                acta.setResponsableActa(responsableActa);
            }
            Grupo idGrupo = acta.getIdGrupo();
            if (idGrupo != null) {
                idGrupo = em.getReference(idGrupo.getClass(), idGrupo.getIdgrupo());
                acta.setIdGrupo(idGrupo);
            }
            FechaExamen idFechaexamen = acta.getIdFechaexamen();
            if (idFechaexamen != null) {
                idFechaexamen = em.getReference(idFechaexamen.getClass(), idFechaexamen.getIdFechaexamen());
                acta.setIdFechaexamen(idFechaexamen);
            }
            Asignatura idAsignatura = acta.getIdAsignatura();
            if (idAsignatura != null) {
                idAsignatura = em.getReference(idAsignatura.getClass(), idAsignatura.getIdasignatura());
                acta.setIdAsignatura(idAsignatura);
            }
            em.persist(acta);
            if (idTipoexamen != null) {
                idTipoexamen.getActaList().add(acta);
                idTipoexamen = em.merge(idTipoexamen);
            }
            if (idTipoacta != null) {
                idTipoacta.getActaList().add(acta);
                idTipoacta = em.merge(idTipoacta);
            }
            if (responsableActa != null) {
                responsableActa.getActaList().add(acta);
                responsableActa = em.merge(responsableActa);
            }
            if (idGrupo != null) {
                idGrupo.getActaList().add(acta);
                idGrupo = em.merge(idGrupo);
            }
            if (idFechaexamen != null) {
                idFechaexamen.getActaList().add(acta);
                idFechaexamen = em.merge(idFechaexamen);
            }
            if (idAsignatura != null) {
                idAsignatura.getActaList().add(acta);
                idAsignatura = em.merge(idAsignatura);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Acta acta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Acta persistentActa = em.find(Acta.class, acta.getIdActa());
            TipoExamen idTipoexamenOld = persistentActa.getIdTipoexamen();
            TipoExamen idTipoexamenNew = acta.getIdTipoexamen();
            TipoActa idTipoactaOld = persistentActa.getIdTipoacta();
            TipoActa idTipoactaNew = acta.getIdTipoacta();
            RecursoHumano responsableActaOld = persistentActa.getResponsableActa();
            RecursoHumano responsableActaNew = acta.getResponsableActa();
            Grupo idGrupoOld = persistentActa.getIdGrupo();
            Grupo idGrupoNew = acta.getIdGrupo();
            FechaExamen idFechaexamenOld = persistentActa.getIdFechaexamen();
            FechaExamen idFechaexamenNew = acta.getIdFechaexamen();
            Asignatura idAsignaturaOld = persistentActa.getIdAsignatura();
            Asignatura idAsignaturaNew = acta.getIdAsignatura();
            if (idTipoexamenNew != null) {
                idTipoexamenNew = em.getReference(idTipoexamenNew.getClass(), idTipoexamenNew.getIdTipoexamen());
                acta.setIdTipoexamen(idTipoexamenNew);
            }
            if (idTipoactaNew != null) {
                idTipoactaNew = em.getReference(idTipoactaNew.getClass(), idTipoactaNew.getIdTipoacta());
                acta.setIdTipoacta(idTipoactaNew);
            }
            if (responsableActaNew != null) {
                responsableActaNew = em.getReference(responsableActaNew.getClass(), responsableActaNew.getIdRecursohumano());
                acta.setResponsableActa(responsableActaNew);
            }
            if (idGrupoNew != null) {
                idGrupoNew = em.getReference(idGrupoNew.getClass(), idGrupoNew.getIdgrupo());
                acta.setIdGrupo(idGrupoNew);
            }
            if (idFechaexamenNew != null) {
                idFechaexamenNew = em.getReference(idFechaexamenNew.getClass(), idFechaexamenNew.getIdFechaexamen());
                acta.setIdFechaexamen(idFechaexamenNew);
            }
            if (idAsignaturaNew != null) {
                idAsignaturaNew = em.getReference(idAsignaturaNew.getClass(), idAsignaturaNew.getIdasignatura());
                acta.setIdAsignatura(idAsignaturaNew);
            }
            acta = em.merge(acta);
            if (idTipoexamenOld != null && !idTipoexamenOld.equals(idTipoexamenNew)) {
                idTipoexamenOld.getActaList().remove(acta);
                idTipoexamenOld = em.merge(idTipoexamenOld);
            }
            if (idTipoexamenNew != null && !idTipoexamenNew.equals(idTipoexamenOld)) {
                idTipoexamenNew.getActaList().add(acta);
                idTipoexamenNew = em.merge(idTipoexamenNew);
            }
            if (idTipoactaOld != null && !idTipoactaOld.equals(idTipoactaNew)) {
                idTipoactaOld.getActaList().remove(acta);
                idTipoactaOld = em.merge(idTipoactaOld);
            }
            if (idTipoactaNew != null && !idTipoactaNew.equals(idTipoactaOld)) {
                idTipoactaNew.getActaList().add(acta);
                idTipoactaNew = em.merge(idTipoactaNew);
            }
            if (responsableActaOld != null && !responsableActaOld.equals(responsableActaNew)) {
                responsableActaOld.getActaList().remove(acta);
                responsableActaOld = em.merge(responsableActaOld);
            }
            if (responsableActaNew != null && !responsableActaNew.equals(responsableActaOld)) {
                responsableActaNew.getActaList().add(acta);
                responsableActaNew = em.merge(responsableActaNew);
            }
            if (idGrupoOld != null && !idGrupoOld.equals(idGrupoNew)) {
                idGrupoOld.getActaList().remove(acta);
                idGrupoOld = em.merge(idGrupoOld);
            }
            if (idGrupoNew != null && !idGrupoNew.equals(idGrupoOld)) {
                idGrupoNew.getActaList().add(acta);
                idGrupoNew = em.merge(idGrupoNew);
            }
            if (idFechaexamenOld != null && !idFechaexamenOld.equals(idFechaexamenNew)) {
                idFechaexamenOld.getActaList().remove(acta);
                idFechaexamenOld = em.merge(idFechaexamenOld);
            }
            if (idFechaexamenNew != null && !idFechaexamenNew.equals(idFechaexamenOld)) {
                idFechaexamenNew.getActaList().add(acta);
                idFechaexamenNew = em.merge(idFechaexamenNew);
            }
            if (idAsignaturaOld != null && !idAsignaturaOld.equals(idAsignaturaNew)) {
                idAsignaturaOld.getActaList().remove(acta);
                idAsignaturaOld = em.merge(idAsignaturaOld);
            }
            if (idAsignaturaNew != null && !idAsignaturaNew.equals(idAsignaturaOld)) {
                idAsignaturaNew.getActaList().add(acta);
                idAsignaturaNew = em.merge(idAsignaturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = acta.getIdActa();
                if (findActa(id) == null) {
                    throw new NonexistentEntityException("The acta with id " + id + " no longer exists.");
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
            Acta acta;
            try {
                acta = em.getReference(Acta.class, id);
                acta.getIdActa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The acta with id " + id + " no longer exists.", enfe);
            }
            TipoExamen idTipoexamen = acta.getIdTipoexamen();
            if (idTipoexamen != null) {
                idTipoexamen.getActaList().remove(acta);
                idTipoexamen = em.merge(idTipoexamen);
            }
            TipoActa idTipoacta = acta.getIdTipoacta();
            if (idTipoacta != null) {
                idTipoacta.getActaList().remove(acta);
                idTipoacta = em.merge(idTipoacta);
            }
            RecursoHumano responsableActa = acta.getResponsableActa();
            if (responsableActa != null) {
                responsableActa.getActaList().remove(acta);
                responsableActa = em.merge(responsableActa);
            }
            Grupo idGrupo = acta.getIdGrupo();
            if (idGrupo != null) {
                idGrupo.getActaList().remove(acta);
                idGrupo = em.merge(idGrupo);
            }
            FechaExamen idFechaexamen = acta.getIdFechaexamen();
            if (idFechaexamen != null) {
                idFechaexamen.getActaList().remove(acta);
                idFechaexamen = em.merge(idFechaexamen);
            }
            Asignatura idAsignatura = acta.getIdAsignatura();
            if (idAsignatura != null) {
                idAsignatura.getActaList().remove(acta);
                idAsignatura = em.merge(idAsignatura);
            }
            em.remove(acta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Acta> findActaEntities() {
        return findActaEntities(true, -1, -1);
    }

    public List<Acta> findActaEntities(int maxResults, int firstResult) {
        return findActaEntities(false, maxResults, firstResult);
    }

    private List<Acta> findActaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Acta as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Acta findActa(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Acta.class, id);
        } finally {
            em.close();
        }
    }

    public int getActaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Acta as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
