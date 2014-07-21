/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Asesor;
import com.utez.secretariaAcademica.entity.Periodo;
import com.utez.secretariaAcademica.entity.Plantel;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "grupo_induccion")
@NamedQueries({
    @NamedQuery(name = "GrupoInduccion.findAll", query = "SELECT g FROM GrupoInduccion g"),
    @NamedQuery(name = "GrupoInduccion.findByIdGrupoinduccion", query = "SELECT g FROM GrupoInduccion g WHERE g.idGrupoinduccion = :idGrupoinduccion"),
    @NamedQuery(name = "GrupoInduccion.findByCupoMaximo", query = "SELECT g FROM GrupoInduccion g WHERE g.cupoMaximo = :cupoMaximo"),
    @NamedQuery(name = "GrupoInduccion.findByCupoMinimo", query = "SELECT g FROM GrupoInduccion g WHERE g.cupoMinimo = :cupoMinimo"),
    @NamedQuery(name = "GrupoInduccion.findByDescripcionGrupoinduccion", query = "SELECT g FROM GrupoInduccion g WHERE g.descripcionGrupoinduccion = :descripcionGrupoinduccion"),
    @NamedQuery(name = "GrupoInduccion.findByLugar", query = "SELECT g FROM GrupoInduccion g WHERE g.lugar = :lugar"),
    @NamedQuery(name = "GrupoInduccion.findByTipoFecha", query = "SELECT g FROM GrupoInduccion g WHERE g.tipoFecha = :tipoFecha")})
public class GrupoInduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_grupoinduccion")
    private Long idGrupoinduccion;
    @Column(name = "cupo_maximo")
    private Integer cupoMaximo;
    @Column(name = "cupo_minimo")
    private Integer cupoMinimo;
    @Column(name = "descripcion_grupoinduccion")
    private String descripcionGrupoinduccion;
    @Column(name = "lugar")
    private String lugar;
    @Column(name = "tipo_fecha")
    private String tipoFecha;
    @OneToMany(mappedBy = "idGrupoinduccion")
    private List<AsignacionGrupoinduccion> asignacionGrupoinduccionList;
    @OneToMany(mappedBy = "idGrupoinduccion")
    private List<FechaInduccion> fechaInduccionList;
    @JoinColumn(name = "id_plantel", referencedColumnName = "id_plantel")
    @ManyToOne
    private Plantel idPlantel;
    @JoinColumn(name = "id_periodo", referencedColumnName = "id_periodo")
    @ManyToOne
    private Periodo idPeriodo;
    @JoinColumn(name = "responsable", referencedColumnName = "id_asesor")
    @ManyToOne
    private Asesor responsable;

    public GrupoInduccion() {
    }

    public GrupoInduccion(Long idGrupoinduccion) {
        this.idGrupoinduccion = idGrupoinduccion;
    }

    public Long getIdGrupoinduccion() {
        return idGrupoinduccion;
    }

    public void setIdGrupoinduccion(Long idGrupoinduccion) {
        this.idGrupoinduccion = idGrupoinduccion;
    }

    public Integer getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(Integer cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public Integer getCupoMinimo() {
        return cupoMinimo;
    }

    public void setCupoMinimo(Integer cupoMinimo) {
        this.cupoMinimo = cupoMinimo;
    }

    public String getDescripcionGrupoinduccion() {
        return descripcionGrupoinduccion;
    }

    public void setDescripcionGrupoinduccion(String descripcionGrupoinduccion) {
        this.descripcionGrupoinduccion = descripcionGrupoinduccion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getTipoFecha() {
        return tipoFecha;
    }

    public void setTipoFecha(String tipoFecha) {
        this.tipoFecha = tipoFecha;
    }

    public List<AsignacionGrupoinduccion> getAsignacionGrupoinduccionList() {
        return asignacionGrupoinduccionList;
    }

    public void setAsignacionGrupoinduccionList(List<AsignacionGrupoinduccion> asignacionGrupoinduccionList) {
        this.asignacionGrupoinduccionList = asignacionGrupoinduccionList;
    }

    public List<FechaInduccion> getFechaInduccionList() {
        return fechaInduccionList;
    }

    public void setFechaInduccionList(List<FechaInduccion> fechaInduccionList) {
        this.fechaInduccionList = fechaInduccionList;
    }

    public Plantel getIdPlantel() {
        return idPlantel;
    }

    public void setIdPlantel(Plantel idPlantel) {
        this.idPlantel = idPlantel;
    }

    public Periodo getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Periodo idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Asesor getResponsable() {
        return responsable;
    }

    public void setResponsable(Asesor responsable) {
        this.responsable = responsable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupoinduccion != null ? idGrupoinduccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoInduccion)) {
            return false;
        }
        GrupoInduccion other = (GrupoInduccion) object;
        if ((this.idGrupoinduccion == null && other.idGrupoinduccion != null) || (this.idGrupoinduccion != null && !this.idGrupoinduccion.equals(other.idGrupoinduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.GrupoInduccion[ idGrupoinduccion=" + idGrupoinduccion + " ]";
    }
    
}
