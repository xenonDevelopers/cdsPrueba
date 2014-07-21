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
@Table(name = "universidad_externa")
@NamedQueries({
    @NamedQuery(name = "UniversidadExterna.findAll", query = "SELECT u FROM UniversidadExterna u"),
    @NamedQuery(name = "UniversidadExterna.findByIdUniversidadexterna", query = "SELECT u FROM UniversidadExterna u WHERE u.idUniversidadexterna = :idUniversidadexterna"),
    @NamedQuery(name = "UniversidadExterna.findByNombreUniversidad", query = "SELECT u FROM UniversidadExterna u WHERE u.nombreUniversidad = :nombreUniversidad")})
public class UniversidadExterna implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_universidadexterna")
    private Long idUniversidadexterna;
    @Column(name = "nombre_universidad")
    private String nombreUniversidad;
    @OneToMany(mappedBy = "idUniversidadexterna")
    private List<PlanEstudiosexterno> planEstudiosexternoList;

    public UniversidadExterna() {
    }

    public UniversidadExterna(Long idUniversidadexterna) {
        this.idUniversidadexterna = idUniversidadexterna;
    }

    public Long getIdUniversidadexterna() {
        return idUniversidadexterna;
    }

    public void setIdUniversidadexterna(Long idUniversidadexterna) {
        this.idUniversidadexterna = idUniversidadexterna;
    }

    public String getNombreUniversidad() {
        return nombreUniversidad;
    }

    public void setNombreUniversidad(String nombreUniversidad) {
        this.nombreUniversidad = nombreUniversidad;
    }

    public List<PlanEstudiosexterno> getPlanEstudiosexternoList() {
        return planEstudiosexternoList;
    }

    public void setPlanEstudiosexternoList(List<PlanEstudiosexterno> planEstudiosexternoList) {
        this.planEstudiosexternoList = planEstudiosexternoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUniversidadexterna != null ? idUniversidadexterna.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UniversidadExterna)) {
            return false;
        }
        UniversidadExterna other = (UniversidadExterna) object;
        if ((this.idUniversidadexterna == null && other.idUniversidadexterna != null) || (this.idUniversidadexterna != null && !this.idUniversidadexterna.equals(other.idUniversidadexterna))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.UniversidadExterna[ idUniversidadexterna=" + idUniversidadexterna + " ]";
    }
    
}
