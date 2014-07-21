/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "posgrado_solicitudautorizacion")
@NamedQueries({
    @NamedQuery(name = "PosgradoSolicitudautorizacion.findAll", query = "SELECT p FROM PosgradoSolicitudautorizacion p"),
    @NamedQuery(name = "PosgradoSolicitudautorizacion.findByIdSolicitudtitulacion", query = "SELECT p FROM PosgradoSolicitudautorizacion p WHERE p.idSolicitudtitulacion = :idSolicitudtitulacion")})
public class PosgradoSolicitudautorizacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_solicitudtitulacion")
    private Long idSolicitudtitulacion;
    @JoinColumn(name = "id_solicitudtitulacion", referencedColumnName = "id_solicitudtitulacion", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private SolicitudTitulacion solicitudTitulacion;
    @JoinColumn(name = "id_planestudiosexterno", referencedColumnName = "id_planestudiosexterno")
    @ManyToOne
    private PlanEstudiosexterno idPlanestudiosexterno;

    public PosgradoSolicitudautorizacion() {
    }

    public PosgradoSolicitudautorizacion(Long idSolicitudtitulacion) {
        this.idSolicitudtitulacion = idSolicitudtitulacion;
    }

    public Long getIdSolicitudtitulacion() {
        return idSolicitudtitulacion;
    }

    public void setIdSolicitudtitulacion(Long idSolicitudtitulacion) {
        this.idSolicitudtitulacion = idSolicitudtitulacion;
    }

    public SolicitudTitulacion getSolicitudTitulacion() {
        return solicitudTitulacion;
    }

    public void setSolicitudTitulacion(SolicitudTitulacion solicitudTitulacion) {
        this.solicitudTitulacion = solicitudTitulacion;
    }

    public PlanEstudiosexterno getIdPlanestudiosexterno() {
        return idPlanestudiosexterno;
    }

    public void setIdPlanestudiosexterno(PlanEstudiosexterno idPlanestudiosexterno) {
        this.idPlanestudiosexterno = idPlanestudiosexterno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSolicitudtitulacion != null ? idSolicitudtitulacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PosgradoSolicitudautorizacion)) {
            return false;
        }
        PosgradoSolicitudautorizacion other = (PosgradoSolicitudautorizacion) object;
        if ((this.idSolicitudtitulacion == null && other.idSolicitudtitulacion != null) || (this.idSolicitudtitulacion != null && !this.idSolicitudtitulacion.equals(other.idSolicitudtitulacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.PosgradoSolicitudautorizacion[ idSolicitudtitulacion=" + idSolicitudtitulacion + " ]";
    }
    
}
