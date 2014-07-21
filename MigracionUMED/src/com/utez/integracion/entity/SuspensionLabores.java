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
@Table(name = "suspension_labores")
@NamedQueries({
    @NamedQuery(name = "SuspensionLabores.findAll", query = "SELECT s FROM SuspensionLabores s"),
    @NamedQuery(name = "SuspensionLabores.findByIdSuspensionlabores", query = "SELECT s FROM SuspensionLabores s WHERE s.idSuspensionlabores = :idSuspensionlabores"),
    @NamedQuery(name = "SuspensionLabores.findByFechaSuspension", query = "SELECT s FROM SuspensionLabores s WHERE s.fechaSuspension = :fechaSuspension")})
public class SuspensionLabores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_suspensionlabores")
    private Long idSuspensionlabores;
    @Column(name = "fecha_suspension")
    @Temporal(TemporalType.DATE)
    private Date fechaSuspension;
    @ManyToMany(mappedBy = "suspensionLaboresList")
    private List<Plantel> plantelList;
    @JoinColumn(name = "id_calendariorectoria", referencedColumnName = "id_calendariorectoria")
    @ManyToOne
    private CalendarioRectoria idCalendariorectoria;

    public SuspensionLabores() {
    }

    public SuspensionLabores(Long idSuspensionlabores) {
        this.idSuspensionlabores = idSuspensionlabores;
    }

    public Long getIdSuspensionlabores() {
        return idSuspensionlabores;
    }

    public void setIdSuspensionlabores(Long idSuspensionlabores) {
        this.idSuspensionlabores = idSuspensionlabores;
    }

    public Date getFechaSuspension() {
        return fechaSuspension;
    }

    public void setFechaSuspension(Date fechaSuspension) {
        this.fechaSuspension = fechaSuspension;
    }

    public List<Plantel> getPlantelList() {
        return plantelList;
    }

    public void setPlantelList(List<Plantel> plantelList) {
        this.plantelList = plantelList;
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
        hash += (idSuspensionlabores != null ? idSuspensionlabores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SuspensionLabores)) {
            return false;
        }
        SuspensionLabores other = (SuspensionLabores) object;
        if ((this.idSuspensionlabores == null && other.idSuspensionlabores != null) || (this.idSuspensionlabores != null && !this.idSuspensionlabores.equals(other.idSuspensionlabores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.SuspensionLabores[ idSuspensionlabores=" + idSuspensionlabores + " ]";
    }
    
}
