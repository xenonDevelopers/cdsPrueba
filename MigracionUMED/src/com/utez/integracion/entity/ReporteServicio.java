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
@Table(name = "reporte_servicio")
@NamedQueries({
    @NamedQuery(name = "ReporteServicio.findAll", query = "SELECT r FROM ReporteServicio r"),
    @NamedQuery(name = "ReporteServicio.findByIdReporteservicio", query = "SELECT r FROM ReporteServicio r WHERE r.idReporteservicio = :idReporteservicio"),
    @NamedQuery(name = "ReporteServicio.findByNumeroReporte", query = "SELECT r FROM ReporteServicio r WHERE r.numeroReporte = :numeroReporte"),
    @NamedQuery(name = "ReporteServicio.findByFechaReporte", query = "SELECT r FROM ReporteServicio r WHERE r.fechaReporte = :fechaReporte")})
public class ReporteServicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reporteservicio")
    private Long idReporteservicio;
    @Column(name = "numero_reporte")
    private Integer numeroReporte;
    @Column(name = "fecha_reporte")
    @Temporal(TemporalType.DATE)
    private Date fechaReporte;
    @JoinColumn(name = "id_serviciosocial", referencedColumnName = "id_serviciosocial")
    @ManyToOne
    private ServicioSocial idServiciosocial;

    public ReporteServicio() {
    }

    public ReporteServicio(Long idReporteservicio) {
        this.idReporteservicio = idReporteservicio;
    }

    public Long getIdReporteservicio() {
        return idReporteservicio;
    }

    public void setIdReporteservicio(Long idReporteservicio) {
        this.idReporteservicio = idReporteservicio;
    }

    public Integer getNumeroReporte() {
        return numeroReporte;
    }

    public void setNumeroReporte(Integer numeroReporte) {
        this.numeroReporte = numeroReporte;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public ServicioSocial getIdServiciosocial() {
        return idServiciosocial;
    }

    public void setIdServiciosocial(ServicioSocial idServiciosocial) {
        this.idServiciosocial = idServiciosocial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReporteservicio != null ? idReporteservicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReporteServicio)) {
            return false;
        }
        ReporteServicio other = (ReporteServicio) object;
        if ((this.idReporteservicio == null && other.idReporteservicio != null) || (this.idReporteservicio != null && !this.idReporteservicio.equals(other.idReporteservicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.ReporteServicio[ idReporteservicio=" + idReporteservicio + " ]";
    }
    
}
