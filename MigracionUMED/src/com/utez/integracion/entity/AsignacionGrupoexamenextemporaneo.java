/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Alumno;
import java.io.Serializable;
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
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "asignacion_grupoexamenextemporaneo")
@NamedQueries({
    @NamedQuery(name = "AsignacionGrupoexamenextemporaneo.findAll", query = "SELECT a FROM AsignacionGrupoexamenextemporaneo a"),
    @NamedQuery(name = "AsignacionGrupoexamenextemporaneo.findByIdAsignaciongrupoexamenextemporaneo", query = "SELECT a FROM AsignacionGrupoexamenextemporaneo a WHERE a.idAsignaciongrupoexamenextemporaneo = :idAsignaciongrupoexamenextemporaneo")})
public class AsignacionGrupoexamenextemporaneo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignaciongrupoexamenextemporaneo")
    private Long idAsignaciongrupoexamenextemporaneo;
    @JoinColumn(name = "id_grupoexamenextemporaneo", referencedColumnName = "id_grupoexamenextemporaneo")
    @ManyToOne
    private GrupoExamenextemporaneo idGrupoexamenextemporaneo;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    @ManyToOne
    private Alumno matricula;

    public AsignacionGrupoexamenextemporaneo() {
    }

    public AsignacionGrupoexamenextemporaneo(Long idAsignaciongrupoexamenextemporaneo) {
        this.idAsignaciongrupoexamenextemporaneo = idAsignaciongrupoexamenextemporaneo;
    }

    public Long getIdAsignaciongrupoexamenextemporaneo() {
        return idAsignaciongrupoexamenextemporaneo;
    }

    public void setIdAsignaciongrupoexamenextemporaneo(Long idAsignaciongrupoexamenextemporaneo) {
        this.idAsignaciongrupoexamenextemporaneo = idAsignaciongrupoexamenextemporaneo;
    }

    public GrupoExamenextemporaneo getIdGrupoexamenextemporaneo() {
        return idGrupoexamenextemporaneo;
    }

    public void setIdGrupoexamenextemporaneo(GrupoExamenextemporaneo idGrupoexamenextemporaneo) {
        this.idGrupoexamenextemporaneo = idGrupoexamenextemporaneo;
    }

    public Alumno getMatricula() {
        return matricula;
    }

    public void setMatricula(Alumno matricula) {
        this.matricula = matricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignaciongrupoexamenextemporaneo != null ? idAsignaciongrupoexamenextemporaneo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionGrupoexamenextemporaneo)) {
            return false;
        }
        AsignacionGrupoexamenextemporaneo other = (AsignacionGrupoexamenextemporaneo) object;
        if ((this.idAsignaciongrupoexamenextemporaneo == null && other.idAsignaciongrupoexamenextemporaneo != null) || (this.idAsignaciongrupoexamenextemporaneo != null && !this.idAsignaciongrupoexamenextemporaneo.equals(other.idAsignaciongrupoexamenextemporaneo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionGrupoexamenextemporaneo[ idAsignaciongrupoexamenextemporaneo=" + idAsignaciongrupoexamenextemporaneo + " ]";
    }
    
}
