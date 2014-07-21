/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Plantel;
import com.utez.secretariaAcademica.entity.Fechasexamenopcionc;
import com.utez.secretariaAcademica.entity.Mesopcionc;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class MesopcioncJpaController implements Serializable {

    public MesopcioncJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mesopcionc mesopcionc) {
        if (mesopcionc.getFechasexamenopcioncList() == null) {
            mesopcionc.setFechasexamenopcioncList(new ArrayList<Fechasexamenopcionc>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Plantel idplantel = mesopcionc.getIdplantel();
            if (idplantel != null) {
                idplantel = em.getReference(idplantel.getClass(), idplantel.getIdplantel());
                mesopcionc.setIdplantel(idplantel);
            }
            List<Fechasexamenopcionc> attachedFechasexamenopcioncList = new ArrayList<Fechasexamenopcionc>();
            for (Fechasexamenopcionc fechasexamenopcioncListFechasexamenopcioncToAttach : mesopcionc.getFechasexamenopcioncList()) {
                fechasexamenopcioncListFechasexamenopcioncToAttach = em.getReference(fechasexamenopcioncListFechasexamenopcioncToAttach.getClass(), fechasexamenopcioncListFechasexamenopcioncToAttach.getIdfechasexamenopcionc());
                attachedFechasexamenopcioncList.add(fechasexamenopcioncListFechasexamenopcioncToAttach);
            }
            mesopcionc.setFechasexamenopcioncList(attachedFechasexamenopcioncList);
            em.persist(mesopcionc);
            if (idplantel != null) {
                idplantel.getMesopcioncList().add(mesopcionc);
                idplantel = em.merge(idplantel);
            }
            for (Fechasexamenopcionc fechasexamenopcioncListFechasexamenopcionc : mesopcionc.getFechasexamenopcioncList()) {
                Mesopcionc oldIdmesopcioncOfFechasexamenopcioncListFechasexamenopcionc = fechasexamenopcioncListFechasexamenopcionc.getIdmesopcionc();
                fechasexamenopcioncListFechasexamenopcionc.setIdmesopcionc(mesopcionc);
                fechasexamenopcioncListFechasexamenopcionc = em.merge(fechasexamenopcioncListFechasexamenopcionc);
                if (oldIdmesopcioncOfFechasexamenopcioncListFechasexamenopcionc != null) {
                    oldIdmesopcioncOfFechasexamenopcioncListFechasexamenopcionc.getFechasexamenopcioncList().remove(fechasexamenopcioncListFechasexamenopcionc);
                    oldIdmesopcioncOfFechasexamenopcioncListFechasexamenopcionc = em.merge(oldIdmesopcioncOfFechasexamenopcioncListFechasexamenopcionc);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mesopcionc mesopcionc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mesopcionc persistentMesopcionc = em.find(Mesopcionc.class, mesopcionc.getIdmesopcionc());
            Plantel idplantelOld = persistentMesopcionc.getIdplantel();
            Plantel idplantelNew = mesopcionc.getIdplantel();
            List<Fechasexamenopcionc> fechasexamenopcioncListOld = persistentMesopcionc.getFechasexamenopcioncList();
            List<Fechasexamenopcionc> fechasexamenopcioncListNew = mesopcionc.getFechasexamenopcioncList();
            if (idplantelNew != null) {
                idplantelNew = em.getReference(idplantelNew.getClass(), idplantelNew.getIdplantel());
                mesopcionc.setIdplantel(idplantelNew);
            }
            List<Fechasexamenopcionc> attachedFechasexamenopcioncListNew = new ArrayList<Fechasexamenopcionc>();
            for (Fechasexamenopcionc fechasexamenopcioncListNewFechasexamenopcioncToAttach : fechasexamenopcioncListNew) {
                fechasexamenopcioncListNewFechasexamenopcioncToAttach = em.getReference(fechasexamenopcioncListNewFechasexamenopcioncToAttach.getClass(), fechasexamenopcioncListNewFechasexamenopcioncToAttach.getIdfechasexamenopcionc());
                attachedFechasexamenopcioncListNew.add(fechasexamenopcioncListNewFechasexamenopcioncToAttach);
            }
            fechasexamenopcioncListNew = attachedFechasexamenopcioncListNew;
            mesopcionc.setFechasexamenopcioncList(fechasexamenopcioncListNew);
            mesopcionc = em.merge(mesopcionc);
            if (idplantelOld != null && !idplantelOld.equals(idplantelNew)) {
                idplantelOld.getMesopcioncList().remove(mesopcionc);
                idplantelOld = em.merge(idplantelOld);
            }
            if (idplantelNew != null && !idplantelNew.equals(idplantelOld)) {
                idplantelNew.getMesopcioncList().add(mesopcionc);
                idplantelNew = em.merge(idplantelNew);
            }
            for (Fechasexamenopcionc fechasexamenopcioncListOldFechasexamenopcionc : fechasexamenopcioncListOld) {
                if (!fechasexamenopcioncListNew.contains(fechasexamenopcioncListOldFechasexamenopcionc)) {
                    fechasexamenopcioncListOldFechasexamenopcionc.setIdmesopcionc(null);
                    fechasexamenopcioncListOldFechasexamenopcionc = em.merge(fechasexamenopcioncListOldFechasexamenopcionc);
                }
            }
            for (Fechasexamenopcionc fechasexamenopcioncListNewFechasexamenopcionc : fechasexamenopcioncListNew) {
                if (!fechasexamenopcioncListOld.contains(fechasexamenopcioncListNewFechasexamenopcionc)) {
                    Mesopcionc oldIdmesopcioncOfFechasexamenopcioncListNewFechasexamenopcionc = fechasexamenopcioncListNewFechasexamenopcionc.getIdmesopcionc();
                    fechasexamenopcioncListNewFechasexamenopcionc.setIdmesopcionc(mesopcionc);
                    fechasexamenopcioncListNewFechasexamenopcionc = em.merge(fechasexamenopcioncListNewFechasexamenopcionc);
                    if (oldIdmesopcioncOfFechasexamenopcioncListNewFechasexamenopcionc != null && !oldIdmesopcioncOfFechasexamenopcioncListNewFechasexamenopcionc.equals(mesopcionc)) {
                        oldIdmesopcioncOfFechasexamenopcioncListNewFechasexamenopcionc.getFechasexamenopcioncList().remove(fechasexamenopcioncListNewFechasexamenopcionc);
                        oldIdmesopcioncOfFechasexamenopcioncListNewFechasexamenopcionc = em.merge(oldIdmesopcioncOfFechasexamenopcioncListNewFechasexamenopcionc);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mesopcionc.getIdmesopcionc();
                if (findMesopcionc(id) == null) {
                    throw new NonexistentEntityException("The mesopcionc with id " + id + " no longer exists.");
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
            Mesopcionc mesopcionc;
            try {
                mesopcionc = em.getReference(Mesopcionc.class, id);
                mesopcionc.getIdmesopcionc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mesopcionc with id " + id + " no longer exists.", enfe);
            }
            Plantel idplantel = mesopcionc.getIdplantel();
            if (idplantel != null) {
                idplantel.getMesopcioncList().remove(mesopcionc);
                idplantel = em.merge(idplantel);
            }
            List<Fechasexamenopcionc> fechasexamenopcioncList = mesopcionc.getFechasexamenopcioncList();
            for (Fechasexamenopcionc fechasexamenopcioncListFechasexamenopcionc : fechasexamenopcioncList) {
                fechasexamenopcioncListFechasexamenopcionc.setIdmesopcionc(null);
                fechasexamenopcioncListFechasexamenopcionc = em.merge(fechasexamenopcioncListFechasexamenopcionc);
            }
            em.remove(mesopcionc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mesopcionc> findMesopcioncEntities() {
        return findMesopcioncEntities(true, -1, -1);
    }

    public List<Mesopcionc> findMesopcioncEntities(int maxResults, int firstResult) {
        return findMesopcioncEntities(false, maxResults, firstResult);
    }

    private List<Mesopcionc> findMesopcioncEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Mesopcionc as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Mesopcionc findMesopcionc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mesopcionc.class, id);
        } finally {
            em.close();
        }
    }

    public int getMesopcioncCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Mesopcionc as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
