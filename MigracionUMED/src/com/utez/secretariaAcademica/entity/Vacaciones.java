/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.entity;

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
@Table(name = "vacaciones")
@NamedQueries({
    @NamedQuery(name = "Vacaciones.findAll", query = "SELECT v FROM Vacaciones v"),
    @NamedQuery(name = "Vacaciones.findByIdvacaciones", query = "SELECT v FROM Vacaciones v WHERE v.idvacaciones = :idvacaciones"),
    @NamedQuery(name = "Vacaciones.findByTipovacacion", query = "SELECT v FROM Vacaciones v WHERE v.tipovacacion = :tipovacacion"),
    @NamedQuery(name = "Vacaciones.findByFechainicio", query = "SELECT v FROM Vacaciones v WHERE v.fechainicio = :fechainicio"),
    @NamedQuery(name = "Vacaciones.findByFechafin", query = "SELECT v FROM Vacaciones v WHERE v.fechafin = :fechafin")})
public class Vacaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvacaciones")
    private Integer idvacaciones;
    @Column(name = "tipovacacion")
    private String tipovacacion;
    @Column(name = "fechainicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Column(name = "fechafin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @JoinColumn(name = "idcalendariorectoria", referencedColumnName = "idcalendariorectoria")
    @ManyToOne
    private Calendariorectoria idcalendariorectoria;

    public Vacaciones() {
    }

    public Vacaciones(Integer idvacaciones) {
        this.idvacaciones = idvacaciones;
    }

    public Integer getIdvacaciones() {
        return idvacaciones;
    }

    public void setIdvacaciones(Integer idvacaciones) {
        this.idvacaciones = idvacaciones;
    }

    public String getTipovacacion() {
        return tipovacacion;
    }

    public void setTipovacacion(String tipovacacion) {
        this.tipovacacion = tipovacacion;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Calendariorectoria getIdcalendariorectoria() {
        return idcalendariorectoria;
    }

    public void setIdcalendariorectoria(Calendariorectoria idcalendariorectoria) {
        this.idcalendariorectoria = idcalendariorectoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvacaciones != null ? idvacaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vacaciones)) {
            return false;
        }
        Vacaciones other = (Vacaciones) object;
        if ((this.idvacaciones == null && other.idvacaciones != null) || (this.idvacaciones != null && !this.idvacaciones.equals(other.idvacaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Vacaciones[ idvacaciones=" + idvacaciones + " ]";
    }
    
}
