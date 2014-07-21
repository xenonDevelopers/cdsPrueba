/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.AsignacionAutorintegradora;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.AsignacionAutorbanco;
import com.utez.integracion.entity.TipoAutor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoAutorJpaController implements Serializable {

    public TipoAutorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoAutor tipoAutor) {
        if (tipoAutor.getAsignacionAutorintegradoraList() == null) {
            tipoAutor.setAsignacionAutorintegradoraList(new ArrayList<AsignacionAutorintegradora>());
        }
        if (tipoAutor.getAsignacionAutorbancoList() == null) {
            tipoAutor.setAsignacionAutorbancoList(new ArrayList<AsignacionAutorbanco>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<AsignacionAutorintegradora> attachedAsignacionAutorintegradoraList = new ArrayList<AsignacionAutorintegradora>();
            for (AsignacionAutorintegradora asignacionAutorintegradoraListAsignacionAutorintegradoraToAttach : tipoAutor.getAsignacionAutorintegradoraList()) {
                asignacionAutorintegradoraListAsignacionAutorintegradoraToAttach = em.getReference(asignacionAutorintegradoraListAsignacionAutorintegradoraToAttach.getClass(), asignacionAutorintegradoraListAsignacionAutorintegradoraToAttach.getIdAsignacionautorintegradora());
                attachedAsignacionAutorintegradoraList.add(asignacionAutorintegradoraListAsignacionAutorintegradoraToAttach);
            }
            tipoAutor.setAsignacionAutorintegradoraList(attachedAsignacionAutorintegradoraList);
            List<AsignacionAutorbanco> attachedAsignacionAutorbancoList = new ArrayList<AsignacionAutorbanco>();
            for (AsignacionAutorbanco asignacionAutorbancoListAsignacionAutorbancoToAttach : tipoAutor.getAsignacionAutorbancoList()) {
                asignacionAutorbancoListAsignacionAutorbancoToAttach = em.getReference(asignacionAutorbancoListAsignacionAutorbancoToAttach.getClass(), asignacionAutorbancoListAsignacionAutorbancoToAttach.getIdAsignacionautorbanco());
                attachedAsignacionAutorbancoList.add(asignacionAutorbancoListAsignacionAutorbancoToAttach);
            }
            tipoAutor.setAsignacionAutorbancoList(attachedAsignacionAutorbancoList);
            em.persist(tipoAutor);
            for (AsignacionAutorintegradora asignacionAutorintegradoraListAsignacionAutorintegradora : tipoAutor.getAsignacionAutorintegradoraList()) {
                TipoAutor oldIdTipoautorOfAsignacionAutorintegradoraListAsignacionAutorintegradora = asignacionAutorintegradoraListAsignacionAutorintegradora.getIdTipoautor();
                asignacionAutorintegradoraListAsignacionAutorintegradora.setIdTipoautor(tipoAutor);
                asignacionAutorintegradoraListAsignacionAutorintegradora = em.merge(asignacionAutorintegradoraListAsignacionAutorintegradora);
                if (oldIdTipoautorOfAsignacionAutorintegradoraListAsignacionAutorintegradora != null) {
                    oldIdTipoautorOfAsignacionAutorintegradoraListAsignacionAutorintegradora.getAsignacionAutorintegradoraList().remove(asignacionAutorintegradoraListAsignacionAutorintegradora);
                    oldIdTipoautorOfAsignacionAutorintegradoraListAsignacionAutorintegradora = em.merge(oldIdTipoautorOfAsignacionAutorintegradoraListAsignacionAutorintegradora);
                }
            }
            for (AsignacionAutorbanco asignacionAutorbancoListAsignacionAutorbanco : tipoAutor.getAsignacionAutorbancoList()) {
                TipoAutor oldIdTipoautorOfAsignacionAutorbancoListAsignacionAutorbanco = asignacionAutorbancoListAsignacionAutorbanco.getIdTipoautor();
                asignacionAutorbancoListAsignacionAutorbanco.setIdTipoautor(tipoAutor);
                asignacionAutorbancoListAsignacionAutorbanco = em.merge(asignacionAutorbancoListAsignacionAutorbanco);
                if (oldIdTipoautorOfAsignacionAutorbancoListAsignacionAutorbanco != null) {
                    oldIdTipoautorOfAsignacionAutorbancoListAsignacionAutorbanco.getAsignacionAutorbancoList().remove(asignacionAutorbancoListAsignacionAutorbanco);
                    oldIdTipoautorOfAsignacionAutorbancoListAsignacionAutorbanco = em.merge(oldIdTipoautorOfAsignacionAutorbancoListAsignacionAutorbanco);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoAutor tipoAutor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoAutor persistentTipoAutor = em.find(TipoAutor.class, tipoAutor.getIdTipoautor());
            List<AsignacionAutorintegradora> asignacionAutorintegradoraListOld = persistentTipoAutor.getAsignacionAutorintegradoraList();
            List<AsignacionAutorintegradora> asignacionAutorintegradoraListNew = tipoAutor.getAsignacionAutorintegradoraList();
            List<AsignacionAutorbanco> asignacionAutorbancoListOld = persistentTipoAutor.getAsignacionAutorbancoList();
            List<AsignacionAutorbanco> asignacionAutorbancoListNew = tipoAutor.getAsignacionAutorbancoList();
            List<AsignacionAutorintegradora> attachedAsignacionAutorintegradoraListNew = new ArrayList<AsignacionAutorintegradora>();
            for (AsignacionAutorintegradora asignacionAutorintegradoraListNewAsignacionAutorintegradoraToAttach : asignacionAutorintegradoraListNew) {
                asignacionAutorintegradoraListNewAsignacionAutorintegradoraToAttach = em.getReference(asignacionAutorintegradoraListNewAsignacionAutorintegradoraToAttach.getClass(), asignacionAutorintegradoraListNewAsignacionAutorintegradoraToAttach.getIdAsignacionautorintegradora());
                attachedAsignacionAutorintegradoraListNew.add(asignacionAutorintegradoraListNewAsignacionAutorintegradoraToAttach);
            }
            asignacionAutorintegradoraListNew = attachedAsignacionAutorintegradoraListNew;
            tipoAutor.setAsignacionAutorintegradoraList(asignacionAutorintegradoraListNew);
            List<AsignacionAutorbanco> attachedAsignacionAutorbancoListNew = new ArrayList<AsignacionAutorbanco>();
            for (AsignacionAutorbanco asignacionAutorbancoListNewAsignacionAutorbancoToAttach : asignacionAutorbancoListNew) {
                asignacionAutorbancoListNewAsignacionAutorbancoToAttach = em.getReference(asignacionAutorbancoListNewAsignacionAutorbancoToAttach.getClass(), asignacionAutorbancoListNewAsignacionAutorbancoToAttach.getIdAsignacionautorbanco());
                attachedAsignacionAutorbancoListNew.add(asignacionAutorbancoListNewAsignacionAutorbancoToAttach);
            }
            asignacionAutorbancoListNew = attachedAsignacionAutorbancoListNew;
            tipoAutor.setAsignacionAutorbancoList(asignacionAutorbancoListNew);
            tipoAutor = em.merge(tipoAutor);
            for (AsignacionAutorintegradora asignacionAutorintegradoraListOldAsignacionAutorintegradora : asignacionAutorintegradoraListOld) {
                if (!asignacionAutorintegradoraListNew.contains(asignacionAutorintegradoraListOldAsignacionAutorintegradora)) {
                    asignacionAutorintegradoraListOldAsignacionAutorintegradora.setIdTipoautor(null);
                    asignacionAutorintegradoraListOldAsignacionAutorintegradora = em.merge(asignacionAutorintegradoraListOldAsignacionAutorintegradora);
                }
            }
            for (AsignacionAutorintegradora asignacionAutorintegradoraListNewAsignacionAutorintegradora : asignacionAutorintegradoraListNew) {
                if (!asignacionAutorintegradoraListOld.contains(asignacionAutorintegradoraListNewAsignacionAutorintegradora)) {
                    TipoAutor oldIdTipoautorOfAsignacionAutorintegradoraListNewAsignacionAutorintegradora = asignacionAutorintegradoraListNewAsignacionAutorintegradora.getIdTipoautor();
                    asignacionAutorintegradoraListNewAsignacionAutorintegradora.setIdTipoautor(tipoAutor);
                    asignacionAutorintegradoraListNewAsignacionAutorintegradora = em.merge(asignacionAutorintegradoraListNewAsignacionAutorintegradora);
                    if (oldIdTipoautorOfAsignacionAutorintegradoraListNewAsignacionAutorintegradora != null && !oldIdTipoautorOfAsignacionAutorintegradoraListNewAsignacionAutorintegradora.equals(tipoAutor)) {
                        oldIdTipoautorOfAsignacionAutorintegradoraListNewAsignacionAutorintegradora.getAsignacionAutorintegradoraList().remove(asignacionAutorintegradoraListNewAsignacionAutorintegradora);
                        oldIdTipoautorOfAsignacionAutorintegradoraListNewAsignacionAutorintegradora = em.merge(oldIdTipoautorOfAsignacionAutorintegradoraListNewAsignacionAutorintegradora);
                    }
                }
            }
            for (AsignacionAutorbanco asignacionAutorbancoListOldAsignacionAutorbanco : asignacionAutorbancoListOld) {
                if (!asignacionAutorbancoListNew.contains(asignacionAutorbancoListOldAsignacionAutorbanco)) {
                    asignacionAutorbancoListOldAsignacionAutorbanco.setIdTipoautor(null);
                    asignacionAutorbancoListOldAsignacionAutorbanco = em.merge(asignacionAutorbancoListOldAsignacionAutorbanco);
                }
            }
            for (AsignacionAutorbanco asignacionAutorbancoListNewAsignacionAutorbanco : asignacionAutorbancoListNew) {
                if (!asignacionAutorbancoListOld.contains(asignacionAutorbancoListNewAsignacionAutorbanco)) {
                    TipoAutor oldIdTipoautorOfAsignacionAutorbancoListNewAsignacionAutorbanco = asignacionAutorbancoListNewAsignacionAutorbanco.getIdTipoautor();
                    asignacionAutorbancoListNewAsignacionAutorbanco.setIdTipoautor(tipoAutor);
                    asignacionAutorbancoListNewAsignacionAutorbanco = em.merge(asignacionAutorbancoListNewAsignacionAutorbanco);
                    if (oldIdTipoautorOfAsignacionAutorbancoListNewAsignacionAutorbanco != null && !oldIdTipoautorOfAsignacionAutorbancoListNewAsignacionAutorbanco.equals(tipoAutor)) {
                        oldIdTipoautorOfAsignacionAutorbancoListNewAsignacionAutorbanco.getAsignacionAutorbancoList().remove(asignacionAutorbancoListNewAsignacionAutorbanco);
                        oldIdTipoautorOfAsignacionAutorbancoListNewAsignacionAutorbanco = em.merge(oldIdTipoautorOfAsignacionAutorbancoListNewAsignacionAutorbanco);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoAutor.getIdTipoautor();
                if (findTipoAutor(id) == null) {
                    throw new NonexistentEntityException("The tipoAutor with id " + id + " no longer exists.");
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
            TipoAutor tipoAutor;
            try {
                tipoAutor = em.getReference(TipoAutor.class, id);
                tipoAutor.getIdTipoautor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoAutor with id " + id + " no longer exists.", enfe);
            }
            List<AsignacionAutorintegradora> asignacionAutorintegradoraList = tipoAutor.getAsignacionAutorintegradoraList();
            for (AsignacionAutorintegradora asignacionAutorintegradoraListAsignacionAutorintegradora : asignacionAutorintegradoraList) {
                asignacionAutorintegradoraListAsignacionAutorintegradora.setIdTipoautor(null);
                asignacionAutorintegradoraListAsignacionAutorintegradora = em.merge(asignacionAutorintegradoraListAsignacionAutorintegradora);
            }
            List<AsignacionAutorbanco> asignacionAutorbancoList = tipoAutor.getAsignacionAutorbancoList();
            for (AsignacionAutorbanco asignacionAutorbancoListAsignacionAutorbanco : asignacionAutorbancoList) {
                asignacionAutorbancoListAsignacionAutorbanco.setIdTipoautor(null);
                asignacionAutorbancoListAsignacionAutorbanco = em.merge(asignacionAutorbancoListAsignacionAutorbanco);
            }
            em.remove(tipoAutor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoAutor> findTipoAutorEntities() {
        return findTipoAutorEntities(true, -1, -1);
    }

    public List<TipoAutor> findTipoAutorEntities(int maxResults, int firstResult) {
        return findTipoAutorEntities(false, maxResults, firstResult);
    }

    private List<TipoAutor> findTipoAutorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoAutor as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoAutor findTipoAutor(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoAutor.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoAutorCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoAutor as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
