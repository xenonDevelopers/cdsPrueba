/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.ControlDocumento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.RecursoHumano;
import com.utez.integracion.entity.Documento;
import com.utez.secretariaAcademica.entity.Alumno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ControlDocumentoJpaController implements Serializable {

    public ControlDocumentoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ControlDocumento controlDocumento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RecursoHumano idRecursohumano = controlDocumento.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano = em.getReference(idRecursohumano.getClass(), idRecursohumano.getIdRecursohumano());
                controlDocumento.setIdRecursohumano(idRecursohumano);
            }
            Documento idDocumento = controlDocumento.getIdDocumento();
            if (idDocumento != null) {
                idDocumento = em.getReference(idDocumento.getClass(), idDocumento.getIdDocumento());
                controlDocumento.setIdDocumento(idDocumento);
            }
            Alumno matricula = controlDocumento.getMatricula();
            if (matricula != null) {
                matricula = em.getReference(matricula.getClass(), matricula.getMatricula());
                controlDocumento.setMatricula(matricula);
            }
            em.persist(controlDocumento);
            if (idRecursohumano != null) {
                idRecursohumano.getControlDocumentoList().add(controlDocumento);
                idRecursohumano = em.merge(idRecursohumano);
            }
            if (idDocumento != null) {
                idDocumento.getControlDocumentoList().add(controlDocumento);
                idDocumento = em.merge(idDocumento);
            }
            if (matricula != null) {
                matricula.getControlDocumentoList().add(controlDocumento);
                matricula = em.merge(matricula);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ControlDocumento controlDocumento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ControlDocumento persistentControlDocumento = em.find(ControlDocumento.class, controlDocumento.getIdControldocumento());
            RecursoHumano idRecursohumanoOld = persistentControlDocumento.getIdRecursohumano();
            RecursoHumano idRecursohumanoNew = controlDocumento.getIdRecursohumano();
            Documento idDocumentoOld = persistentControlDocumento.getIdDocumento();
            Documento idDocumentoNew = controlDocumento.getIdDocumento();
            Alumno matriculaOld = persistentControlDocumento.getMatricula();
            Alumno matriculaNew = controlDocumento.getMatricula();
            if (idRecursohumanoNew != null) {
                idRecursohumanoNew = em.getReference(idRecursohumanoNew.getClass(), idRecursohumanoNew.getIdRecursohumano());
                controlDocumento.setIdRecursohumano(idRecursohumanoNew);
            }
            if (idDocumentoNew != null) {
                idDocumentoNew = em.getReference(idDocumentoNew.getClass(), idDocumentoNew.getIdDocumento());
                controlDocumento.setIdDocumento(idDocumentoNew);
            }
            if (matriculaNew != null) {
                matriculaNew = em.getReference(matriculaNew.getClass(), matriculaNew.getMatricula());
                controlDocumento.setMatricula(matriculaNew);
            }
            controlDocumento = em.merge(controlDocumento);
            if (idRecursohumanoOld != null && !idRecursohumanoOld.equals(idRecursohumanoNew)) {
                idRecursohumanoOld.getControlDocumentoList().remove(controlDocumento);
                idRecursohumanoOld = em.merge(idRecursohumanoOld);
            }
            if (idRecursohumanoNew != null && !idRecursohumanoNew.equals(idRecursohumanoOld)) {
                idRecursohumanoNew.getControlDocumentoList().add(controlDocumento);
                idRecursohumanoNew = em.merge(idRecursohumanoNew);
            }
            if (idDocumentoOld != null && !idDocumentoOld.equals(idDocumentoNew)) {
                idDocumentoOld.getControlDocumentoList().remove(controlDocumento);
                idDocumentoOld = em.merge(idDocumentoOld);
            }
            if (idDocumentoNew != null && !idDocumentoNew.equals(idDocumentoOld)) {
                idDocumentoNew.getControlDocumentoList().add(controlDocumento);
                idDocumentoNew = em.merge(idDocumentoNew);
            }
            if (matriculaOld != null && !matriculaOld.equals(matriculaNew)) {
                matriculaOld.getControlDocumentoList().remove(controlDocumento);
                matriculaOld = em.merge(matriculaOld);
            }
            if (matriculaNew != null && !matriculaNew.equals(matriculaOld)) {
                matriculaNew.getControlDocumentoList().add(controlDocumento);
                matriculaNew = em.merge(matriculaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = controlDocumento.getIdControldocumento();
                if (findControlDocumento(id) == null) {
                    throw new NonexistentEntityException("The controlDocumento with id " + id + " no longer exists.");
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
            ControlDocumento controlDocumento;
            try {
                controlDocumento = em.getReference(ControlDocumento.class, id);
                controlDocumento.getIdControldocumento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The controlDocumento with id " + id + " no longer exists.", enfe);
            }
            RecursoHumano idRecursohumano = controlDocumento.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano.getControlDocumentoList().remove(controlDocumento);
                idRecursohumano = em.merge(idRecursohumano);
            }
            Documento idDocumento = controlDocumento.getIdDocumento();
            if (idDocumento != null) {
                idDocumento.getControlDocumentoList().remove(controlDocumento);
                idDocumento = em.merge(idDocumento);
            }
            Alumno matricula = controlDocumento.getMatricula();
            if (matricula != null) {
                matricula.getControlDocumentoList().remove(controlDocumento);
                matricula = em.merge(matricula);
            }
            em.remove(controlDocumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ControlDocumento> findControlDocumentoEntities() {
        return findControlDocumentoEntities(true, -1, -1);
    }

    public List<ControlDocumento> findControlDocumentoEntities(int maxResults, int firstResult) {
        return findControlDocumentoEntities(false, maxResults, firstResult);
    }

    private List<ControlDocumento> findControlDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ControlDocumento as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ControlDocumento findControlDocumento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ControlDocumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getControlDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ControlDocumento as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
