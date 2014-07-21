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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "programacion_cancelada")
@NamedQueries({
    @NamedQuery(name = "ProgramacionCancelada.findAll", query = "SELECT p FROM ProgramacionCancelada p"),
    @NamedQuery(name = "ProgramacionCancelada.findByIdProgramacion", query = "SELECT p FROM ProgramacionCancelada p WHERE p.idProgramacion = :idProgramacion"),
    @NamedQuery(name = "ProgramacionCancelada.findByFechaCancelacion", query = "SELECT p FROM ProgramacionCancelada p WHERE p.fechaCancelacion = :fechaCancelacion"),
    @NamedQuery(name = "ProgramacionCancelada.findByMotivoCancelacion", query = "SELECT p FROM ProgramacionCancelada p WHERE p.motivoCancelacion = :motivoCancelacion")})
public class ProgramacionCancelada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_programacion")
    private Long idProgramacion;
    @Column(name = "fecha_cancelacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCancelacion;
    @Column(name = "motivo_cancelacion")
    private String motivoCancelacion;
    @JoinColumn(name = "id_programacion", referencedColumnName = "id_programacion", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Programacion programacion;

    public ProgramacionCancelada() {
    }

    public ProgramacionCancelada(Long idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public Long getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(Long idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public String getMotivoCancelacion() {
        return motivoCancelacion;
    }

    public void setMotivoCancelacion(String motivoCancelacion) {
        this.motivoCancelacion = motivoCancelacion;
    }

    public Programacion getProgramacion() {
        return programacion;
    }

    public void setProgramacion(Programacion programacion) {
        this.programacion = programacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProgramacion != null ? idProgramacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramacionCancelada)) {
            return false;
        }
        ProgramacionCancelada other = (ProgramacionCancelada) object;
        if ((this.idProgramacion == null && other.idProgramacion != null) || (this.idProgramacion != null && !this.idProgramacion.equals(other.idProgramacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.ProgramacionCancelada[ idProgramacion=" + idProgramacion + " ]";
    }
    
}
