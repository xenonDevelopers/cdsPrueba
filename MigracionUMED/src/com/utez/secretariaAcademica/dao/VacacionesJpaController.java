/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Calendariorectoria;
import com.utez.secretariaAcademica.entity.Vacaciones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class VacacionesJpaController implements Serializable {

    public VacacionesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vacaciones vacaciones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calendariorectoria idcalendariorectoria = vacaciones.getIdcalendariorectoria();
            if (idcalendariorectoria != null) {
                idcalendariorectoria = em.getReference(idcalendariorectoria.getClass(), idcalendariorectoria.getIdcalendariorectoria());
                vacaciones.setIdcalendariorectoria(idcalendariorectoria);
            }
            em.persist(vacaciones);
            if (idcalendariorectoria != null) {
                idcalendariorectoria.getVacacionesList().add(vacaciones);
                idcalendariorectoria = em.merge(idcalendariorectoria);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vacaciones vacaciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vacaciones persistentVacaciones = em.find(Vacaciones.class, vacaciones.getIdvacaciones());
            Calendariorectoria idcalendariorectoriaOld = persistentVacaciones.getIdcalendariorectoria();
            Calendariorectoria idcalendariorectoriaNew = vacaciones.getIdcalendariorectoria();
            if (idcalendariorectoriaNew != null) {
                idcalendariorectoriaNew = em.getReference(idcalendariorectoriaNew.getClass(), idcalendariorectoriaNew.getIdcalendariorectoria());
                vacaciones.setIdcalendariorectoria(idcalendariorectoriaNew);
            }
            vacaciones = em.merge(vacaciones);
            if (idcalendariorectoriaOld != null && !idcalendariorectoriaOld.equals(idcalendariorectoriaNew)) {
                idcalendariorectoriaOld.getVacacionesList().remove(vacaciones);
                idcalendariorectoriaOld = em.merge(idcalendariorectoriaOld);
            }
            if (idcalendariorectoriaNew != null && !idcalendariorectoriaNew.equals(idcalendariorectoriaOld)) {
                idcalendariorectoriaNew.getVacacionesList().add(vacaciones);
                idcalendariorectoriaNew = em.merge(idcalendariorectoriaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vacaciones.getIdvacaciones();
                if (findVacaciones(id) == null) {
                    throw new NonexistentEntityException("The vacaciones with id " + id + " no longer exists.");
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
            Vacaciones vacaciones;
            try {
                vacaciones = em.getReference(Vacaciones.class, id);
                vacaciones.getIdvacaciones();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vacaciones with id " + id + " no longer exists.", enfe);
            }
            Calendariorectoria idcalendariorectoria = vacaciones.getIdcalendariorectoria();
            if (idcalendariorectoria != null) {
                idcalendariorectoria.getVacacionesList().remove(vacaciones);
                idcalendariorectoria = em.merge(idcalendariorectoria);
            }
            em.remove(vacaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vacaciones> findVacacionesEntities() {
        return findVacacionesEntities(true, -1, -1);
    }

    public List<Vacaciones> findVacacionesEntities(int maxResults, int firstResult) {
        return findVacacionesEntities(false, maxResults, firstResult);
    }

    private List<Vacaciones> findVacacionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Vacaciones as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Vacaciones findVacaciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vacaciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getVacacionesCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Vacaciones as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
