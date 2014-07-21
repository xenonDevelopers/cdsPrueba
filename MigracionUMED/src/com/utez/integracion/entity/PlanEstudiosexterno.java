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
@Table(name = "plan_estudiosexterno")
@NamedQueries({
    @NamedQuery(name = "PlanEstudiosexterno.findAll", query = "SELECT p FROM PlanEstudiosexterno p"),
    @NamedQuery(name = "PlanEstudiosexterno.findByIdPlanestudiosexterno", query = "SELECT p FROM PlanEstudiosexterno p WHERE p.idPlanestudiosexterno = :idPlanestudiosexterno"),
    @NamedQuery(name = "PlanEstudiosexterno.findByRvoe", query = "SELECT p FROM PlanEstudiosexterno p WHERE p.rvoe = :rvoe"),
    @NamedQuery(name = "PlanEstudiosexterno.findByNombrePlanestudios", query = "SELECT p FROM PlanEstudiosexterno p WHERE p.nombrePlanestudios = :nombrePlanestudios")})
public class PlanEstudiosexterno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_planestudiosexterno")
    private Long idPlanestudiosexterno;
    @Column(name = "rvoe")
    private String rvoe;
    @Column(name = "nombre_planestudios")
    private String nombrePlanestudios;
    @OneToMany(mappedBy = "idPlanestudiosexterno")
    private List<PosgradoSolicitudautorizacion> posgradoSolicitudautorizacionList;
    @JoinColumn(name = "id_universidadexterna", referencedColumnName = "id_universidadexterna")
    @ManyToOne
    private UniversidadExterna idUniversidadexterna;

    public PlanEstudiosexterno() {
    }

    public PlanEstudiosexterno(Long idPlanestudiosexterno) {
        this.idPlanestudiosexterno = idPlanestudiosexterno;
    }

    public Long getIdPlanestudiosexterno() {
        return idPlanestudiosexterno;
    }

    public void setIdPlanestudiosexterno(Long idPlanestudiosexterno) {
        this.idPlanestudiosexterno = idPlanestudiosexterno;
    }

    public String getRvoe() {
        return rvoe;
    }

    public void setRvoe(String rvoe) {
        this.rvoe = rvoe;
    }

    public String getNombrePlanestudios() {
        return nombrePlanestudios;
    }

    public void setNombrePlanestudios(String nombrePlanestudios) {
        this.nombrePlanestudios = nombrePlanestudios;
    }

    public List<PosgradoSolicitudautorizacion> getPosgradoSolicitudautorizacionList() {
        return posgradoSolicitudautorizacionList;
    }

    public void setPosgradoSolicitudautorizacionList(List<PosgradoSolicitudautorizacion> posgradoSolicitudautorizacionList) {
        this.posgradoSolicitudautorizacionList = posgradoSolicitudautorizacionList;
    }

    public UniversidadExterna getIdUniversidadexterna() {
        return idUniversidadexterna;
    }

    public void setIdUniversidadexterna(UniversidadExterna idUniversidadexterna) {
        this.idUniversidadexterna = idUniversidadexterna;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanestudiosexterno != null ? idPlanestudiosexterno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanEstudiosexterno)) {
            return false;
        }
        PlanEstudiosexterno other = (PlanEstudiosexterno) object;
        if ((this.idPlanestudiosexterno == null && other.idPlanestudiosexterno != null) || (this.idPlanestudiosexterno != null && !this.idPlanestudiosexterno.equals(other.idPlanestudiosexterno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.PlanEstudiosexterno[ idPlanestudiosexterno=" + idPlanestudiosexterno + " ]";
    }
    
}
