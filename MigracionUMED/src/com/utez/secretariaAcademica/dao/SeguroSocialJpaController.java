/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoInstitucionseguro;
import com.utez.integracion.entity.Persona;
import com.utez.integracion.entity.SeguroSocial;
import com.utez.secretariaAcademica.dao.exceptions.IllegalOrphanException;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secretariaAcademica.dao.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class SeguroSocialJpaController implements Serializable {

    public SeguroSocialJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeguroSocial seguroSocial) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Persona personaOrphanCheck = seguroSocial.getPersona();
        if (personaOrphanCheck != null) {
            SeguroSocial oldSeguroSocialOfPersona = personaOrphanCheck.getSeguroSocial();
            if (oldSeguroSocialOfPersona != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Persona " + personaOrphanCheck + " already has an item of type SeguroSocial whose persona column cannot be null. Please make another selection for the persona field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoInstitucionseguro institucionSeguro = seguroSocial.getInstitucionSeguro();
            if (institucionSeguro != null) {
                institucionSeguro = em.getReference(institucionSeguro.getClass(), institucionSeguro.getIdInstitucionseguro());
                seguroSocial.setInstitucionSeguro(institucionSeguro);
            }
            Persona persona = seguroSocial.getPersona();
            if (persona != null) {
                persona = em.getReference(persona.getClass(), persona.getCurp());
                seguroSocial.setPersona(persona);
            }
            em.persist(seguroSocial);
            if (institucionSeguro != null) {
                institucionSeguro.getSeguroSocialList().add(seguroSocial);
                institucionSeguro = em.merge(institucionSeguro);
            }
            if (persona != null) {
                persona.setSeguroSocial(seguroSocial);
                persona = em.merge(persona);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSeguroSocial(seguroSocial.getCurp()) != null) {
                throw new PreexistingEntityException("SeguroSocial " + seguroSocial + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeguroSocial seguroSocial) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeguroSocial persistentSeguroSocial = em.find(SeguroSocial.class, seguroSocial.getCurp());
            TipoInstitucionseguro institucionSeguroOld = persistentSeguroSocial.getInstitucionSeguro();
            TipoInstitucionseguro institucionSeguroNew = seguroSocial.getInstitucionSeguro();
            Persona personaOld = persistentSeguroSocial.getPersona();
            Persona personaNew = seguroSocial.getPersona();
            List<String> illegalOrphanMessages = null;
            if (personaNew != null && !personaNew.equals(personaOld)) {
                SeguroSocial oldSeguroSocialOfPersona = personaNew.getSeguroSocial();
                if (oldSeguroSocialOfPersona != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Persona " + personaNew + " already has an item of type SeguroSocial whose persona column cannot be null. Please make another selection for the persona field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (institucionSeguroNew != null) {
                institucionSeguroNew = em.getReference(institucionSeguroNew.getClass(), institucionSeguroNew.getIdInstitucionseguro());
                seguroSocial.setInstitucionSeguro(institucionSeguroNew);
            }
            if (personaNew != null) {
                personaNew = em.getReference(personaNew.getClass(), personaNew.getCurp());
                seguroSocial.setPersona(personaNew);
            }
            seguroSocial = em.merge(seguroSocial);
            if (institucionSeguroOld != null && !institucionSeguroOld.equals(institucionSeguroNew)) {
                institucionSeguroOld.getSeguroSocialList().remove(seguroSocial);
                institucionSeguroOld = em.merge(institucionSeguroOld);
            }
            if (institucionSeguroNew != null && !institucionSeguroNew.equals(institucionSeguroOld)) {
                institucionSeguroNew.getSeguroSocialList().add(seguroSocial);
                institucionSeguroNew = em.merge(institucionSeguroNew);
            }
            if (personaOld != null && !personaOld.equals(personaNew)) {
                personaOld.setSeguroSocial(null);
                personaOld = em.merge(personaOld);
            }
            if (personaNew != null && !personaNew.equals(personaOld)) {
                personaNew.setSeguroSocial(seguroSocial);
                personaNew = em.merge(personaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = seguroSocial.getCurp();
                if (findSeguroSocial(id) == null) {
                    throw new NonexistentEntityException("The seguroSocial with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeguroSocial seguroSocial;
            try {
                seguroSocial = em.getReference(SeguroSocial.class, id);
                seguroSocial.getCurp();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seguroSocial with id " + id + " no longer exists.", enfe);
            }
            TipoInstitucionseguro institucionSeguro = seguroSocial.getInstitucionSeguro();
            if (institucionSeguro != null) {
                institucionSeguro.getSeguroSocialList().remove(seguroSocial);
                institucionSeguro = em.merge(institucionSeguro);
            }
            Persona persona = seguroSocial.getPersona();
            if (persona != null) {
                persona.setSeguroSocial(null);
                persona = em.merge(persona);
            }
            em.remove(seguroSocial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeguroSocial> findSeguroSocialEntities() {
        return findSeguroSocialEntities(true, -1, -1);
    }

    public List<SeguroSocial> findSeguroSocialEntities(int maxResults, int firstResult) {
        return findSeguroSocialEntities(false, maxResults, firstResult);
    }

    private List<SeguroSocial> findSeguroSocialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from SeguroSocial as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public SeguroSocial findSeguroSocial(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeguroSocial.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeguroSocialCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from SeguroSocial as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
