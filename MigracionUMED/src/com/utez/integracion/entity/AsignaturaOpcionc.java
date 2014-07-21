/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "asignatura_opcionc")
@NamedQueries({
    @NamedQuery(name = "AsignaturaOpcionc.findAll", query = "SELECT a FROM AsignaturaOpcionc a"),
    @NamedQuery(name = "AsignaturaOpcionc.findByIdAsignaturaopcionc", query = "SELECT a FROM AsignaturaOpcionc a WHERE a.idAsignaturaopcionc = :idAsignaturaopcionc"),
    @NamedQuery(name = "AsignaturaOpcionc.findByNumeroAsignatura", query = "SELECT a FROM AsignaturaOpcionc a WHERE a.numeroAsignatura = :numeroAsignatura")})
public class AsignaturaOpcionc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignaturaopcionc")
    private Long idAsignaturaopcionc;
    @Column(name = "numero_asignatura")
    private Integer numeroAsignatura;
    @OneToMany(mappedBy = "idCalendarioopcionc")
    private List<ProgramacionOpcionc> programacionOpcioncList;
    @JoinColumn(name = "id_mesopcionc", referencedColumnName = "id_mesopcionc")
    @ManyToOne
    private MesOpcionc idMesopcionc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignaturaOpcionc")
    private List<FechaExamenopcionc> fechaExamenopcioncList;

    public AsignaturaOpcionc() {
    }

    public AsignaturaOpcionc(Long idAsignaturaopcionc) {
        this.idAsignaturaopcionc = idAsignaturaopcionc;
    }

    public Long getIdAsignaturaopcionc() {
        return idAsignaturaopcionc;
    }

    public void setIdAsignaturaopcionc(Long idAsignaturaopcionc) {
        this.idAsignaturaopcionc = idAsignaturaopcionc;
    }

    public Integer getNumeroAsignatura() {
        return numeroAsignatura;
    }

    public void setNumeroAsignatura(Integer numeroAsignatura) {
        this.numeroAsignatura = numeroAsignatura;
    }

    public List<ProgramacionOpcionc> getProgramacionOpcioncList() {
        return programacionOpcioncList;
    }

    public void setProgramacionOpcioncList(List<ProgramacionOpcionc> programacionOpcioncList) {
        this.programacionOpcioncList = programacionOpcioncList;
    }

    public MesOpcionc getIdMesopcionc() {
        return idMesopcionc;
    }

    public void setIdMesopcionc(MesOpcionc idMesopcionc) {
        this.idMesopcionc = idMesopcionc;
    }

    public List<FechaExamenopcionc> getFechaExamenopcioncList() {
        return fechaExamenopcioncList;
    }

    public void setFechaExamenopcioncList(List<FechaExamenopcionc> fechaExamenopcioncList) {
        this.fechaExamenopcioncList = fechaExamenopcioncList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignaturaopcionc != null ? idAsignaturaopcionc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignaturaOpcionc)) {
            return false;
        }
        AsignaturaOpcionc other = (AsignaturaOpcionc) object;
        if ((this.idAsignaturaopcionc == null && other.idAsignaturaopcionc != null) || (this.idAsignaturaopcionc != null && !this.idAsignaturaopcionc.equals(other.idAsignaturaopcionc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignaturaOpcionc[ idAsignaturaopcionc=" + idAsignaturaopcionc + " ]";
    }
    
}
