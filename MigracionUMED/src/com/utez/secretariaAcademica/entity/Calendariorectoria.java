/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.entity;

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
@Table(name = "calendariorectoria")
@NamedQueries({
    @NamedQuery(name = "Calendariorectoria.findAll", query = "SELECT c FROM Calendariorectoria c"),
    @NamedQuery(name = "Calendariorectoria.findByIdcalendariorectoria", query = "SELECT c FROM Calendariorectoria c WHERE c.idcalendariorectoria = :idcalendariorectoria"),
    @NamedQuery(name = "Calendariorectoria.findByPeriodo", query = "SELECT c FROM Calendariorectoria c WHERE c.periodo = :periodo")})
public class Calendariorectoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcalendariorectoria")
    private Integer idcalendariorectoria;
    @Column(name = "periodo")
    private String periodo;
    @OneToMany(mappedBy = "idcalendariorectoria")
    private List<Vacaciones> vacacionesList;
    @JoinColumn(name = "idplantel", referencedColumnName = "idplantel")
    @ManyToOne
    private Plantel idplantel;
    @OneToMany(mappedBy = "idcalendariorectoria")
    private List<Suspensionlabores> suspensionlaboresList;

    public Calendariorectoria() {
    }

    public Calendariorectoria(Integer idcalendariorectoria) {
        this.idcalendariorectoria = idcalendariorectoria;
    }

    public Integer getIdcalendariorectoria() {
        return idcalendariorectoria;
    }

    public void setIdcalendariorectoria(Integer idcalendariorectoria) {
        this.idcalendariorectoria = idcalendariorectoria;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public List<Vacaciones> getVacacionesList() {
        return vacacionesList;
    }

    public void setVacacionesList(List<Vacaciones> vacacionesList) {
        this.vacacionesList = vacacionesList;
    }

    public Plantel getIdplantel() {
        return idplantel;
    }

    public void setIdplantel(Plantel idplantel) {
        this.idplantel = idplantel;
    }

    public List<Suspensionlabores> getSuspensionlaboresList() {
        return suspensionlaboresList;
    }

    public void setSuspensionlaboresList(List<Suspensionlabores> suspensionlaboresList) {
        this.suspensionlaboresList = suspensionlaboresList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcalendariorectoria != null ? idcalendariorectoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calendariorectoria)) {
            return false;
        }
        Calendariorectoria other = (Calendariorectoria) object;
        if ((this.idcalendariorectoria == null && other.idcalendariorectoria != null) || (this.idcalendariorectoria != null && !this.idcalendariorectoria.equals(other.idcalendariorectoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Calendariorectoria[ idcalendariorectoria=" + idcalendariorectoria + " ]";
    }
    
}
