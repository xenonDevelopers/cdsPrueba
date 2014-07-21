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
@Table(name = "horario")
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h"),
    @NamedQuery(name = "Horario.findByIdHorario", query = "SELECT h FROM Horario h WHERE h.idHorario = :idHorario"),
    @NamedQuery(name = "Horario.findByInicio", query = "SELECT h FROM Horario h WHERE h.inicio = :inicio"),
    @NamedQuery(name = "Horario.findByFin", query = "SELECT h FROM Horario h WHERE h.fin = :fin")})
public class Horario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_horario")
    private Long idHorario;
    @Column(name = "inicio")
    @Temporal(TemporalType.TIME)
    private Date inicio;
    @Column(name = "fin")
    @Temporal(TemporalType.TIME)
    private Date fin;
    @OneToMany(mappedBy = "idHorario")
    private List<FechaInduccion> fechaInduccionList;
    @OneToMany(mappedBy = "idHorario")
    private List<CalendarioAsignatura> calendarioAsignaturaList;

    public Horario() {
    }

    public Horario(Long idHorario) {
        this.idHorario = idHorario;
    }

    public Long getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Long idHorario) {
        this.idHorario = idHorario;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public List<FechaInduccion> getFechaInduccionList() {
        return fechaInduccionList;
    }

    public void setFechaInduccionList(List<FechaInduccion> fechaInduccionList) {
        this.fechaInduccionList = fechaInduccionList;
    }

    public List<CalendarioAsignatura> getCalendarioAsignaturaList() {
        return calendarioAsignaturaList;
    }

    public void setCalendarioAsignaturaList(List<CalendarioAsignatura> calendarioAsignaturaList) {
        this.calendarioAsignaturaList = calendarioAsignaturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHorario != null ? idHorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.idHorario == null && other.idHorario != null) || (this.idHorario != null && !this.idHorario.equals(other.idHorario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Horario[ idHorario=" + idHorario + " ]";
    }
    
}
