/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Periodo;
import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "calendario_rectoria")
@NamedQueries({
    @NamedQuery(name = "CalendarioRectoria.findAll", query = "SELECT c FROM CalendarioRectoria c"),
    @NamedQuery(name = "CalendarioRectoria.findByIdCalendariorectoria", query = "SELECT c FROM CalendarioRectoria c WHERE c.idCalendariorectoria = :idCalendariorectoria")})
public class CalendarioRectoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_calendariorectoria")
    private Long idCalendariorectoria;
    @OneToMany(mappedBy = "idCalendariorectoria")
    private List<Vacacion> vacacionList;
    @OneToMany(mappedBy = "idCalendariorectoria")
    private List<SuspensionLabores> suspensionLaboresList;
    @JoinColumn(name = "id_periodo", referencedColumnName = "id_periodo")
    @ManyToOne
    private Periodo idPeriodo;

    public CalendarioRectoria() {
    }

    public CalendarioRectoria(Long idCalendariorectoria) {
        this.idCalendariorectoria = idCalendariorectoria;
    }

    public Long getIdCalendariorectoria() {
        return idCalendariorectoria;
    }

    public void setIdCalendariorectoria(Long idCalendariorectoria) {
        this.idCalendariorectoria = idCalendariorectoria;
    }

    public List<Vacacion> getVacacionList() {
        return vacacionList;
    }

    public void setVacacionList(List<Vacacion> vacacionList) {
        this.vacacionList = vacacionList;
    }

    public List<SuspensionLabores> getSuspensionLaboresList() {
        return suspensionLaboresList;
    }

    public void setSuspensionLaboresList(List<SuspensionLabores> suspensionLaboresList) {
        this.suspensionLaboresList = suspensionLaboresList;
    }

    public Periodo getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Periodo idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCalendariorectoria != null ? idCalendariorectoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalendarioRectoria)) {
            return false;
        }
        CalendarioRectoria other = (CalendarioRectoria) object;
        if ((this.idCalendariorectoria == null && other.idCalendariorectoria != null) || (this.idCalendariorectoria != null && !this.idCalendariorectoria.equals(other.idCalendariorectoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.CalendarioRectoria[ idCalendariorectoria=" + idCalendariorectoria + " ]";
    }
    
}
