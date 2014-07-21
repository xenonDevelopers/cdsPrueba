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
import com.utez.integracion.entity.Programacion;
import com.utez.integracion.entity.ProgramacionCancelada;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ProgramacionCanceladaJpaController implements Serializable {

    public ProgramacionCanceladaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProgramacionCancelada programacionCancelada) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Programacion programacionOrphanCheck = programacionCancelada.getProgramacion();
        if (programacionOrphanCheck != null) {
            ProgramacionCancelada oldProgramacionCanceladaOfProgramacion = programacionOrphanCheck.getProgramacionCancelada();
            if (oldProgramacionCanceladaOfProgramacion != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Programacion " + programacionOrphanCheck + " already has an item of type ProgramacionCancelada whose programacion column cannot be null. Please make another selection for the programacion field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programacion programacion = programacionCancelada.getProgramacion();
            if (programacion != null) {
                programacion = em.getReference(programacion.getClass(), programacion.getIdProgramacion());
                programacionCancelada.setProgramacion(programacion);
            }
            em.persist(programacionCancelada);
            if (programacion != null) {
                programacion.setProgramacionCancelada(programacionCancelada);
                programacion = em.merge(programacion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProgramacionCancelada(programacionCancelada.getIdProgramacion()) != null) {
                throw new PreexistingEntityException("ProgramacionCancelada " + programacionCancelada + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProgramacionCancelada programacionCancelada) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProgramacionCancelada persistentProgramacionCancelada = em.find(ProgramacionCancelada.class, programacionCancelada.getIdProgramacion());
            Programacion programacionOld = persistentProgramacionCancelada.getProgramacion();
            Programacion programacionNew = programacionCancelada.getProgramacion();
            List<String> illegalOrphanMessages = null;
            if (programacionNew != null && !programacionNew.equals(programacionOld)) {
                ProgramacionCancelada oldProgramacionCanceladaOfProgramacion = programacionNew.getProgramacionCancelada();
                if (oldProgramacionCanceladaOfProgramacion != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Programacion " + programacionNew + " already has an item of type ProgramacionCancelada whose programacion column cannot be null. Please make another selection for the programacion field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (programacionNew != null) {
                programacionNew = em.getReference(programacionNew.getClass(), programacionNew.getIdProgramacion());
                programacionCancelada.setProgramacion(programacionNew);
            }
            programacionCancelada = em.merge(programacionCancelada);
            if (programacionOld != null && !programacionOld.equals(programacionNew)) {
                programacionOld.setProgramacionCancelada(null);
                programacionOld = em.merge(programacionOld);
            }
            if (programacionNew != null && !programacionNew.equals(programacionOld)) {
                programacionNew.setProgramacionCancelada(programacionCancelada);
                programacionNew = em.merge(programacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = programacionCancelada.getIdProgramacion();
                if (findProgramacionCancelada(id) == null) {
                    throw new NonexistentEntityException("The programacionCancelada with id " + id + " no longer exists.");
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
            ProgramacionCancelada programacionCancelada;
            try {
                programacionCancelada = em.getReference(ProgramacionCancelada.class, id);
                programacionCancelada.getIdProgramacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programacionCancelada with id " + id + " no longer exists.", enfe);
            }
            Programacion programacion = programacionCancelada.getProgramacion();
            if (programacion != null) {
                programacion.setProgramacionCancelada(null);
                programacion = em.merge(programacion);
            }
            em.remove(programacionCancelada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProgramacionCancelada> findProgramacionCanceladaEntities() {
        return findProgramacionCanceladaEntities(true, -1, -1);
    }

    public List<ProgramacionCancelada> findProgramacionCanceladaEntities(int maxResults, int firstResult) {
        return findProgramacionCanceladaEntities(false, maxResults, firstResult);
    }

    private List<ProgramacionCancelada> findProgramacionCanceladaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ProgramacionCancelada as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ProgramacionCancelada findProgramacionCancelada(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProgramacionCancelada.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramacionCanceladaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ProgramacionCancelada as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
