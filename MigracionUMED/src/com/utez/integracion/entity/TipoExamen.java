/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "tipo_examen")
@NamedQueries({
    @NamedQuery(name = "TipoExamen.findAll", query = "SELECT t FROM TipoExamen t"),
    @NamedQuery(name = "TipoExamen.findByIdTipoexamen", query = "SELECT t FROM TipoExamen t WHERE t.idTipoexamen = :idTipoexamen"),
    @NamedQuery(name = "TipoExamen.findByDescripcionTipoexamen", query = "SELECT t FROM TipoExamen t WHERE t.descripcionTipoexamen = :descripcionTipoexamen"),
    @NamedQuery(name = "TipoExamen.findByOrden", query = "SELECT t FROM TipoExamen t WHERE t.orden = :orden")})
public class TipoExamen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipoexamen")
    private Long idTipoexamen;
    @Column(name = "descripcion_tipoexamen")
    private String descripcionTipoexamen;
    @Column(name = "orden")
    private Integer orden;
    @OneToMany(mappedBy = "idTipoexamen")
    private List<ExamenExtemporaneo> examenExtemporaneoList;
    @OneToMany(mappedBy = "idTipoexamen")
    private List<Acta> actaList;
    @OneToMany(mappedBy = "idTipoexamen")
    private List<AsignacionEvaluacion> asignacionEvaluacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoExamen")
    private List<Calificacion> calificacionList;
    @OneToMany(mappedBy = "idTipoexamen")
    private List<FechaExamen> fechaExamenList;
    @OneToMany(mappedBy = "idTipoexamen")
    private List<ExamenImpreso> examenImpresoList;
    @OneToMany(mappedBy = "idTipoexamen")
    private List<ExamenIndividual> examenIndividualList;
    @OneToMany(mappedBy = "idTipoexamen")
    private List<HistoricoCalificacion> historicoCalificacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoExamen")
    private List<FechaExamenopcionc> fechaExamenopcioncList;

    public TipoExamen() {
    }

    public TipoExamen(Long idTipoexamen) {
        this.idTipoexamen = idTipoexamen;
    }

    public Long getIdTipoexamen() {
        return idTipoexamen;
    }

    public void setIdTipoexamen(Long idTipoexamen) {
        this.idTipoexamen = idTipoexamen;
    }

    public String getDescripcionTipoexamen() {
        return descripcionTipoexamen;
    }

    public void setDescripcionTipoexamen(String descripcionTipoexamen) {
        this.descripcionTipoexamen = descripcionTipoexamen;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public List<ExamenExtemporaneo> getExamenExtemporaneoList() {
        return examenExtemporaneoList;
    }

    public void setExamenExtemporaneoList(List<ExamenExtemporaneo> examenExtemporaneoList) {
        this.examenExtemporaneoList = examenExtemporaneoList;
    }

    public List<Acta> getActaList() {
        return actaList;
    }

    public void setActaList(List<Acta> actaList) {
        this.actaList = actaList;
    }

    public List<AsignacionEvaluacion> getAsignacionEvaluacionList() {
        return asignacionEvaluacionList;
    }

    public void setAsignacionEvaluacionList(List<AsignacionEvaluacion> asignacionEvaluacionList) {
        this.asignacionEvaluacionList = asignacionEvaluacionList;
    }

    public List<Calificacion> getCalificacionList() {
        return calificacionList;
    }

    public void setCalificacionList(List<Calificacion> calificacionList) {
        this.calificacionList = calificacionList;
    }

    public List<FechaExamen> getFechaExamenList() {
        return fechaExamenList;
    }

    public void setFechaExamenList(List<FechaExamen> fechaExamenList) {
        this.fechaExamenList = fechaExamenList;
    }

    public List<ExamenImpreso> getExamenImpresoList() {
        return examenImpresoList;
    }

    public void setExamenImpresoList(List<ExamenImpreso> examenImpresoList) {
        this.examenImpresoList = examenImpresoList;
    }

    public List<ExamenIndividual> getExamenIndividualList() {
        return examenIndividualList;
    }

    public void setExamenIndividualList(List<ExamenIndividual> examenIndividualList) {
        this.examenIndividualList = examenIndividualList;
    }

    public List<HistoricoCalificacion> getHistoricoCalificacionList() {
        return historicoCalificacionList;
    }

    public void setHistoricoCalificacionList(List<HistoricoCalificacion> historicoCalificacionList) {
        this.historicoCalificacionList = historicoCalificacionList;
    }

    public List<FechaExamenopcionc> getFechaExamenopcioncList() {
        return fechaExamenopcioncList;
    }

    public void setFechaExamenopcioncList(List<FechaExamenopcionc> fechaExamenopcioncList) {
        this.fechaExamenopcioncList = fechaExamenopcioncList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoexamen != null ? idTipoexamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoExamen)) {
            return false;
        }
        TipoExamen other = (TipoExamen) object;
        if ((this.idTipoexamen == null && other.idTipoexamen != null) || (this.idTipoexamen != null && !this.idTipoexamen.equals(other.idTipoexamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoExamen[ idTipoexamen=" + idTipoexamen + " ]";
    }
    
}
