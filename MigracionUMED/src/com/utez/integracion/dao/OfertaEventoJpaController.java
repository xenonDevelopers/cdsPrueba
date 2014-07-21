/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.RecursoHumano;
import com.utez.integracion.entity.Evento;
import com.utez.integracion.entity.CalificacionModulo;
import com.utez.integracion.entity.OfertaEvento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class OfertaEventoJpaController implements Serializable {

    public OfertaEventoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OfertaEvento ofertaEvento) {
        if (ofertaEvento.getCalificacionModuloList() == null) {
            ofertaEvento.setCalificacionModuloList(new ArrayList<CalificacionModulo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RecursoHumano idInstructor = ofertaEvento.getIdInstructor();
            if (idInstructor != null) {
                idInstructor = em.getReference(idInstructor.getClass(), idInstructor.getIdRecursohumano());
                ofertaEvento.setIdInstructor(idInstructor);
            }
            Evento idEvento = ofertaEvento.getIdEvento();
            if (idEvento != null) {
                idEvento = em.getReference(idEvento.getClass(), idEvento.getIdEvento());
                ofertaEvento.setIdEvento(idEvento);
            }
            List<CalificacionModulo> attachedCalificacionModuloList = new ArrayList<CalificacionModulo>();
            for (CalificacionModulo calificacionModuloListCalificacionModuloToAttach : ofertaEvento.getCalificacionModuloList()) {
                calificacionModuloListCalificacionModuloToAttach = em.getReference(calificacionModuloListCalificacionModuloToAttach.getClass(), calificacionModuloListCalificacionModuloToAttach.getIdCalificacionevento());
                attachedCalificacionModuloList.add(calificacionModuloListCalificacionModuloToAttach);
            }
            ofertaEvento.setCalificacionModuloList(attachedCalificacionModuloList);
            em.persist(ofertaEvento);
            if (idInstructor != null) {
                idInstructor.getOfertaEventoList().add(ofertaEvento);
                idInstructor = em.merge(idInstructor);
            }
            if (idEvento != null) {
                idEvento.getOfertaEventoList().add(ofertaEvento);
                idEvento = em.merge(idEvento);
            }
            for (CalificacionModulo calificacionModuloListCalificacionModulo : ofertaEvento.getCalificacionModuloList()) {
                OfertaEvento oldIdOfertaeventoOfCalificacionModuloListCalificacionModulo = calificacionModuloListCalificacionModulo.getIdOfertaevento();
                calificacionModuloListCalificacionModulo.setIdOfertaevento(ofertaEvento);
                calificacionModuloListCalificacionModulo = em.merge(calificacionModuloListCalificacionModulo);
                if (oldIdOfertaeventoOfCalificacionModuloListCalificacionModulo != null) {
                    oldIdOfertaeventoOfCalificacionModuloListCalificacionModulo.getCalificacionModuloList().remove(calificacionModuloListCalificacionModulo);
                    oldIdOfertaeventoOfCalificacionModuloListCalificacionModulo = em.merge(oldIdOfertaeventoOfCalificacionModuloListCalificacionModulo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OfertaEvento ofertaEvento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OfertaEvento persistentOfertaEvento = em.find(OfertaEvento.class, ofertaEvento.getIdOfertaevento());
            RecursoHumano idInstructorOld = persistentOfertaEvento.getIdInstructor();
            RecursoHumano idInstructorNew = ofertaEvento.getIdInstructor();
            Evento idEventoOld = persistentOfertaEvento.getIdEvento();
            Evento idEventoNew = ofertaEvento.getIdEvento();
            List<CalificacionModulo> calificacionModuloListOld = persistentOfertaEvento.getCalificacionModuloList();
            List<CalificacionModulo> calificacionModuloListNew = ofertaEvento.getCalificacionModuloList();
            if (idInstructorNew != null) {
                idInstructorNew = em.getReference(idInstructorNew.getClass(), idInstructorNew.getIdRecursohumano());
                ofertaEvento.setIdInstructor(idInstructorNew);
            }
            if (idEventoNew != null) {
                idEventoNew = em.getReference(idEventoNew.getClass(), idEventoNew.getIdEvento());
                ofertaEvento.setIdEvento(idEventoNew);
            }
            List<CalificacionModulo> attachedCalificacionModuloListNew = new ArrayList<CalificacionModulo>();
            for (CalificacionModulo calificacionModuloListNewCalificacionModuloToAttach : calificacionModuloListNew) {
                calificacionModuloListNewCalificacionModuloToAttach = em.getReference(calificacionModuloListNewCalificacionModuloToAttach.getClass(), calificacionModuloListNewCalificacionModuloToAttach.getIdCalificacionevento());
                attachedCalificacionModuloListNew.add(calificacionModuloListNewCalificacionModuloToAttach);
            }
            calificacionModuloListNew = attachedCalificacionModuloListNew;
            ofertaEvento.setCalificacionModuloList(calificacionModuloListNew);
            ofertaEvento = em.merge(ofertaEvento);
            if (idInstructorOld != null && !idInstructorOld.equals(idInstructorNew)) {
                idInstructorOld.getOfertaEventoList().remove(ofertaEvento);
                idInstructorOld = em.merge(idInstructorOld);
            }
            if (idInstructorNew != null && !idInstructorNew.equals(idInstructorOld)) {
                idInstructorNew.getOfertaEventoList().add(ofertaEvento);
                idInstructorNew = em.merge(idInstructorNew);
            }
            if (idEventoOld != null && !idEventoOld.equals(idEventoNew)) {
                idEventoOld.getOfertaEventoList().remove(ofertaEvento);
                idEventoOld = em.merge(idEventoOld);
            }
            if (idEventoNew != null && !idEventoNew.equals(idEventoOld)) {
                idEventoNew.getOfertaEventoList().add(ofertaEvento);
                idEventoNew = em.merge(idEventoNew);
            }
            for (CalificacionModulo calificacionModuloListOldCalificacionModulo : calificacionModuloListOld) {
                if (!calificacionModuloListNew.contains(calificacionModuloListOldCalificacionModulo)) {
                    calificacionModuloListOldCalificacionModulo.setIdOfertaevento(null);
                    calificacionModuloListOldCalificacionModulo = em.merge(calificacionModuloListOldCalificacionModulo);
                }
            }
            for (CalificacionModulo calificacionModuloListNewCalificacionModulo : calificacionModuloListNew) {
                if (!calificacionModuloListOld.contains(calificacionModuloListNewCalificacionModulo)) {
                    OfertaEvento oldIdOfertaeventoOfCalificacionModuloListNewCalificacionModulo = calificacionModuloListNewCalificacionModulo.getIdOfertaevento();
                    calificacionModuloListNewCalificacionModulo.setIdOfertaevento(ofertaEvento);
                    calificacionModuloListNewCalificacionModulo = em.merge(calificacionModuloListNewCalificacionModulo);
                    if (oldIdOfertaeventoOfCalificacionModuloListNewCalificacionModulo != null && !oldIdOfertaeventoOfCalificacionModuloListNewCalificacionModulo.equals(ofertaEvento)) {
                        oldIdOfertaeventoOfCalificacionModuloListNewCalificacionModulo.getCalificacionModuloList().remove(calificacionModuloListNewCalificacionModulo);
                        oldIdOfertaeventoOfCalificacionModuloListNewCalificacionModulo = em.merge(oldIdOfertaeventoOfCalificacionModuloListNewCalificacionModulo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = ofertaEvento.getIdOfertaevento();
                if (findOfertaEvento(id) == null) {
                    throw new NonexistentEntityException("The ofertaEvento with id " + id + " no longer exists.");
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
            OfertaEvento ofertaEvento;
            try {
                ofertaEvento = em.getReference(OfertaEvento.class, id);
                ofertaEvento.getIdOfertaevento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ofertaEvento with id " + id + " no longer exists.", enfe);
            }
            RecursoHumano idInstructor = ofertaEvento.getIdInstructor();
            if (idInstructor != null) {
                idInstructor.getOfertaEventoList().remove(ofertaEvento);
                idInstructor = em.merge(idInstructor);
            }
            Evento idEvento = ofertaEvento.getIdEvento();
            if (idEvento != null) {
                idEvento.getOfertaEventoList().remove(ofertaEvento);
                idEvento = em.merge(idEvento);
            }
            List<CalificacionModulo> calificacionModuloList = ofertaEvento.getCalificacionModuloList();
            for (CalificacionModulo calificacionModuloListCalificacionModulo : calificacionModuloList) {
                calificacionModuloListCalificacionModulo.setIdOfertaevento(null);
                calificacionModuloListCalificacionModulo = em.merge(calificacionModuloListCalificacionModulo);
            }
            em.remove(ofertaEvento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OfertaEvento> findOfertaEventoEntities() {
        return findOfertaEventoEntities(true, -1, -1);
    }

    public List<OfertaEvento> findOfertaEventoEntities(int maxResults, int firstResult) {
        return findOfertaEventoEntities(false, maxResults, firstResult);
    }

    private List<OfertaEvento> findOfertaEventoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from OfertaEvento as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public OfertaEvento findOfertaEvento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OfertaEvento.class, id);
        } finally {
            em.close();
        }
    }

    public int getOfertaEventoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from OfertaEvento as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
