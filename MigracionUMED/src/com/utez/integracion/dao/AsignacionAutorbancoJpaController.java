/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.AsignacionAutorbanco;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoAutor;
import com.utez.integracion.entity.BancoReactivo;
import com.utez.secretariaAcademica.entity.Asesor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionAutorbancoJpaController implements Serializable {

    public AsignacionAutorbancoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionAutorbanco asignacionAutorbanco) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoAutor idTipoautor = asignacionAutorbanco.getIdTipoautor();
            if (idTipoautor != null) {
                idTipoautor = em.getReference(idTipoautor.getClass(), idTipoautor.getIdTipoautor());
                asignacionAutorbanco.setIdTipoautor(idTipoautor);
            }
            BancoReactivo idBancoreactivo = asignacionAutorbanco.getIdBancoreactivo();
            if (idBancoreactivo != null) {
                idBancoreactivo = em.getReference(idBancoreactivo.getClass(), idBancoreactivo.getIdBancoreactivo());
                asignacionAutorbanco.setIdBancoreactivo(idBancoreactivo);
            }
            Asesor idAutor = asignacionAutorbanco.getIdAutor();
            if (idAutor != null) {
                idAutor = em.getReference(idAutor.getClass(), idAutor.getIdasesor());
                asignacionAutorbanco.setIdAutor(idAutor);
            }
            em.persist(asignacionAutorbanco);
            if (idTipoautor != null) {
                idTipoautor.getAsignacionAutorbancoList().add(asignacionAutorbanco);
                idTipoautor = em.merge(idTipoautor);
            }
            if (idBancoreactivo != null) {
                idBancoreactivo.getAsignacionAutorbancoList().add(asignacionAutorbanco);
                idBancoreactivo = em.merge(idBancoreactivo);
            }
            if (idAutor != null) {
                idAutor.getAsignacionAutorbancoList().add(asignacionAutorbanco);
                idAutor = em.merge(idAutor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionAutorbanco asignacionAutorbanco) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionAutorbanco persistentAsignacionAutorbanco = em.find(AsignacionAutorbanco.class, asignacionAutorbanco.getIdAsignacionautorbanco());
            TipoAutor idTipoautorOld = persistentAsignacionAutorbanco.getIdTipoautor();
            TipoAutor idTipoautorNew = asignacionAutorbanco.getIdTipoautor();
            BancoReactivo idBancoreactivoOld = persistentAsignacionAutorbanco.getIdBancoreactivo();
            BancoReactivo idBancoreactivoNew = asignacionAutorbanco.getIdBancoreactivo();
            Asesor idAutorOld = persistentAsignacionAutorbanco.getIdAutor();
            Asesor idAutorNew = asignacionAutorbanco.getIdAutor();
            if (idTipoautorNew != null) {
                idTipoautorNew = em.getReference(idTipoautorNew.getClass(), idTipoautorNew.getIdTipoautor());
                asignacionAutorbanco.setIdTipoautor(idTipoautorNew);
            }
            if (idBancoreactivoNew != null) {
                idBancoreactivoNew = em.getReference(idBancoreactivoNew.getClass(), idBancoreactivoNew.getIdBancoreactivo());
                asignacionAutorbanco.setIdBancoreactivo(idBancoreactivoNew);
            }
            if (idAutorNew != null) {
                idAutorNew = em.getReference(idAutorNew.getClass(), idAutorNew.getIdasesor());
                asignacionAutorbanco.setIdAutor(idAutorNew);
            }
            asignacionAutorbanco = em.merge(asignacionAutorbanco);
            if (idTipoautorOld != null && !idTipoautorOld.equals(idTipoautorNew)) {
                idTipoautorOld.getAsignacionAutorbancoList().remove(asignacionAutorbanco);
                idTipoautorOld = em.merge(idTipoautorOld);
            }
            if (idTipoautorNew != null && !idTipoautorNew.equals(idTipoautorOld)) {
                idTipoautorNew.getAsignacionAutorbancoList().add(asignacionAutorbanco);
                idTipoautorNew = em.merge(idTipoautorNew);
            }
            if (idBancoreactivoOld != null && !idBancoreactivoOld.equals(idBancoreactivoNew)) {
                idBancoreactivoOld.getAsignacionAutorbancoList().remove(asignacionAutorbanco);
                idBancoreactivoOld = em.merge(idBancoreactivoOld);
            }
            if (idBancoreactivoNew != null && !idBancoreactivoNew.equals(idBancoreactivoOld)) {
                idBancoreactivoNew.getAsignacionAutorbancoList().add(asignacionAutorbanco);
                idBancoreactivoNew = em.merge(idBancoreactivoNew);
            }
            if (idAutorOld != null && !idAutorOld.equals(idAutorNew)) {
                idAutorOld.getAsignacionAutorbancoList().remove(asignacionAutorbanco);
                idAutorOld = em.merge(idAutorOld);
            }
            if (idAutorNew != null && !idAutorNew.equals(idAutorOld)) {
                idAutorNew.getAsignacionAutorbancoList().add(asignacionAutorbanco);
                idAutorNew = em.merge(idAutorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionAutorbanco.getIdAsignacionautorbanco();
                if (findAsignacionAutorbanco(id) == null) {
                    throw new NonexistentEntityException("The asignacionAutorbanco with id " + id + " no longer exists.");
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
            AsignacionAutorbanco asignacionAutorbanco;
            try {
                asignacionAutorbanco = em.getReference(AsignacionAutorbanco.class, id);
                asignacionAutorbanco.getIdAsignacionautorbanco();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionAutorbanco with id " + id + " no longer exists.", enfe);
            }
            TipoAutor idTipoautor = asignacionAutorbanco.getIdTipoautor();
            if (idTipoautor != null) {
                idTipoautor.getAsignacionAutorbancoList().remove(asignacionAutorbanco);
                idTipoautor = em.merge(idTipoautor);
            }
            BancoReactivo idBancoreactivo = asignacionAutorbanco.getIdBancoreactivo();
            if (idBancoreactivo != null) {
                idBancoreactivo.getAsignacionAutorbancoList().remove(asignacionAutorbanco);
                idBancoreactivo = em.merge(idBancoreactivo);
            }
            Asesor idAutor = asignacionAutorbanco.getIdAutor();
            if (idAutor != null) {
                idAutor.getAsignacionAutorbancoList().remove(asignacionAutorbanco);
                idAutor = em.merge(idAutor);
            }
            em.remove(asignacionAutorbanco);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionAutorbanco> findAsignacionAutorbancoEntities() {
        return findAsignacionAutorbancoEntities(true, -1, -1);
    }

    public List<AsignacionAutorbanco> findAsignacionAutorbancoEntities(int maxResults, int firstResult) {
        return findAsignacionAutorbancoEntities(false, maxResults, firstResult);
    }

    private List<AsignacionAutorbanco> findAsignacionAutorbancoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionAutorbanco as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionAutorbanco findAsignacionAutorbanco(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionAutorbanco.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionAutorbancoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionAutorbanco as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
