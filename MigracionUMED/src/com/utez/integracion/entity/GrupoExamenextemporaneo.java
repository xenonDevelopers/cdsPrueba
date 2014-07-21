/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Asesor;
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
@Table(name = "grupo_examenextemporaneo")
@NamedQueries({
    @NamedQuery(name = "GrupoExamenextemporaneo.findAll", query = "SELECT g FROM GrupoExamenextemporaneo g"),
    @NamedQuery(name = "GrupoExamenextemporaneo.findByIdGrupoexamenextemporaneo", query = "SELECT g FROM GrupoExamenextemporaneo g WHERE g.idGrupoexamenextemporaneo = :idGrupoexamenextemporaneo")})
public class GrupoExamenextemporaneo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_grupoexamenextemporaneo")
    private Long idGrupoexamenextemporaneo;
    @OneToMany(mappedBy = "idGrupoexamenextemporaneo")
    private List<AsignacionGrupoexamenextemporaneo> asignacionGrupoexamenextemporaneoList;
    @JoinColumn(name = "id_asesor", referencedColumnName = "id_asesor")
    @ManyToOne
    private Asesor idAsesor;

    public GrupoExamenextemporaneo() {
    }

    public GrupoExamenextemporaneo(Long idGrupoexamenextemporaneo) {
        this.idGrupoexamenextemporaneo = idGrupoexamenextemporaneo;
    }

    public Long getIdGrupoexamenextemporaneo() {
        return idGrupoexamenextemporaneo;
    }

    public void setIdGrupoexamenextemporaneo(Long idGrupoexamenextemporaneo) {
        this.idGrupoexamenextemporaneo = idGrupoexamenextemporaneo;
    }

    public List<AsignacionGrupoexamenextemporaneo> getAsignacionGrupoexamenextemporaneoList() {
        return asignacionGrupoexamenextemporaneoList;
    }

    public void setAsignacionGrupoexamenextemporaneoList(List<AsignacionGrupoexamenextemporaneo> asignacionGrupoexamenextemporaneoList) {
        this.asignacionGrupoexamenextemporaneoList = asignacionGrupoexamenextemporaneoList;
    }

    public Asesor getIdAsesor() {
        return idAsesor;
    }

    public void setIdAsesor(Asesor idAsesor) {
        this.idAsesor = idAsesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupoexamenextemporaneo != null ? idGrupoexamenextemporaneo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoExamenextemporaneo)) {
            return false;
        }
        GrupoExamenextemporaneo other = (GrupoExamenextemporaneo) object;
        if ((this.idGrupoexamenextemporaneo == null && other.idGrupoexamenextemporaneo != null) || (this.idGrupoexamenextemporaneo != null && !this.idGrupoexamenextemporaneo.equals(other.idGrupoexamenextemporaneo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.GrupoExamenextemporaneo[ idGrupoexamenextemporaneo=" + idGrupoexamenextemporaneo + " ]";
    }
    
}
