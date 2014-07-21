/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Documento;
import com.utez.integracion.entity.TipoDocumento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoDocumentoJpaController implements Serializable {

    public TipoDocumentoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoDocumento tipoDocumento) {
        if (tipoDocumento.getDocumentoList() == null) {
            tipoDocumento.setDocumentoList(new ArrayList<Documento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Documento> attachedDocumentoList = new ArrayList<Documento>();
            for (Documento documentoListDocumentoToAttach : tipoDocumento.getDocumentoList()) {
                documentoListDocumentoToAttach = em.getReference(documentoListDocumentoToAttach.getClass(), documentoListDocumentoToAttach.getIdDocumento());
                attachedDocumentoList.add(documentoListDocumentoToAttach);
            }
            tipoDocumento.setDocumentoList(attachedDocumentoList);
            em.persist(tipoDocumento);
            for (Documento documentoListDocumento : tipoDocumento.getDocumentoList()) {
                TipoDocumento oldIdTipodocumentoOfDocumentoListDocumento = documentoListDocumento.getIdTipodocumento();
                documentoListDocumento.setIdTipodocumento(tipoDocumento);
                documentoListDocumento = em.merge(documentoListDocumento);
                if (oldIdTipodocumentoOfDocumentoListDocumento != null) {
                    oldIdTipodocumentoOfDocumentoListDocumento.getDocumentoList().remove(documentoListDocumento);
                    oldIdTipodocumentoOfDocumentoListDocumento = em.merge(oldIdTipodocumentoOfDocumentoListDocumento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoDocumento tipoDocumento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDocumento persistentTipoDocumento = em.find(TipoDocumento.class, tipoDocumento.getIdTipodocumento());
            List<Documento> documentoListOld = persistentTipoDocumento.getDocumentoList();
            List<Documento> documentoListNew = tipoDocumento.getDocumentoList();
            List<Documento> attachedDocumentoListNew = new ArrayList<Documento>();
            for (Documento documentoListNewDocumentoToAttach : documentoListNew) {
                documentoListNewDocumentoToAttach = em.getReference(documentoListNewDocumentoToAttach.getClass(), documentoListNewDocumentoToAttach.getIdDocumento());
                attachedDocumentoListNew.add(documentoListNewDocumentoToAttach);
            }
            documentoListNew = attachedDocumentoListNew;
            tipoDocumento.setDocumentoList(documentoListNew);
            tipoDocumento = em.merge(tipoDocumento);
            for (Documento documentoListOldDocumento : documentoListOld) {
                if (!documentoListNew.contains(documentoListOldDocumento)) {
                    documentoListOldDocumento.setIdTipodocumento(null);
                    documentoListOldDocumento = em.merge(documentoListOldDocumento);
                }
            }
            for (Documento documentoListNewDocumento : documentoListNew) {
                if (!documentoListOld.contains(documentoListNewDocumento)) {
                    TipoDocumento oldIdTipodocumentoOfDocumentoListNewDocumento = documentoListNewDocumento.getIdTipodocumento();
                    documentoListNewDocumento.setIdTipodocumento(tipoDocumento);
                    documentoListNewDocumento = em.merge(documentoListNewDocumento);
                    if (oldIdTipodocumentoOfDocumentoListNewDocumento != null && !oldIdTipodocumentoOfDocumentoListNewDocumento.equals(tipoDocumento)) {
                        oldIdTipodocumentoOfDocumentoListNewDocumento.getDocumentoList().remove(documentoListNewDocumento);
                        oldIdTipodocumentoOfDocumentoListNewDocumento = em.merge(oldIdTipodocumentoOfDocumentoListNewDocumento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoDocumento.getIdTipodocumento();
                if (findTipoDocumento(id) == null) {
                    throw new NonexistentEntityException("The tipoDocumento with id " + id + " no longer exists.");
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
            TipoDocumento tipoDocumento;
            try {
                tipoDocumento = em.getReference(TipoDocumento.class, id);
                tipoDocumento.getIdTipodocumento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoDocumento with id " + id + " no longer exists.", enfe);
            }
            List<Documento> documentoList = tipoDocumento.getDocumentoList();
            for (Documento documentoListDocumento : documentoList) {
                documentoListDocumento.setIdTipodocumento(null);
                documentoListDocumento = em.merge(documentoListDocumento);
            }
            em.remove(tipoDocumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoDocumento> findTipoDocumentoEntities() {
        return findTipoDocumentoEntities(true, -1, -1);
    }

    public List<TipoDocumento> findTipoDocumentoEntities(int maxResults, int firstResult) {
        return findTipoDocumentoEntities(false, maxResults, firstResult);
    }

    private List<TipoDocumento> findTipoDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoDocumento as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoDocumento findTipoDocumento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoDocumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoDocumento as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
