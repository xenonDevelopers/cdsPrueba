/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.IllegalOrphanException;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Cuadernoprogramacion;
import com.utez.secretariaAcademica.entity.Programacionra;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ProgramacionraJpaController implements Serializable {

    public ProgramacionraJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Programacionra programacionra) throws IllegalOrphanException {
        List<String> illegalOrphanMessages = null;
        Cuadernoprogramacion cuadernoprogramacionOrphanCheck = programacionra.getCuadernoprogramacion();
        if (cuadernoprogramacionOrphanCheck != null) {
            Programacionra oldProgramacionraOfCuadernoprogramacion = cuadernoprogramacionOrphanCheck.getProgramacionra();
            if (oldProgramacionraOfCuadernoprogramacion != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Cuadernoprogramacion " + cuadernoprogramacionOrphanCheck + " already has an item of type Programacionra whose cuadernoprogramacion column cannot be null. Please make another selection for the cuadernoprogramacion field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuadernoprogramacion cuadernoprogramacion = programacionra.getCuadernoprogramacion();
            if (cuadernoprogramacion != null) {
                cuadernoprogramacion = em.getReference(cuadernoprogramacion.getClass(), cuadernoprogramacion.getIdcuadernoprogramacion());
                programacionra.setCuadernoprogramacion(cuadernoprogramacion);
            }
            em.persist(programacionra);
            if (cuadernoprogramacion != null) {
                cuadernoprogramacion.setProgramacionra(programacionra);
                cuadernoprogramacion = em.merge(cuadernoprogramacion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Programacionra programacionra) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programacionra persistentProgramacionra = em.find(Programacionra.class, programacionra.getIdprogramacionra());
            Cuadernoprogramacion cuadernoprogramacionOld = persistentProgramacionra.getCuadernoprogramacion();
            Cuadernoprogramacion cuadernoprogramacionNew = programacionra.getCuadernoprogramacion();
            List<String> illegalOrphanMessages = null;
            if (cuadernoprogramacionNew != null && !cuadernoprogramacionNew.equals(cuadernoprogramacionOld)) {
                Programacionra oldProgramacionraOfCuadernoprogramacion = cuadernoprogramacionNew.getProgramacionra();
                if (oldProgramacionraOfCuadernoprogramacion != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Cuadernoprogramacion " + cuadernoprogramacionNew + " already has an item of type Programacionra whose cuadernoprogramacion column cannot be null. Please make another selection for the cuadernoprogramacion field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cuadernoprogramacionNew != null) {
                cuadernoprogramacionNew = em.getReference(cuadernoprogramacionNew.getClass(), cuadernoprogramacionNew.getIdcuadernoprogramacion());
                programacionra.setCuadernoprogramacion(cuadernoprogramacionNew);
            }
            programacionra = em.merge(programacionra);
            if (cuadernoprogramacionOld != null && !cuadernoprogramacionOld.equals(cuadernoprogramacionNew)) {
                cuadernoprogramacionOld.setProgramacionra(null);
                cuadernoprogramacionOld = em.merge(cuadernoprogramacionOld);
            }
            if (cuadernoprogramacionNew != null && !cuadernoprogramacionNew.equals(cuadernoprogramacionOld)) {
                cuadernoprogramacionNew.setProgramacionra(programacionra);
                cuadernoprogramacionNew = em.merge(cuadernoprogramacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = programacionra.getIdprogramacionra();
                if (findProgramacionra(id) == null) {
                    throw new NonexistentEntityException("The programacionra with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programacionra programacionra;
            try {
                programacionra = em.getReference(Programacionra.class, id);
                programacionra.getIdprogramacionra();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programacionra with id " + id + " no longer exists.", enfe);
            }
            Cuadernoprogramacion cuadernoprogramacion = programacionra.getCuadernoprogramacion();
            if (cuadernoprogramacion != null) {
                cuadernoprogramacion.setProgramacionra(null);
                cuadernoprogramacion = em.merge(cuadernoprogramacion);
            }
            em.remove(programacionra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Programacionra> findProgramacionraEntities() {
        return findProgramacionraEntities(true, -1, -1);
    }

    public List<Programacionra> findProgramacionraEntities(int maxResults, int firstResult) {
        return findProgramacionraEntities(false, maxResults, firstResult);
    }

    private List<Programacionra> findProgramacionraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Programacionra as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Programacionra findProgramacionra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Programacionra.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramacionraCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Programacionra as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
