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
import com.utez.integracion.entity.TipoReactivo;
import com.utez.integracion.entity.TipoAmbito;
import com.utez.integracion.entity.BancoReactivo;
import com.utez.integracion.entity.ReactivoExamenimpreso;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.ContenidoReactivo;
import com.utez.integracion.entity.Reactivo;
import com.utez.integracion.entity.ReactivoImagen;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ReactivoJpaController implements Serializable {

    public ReactivoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reactivo reactivo) {
        if (reactivo.getReactivoExamenimpresoList() == null) {
            reactivo.setReactivoExamenimpresoList(new ArrayList<ReactivoExamenimpreso>());
        }
        if (reactivo.getContenidoReactivoList() == null) {
            reactivo.setContenidoReactivoList(new ArrayList<ContenidoReactivo>());
        }
        if (reactivo.getReactivoImagenList() == null) {
            reactivo.setReactivoImagenList(new ArrayList<ReactivoImagen>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoReactivo idTiporeactivo = reactivo.getIdTiporeactivo();
            if (idTiporeactivo != null) {
                idTiporeactivo = em.getReference(idTiporeactivo.getClass(), idTiporeactivo.getIdTiporeactivo());
                reactivo.setIdTiporeactivo(idTiporeactivo);
            }
            TipoAmbito idTipoambito = reactivo.getIdTipoambito();
            if (idTipoambito != null) {
                idTipoambito = em.getReference(idTipoambito.getClass(), idTipoambito.getIdTipoambito());
                reactivo.setIdTipoambito(idTipoambito);
            }
            BancoReactivo idBancoreactivo = reactivo.getIdBancoreactivo();
            if (idBancoreactivo != null) {
                idBancoreactivo = em.getReference(idBancoreactivo.getClass(), idBancoreactivo.getIdBancoreactivo());
                reactivo.setIdBancoreactivo(idBancoreactivo);
            }
            List<ReactivoExamenimpreso> attachedReactivoExamenimpresoList = new ArrayList<ReactivoExamenimpreso>();
            for (ReactivoExamenimpreso reactivoExamenimpresoListReactivoExamenimpresoToAttach : reactivo.getReactivoExamenimpresoList()) {
                reactivoExamenimpresoListReactivoExamenimpresoToAttach = em.getReference(reactivoExamenimpresoListReactivoExamenimpresoToAttach.getClass(), reactivoExamenimpresoListReactivoExamenimpresoToAttach.getReactivoExamenimpresoPK());
                attachedReactivoExamenimpresoList.add(reactivoExamenimpresoListReactivoExamenimpresoToAttach);
            }
            reactivo.setReactivoExamenimpresoList(attachedReactivoExamenimpresoList);
            List<ContenidoReactivo> attachedContenidoReactivoList = new ArrayList<ContenidoReactivo>();
            for (ContenidoReactivo contenidoReactivoListContenidoReactivoToAttach : reactivo.getContenidoReactivoList()) {
                contenidoReactivoListContenidoReactivoToAttach = em.getReference(contenidoReactivoListContenidoReactivoToAttach.getClass(), contenidoReactivoListContenidoReactivoToAttach.getIdContenidoreactivo());
                attachedContenidoReactivoList.add(contenidoReactivoListContenidoReactivoToAttach);
            }
            reactivo.setContenidoReactivoList(attachedContenidoReactivoList);
            List<ReactivoImagen> attachedReactivoImagenList = new ArrayList<ReactivoImagen>();
            for (ReactivoImagen reactivoImagenListReactivoImagenToAttach : reactivo.getReactivoImagenList()) {
                reactivoImagenListReactivoImagenToAttach = em.getReference(reactivoImagenListReactivoImagenToAttach.getClass(), reactivoImagenListReactivoImagenToAttach.getIdReactivoimagen());
                attachedReactivoImagenList.add(reactivoImagenListReactivoImagenToAttach);
            }
            reactivo.setReactivoImagenList(attachedReactivoImagenList);
            em.persist(reactivo);
            if (idTiporeactivo != null) {
                idTiporeactivo.getReactivoList().add(reactivo);
                idTiporeactivo = em.merge(idTiporeactivo);
            }
            if (idTipoambito != null) {
                idTipoambito.getReactivoList().add(reactivo);
                idTipoambito = em.merge(idTipoambito);
            }
            if (idBancoreactivo != null) {
                idBancoreactivo.getReactivoList().add(reactivo);
                idBancoreactivo = em.merge(idBancoreactivo);
            }
            for (ReactivoExamenimpreso reactivoExamenimpresoListReactivoExamenimpreso : reactivo.getReactivoExamenimpresoList()) {
                Reactivo oldReactivoOfReactivoExamenimpresoListReactivoExamenimpreso = reactivoExamenimpresoListReactivoExamenimpreso.getReactivo();
                reactivoExamenimpresoListReactivoExamenimpreso.setReactivo(reactivo);
                reactivoExamenimpresoListReactivoExamenimpreso = em.merge(reactivoExamenimpresoListReactivoExamenimpreso);
                if (oldReactivoOfReactivoExamenimpresoListReactivoExamenimpreso != null) {
                    oldReactivoOfReactivoExamenimpresoListReactivoExamenimpreso.getReactivoExamenimpresoList().remove(reactivoExamenimpresoListReactivoExamenimpreso);
                    oldReactivoOfReactivoExamenimpresoListReactivoExamenimpreso = em.merge(oldReactivoOfReactivoExamenimpresoListReactivoExamenimpreso);
                }
            }
            for (ContenidoReactivo contenidoReactivoListContenidoReactivo : reactivo.getContenidoReactivoList()) {
                Reactivo oldIdReactivoOfContenidoReactivoListContenidoReactivo = contenidoReactivoListContenidoReactivo.getIdReactivo();
                contenidoReactivoListContenidoReactivo.setIdReactivo(reactivo);
                contenidoReactivoListContenidoReactivo = em.merge(contenidoReactivoListContenidoReactivo);
                if (oldIdReactivoOfContenidoReactivoListContenidoReactivo != null) {
                    oldIdReactivoOfContenidoReactivoListContenidoReactivo.getContenidoReactivoList().remove(contenidoReactivoListContenidoReactivo);
                    oldIdReactivoOfContenidoReactivoListContenidoReactivo = em.merge(oldIdReactivoOfContenidoReactivoListContenidoReactivo);
                }
            }
            for (ReactivoImagen reactivoImagenListReactivoImagen : reactivo.getReactivoImagenList()) {
                Reactivo oldIdReactivoOfReactivoImagenListReactivoImagen = reactivoImagenListReactivoImagen.getIdReactivo();
                reactivoImagenListReactivoImagen.setIdReactivo(reactivo);
                reactivoImagenListReactivoImagen = em.merge(reactivoImagenListReactivoImagen);
                if (oldIdReactivoOfReactivoImagenListReactivoImagen != null) {
                    oldIdReactivoOfReactivoImagenListReactivoImagen.getReactivoImagenList().remove(reactivoImagenListReactivoImagen);
                    oldIdReactivoOfReactivoImagenListReactivoImagen = em.merge(oldIdReactivoOfReactivoImagenListReactivoImagen);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reactivo reactivo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reactivo persistentReactivo = em.find(Reactivo.class, reactivo.getIdReactivo());
            TipoReactivo idTiporeactivoOld = persistentReactivo.getIdTiporeactivo();
            TipoReactivo idTiporeactivoNew = reactivo.getIdTiporeactivo();
            TipoAmbito idTipoambitoOld = persistentReactivo.getIdTipoambito();
            TipoAmbito idTipoambitoNew = reactivo.getIdTipoambito();
            BancoReactivo idBancoreactivoOld = persistentReactivo.getIdBancoreactivo();
            BancoReactivo idBancoreactivoNew = reactivo.getIdBancoreactivo();
            List<ReactivoExamenimpreso> reactivoExamenimpresoListOld = persistentReactivo.getReactivoExamenimpresoList();
            List<ReactivoExamenimpreso> reactivoExamenimpresoListNew = reactivo.getReactivoExamenimpresoList();
            List<ContenidoReactivo> contenidoReactivoListOld = persistentReactivo.getContenidoReactivoList();
            List<ContenidoReactivo> contenidoReactivoListNew = reactivo.getContenidoReactivoList();
            List<ReactivoImagen> reactivoImagenListOld = persistentReactivo.getReactivoImagenList();
            List<ReactivoImagen> reactivoImagenListNew = reactivo.getReactivoImagenList();
            List<String> illegalOrphanMessages = null;
            for (ReactivoExamenimpreso reactivoExamenimpresoListOldReactivoExamenimpreso : reactivoExamenimpresoListOld) {
                if (!reactivoExamenimpresoListNew.contains(reactivoExamenimpresoListOldReactivoExamenimpreso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ReactivoExamenimpreso " + reactivoExamenimpresoListOldReactivoExamenimpreso + " since its reactivo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTiporeactivoNew != null) {
                idTiporeactivoNew = em.getReference(idTiporeactivoNew.getClass(), idTiporeactivoNew.getIdTiporeactivo());
                reactivo.setIdTiporeactivo(idTiporeactivoNew);
            }
            if (idTipoambitoNew != null) {
                idTipoambitoNew = em.getReference(idTipoambitoNew.getClass(), idTipoambitoNew.getIdTipoambito());
                reactivo.setIdTipoambito(idTipoambitoNew);
            }
            if (idBancoreactivoNew != null) {
                idBancoreactivoNew = em.getReference(idBancoreactivoNew.getClass(), idBancoreactivoNew.getIdBancoreactivo());
                reactivo.setIdBancoreactivo(idBancoreactivoNew);
            }
            List<ReactivoExamenimpreso> attachedReactivoExamenimpresoListNew = new ArrayList<ReactivoExamenimpreso>();
            for (ReactivoExamenimpreso reactivoExamenimpresoListNewReactivoExamenimpresoToAttach : reactivoExamenimpresoListNew) {
                reactivoExamenimpresoListNewReactivoExamenimpresoToAttach = em.getReference(reactivoExamenimpresoListNewReactivoExamenimpresoToAttach.getClass(), reactivoExamenimpresoListNewReactivoExamenimpresoToAttach.getReactivoExamenimpresoPK());
                attachedReactivoExamenimpresoListNew.add(reactivoExamenimpresoListNewReactivoExamenimpresoToAttach);
            }
            reactivoExamenimpresoListNew = attachedReactivoExamenimpresoListNew;
            reactivo.setReactivoExamenimpresoList(reactivoExamenimpresoListNew);
            List<ContenidoReactivo> attachedContenidoReactivoListNew = new ArrayList<ContenidoReactivo>();
            for (ContenidoReactivo contenidoReactivoListNewContenidoReactivoToAttach : contenidoReactivoListNew) {
                contenidoReactivoListNewContenidoReactivoToAttach = em.getReference(contenidoReactivoListNewContenidoReactivoToAttach.getClass(), contenidoReactivoListNewContenidoReactivoToAttach.getIdContenidoreactivo());
                attachedContenidoReactivoListNew.add(contenidoReactivoListNewContenidoReactivoToAttach);
            }
            contenidoReactivoListNew = attachedContenidoReactivoListNew;
            reactivo.setContenidoReactivoList(contenidoReactivoListNew);
            List<ReactivoImagen> attachedReactivoImagenListNew = new ArrayList<ReactivoImagen>();
            for (ReactivoImagen reactivoImagenListNewReactivoImagenToAttach : reactivoImagenListNew) {
                reactivoImagenListNewReactivoImagenToAttach = em.getReference(reactivoImagenListNewReactivoImagenToAttach.getClass(), reactivoImagenListNewReactivoImagenToAttach.getIdReactivoimagen());
                attachedReactivoImagenListNew.add(reactivoImagenListNewReactivoImagenToAttach);
            }
            reactivoImagenListNew = attachedReactivoImagenListNew;
            reactivo.setReactivoImagenList(reactivoImagenListNew);
            reactivo = em.merge(reactivo);
            if (idTiporeactivoOld != null && !idTiporeactivoOld.equals(idTiporeactivoNew)) {
                idTiporeactivoOld.getReactivoList().remove(reactivo);
                idTiporeactivoOld = em.merge(idTiporeactivoOld);
            }
            if (idTiporeactivoNew != null && !idTiporeactivoNew.equals(idTiporeactivoOld)) {
                idTiporeactivoNew.getReactivoList().add(reactivo);
                idTiporeactivoNew = em.merge(idTiporeactivoNew);
            }
            if (idTipoambitoOld != null && !idTipoambitoOld.equals(idTipoambitoNew)) {
                idTipoambitoOld.getReactivoList().remove(reactivo);
                idTipoambitoOld = em.merge(idTipoambitoOld);
            }
            if (idTipoambitoNew != null && !idTipoambitoNew.equals(idTipoambitoOld)) {
                idTipoambitoNew.getReactivoList().add(reactivo);
                idTipoambitoNew = em.merge(idTipoambitoNew);
            }
            if (idBancoreactivoOld != null && !idBancoreactivoOld.equals(idBancoreactivoNew)) {
                idBancoreactivoOld.getReactivoList().remove(reactivo);
                idBancoreactivoOld = em.merge(idBancoreactivoOld);
            }
            if (idBancoreactivoNew != null && !idBancoreactivoNew.equals(idBancoreactivoOld)) {
                idBancoreactivoNew.getReactivoList().add(reactivo);
                idBancoreactivoNew = em.merge(idBancoreactivoNew);
            }
            for (ReactivoExamenimpreso reactivoExamenimpresoListNewReactivoExamenimpreso : reactivoExamenimpresoListNew) {
                if (!reactivoExamenimpresoListOld.contains(reactivoExamenimpresoListNewReactivoExamenimpreso)) {
                    Reactivo oldReactivoOfReactivoExamenimpresoListNewReactivoExamenimpreso = reactivoExamenimpresoListNewReactivoExamenimpreso.getReactivo();
                    reactivoExamenimpresoListNewReactivoExamenimpreso.setReactivo(reactivo);
                    reactivoExamenimpresoListNewReactivoExamenimpreso = em.merge(reactivoExamenimpresoListNewReactivoExamenimpreso);
                    if (oldReactivoOfReactivoExamenimpresoListNewReactivoExamenimpreso != null && !oldReactivoOfReactivoExamenimpresoListNewReactivoExamenimpreso.equals(reactivo)) {
                        oldReactivoOfReactivoExamenimpresoListNewReactivoExamenimpreso.getReactivoExamenimpresoList().remove(reactivoExamenimpresoListNewReactivoExamenimpreso);
                        oldReactivoOfReactivoExamenimpresoListNewReactivoExamenimpreso = em.merge(oldReactivoOfReactivoExamenimpresoListNewReactivoExamenimpreso);
                    }
                }
            }
            for (ContenidoReactivo contenidoReactivoListOldContenidoReactivo : contenidoReactivoListOld) {
                if (!contenidoReactivoListNew.contains(contenidoReactivoListOldContenidoReactivo)) {
                    contenidoReactivoListOldContenidoReactivo.setIdReactivo(null);
                    contenidoReactivoListOldContenidoReactivo = em.merge(contenidoReactivoListOldContenidoReactivo);
                }
            }
            for (ContenidoReactivo contenidoReactivoListNewContenidoReactivo : contenidoReactivoListNew) {
                if (!contenidoReactivoListOld.contains(contenidoReactivoListNewContenidoReactivo)) {
                    Reactivo oldIdReactivoOfContenidoReactivoListNewContenidoReactivo = contenidoReactivoListNewContenidoReactivo.getIdReactivo();
                    contenidoReactivoListNewContenidoReactivo.setIdReactivo(reactivo);
                    contenidoReactivoListNewContenidoReactivo = em.merge(contenidoReactivoListNewContenidoReactivo);
                    if (oldIdReactivoOfContenidoReactivoListNewContenidoReactivo != null && !oldIdReactivoOfContenidoReactivoListNewContenidoReactivo.equals(reactivo)) {
                        oldIdReactivoOfContenidoReactivoListNewContenidoReactivo.getContenidoReactivoList().remove(contenidoReactivoListNewContenidoReactivo);
                        oldIdReactivoOfContenidoReactivoListNewContenidoReactivo = em.merge(oldIdReactivoOfContenidoReactivoListNewContenidoReactivo);
                    }
                }
            }
            for (ReactivoImagen reactivoImagenListOldReactivoImagen : reactivoImagenListOld) {
                if (!reactivoImagenListNew.contains(reactivoImagenListOldReactivoImagen)) {
                    reactivoImagenListOldReactivoImagen.setIdReactivo(null);
                    reactivoImagenListOldReactivoImagen = em.merge(reactivoImagenListOldReactivoImagen);
                }
            }
            for (ReactivoImagen reactivoImagenListNewReactivoImagen : reactivoImagenListNew) {
                if (!reactivoImagenListOld.contains(reactivoImagenListNewReactivoImagen)) {
                    Reactivo oldIdReactivoOfReactivoImagenListNewReactivoImagen = reactivoImagenListNewReactivoImagen.getIdReactivo();
                    reactivoImagenListNewReactivoImagen.setIdReactivo(reactivo);
                    reactivoImagenListNewReactivoImagen = em.merge(reactivoImagenListNewReactivoImagen);
                    if (oldIdReactivoOfReactivoImagenListNewReactivoImagen != null && !oldIdReactivoOfReactivoImagenListNewReactivoImagen.equals(reactivo)) {
                        oldIdReactivoOfReactivoImagenListNewReactivoImagen.getReactivoImagenList().remove(reactivoImagenListNewReactivoImagen);
                        oldIdReactivoOfReactivoImagenListNewReactivoImagen = em.merge(oldIdReactivoOfReactivoImagenListNewReactivoImagen);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = reactivo.getIdReactivo();
                if (findReactivo(id) == null) {
                    throw new NonexistentEntityException("The reactivo with id " + id + " no longer exists.");
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
            Reactivo reactivo;
            try {
                reactivo = em.getReference(Reactivo.class, id);
                reactivo.getIdReactivo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reactivo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ReactivoExamenimpreso> reactivoExamenimpresoListOrphanCheck = reactivo.getReactivoExamenimpresoList();
            for (ReactivoExamenimpreso reactivoExamenimpresoListOrphanCheckReactivoExamenimpreso : reactivoExamenimpresoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reactivo (" + reactivo + ") cannot be destroyed since the ReactivoExamenimpreso " + reactivoExamenimpresoListOrphanCheckReactivoExamenimpreso + " in its reactivoExamenimpresoList field has a non-nullable reactivo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoReactivo idTiporeactivo = reactivo.getIdTiporeactivo();
            if (idTiporeactivo != null) {
                idTiporeactivo.getReactivoList().remove(reactivo);
                idTiporeactivo = em.merge(idTiporeactivo);
            }
            TipoAmbito idTipoambito = reactivo.getIdTipoambito();
            if (idTipoambito != null) {
                idTipoambito.getReactivoList().remove(reactivo);
                idTipoambito = em.merge(idTipoambito);
            }
            BancoReactivo idBancoreactivo = reactivo.getIdBancoreactivo();
            if (idBancoreactivo != null) {
                idBancoreactivo.getReactivoList().remove(reactivo);
                idBancoreactivo = em.merge(idBancoreactivo);
            }
            List<ContenidoReactivo> contenidoReactivoList = reactivo.getContenidoReactivoList();
            for (ContenidoReactivo contenidoReactivoListContenidoReactivo : contenidoReactivoList) {
                contenidoReactivoListContenidoReactivo.setIdReactivo(null);
                contenidoReactivoListContenidoReactivo = em.merge(contenidoReactivoListContenidoReactivo);
            }
            List<ReactivoImagen> reactivoImagenList = reactivo.getReactivoImagenList();
            for (ReactivoImagen reactivoImagenListReactivoImagen : reactivoImagenList) {
                reactivoImagenListReactivoImagen.setIdReactivo(null);
                reactivoImagenListReactivoImagen = em.merge(reactivoImagenListReactivoImagen);
            }
            em.remove(reactivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reactivo> findReactivoEntities() {
        return findReactivoEntities(true, -1, -1);
    }

    public List<Reactivo> findReactivoEntities(int maxResults, int firstResult) {
        return findReactivoEntities(false, maxResults, firstResult);
    }

    private List<Reactivo> findReactivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Reactivo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Reactivo findReactivo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reactivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getReactivoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Reactivo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
