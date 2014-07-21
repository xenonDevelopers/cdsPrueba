/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "historico_calendarioasignatura")
@NamedQueries({
    @NamedQuery(name = "HistoricoCalendarioasignatura.findAll", query = "SELECT h FROM HistoricoCalendarioasignatura h"),
    @NamedQuery(name = "HistoricoCalendarioasignatura.findByIdHistoricocalendarioasignatura", query = "SELECT h FROM HistoricoCalendarioasignatura h WHERE h.idHistoricocalendarioasignatura = :idHistoricocalendarioasignatura"),
    @NamedQuery(name = "HistoricoCalendarioasignatura.findByIdAsignatura", query = "SELECT h FROM HistoricoCalendarioasignatura h WHERE h.idAsignatura = :idAsignatura"),
    @NamedQuery(name = "HistoricoCalendarioasignatura.findByBloque", query = "SELECT h FROM HistoricoCalendarioasignatura h WHERE h.bloque = :bloque"),
    @NamedQuery(name = "HistoricoCalendarioasignatura.findByIdHorario", query = "SELECT h FROM HistoricoCalendarioasignatura h WHERE h.idHorario = :idHorario"),
    @NamedQuery(name = "HistoricoCalendarioasignatura.findByFechaModificacion", query = "SELECT h FROM HistoricoCalendarioasignatura h WHERE h.fechaModificacion = :fechaModificacion")})
public class HistoricoCalendarioasignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historicocalendarioasignatura")
    private Long idHistoricocalendarioasignatura;
    @Column(name = "id_asignatura")
    private BigInteger idAsignatura;
    @Column(name = "bloque")
    private Integer bloque;
    @Column(name = "id_horario")
    private BigInteger idHorario;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "id_calendarioasignatura", referencedColumnName = "id_calendarioasignatura")
    @ManyToOne
    private CalendarioAsignatura idCalendarioasignatura;

    public HistoricoCalendarioasignatura() {
    }

    public HistoricoCalendarioasignatura(Long idHistoricocalendarioasignatura) {
        this.idHistoricocalendarioasignatura = idHistoricocalendarioasignatura;
    }

    public Long getIdHistoricocalendarioasignatura() {
        return idHistoricocalendarioasignatura;
    }

    public void setIdHistoricocalendarioasignatura(Long idHistoricocalendarioasignatura) {
        this.idHistoricocalendarioasignatura = idHistoricocalendarioasignatura;
    }

    public BigInteger getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(BigInteger idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public Integer getBloque() {
        return bloque;
    }

    public void setBloque(Integer bloque) {
        this.bloque = bloque;
    }

    public BigInteger getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(BigInteger idHorario) {
        this.idHorario = idHorario;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public CalendarioAsignatura getIdCalendarioasignatura() {
        return idCalendarioasignatura;
    }

    public void setIdCalendarioasignatura(CalendarioAsignatura idCalendarioasignatura) {
        this.idCalendarioasignatura = idCalendarioasignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistoricocalendarioasignatura != null ? idHistoricocalendarioasignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoCalendarioasignatura)) {
            return false;
        }
        HistoricoCalendarioasignatura other = (HistoricoCalendarioasignatura) object;
        if ((this.idHistoricocalendarioasignatura == null && other.idHistoricocalendarioasignatura != null) || (this.idHistoricocalendarioasignatura != null && !this.idHistoricocalendarioasignatura.equals(other.idHistoricocalendarioasignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.HistoricoCalendarioasignatura[ idHistoricocalendarioasignatura=" + idHistoricocalendarioasignatura + " ]";
    }
    
}
