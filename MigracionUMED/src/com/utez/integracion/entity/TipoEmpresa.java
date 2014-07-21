/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
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

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "tipo_empresa")
@NamedQueries({
    @NamedQuery(name = "TipoEmpresa.findAll", query = "SELECT t FROM TipoEmpresa t"),
    @NamedQuery(name = "TipoEmpresa.findByIdTipoempresa", query = "SELECT t FROM TipoEmpresa t WHERE t.idTipoempresa = :idTipoempresa"),
    @NamedQuery(name = "TipoEmpresa.findByDescripcionTipoempresa", query = "SELECT t FROM TipoEmpresa t WHERE t.descripcionTipoempresa = :descripcionTipoempresa")})
public class TipoEmpresa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipoempresa")
    private Long idTipoempresa;
    @Column(name = "descripcion_tipoempresa")
    private String descripcionTipoempresa;
    @OneToMany(mappedBy = "idTipoinstitucion")
    private List<ExperienciaLaboral> experienciaLaboralList;

    public TipoEmpresa() {
    }

    public TipoEmpresa(Long idTipoempresa) {
        this.idTipoempresa = idTipoempresa;
    }

    public Long getIdTipoempresa() {
        return idTipoempresa;
    }

    public void setIdTipoempresa(Long idTipoempresa) {
        this.idTipoempresa = idTipoempresa;
    }

    public String getDescripcionTipoempresa() {
        return descripcionTipoempresa;
    }

    public void setDescripcionTipoempresa(String descripcionTipoempresa) {
        this.descripcionTipoempresa = descripcionTipoempresa;
    }

    public List<ExperienciaLaboral> getExperienciaLaboralList() {
        return experienciaLaboralList;
    }

    public void setExperienciaLaboralList(List<ExperienciaLaboral> experienciaLaboralList) {
        this.experienciaLaboralList = experienciaLaboralList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoempresa != null ? idTipoempresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEmpresa)) {
            return false;
        }
        TipoEmpresa other = (TipoEmpresa) object;
        if ((this.idTipoempresa == null && other.idTipoempresa != null) || (this.idTipoempresa != null && !this.idTipoempresa.equals(other.idTipoempresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoEmpresa[ idTipoempresa=" + idTipoempresa + " ]";
    }
    
}
