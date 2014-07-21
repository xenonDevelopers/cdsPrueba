/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Asesoriaasignatura;
import com.utez.secretariaAcademica.entity.Fechasexamen;
import com.utez.secretariaAcademica.entity.Historicogrupo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class FechasexamenJpaController implements Serializable {

    public FechasexamenJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fechasexamen fechasexamen) {
        if (fechasexamen.getHistoricogrupoList() == null) {
            fechasexamen.setHistoricogrupoList(new ArrayList<Historicogrupo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asesoriaasignatura idasesoriaasignatura = fechasexamen.getIdasesoriaasignatura();
            if (idasesoriaasignatura != null) {
                idasesoriaasignatura = em.getReference(idasesoriaasignatura.getClass(), idasesoriaasignatura.getIdasesoriaasignatura());
                fechasexamen.setIdasesoriaasignatura(idasesoriaasignatura);
            }
            List<Historicogrupo> attachedHistoricogrupoList = new ArrayList<Historicogrupo>();
            for (Historicogrupo historicogrupoListHistoricogrupoToAttach : fechasexamen.getHistoricogrupoList()) {
                historicogrupoListHistoricogrupoToAttach = em.getReference(historicogrupoListHistoricogrupoToAttach.getClass(), historicogrupoListHistoricogrupoToAttach.getIdhistoricogrupo());
                attachedHistoricogrupoList.add(historicogrupoListHistoricogrupoToAttach);
            }
            fechasexamen.setHistoricogrupoList(attachedHistoricogrupoList);
            em.persist(fechasexamen);
            if (idasesoriaasignatura != null) {
                idasesoriaasignatura.getFechasexamenList().add(fechasexamen);
                idasesoriaasignatura = em.merge(idasesoriaasignatura);
            }
            for (Historicogrupo historicogrupoListHistoricogrupo : fechasexamen.getHistoricogrupoList()) {
                Fechasexamen oldIdfechaexamenOfHistoricogrupoListHistoricogrupo = historicogrupoListHistoricogrupo.getIdfechaexamen();
                historicogrupoListHistoricogrupo.setIdfechaexamen(fechasexamen);
                historicogrupoListHistoricogrupo = em.merge(historicogrupoListHistoricogrupo);
                if (oldIdfechaexamenOfHistoricogrupoListHistoricogrupo != null) {
                    oldIdfechaexamenOfHistoricogrupoListHistoricogrupo.getHistoricogrupoList().remove(historicogrupoListHistoricogrupo);
                    oldIdfechaexamenOfHistoricogrupoListHistoricogrupo = em.merge(oldIdfechaexamenOfHistoricogrupoListHistoricogrupo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fechasexamen fechasexamen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fechasexamen persistentFechasexamen = em.find(Fechasexamen.class, fechasexamen.getIdfechaexamen());
            Asesoriaasignatura idasesoriaasignaturaOld = persistentFechasexamen.getIdasesoriaasignatura();
            Asesoriaasignatura idasesoriaasignaturaNew = fechasexamen.getIdasesoriaasignatura();
            List<Historicogrupo> historicogrupoListOld = persistentFechasexamen.getHistoricogrupoList();
            List<Historicogrupo> historicogrupoListNew = fechasexamen.getHistoricogrupoList();
            if (idasesoriaasignaturaNew != null) {
                idasesoriaasignaturaNew = em.getReference(idasesoriaasignaturaNew.getClass(), idasesoriaasignaturaNew.getIdasesoriaasignatura());
                fechasexamen.setIdasesoriaasignatura(idasesoriaasignaturaNew);
            }
            List<Historicogrupo> attachedHistoricogrupoListNew = new ArrayList<Historicogrupo>();
            for (Historicogrupo historicogrupoListNewHistoricogrupoToAttach : historicogrupoListNew) {
                historicogrupoListNewHistoricogrupoToAttach = em.getReference(historicogrupoListNewHistoricogrupoToAttach.getClass(), historicogrupoListNewHistoricogrupoToAttach.getIdhistoricogrupo());
                attachedHistoricogrupoListNew.add(historicogrupoListNewHistoricogrupoToAttach);
            }
            historicogrupoListNew = attachedHistoricogrupoListNew;
            fechasexamen.setHistoricogrupoList(historicogrupoListNew);
            fechasexamen = em.merge(fechasexamen);
            if (idasesoriaasignaturaOld != null && !idasesoriaasignaturaOld.equals(idasesoriaasignaturaNew)) {
                idasesoriaasignaturaOld.getFechasexamenList().remove(fechasexamen);
                idasesoriaasignaturaOld = em.merge(idasesoriaasignaturaOld);
            }
            if (idasesoriaasignaturaNew != null && !idasesoriaasignaturaNew.equals(idasesoriaasignaturaOld)) {
                idasesoriaasignaturaNew.getFechasexamenList().add(fechasexamen);
                idasesoriaasignaturaNew = em.merge(idasesoriaasignaturaNew);
            }
            for (Historicogrupo historicogrupoListOldHistoricogrupo : historicogrupoListOld) {
                if (!historicogrupoListNew.contains(historicogrupoListOldHistoricogrupo)) {
                    historicogrupoListOldHistoricogrupo.setIdfechaexamen(null);
                    historicogrupoListOldHistoricogrupo = em.merge(historicogrupoListOldHistoricogrupo);
                }
            }
            for (Historicogrupo historicogrupoListNewHistoricogrupo : historicogrupoListNew) {
                if (!historicogrupoListOld.contains(historicogrupoListNewHistoricogrupo)) {
                    Fechasexamen oldIdfechaexamenOfHistoricogrupoListNewHistoricogrupo = historicogrupoListNewHistoricogrupo.getIdfechaexamen();
                    historicogrupoListNewHistoricogrupo.setIdfechaexamen(fechasexamen);
                    historicogrupoListNewHistoricogrupo = em.merge(historicogrupoListNewHistoricogrupo);
                    if (oldIdfechaexamenOfHistoricogrupoListNewHistoricogrupo != null && !oldIdfechaexamenOfHistoricogrupoListNewHistoricogrupo.equals(fechasexamen)) {
                        oldIdfechaexamenOfHistoricogrupoListNewHistoricogrupo.getHistoricogrupoList().remove(historicogrupoListNewHistoricogrupo);
                        oldIdfechaexamenOfHistoricogrupoListNewHistoricogrupo = em.merge(oldIdfechaexamenOfHistoricogrupoListNewHistoricogrupo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fechasexamen.getIdfechaexamen();
                if (findFechasexamen(id) == null) {
                    throw new NonexistentEntityException("The fechasexamen with id " + id + " no longer exists.");
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
            Fechasexamen fechasexamen;
            try {
                fechasexamen = em.getReference(Fechasexamen.class, id);
                fechasexamen.getIdfechaexamen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fechasexamen with id " + id + " no longer exists.", enfe);
            }
            Asesoriaasignatura idasesoriaasignatura = fechasexamen.getIdasesoriaasignatura();
            if (idasesoriaasignatura != null) {
                idasesoriaasignatura.getFechasexamenList().remove(fechasexamen);
                idasesoriaasignatura = em.merge(idasesoriaasignatura);
            }
            List<Historicogrupo> historicogrupoList = fechasexamen.getHistoricogrupoList();
            for (Historicogrupo historicogrupoListHistoricogrupo : historicogrupoList) {
                historicogrupoListHistoricogrupo.setIdfechaexamen(null);
                historicogrupoListHistoricogrupo = em.merge(historicogrupoListHistoricogrupo);
            }
            em.remove(fechasexamen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fechasexamen> findFechasexamenEntities() {
        return findFechasexamenEntities(true, -1, -1);
    }

    public List<Fechasexamen> findFechasexamenEntities(int maxResults, int firstResult) {
        return findFechasexamenEntities(false, maxResults, firstResult);
    }

    private List<Fechasexamen> findFechasexamenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Fechasexamen as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Fechasexamen findFechasexamen(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fechasexamen.class, id);
        } finally {
            em.close();
        }
    }

    public int getFechasexamenCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Fechasexamen as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
