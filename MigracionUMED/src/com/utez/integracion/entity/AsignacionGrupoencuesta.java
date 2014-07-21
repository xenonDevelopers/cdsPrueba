/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

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
@Table(name = "asignacion_grupoencuesta")
@NamedQueries({
    @NamedQuery(name = "AsignacionGrupoencuesta.findAll", query = "SELECT a FROM AsignacionGrupoencuesta a"),
    @NamedQuery(name = "AsignacionGrupoencuesta.findByIdAsignaciongrupoencuesta", query = "SELECT a FROM AsignacionGrupoencuesta a WHERE a.idAsignaciongrupoencuesta = :idAsignaciongrupoencuesta")})
public class AsignacionGrupoencuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignaciongrupoencuesta")
    private Long idAsignaciongrupoencuesta;
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")
    @ManyToOne
    private Grupo idGrupo;
    @JoinColumn(name = "id_encuesta", referencedColumnName = "id_encuesta")
    @ManyToOne
    private Encuesta idEncuesta;

    public AsignacionGrupoencuesta() {
    }

    public AsignacionGrupoencuesta(Long idAsignaciongrupoencuesta) {
        this.idAsignaciongrupoencuesta = idAsignaciongrupoencuesta;
    }

    public Long getIdAsignaciongrupoencuesta() {
        return idAsignaciongrupoencuesta;
    }

    public void setIdAsignaciongrupoencuesta(Long idAsignaciongrupoencuesta) {
        this.idAsignaciongrupoencuesta = idAsignaciongrupoencuesta;
    }

    public Grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupo idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Encuesta getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Encuesta idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignaciongrupoencuesta != null ? idAsignaciongrupoencuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionGrupoencuesta)) {
            return false;
        }
        AsignacionGrupoencuesta other = (AsignacionGrupoencuesta) object;
        if ((this.idAsignaciongrupoencuesta == null && other.idAsignaciongrupoencuesta != null) || (this.idAsignaciongrupoencuesta != null && !this.idAsignaciongrupoencuesta.equals(other.idAsignaciongrupoencuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionGrupoencuesta[ idAsignaciongrupoencuesta=" + idAsignaciongrupoencuesta + " ]";
    }
    
}
