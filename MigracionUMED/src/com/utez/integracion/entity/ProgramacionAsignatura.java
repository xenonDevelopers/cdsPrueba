/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Asignatura;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "programacion_asignatura")
@NamedQueries({
    @NamedQuery(name = "ProgramacionAsignatura.findAll", query = "SELECT p FROM ProgramacionAsignatura p"),
    @NamedQuery(name = "ProgramacionAsignatura.findByIdProgramacionasignatura", query = "SELECT p FROM ProgramacionAsignatura p WHERE p.idProgramacionasignatura = :idProgramacionasignatura")})
public class ProgramacionAsignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_programacionasignatura")
    private Long idProgramacionasignatura;
    @JoinColumn(name = "id_programacion", referencedColumnName = "id_programacion")
    @ManyToOne
    private Programacion idProgramacion;
    @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura")
    @ManyToOne
    private Asignatura idAsignatura;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "programacionAsignatura")
    private ProgramacionEspecifica programacionEspecifica;

    public ProgramacionAsignatura() {
    }

    public ProgramacionAsignatura(Long idProgramacionasignatura) {
        this.idProgramacionasignatura = idProgramacionasignatura;
    }

    public Long getIdProgramacionasignatura() {
        return idProgramacionasignatura;
    }

    public void setIdProgramacionasignatura(Long idProgramacionasignatura) {
        this.idProgramacionasignatura = idProgramacionasignatura;
    }

    public Programacion getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(Programacion idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public Asignatura getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Asignatura idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public ProgramacionEspecifica getProgramacionEspecifica() {
        return programacionEspecifica;
    }

    public void setProgramacionEspecifica(ProgramacionEspecifica programacionEspecifica) {
        this.programacionEspecifica = programacionEspecifica;
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
        if (!(object instanceof ProgramacionAsignatura)) {
            return false;
        }
        ProgramacionAsignatura other = (ProgramacionAsignatura) object;
        if ((this.idProgramacionasignatura == null && other.idProgramacionasignatura != null) || (this.idProgramacionasignatura != null && !this.idProgramacionasignatura.equals(other.idProgramacionasignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.ProgramacionAsignatura[ idProgramacionasignatura=" + idProgramacionasignatura + " ]";
    }
    
}
