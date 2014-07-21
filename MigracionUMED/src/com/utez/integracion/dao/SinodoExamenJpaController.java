/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.SinodoExamen;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TramiteTitulacion;
import com.utez.integracion.entity.TipoSinodo;
import com.utez.secretariaAcademica.entity.Asesor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class SinodoExamenJpaController implements Serializable {

    public SinodoExamenJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SinodoExamen sinodoExamen) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TramiteTitulacion idTramitetitulacion = sinodoExamen.getIdTramitetitulacion();
            if (idTramitetitulacion != null) {
                idTramitetitulacion = em.getReference(idTramitetitulacion.getClass(), idTramitetitulacion.getIdTramitetitulacion());
                sinodoExamen.setIdTramitetitulacion(idTramitetitulacion);
            }
            TipoSinodo idTiposinodo = sinodoExamen.getIdTiposinodo();
            if (idTiposinodo != null) {
                idTiposinodo = em.getReference(idTiposinodo.getClass(), idTiposinodo.getIdTiposinodo());
                sinodoExamen.setIdTiposinodo(idTiposinodo);
            }
            Asesor idAsesor = sinodoExamen.getIdAsesor();
            if (idAsesor != null) {
                idAsesor = em.getReference(idAsesor.getClass(), idAsesor.getIdasesor());
                sinodoExamen.setIdAsesor(idAsesor);
            }
            em.persist(sinodoExamen);
            if (idTramitetitulacion != null) {
                idTramitetitulacion.getSinodoExamenList().add(sinodoExamen);
                idTramitetitulacion = em.merge(idTramitetitulacion);
            }
            if (idTiposinodo != null) {
                idTiposinodo.getSinodoExamenList().add(sinodoExamen);
                idTiposinodo = em.merge(idTiposinodo);
            }
            if (idAsesor != null) {
                idAsesor.getSinodoExamenList().add(sinodoExamen);
                idAsesor = em.merge(idAsesor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SinodoExamen sinodoExamen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SinodoExamen persistentSinodoExamen = em.find(SinodoExamen.class, sinodoExamen.getIdSinodo());
            TramiteTitulacion idTramitetitulacionOld = persistentSinodoExamen.getIdTramitetitulacion();
            TramiteTitulacion idTramitetitulacionNew = sinodoExamen.getIdTramitetitulacion();
            TipoSinodo idTiposinodoOld = persistentSinodoExamen.getIdTiposinodo();
            TipoSinodo idTiposinodoNew = sinodoExamen.getIdTiposinodo();
            Asesor idAsesorOld = persistentSinodoExamen.getIdAsesor();
            Asesor idAsesorNew = sinodoExamen.getIdAsesor();
            if (idTramitetitulacionNew != null) {
                idTramitetitulacionNew = em.getReference(idTramitetitulacionNew.getClass(), idTramitetitulacionNew.getIdTramitetitulacion());
                sinodoExamen.setIdTramitetitulacion(idTramitetitulacionNew);
            }
            if (idTiposinodoNew != null) {
                idTiposinodoNew = em.getReference(idTiposinodoNew.getClass(), idTiposinodoNew.getIdTiposinodo());
                sinodoExamen.setIdTiposinodo(idTiposinodoNew);
            }
            if (idAsesorNew != null) {
                idAsesorNew = em.getReference(idAsesorNew.getClass(), idAsesorNew.getIdasesor());
                sinodoExamen.setIdAsesor(idAsesorNew);
            }
            sinodoExamen = em.merge(sinodoExamen);
            if (idTramitetitulacionOld != null && !idTramitetitulacionOld.equals(idTramitetitulacionNew)) {
                idTramitetitulacionOld.getSinodoExamenList().remove(sinodoExamen);
                idTramitetitulacionOld = em.merge(idTramitetitulacionOld);
            }
            if (idTramitetitulacionNew != null && !idTramitetitulacionNew.equals(idTramitetitulacionOld)) {
                idTramitetitulacionNew.getSinodoExamenList().add(sinodoExamen);
                idTramitetitulacionNew = em.merge(idTramitetitulacionNew);
            }
            if (idTiposinodoOld != null && !idTiposinodoOld.equals(idTiposinodoNew)) {
                idTiposinodoOld.getSinodoExamenList().remove(sinodoExamen);
                idTiposinodoOld = em.merge(idTiposinodoOld);
            }
            if (idTiposinodoNew != null && !idTiposinodoNew.equals(idTiposinodoOld)) {
                idTiposinodoNew.getSinodoExamenList().add(sinodoExamen);
                idTiposinodoNew = em.merge(idTiposinodoNew);
            }
            if (idAsesorOld != null && !idAsesorOld.equals(idAsesorNew)) {
                idAsesorOld.getSinodoExamenList().remove(sinodoExamen);
                idAsesorOld = em.merge(idAsesorOld);
            }
            if (idAsesorNew != null && !idAsesorNew.equals(idAsesorOld)) {
                idAsesorNew.getSinodoExamenList().add(sinodoExamen);
                idAsesorNew = em.merge(idAsesorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = sinodoExamen.getIdSinodo();
                if (findSinodoExamen(id) == null) {
                    throw new NonexistentEntityException("The sinodoExamen with id " + id + " no longer exists.");
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
            SinodoExamen sinodoExamen;
            try {
                sinodoExamen = em.getReference(SinodoExamen.class, id);
                sinodoExamen.getIdSinodo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sinodoExamen with id " + id + " no longer exists.", enfe);
            }
            TramiteTitulacion idTramitetitulacion = sinodoExamen.getIdTramitetitulacion();
            if (idTramitetitulacion != null) {
                idTramitetitulacion.getSinodoExamenList().remove(sinodoExamen);
                idTramitetitulacion = em.merge(idTramitetitulacion);
            }
            TipoSinodo idTiposinodo = sinodoExamen.getIdTiposinodo();
            if (idTiposinodo != null) {
                idTiposinodo.getSinodoExamenList().remove(sinodoExamen);
                idTiposinodo = em.merge(idTiposinodo);
            }
            Asesor idAsesor = sinodoExamen.getIdAsesor();
            if (idAsesor != null) {
                idAsesor.getSinodoExamenList().remove(sinodoExamen);
                idAsesor = em.merge(idAsesor);
            }
            em.remove(sinodoExamen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SinodoExamen> findSinodoExamenEntities() {
        return findSinodoExamenEntities(true, -1, -1);
    }

    public List<SinodoExamen> findSinodoExamenEntities(int maxResults, int firstResult) {
        return findSinodoExamenEntities(false, maxResults, firstResult);
    }

    private List<SinodoExamen> findSinodoExamenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from SinodoExamen as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public SinodoExamen findSinodoExamen(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SinodoExamen.class, id);
        } finally {
            em.close();
        }
    }

    public int getSinodoExamenCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from SinodoExamen as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
