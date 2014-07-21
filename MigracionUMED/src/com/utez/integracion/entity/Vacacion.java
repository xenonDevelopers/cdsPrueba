/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Plantel;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
@Table(name = "vacacion")
@NamedQueries({
    @NamedQuery(name = "Vacacion.findAll", query = "SELECT v FROM Vacacion v"),
    @NamedQuery(name = "Vacacion.findByIdVacacion", query = "SELECT v FROM Vacacion v WHERE v.idVacacion = :idVacacion"),
    @NamedQuery(name = "Vacacion.findByFechaInicio", query = "SELECT v FROM Vacacion v WHERE v.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Vacacion.findByFechaFin", query = "SELECT v FROM Vacacion v WHERE v.fechaFin = :fechaFin")})
public class Vacacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_vacacion")
    private Long idVacacion;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @ManyToMany(mappedBy = "vacacionList")
    private List<Plantel> plantelList;
    @JoinColumn(name = "id_tipovacacion", referencedColumnName = "id_tipovacacion")
    @ManyToOne
    private TipoVacacion idTipovacacion;
    @JoinColumn(name = "id_calendariorectoria", referencedColumnName = "id_calendariorectoria")
    @ManyToOne
    private CalendarioRectoria idCalendariorectoria;

    public Vacacion() {
    }

    public Vacacion(Long idVacacion) {
        this.idVacacion = idVacacion;
    }

    public Long getIdVacacion() {
        return idVacacion;
    }

    public void setIdVacacion(Long idVacacion) {
        this.idVacacion = idVacacion;
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

    public List<Plantel> getPlantelList() {
        return plantelList;
    }

    public void setPlantelList(List<Plantel> plantelList) {
        this.plantelList = plantelList;
    }

    public TipoVacacion getIdTipovacacion() {
        return idTipovacacion;
    }

    public void setIdTipovacacion(TipoVacacion idTipovacacion) {
        this.idTipovacacion = idTipovacacion;
    }

    public CalendarioRectoria getIdCalendariorectoria() {
        return idCalendariorectoria;
    }

    public void setIdCalendariorectoria(CalendarioRectoria idCalendariorectoria) {
        this.idCalendariorectoria = idCalendariorectoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVacacion != null ? idVacacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vacacion)) {
            return false;
        }
        Vacacion other = (Vacacion) object;
        if ((this.idVacacion == null && other.idVacacion != null) || (this.idVacacion != null && !this.idVacacion.equals(other.idVacacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Vacacion[ idVacacion=" + idVacacion + " ]";
    }
    
}
