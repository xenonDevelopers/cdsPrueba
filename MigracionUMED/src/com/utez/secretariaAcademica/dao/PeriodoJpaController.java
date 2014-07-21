/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Grupo;
import java.util.ArrayList;
import java.util.List;
import com.utez.secretariaAcademica.entity.Calendario;
import com.utez.integracion.entity.CalendarioEscolar;
import com.utez.integracion.entity.PagoPeriodo;
import com.utez.integracion.entity.GrupoInduccion;
import com.utez.integracion.entity.CalendarioRectoria;
import com.utez.integracion.entity.Aspirante;
import com.utez.integracion.entity.Generacion;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secretariaAcademica.dao.exceptions.PreexistingEntityException;
import com.utez.secretariaAcademica.entity.Periodo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class PeriodoJpaController implements Serializable {

    public PeriodoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Periodo periodo) throws PreexistingEntityException, Exception {
        if (periodo.getGrupoList() == null) {
            periodo.setGrupoList(new ArrayList<Grupo>());
        }
        if (periodo.getCalendarioList() == null) {
            periodo.setCalendarioList(new ArrayList<Calendario>());
        }
        if (periodo.getCalendarioEscolarList() == null) {
            periodo.setCalendarioEscolarList(new ArrayList<CalendarioEscolar>());
        }
        if (periodo.getPagoPeriodoList() == null) {
            periodo.setPagoPeriodoList(new ArrayList<PagoPeriodo>());
        }
        if (periodo.getGrupoInduccionList() == null) {
            periodo.setGrupoInduccionList(new ArrayList<GrupoInduccion>());
        }
        if (periodo.getCalendarioRectoriaList() == null) {
            periodo.setCalendarioRectoriaList(new ArrayList<CalendarioRectoria>());
        }
        if (periodo.getAspiranteList() == null) {
            periodo.setAspiranteList(new ArrayList<Aspirante>());
        }
        if (periodo.getGeneracionList() == null) {
            periodo.setGeneracionList(new ArrayList<Generacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Grupo> attachedGrupoList = new ArrayList<Grupo>();
            for (Grupo grupoListGrupoToAttach : periodo.getGrupoList()) {
                grupoListGrupoToAttach = em.getReference(grupoListGrupoToAttach.getClass(), grupoListGrupoToAttach.getIdgrupo());
                attachedGrupoList.add(grupoListGrupoToAttach);
            }
            periodo.setGrupoList(attachedGrupoList);
            List<Calendario> attachedCalendarioList = new ArrayList<Calendario>();
            for (Calendario calendarioListCalendarioToAttach : periodo.getCalendarioList()) {
                calendarioListCalendarioToAttach = em.getReference(calendarioListCalendarioToAttach.getClass(), calendarioListCalendarioToAttach.getIdcalendario());
                attachedCalendarioList.add(calendarioListCalendarioToAttach);
            }
            periodo.setCalendarioList(attachedCalendarioList);
            List<CalendarioEscolar> attachedCalendarioEscolarList = new ArrayList<CalendarioEscolar>();
            for (CalendarioEscolar calendarioEscolarListCalendarioEscolarToAttach : periodo.getCalendarioEscolarList()) {
                calendarioEscolarListCalendarioEscolarToAttach = em.getReference(calendarioEscolarListCalendarioEscolarToAttach.getClass(), calendarioEscolarListCalendarioEscolarToAttach.getIdCalendarioescolar());
                attachedCalendarioEscolarList.add(calendarioEscolarListCalendarioEscolarToAttach);
            }
            periodo.setCalendarioEscolarList(attachedCalendarioEscolarList);
            List<PagoPeriodo> attachedPagoPeriodoList = new ArrayList<PagoPeriodo>();
            for (PagoPeriodo pagoPeriodoListPagoPeriodoToAttach : periodo.getPagoPeriodoList()) {
                pagoPeriodoListPagoPeriodoToAttach = em.getReference(pagoPeriodoListPagoPeriodoToAttach.getClass(), pagoPeriodoListPagoPeriodoToAttach.getIdPagoperiodo());
                attachedPagoPeriodoList.add(pagoPeriodoListPagoPeriodoToAttach);
            }
            periodo.setPagoPeriodoList(attachedPagoPeriodoList);
            List<GrupoInduccion> attachedGrupoInduccionList = new ArrayList<GrupoInduccion>();
            for (GrupoInduccion grupoInduccionListGrupoInduccionToAttach : periodo.getGrupoInduccionList()) {
                grupoInduccionListGrupoInduccionToAttach = em.getReference(grupoInduccionListGrupoInduccionToAttach.getClass(), grupoInduccionListGrupoInduccionToAttach.getIdGrupoinduccion());
                attachedGrupoInduccionList.add(grupoInduccionListGrupoInduccionToAttach);
            }
            periodo.setGrupoInduccionList(attachedGrupoInduccionList);
            List<CalendarioRectoria> attachedCalendarioRectoriaList = new ArrayList<CalendarioRectoria>();
            for (CalendarioRectoria calendarioRectoriaListCalendarioRectoriaToAttach : periodo.getCalendarioRectoriaList()) {
                calendarioRectoriaListCalendarioRectoriaToAttach = em.getReference(calendarioRectoriaListCalendarioRectoriaToAttach.getClass(), calendarioRectoriaListCalendarioRectoriaToAttach.getIdCalendariorectoria());
                attachedCalendarioRectoriaList.add(calendarioRectoriaListCalendarioRectoriaToAttach);
            }
            periodo.setCalendarioRectoriaList(attachedCalendarioRectoriaList);
            List<Aspirante> attachedAspiranteList = new ArrayList<Aspirante>();
            for (Aspirante aspiranteListAspiranteToAttach : periodo.getAspiranteList()) {
                aspiranteListAspiranteToAttach = em.getReference(aspiranteListAspiranteToAttach.getClass(), aspiranteListAspiranteToAttach.getIdAspirante());
                attachedAspiranteList.add(aspiranteListAspiranteToAttach);
            }
            periodo.setAspiranteList(attachedAspiranteList);
            List<Generacion> attachedGeneracionList = new ArrayList<Generacion>();
            for (Generacion generacionListGeneracionToAttach : periodo.getGeneracionList()) {
                generacionListGeneracionToAttach = em.getReference(generacionListGeneracionToAttach.getClass(), generacionListGeneracionToAttach.getIdGeneracion());
                attachedGeneracionList.add(generacionListGeneracionToAttach);
            }
            periodo.setGeneracionList(attachedGeneracionList);
            em.persist(periodo);
            for (Grupo grupoListGrupo : periodo.getGrupoList()) {
                Periodo oldPeriodoingresoOfGrupoListGrupo = grupoListGrupo.getPeriodoingreso();
                grupoListGrupo.setPeriodoingreso(periodo);
                grupoListGrupo = em.merge(grupoListGrupo);
                if (oldPeriodoingresoOfGrupoListGrupo != null) {
                    oldPeriodoingresoOfGrupoListGrupo.getGrupoList().remove(grupoListGrupo);
                    oldPeriodoingresoOfGrupoListGrupo = em.merge(oldPeriodoingresoOfGrupoListGrupo);
                }
            }
            for (Calendario calendarioListCalendario : periodo.getCalendarioList()) {
                Periodo oldPeriodoOfCalendarioListCalendario = calendarioListCalendario.getPeriodo();
                calendarioListCalendario.setPeriodo(periodo);
                calendarioListCalendario = em.merge(calendarioListCalendario);
                if (oldPeriodoOfCalendarioListCalendario != null) {
                    oldPeriodoOfCalendarioListCalendario.getCalendarioList().remove(calendarioListCalendario);
                    oldPeriodoOfCalendarioListCalendario = em.merge(oldPeriodoOfCalendarioListCalendario);
                }
            }
            for (CalendarioEscolar calendarioEscolarListCalendarioEscolar : periodo.getCalendarioEscolarList()) {
                Periodo oldIdPeriodoOfCalendarioEscolarListCalendarioEscolar = calendarioEscolarListCalendarioEscolar.getIdPeriodo();
                calendarioEscolarListCalendarioEscolar.setIdPeriodo(periodo);
                calendarioEscolarListCalendarioEscolar = em.merge(calendarioEscolarListCalendarioEscolar);
                if (oldIdPeriodoOfCalendarioEscolarListCalendarioEscolar != null) {
                    oldIdPeriodoOfCalendarioEscolarListCalendarioEscolar.getCalendarioEscolarList().remove(calendarioEscolarListCalendarioEscolar);
                    oldIdPeriodoOfCalendarioEscolarListCalendarioEscolar = em.merge(oldIdPeriodoOfCalendarioEscolarListCalendarioEscolar);
                }
            }
            for (PagoPeriodo pagoPeriodoListPagoPeriodo : periodo.getPagoPeriodoList()) {
                Periodo oldIdPeriodoOfPagoPeriodoListPagoPeriodo = pagoPeriodoListPagoPeriodo.getIdPeriodo();
                pagoPeriodoListPagoPeriodo.setIdPeriodo(periodo);
                pagoPeriodoListPagoPeriodo = em.merge(pagoPeriodoListPagoPeriodo);
                if (oldIdPeriodoOfPagoPeriodoListPagoPeriodo != null) {
                    oldIdPeriodoOfPagoPeriodoListPagoPeriodo.getPagoPeriodoList().remove(pagoPeriodoListPagoPeriodo);
                    oldIdPeriodoOfPagoPeriodoListPagoPeriodo = em.merge(oldIdPeriodoOfPagoPeriodoListPagoPeriodo);
                }
            }
            for (GrupoInduccion grupoInduccionListGrupoInduccion : periodo.getGrupoInduccionList()) {
                Periodo oldIdPeriodoOfGrupoInduccionListGrupoInduccion = grupoInduccionListGrupoInduccion.getIdPeriodo();
                grupoInduccionListGrupoInduccion.setIdPeriodo(periodo);
                grupoInduccionListGrupoInduccion = em.merge(grupoInduccionListGrupoInduccion);
                if (oldIdPeriodoOfGrupoInduccionListGrupoInduccion != null) {
                    oldIdPeriodoOfGrupoInduccionListGrupoInduccion.getGrupoInduccionList().remove(grupoInduccionListGrupoInduccion);
                    oldIdPeriodoOfGrupoInduccionListGrupoInduccion = em.merge(oldIdPeriodoOfGrupoInduccionListGrupoInduccion);
                }
            }
            for (CalendarioRectoria calendarioRectoriaListCalendarioRectoria : periodo.getCalendarioRectoriaList()) {
                Periodo oldIdPeriodoOfCalendarioRectoriaListCalendarioRectoria = calendarioRectoriaListCalendarioRectoria.getIdPeriodo();
                calendarioRectoriaListCalendarioRectoria.setIdPeriodo(periodo);
                calendarioRectoriaListCalendarioRectoria = em.merge(calendarioRectoriaListCalendarioRectoria);
                if (oldIdPeriodoOfCalendarioRectoriaListCalendarioRectoria != null) {
                    oldIdPeriodoOfCalendarioRectoriaListCalendarioRectoria.getCalendarioRectoriaList().remove(calendarioRectoriaListCalendarioRectoria);
                    oldIdPeriodoOfCalendarioRectoriaListCalendarioRectoria = em.merge(oldIdPeriodoOfCalendarioRectoriaListCalendarioRectoria);
                }
            }
            for (Aspirante aspiranteListAspirante : periodo.getAspiranteList()) {
                Periodo oldIdPeriodoOfAspiranteListAspirante = aspiranteListAspirante.getIdPeriodo();
                aspiranteListAspirante.setIdPeriodo(periodo);
                aspiranteListAspirante = em.merge(aspiranteListAspirante);
                if (oldIdPeriodoOfAspiranteListAspirante != null) {
                    oldIdPeriodoOfAspiranteListAspirante.getAspiranteList().remove(aspiranteListAspirante);
                    oldIdPeriodoOfAspiranteListAspirante = em.merge(oldIdPeriodoOfAspiranteListAspirante);
                }
            }
            for (Generacion generacionListGeneracion : periodo.getGeneracionList()) {
                Periodo oldIdPeriodoOfGeneracionListGeneracion = generacionListGeneracion.getIdPeriodo();
                generacionListGeneracion.setIdPeriodo(periodo);
                generacionListGeneracion = em.merge(generacionListGeneracion);
                if (oldIdPeriodoOfGeneracionListGeneracion != null) {
                    oldIdPeriodoOfGeneracionListGeneracion.getGeneracionList().remove(generacionListGeneracion);
                    oldIdPeriodoOfGeneracionListGeneracion = em.merge(oldIdPeriodoOfGeneracionListGeneracion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPeriodo(periodo.getPeriodo()) != null) {
                throw new PreexistingEntityException("Periodo " + periodo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Periodo periodo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Periodo persistentPeriodo = em.find(Periodo.class, periodo.getPeriodo());
            List<Grupo> grupoListOld = persistentPeriodo.getGrupoList();
            List<Grupo> grupoListNew = periodo.getGrupoList();
            List<Calendario> calendarioListOld = persistentPeriodo.getCalendarioList();
            List<Calendario> calendarioListNew = periodo.getCalendarioList();
            List<CalendarioEscolar> calendarioEscolarListOld = persistentPeriodo.getCalendarioEscolarList();
            List<CalendarioEscolar> calendarioEscolarListNew = periodo.getCalendarioEscolarList();
            List<PagoPeriodo> pagoPeriodoListOld = persistentPeriodo.getPagoPeriodoList();
            List<PagoPeriodo> pagoPeriodoListNew = periodo.getPagoPeriodoList();
            List<GrupoInduccion> grupoInduccionListOld = persistentPeriodo.getGrupoInduccionList();
            List<GrupoInduccion> grupoInduccionListNew = periodo.getGrupoInduccionList();
            List<CalendarioRectoria> calendarioRectoriaListOld = persistentPeriodo.getCalendarioRectoriaList();
            List<CalendarioRectoria> calendarioRectoriaListNew = periodo.getCalendarioRectoriaList();
            List<Aspirante> aspiranteListOld = persistentPeriodo.getAspiranteList();
            List<Aspirante> aspiranteListNew = periodo.getAspiranteList();
            List<Generacion> generacionListOld = persistentPeriodo.getGeneracionList();
            List<Generacion> generacionListNew = periodo.getGeneracionList();
            List<Grupo> attachedGrupoListNew = new ArrayList<Grupo>();
            for (Grupo grupoListNewGrupoToAttach : grupoListNew) {
                grupoListNewGrupoToAttach = em.getReference(grupoListNewGrupoToAttach.getClass(), grupoListNewGrupoToAttach.getIdgrupo());
                attachedGrupoListNew.add(grupoListNewGrupoToAttach);
            }
            grupoListNew = attachedGrupoListNew;
            periodo.setGrupoList(grupoListNew);
            List<Calendario> attachedCalendarioListNew = new ArrayList<Calendario>();
            for (Calendario calendarioListNewCalendarioToAttach : calendarioListNew) {
                calendarioListNewCalendarioToAttach = em.getReference(calendarioListNewCalendarioToAttach.getClass(), calendarioListNewCalendarioToAttach.getIdcalendario());
                attachedCalendarioListNew.add(calendarioListNewCalendarioToAttach);
            }
            calendarioListNew = attachedCalendarioListNew;
            periodo.setCalendarioList(calendarioListNew);
            List<CalendarioEscolar> attachedCalendarioEscolarListNew = new ArrayList<CalendarioEscolar>();
            for (CalendarioEscolar calendarioEscolarListNewCalendarioEscolarToAttach : calendarioEscolarListNew) {
                calendarioEscolarListNewCalendarioEscolarToAttach = em.getReference(calendarioEscolarListNewCalendarioEscolarToAttach.getClass(), calendarioEscolarListNewCalendarioEscolarToAttach.getIdCalendarioescolar());
                attachedCalendarioEscolarListNew.add(calendarioEscolarListNewCalendarioEscolarToAttach);
            }
            calendarioEscolarListNew = attachedCalendarioEscolarListNew;
            periodo.setCalendarioEscolarList(calendarioEscolarListNew);
            List<PagoPeriodo> attachedPagoPeriodoListNew = new ArrayList<PagoPeriodo>();
            for (PagoPeriodo pagoPeriodoListNewPagoPeriodoToAttach : pagoPeriodoListNew) {
                pagoPeriodoListNewPagoPeriodoToAttach = em.getReference(pagoPeriodoListNewPagoPeriodoToAttach.getClass(), pagoPeriodoListNewPagoPeriodoToAttach.getIdPagoperiodo());
                attachedPagoPeriodoListNew.add(pagoPeriodoListNewPagoPeriodoToAttach);
            }
            pagoPeriodoListNew = attachedPagoPeriodoListNew;
            periodo.setPagoPeriodoList(pagoPeriodoListNew);
            List<GrupoInduccion> attachedGrupoInduccionListNew = new ArrayList<GrupoInduccion>();
            for (GrupoInduccion grupoInduccionListNewGrupoInduccionToAttach : grupoInduccionListNew) {
                grupoInduccionListNewGrupoInduccionToAttach = em.getReference(grupoInduccionListNewGrupoInduccionToAttach.getClass(), grupoInduccionListNewGrupoInduccionToAttach.getIdGrupoinduccion());
                attachedGrupoInduccionListNew.add(grupoInduccionListNewGrupoInduccionToAttach);
            }
            grupoInduccionListNew = attachedGrupoInduccionListNew;
            periodo.setGrupoInduccionList(grupoInduccionListNew);
            List<CalendarioRectoria> attachedCalendarioRectoriaListNew = new ArrayList<CalendarioRectoria>();
            for (CalendarioRectoria calendarioRectoriaListNewCalendarioRectoriaToAttach : calendarioRectoriaListNew) {
                calendarioRectoriaListNewCalendarioRectoriaToAttach = em.getReference(calendarioRectoriaListNewCalendarioRectoriaToAttach.getClass(), calendarioRectoriaListNewCalendarioRectoriaToAttach.getIdCalendariorectoria());
                attachedCalendarioRectoriaListNew.add(calendarioRectoriaListNewCalendarioRectoriaToAttach);
            }
            calendarioRectoriaListNew = attachedCalendarioRectoriaListNew;
            periodo.setCalendarioRectoriaList(calendarioRectoriaListNew);
            List<Aspirante> attachedAspiranteListNew = new ArrayList<Aspirante>();
            for (Aspirante aspiranteListNewAspiranteToAttach : aspiranteListNew) {
                aspiranteListNewAspiranteToAttach = em.getReference(aspiranteListNewAspiranteToAttach.getClass(), aspiranteListNewAspiranteToAttach.getIdAspirante());
                attachedAspiranteListNew.add(aspiranteListNewAspiranteToAttach);
            }
            aspiranteListNew = attachedAspiranteListNew;
            periodo.setAspiranteList(aspiranteListNew);
            List<Generacion> attachedGeneracionListNew = new ArrayList<Generacion>();
            for (Generacion generacionListNewGeneracionToAttach : generacionListNew) {
                generacionListNewGeneracionToAttach = em.getReference(generacionListNewGeneracionToAttach.getClass(), generacionListNewGeneracionToAttach.getIdGeneracion());
                attachedGeneracionListNew.add(generacionListNewGeneracionToAttach);
            }
            generacionListNew = attachedGeneracionListNew;
            periodo.setGeneracionList(generacionListNew);
            periodo = em.merge(periodo);
            for (Grupo grupoListOldGrupo : grupoListOld) {
                if (!grupoListNew.contains(grupoListOldGrupo)) {
                    grupoListOldGrupo.setPeriodoingreso(null);
                    grupoListOldGrupo = em.merge(grupoListOldGrupo);
                }
            }
            for (Grupo grupoListNewGrupo : grupoListNew) {
                if (!grupoListOld.contains(grupoListNewGrupo)) {
                    Periodo oldPeriodoingresoOfGrupoListNewGrupo = grupoListNewGrupo.getPeriodoingreso();
                    grupoListNewGrupo.setPeriodoingreso(periodo);
                    grupoListNewGrupo = em.merge(grupoListNewGrupo);
                    if (oldPeriodoingresoOfGrupoListNewGrupo != null && !oldPeriodoingresoOfGrupoListNewGrupo.equals(periodo)) {
                        oldPeriodoingresoOfGrupoListNewGrupo.getGrupoList().remove(grupoListNewGrupo);
                        oldPeriodoingresoOfGrupoListNewGrupo = em.merge(oldPeriodoingresoOfGrupoListNewGrupo);
                    }
                }
            }
            for (Calendario calendarioListOldCalendario : calendarioListOld) {
                if (!calendarioListNew.contains(calendarioListOldCalendario)) {
                    calendarioListOldCalendario.setPeriodo(null);
                    calendarioListOldCalendario = em.merge(calendarioListOldCalendario);
                }
            }
            for (Calendario calendarioListNewCalendario : calendarioListNew) {
                if (!calendarioListOld.contains(calendarioListNewCalendario)) {
                    Periodo oldPeriodoOfCalendarioListNewCalendario = calendarioListNewCalendario.getPeriodo();
                    calendarioListNewCalendario.setPeriodo(periodo);
                    calendarioListNewCalendario = em.merge(calendarioListNewCalendario);
                    if (oldPeriodoOfCalendarioListNewCalendario != null && !oldPeriodoOfCalendarioListNewCalendario.equals(periodo)) {
                        oldPeriodoOfCalendarioListNewCalendario.getCalendarioList().remove(calendarioListNewCalendario);
                        oldPeriodoOfCalendarioListNewCalendario = em.merge(oldPeriodoOfCalendarioListNewCalendario);
                    }
                }
            }
            for (CalendarioEscolar calendarioEscolarListOldCalendarioEscolar : calendarioEscolarListOld) {
                if (!calendarioEscolarListNew.contains(calendarioEscolarListOldCalendarioEscolar)) {
                    calendarioEscolarListOldCalendarioEscolar.setIdPeriodo(null);
                    calendarioEscolarListOldCalendarioEscolar = em.merge(calendarioEscolarListOldCalendarioEscolar);
                }
            }
            for (CalendarioEscolar calendarioEscolarListNewCalendarioEscolar : calendarioEscolarListNew) {
                if (!calendarioEscolarListOld.contains(calendarioEscolarListNewCalendarioEscolar)) {
                    Periodo oldIdPeriodoOfCalendarioEscolarListNewCalendarioEscolar = calendarioEscolarListNewCalendarioEscolar.getIdPeriodo();
                    calendarioEscolarListNewCalendarioEscolar.setIdPeriodo(periodo);
                    calendarioEscolarListNewCalendarioEscolar = em.merge(calendarioEscolarListNewCalendarioEscolar);
                    if (oldIdPeriodoOfCalendarioEscolarListNewCalendarioEscolar != null && !oldIdPeriodoOfCalendarioEscolarListNewCalendarioEscolar.equals(periodo)) {
                        oldIdPeriodoOfCalendarioEscolarListNewCalendarioEscolar.getCalendarioEscolarList().remove(calendarioEscolarListNewCalendarioEscolar);
                        oldIdPeriodoOfCalendarioEscolarListNewCalendarioEscolar = em.merge(oldIdPeriodoOfCalendarioEscolarListNewCalendarioEscolar);
                    }
                }
            }
            for (PagoPeriodo pagoPeriodoListOldPagoPeriodo : pagoPeriodoListOld) {
                if (!pagoPeriodoListNew.contains(pagoPeriodoListOldPagoPeriodo)) {
                    pagoPeriodoListOldPagoPeriodo.setIdPeriodo(null);
                    pagoPeriodoListOldPagoPeriodo = em.merge(pagoPeriodoListOldPagoPeriodo);
                }
            }
            for (PagoPeriodo pagoPeriodoListNewPagoPeriodo : pagoPeriodoListNew) {
                if (!pagoPeriodoListOld.contains(pagoPeriodoListNewPagoPeriodo)) {
                    Periodo oldIdPeriodoOfPagoPeriodoListNewPagoPeriodo = pagoPeriodoListNewPagoPeriodo.getIdPeriodo();
                    pagoPeriodoListNewPagoPeriodo.setIdPeriodo(periodo);
                    pagoPeriodoListNewPagoPeriodo = em.merge(pagoPeriodoListNewPagoPeriodo);
                    if (oldIdPeriodoOfPagoPeriodoListNewPagoPeriodo != null && !oldIdPeriodoOfPagoPeriodoListNewPagoPeriodo.equals(periodo)) {
                        oldIdPeriodoOfPagoPeriodoListNewPagoPeriodo.getPagoPeriodoList().remove(pagoPeriodoListNewPagoPeriodo);
                        oldIdPeriodoOfPagoPeriodoListNewPagoPeriodo = em.merge(oldIdPeriodoOfPagoPeriodoListNewPagoPeriodo);
                    }
                }
            }
            for (GrupoInduccion grupoInduccionListOldGrupoInduccion : grupoInduccionListOld) {
                if (!grupoInduccionListNew.contains(grupoInduccionListOldGrupoInduccion)) {
                    grupoInduccionListOldGrupoInduccion.setIdPeriodo(null);
                    grupoInduccionListOldGrupoInduccion = em.merge(grupoInduccionListOldGrupoInduccion);
                }
            }
            for (GrupoInduccion grupoInduccionListNewGrupoInduccion : grupoInduccionListNew) {
                if (!grupoInduccionListOld.contains(grupoInduccionListNewGrupoInduccion)) {
                    Periodo oldIdPeriodoOfGrupoInduccionListNewGrupoInduccion = grupoInduccionListNewGrupoInduccion.getIdPeriodo();
                    grupoInduccionListNewGrupoInduccion.setIdPeriodo(periodo);
                    grupoInduccionListNewGrupoInduccion = em.merge(grupoInduccionListNewGrupoInduccion);
                    if (oldIdPeriodoOfGrupoInduccionListNewGrupoInduccion != null && !oldIdPeriodoOfGrupoInduccionListNewGrupoInduccion.equals(periodo)) {
                        oldIdPeriodoOfGrupoInduccionListNewGrupoInduccion.getGrupoInduccionList().remove(grupoInduccionListNewGrupoInduccion);
                        oldIdPeriodoOfGrupoInduccionListNewGrupoInduccion = em.merge(oldIdPeriodoOfGrupoInduccionListNewGrupoInduccion);
                    }
                }
            }
            for (CalendarioRectoria calendarioRectoriaListOldCalendarioRectoria : calendarioRectoriaListOld) {
                if (!calendarioRectoriaListNew.contains(calendarioRectoriaListOldCalendarioRectoria)) {
                    calendarioRectoriaListOldCalendarioRectoria.setIdPeriodo(null);
                    calendarioRectoriaListOldCalendarioRectoria = em.merge(calendarioRectoriaListOldCalendarioRectoria);
                }
            }
            for (CalendarioRectoria calendarioRectoriaListNewCalendarioRectoria : calendarioRectoriaListNew) {
                if (!calendarioRectoriaListOld.contains(calendarioRectoriaListNewCalendarioRectoria)) {
                    Periodo oldIdPeriodoOfCalendarioRectoriaListNewCalendarioRectoria = calendarioRectoriaListNewCalendarioRectoria.getIdPeriodo();
                    calendarioRectoriaListNewCalendarioRectoria.setIdPeriodo(periodo);
                    calendarioRectoriaListNewCalendarioRectoria = em.merge(calendarioRectoriaListNewCalendarioRectoria);
                    if (oldIdPeriodoOfCalendarioRectoriaListNewCalendarioRectoria != null && !oldIdPeriodoOfCalendarioRectoriaListNewCalendarioRectoria.equals(periodo)) {
                        oldIdPeriodoOfCalendarioRectoriaListNewCalendarioRectoria.getCalendarioRectoriaList().remove(calendarioRectoriaListNewCalendarioRectoria);
                        oldIdPeriodoOfCalendarioRectoriaListNewCalendarioRectoria = em.merge(oldIdPeriodoOfCalendarioRectoriaListNewCalendarioRectoria);
                    }
                }
            }
            for (Aspirante aspiranteListOldAspirante : aspiranteListOld) {
                if (!aspiranteListNew.contains(aspiranteListOldAspirante)) {
                    aspiranteListOldAspirante.setIdPeriodo(null);
                    aspiranteListOldAspirante = em.merge(aspiranteListOldAspirante);
                }
            }
            for (Aspirante aspiranteListNewAspirante : aspiranteListNew) {
                if (!aspiranteListOld.contains(aspiranteListNewAspirante)) {
                    Periodo oldIdPeriodoOfAspiranteListNewAspirante = aspiranteListNewAspirante.getIdPeriodo();
                    aspiranteListNewAspirante.setIdPeriodo(periodo);
                    aspiranteListNewAspirante = em.merge(aspiranteListNewAspirante);
                    if (oldIdPeriodoOfAspiranteListNewAspirante != null && !oldIdPeriodoOfAspiranteListNewAspirante.equals(periodo)) {
                        oldIdPeriodoOfAspiranteListNewAspirante.getAspiranteList().remove(aspiranteListNewAspirante);
                        oldIdPeriodoOfAspiranteListNewAspirante = em.merge(oldIdPeriodoOfAspiranteListNewAspirante);
                    }
                }
            }
            for (Generacion generacionListOldGeneracion : generacionListOld) {
                if (!generacionListNew.contains(generacionListOldGeneracion)) {
                    generacionListOldGeneracion.setIdPeriodo(null);
                    generacionListOldGeneracion = em.merge(generacionListOldGeneracion);
                }
            }
            for (Generacion generacionListNewGeneracion : generacionListNew) {
                if (!generacionListOld.contains(generacionListNewGeneracion)) {
                    Periodo oldIdPeriodoOfGeneracionListNewGeneracion = generacionListNewGeneracion.getIdPeriodo();
                    generacionListNewGeneracion.setIdPeriodo(periodo);
                    generacionListNewGeneracion = em.merge(generacionListNewGeneracion);
                    if (oldIdPeriodoOfGeneracionListNewGeneracion != null && !oldIdPeriodoOfGeneracionListNewGeneracion.equals(periodo)) {
                        oldIdPeriodoOfGeneracionListNewGeneracion.getGeneracionList().remove(generacionListNewGeneracion);
                        oldIdPeriodoOfGeneracionListNewGeneracion = em.merge(oldIdPeriodoOfGeneracionListNewGeneracion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = periodo.getPeriodo();
                if (findPeriodo(id) == null) {
                    throw new NonexistentEntityException("The periodo with id " + id + " no longer exists.");
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
            Periodo periodo;
            try {
                periodo = em.getReference(Periodo.class, id);
                periodo.getPeriodo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The periodo with id " + id + " no longer exists.", enfe);
            }
            List<Grupo> grupoList = periodo.getGrupoList();
            for (Grupo grupoListGrupo : grupoList) {
                grupoListGrupo.setPeriodoingreso(null);
                grupoListGrupo = em.merge(grupoListGrupo);
            }
            List<Calendario> calendarioList = periodo.getCalendarioList();
            for (Calendario calendarioListCalendario : calendarioList) {
                calendarioListCalendario.setPeriodo(null);
                calendarioListCalendario = em.merge(calendarioListCalendario);
            }
            List<CalendarioEscolar> calendarioEscolarList = periodo.getCalendarioEscolarList();
            for (CalendarioEscolar calendarioEscolarListCalendarioEscolar : calendarioEscolarList) {
                calendarioEscolarListCalendarioEscolar.setIdPeriodo(null);
                calendarioEscolarListCalendarioEscolar = em.merge(calendarioEscolarListCalendarioEscolar);
            }
            List<PagoPeriodo> pagoPeriodoList = periodo.getPagoPeriodoList();
            for (PagoPeriodo pagoPeriodoListPagoPeriodo : pagoPeriodoList) {
                pagoPeriodoListPagoPeriodo.setIdPeriodo(null);
                pagoPeriodoListPagoPeriodo = em.merge(pagoPeriodoListPagoPeriodo);
            }
            List<GrupoInduccion> grupoInduccionList = periodo.getGrupoInduccionList();
            for (GrupoInduccion grupoInduccionListGrupoInduccion : grupoInduccionList) {
                grupoInduccionListGrupoInduccion.setIdPeriodo(null);
                grupoInduccionListGrupoInduccion = em.merge(grupoInduccionListGrupoInduccion);
            }
            List<CalendarioRectoria> calendarioRectoriaList = periodo.getCalendarioRectoriaList();
            for (CalendarioRectoria calendarioRectoriaListCalendarioRectoria : calendarioRectoriaList) {
                calendarioRectoriaListCalendarioRectoria.setIdPeriodo(null);
                calendarioRectoriaListCalendarioRectoria = em.merge(calendarioRectoriaListCalendarioRectoria);
            }
            List<Aspirante> aspiranteList = periodo.getAspiranteList();
            for (Aspirante aspiranteListAspirante : aspiranteList) {
                aspiranteListAspirante.setIdPeriodo(null);
                aspiranteListAspirante = em.merge(aspiranteListAspirante);
            }
            List<Generacion> generacionList = periodo.getGeneracionList();
            for (Generacion generacionListGeneracion : generacionList) {
                generacionListGeneracion.setIdPeriodo(null);
                generacionListGeneracion = em.merge(generacionListGeneracion);
            }
            em.remove(periodo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Periodo> findPeriodoEntities() {
        return findPeriodoEntities(true, -1, -1);
    }

    public List<Periodo> findPeriodoEntities(int maxResults, int firstResult) {
        return findPeriodoEntities(false, maxResults, firstResult);
    }

    private List<Periodo> findPeriodoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Periodo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Periodo findPeriodo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Periodo.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeriodoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Periodo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
