/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Alumno;
import com.utez.secretariaAcademica.entity.Grupo;
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
@Table(name = "asignacion_grupo")
@NamedQueries({
    @NamedQuery(name = "AsignacionGrupo.findAll", query = "SELECT a FROM AsignacionGrupo a"),
    @NamedQuery(name = "AsignacionGrupo.findByIdAsignaciongrupo", query = "SELECT a FROM AsignacionGrupo a WHERE a.idAsignaciongrupo = :idAsignaciongrupo"),
    @NamedQuery(name = "AsignacionGrupo.findByEstadoAsignacion", query = "SELECT a FROM AsignacionGrupo a WHERE a.estadoAsignacion = :estadoAsignacion")})
public class AsignacionGrupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignaciongrupo")
    private Long idAsignaciongrupo;
    @Column(name = "estado_asignacion")
    private String estadoAsignacion;
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")
    @ManyToOne(optional = false)
    private Grupo idGrupo;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    @ManyToOne(optional = false)
    private Alumno matricula;

    public AsignacionGrupo() {
    }

    public AsignacionGrupo(Long idAsignaciongrupo) {
        this.idAsignaciongrupo = idAsignaciongrupo;
    }

    public Long getIdAsignaciongrupo() {
        return idAsignaciongrupo;
    }

    public void setIdAsignaciongrupo(Long idAsignaciongrupo) {
        this.idAsignaciongrupo = idAsignaciongrupo;
    }

    public String getEstadoAsignacion() {
        return estadoAsignacion;
    }

    public void setEstadoAsignacion(String estadoAsignacion) {
        this.estadoAsignacion = estadoAsignacion;
    }

    public Grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupo idGrupo) {
        this.idGrupo = idGrupo;
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
        hash += (idAsignaciongrupo != null ? idAsignaciongrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionGrupo)) {
            return false;
        }
        AsignacionGrupo other = (AsignacionGrupo) object;
        if ((this.idAsignaciongrupo == null && other.idAsignaciongrupo != null) || (this.idAsignaciongrupo != null && !this.idAsignaciongrupo.equals(other.idAsignaciongrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionGrupo[ idAsignaciongrupo=" + idAsignaciongrupo + " ]";
    }
    
}
