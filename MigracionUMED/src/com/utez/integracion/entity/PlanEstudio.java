/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Asignatura;
import com.utez.secretariaAcademica.entity.Plantel;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "plan_estudio")
@NamedQueries({
    @NamedQuery(name = "PlanEstudio.findAll", query = "SELECT p FROM PlanEstudio p"),
    @NamedQuery(name = "PlanEstudio.findByIdPlanestudio", query = "SELECT p FROM PlanEstudio p WHERE p.idPlanestudio = :idPlanestudio"),
    @NamedQuery(name = "PlanEstudio.findByRvoe", query = "SELECT p FROM PlanEstudio p WHERE p.rvoe = :rvoe"),
    @NamedQuery(name = "PlanEstudio.findByNombrePlanestudio", query = "SELECT p FROM PlanEstudio p WHERE p.nombrePlanestudio = :nombrePlanestudio"),
    @NamedQuery(name = "PlanEstudio.findByFechaAcuerdo", query = "SELECT p FROM PlanEstudio p WHERE p.fechaAcuerdo = :fechaAcuerdo"),
    @NamedQuery(name = "PlanEstudio.findByNumeroAcuerdo", query = "SELECT p FROM PlanEstudio p WHERE p.numeroAcuerdo = :numeroAcuerdo"),
    @NamedQuery(name = "PlanEstudio.findByAbreviaturaTitulo", query = "SELECT p FROM PlanEstudio p WHERE p.abreviaturaTitulo = :abreviaturaTitulo"),
    @NamedQuery(name = "PlanEstudio.findByClave", query = "SELECT p FROM PlanEstudio p WHERE p.clave = :clave"),
    @NamedQuery(name = "PlanEstudio.findByEstadoPlanestudio", query = "SELECT p FROM PlanEstudio p WHERE p.estadoPlanestudio = :estadoPlanestudio")})
public class PlanEstudio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_planestudio")
    private Long idPlanestudio;
    @Column(name = "rvoe")
    private String rvoe;
    @Column(name = "nombre_planestudio")
    private String nombrePlanestudio;
    @Column(name = "fecha_acuerdo")
    @Temporal(TemporalType.DATE)
    private Date fechaAcuerdo;
    @Column(name = "numero_acuerdo")
    private String numeroAcuerdo;
    @Column(name = "abreviatura_titulo")
    private String abreviaturaTitulo;
    @Column(name = "clave")
    private String clave;
    @Column(name = "estado_planestudio")
    private String estadoPlanestudio;
    @JoinTable(name = "asignacion_tipotitulacionplan", joinColumns = {
        @JoinColumn(name = "id_planestudio", referencedColumnName = "id_planestudio")}, inverseJoinColumns = {
        @JoinColumn(name = "id_tipotitulacion", referencedColumnName = "id_tipotitulacion")})
    @ManyToMany
    private List<TipoTitulacion> tipoTitulacionList;
    @JoinColumn(name = "id_tiponivelacademico", referencedColumnName = "id_tiponivelacademico")
    @ManyToOne
    private TipoNivelacademico idTiponivelacademico;
    @JoinColumn(name = "id_plantel", referencedColumnName = "id_plantel")
    @ManyToOne
    private Plantel idPlantel;
    @JoinColumn(name = "id_institucion", referencedColumnName = "id_institucion")
    @ManyToOne
    private InstitucionRegistro idInstitucion;
    @OneToMany(mappedBy = "idPlanestudio")
    private List<Aspirante> aspiranteList;
    @OneToMany(mappedBy = "idPlanestudio")
    private List<Generacion> generacionList;
    @OneToMany(mappedBy = "idPlanestudio")
    private List<AsignacionAsesorplan> asignacionAsesorplanList;
    @OneToMany(mappedBy = "idPlanestudio")
    private List<Asignatura> asignaturaList;

    public PlanEstudio() {
    }

    public PlanEstudio(Long idPlanestudio) {
        this.idPlanestudio = idPlanestudio;
    }

    public Long getIdPlanestudio() {
        return idPlanestudio;
    }

    public void setIdPlanestudio(Long idPlanestudio) {
        this.idPlanestudio = idPlanestudio;
    }

    public String getRvoe() {
        return rvoe;
    }

    public void setRvoe(String rvoe) {
        this.rvoe = rvoe;
    }

    public String getNombrePlanestudio() {
        return nombrePlanestudio;
    }

    public void setNombrePlanestudio(String nombrePlanestudio) {
        this.nombrePlanestudio = nombrePlanestudio;
    }

    public Date getFechaAcuerdo() {
        return fechaAcuerdo;
    }

    public void setFechaAcuerdo(Date fechaAcuerdo) {
        this.fechaAcuerdo = fechaAcuerdo;
    }

    public String getNumeroAcuerdo() {
        return numeroAcuerdo;
    }

    public void setNumeroAcuerdo(String numeroAcuerdo) {
        this.numeroAcuerdo = numeroAcuerdo;
    }

    public String getAbreviaturaTitulo() {
        return abreviaturaTitulo;
    }

    public void setAbreviaturaTitulo(String abreviaturaTitulo) {
        this.abreviaturaTitulo = abreviaturaTitulo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEstadoPlanestudio() {
        return estadoPlanestudio;
    }

    public void setEstadoPlanestudio(String estadoPlanestudio) {
        this.estadoPlanestudio = estadoPlanestudio;
    }

    public List<TipoTitulacion> getTipoTitulacionList() {
        return tipoTitulacionList;
    }

    public void setTipoTitulacionList(List<TipoTitulacion> tipoTitulacionList) {
        this.tipoTitulacionList = tipoTitulacionList;
    }

    public TipoNivelacademico getIdTiponivelacademico() {
        return idTiponivelacademico;
    }

    public void setIdTiponivelacademico(TipoNivelacademico idTiponivelacademico) {
        this.idTiponivelacademico = idTiponivelacademico;
    }

    public Plantel getIdPlantel() {
        return idPlantel;
    }

    public void setIdPlantel(Plantel idPlantel) {
        this.idPlantel = idPlantel;
    }

    public InstitucionRegistro getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(InstitucionRegistro idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public List<Aspirante> getAspiranteList() {
        return aspiranteList;
    }

    public void setAspiranteList(List<Aspirante> aspiranteList) {
        this.aspiranteList = aspiranteList;
    }

    public List<Generacion> getGeneracionList() {
        return generacionList;
    }

    public void setGeneracionList(List<Generacion> generacionList) {
        this.generacionList = generacionList;
    }

    public List<AsignacionAsesorplan> getAsignacionAsesorplanList() {
        return asignacionAsesorplanList;
    }

    public void setAsignacionAsesorplanList(List<AsignacionAsesorplan> asignacionAsesorplanList) {
        this.asignacionAsesorplanList = asignacionAsesorplanList;
    }

    public List<Asignatura> getAsignaturaList() {
        return asignaturaList;
    }

    public void setAsignaturaList(List<Asignatura> asignaturaList) {
        this.asignaturaList = asignaturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanestudio != null ? idPlanestudio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanEstudio)) {
            return false;
        }
        PlanEstudio other = (PlanEstudio) object;
        if ((this.idPlanestudio == null && other.idPlanestudio != null) || (this.idPlanestudio != null && !this.idPlanestudio.equals(other.idPlanestudio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.PlanEstudio[ idPlanestudio=" + idPlanestudio + " ]";
    }
    
}
