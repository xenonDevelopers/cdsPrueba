/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.AsignaturaOpcionc;
import com.utez.integracion.entity.MesOpcionc;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class MesOpcioncJpaController implements Serializable {

    public MesOpcioncJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MesOpcionc mesOpcionc) {
        if (mesOpcionc.getAsignaturaOpcioncList() == null) {
            mesOpcionc.setAsignaturaOpcioncList(new ArrayList<AsignaturaOpcionc>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<AsignaturaOpcionc> attachedAsignaturaOpcioncList = new ArrayList<AsignaturaOpcionc>();
            for (AsignaturaOpcionc asignaturaOpcioncListAsignaturaOpcioncToAttach : mesOpcionc.getAsignaturaOpcioncList()) {
                asignaturaOpcioncListAsignaturaOpcioncToAttach = em.getReference(asignaturaOpcioncListAsignaturaOpcioncToAttach.getClass(), asignaturaOpcioncListAsignaturaOpcioncToAttach.getIdAsignaturaopcionc());
                attachedAsignaturaOpcioncList.add(asignaturaOpcioncListAsignaturaOpcioncToAttach);
            }
            mesOpcionc.setAsignaturaOpcioncList(attachedAsignaturaOpcioncList);
            em.persist(mesOpcionc);
            for (AsignaturaOpcionc asignaturaOpcioncListAsignaturaOpcionc : mesOpcionc.getAsignaturaOpcioncList()) {
                MesOpcionc oldIdMesopcioncOfAsignaturaOpcioncListAsignaturaOpcionc = asignaturaOpcioncListAsignaturaOpcionc.getIdMesopcionc();
                asignaturaOpcioncListAsignaturaOpcionc.setIdMesopcionc(mesOpcionc);
                asignaturaOpcioncListAsignaturaOpcionc = em.merge(asignaturaOpcioncListAsignaturaOpcionc);
                if (oldIdMesopcioncOfAsignaturaOpcioncListAsignaturaOpcionc != null) {
                    oldIdMesopcioncOfAsignaturaOpcioncListAsignaturaOpcionc.getAsignaturaOpcioncList().remove(asignaturaOpcioncListAsignaturaOpcionc);
                    oldIdMesopcioncOfAsignaturaOpcioncListAsignaturaOpcionc = em.merge(oldIdMesopcioncOfAsignaturaOpcioncListAsignaturaOpcionc);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MesOpcionc mesOpcionc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MesOpcionc persistentMesOpcionc = em.find(MesOpcionc.class, mesOpcionc.getIdMesopcionc());
            List<AsignaturaOpcionc> asignaturaOpcioncListOld = persistentMesOpcionc.getAsignaturaOpcioncList();
            List<AsignaturaOpcionc> asignaturaOpcioncListNew = mesOpcionc.getAsignaturaOpcioncList();
            List<AsignaturaOpcionc> attachedAsignaturaOpcioncListNew = new ArrayList<AsignaturaOpcionc>();
            for (AsignaturaOpcionc asignaturaOpcioncListNewAsignaturaOpcioncToAttach : asignaturaOpcioncListNew) {
                asignaturaOpcioncListNewAsignaturaOpcioncToAttach = em.getReference(asignaturaOpcioncListNewAsignaturaOpcioncToAttach.getClass(), asignaturaOpcioncListNewAsignaturaOpcioncToAttach.getIdAsignaturaopcionc());
                attachedAsignaturaOpcioncListNew.add(asignaturaOpcioncListNewAsignaturaOpcioncToAttach);
            }
            asignaturaOpcioncListNew = attachedAsignaturaOpcioncListNew;
            mesOpcionc.setAsignaturaOpcioncList(asignaturaOpcioncListNew);
            mesOpcionc = em.merge(mesOpcionc);
            for (AsignaturaOpcionc asignaturaOpcioncListOldAsignaturaOpcionc : asignaturaOpcioncListOld) {
                if (!asignaturaOpcioncListNew.contains(asignaturaOpcioncListOldAsignaturaOpcionc)) {
                    asignaturaOpcioncListOldAsignaturaOpcionc.setIdMesopcionc(null);
                    asignaturaOpcioncListOldAsignaturaOpcionc = em.merge(asignaturaOpcioncListOldAsignaturaOpcionc);
                }
            }
            for (AsignaturaOpcionc asignaturaOpcioncListNewAsignaturaOpcionc : asignaturaOpcioncListNew) {
                if (!asignaturaOpcioncListOld.contains(asignaturaOpcioncListNewAsignaturaOpcionc)) {
                    MesOpcionc oldIdMesopcioncOfAsignaturaOpcioncListNewAsignaturaOpcionc = asignaturaOpcioncListNewAsignaturaOpcionc.getIdMesopcionc();
                    asignaturaOpcioncListNewAsignaturaOpcionc.setIdMesopcionc(mesOpcionc);
                    asignaturaOpcioncListNewAsignaturaOpcionc = em.merge(asignaturaOpcioncListNewAsignaturaOpcionc);
                    if (oldIdMesopcioncOfAsignaturaOpcioncListNewAsignaturaOpcionc != null && !oldIdMesopcioncOfAsignaturaOpcioncListNewAsignaturaOpcionc.equals(mesOpcionc)) {
                        oldIdMesopcioncOfAsignaturaOpcioncListNewAsignaturaOpcionc.getAsignaturaOpcioncList().remove(asignaturaOpcioncListNewAsignaturaOpcionc);
                        oldIdMesopcioncOfAsignaturaOpcioncListNewAsignaturaOpcionc = em.merge(oldIdMesopcioncOfAsignaturaOpcioncListNewAsignaturaOpcionc);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = mesOpcionc.getIdMesopcionc();
                if (findMesOpcionc(id) == null) {
                    throw new NonexistentEntityException("The mesOpcionc with id " + id + " no longer exists.");
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
            MesOpcionc mesOpcionc;
            try {
                mesOpcionc = em.getReference(MesOpcionc.class, id);
                mesOpcionc.getIdMesopcionc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mesOpcionc with id " + id + " no longer exists.", enfe);
            }
            List<AsignaturaOpcionc> asignaturaOpcioncList = mesOpcionc.getAsignaturaOpcioncList();
            for (AsignaturaOpcionc asignaturaOpcioncListAsignaturaOpcionc : asignaturaOpcioncList) {
                asignaturaOpcioncListAsignaturaOpcionc.setIdMesopcionc(null);
                asignaturaOpcioncListAsignaturaOpcionc = em.merge(asignaturaOpcioncListAsignaturaOpcionc);
            }
            em.remove(mesOpcionc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MesOpcionc> findMesOpcioncEntities() {
        return findMesOpcioncEntities(true, -1, -1);
    }

    public List<MesOpcionc> findMesOpcioncEntities(int maxResults, int firstResult) {
        return findMesOpcioncEntities(false, maxResults, firstResult);
    }

    private List<MesOpcionc> findMesOpcioncEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from MesOpcionc as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public MesOpcionc findMesOpcionc(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MesOpcionc.class, id);
        } finally {
            em.close();
        }
    }

    public int getMesOpcioncCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from MesOpcionc as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
