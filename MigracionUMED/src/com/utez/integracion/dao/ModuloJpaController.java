/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Evento;
import com.utez.integracion.entity.CalificacionModulo;
import com.utez.integracion.entity.Modulo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ModuloJpaController implements Serializable {

    public ModuloJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Modulo modulo) {
        if (modulo.getCalificacionModuloList() == null) {
            modulo.setCalificacionModuloList(new ArrayList<CalificacionModulo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Evento idEvento = modulo.getIdEvento();
            if (idEvento != null) {
                idEvento = em.getReference(idEvento.getClass(), idEvento.getIdEvento());
                modulo.setIdEvento(idEvento);
            }
            List<CalificacionModulo> attachedCalificacionModuloList = new ArrayList<CalificacionModulo>();
            for (CalificacionModulo calificacionModuloListCalificacionModuloToAttach : modulo.getCalificacionModuloList()) {
                calificacionModuloListCalificacionModuloToAttach = em.getReference(calificacionModuloListCalificacionModuloToAttach.getClass(), calificacionModuloListCalificacionModuloToAttach.getIdCalificacionevento());
                attachedCalificacionModuloList.add(calificacionModuloListCalificacionModuloToAttach);
            }
            modulo.setCalificacionModuloList(attachedCalificacionModuloList);
            em.persist(modulo);
            if (idEvento != null) {
                idEvento.getModuloList().add(modulo);
                idEvento = em.merge(idEvento);
            }
            for (CalificacionModulo calificacionModuloListCalificacionModulo : modulo.getCalificacionModuloList()) {
                Modulo oldIdModuloOfCalificacionModuloListCalificacionModulo = calificacionModuloListCalificacionModulo.getIdModulo();
                calificacionModuloListCalificacionModulo.setIdModulo(modulo);
                calificacionModuloListCalificacionModulo = em.merge(calificacionModuloListCalificacionModulo);
                if (oldIdModuloOfCalificacionModuloListCalificacionModulo != null) {
                    oldIdModuloOfCalificacionModuloListCalificacionModulo.getCalificacionModuloList().remove(calificacionModuloListCalificacionModulo);
                    oldIdModuloOfCalificacionModuloListCalificacionModulo = em.merge(oldIdModuloOfCalificacionModuloListCalificacionModulo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Modulo modulo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Modulo persistentModulo = em.find(Modulo.class, modulo.getIdModulo());
            Evento idEventoOld = persistentModulo.getIdEvento();
            Evento idEventoNew = modulo.getIdEvento();
            List<CalificacionModulo> calificacionModuloListOld = persistentModulo.getCalificacionModuloList();
            List<CalificacionModulo> calificacionModuloListNew = modulo.getCalificacionModuloList();
            if (idEventoNew != null) {
                idEventoNew = em.getReference(idEventoNew.getClass(), idEventoNew.getIdEvento());
                modulo.setIdEvento(idEventoNew);
            }
            List<CalificacionModulo> attachedCalificacionModuloListNew = new ArrayList<CalificacionModulo>();
            for (CalificacionModulo calificacionModuloListNewCalificacionModuloToAttach : calificacionModuloListNew) {
                calificacionModuloListNewCalificacionModuloToAttach = em.getReference(calificacionModuloListNewCalificacionModuloToAttach.getClass(), calificacionModuloListNewCalificacionModuloToAttach.getIdCalificacionevento());
                attachedCalificacionModuloListNew.add(calificacionModuloListNewCalificacionModuloToAttach);
            }
            calificacionModuloListNew = attachedCalificacionModuloListNew;
            modulo.setCalificacionModuloList(calificacionModuloListNew);
            modulo = em.merge(modulo);
            if (idEventoOld != null && !idEventoOld.equals(idEventoNew)) {
                idEventoOld.getModuloList().remove(modulo);
                idEventoOld = em.merge(idEventoOld);
            }
            if (idEventoNew != null && !idEventoNew.equals(idEventoOld)) {
                idEventoNew.getModuloList().add(modulo);
                idEventoNew = em.merge(idEventoNew);
            }
            for (CalificacionModulo calificacionModuloListOldCalificacionModulo : calificacionModuloListOld) {
                if (!calificacionModuloListNew.contains(calificacionModuloListOldCalificacionModulo)) {
                    calificacionModuloListOldCalificacionModulo.setIdModulo(null);
                    calificacionModuloListOldCalificacionModulo = em.merge(calificacionModuloListOldCalificacionModulo);
                }
            }
            for (CalificacionModulo calificacionModuloListNewCalificacionModulo : calificacionModuloListNew) {
                if (!calificacionModuloListOld.contains(calificacionModuloListNewCalificacionModulo)) {
                    Modulo oldIdModuloOfCalificacionModuloListNewCalificacionModulo = calificacionModuloListNewCalificacionModulo.getIdModulo();
                    calificacionModuloListNewCalificacionModulo.setIdModulo(modulo);
                    calificacionModuloListNewCalificacionModulo = em.merge(calificacionModuloListNewCalificacionModulo);
                    if (oldIdModuloOfCalificacionModuloListNewCalificacionModulo != null && !oldIdModuloOfCalificacionModuloListNewCalificacionModulo.equals(modulo)) {
                        oldIdModuloOfCalificacionModuloListNewCalificacionModulo.getCalificacionModuloList().remove(calificacionModuloListNewCalificacionModulo);
                        oldIdModuloOfCalificacionModuloListNewCalificacionModulo = em.merge(oldIdModuloOfCalificacionModuloListNewCalificacionModulo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = modulo.getIdModulo();
                if (findModulo(id) == null) {
                    throw new NonexistentEntityException("The modulo with id " + id + " no longer exists.");
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
            Modulo modulo;
            try {
                modulo = em.getReference(Modulo.class, id);
                modulo.getIdModulo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The modulo with id " + id + " no longer exists.", enfe);
            }
            Evento idEvento = modulo.getIdEvento();
            if (idEvento != null) {
                idEvento.getModuloList().remove(modulo);
                idEvento = em.merge(idEvento);
            }
            List<CalificacionModulo> calificacionModuloList = modulo.getCalificacionModuloList();
            for (CalificacionModulo calificacionModuloListCalificacionModulo : calificacionModuloList) {
                calificacionModuloListCalificacionModulo.setIdModulo(null);
                calificacionModuloListCalificacionModulo = em.merge(calificacionModuloListCalificacionModulo);
            }
            em.remove(modulo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Modulo> findModuloEntities() {
        return findModuloEntities(true, -1, -1);
    }

    public List<Modulo> findModuloEntities(int maxResults, int firstResult) {
        return findModuloEntities(false, maxResults, firstResult);
    }

    private List<Modulo> findModuloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Modulo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Modulo findModulo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Modulo.class, id);
        } finally {
            em.close();
        }
    }

    public int getModuloCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Modulo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
