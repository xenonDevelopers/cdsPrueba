/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "asignacion_integradoragrupo")
@NamedQueries({
    @NamedQuery(name = "AsignacionIntegradoragrupo.findAll", query = "SELECT a FROM AsignacionIntegradoragrupo a"),
    @NamedQuery(name = "AsignacionIntegradoragrupo.findByIdAsignacionintegradoragrupo", query = "SELECT a FROM AsignacionIntegradoragrupo a WHERE a.idAsignacionintegradoragrupo = :idAsignacionintegradoragrupo"),
    @NamedQuery(name = "AsignacionIntegradoragrupo.findByFechaAsignacion", query = "SELECT a FROM AsignacionIntegradoragrupo a WHERE a.fechaAsignacion = :fechaAsignacion")})
public class AsignacionIntegradoragrupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignacionintegradoragrupo")
    private Long idAsignacionintegradoragrupo;
    @Column(name = "fecha_asignacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAsignacion;
    @JoinColumn(name = "id_calendarioasignatura", referencedColumnName = "id_calendarioasignatura")
    @ManyToOne
    private CalendarioAsignatura idCalendarioasignatura;
    @JoinColumn(name = "id_actividadintegradora", referencedColumnName = "id_actividadintegradora")
    @ManyToOne
    private ActividadIntegradora idActividadintegradora;

    public AsignacionIntegradoragrupo() {
    }

    public AsignacionIntegradoragrupo(Long idAsignacionintegradoragrupo) {
        this.idAsignacionintegradoragrupo = idAsignacionintegradoragrupo;
    }

    public Long getIdAsignacionintegradoragrupo() {
        return idAsignacionintegradoragrupo;
    }

    public void setIdAsignacionintegradoragrupo(Long idAsignacionintegradoragrupo) {
        this.idAsignacionintegradoragrupo = idAsignacionintegradoragrupo;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public CalendarioAsignatura getIdCalendarioasignatura() {
        return idCalendarioasignatura;
    }

    public void setIdCalendarioasignatura(CalendarioAsignatura idCalendarioasignatura) {
        this.idCalendarioasignatura = idCalendarioasignatura;
    }

    public ActividadIntegradora getIdActividadintegradora() {
        return idActividadintegradora;
    }

    public void setIdActividadintegradora(ActividadIntegradora idActividadintegradora) {
        this.idActividadintegradora = idActividadintegradora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacionintegradoragrupo != null ? idAsignacionintegradoragrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionIntegradoragrupo)) {
            return false;
        }
        AsignacionIntegradoragrupo other = (AsignacionIntegradoragrupo) object;
        if ((this.idAsignacionintegradoragrupo == null && other.idAsignacionintegradoragrupo != null) || (this.idAsignacionintegradoragrupo != null && !this.idAsignacionintegradoragrupo.equals(other.idAsignacionintegradoragrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionIntegradoragrupo[ idAsignacionintegradoragrupo=" + idAsignacionintegradoragrupo + " ]";
    }
    
}
