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
@Table(name = "historico_calificacion")
@NamedQueries({
    @NamedQuery(name = "HistoricoCalificacion.findAll", query = "SELECT h FROM HistoricoCalificacion h"),
    @NamedQuery(name = "HistoricoCalificacion.findByIdHistoricocalificacion", query = "SELECT h FROM HistoricoCalificacion h WHERE h.idHistoricocalificacion = :idHistoricocalificacion"),
    @NamedQuery(name = "HistoricoCalificacion.findByTrabajo", query = "SELECT h FROM HistoricoCalificacion h WHERE h.trabajo = :trabajo"),
    @NamedQuery(name = "HistoricoCalificacion.findByExamen", query = "SELECT h FROM HistoricoCalificacion h WHERE h.examen = :examen"),
    @NamedQuery(name = "HistoricoCalificacion.findByFechaRegistro", query = "SELECT h FROM HistoricoCalificacion h WHERE h.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "HistoricoCalificacion.findByObservaciones", query = "SELECT h FROM HistoricoCalificacion h WHERE h.observaciones = :observaciones")})
public class HistoricoCalificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historicocalificacion")
    private Long idHistoricocalificacion;
    @Column(name = "trabajo")
    private String trabajo;
    @Column(name = "examen")
    private String examen;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_tipoexamen", referencedColumnName = "id_tipoexamen")
    @ManyToOne
    private TipoExamen idTipoexamen;
    @JoinColumn(name = "id_esquemaalumnoasignatura", referencedColumnName = "id_esquemaalumnoasignatura")
    @ManyToOne
    private EsquemaAlumnoasignatura idEsquemaalumnoasignatura;

    public HistoricoCalificacion() {
    }

    public HistoricoCalificacion(Long idHistoricocalificacion) {
        this.idHistoricocalificacion = idHistoricocalificacion;
    }

    public Long getIdHistoricocalificacion() {
        return idHistoricocalificacion;
    }

    public void setIdHistoricocalificacion(Long idHistoricocalificacion) {
        this.idHistoricocalificacion = idHistoricocalificacion;
    }

    public String getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(String trabajo) {
        this.trabajo = trabajo;
    }

    public String getExamen() {
        return examen;
    }

    public void setExamen(String examen) {
        this.examen = examen;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public TipoExamen getIdTipoexamen() {
        return idTipoexamen;
    }

    public void setIdTipoexamen(TipoExamen idTipoexamen) {
        this.idTipoexamen = idTipoexamen;
    }

    public EsquemaAlumnoasignatura getIdEsquemaalumnoasignatura() {
        return idEsquemaalumnoasignatura;
    }

    public void setIdEsquemaalumnoasignatura(EsquemaAlumnoasignatura idEsquemaalumnoasignatura) {
        this.idEsquemaalumnoasignatura = idEsquemaalumnoasignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistoricocalificacion != null ? idHistoricocalificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoCalificacion)) {
            return false;
        }
        HistoricoCalificacion other = (HistoricoCalificacion) object;
        if ((this.idHistoricocalificacion == null && other.idHistoricocalificacion != null) || (this.idHistoricocalificacion != null && !this.idHistoricocalificacion.equals(other.idHistoricocalificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.HistoricoCalificacion[ idHistoricocalificacion=" + idHistoricocalificacion + " ]";
    }
    
}
