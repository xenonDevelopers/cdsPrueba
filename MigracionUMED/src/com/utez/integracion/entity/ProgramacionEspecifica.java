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
@Table(name = "programacion_especifica")
@NamedQueries({
    @NamedQuery(name = "ProgramacionEspecifica.findAll", query = "SELECT p FROM ProgramacionEspecifica p"),
    @NamedQuery(name = "ProgramacionEspecifica.findByIdProgramacionasignatura", query = "SELECT p FROM ProgramacionEspecifica p WHERE p.idProgramacionasignatura = :idProgramacionasignatura")})
public class ProgramacionEspecifica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_programacionasignatura")
    private Long idProgramacionasignatura;
    @JoinColumn(name = "id_programacionasignatura", referencedColumnName = "id_programacionasignatura", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private ProgramacionAsignatura programacionAsignatura;
    @JoinColumn(name = "id_calendarioasignatura", referencedColumnName = "id_calendarioasignatura")
    @ManyToOne
    private CalendarioAsignatura idCalendarioasignatura;

    public ProgramacionEspecifica() {
    }

    public ProgramacionEspecifica(Long idProgramacionasignatura) {
        this.idProgramacionasignatura = idProgramacionasignatura;
    }

    public Long getIdProgramacionasignatura() {
        return idProgramacionasignatura;
    }

    public void setIdProgramacionasignatura(Long idProgramacionasignatura) {
        this.idProgramacionasignatura = idProgramacionasignatura;
    }

    public ProgramacionAsignatura getProgramacionAsignatura() {
        return programacionAsignatura;
    }

    public void setProgramacionAsignatura(ProgramacionAsignatura programacionAsignatura) {
        this.programacionAsignatura = programacionAsignatura;
    }

    public CalendarioAsignatura getIdCalendarioasignatura() {
        return idCalendarioasignatura;
    }

    public void setIdCalendarioasignatura(CalendarioAsignatura idCalendarioasignatura) {
        this.idCalendarioasignatura = idCalendarioasignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProgramacionasignatura != null ? idProgramacionasignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramacionEspecifica)) {
            return false;
        }
        ProgramacionEspecifica other = (ProgramacionEspecifica) object;
        if ((this.idProgramacionasignatura == null && other.idProgramacionasignatura != null) || (this.idProgramacionasignatura != null && !this.idProgramacionasignatura.equals(other.idProgramacionasignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.ProgramacionEspecifica[ idProgramacionasignatura=" + idProgramacionasignatura + " ]";
    }
    
}
