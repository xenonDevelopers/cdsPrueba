/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.FechaExamenprogramado;
import com.utez.integracion.entity.Aplicador;
import com.utez.integracion.entity.AsignacionAplicador;
import com.utez.integracion.entity.NominaAplicadores;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionAplicadorJpaController implements Serializable {

    public AsignacionAplicadorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionAplicador asignacionAplicador) {
        if (asignacionAplicador.getNominaAplicadoresList() == null) {
            asignacionAplicador.setNominaAplicadoresList(new ArrayList<NominaAplicadores>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FechaExamenprogramado idFechaexamenprogramado = asignacionAplicador.getIdFechaexamenprogramado();
            if (idFechaexamenprogramado != null) {
                idFechaexamenprogramado = em.getReference(idFechaexamenprogramado.getClass(), idFechaexamenprogramado.getIdFechaExamen());
                asignacionAplicador.setIdFechaexamenprogramado(idFechaexamenprogramado);
            }
            Aplicador idAplicador = asignacionAplicador.getIdAplicador();
            if (idAplicador != null) {
                idAplicador = em.getReference(idAplicador.getClass(), idAplicador.getIdAplicador());
                asignacionAplicador.setIdAplicador(idAplicador);
            }
            List<NominaAplicadores> attachedNominaAplicadoresList = new ArrayList<NominaAplicadores>();
            for (NominaAplicadores nominaAplicadoresListNominaAplicadoresToAttach : asignacionAplicador.getNominaAplicadoresList()) {
                nominaAplicadoresListNominaAplicadoresToAttach = em.getReference(nominaAplicadoresListNominaAplicadoresToAttach.getClass(), nominaAplicadoresListNominaAplicadoresToAttach.getIdNominaaplicadores());
                attachedNominaAplicadoresList.add(nominaAplicadoresListNominaAplicadoresToAttach);
            }
            asignacionAplicador.setNominaAplicadoresList(attachedNominaAplicadoresList);
            em.persist(asignacionAplicador);
            if (idFechaexamenprogramado != null) {
                idFechaexamenprogramado.getAsignacionAplicadorList().add(asignacionAplicador);
                idFechaexamenprogramado = em.merge(idFechaexamenprogramado);
            }
            if (idAplicador != null) {
                idAplicador.getAsignacionAplicadorList().add(asignacionAplicador);
                idAplicador = em.merge(idAplicador);
            }
            for (NominaAplicadores nominaAplicadoresListNominaAplicadores : asignacionAplicador.getNominaAplicadoresList()) {
                nominaAplicadoresListNominaAplicadores.getAsignacionAplicadorList().add(asignacionAplicador);
                nominaAplicadoresListNominaAplicadores = em.merge(nominaAplicadoresListNominaAplicadores);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionAplicador asignacionAplicador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionAplicador persistentAsignacionAplicador = em.find(AsignacionAplicador.class, asignacionAplicador.getIdAsignacionaplicador());
            FechaExamenprogramado idFechaexamenprogramadoOld = persistentAsignacionAplicador.getIdFechaexamenprogramado();
            FechaExamenprogramado idFechaexamenprogramadoNew = asignacionAplicador.getIdFechaexamenprogramado();
            Aplicador idAplicadorOld = persistentAsignacionAplicador.getIdAplicador();
            Aplicador idAplicadorNew = asignacionAplicador.getIdAplicador();
            List<NominaAplicadores> nominaAplicadoresListOld = persistentAsignacionAplicador.getNominaAplicadoresList();
            List<NominaAplicadores> nominaAplicadoresListNew = asignacionAplicador.getNominaAplicadoresList();
            if (idFechaexamenprogramadoNew != null) {
                idFechaexamenprogramadoNew = em.getReference(idFechaexamenprogramadoNew.getClass(), idFechaexamenprogramadoNew.getIdFechaExamen());
                asignacionAplicador.setIdFechaexamenprogramado(idFechaexamenprogramadoNew);
            }
            if (idAplicadorNew != null) {
                idAplicadorNew = em.getReference(idAplicadorNew.getClass(), idAplicadorNew.getIdAplicador());
                asignacionAplicador.setIdAplicador(idAplicadorNew);
            }
            List<NominaAplicadores> attachedNominaAplicadoresListNew = new ArrayList<NominaAplicadores>();
            for (NominaAplicadores nominaAplicadoresListNewNominaAplicadoresToAttach : nominaAplicadoresListNew) {
                nominaAplicadoresListNewNominaAplicadoresToAttach = em.getReference(nominaAplicadoresListNewNominaAplicadoresToAttach.getClass(), nominaAplicadoresListNewNominaAplicadoresToAttach.getIdNominaaplicadores());
                attachedNominaAplicadoresListNew.add(nominaAplicadoresListNewNominaAplicadoresToAttach);
            }
            nominaAplicadoresListNew = attachedNominaAplicadoresListNew;
            asignacionAplicador.setNominaAplicadoresList(nominaAplicadoresListNew);
            asignacionAplicador = em.merge(asignacionAplicador);
            if (idFechaexamenprogramadoOld != null && !idFechaexamenprogramadoOld.equals(idFechaexamenprogramadoNew)) {
                idFechaexamenprogramadoOld.getAsignacionAplicadorList().remove(asignacionAplicador);
                idFechaexamenprogramadoOld = em.merge(idFechaexamenprogramadoOld);
            }
            if (idFechaexamenprogramadoNew != null && !idFechaexamenprogramadoNew.equals(idFechaexamenprogramadoOld)) {
                idFechaexamenprogramadoNew.getAsignacionAplicadorList().add(asignacionAplicador);
                idFechaexamenprogramadoNew = em.merge(idFechaexamenprogramadoNew);
            }
            if (idAplicadorOld != null && !idAplicadorOld.equals(idAplicadorNew)) {
                idAplicadorOld.getAsignacionAplicadorList().remove(asignacionAplicador);
                idAplicadorOld = em.merge(idAplicadorOld);
            }
            if (idAplicadorNew != null && !idAplicadorNew.equals(idAplicadorOld)) {
                idAplicadorNew.getAsignacionAplicadorList().add(asignacionAplicador);
                idAplicadorNew = em.merge(idAplicadorNew);
            }
            for (NominaAplicadores nominaAplicadoresListOldNominaAplicadores : nominaAplicadoresListOld) {
                if (!nominaAplicadoresListNew.contains(nominaAplicadoresListOldNominaAplicadores)) {
                    nominaAplicadoresListOldNominaAplicadores.getAsignacionAplicadorList().remove(asignacionAplicador);
                    nominaAplicadoresListOldNominaAplicadores = em.merge(nominaAplicadoresListOldNominaAplicadores);
                }
            }
            for (NominaAplicadores nominaAplicadoresListNewNominaAplicadores : nominaAplicadoresListNew) {
                if (!nominaAplicadoresListOld.contains(nominaAplicadoresListNewNominaAplicadores)) {
                    nominaAplicadoresListNewNominaAplicadores.getAsignacionAplicadorList().add(asignacionAplicador);
                    nominaAplicadoresListNewNominaAplicadores = em.merge(nominaAplicadoresListNewNominaAplicadores);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionAplicador.getIdAsignacionaplicador();
                if (findAsignacionAplicador(id) == null) {
                    throw new NonexistentEntityException("The asignacionAplicador with id " + id + " no longer exists.");
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
            AsignacionAplicador asignacionAplicador;
            try {
                asignacionAplicador = em.getReference(AsignacionAplicador.class, id);
                asignacionAplicador.getIdAsignacionaplicador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionAplicador with id " + id + " no longer exists.", enfe);
            }
            FechaExamenprogramado idFechaexamenprogramado = asignacionAplicador.getIdFechaexamenprogramado();
            if (idFechaexamenprogramado != null) {
                idFechaexamenprogramado.getAsignacionAplicadorList().remove(asignacionAplicador);
                idFechaexamenprogramado = em.merge(idFechaexamenprogramado);
            }
            Aplicador idAplicador = asignacionAplicador.getIdAplicador();
            if (idAplicador != null) {
                idAplicador.getAsignacionAplicadorList().remove(asignacionAplicador);
                idAplicador = em.merge(idAplicador);
            }
            List<NominaAplicadores> nominaAplicadoresList = asignacionAplicador.getNominaAplicadoresList();
            for (NominaAplicadores nominaAplicadoresListNominaAplicadores : nominaAplicadoresList) {
                nominaAplicadoresListNominaAplicadores.getAsignacionAplicadorList().remove(asignacionAplicador);
                nominaAplicadoresListNominaAplicadores = em.merge(nominaAplicadoresListNominaAplicadores);
            }
            em.remove(asignacionAplicador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionAplicador> findAsignacionAplicadorEntities() {
        return findAsignacionAplicadorEntities(true, -1, -1);
    }

    public List<AsignacionAplicador> findAsignacionAplicadorEntities(int maxResults, int firstResult) {
        return findAsignacionAplicadorEntities(false, maxResults, firstResult);
    }

    private List<AsignacionAplicador> findAsignacionAplicadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionAplicador as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionAplicador findAsignacionAplicador(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionAplicador.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionAplicadorCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionAplicador as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
