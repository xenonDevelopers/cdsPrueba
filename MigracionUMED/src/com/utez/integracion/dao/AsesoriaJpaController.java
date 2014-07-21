/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.Asesoria;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.CalendarioAsignatura;
import com.utez.integracion.entity.HistoricoAsesoria;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsesoriaJpaController implements Serializable {

    public AsesoriaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asesoria asesoria) {
        if (asesoria.getHistoricoAsesoriaList() == null) {
            asesoria.setHistoricoAsesoriaList(new ArrayList<HistoricoAsesoria>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CalendarioAsignatura idCalendarioasignatura = asesoria.getIdCalendarioasignatura();
            if (idCalendarioasignatura != null) {
                idCalendarioasignatura = em.getReference(idCalendarioasignatura.getClass(), idCalendarioasignatura.getIdCalendarioasignatura());
                asesoria.setIdCalendarioasignatura(idCalendarioasignatura);
            }
            List<HistoricoAsesoria> attachedHistoricoAsesoriaList = new ArrayList<HistoricoAsesoria>();
            for (HistoricoAsesoria historicoAsesoriaListHistoricoAsesoriaToAttach : asesoria.getHistoricoAsesoriaList()) {
                historicoAsesoriaListHistoricoAsesoriaToAttach = em.getReference(historicoAsesoriaListHistoricoAsesoriaToAttach.getClass(), historicoAsesoriaListHistoricoAsesoriaToAttach.getIdHistoricoasesoria());
                attachedHistoricoAsesoriaList.add(historicoAsesoriaListHistoricoAsesoriaToAttach);
            }
            asesoria.setHistoricoAsesoriaList(attachedHistoricoAsesoriaList);
            em.persist(asesoria);
            if (idCalendarioasignatura != null) {
                idCalendarioasignatura.getAsesoriaList().add(asesoria);
                idCalendarioasignatura = em.merge(idCalendarioasignatura);
            }
            for (HistoricoAsesoria historicoAsesoriaListHistoricoAsesoria : asesoria.getHistoricoAsesoriaList()) {
                Asesoria oldIdAsesoriaOfHistoricoAsesoriaListHistoricoAsesoria = historicoAsesoriaListHistoricoAsesoria.getIdAsesoria();
                historicoAsesoriaListHistoricoAsesoria.setIdAsesoria(asesoria);
                historicoAsesoriaListHistoricoAsesoria = em.merge(historicoAsesoriaListHistoricoAsesoria);
                if (oldIdAsesoriaOfHistoricoAsesoriaListHistoricoAsesoria != null) {
                    oldIdAsesoriaOfHistoricoAsesoriaListHistoricoAsesoria.getHistoricoAsesoriaList().remove(historicoAsesoriaListHistoricoAsesoria);
                    oldIdAsesoriaOfHistoricoAsesoriaListHistoricoAsesoria = em.merge(oldIdAsesoriaOfHistoricoAsesoriaListHistoricoAsesoria);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asesoria asesoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asesoria persistentAsesoria = em.find(Asesoria.class, asesoria.getIdAsesoria());
            CalendarioAsignatura idCalendarioasignaturaOld = persistentAsesoria.getIdCalendarioasignatura();
            CalendarioAsignatura idCalendarioasignaturaNew = asesoria.getIdCalendarioasignatura();
            List<HistoricoAsesoria> historicoAsesoriaListOld = persistentAsesoria.getHistoricoAsesoriaList();
            List<HistoricoAsesoria> historicoAsesoriaListNew = asesoria.getHistoricoAsesoriaList();
            if (idCalendarioasignaturaNew != null) {
                idCalendarioasignaturaNew = em.getReference(idCalendarioasignaturaNew.getClass(), idCalendarioasignaturaNew.getIdCalendarioasignatura());
                asesoria.setIdCalendarioasignatura(idCalendarioasignaturaNew);
            }
            List<HistoricoAsesoria> attachedHistoricoAsesoriaListNew = new ArrayList<HistoricoAsesoria>();
            for (HistoricoAsesoria historicoAsesoriaListNewHistoricoAsesoriaToAttach : historicoAsesoriaListNew) {
                historicoAsesoriaListNewHistoricoAsesoriaToAttach = em.getReference(historicoAsesoriaListNewHistoricoAsesoriaToAttach.getClass(), historicoAsesoriaListNewHistoricoAsesoriaToAttach.getIdHistoricoasesoria());
                attachedHistoricoAsesoriaListNew.add(historicoAsesoriaListNewHistoricoAsesoriaToAttach);
            }
            historicoAsesoriaListNew = attachedHistoricoAsesoriaListNew;
            asesoria.setHistoricoAsesoriaList(historicoAsesoriaListNew);
            asesoria = em.merge(asesoria);
            if (idCalendarioasignaturaOld != null && !idCalendarioasignaturaOld.equals(idCalendarioasignaturaNew)) {
                idCalendarioasignaturaOld.getAsesoriaList().remove(asesoria);
                idCalendarioasignaturaOld = em.merge(idCalendarioasignaturaOld);
            }
            if (idCalendarioasignaturaNew != null && !idCalendarioasignaturaNew.equals(idCalendarioasignaturaOld)) {
                idCalendarioasignaturaNew.getAsesoriaList().add(asesoria);
                idCalendarioasignaturaNew = em.merge(idCalendarioasignaturaNew);
            }
            for (HistoricoAsesoria historicoAsesoriaListOldHistoricoAsesoria : historicoAsesoriaListOld) {
                if (!historicoAsesoriaListNew.contains(historicoAsesoriaListOldHistoricoAsesoria)) {
                    historicoAsesoriaListOldHistoricoAsesoria.setIdAsesoria(null);
                    historicoAsesoriaListOldHistoricoAsesoria = em.merge(historicoAsesoriaListOldHistoricoAsesoria);
                }
            }
            for (HistoricoAsesoria historicoAsesoriaListNewHistoricoAsesoria : historicoAsesoriaListNew) {
                if (!historicoAsesoriaListOld.contains(historicoAsesoriaListNewHistoricoAsesoria)) {
                    Asesoria oldIdAsesoriaOfHistoricoAsesoriaListNewHistoricoAsesoria = historicoAsesoriaListNewHistoricoAsesoria.getIdAsesoria();
                    historicoAsesoriaListNewHistoricoAsesoria.setIdAsesoria(asesoria);
                    historicoAsesoriaListNewHistoricoAsesoria = em.merge(historicoAsesoriaListNewHistoricoAsesoria);
                    if (oldIdAsesoriaOfHistoricoAsesoriaListNewHistoricoAsesoria != null && !oldIdAsesoriaOfHistoricoAsesoriaListNewHistoricoAsesoria.equals(asesoria)) {
                        oldIdAsesoriaOfHistoricoAsesoriaListNewHistoricoAsesoria.getHistoricoAsesoriaList().remove(historicoAsesoriaListNewHistoricoAsesoria);
                        oldIdAsesoriaOfHistoricoAsesoriaListNewHistoricoAsesoria = em.merge(oldIdAsesoriaOfHistoricoAsesoriaListNewHistoricoAsesoria);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asesoria.getIdAsesoria();
                if (findAsesoria(id) == null) {
                    throw new NonexistentEntityException("The asesoria with id " + id + " no longer exists.");
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
            Asesoria asesoria;
            try {
                asesoria = em.getReference(Asesoria.class, id);
                asesoria.getIdAsesoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asesoria with id " + id + " no longer exists.", enfe);
            }
            CalendarioAsignatura idCalendarioasignatura = asesoria.getIdCalendarioasignatura();
            if (idCalendarioasignatura != null) {
                idCalendarioasignatura.getAsesoriaList().remove(asesoria);
                idCalendarioasignatura = em.merge(idCalendarioasignatura);
            }
            List<HistoricoAsesoria> historicoAsesoriaList = asesoria.getHistoricoAsesoriaList();
            for (HistoricoAsesoria historicoAsesoriaListHistoricoAsesoria : historicoAsesoriaList) {
                historicoAsesoriaListHistoricoAsesoria.setIdAsesoria(null);
                historicoAsesoriaListHistoricoAsesoria = em.merge(historicoAsesoriaListHistoricoAsesoria);
            }
            em.remove(asesoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asesoria> findAsesoriaEntities() {
        return findAsesoriaEntities(true, -1, -1);
    }

    public List<Asesoria> findAsesoriaEntities(int maxResults, int firstResult) {
        return findAsesoriaEntities(false, maxResults, firstResult);
    }

    private List<Asesoria> findAsesoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Asesoria as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Asesoria findAsesoria(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asesoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsesoriaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Asesoria as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
