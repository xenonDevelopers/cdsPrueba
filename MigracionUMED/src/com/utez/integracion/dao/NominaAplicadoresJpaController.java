/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.AsignacionAplicador;
import com.utez.integracion.entity.NominaAplicadores;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class NominaAplicadoresJpaController implements Serializable {

    public NominaAplicadoresJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NominaAplicadores nominaAplicadores) {
        if (nominaAplicadores.getAsignacionAplicadorList() == null) {
            nominaAplicadores.setAsignacionAplicadorList(new ArrayList<AsignacionAplicador>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<AsignacionAplicador> attachedAsignacionAplicadorList = new ArrayList<AsignacionAplicador>();
            for (AsignacionAplicador asignacionAplicadorListAsignacionAplicadorToAttach : nominaAplicadores.getAsignacionAplicadorList()) {
                asignacionAplicadorListAsignacionAplicadorToAttach = em.getReference(asignacionAplicadorListAsignacionAplicadorToAttach.getClass(), asignacionAplicadorListAsignacionAplicadorToAttach.getIdAsignacionaplicador());
                attachedAsignacionAplicadorList.add(asignacionAplicadorListAsignacionAplicadorToAttach);
            }
            nominaAplicadores.setAsignacionAplicadorList(attachedAsignacionAplicadorList);
            em.persist(nominaAplicadores);
            for (AsignacionAplicador asignacionAplicadorListAsignacionAplicador : nominaAplicadores.getAsignacionAplicadorList()) {
                asignacionAplicadorListAsignacionAplicador.getNominaAplicadoresList().add(nominaAplicadores);
                asignacionAplicadorListAsignacionAplicador = em.merge(asignacionAplicadorListAsignacionAplicador);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NominaAplicadores nominaAplicadores) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NominaAplicadores persistentNominaAplicadores = em.find(NominaAplicadores.class, nominaAplicadores.getIdNominaaplicadores());
            List<AsignacionAplicador> asignacionAplicadorListOld = persistentNominaAplicadores.getAsignacionAplicadorList();
            List<AsignacionAplicador> asignacionAplicadorListNew = nominaAplicadores.getAsignacionAplicadorList();
            List<AsignacionAplicador> attachedAsignacionAplicadorListNew = new ArrayList<AsignacionAplicador>();
            for (AsignacionAplicador asignacionAplicadorListNewAsignacionAplicadorToAttach : asignacionAplicadorListNew) {
                asignacionAplicadorListNewAsignacionAplicadorToAttach = em.getReference(asignacionAplicadorListNewAsignacionAplicadorToAttach.getClass(), asignacionAplicadorListNewAsignacionAplicadorToAttach.getIdAsignacionaplicador());
                attachedAsignacionAplicadorListNew.add(asignacionAplicadorListNewAsignacionAplicadorToAttach);
            }
            asignacionAplicadorListNew = attachedAsignacionAplicadorListNew;
            nominaAplicadores.setAsignacionAplicadorList(asignacionAplicadorListNew);
            nominaAplicadores = em.merge(nominaAplicadores);
            for (AsignacionAplicador asignacionAplicadorListOldAsignacionAplicador : asignacionAplicadorListOld) {
                if (!asignacionAplicadorListNew.contains(asignacionAplicadorListOldAsignacionAplicador)) {
                    asignacionAplicadorListOldAsignacionAplicador.getNominaAplicadoresList().remove(nominaAplicadores);
                    asignacionAplicadorListOldAsignacionAplicador = em.merge(asignacionAplicadorListOldAsignacionAplicador);
                }
            }
            for (AsignacionAplicador asignacionAplicadorListNewAsignacionAplicador : asignacionAplicadorListNew) {
                if (!asignacionAplicadorListOld.contains(asignacionAplicadorListNewAsignacionAplicador)) {
                    asignacionAplicadorListNewAsignacionAplicador.getNominaAplicadoresList().add(nominaAplicadores);
                    asignacionAplicadorListNewAsignacionAplicador = em.merge(asignacionAplicadorListNewAsignacionAplicador);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nominaAplicadores.getIdNominaaplicadores();
                if (findNominaAplicadores(id) == null) {
                    throw new NonexistentEntityException("The nominaAplicadores with id " + id + " no longer exists.");
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
            NominaAplicadores nominaAplicadores;
            try {
                nominaAplicadores = em.getReference(NominaAplicadores.class, id);
                nominaAplicadores.getIdNominaaplicadores();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nominaAplicadores with id " + id + " no longer exists.", enfe);
            }
            List<AsignacionAplicador> asignacionAplicadorList = nominaAplicadores.getAsignacionAplicadorList();
            for (AsignacionAplicador asignacionAplicadorListAsignacionAplicador : asignacionAplicadorList) {
                asignacionAplicadorListAsignacionAplicador.getNominaAplicadoresList().remove(nominaAplicadores);
                asignacionAplicadorListAsignacionAplicador = em.merge(asignacionAplicadorListAsignacionAplicador);
            }
            em.remove(nominaAplicadores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NominaAplicadores> findNominaAplicadoresEntities() {
        return findNominaAplicadoresEntities(true, -1, -1);
    }

    public List<NominaAplicadores> findNominaAplicadoresEntities(int maxResults, int firstResult) {
        return findNominaAplicadoresEntities(false, maxResults, firstResult);
    }

    private List<NominaAplicadores> findNominaAplicadoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from NominaAplicadores as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public NominaAplicadores findNominaAplicadores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NominaAplicadores.class, id);
        } finally {
            em.close();
        }
    }

    public int getNominaAplicadoresCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from NominaAplicadores as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
