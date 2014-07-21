/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.IllegalOrphanException;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secretariaAcademica.entity.Fechaasesoriabloquelinea;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Fechasexamenbloque;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class FechaasesoriabloquelineaJpaController implements Serializable {

    public FechaasesoriabloquelineaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fechaasesoriabloquelinea fechaasesoriabloquelinea) {
        if (fechaasesoriabloquelinea.getFechasexamenbloqueList() == null) {
            fechaasesoriabloquelinea.setFechasexamenbloqueList(new ArrayList<Fechasexamenbloque>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Fechasexamenbloque> attachedFechasexamenbloqueList = new ArrayList<Fechasexamenbloque>();
            for (Fechasexamenbloque fechasexamenbloqueListFechasexamenbloqueToAttach : fechaasesoriabloquelinea.getFechasexamenbloqueList()) {
                fechasexamenbloqueListFechasexamenbloqueToAttach = em.getReference(fechasexamenbloqueListFechasexamenbloqueToAttach.getClass(), fechasexamenbloqueListFechasexamenbloqueToAttach.getIdfechasexamenbloque());
                attachedFechasexamenbloqueList.add(fechasexamenbloqueListFechasexamenbloqueToAttach);
            }
            fechaasesoriabloquelinea.setFechasexamenbloqueList(attachedFechasexamenbloqueList);
            em.persist(fechaasesoriabloquelinea);
            for (Fechasexamenbloque fechasexamenbloqueListFechasexamenbloque : fechaasesoriabloquelinea.getFechasexamenbloqueList()) {
                Fechaasesoriabloquelinea oldIdfechaaseseoriabloqueOfFechasexamenbloqueListFechasexamenbloque = fechasexamenbloqueListFechasexamenbloque.getIdfechaaseseoriabloque();
                fechasexamenbloqueListFechasexamenbloque.setIdfechaaseseoriabloque(fechaasesoriabloquelinea);
                fechasexamenbloqueListFechasexamenbloque = em.merge(fechasexamenbloqueListFechasexamenbloque);
                if (oldIdfechaaseseoriabloqueOfFechasexamenbloqueListFechasexamenbloque != null) {
                    oldIdfechaaseseoriabloqueOfFechasexamenbloqueListFechasexamenbloque.getFechasexamenbloqueList().remove(fechasexamenbloqueListFechasexamenbloque);
                    oldIdfechaaseseoriabloqueOfFechasexamenbloqueListFechasexamenbloque = em.merge(oldIdfechaaseseoriabloqueOfFechasexamenbloqueListFechasexamenbloque);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fechaasesoriabloquelinea fechaasesoriabloquelinea) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fechaasesoriabloquelinea persistentFechaasesoriabloquelinea = em.find(Fechaasesoriabloquelinea.class, fechaasesoriabloquelinea.getIdfechaaseseoriabloque());
            List<Fechasexamenbloque> fechasexamenbloqueListOld = persistentFechaasesoriabloquelinea.getFechasexamenbloqueList();
            List<Fechasexamenbloque> fechasexamenbloqueListNew = fechaasesoriabloquelinea.getFechasexamenbloqueList();
            List<String> illegalOrphanMessages = null;
            for (Fechasexamenbloque fechasexamenbloqueListOldFechasexamenbloque : fechasexamenbloqueListOld) {
                if (!fechasexamenbloqueListNew.contains(fechasexamenbloqueListOldFechasexamenbloque)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Fechasexamenbloque " + fechasexamenbloqueListOldFechasexamenbloque + " since its idfechaaseseoriabloque field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Fechasexamenbloque> attachedFechasexamenbloqueListNew = new ArrayList<Fechasexamenbloque>();
            for (Fechasexamenbloque fechasexamenbloqueListNewFechasexamenbloqueToAttach : fechasexamenbloqueListNew) {
                fechasexamenbloqueListNewFechasexamenbloqueToAttach = em.getReference(fechasexamenbloqueListNewFechasexamenbloqueToAttach.getClass(), fechasexamenbloqueListNewFechasexamenbloqueToAttach.getIdfechasexamenbloque());
                attachedFechasexamenbloqueListNew.add(fechasexamenbloqueListNewFechasexamenbloqueToAttach);
            }
            fechasexamenbloqueListNew = attachedFechasexamenbloqueListNew;
            fechaasesoriabloquelinea.setFechasexamenbloqueList(fechasexamenbloqueListNew);
            fechaasesoriabloquelinea = em.merge(fechaasesoriabloquelinea);
            for (Fechasexamenbloque fechasexamenbloqueListNewFechasexamenbloque : fechasexamenbloqueListNew) {
                if (!fechasexamenbloqueListOld.contains(fechasexamenbloqueListNewFechasexamenbloque)) {
                    Fechaasesoriabloquelinea oldIdfechaaseseoriabloqueOfFechasexamenbloqueListNewFechasexamenbloque = fechasexamenbloqueListNewFechasexamenbloque.getIdfechaaseseoriabloque();
                    fechasexamenbloqueListNewFechasexamenbloque.setIdfechaaseseoriabloque(fechaasesoriabloquelinea);
                    fechasexamenbloqueListNewFechasexamenbloque = em.merge(fechasexamenbloqueListNewFechasexamenbloque);
                    if (oldIdfechaaseseoriabloqueOfFechasexamenbloqueListNewFechasexamenbloque != null && !oldIdfechaaseseoriabloqueOfFechasexamenbloqueListNewFechasexamenbloque.equals(fechaasesoriabloquelinea)) {
                        oldIdfechaaseseoriabloqueOfFechasexamenbloqueListNewFechasexamenbloque.getFechasexamenbloqueList().remove(fechasexamenbloqueListNewFechasexamenbloque);
                        oldIdfechaaseseoriabloqueOfFechasexamenbloqueListNewFechasexamenbloque = em.merge(oldIdfechaaseseoriabloqueOfFechasexamenbloqueListNewFechasexamenbloque);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fechaasesoriabloquelinea.getIdfechaaseseoriabloque();
                if (findFechaasesoriabloquelinea(id) == null) {
                    throw new NonexistentEntityException("The fechaasesoriabloquelinea with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fechaasesoriabloquelinea fechaasesoriabloquelinea;
            try {
                fechaasesoriabloquelinea = em.getReference(Fechaasesoriabloquelinea.class, id);
                fechaasesoriabloquelinea.getIdfechaaseseoriabloque();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fechaasesoriabloquelinea with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Fechasexamenbloque> fechasexamenbloqueListOrphanCheck = fechaasesoriabloquelinea.getFechasexamenbloqueList();
            for (Fechasexamenbloque fechasexamenbloqueListOrphanCheckFechasexamenbloque : fechasexamenbloqueListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Fechaasesoriabloquelinea (" + fechaasesoriabloquelinea + ") cannot be destroyed since the Fechasexamenbloque " + fechasexamenbloqueListOrphanCheckFechasexamenbloque + " in its fechasexamenbloqueList field has a non-nullable idfechaaseseoriabloque field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(fechaasesoriabloquelinea);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fechaasesoriabloquelinea> findFechaasesoriabloquelineaEntities() {
        return findFechaasesoriabloquelineaEntities(true, -1, -1);
    }

    public List<Fechaasesoriabloquelinea> findFechaasesoriabloquelineaEntities(int maxResults, int firstResult) {
        return findFechaasesoriabloquelineaEntities(false, maxResults, firstResult);
    }

    private List<Fechaasesoriabloquelinea> findFechaasesoriabloquelineaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Fechaasesoriabloquelinea as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Fechaasesoriabloquelinea findFechaasesoriabloquelinea(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fechaasesoriabloquelinea.class, id);
        } finally {
            em.close();
        }
    }

    public int getFechaasesoriabloquelineaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Fechaasesoriabloquelinea as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
