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
@Table(name = "historico_asesorasignatura")
@NamedQueries({
    @NamedQuery(name = "HistoricoAsesorasignatura.findAll", query = "SELECT h FROM HistoricoAsesorasignatura h"),
    @NamedQuery(name = "HistoricoAsesorasignatura.findByIdHistoricoidasignaturaasesor", query = "SELECT h FROM HistoricoAsesorasignatura h WHERE h.idHistoricoidasignaturaasesor = :idHistoricoidasignaturaasesor"),
    @NamedQuery(name = "HistoricoAsesorasignatura.findByIdAsesor", query = "SELECT h FROM HistoricoAsesorasignatura h WHERE h.idAsesor = :idAsesor"),
    @NamedQuery(name = "HistoricoAsesorasignatura.findByFechaModificacion", query = "SELECT h FROM HistoricoAsesorasignatura h WHERE h.fechaModificacion = :fechaModificacion")})
public class HistoricoAsesorasignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historicoidasignaturaasesor")
    private Long idHistoricoidasignaturaasesor;
    @Column(name = "id_asesor")
    private BigInteger idAsesor;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "id_calendarioasignatura", referencedColumnName = "id_calendarioasignatura")
    @ManyToOne
    private CalendarioAsignatura idCalendarioasignatura;

    public HistoricoAsesorasignatura() {
    }

    public HistoricoAsesorasignatura(Long idHistoricoidasignaturaasesor) {
        this.idHistoricoidasignaturaasesor = idHistoricoidasignaturaasesor;
    }

    public Long getIdHistoricoidasignaturaasesor() {
        return idHistoricoidasignaturaasesor;
    }

    public void setIdHistoricoidasignaturaasesor(Long idHistoricoidasignaturaasesor) {
        this.idHistoricoidasignaturaasesor = idHistoricoidasignaturaasesor;
    }

    public BigInteger getIdAsesor() {
        return idAsesor;
    }

    public void setIdAsesor(BigInteger idAsesor) {
        this.idAsesor = idAsesor;
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
        hash += (idHistoricoidasignaturaasesor != null ? idHistoricoidasignaturaasesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoAsesorasignatura)) {
            return false;
        }
        HistoricoAsesorasignatura other = (HistoricoAsesorasignatura) object;
        if ((this.idHistoricoidasignaturaasesor == null && other.idHistoricoidasignaturaasesor != null) || (this.idHistoricoidasignaturaasesor != null && !this.idHistoricoidasignaturaasesor.equals(other.idHistoricoidasignaturaasesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.HistoricoAsesorasignatura[ idHistoricoidasignaturaasesor=" + idHistoricoidasignaturaasesor + " ]";
    }
    
}
