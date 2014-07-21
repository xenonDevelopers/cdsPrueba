/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "fecha_examenprogramado")
@NamedQueries({
    @NamedQuery(name = "FechaExamenprogramado.findAll", query = "SELECT f FROM FechaExamenprogramado f"),
    @NamedQuery(name = "FechaExamenprogramado.findByIdFechaExamen", query = "SELECT f FROM FechaExamenprogramado f WHERE f.idFechaExamen = :idFechaExamen"),
    @NamedQuery(name = "FechaExamenprogramado.findByFechaExamen", query = "SELECT f FROM FechaExamenprogramado f WHERE f.fechaExamen = :fechaExamen"),
    @NamedQuery(name = "FechaExamenprogramado.findByProgramado", query = "SELECT f FROM FechaExamenprogramado f WHERE f.programado = :programado")})
public class FechaExamenprogramado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_fecha_examen")
    private Long idFechaExamen;
    @Column(name = "fecha_examen")
    @Temporal(TemporalType.DATE)
    private Date fechaExamen;
    @Column(name = "programado")
    private Boolean programado;
    @OneToMany(mappedBy = "idFechaexamen")
    private List<AsignacionEncuestadocente> asignacionEncuestadocenteList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "fechaExamenprogramado")
    private VigenciaCalificacion vigenciaCalificacion;
    @OneToMany(mappedBy = "idFechaexamenprogramado")
    private List<AsignacionAplicador> asignacionAplicadorList;
    @JoinColumn(name = "id_fecha_examen", referencedColumnName = "id_fechaexamen", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private FechaExamen fechaExamen1;
    @OneToMany(mappedBy = "idFechaexamenprogramado")
    private List<EntregaExamen> entregaExamenList;

    public FechaExamenprogramado() {
    }

    public FechaExamenprogramado(Long idFechaExamen) {
        this.idFechaExamen = idFechaExamen;
    }

    public Long getIdFechaExamen() {
        return idFechaExamen;
    }

    public void setIdFechaExamen(Long idFechaExamen) {
        this.idFechaExamen = idFechaExamen;
    }

    public Date getFechaExamen() {
        return fechaExamen;
    }

    public void setFechaExamen(Date fechaExamen) {
        this.fechaExamen = fechaExamen;
    }

    public Boolean getProgramado() {
        return programado;
    }

    public void setProgramado(Boolean programado) {
        this.programado = programado;
    }

    public List<AsignacionEncuestadocente> getAsignacionEncuestadocenteList() {
        return asignacionEncuestadocenteList;
    }

    public void setAsignacionEncuestadocenteList(List<AsignacionEncuestadocente> asignacionEncuestadocenteList) {
        this.asignacionEncuestadocenteList = asignacionEncuestadocenteList;
    }

    public VigenciaCalificacion getVigenciaCalificacion() {
        return vigenciaCalificacion;
    }

    public void setVigenciaCalificacion(VigenciaCalificacion vigenciaCalificacion) {
        this.vigenciaCalificacion = vigenciaCalificacion;
    }

    public List<AsignacionAplicador> getAsignacionAplicadorList() {
        return asignacionAplicadorList;
    }

    public void setAsignacionAplicadorList(List<AsignacionAplicador> asignacionAplicadorList) {
        this.asignacionAplicadorList = asignacionAplicadorList;
    }

    public FechaExamen getFechaExamen1() {
        return fechaExamen1;
    }

    public void setFechaExamen1(FechaExamen fechaExamen1) {
        this.fechaExamen1 = fechaExamen1;
    }

    public List<EntregaExamen> getEntregaExamenList() {
        return entregaExamenList;
    }

    public void setEntregaExamenList(List<EntregaExamen> entregaExamenList) {
        this.entregaExamenList = entregaExamenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFechaExamen != null ? idFechaExamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FechaExamenprogramado)) {
            return false;
        }
        FechaExamenprogramado other = (FechaExamenprogramado) object;
        if ((this.idFechaExamen == null && other.idFechaExamen != null) || (this.idFechaExamen != null && !this.idFechaExamen.equals(other.idFechaExamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.FechaExamenprogramado[ idFechaExamen=" + idFechaExamen + " ]";
    }
    
}
