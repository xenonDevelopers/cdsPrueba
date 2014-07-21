/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.ProgramacionAsignatura;
import com.utez.integracion.entity.CalendarioAsignatura;
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
public class ProgramacionEspecificaJpaController implements Serializable {

    public ProgramacionEspecificaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProgramacionEspecifica programacionEspecifica) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        ProgramacionAsignatura programacionAsignaturaOrphanCheck = programacionEspecifica.getProgramacionAsignatura();
        if (programacionAsignaturaOrphanCheck != null) {
            ProgramacionEspecifica oldProgramacionEspecificaOfProgramacionAsignatura = programacionAsignaturaOrphanCheck.getProgramacionEspecifica();
            if (oldProgramacionEspecificaOfProgramacionAsignatura != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The ProgramacionAsignatura " + programacionAsignaturaOrphanCheck + " already has an item of type ProgramacionEspecifica whose programacionAsignatura column cannot be null. Please make another selection for the programacionAsignatura field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProgramacionAsignatura programacionAsignatura = programacionEspecifica.getProgramacionAsignatura();
            if (programacionAsignatura != null) {
                programacionAsignatura = em.getReference(programacionAsignatura.getClass(), programacionAsignatura.getIdProgramacionasignatura());
                programacionEspecifica.setProgramacionAsignatura(programacionAsignatura);
            }
            CalendarioAsignatura idCalendarioasignatura = programacionEspecifica.getIdCalendarioasignatura();
            if (idCalendarioasignatura != null) {
                idCalendarioasignatura = em.getReference(idCalendarioasignatura.getClass(), idCalendarioasignatura.getIdCalendarioasignatura());
                programacionEspecifica.setIdCalendarioasignatura(idCalendarioasignatura);
            }
            em.persist(programacionEspecifica);
            if (programacionAsignatura != null) {
                programacionAsignatura.setProgramacionEspecifica(programacionEspecifica);
                programacionAsignatura = em.merge(programacionAsignatura);
            }
            if (idCalendarioasignatura != null) {
                idCalendarioasignatura.getProgramacionEspecificaList().add(programacionEspecifica);
                idCalendarioasignatura = em.merge(idCalendarioasignatura);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProgramacionEspecifica(programacionEspecifica.getIdProgramacionasignatura()) != null) {
                throw new PreexistingEntityException("ProgramacionEspecifica " + programacionEspecifica + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProgramacionEspecifica programacionEspecifica) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProgramacionEspecifica persistentProgramacionEspecifica = em.find(ProgramacionEspecifica.class, programacionEspecifica.getIdProgramacionasignatura());
            ProgramacionAsignatura programacionAsignaturaOld = persistentProgramacionEspecifica.getProgramacionAsignatura();
            ProgramacionAsignatura programacionAsignaturaNew = programacionEspecifica.getProgramacionAsignatura();
            CalendarioAsignatura idCalendarioasignaturaOld = persistentProgramacionEspecifica.getIdCalendarioasignatura();
            CalendarioAsignatura idCalendarioasignaturaNew = programacionEspecifica.getIdCalendarioasignatura();
            List<String> illegalOrphanMessages = null;
            if (programacionAsignaturaNew != null && !programacionAsignaturaNew.equals(programacionAsignaturaOld)) {
                ProgramacionEspecifica oldProgramacionEspecificaOfProgramacionAsignatura = programacionAsignaturaNew.getProgramacionEspecifica();
                if (oldProgramacionEspecificaOfProgramacionAsignatura != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The ProgramacionAsignatura " + programacionAsignaturaNew + " already has an item of type ProgramacionEspecifica whose programacionAsignatura column cannot be null. Please make another selection for the programacionAsignatura field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (programacionAsignaturaNew != null) {
                programacionAsignaturaNew = em.getReference(programacionAsignaturaNew.getClass(), programacionAsignaturaNew.getIdProgramacionasignatura());
                programacionEspecifica.setProgramacionAsignatura(programacionAsignaturaNew);
            }
            if (idCalendarioasignaturaNew != null) {
                idCalendarioasignaturaNew = em.getReference(idCalendarioasignaturaNew.getClass(), idCalendarioasignaturaNew.getIdCalendarioasignatura());
                programacionEspecifica.setIdCalendarioasignatura(idCalendarioasignaturaNew);
            }
            programacionEspecifica = em.merge(programacionEspecifica);
            if (programacionAsignaturaOld != null && !programacionAsignaturaOld.equals(programacionAsignaturaNew)) {
                programacionAsignaturaOld.setProgramacionEspecifica(null);
                programacionAsignaturaOld = em.merge(programacionAsignaturaOld);
            }
            if (programacionAsignaturaNew != null && !programacionAsignaturaNew.equals(programacionAsignaturaOld)) {
                programacionAsignaturaNew.setProgramacionEspecifica(programacionEspecifica);
                programacionAsignaturaNew = em.merge(programacionAsignaturaNew);
            }
            if (idCalendarioasignaturaOld != null && !idCalendarioasignaturaOld.equals(idCalendarioasignaturaNew)) {
                idCalendarioasignaturaOld.getProgramacionEspecificaList().remove(programacionEspecifica);
                idCalendarioasignaturaOld = em.merge(idCalendarioasignaturaOld);
            }
            if (idCalendarioasignaturaNew != null && !idCalendarioasignaturaNew.equals(idCalendarioasignaturaOld)) {
                idCalendarioasignaturaNew.getProgramacionEspecificaList().add(programacionEspecifica);
                idCalendarioasignaturaNew = em.merge(idCalendarioasignaturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = programacionEspecifica.getIdProgramacionasignatura();
                if (findProgramacionEspecifica(id) == null) {
                    throw new NonexistentEntityException("The programacionEspecifica with id " + id + " no longer exists.");
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
            ProgramacionEspecifica programacionEspecifica;
            try {
                programacionEspecifica = em.getReference(ProgramacionEspecifica.class, id);
                programacionEspecifica.getIdProgramacionasignatura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programacionEspecifica with id " + id + " no longer exists.", enfe);
            }
            ProgramacionAsignatura programacionAsignatura = programacionEspecifica.getProgramacionAsignatura();
            if (programacionAsignatura != null) {
                programacionAsignatura.setProgramacionEspecifica(null);
                programacionAsignatura = em.merge(programacionAsignatura);
            }
            CalendarioAsignatura idCalendarioasignatura = programacionEspecifica.getIdCalendarioasignatura();
            if (idCalendarioasignatura != null) {
                idCalendarioasignatura.getProgramacionEspecificaList().remove(programacionEspecifica);
                idCalendarioasignatura = em.merge(idCalendarioasignatura);
            }
            em.remove(programacionEspecifica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProgramacionEspecifica> findProgramacionEspecificaEntities() {
        return findProgramacionEspecificaEntities(true, -1, -1);
    }

    public List<ProgramacionEspecifica> findProgramacionEspecificaEntities(int maxResults, int firstResult) {
        return findProgramacionEspecificaEntities(false, maxResults, firstResult);
    }

    private List<ProgramacionEspecifica> findProgramacionEspecificaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ProgramacionEspecifica as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ProgramacionEspecifica findProgramacionEspecifica(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProgramacionEspecifica.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramacionEspecificaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ProgramacionEspecifica as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
