/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.SeguroSocial;
import com.utez.integracion.entity.TipoInstitucionseguro;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoInstitucionseguroJpaController implements Serializable {

    public TipoInstitucionseguroJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoInstitucionseguro tipoInstitucionseguro) {
        if (tipoInstitucionseguro.getSeguroSocialList() == null) {
            tipoInstitucionseguro.setSeguroSocialList(new ArrayList<SeguroSocial>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SeguroSocial> attachedSeguroSocialList = new ArrayList<SeguroSocial>();
            for (SeguroSocial seguroSocialListSeguroSocialToAttach : tipoInstitucionseguro.getSeguroSocialList()) {
                seguroSocialListSeguroSocialToAttach = em.getReference(seguroSocialListSeguroSocialToAttach.getClass(), seguroSocialListSeguroSocialToAttach.getCurp());
                attachedSeguroSocialList.add(seguroSocialListSeguroSocialToAttach);
            }
            tipoInstitucionseguro.setSeguroSocialList(attachedSeguroSocialList);
            em.persist(tipoInstitucionseguro);
            for (SeguroSocial seguroSocialListSeguroSocial : tipoInstitucionseguro.getSeguroSocialList()) {
                TipoInstitucionseguro oldInstitucionSeguroOfSeguroSocialListSeguroSocial = seguroSocialListSeguroSocial.getInstitucionSeguro();
                seguroSocialListSeguroSocial.setInstitucionSeguro(tipoInstitucionseguro);
                seguroSocialListSeguroSocial = em.merge(seguroSocialListSeguroSocial);
                if (oldInstitucionSeguroOfSeguroSocialListSeguroSocial != null) {
                    oldInstitucionSeguroOfSeguroSocialListSeguroSocial.getSeguroSocialList().remove(seguroSocialListSeguroSocial);
                    oldInstitucionSeguroOfSeguroSocialListSeguroSocial = em.merge(oldInstitucionSeguroOfSeguroSocialListSeguroSocial);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoInstitucionseguro tipoInstitucionseguro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoInstitucionseguro persistentTipoInstitucionseguro = em.find(TipoInstitucionseguro.class, tipoInstitucionseguro.getIdInstitucionseguro());
            List<SeguroSocial> seguroSocialListOld = persistentTipoInstitucionseguro.getSeguroSocialList();
            List<SeguroSocial> seguroSocialListNew = tipoInstitucionseguro.getSeguroSocialList();
            List<SeguroSocial> attachedSeguroSocialListNew = new ArrayList<SeguroSocial>();
            for (SeguroSocial seguroSocialListNewSeguroSocialToAttach : seguroSocialListNew) {
                seguroSocialListNewSeguroSocialToAttach = em.getReference(seguroSocialListNewSeguroSocialToAttach.getClass(), seguroSocialListNewSeguroSocialToAttach.getCurp());
                attachedSeguroSocialListNew.add(seguroSocialListNewSeguroSocialToAttach);
            }
            seguroSocialListNew = attachedSeguroSocialListNew;
            tipoInstitucionseguro.setSeguroSocialList(seguroSocialListNew);
            tipoInstitucionseguro = em.merge(tipoInstitucionseguro);
            for (SeguroSocial seguroSocialListOldSeguroSocial : seguroSocialListOld) {
                if (!seguroSocialListNew.contains(seguroSocialListOldSeguroSocial)) {
                    seguroSocialListOldSeguroSocial.setInstitucionSeguro(null);
                    seguroSocialListOldSeguroSocial = em.merge(seguroSocialListOldSeguroSocial);
                }
            }
            for (SeguroSocial seguroSocialListNewSeguroSocial : seguroSocialListNew) {
                if (!seguroSocialListOld.contains(seguroSocialListNewSeguroSocial)) {
                    TipoInstitucionseguro oldInstitucionSeguroOfSeguroSocialListNewSeguroSocial = seguroSocialListNewSeguroSocial.getInstitucionSeguro();
                    seguroSocialListNewSeguroSocial.setInstitucionSeguro(tipoInstitucionseguro);
                    seguroSocialListNewSeguroSocial = em.merge(seguroSocialListNewSeguroSocial);
                    if (oldInstitucionSeguroOfSeguroSocialListNewSeguroSocial != null && !oldInstitucionSeguroOfSeguroSocialListNewSeguroSocial.equals(tipoInstitucionseguro)) {
                        oldInstitucionSeguroOfSeguroSocialListNewSeguroSocial.getSeguroSocialList().remove(seguroSocialListNewSeguroSocial);
                        oldInstitucionSeguroOfSeguroSocialListNewSeguroSocial = em.merge(oldInstitucionSeguroOfSeguroSocialListNewSeguroSocial);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoInstitucionseguro.getIdInstitucionseguro();
                if (findTipoInstitucionseguro(id) == null) {
                    throw new NonexistentEntityException("The tipoInstitucionseguro with id " + id + " no longer exists.");
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
            TipoInstitucionseguro tipoInstitucionseguro;
            try {
                tipoInstitucionseguro = em.getReference(TipoInstitucionseguro.class, id);
                tipoInstitucionseguro.getIdInstitucionseguro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoInstitucionseguro with id " + id + " no longer exists.", enfe);
            }
            List<SeguroSocial> seguroSocialList = tipoInstitucionseguro.getSeguroSocialList();
            for (SeguroSocial seguroSocialListSeguroSocial : seguroSocialList) {
                seguroSocialListSeguroSocial.setInstitucionSeguro(null);
                seguroSocialListSeguroSocial = em.merge(seguroSocialListSeguroSocial);
            }
            em.remove(tipoInstitucionseguro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoInstitucionseguro> findTipoInstitucionseguroEntities() {
        return findTipoInstitucionseguroEntities(true, -1, -1);
    }

    public List<TipoInstitucionseguro> findTipoInstitucionseguroEntities(int maxResults, int firstResult) {
        return findTipoInstitucionseguroEntities(false, maxResults, firstResult);
    }

    private List<TipoInstitucionseguro> findTipoInstitucionseguroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoInstitucionseguro as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoInstitucionseguro findTipoInstitucionseguro(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoInstitucionseguro.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoInstitucionseguroCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoInstitucionseguro as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
