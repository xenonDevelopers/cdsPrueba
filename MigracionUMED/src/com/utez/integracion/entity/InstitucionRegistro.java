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
@Table(name = "institucion_registro")
@NamedQueries({
    @NamedQuery(name = "InstitucionRegistro.findAll", query = "SELECT i FROM InstitucionRegistro i"),
    @NamedQuery(name = "InstitucionRegistro.findByIdInstitucion", query = "SELECT i FROM InstitucionRegistro i WHERE i.idInstitucion = :idInstitucion"),
    @NamedQuery(name = "InstitucionRegistro.findByInstitucion", query = "SELECT i FROM InstitucionRegistro i WHERE i.institucion = :institucion")})
public class InstitucionRegistro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_institucion")
    private Long idInstitucion;
    @Column(name = "institucion")
    private String institucion;
    @OneToMany(mappedBy = "idInstitucion")
    private List<PlanEstudio> planEstudioList;

    public InstitucionRegistro() {
    }

    public InstitucionRegistro(Long idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public Long getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(Long idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public List<PlanEstudio> getPlanEstudioList() {
        return planEstudioList;
    }

    public void setPlanEstudioList(List<PlanEstudio> planEstudioList) {
        this.planEstudioList = planEstudioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInstitucion != null ? idInstitucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InstitucionRegistro)) {
            return false;
        }
        InstitucionRegistro other = (InstitucionRegistro) object;
        if ((this.idInstitucion == null && other.idInstitucion != null) || (this.idInstitucion != null && !this.idInstitucion.equals(other.idInstitucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.InstitucionRegistro[ idInstitucion=" + idInstitucion + " ]";
    }
    
}
