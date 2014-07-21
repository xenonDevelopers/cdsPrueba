/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.Evento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoEvento;
import com.utez.secretariaAcademica.entity.Plantel;
import com.utez.integracion.entity.OfertaEvento;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.Modulo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class EventoJpaController implements Serializable {

    public EventoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Evento evento) {
        if (evento.getOfertaEventoList() == null) {
            evento.setOfertaEventoList(new ArrayList<OfertaEvento>());
        }
        if (evento.getModuloList() == null) {
            evento.setModuloList(new ArrayList<Modulo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoEvento idTipoevento = evento.getIdTipoevento();
            if (idTipoevento != null) {
                idTipoevento = em.getReference(idTipoevento.getClass(), idTipoevento.getIdTipoevento());
                evento.setIdTipoevento(idTipoevento);
            }
            Plantel idPlantel = evento.getIdPlantel();
            if (idPlantel != null) {
                idPlantel = em.getReference(idPlantel.getClass(), idPlantel.getIdplantel());
                evento.setIdPlantel(idPlantel);
            }
            List<OfertaEvento> attachedOfertaEventoList = new ArrayList<OfertaEvento>();
            for (OfertaEvento ofertaEventoListOfertaEventoToAttach : evento.getOfertaEventoList()) {
                ofertaEventoListOfertaEventoToAttach = em.getReference(ofertaEventoListOfertaEventoToAttach.getClass(), ofertaEventoListOfertaEventoToAttach.getIdOfertaevento());
                attachedOfertaEventoList.add(ofertaEventoListOfertaEventoToAttach);
            }
            evento.setOfertaEventoList(attachedOfertaEventoList);
            List<Modulo> attachedModuloList = new ArrayList<Modulo>();
            for (Modulo moduloListModuloToAttach : evento.getModuloList()) {
                moduloListModuloToAttach = em.getReference(moduloListModuloToAttach.getClass(), moduloListModuloToAttach.getIdModulo());
                attachedModuloList.add(moduloListModuloToAttach);
            }
            evento.setModuloList(attachedModuloList);
            em.persist(evento);
            if (idTipoevento != null) {
                idTipoevento.getEventoList().add(evento);
                idTipoevento = em.merge(idTipoevento);
            }
            if (idPlantel != null) {
                idPlantel.getEventoList().add(evento);
                idPlantel = em.merge(idPlantel);
            }
            for (OfertaEvento ofertaEventoListOfertaEvento : evento.getOfertaEventoList()) {
                Evento oldIdEventoOfOfertaEventoListOfertaEvento = ofertaEventoListOfertaEvento.getIdEvento();
                ofertaEventoListOfertaEvento.setIdEvento(evento);
                ofertaEventoListOfertaEvento = em.merge(ofertaEventoListOfertaEvento);
                if (oldIdEventoOfOfertaEventoListOfertaEvento != null) {
                    oldIdEventoOfOfertaEventoListOfertaEvento.getOfertaEventoList().remove(ofertaEventoListOfertaEvento);
                    oldIdEventoOfOfertaEventoListOfertaEvento = em.merge(oldIdEventoOfOfertaEventoListOfertaEvento);
                }
            }
            for (Modulo moduloListModulo : evento.getModuloList()) {
                Evento oldIdEventoOfModuloListModulo = moduloListModulo.getIdEvento();
                moduloListModulo.setIdEvento(evento);
                moduloListModulo = em.merge(moduloListModulo);
                if (oldIdEventoOfModuloListModulo != null) {
                    oldIdEventoOfModuloListModulo.getModuloList().remove(moduloListModulo);
                    oldIdEventoOfModuloListModulo = em.merge(oldIdEventoOfModuloListModulo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Evento evento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Evento persistentEvento = em.find(Evento.class, evento.getIdEvento());
            TipoEvento idTipoeventoOld = persistentEvento.getIdTipoevento();
            TipoEvento idTipoeventoNew = evento.getIdTipoevento();
            Plantel idPlantelOld = persistentEvento.getIdPlantel();
            Plantel idPlantelNew = evento.getIdPlantel();
            List<OfertaEvento> ofertaEventoListOld = persistentEvento.getOfertaEventoList();
            List<OfertaEvento> ofertaEventoListNew = evento.getOfertaEventoList();
            List<Modulo> moduloListOld = persistentEvento.getModuloList();
            List<Modulo> moduloListNew = evento.getModuloList();
            if (idTipoeventoNew != null) {
                idTipoeventoNew = em.getReference(idTipoeventoNew.getClass(), idTipoeventoNew.getIdTipoevento());
                evento.setIdTipoevento(idTipoeventoNew);
            }
            if (idPlantelNew != null) {
                idPlantelNew = em.getReference(idPlantelNew.getClass(), idPlantelNew.getIdplantel());
                evento.setIdPlantel(idPlantelNew);
            }
            List<OfertaEvento> attachedOfertaEventoListNew = new ArrayList<OfertaEvento>();
            for (OfertaEvento ofertaEventoListNewOfertaEventoToAttach : ofertaEventoListNew) {
                ofertaEventoListNewOfertaEventoToAttach = em.getReference(ofertaEventoListNewOfertaEventoToAttach.getClass(), ofertaEventoListNewOfertaEventoToAttach.getIdOfertaevento());
                attachedOfertaEventoListNew.add(ofertaEventoListNewOfertaEventoToAttach);
            }
            ofertaEventoListNew = attachedOfertaEventoListNew;
            evento.setOfertaEventoList(ofertaEventoListNew);
            List<Modulo> attachedModuloListNew = new ArrayList<Modulo>();
            for (Modulo moduloListNewModuloToAttach : moduloListNew) {
                moduloListNewModuloToAttach = em.getReference(moduloListNewModuloToAttach.getClass(), moduloListNewModuloToAttach.getIdModulo());
                attachedModuloListNew.add(moduloListNewModuloToAttach);
            }
            moduloListNew = attachedModuloListNew;
            evento.setModuloList(moduloListNew);
            evento = em.merge(evento);
            if (idTipoeventoOld != null && !idTipoeventoOld.equals(idTipoeventoNew)) {
                idTipoeventoOld.getEventoList().remove(evento);
                idTipoeventoOld = em.merge(idTipoeventoOld);
            }
            if (idTipoeventoNew != null && !idTipoeventoNew.equals(idTipoeventoOld)) {
                idTipoeventoNew.getEventoList().add(evento);
                idTipoeventoNew = em.merge(idTipoeventoNew);
            }
            if (idPlantelOld != null && !idPlantelOld.equals(idPlantelNew)) {
                idPlantelOld.getEventoList().remove(evento);
                idPlantelOld = em.merge(idPlantelOld);
            }
            if (idPlantelNew != null && !idPlantelNew.equals(idPlantelOld)) {
                idPlantelNew.getEventoList().add(evento);
                idPlantelNew = em.merge(idPlantelNew);
            }
            for (OfertaEvento ofertaEventoListOldOfertaEvento : ofertaEventoListOld) {
                if (!ofertaEventoListNew.contains(ofertaEventoListOldOfertaEvento)) {
                    ofertaEventoListOldOfertaEvento.setIdEvento(null);
                    ofertaEventoListOldOfertaEvento = em.merge(ofertaEventoListOldOfertaEvento);
                }
            }
            for (OfertaEvento ofertaEventoListNewOfertaEvento : ofertaEventoListNew) {
                if (!ofertaEventoListOld.contains(ofertaEventoListNewOfertaEvento)) {
                    Evento oldIdEventoOfOfertaEventoListNewOfertaEvento = ofertaEventoListNewOfertaEvento.getIdEvento();
                    ofertaEventoListNewOfertaEvento.setIdEvento(evento);
                    ofertaEventoListNewOfertaEvento = em.merge(ofertaEventoListNewOfertaEvento);
                    if (oldIdEventoOfOfertaEventoListNewOfertaEvento != null && !oldIdEventoOfOfertaEventoListNewOfertaEvento.equals(evento)) {
                        oldIdEventoOfOfertaEventoListNewOfertaEvento.getOfertaEventoList().remove(ofertaEventoListNewOfertaEvento);
                        oldIdEventoOfOfertaEventoListNewOfertaEvento = em.merge(oldIdEventoOfOfertaEventoListNewOfertaEvento);
                    }
                }
            }
            for (Modulo moduloListOldModulo : moduloListOld) {
                if (!moduloListNew.contains(moduloListOldModulo)) {
                    moduloListOldModulo.setIdEvento(null);
                    moduloListOldModulo = em.merge(moduloListOldModulo);
                }
            }
            for (Modulo moduloListNewModulo : moduloListNew) {
                if (!moduloListOld.contains(moduloListNewModulo)) {
                    Evento oldIdEventoOfModuloListNewModulo = moduloListNewModulo.getIdEvento();
                    moduloListNewModulo.setIdEvento(evento);
                    moduloListNewModulo = em.merge(moduloListNewModulo);
                    if (oldIdEventoOfModuloListNewModulo != null && !oldIdEventoOfModuloListNewModulo.equals(evento)) {
                        oldIdEventoOfModuloListNewModulo.getModuloList().remove(moduloListNewModulo);
                        oldIdEventoOfModuloListNewModulo = em.merge(oldIdEventoOfModuloListNewModulo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = evento.getIdEvento();
                if (findEvento(id) == null) {
                    throw new NonexistentEntityException("The evento with id " + id + " no longer exists.");
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
            Evento evento;
            try {
                evento = em.getReference(Evento.class, id);
                evento.getIdEvento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The evento with id " + id + " no longer exists.", enfe);
            }
            TipoEvento idTipoevento = evento.getIdTipoevento();
            if (idTipoevento != null) {
                idTipoevento.getEventoList().remove(evento);
                idTipoevento = em.merge(idTipoevento);
            }
            Plantel idPlantel = evento.getIdPlantel();
            if (idPlantel != null) {
                idPlantel.getEventoList().remove(evento);
                idPlantel = em.merge(idPlantel);
            }
            List<OfertaEvento> ofertaEventoList = evento.getOfertaEventoList();
            for (OfertaEvento ofertaEventoListOfertaEvento : ofertaEventoList) {
                ofertaEventoListOfertaEvento.setIdEvento(null);
                ofertaEventoListOfertaEvento = em.merge(ofertaEventoListOfertaEvento);
            }
            List<Modulo> moduloList = evento.getModuloList();
            for (Modulo moduloListModulo : moduloList) {
                moduloListModulo.setIdEvento(null);
                moduloListModulo = em.merge(moduloListModulo);
            }
            em.remove(evento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Evento> findEventoEntities() {
        return findEventoEntities(true, -1, -1);
    }

    public List<Evento> findEventoEntities(int maxResults, int firstResult) {
        return findEventoEntities(false, maxResults, firstResult);
    }

    private List<Evento> findEventoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Evento as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Evento findEvento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Evento.class, id);
        } finally {
            em.close();
        }
    }

    public int getEventoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Evento as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
