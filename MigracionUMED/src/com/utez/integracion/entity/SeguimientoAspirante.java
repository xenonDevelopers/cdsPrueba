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
@Table(name = "seguimiento_aspirante")
@NamedQueries({
    @NamedQuery(name = "SeguimientoAspirante.findAll", query = "SELECT s FROM SeguimientoAspirante s"),
    @NamedQuery(name = "SeguimientoAspirante.findByIdSeguimiento", query = "SELECT s FROM SeguimientoAspirante s WHERE s.idSeguimiento = :idSeguimiento"),
    @NamedQuery(name = "SeguimientoAspirante.findByFechaSeguimiento", query = "SELECT s FROM SeguimientoAspirante s WHERE s.fechaSeguimiento = :fechaSeguimiento"),
    @NamedQuery(name = "SeguimientoAspirante.findByObservaciones", query = "SELECT s FROM SeguimientoAspirante s WHERE s.observaciones = :observaciones")})
public class SeguimientoAspirante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_seguimiento")
    private Long idSeguimiento;
    @Column(name = "fecha_seguimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaSeguimiento;
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_aspirante", referencedColumnName = "id_aspirante")
    @ManyToOne
    private Aspirante idAspirante;

    public SeguimientoAspirante() {
    }

    public SeguimientoAspirante(Long idSeguimiento) {
        this.idSeguimiento = idSeguimiento;
    }

    public Long getIdSeguimiento() {
        return idSeguimiento;
    }

    public void setIdSeguimiento(Long idSeguimiento) {
        this.idSeguimiento = idSeguimiento;
    }

    public Date getFechaSeguimiento() {
        return fechaSeguimiento;
    }

    public void setFechaSeguimiento(Date fechaSeguimiento) {
        this.fechaSeguimiento = fechaSeguimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Aspirante getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(Aspirante idAspirante) {
        this.idAspirante = idAspirante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSeguimiento != null ? idSeguimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeguimientoAspirante)) {
            return false;
        }
        SeguimientoAspirante other = (SeguimientoAspirante) object;
        if ((this.idSeguimiento == null && other.idSeguimiento != null) || (this.idSeguimiento != null && !this.idSeguimiento.equals(other.idSeguimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.SeguimientoAspirante[ idSeguimiento=" + idSeguimiento + " ]";
    }
    
}
