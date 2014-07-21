/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.AsignaturaAsesor;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.MotivoAsignaturaasesor;
import com.utez.integracion.entity.CalendarioAsignatura;
import com.utez.secretariaAcademica.entity.Asesor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignaturaAsesorJpaController implements Serializable {

    public AsignaturaAsesorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignaturaAsesor asignaturaAsesor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MotivoAsignaturaasesor motivoAsignaturaasesor = asignaturaAsesor.getMotivoAsignaturaasesor();
            if (motivoAsignaturaasesor != null) {
                motivoAsignaturaasesor = em.getReference(motivoAsignaturaasesor.getClass(), motivoAsignaturaasesor.getIdAsignaturaasesor());
                asignaturaAsesor.setMotivoAsignaturaasesor(motivoAsignaturaasesor);
            }
            CalendarioAsignatura idCalendarioasignatura = asignaturaAsesor.getIdCalendarioasignatura();
            if (idCalendarioasignatura != null) {
                idCalendarioasignatura = em.getReference(idCalendarioasignatura.getClass(), idCalendarioasignatura.getIdCalendarioasignatura());
                asignaturaAsesor.setIdCalendarioasignatura(idCalendarioasignatura);
            }
            Asesor idAsesor = asignaturaAsesor.getIdAsesor();
            if (idAsesor != null) {
                idAsesor = em.getReference(idAsesor.getClass(), idAsesor.getIdasesor());
                asignaturaAsesor.setIdAsesor(idAsesor);
            }
            em.persist(asignaturaAsesor);
            if (motivoAsignaturaasesor != null) {
                AsignaturaAsesor oldAsignaturaAsesorOfMotivoAsignaturaasesor = motivoAsignaturaasesor.getAsignaturaAsesor();
                if (oldAsignaturaAsesorOfMotivoAsignaturaasesor != null) {
                    oldAsignaturaAsesorOfMotivoAsignaturaasesor.setMotivoAsignaturaasesor(null);
                    oldAsignaturaAsesorOfMotivoAsignaturaasesor = em.merge(oldAsignaturaAsesorOfMotivoAsignaturaasesor);
                }
                motivoAsignaturaasesor.setAsignaturaAsesor(asignaturaAsesor);
                motivoAsignaturaasesor = em.merge(motivoAsignaturaasesor);
            }
            if (idCalendarioasignatura != null) {
                idCalendarioasignatura.getAsignaturaAsesorList().add(asignaturaAsesor);
                idCalendarioasignatura = em.merge(idCalendarioasignatura);
            }
            if (idAsesor != null) {
                idAsesor.getAsignaturaAsesorList().add(asignaturaAsesor);
                idAsesor = em.merge(idAsesor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignaturaAsesor asignaturaAsesor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignaturaAsesor persistentAsignaturaAsesor = em.find(AsignaturaAsesor.class, asignaturaAsesor.getIdAsignaturaasesor());
            MotivoAsignaturaasesor motivoAsignaturaasesorOld = persistentAsignaturaAsesor.getMotivoAsignaturaasesor();
            MotivoAsignaturaasesor motivoAsignaturaasesorNew = asignaturaAsesor.getMotivoAsignaturaasesor();
            CalendarioAsignatura idCalendarioasignaturaOld = persistentAsignaturaAsesor.getIdCalendarioasignatura();
            CalendarioAsignatura idCalendarioasignaturaNew = asignaturaAsesor.getIdCalendarioasignatura();
            Asesor idAsesorOld = persistentAsignaturaAsesor.getIdAsesor();
            Asesor idAsesorNew = asignaturaAsesor.getIdAsesor();
            List<String> illegalOrphanMessages = null;
            if (motivoAsignaturaasesorOld != null && !motivoAsignaturaasesorOld.equals(motivoAsignaturaasesorNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain MotivoAsignaturaasesor " + motivoAsignaturaasesorOld + " since its asignaturaAsesor field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (motivoAsignaturaasesorNew != null) {
                motivoAsignaturaasesorNew = em.getReference(motivoAsignaturaasesorNew.getClass(), motivoAsignaturaasesorNew.getIdAsignaturaasesor());
                asignaturaAsesor.setMotivoAsignaturaasesor(motivoAsignaturaasesorNew);
            }
            if (idCalendarioasignaturaNew != null) {
                idCalendarioasignaturaNew = em.getReference(idCalendarioasignaturaNew.getClass(), idCalendarioasignaturaNew.getIdCalendarioasignatura());
                asignaturaAsesor.setIdCalendarioasignatura(idCalendarioasignaturaNew);
            }
            if (idAsesorNew != null) {
                idAsesorNew = em.getReference(idAsesorNew.getClass(), idAsesorNew.getIdasesor());
                asignaturaAsesor.setIdAsesor(idAsesorNew);
            }
            asignaturaAsesor = em.merge(asignaturaAsesor);
            if (motivoAsignaturaasesorNew != null && !motivoAsignaturaasesorNew.equals(motivoAsignaturaasesorOld)) {
                AsignaturaAsesor oldAsignaturaAsesorOfMotivoAsignaturaasesor = motivoAsignaturaasesorNew.getAsignaturaAsesor();
                if (oldAsignaturaAsesorOfMotivoAsignaturaasesor != null) {
                    oldAsignaturaAsesorOfMotivoAsignaturaasesor.setMotivoAsignaturaasesor(null);
                    oldAsignaturaAsesorOfMotivoAsignaturaasesor = em.merge(oldAsignaturaAsesorOfMotivoAsignaturaasesor);
                }
                motivoAsignaturaasesorNew.setAsignaturaAsesor(asignaturaAsesor);
                motivoAsignaturaasesorNew = em.merge(motivoAsignaturaasesorNew);
            }
            if (idCalendarioasignaturaOld != null && !idCalendarioasignaturaOld.equals(idCalendarioasignaturaNew)) {
                idCalendarioasignaturaOld.getAsignaturaAsesorList().remove(asignaturaAsesor);
                idCalendarioasignaturaOld = em.merge(idCalendarioasignaturaOld);
            }
            if (idCalendarioasignaturaNew != null && !idCalendarioasignaturaNew.equals(idCalendarioasignaturaOld)) {
                idCalendarioasignaturaNew.getAsignaturaAsesorList().add(asignaturaAsesor);
                idCalendarioasignaturaNew = em.merge(idCalendarioasignaturaNew);
            }
            if (idAsesorOld != null && !idAsesorOld.equals(idAsesorNew)) {
                idAsesorOld.getAsignaturaAsesorList().remove(asignaturaAsesor);
                idAsesorOld = em.merge(idAsesorOld);
            }
            if (idAsesorNew != null && !idAsesorNew.equals(idAsesorOld)) {
                idAsesorNew.getAsignaturaAsesorList().add(asignaturaAsesor);
                idAsesorNew = em.merge(idAsesorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignaturaAsesor.getIdAsignaturaasesor();
                if (findAsignaturaAsesor(id) == null) {
                    throw new NonexistentEntityException("The asignaturaAsesor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignaturaAsesor asignaturaAsesor;
            try {
                asignaturaAsesor = em.getReference(AsignaturaAsesor.class, id);
                asignaturaAsesor.getIdAsignaturaasesor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignaturaAsesor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            MotivoAsignaturaasesor motivoAsignaturaasesorOrphanCheck = asignaturaAsesor.getMotivoAsignaturaasesor();
            if (motivoAsignaturaasesorOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AsignaturaAsesor (" + asignaturaAsesor + ") cannot be destroyed since the MotivoAsignaturaasesor " + motivoAsignaturaasesorOrphanCheck + " in its motivoAsignaturaasesor field has a non-nullable asignaturaAsesor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CalendarioAsignatura idCalendarioasignatura = asignaturaAsesor.getIdCalendarioasignatura();
            if (idCalendarioasignatura != null) {
                idCalendarioasignatura.getAsignaturaAsesorList().remove(asignaturaAsesor);
                idCalendarioasignatura = em.merge(idCalendarioasignatura);
            }
            Asesor idAsesor = asignaturaAsesor.getIdAsesor();
            if (idAsesor != null) {
                idAsesor.getAsignaturaAsesorList().remove(asignaturaAsesor);
                idAsesor = em.merge(idAsesor);
            }
            em.remove(asignaturaAsesor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignaturaAsesor> findAsignaturaAsesorEntities() {
        return findAsignaturaAsesorEntities(true, -1, -1);
    }

    public List<AsignaturaAsesor> findAsignaturaAsesorEntities(int maxResults, int firstResult) {
        return findAsignaturaAsesorEntities(false, maxResults, firstResult);
    }

    private List<AsignaturaAsesor> findAsignaturaAsesorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignaturaAsesor as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignaturaAsesor findAsignaturaAsesor(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignaturaAsesor.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignaturaAsesorCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignaturaAsesor as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
