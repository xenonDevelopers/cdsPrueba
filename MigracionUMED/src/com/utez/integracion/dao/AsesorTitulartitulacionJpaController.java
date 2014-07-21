/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import com.utez.integracion.entity.AsesorTitulartitulacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TramiteTitulacion;
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
public class AsesorTitulartitulacionJpaController implements Serializable {

    public AsesorTitulartitulacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsesorTitulartitulacion asesorTitulartitulacion) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        TramiteTitulacion tramiteTitulacionOrphanCheck = asesorTitulartitulacion.getTramiteTitulacion();
        if (tramiteTitulacionOrphanCheck != null) {
            AsesorTitulartitulacion oldAsesorTitulartitulacionOfTramiteTitulacion = tramiteTitulacionOrphanCheck.getAsesorTitulartitulacion();
            if (oldAsesorTitulartitulacionOfTramiteTitulacion != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The TramiteTitulacion " + tramiteTitulacionOrphanCheck + " already has an item of type AsesorTitulartitulacion whose tramiteTitulacion column cannot be null. Please make another selection for the tramiteTitulacion field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TramiteTitulacion tramiteTitulacion = asesorTitulartitulacion.getTramiteTitulacion();
            if (tramiteTitulacion != null) {
                tramiteTitulacion = em.getReference(tramiteTitulacion.getClass(), tramiteTitulacion.getIdTramitetitulacion());
                asesorTitulartitulacion.setTramiteTitulacion(tramiteTitulacion);
            }
            Asesor idAsesor = asesorTitulartitulacion.getIdAsesor();
            if (idAsesor != null) {
                idAsesor = em.getReference(idAsesor.getClass(), idAsesor.getIdasesor());
                asesorTitulartitulacion.setIdAsesor(idAsesor);
            }
            em.persist(asesorTitulartitulacion);
            if (tramiteTitulacion != null) {
                tramiteTitulacion.setAsesorTitulartitulacion(asesorTitulartitulacion);
                tramiteTitulacion = em.merge(tramiteTitulacion);
            }
            if (idAsesor != null) {
                idAsesor.getAsesorTitulartitulacionList().add(asesorTitulartitulacion);
                idAsesor = em.merge(idAsesor);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAsesorTitulartitulacion(asesorTitulartitulacion.getIdTramitetitulacion()) != null) {
                throw new PreexistingEntityException("AsesorTitulartitulacion " + asesorTitulartitulacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsesorTitulartitulacion asesorTitulartitulacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsesorTitulartitulacion persistentAsesorTitulartitulacion = em.find(AsesorTitulartitulacion.class, asesorTitulartitulacion.getIdTramitetitulacion());
            TramiteTitulacion tramiteTitulacionOld = persistentAsesorTitulartitulacion.getTramiteTitulacion();
            TramiteTitulacion tramiteTitulacionNew = asesorTitulartitulacion.getTramiteTitulacion();
            Asesor idAsesorOld = persistentAsesorTitulartitulacion.getIdAsesor();
            Asesor idAsesorNew = asesorTitulartitulacion.getIdAsesor();
            List<String> illegalOrphanMessages = null;
            if (tramiteTitulacionNew != null && !tramiteTitulacionNew.equals(tramiteTitulacionOld)) {
                AsesorTitulartitulacion oldAsesorTitulartitulacionOfTramiteTitulacion = tramiteTitulacionNew.getAsesorTitulartitulacion();
                if (oldAsesorTitulartitulacionOfTramiteTitulacion != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The TramiteTitulacion " + tramiteTitulacionNew + " already has an item of type AsesorTitulartitulacion whose tramiteTitulacion column cannot be null. Please make another selection for the tramiteTitulacion field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tramiteTitulacionNew != null) {
                tramiteTitulacionNew = em.getReference(tramiteTitulacionNew.getClass(), tramiteTitulacionNew.getIdTramitetitulacion());
                asesorTitulartitulacion.setTramiteTitulacion(tramiteTitulacionNew);
            }
            if (idAsesorNew != null) {
                idAsesorNew = em.getReference(idAsesorNew.getClass(), idAsesorNew.getIdasesor());
                asesorTitulartitulacion.setIdAsesor(idAsesorNew);
            }
            asesorTitulartitulacion = em.merge(asesorTitulartitulacion);
            if (tramiteTitulacionOld != null && !tramiteTitulacionOld.equals(tramiteTitulacionNew)) {
                tramiteTitulacionOld.setAsesorTitulartitulacion(null);
                tramiteTitulacionOld = em.merge(tramiteTitulacionOld);
            }
            if (tramiteTitulacionNew != null && !tramiteTitulacionNew.equals(tramiteTitulacionOld)) {
                tramiteTitulacionNew.setAsesorTitulartitulacion(asesorTitulartitulacion);
                tramiteTitulacionNew = em.merge(tramiteTitulacionNew);
            }
            if (idAsesorOld != null && !idAsesorOld.equals(idAsesorNew)) {
                idAsesorOld.getAsesorTitulartitulacionList().remove(asesorTitulartitulacion);
                idAsesorOld = em.merge(idAsesorOld);
            }
            if (idAsesorNew != null && !idAsesorNew.equals(idAsesorOld)) {
                idAsesorNew.getAsesorTitulartitulacionList().add(asesorTitulartitulacion);
                idAsesorNew = em.merge(idAsesorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asesorTitulartitulacion.getIdTramitetitulacion();
                if (findAsesorTitulartitulacion(id) == null) {
                    throw new NonexistentEntityException("The asesorTitulartitulacion with id " + id + " no longer exists.");
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
            AsesorTitulartitulacion asesorTitulartitulacion;
            try {
                asesorTitulartitulacion = em.getReference(AsesorTitulartitulacion.class, id);
                asesorTitulartitulacion.getIdTramitetitulacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asesorTitulartitulacion with id " + id + " no longer exists.", enfe);
            }
            TramiteTitulacion tramiteTitulacion = asesorTitulartitulacion.getTramiteTitulacion();
            if (tramiteTitulacion != null) {
                tramiteTitulacion.setAsesorTitulartitulacion(null);
                tramiteTitulacion = em.merge(tramiteTitulacion);
            }
            Asesor idAsesor = asesorTitulartitulacion.getIdAsesor();
            if (idAsesor != null) {
                idAsesor.getAsesorTitulartitulacionList().remove(asesorTitulartitulacion);
                idAsesor = em.merge(idAsesor);
            }
            em.remove(asesorTitulartitulacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsesorTitulartitulacion> findAsesorTitulartitulacionEntities() {
        return findAsesorTitulartitulacionEntities(true, -1, -1);
    }

    public List<AsesorTitulartitulacion> findAsesorTitulartitulacionEntities(int maxResults, int firstResult) {
        return findAsesorTitulartitulacionEntities(false, maxResults, firstResult);
    }

    private List<AsesorTitulartitulacion> findAsesorTitulartitulacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsesorTitulartitulacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsesorTitulartitulacion findAsesorTitulartitulacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsesorTitulartitulacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsesorTitulartitulacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsesorTitulartitulacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
