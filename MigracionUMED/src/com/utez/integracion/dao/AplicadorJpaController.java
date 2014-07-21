/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.Aplicador;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.RecursoHumano;
import com.utez.integracion.entity.AsignacionAplicador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AplicadorJpaController implements Serializable {

    public AplicadorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Aplicador aplicador) {
        if (aplicador.getAsignacionAplicadorList() == null) {
            aplicador.setAsignacionAplicadorList(new ArrayList<AsignacionAplicador>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RecursoHumano idRecursohumano = aplicador.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano = em.getReference(idRecursohumano.getClass(), idRecursohumano.getIdRecursohumano());
                aplicador.setIdRecursohumano(idRecursohumano);
            }
            List<AsignacionAplicador> attachedAsignacionAplicadorList = new ArrayList<AsignacionAplicador>();
            for (AsignacionAplicador asignacionAplicadorListAsignacionAplicadorToAttach : aplicador.getAsignacionAplicadorList()) {
                asignacionAplicadorListAsignacionAplicadorToAttach = em.getReference(asignacionAplicadorListAsignacionAplicadorToAttach.getClass(), asignacionAplicadorListAsignacionAplicadorToAttach.getIdAsignacionaplicador());
                attachedAsignacionAplicadorList.add(asignacionAplicadorListAsignacionAplicadorToAttach);
            }
            aplicador.setAsignacionAplicadorList(attachedAsignacionAplicadorList);
            em.persist(aplicador);
            if (idRecursohumano != null) {
                idRecursohumano.getAplicadorList().add(aplicador);
                idRecursohumano = em.merge(idRecursohumano);
            }
            for (AsignacionAplicador asignacionAplicadorListAsignacionAplicador : aplicador.getAsignacionAplicadorList()) {
                Aplicador oldIdAplicadorOfAsignacionAplicadorListAsignacionAplicador = asignacionAplicadorListAsignacionAplicador.getIdAplicador();
                asignacionAplicadorListAsignacionAplicador.setIdAplicador(aplicador);
                asignacionAplicadorListAsignacionAplicador = em.merge(asignacionAplicadorListAsignacionAplicador);
                if (oldIdAplicadorOfAsignacionAplicadorListAsignacionAplicador != null) {
                    oldIdAplicadorOfAsignacionAplicadorListAsignacionAplicador.getAsignacionAplicadorList().remove(asignacionAplicadorListAsignacionAplicador);
                    oldIdAplicadorOfAsignacionAplicadorListAsignacionAplicador = em.merge(oldIdAplicadorOfAsignacionAplicadorListAsignacionAplicador);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Aplicador aplicador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aplicador persistentAplicador = em.find(Aplicador.class, aplicador.getIdAplicador());
            RecursoHumano idRecursohumanoOld = persistentAplicador.getIdRecursohumano();
            RecursoHumano idRecursohumanoNew = aplicador.getIdRecursohumano();
            List<AsignacionAplicador> asignacionAplicadorListOld = persistentAplicador.getAsignacionAplicadorList();
            List<AsignacionAplicador> asignacionAplicadorListNew = aplicador.getAsignacionAplicadorList();
            if (idRecursohumanoNew != null) {
                idRecursohumanoNew = em.getReference(idRecursohumanoNew.getClass(), idRecursohumanoNew.getIdRecursohumano());
                aplicador.setIdRecursohumano(idRecursohumanoNew);
            }
            List<AsignacionAplicador> attachedAsignacionAplicadorListNew = new ArrayList<AsignacionAplicador>();
            for (AsignacionAplicador asignacionAplicadorListNewAsignacionAplicadorToAttach : asignacionAplicadorListNew) {
                asignacionAplicadorListNewAsignacionAplicadorToAttach = em.getReference(asignacionAplicadorListNewAsignacionAplicadorToAttach.getClass(), asignacionAplicadorListNewAsignacionAplicadorToAttach.getIdAsignacionaplicador());
                attachedAsignacionAplicadorListNew.add(asignacionAplicadorListNewAsignacionAplicadorToAttach);
            }
            asignacionAplicadorListNew = attachedAsignacionAplicadorListNew;
            aplicador.setAsignacionAplicadorList(asignacionAplicadorListNew);
            aplicador = em.merge(aplicador);
            if (idRecursohumanoOld != null && !idRecursohumanoOld.equals(idRecursohumanoNew)) {
                idRecursohumanoOld.getAplicadorList().remove(aplicador);
                idRecursohumanoOld = em.merge(idRecursohumanoOld);
            }
            if (idRecursohumanoNew != null && !idRecursohumanoNew.equals(idRecursohumanoOld)) {
                idRecursohumanoNew.getAplicadorList().add(aplicador);
                idRecursohumanoNew = em.merge(idRecursohumanoNew);
            }
            for (AsignacionAplicador asignacionAplicadorListOldAsignacionAplicador : asignacionAplicadorListOld) {
                if (!asignacionAplicadorListNew.contains(asignacionAplicadorListOldAsignacionAplicador)) {
                    asignacionAplicadorListOldAsignacionAplicador.setIdAplicador(null);
                    asignacionAplicadorListOldAsignacionAplicador = em.merge(asignacionAplicadorListOldAsignacionAplicador);
                }
            }
            for (AsignacionAplicador asignacionAplicadorListNewAsignacionAplicador : asignacionAplicadorListNew) {
                if (!asignacionAplicadorListOld.contains(asignacionAplicadorListNewAsignacionAplicador)) {
                    Aplicador oldIdAplicadorOfAsignacionAplicadorListNewAsignacionAplicador = asignacionAplicadorListNewAsignacionAplicador.getIdAplicador();
                    asignacionAplicadorListNewAsignacionAplicador.setIdAplicador(aplicador);
                    asignacionAplicadorListNewAsignacionAplicador = em.merge(asignacionAplicadorListNewAsignacionAplicador);
                    if (oldIdAplicadorOfAsignacionAplicadorListNewAsignacionAplicador != null && !oldIdAplicadorOfAsignacionAplicadorListNewAsignacionAplicador.equals(aplicador)) {
                        oldIdAplicadorOfAsignacionAplicadorListNewAsignacionAplicador.getAsignacionAplicadorList().remove(asignacionAplicadorListNewAsignacionAplicador);
                        oldIdAplicadorOfAsignacionAplicadorListNewAsignacionAplicador = em.merge(oldIdAplicadorOfAsignacionAplicadorListNewAsignacionAplicador);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = aplicador.getIdAplicador();
                if (findAplicador(id) == null) {
                    throw new NonexistentEntityException("The aplicador with id " + id + " no longer exists.");
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
            Aplicador aplicador;
            try {
                aplicador = em.getReference(Aplicador.class, id);
                aplicador.getIdAplicador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aplicador with id " + id + " no longer exists.", enfe);
            }
            RecursoHumano idRecursohumano = aplicador.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano.getAplicadorList().remove(aplicador);
                idRecursohumano = em.merge(idRecursohumano);
            }
            List<AsignacionAplicador> asignacionAplicadorList = aplicador.getAsignacionAplicadorList();
            for (AsignacionAplicador asignacionAplicadorListAsignacionAplicador : asignacionAplicadorList) {
                asignacionAplicadorListAsignacionAplicador.setIdAplicador(null);
                asignacionAplicadorListAsignacionAplicador = em.merge(asignacionAplicadorListAsignacionAplicador);
            }
            em.remove(aplicador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Aplicador> findAplicadorEntities() {
        return findAplicadorEntities(true, -1, -1);
    }

    public List<Aplicador> findAplicadorEntities(int maxResults, int firstResult) {
        return findAplicadorEntities(false, maxResults, firstResult);
    }

    private List<Aplicador> findAplicadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Aplicador as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Aplicador findAplicador(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Aplicador.class, id);
        } finally {
            em.close();
        }
    }

    public int getAplicadorCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Aplicador as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
