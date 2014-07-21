/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Asesor;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
@Table(name = "programacion_opcionc")
@NamedQueries({
    @NamedQuery(name = "ProgramacionOpcionc.findAll", query = "SELECT p FROM ProgramacionOpcionc p"),
    @NamedQuery(name = "ProgramacionOpcionc.findByIdProgramacionasignatura", query = "SELECT p FROM ProgramacionOpcionc p WHERE p.idProgramacionasignatura = :idProgramacionasignatura")})
public class ProgramacionOpcionc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_programacionasignatura")
    private Long idProgramacionasignatura;
    @ManyToMany(mappedBy = "programacionOpcioncList")
    private List<Asesor> asesorList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "programacionOpcionc1")
    private ProgramacionOpcionc programacionOpcionc;
    @JoinColumn(name = "id_programacionasignatura", referencedColumnName = "id_programacionasignatura", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private ProgramacionOpcionc programacionOpcionc1;
    @JoinColumn(name = "id_calendarioopcionc", referencedColumnName = "id_asignaturaopcionc")
    @ManyToOne
    private AsignaturaOpcionc idCalendarioopcionc;

    public ProgramacionOpcionc() {
    }

    public ProgramacionOpcionc(Long idProgramacionasignatura) {
        this.idProgramacionasignatura = idProgramacionasignatura;
    }

    public Long getIdProgramacionasignatura() {
        return idProgramacionasignatura;
    }

    public void setIdProgramacionasignatura(Long idProgramacionasignatura) {
        this.idProgramacionasignatura = idProgramacionasignatura;
    }

    public List<Asesor> getAsesorList() {
        return asesorList;
    }

    public void setAsesorList(List<Asesor> asesorList) {
        this.asesorList = asesorList;
    }

    public ProgramacionOpcionc getProgramacionOpcionc() {
        return programacionOpcionc;
    }

    public void setProgramacionOpcionc(ProgramacionOpcionc programacionOpcionc) {
        this.programacionOpcionc = programacionOpcionc;
    }

    public ProgramacionOpcionc getProgramacionOpcionc1() {
        return programacionOpcionc1;
    }

    public void setProgramacionOpcionc1(ProgramacionOpcionc programacionOpcionc1) {
        this.programacionOpcionc1 = programacionOpcionc1;
    }

    public AsignaturaOpcionc getIdCalendarioopcionc() {
        return idCalendarioopcionc;
    }

    public void setIdCalendarioopcionc(AsignaturaOpcionc idCalendarioopcionc) {
        this.idCalendarioopcionc = idCalendarioopcionc;
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
        if (!(object instanceof ProgramacionOpcionc)) {
            return false;
        }
        ProgramacionOpcionc other = (ProgramacionOpcionc) object;
        if ((this.idProgramacionasignatura == null && other.idProgramacionasignatura != null) || (this.idProgramacionasignatura != null && !this.idProgramacionasignatura.equals(other.idProgramacionasignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.ProgramacionOpcionc[ idProgramacionasignatura=" + idProgramacionasignatura + " ]";
    }
    
}
