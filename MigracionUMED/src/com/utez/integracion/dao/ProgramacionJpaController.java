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
import com.utez.integracion.entity.ProgramacionCancelada;
import com.utez.integracion.entity.Equivalencia;
import com.utez.integracion.entity.Programacion;
import com.utez.integracion.entity.TipoProgramacion;
import com.utez.integracion.entity.SolicitudProgramacion;
import com.utez.integracion.entity.ProgramacionAsignatura;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ProgramacionJpaController implements Serializable {

    public ProgramacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Programacion programacion) {
        if (programacion.getProgramacionAsignaturaList() == null) {
            programacion.setProgramacionAsignaturaList(new ArrayList<ProgramacionAsignatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProgramacionCancelada programacionCancelada = programacion.getProgramacionCancelada();
            if (programacionCancelada != null) {
                programacionCancelada = em.getReference(programacionCancelada.getClass(), programacionCancelada.getIdProgramacion());
                programacion.setProgramacionCancelada(programacionCancelada);
            }
            Equivalencia equivalencia = programacion.getEquivalencia();
            if (equivalencia != null) {
                equivalencia = em.getReference(equivalencia.getClass(), equivalencia.getIdProgramacion());
                programacion.setEquivalencia(equivalencia);
            }
            TipoProgramacion idTipoprogramacion = programacion.getIdTipoprogramacion();
            if (idTipoprogramacion != null) {
                idTipoprogramacion = em.getReference(idTipoprogramacion.getClass(), idTipoprogramacion.getIdTipoprogramacion());
                programacion.setIdTipoprogramacion(idTipoprogramacion);
            }
            SolicitudProgramacion idSolicitudprogramacion = programacion.getIdSolicitudprogramacion();
            if (idSolicitudprogramacion != null) {
                idSolicitudprogramacion = em.getReference(idSolicitudprogramacion.getClass(), idSolicitudprogramacion.getIdSolicitudprogramacion());
                programacion.setIdSolicitudprogramacion(idSolicitudprogramacion);
            }
            List<ProgramacionAsignatura> attachedProgramacionAsignaturaList = new ArrayList<ProgramacionAsignatura>();
            for (ProgramacionAsignatura programacionAsignaturaListProgramacionAsignaturaToAttach : programacion.getProgramacionAsignaturaList()) {
                programacionAsignaturaListProgramacionAsignaturaToAttach = em.getReference(programacionAsignaturaListProgramacionAsignaturaToAttach.getClass(), programacionAsignaturaListProgramacionAsignaturaToAttach.getIdProgramacionasignatura());
                attachedProgramacionAsignaturaList.add(programacionAsignaturaListProgramacionAsignaturaToAttach);
            }
            programacion.setProgramacionAsignaturaList(attachedProgramacionAsignaturaList);
            em.persist(programacion);
            if (programacionCancelada != null) {
                Programacion oldProgramacionOfProgramacionCancelada = programacionCancelada.getProgramacion();
                if (oldProgramacionOfProgramacionCancelada != null) {
                    oldProgramacionOfProgramacionCancelada.setProgramacionCancelada(null);
                    oldProgramacionOfProgramacionCancelada = em.merge(oldProgramacionOfProgramacionCancelada);
                }
                programacionCancelada.setProgramacion(programacion);
                programacionCancelada = em.merge(programacionCancelada);
            }
            if (equivalencia != null) {
                Programacion oldProgramacionOfEquivalencia = equivalencia.getProgramacion();
                if (oldProgramacionOfEquivalencia != null) {
                    oldProgramacionOfEquivalencia.setEquivalencia(null);
                    oldProgramacionOfEquivalencia = em.merge(oldProgramacionOfEquivalencia);
                }
                equivalencia.setProgramacion(programacion);
                equivalencia = em.merge(equivalencia);
            }
            if (idTipoprogramacion != null) {
                idTipoprogramacion.getProgramacionList().add(programacion);
                idTipoprogramacion = em.merge(idTipoprogramacion);
            }
            if (idSolicitudprogramacion != null) {
                idSolicitudprogramacion.getProgramacionList().add(programacion);
                idSolicitudprogramacion = em.merge(idSolicitudprogramacion);
            }
            for (ProgramacionAsignatura programacionAsignaturaListProgramacionAsignatura : programacion.getProgramacionAsignaturaList()) {
                Programacion oldIdProgramacionOfProgramacionAsignaturaListProgramacionAsignatura = programacionAsignaturaListProgramacionAsignatura.getIdProgramacion();
                programacionAsignaturaListProgramacionAsignatura.setIdProgramacion(programacion);
                programacionAsignaturaListProgramacionAsignatura = em.merge(programacionAsignaturaListProgramacionAsignatura);
                if (oldIdProgramacionOfProgramacionAsignaturaListProgramacionAsignatura != null) {
                    oldIdProgramacionOfProgramacionAsignaturaListProgramacionAsignatura.getProgramacionAsignaturaList().remove(programacionAsignaturaListProgramacionAsignatura);
                    oldIdProgramacionOfProgramacionAsignaturaListProgramacionAsignatura = em.merge(oldIdProgramacionOfProgramacionAsignaturaListProgramacionAsignatura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Programacion programacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programacion persistentProgramacion = em.find(Programacion.class, programacion.getIdProgramacion());
            ProgramacionCancelada programacionCanceladaOld = persistentProgramacion.getProgramacionCancelada();
            ProgramacionCancelada programacionCanceladaNew = programacion.getProgramacionCancelada();
            Equivalencia equivalenciaOld = persistentProgramacion.getEquivalencia();
            Equivalencia equivalenciaNew = programacion.getEquivalencia();
            TipoProgramacion idTipoprogramacionOld = persistentProgramacion.getIdTipoprogramacion();
            TipoProgramacion idTipoprogramacionNew = programacion.getIdTipoprogramacion();
            SolicitudProgramacion idSolicitudprogramacionOld = persistentProgramacion.getIdSolicitudprogramacion();
            SolicitudProgramacion idSolicitudprogramacionNew = programacion.getIdSolicitudprogramacion();
            List<ProgramacionAsignatura> programacionAsignaturaListOld = persistentProgramacion.getProgramacionAsignaturaList();
            List<ProgramacionAsignatura> programacionAsignaturaListNew = programacion.getProgramacionAsignaturaList();
            List<String> illegalOrphanMessages = null;
            if (programacionCanceladaOld != null && !programacionCanceladaOld.equals(programacionCanceladaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain ProgramacionCancelada " + programacionCanceladaOld + " since its programacion field is not nullable.");
            }
            if (equivalenciaOld != null && !equivalenciaOld.equals(equivalenciaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Equivalencia " + equivalenciaOld + " since its programacion field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (programacionCanceladaNew != null) {
                programacionCanceladaNew = em.getReference(programacionCanceladaNew.getClass(), programacionCanceladaNew.getIdProgramacion());
                programacion.setProgramacionCancelada(programacionCanceladaNew);
            }
            if (equivalenciaNew != null) {
                equivalenciaNew = em.getReference(equivalenciaNew.getClass(), equivalenciaNew.getIdProgramacion());
                programacion.setEquivalencia(equivalenciaNew);
            }
            if (idTipoprogramacionNew != null) {
                idTipoprogramacionNew = em.getReference(idTipoprogramacionNew.getClass(), idTipoprogramacionNew.getIdTipoprogramacion());
                programacion.setIdTipoprogramacion(idTipoprogramacionNew);
            }
            if (idSolicitudprogramacionNew != null) {
                idSolicitudprogramacionNew = em.getReference(idSolicitudprogramacionNew.getClass(), idSolicitudprogramacionNew.getIdSolicitudprogramacion());
                programacion.setIdSolicitudprogramacion(idSolicitudprogramacionNew);
            }
            List<ProgramacionAsignatura> attachedProgramacionAsignaturaListNew = new ArrayList<ProgramacionAsignatura>();
            for (ProgramacionAsignatura programacionAsignaturaListNewProgramacionAsignaturaToAttach : programacionAsignaturaListNew) {
                programacionAsignaturaListNewProgramacionAsignaturaToAttach = em.getReference(programacionAsignaturaListNewProgramacionAsignaturaToAttach.getClass(), programacionAsignaturaListNewProgramacionAsignaturaToAttach.getIdProgramacionasignatura());
                attachedProgramacionAsignaturaListNew.add(programacionAsignaturaListNewProgramacionAsignaturaToAttach);
            }
            programacionAsignaturaListNew = attachedProgramacionAsignaturaListNew;
            programacion.setProgramacionAsignaturaList(programacionAsignaturaListNew);
            programacion = em.merge(programacion);
            if (programacionCanceladaNew != null && !programacionCanceladaNew.equals(programacionCanceladaOld)) {
                Programacion oldProgramacionOfProgramacionCancelada = programacionCanceladaNew.getProgramacion();
                if (oldProgramacionOfProgramacionCancelada != null) {
                    oldProgramacionOfProgramacionCancelada.setProgramacionCancelada(null);
                    oldProgramacionOfProgramacionCancelada = em.merge(oldProgramacionOfProgramacionCancelada);
                }
                programacionCanceladaNew.setProgramacion(programacion);
                programacionCanceladaNew = em.merge(programacionCanceladaNew);
            }
            if (equivalenciaNew != null && !equivalenciaNew.equals(equivalenciaOld)) {
                Programacion oldProgramacionOfEquivalencia = equivalenciaNew.getProgramacion();
                if (oldProgramacionOfEquivalencia != null) {
                    oldProgramacionOfEquivalencia.setEquivalencia(null);
                    oldProgramacionOfEquivalencia = em.merge(oldProgramacionOfEquivalencia);
                }
                equivalenciaNew.setProgramacion(programacion);
                equivalenciaNew = em.merge(equivalenciaNew);
            }
            if (idTipoprogramacionOld != null && !idTipoprogramacionOld.equals(idTipoprogramacionNew)) {
                idTipoprogramacionOld.getProgramacionList().remove(programacion);
                idTipoprogramacionOld = em.merge(idTipoprogramacionOld);
            }
            if (idTipoprogramacionNew != null && !idTipoprogramacionNew.equals(idTipoprogramacionOld)) {
                idTipoprogramacionNew.getProgramacionList().add(programacion);
                idTipoprogramacionNew = em.merge(idTipoprogramacionNew);
            }
            if (idSolicitudprogramacionOld != null && !idSolicitudprogramacionOld.equals(idSolicitudprogramacionNew)) {
                idSolicitudprogramacionOld.getProgramacionList().remove(programacion);
                idSolicitudprogramacionOld = em.merge(idSolicitudprogramacionOld);
            }
            if (idSolicitudprogramacionNew != null && !idSolicitudprogramacionNew.equals(idSolicitudprogramacionOld)) {
                idSolicitudprogramacionNew.getProgramacionList().add(programacion);
                idSolicitudprogramacionNew = em.merge(idSolicitudprogramacionNew);
            }
            for (ProgramacionAsignatura programacionAsignaturaListOldProgramacionAsignatura : programacionAsignaturaListOld) {
                if (!programacionAsignaturaListNew.contains(programacionAsignaturaListOldProgramacionAsignatura)) {
                    programacionAsignaturaListOldProgramacionAsignatura.setIdProgramacion(null);
                    programacionAsignaturaListOldProgramacionAsignatura = em.merge(programacionAsignaturaListOldProgramacionAsignatura);
                }
            }
            for (ProgramacionAsignatura programacionAsignaturaListNewProgramacionAsignatura : programacionAsignaturaListNew) {
                if (!programacionAsignaturaListOld.contains(programacionAsignaturaListNewProgramacionAsignatura)) {
                    Programacion oldIdProgramacionOfProgramacionAsignaturaListNewProgramacionAsignatura = programacionAsignaturaListNewProgramacionAsignatura.getIdProgramacion();
                    programacionAsignaturaListNewProgramacionAsignatura.setIdProgramacion(programacion);
                    programacionAsignaturaListNewProgramacionAsignatura = em.merge(programacionAsignaturaListNewProgramacionAsignatura);
                    if (oldIdProgramacionOfProgramacionAsignaturaListNewProgramacionAsignatura != null && !oldIdProgramacionOfProgramacionAsignaturaListNewProgramacionAsignatura.equals(programacion)) {
                        oldIdProgramacionOfProgramacionAsignaturaListNewProgramacionAsignatura.getProgramacionAsignaturaList().remove(programacionAsignaturaListNewProgramacionAsignatura);
                        oldIdProgramacionOfProgramacionAsignaturaListNewProgramacionAsignatura = em.merge(oldIdProgramacionOfProgramacionAsignaturaListNewProgramacionAsignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = programacion.getIdProgramacion();
                if (findProgramacion(id) == null) {
                    throw new NonexistentEntityException("The programacion with id " + id + " no longer exists.");
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
            Programacion programacion;
            try {
                programacion = em.getReference(Programacion.class, id);
                programacion.getIdProgramacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            ProgramacionCancelada programacionCanceladaOrphanCheck = programacion.getProgramacionCancelada();
            if (programacionCanceladaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Programacion (" + programacion + ") cannot be destroyed since the ProgramacionCancelada " + programacionCanceladaOrphanCheck + " in its programacionCancelada field has a non-nullable programacion field.");
            }
            Equivalencia equivalenciaOrphanCheck = programacion.getEquivalencia();
            if (equivalenciaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Programacion (" + programacion + ") cannot be destroyed since the Equivalencia " + equivalenciaOrphanCheck + " in its equivalencia field has a non-nullable programacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoProgramacion idTipoprogramacion = programacion.getIdTipoprogramacion();
            if (idTipoprogramacion != null) {
                idTipoprogramacion.getProgramacionList().remove(programacion);
                idTipoprogramacion = em.merge(idTipoprogramacion);
            }
            SolicitudProgramacion idSolicitudprogramacion = programacion.getIdSolicitudprogramacion();
            if (idSolicitudprogramacion != null) {
                idSolicitudprogramacion.getProgramacionList().remove(programacion);
                idSolicitudprogramacion = em.merge(idSolicitudprogramacion);
            }
            List<ProgramacionAsignatura> programacionAsignaturaList = programacion.getProgramacionAsignaturaList();
            for (ProgramacionAsignatura programacionAsignaturaListProgramacionAsignatura : programacionAsignaturaList) {
                programacionAsignaturaListProgramacionAsignatura.setIdProgramacion(null);
                programacionAsignaturaListProgramacionAsignatura = em.merge(programacionAsignaturaListProgramacionAsignatura);
            }
            em.remove(programacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Programacion> findProgramacionEntities() {
        return findProgramacionEntities(true, -1, -1);
    }

    public List<Programacion> findProgramacionEntities(int maxResults, int firstResult) {
        return findProgramacionEntities(false, maxResults, firstResult);
    }

    private List<Programacion> findProgramacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Programacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Programacion findProgramacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Programacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Programacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
