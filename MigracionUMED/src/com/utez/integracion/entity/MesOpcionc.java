/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "mes_opcionc")
@NamedQueries({
    @NamedQuery(name = "MesOpcionc.findAll", query = "SELECT m FROM MesOpcionc m"),
    @NamedQuery(name = "MesOpcionc.findByIdMesopcionc", query = "SELECT m FROM MesOpcionc m WHERE m.idMesopcionc = :idMesopcionc"),
    @NamedQuery(name = "MesOpcionc.findByFechaInicio", query = "SELECT m FROM MesOpcionc m WHERE m.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "MesOpcionc.findByFechaFin", query = "SELECT m FROM MesOpcionc m WHERE m.fechaFin = :fechaFin")})
public class MesOpcionc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mesopcionc")
    private Long idMesopcionc;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @OneToMany(mappedBy = "idMesopcionc")
    private List<AsignaturaOpcionc> asignaturaOpcioncList;

    public MesOpcionc() {
    }

    public MesOpcionc(Long idMesopcionc) {
        this.idMesopcionc = idMesopcionc;
    }

    public Long getIdMesopcionc() {
        return idMesopcionc;
    }

    public void setIdMesopcionc(Long idMesopcionc) {
        this.idMesopcionc = idMesopcionc;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<AsignaturaOpcionc> getAsignaturaOpcioncList() {
        return asignaturaOpcioncList;
    }

    public void setAsignaturaOpcioncList(List<AsignaturaOpcionc> asignaturaOpcioncList) {
        this.asignaturaOpcioncList = asignaturaOpcioncList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMesopcionc != null ? idMesopcionc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MesOpcionc)) {
            return false;
        }
        MesOpcionc other = (MesOpcionc) object;
        if ((this.idMesopcionc == null && other.idMesopcionc != null) || (this.idMesopcionc != null && !this.idMesopcionc.equals(other.idMesopcionc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.MesOpcionc[ idMesopcionc=" + idMesopcionc + " ]";
    }
    
}
