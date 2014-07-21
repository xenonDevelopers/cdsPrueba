/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Grupo;
import com.utez.integracion.entity.EsquemaAlumnoasignatura;
import com.utez.integracion.entity.GrupoAlumnoesquema;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class GrupoAlumnoesquemaJpaController implements Serializable {

    public GrupoAlumnoesquemaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GrupoAlumnoesquema grupoAlumnoesquema) throws IllegalOrphanException {
        List<String> illegalOrphanMessages = null;
        EsquemaAlumnoasignatura esquemaAlumnoasignaturaOrphanCheck = grupoAlumnoesquema.getEsquemaAlumnoasignatura();
        if (esquemaAlumnoasignaturaOrphanCheck != null) {
            GrupoAlumnoesquema oldGrupoAlumnoesquemaOfEsquemaAlumnoasignatura = esquemaAlumnoasignaturaOrphanCheck.getGrupoAlumnoesquema();
            if (oldGrupoAlumnoesquemaOfEsquemaAlumnoasignatura != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The EsquemaAlumnoasignatura " + esquemaAlumnoasignaturaOrphanCheck + " already has an item of type GrupoAlumnoesquema whose esquemaAlumnoasignatura column cannot be null. Please make another selection for the esquemaAlumnoasignatura field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo idGrupo = grupoAlumnoesquema.getIdGrupo();
            if (idGrupo != null) {
                idGrupo = em.getReference(idGrupo.getClass(), idGrupo.getIdgrupo());
                grupoAlumnoesquema.setIdGrupo(idGrupo);
            }
            EsquemaAlumnoasignatura esquemaAlumnoasignatura = grupoAlumnoesquema.getEsquemaAlumnoasignatura();
            if (esquemaAlumnoasignatura != null) {
                esquemaAlumnoasignatura = em.getReference(esquemaAlumnoasignatura.getClass(), esquemaAlumnoasignatura.getIdEsquemaalumnoasignatura());
                grupoAlumnoesquema.setEsquemaAlumnoasignatura(esquemaAlumnoasignatura);
            }
            em.persist(grupoAlumnoesquema);
            if (idGrupo != null) {
                idGrupo.getGrupoAlumnoesquemaList().add(grupoAlumnoesquema);
                idGrupo = em.merge(idGrupo);
            }
            if (esquemaAlumnoasignatura != null) {
                esquemaAlumnoasignatura.setGrupoAlumnoesquema(grupoAlumnoesquema);
                esquemaAlumnoasignatura = em.merge(esquemaAlumnoasignatura);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GrupoAlumnoesquema grupoAlumnoesquema) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GrupoAlumnoesquema persistentGrupoAlumnoesquema = em.find(GrupoAlumnoesquema.class, grupoAlumnoesquema.getIdEsquemaalumnoasignatura());
            Grupo idGrupoOld = persistentGrupoAlumnoesquema.getIdGrupo();
            Grupo idGrupoNew = grupoAlumnoesquema.getIdGrupo();
            EsquemaAlumnoasignatura esquemaAlumnoasignaturaOld = persistentGrupoAlumnoesquema.getEsquemaAlumnoasignatura();
            EsquemaAlumnoasignatura esquemaAlumnoasignaturaNew = grupoAlumnoesquema.getEsquemaAlumnoasignatura();
            List<String> illegalOrphanMessages = null;
            if (esquemaAlumnoasignaturaNew != null && !esquemaAlumnoasignaturaNew.equals(esquemaAlumnoasignaturaOld)) {
                GrupoAlumnoesquema oldGrupoAlumnoesquemaOfEsquemaAlumnoasignatura = esquemaAlumnoasignaturaNew.getGrupoAlumnoesquema();
                if (oldGrupoAlumnoesquemaOfEsquemaAlumnoasignatura != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The EsquemaAlumnoasignatura " + esquemaAlumnoasignaturaNew + " already has an item of type GrupoAlumnoesquema whose esquemaAlumnoasignatura column cannot be null. Please make another selection for the esquemaAlumnoasignatura field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idGrupoNew != null) {
                idGrupoNew = em.getReference(idGrupoNew.getClass(), idGrupoNew.getIdgrupo());
                grupoAlumnoesquema.setIdGrupo(idGrupoNew);
            }
            if (esquemaAlumnoasignaturaNew != null) {
                esquemaAlumnoasignaturaNew = em.getReference(esquemaAlumnoasignaturaNew.getClass(), esquemaAlumnoasignaturaNew.getIdEsquemaalumnoasignatura());
                grupoAlumnoesquema.setEsquemaAlumnoasignatura(esquemaAlumnoasignaturaNew);
            }
            grupoAlumnoesquema = em.merge(grupoAlumnoesquema);
            if (idGrupoOld != null && !idGrupoOld.equals(idGrupoNew)) {
                idGrupoOld.getGrupoAlumnoesquemaList().remove(grupoAlumnoesquema);
                idGrupoOld = em.merge(idGrupoOld);
            }
            if (idGrupoNew != null && !idGrupoNew.equals(idGrupoOld)) {
                idGrupoNew.getGrupoAlumnoesquemaList().add(grupoAlumnoesquema);
                idGrupoNew = em.merge(idGrupoNew);
            }
            if (esquemaAlumnoasignaturaOld != null && !esquemaAlumnoasignaturaOld.equals(esquemaAlumnoasignaturaNew)) {
                esquemaAlumnoasignaturaOld.setGrupoAlumnoesquema(null);
                esquemaAlumnoasignaturaOld = em.merge(esquemaAlumnoasignaturaOld);
            }
            if (esquemaAlumnoasignaturaNew != null && !esquemaAlumnoasignaturaNew.equals(esquemaAlumnoasignaturaOld)) {
                esquemaAlumnoasignaturaNew.setGrupoAlumnoesquema(grupoAlumnoesquema);
                esquemaAlumnoasignaturaNew = em.merge(esquemaAlumnoasignaturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = grupoAlumnoesquema.getIdEsquemaalumnoasignatura();
                if (findGrupoAlumnoesquema(id) == null) {
                    throw new NonexistentEntityException("The grupoAlumnoesquema with id " + id + " no longer exists.");
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
            GrupoAlumnoesquema grupoAlumnoesquema;
            try {
                grupoAlumnoesquema = em.getReference(GrupoAlumnoesquema.class, id);
                grupoAlumnoesquema.getIdEsquemaalumnoasignatura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupoAlumnoesquema with id " + id + " no longer exists.", enfe);
            }
            Grupo idGrupo = grupoAlumnoesquema.getIdGrupo();
            if (idGrupo != null) {
                idGrupo.getGrupoAlumnoesquemaList().remove(grupoAlumnoesquema);
                idGrupo = em.merge(idGrupo);
            }
            EsquemaAlumnoasignatura esquemaAlumnoasignatura = grupoAlumnoesquema.getEsquemaAlumnoasignatura();
            if (esquemaAlumnoasignatura != null) {
                esquemaAlumnoasignatura.setGrupoAlumnoesquema(null);
                esquemaAlumnoasignatura = em.merge(esquemaAlumnoasignatura);
            }
            em.remove(grupoAlumnoesquema);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GrupoAlumnoesquema> findGrupoAlumnoesquemaEntities() {
        return findGrupoAlumnoesquemaEntities(true, -1, -1);
    }

    public List<GrupoAlumnoesquema> findGrupoAlumnoesquemaEntities(int maxResults, int firstResult) {
        return findGrupoAlumnoesquemaEntities(false, maxResults, firstResult);
    }

    private List<GrupoAlumnoesquema> findGrupoAlumnoesquemaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from GrupoAlumnoesquema as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public GrupoAlumnoesquema findGrupoAlumnoesquema(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GrupoAlumnoesquema.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrupoAlumnoesquemaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from GrupoAlumnoesquema as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
