/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Cuadernoprogramacion;
import com.utez.secretariaAcademica.entity.Programacioncancelada;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ProgramacioncanceladaJpaController implements Serializable {

    public ProgramacioncanceladaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Programacioncancelada programacioncancelada) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuadernoprogramacion idcuadernoprogramacion = programacioncancelada.getIdcuadernoprogramacion();
            if (idcuadernoprogramacion != null) {
                idcuadernoprogramacion = em.getReference(idcuadernoprogramacion.getClass(), idcuadernoprogramacion.getIdcuadernoprogramacion());
                programacioncancelada.setIdcuadernoprogramacion(idcuadernoprogramacion);
            }
            em.persist(programacioncancelada);
            if (idcuadernoprogramacion != null) {
                idcuadernoprogramacion.getProgramacioncanceladaList().add(programacioncancelada);
                idcuadernoprogramacion = em.merge(idcuadernoprogramacion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Programacioncancelada programacioncancelada) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programacioncancelada persistentProgramacioncancelada = em.find(Programacioncancelada.class, programacioncancelada.getIdprogramacioncancelada());
            Cuadernoprogramacion idcuadernoprogramacionOld = persistentProgramacioncancelada.getIdcuadernoprogramacion();
            Cuadernoprogramacion idcuadernoprogramacionNew = programacioncancelada.getIdcuadernoprogramacion();
            if (idcuadernoprogramacionNew != null) {
                idcuadernoprogramacionNew = em.getReference(idcuadernoprogramacionNew.getClass(), idcuadernoprogramacionNew.getIdcuadernoprogramacion());
                programacioncancelada.setIdcuadernoprogramacion(idcuadernoprogramacionNew);
            }
            programacioncancelada = em.merge(programacioncancelada);
            if (idcuadernoprogramacionOld != null && !idcuadernoprogramacionOld.equals(idcuadernoprogramacionNew)) {
                idcuadernoprogramacionOld.getProgramacioncanceladaList().remove(programacioncancelada);
                idcuadernoprogramacionOld = em.merge(idcuadernoprogramacionOld);
            }
            if (idcuadernoprogramacionNew != null && !idcuadernoprogramacionNew.equals(idcuadernoprogramacionOld)) {
                idcuadernoprogramacionNew.getProgramacioncanceladaList().add(programacioncancelada);
                idcuadernoprogramacionNew = em.merge(idcuadernoprogramacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = programacioncancelada.getIdprogramacioncancelada();
                if (findProgramacioncancelada(id) == null) {
                    throw new NonexistentEntityException("The programacioncancelada with id " + id + " no longer exists.");
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
            Programacioncancelada programacioncancelada;
            try {
                programacioncancelada = em.getReference(Programacioncancelada.class, id);
                programacioncancelada.getIdprogramacioncancelada();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programacioncancelada with id " + id + " no longer exists.", enfe);
            }
            Cuadernoprogramacion idcuadernoprogramacion = programacioncancelada.getIdcuadernoprogramacion();
            if (idcuadernoprogramacion != null) {
                idcuadernoprogramacion.getProgramacioncanceladaList().remove(programacioncancelada);
                idcuadernoprogramacion = em.merge(idcuadernoprogramacion);
            }
            em.remove(programacioncancelada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Programacioncancelada> findProgramacioncanceladaEntities() {
        return findProgramacioncanceladaEntities(true, -1, -1);
    }

    public List<Programacioncancelada> findProgramacioncanceladaEntities(int maxResults, int firstResult) {
        return findProgramacioncanceladaEntities(false, maxResults, firstResult);
    }

    private List<Programacioncancelada> findProgramacioncanceladaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Programacioncancelada as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Programacioncancelada findProgramacioncancelada(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Programacioncancelada.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramacioncanceladaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Programacioncancelada as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
