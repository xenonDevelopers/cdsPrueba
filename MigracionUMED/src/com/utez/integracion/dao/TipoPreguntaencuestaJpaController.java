/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Pregunta;
import com.utez.integracion.entity.TipoPreguntaencuesta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoPreguntaencuestaJpaController implements Serializable {

    public TipoPreguntaencuestaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoPreguntaencuesta tipoPreguntaencuesta) {
        if (tipoPreguntaencuesta.getPreguntaList() == null) {
            tipoPreguntaencuesta.setPreguntaList(new ArrayList<Pregunta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pregunta> attachedPreguntaList = new ArrayList<Pregunta>();
            for (Pregunta preguntaListPreguntaToAttach : tipoPreguntaencuesta.getPreguntaList()) {
                preguntaListPreguntaToAttach = em.getReference(preguntaListPreguntaToAttach.getClass(), preguntaListPreguntaToAttach.getIdPregunta());
                attachedPreguntaList.add(preguntaListPreguntaToAttach);
            }
            tipoPreguntaencuesta.setPreguntaList(attachedPreguntaList);
            em.persist(tipoPreguntaencuesta);
            for (Pregunta preguntaListPregunta : tipoPreguntaencuesta.getPreguntaList()) {
                TipoPreguntaencuesta oldIdTipopreguntaOfPreguntaListPregunta = preguntaListPregunta.getIdTipopregunta();
                preguntaListPregunta.setIdTipopregunta(tipoPreguntaencuesta);
                preguntaListPregunta = em.merge(preguntaListPregunta);
                if (oldIdTipopreguntaOfPreguntaListPregunta != null) {
                    oldIdTipopreguntaOfPreguntaListPregunta.getPreguntaList().remove(preguntaListPregunta);
                    oldIdTipopreguntaOfPreguntaListPregunta = em.merge(oldIdTipopreguntaOfPreguntaListPregunta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoPreguntaencuesta tipoPreguntaencuesta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoPreguntaencuesta persistentTipoPreguntaencuesta = em.find(TipoPreguntaencuesta.class, tipoPreguntaencuesta.getIdTipopregunta());
            List<Pregunta> preguntaListOld = persistentTipoPreguntaencuesta.getPreguntaList();
            List<Pregunta> preguntaListNew = tipoPreguntaencuesta.getPreguntaList();
            List<Pregunta> attachedPreguntaListNew = new ArrayList<Pregunta>();
            for (Pregunta preguntaListNewPreguntaToAttach : preguntaListNew) {
                preguntaListNewPreguntaToAttach = em.getReference(preguntaListNewPreguntaToAttach.getClass(), preguntaListNewPreguntaToAttach.getIdPregunta());
                attachedPreguntaListNew.add(preguntaListNewPreguntaToAttach);
            }
            preguntaListNew = attachedPreguntaListNew;
            tipoPreguntaencuesta.setPreguntaList(preguntaListNew);
            tipoPreguntaencuesta = em.merge(tipoPreguntaencuesta);
            for (Pregunta preguntaListOldPregunta : preguntaListOld) {
                if (!preguntaListNew.contains(preguntaListOldPregunta)) {
                    preguntaListOldPregunta.setIdTipopregunta(null);
                    preguntaListOldPregunta = em.merge(preguntaListOldPregunta);
                }
            }
            for (Pregunta preguntaListNewPregunta : preguntaListNew) {
                if (!preguntaListOld.contains(preguntaListNewPregunta)) {
                    TipoPreguntaencuesta oldIdTipopreguntaOfPreguntaListNewPregunta = preguntaListNewPregunta.getIdTipopregunta();
                    preguntaListNewPregunta.setIdTipopregunta(tipoPreguntaencuesta);
                    preguntaListNewPregunta = em.merge(preguntaListNewPregunta);
                    if (oldIdTipopreguntaOfPreguntaListNewPregunta != null && !oldIdTipopreguntaOfPreguntaListNewPregunta.equals(tipoPreguntaencuesta)) {
                        oldIdTipopreguntaOfPreguntaListNewPregunta.getPreguntaList().remove(preguntaListNewPregunta);
                        oldIdTipopreguntaOfPreguntaListNewPregunta = em.merge(oldIdTipopreguntaOfPreguntaListNewPregunta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoPreguntaencuesta.getIdTipopregunta();
                if (findTipoPreguntaencuesta(id) == null) {
                    throw new NonexistentEntityException("The tipoPreguntaencuesta with id " + id + " no longer exists.");
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
            TipoPreguntaencuesta tipoPreguntaencuesta;
            try {
                tipoPreguntaencuesta = em.getReference(TipoPreguntaencuesta.class, id);
                tipoPreguntaencuesta.getIdTipopregunta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoPreguntaencuesta with id " + id + " no longer exists.", enfe);
            }
            List<Pregunta> preguntaList = tipoPreguntaencuesta.getPreguntaList();
            for (Pregunta preguntaListPregunta : preguntaList) {
                preguntaListPregunta.setIdTipopregunta(null);
                preguntaListPregunta = em.merge(preguntaListPregunta);
            }
            em.remove(tipoPreguntaencuesta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoPreguntaencuesta> findTipoPreguntaencuestaEntities() {
        return findTipoPreguntaencuestaEntities(true, -1, -1);
    }

    public List<TipoPreguntaencuesta> findTipoPreguntaencuestaEntities(int maxResults, int firstResult) {
        return findTipoPreguntaencuestaEntities(false, maxResults, firstResult);
    }

    private List<TipoPreguntaencuesta> findTipoPreguntaencuestaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoPreguntaencuesta as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoPreguntaencuesta findTipoPreguntaencuesta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoPreguntaencuesta.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoPreguntaencuestaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoPreguntaencuesta as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
