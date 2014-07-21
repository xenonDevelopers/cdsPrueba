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
import com.utez.integracion.entity.Programacion;
import com.utez.integracion.entity.ProgramacionAsignatura;
import com.utez.secretariaAcademica.entity.Asignatura;
import com.utez.integracion.entity.ProgramacionEspecifica;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ProgramacionAsignaturaJpaController implements Serializable {

    public ProgramacionAsignaturaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProgramacionAsignatura programacionAsignatura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programacion idProgramacion = programacionAsignatura.getIdProgramacion();
            if (idProgramacion != null) {
                idProgramacion = em.getReference(idProgramacion.getClass(), idProgramacion.getIdProgramacion());
                programacionAsignatura.setIdProgramacion(idProgramacion);
            }
            Asignatura idAsignatura = programacionAsignatura.getIdAsignatura();
            if (idAsignatura != null) {
                idAsignatura = em.getReference(idAsignatura.getClass(), idAsignatura.getIdasignatura());
                programacionAsignatura.setIdAsignatura(idAsignatura);
            }
            ProgramacionEspecifica programacionEspecifica = programacionAsignatura.getProgramacionEspecifica();
            if (programacionEspecifica != null) {
                programacionEspecifica = em.getReference(programacionEspecifica.getClass(), programacionEspecifica.getIdProgramacionasignatura());
                programacionAsignatura.setProgramacionEspecifica(programacionEspecifica);
            }
            em.persist(programacionAsignatura);
            if (idProgramacion != null) {
                idProgramacion.getProgramacionAsignaturaList().add(programacionAsignatura);
                idProgramacion = em.merge(idProgramacion);
            }
            if (idAsignatura != null) {
                idAsignatura.getProgramacionAsignaturaList().add(programacionAsignatura);
                idAsignatura = em.merge(idAsignatura);
            }
            if (programacionEspecifica != null) {
                ProgramacionAsignatura oldProgramacionAsignaturaOfProgramacionEspecifica = programacionEspecifica.getProgramacionAsignatura();
                if (oldProgramacionAsignaturaOfProgramacionEspecifica != null) {
                    oldProgramacionAsignaturaOfProgramacionEspecifica.setProgramacionEspecifica(null);
                    oldProgramacionAsignaturaOfProgramacionEspecifica = em.merge(oldProgramacionAsignaturaOfProgramacionEspecifica);
                }
                programacionEspecifica.setProgramacionAsignatura(programacionAsignatura);
                programacionEspecifica = em.merge(programacionEspecifica);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProgramacionAsignatura programacionAsignatura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProgramacionAsignatura persistentProgramacionAsignatura = em.find(ProgramacionAsignatura.class, programacionAsignatura.getIdProgramacionasignatura());
            Programacion idProgramacionOld = persistentProgramacionAsignatura.getIdProgramacion();
            Programacion idProgramacionNew = programacionAsignatura.getIdProgramacion();
            Asignatura idAsignaturaOld = persistentProgramacionAsignatura.getIdAsignatura();
            Asignatura idAsignaturaNew = programacionAsignatura.getIdAsignatura();
            ProgramacionEspecifica programacionEspecificaOld = persistentProgramacionAsignatura.getProgramacionEspecifica();
            ProgramacionEspecifica programacionEspecificaNew = programacionAsignatura.getProgramacionEspecifica();
            List<String> illegalOrphanMessages = null;
            if (programacionEspecificaOld != null && !programacionEspecificaOld.equals(programacionEspecificaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain ProgramacionEspecifica " + programacionEspecificaOld + " since its programacionAsignatura field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idProgramacionNew != null) {
                idProgramacionNew = em.getReference(idProgramacionNew.getClass(), idProgramacionNew.getIdProgramacion());
                programacionAsignatura.setIdProgramacion(idProgramacionNew);
            }
            if (idAsignaturaNew != null) {
                idAsignaturaNew = em.getReference(idAsignaturaNew.getClass(), idAsignaturaNew.getIdasignatura());
                programacionAsignatura.setIdAsignatura(idAsignaturaNew);
            }
            if (programacionEspecificaNew != null) {
                programacionEspecificaNew = em.getReference(programacionEspecificaNew.getClass(), programacionEspecificaNew.getIdProgramacionasignatura());
                programacionAsignatura.setProgramacionEspecifica(programacionEspecificaNew);
            }
            programacionAsignatura = em.merge(programacionAsignatura);
            if (idProgramacionOld != null && !idProgramacionOld.equals(idProgramacionNew)) {
                idProgramacionOld.getProgramacionAsignaturaList().remove(programacionAsignatura);
                idProgramacionOld = em.merge(idProgramacionOld);
            }
            if (idProgramacionNew != null && !idProgramacionNew.equals(idProgramacionOld)) {
                idProgramacionNew.getProgramacionAsignaturaList().add(programacionAsignatura);
                idProgramacionNew = em.merge(idProgramacionNew);
            }
            if (idAsignaturaOld != null && !idAsignaturaOld.equals(idAsignaturaNew)) {
                idAsignaturaOld.getProgramacionAsignaturaList().remove(programacionAsignatura);
                idAsignaturaOld = em.merge(idAsignaturaOld);
            }
            if (idAsignaturaNew != null && !idAsignaturaNew.equals(idAsignaturaOld)) {
                idAsignaturaNew.getProgramacionAsignaturaList().add(programacionAsignatura);
                idAsignaturaNew = em.merge(idAsignaturaNew);
            }
            if (programacionEspecificaNew != null && !programacionEspecificaNew.equals(programacionEspecificaOld)) {
                ProgramacionAsignatura oldProgramacionAsignaturaOfProgramacionEspecifica = programacionEspecificaNew.getProgramacionAsignatura();
                if (oldProgramacionAsignaturaOfProgramacionEspecifica != null) {
                    oldProgramacionAsignaturaOfProgramacionEspecifica.setProgramacionEspecifica(null);
                    oldProgramacionAsignaturaOfProgramacionEspecifica = em.merge(oldProgramacionAsignaturaOfProgramacionEspecifica);
                }
                programacionEspecificaNew.setProgramacionAsignatura(programacionAsignatura);
                programacionEspecificaNew = em.merge(programacionEspecificaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = programacionAsignatura.getIdProgramacionasignatura();
                if (findProgramacionAsignatura(id) == null) {
                    throw new NonexistentEntityException("The programacionAsignatura with id " + id + " no longer exists.");
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
            ProgramacionAsignatura programacionAsignatura;
            try {
                programacionAsignatura = em.getReference(ProgramacionAsignatura.class, id);
                programacionAsignatura.getIdProgramacionasignatura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programacionAsignatura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            ProgramacionEspecifica programacionEspecificaOrphanCheck = programacionAsignatura.getProgramacionEspecifica();
            if (programacionEspecificaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ProgramacionAsignatura (" + programacionAsignatura + ") cannot be destroyed since the ProgramacionEspecifica " + programacionEspecificaOrphanCheck + " in its programacionEspecifica field has a non-nullable programacionAsignatura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Programacion idProgramacion = programacionAsignatura.getIdProgramacion();
            if (idProgramacion != null) {
                idProgramacion.getProgramacionAsignaturaList().remove(programacionAsignatura);
                idProgramacion = em.merge(idProgramacion);
            }
            Asignatura idAsignatura = programacionAsignatura.getIdAsignatura();
            if (idAsignatura != null) {
                idAsignatura.getProgramacionAsignaturaList().remove(programacionAsignatura);
                idAsignatura = em.merge(idAsignatura);
            }
            em.remove(programacionAsignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProgramacionAsignatura> findProgramacionAsignaturaEntities() {
        return findProgramacionAsignaturaEntities(true, -1, -1);
    }

    public List<ProgramacionAsignatura> findProgramacionAsignaturaEntities(int maxResults, int firstResult) {
        return findProgramacionAsignaturaEntities(false, maxResults, firstResult);
    }

    private List<ProgramacionAsignatura> findProgramacionAsignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ProgramacionAsignatura as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ProgramacionAsignatura findProgramacionAsignatura(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProgramacionAsignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramacionAsignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ProgramacionAsignatura as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
