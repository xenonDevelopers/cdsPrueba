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
@Table(name = "tipo_trabajo")
@NamedQueries({
    @NamedQuery(name = "TipoTrabajo.findAll", query = "SELECT t FROM TipoTrabajo t"),
    @NamedQuery(name = "TipoTrabajo.findByIdTipotrabajo", query = "SELECT t FROM TipoTrabajo t WHERE t.idTipotrabajo = :idTipotrabajo"),
    @NamedQuery(name = "TipoTrabajo.findByDescripcionTipotrabajo", query = "SELECT t FROM TipoTrabajo t WHERE t.descripcionTipotrabajo = :descripcionTipotrabajo")})
public class TipoTrabajo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipotrabajo")
    private Long idTipotrabajo;
    @Column(name = "descripcion_tipotrabajo")
    private String descripcionTipotrabajo;
    @OneToMany(mappedBy = "idTipotrabajo")
    private List<ExperienciaLaboral> experienciaLaboralList;

    public TipoTrabajo() {
    }

    public TipoTrabajo(Long idTipotrabajo) {
        this.idTipotrabajo = idTipotrabajo;
    }

    public Long getIdTipotrabajo() {
        return idTipotrabajo;
    }

    public void setIdTipotrabajo(Long idTipotrabajo) {
        this.idTipotrabajo = idTipotrabajo;
    }

    public String getDescripcionTipotrabajo() {
        return descripcionTipotrabajo;
    }

    public void setDescripcionTipotrabajo(String descripcionTipotrabajo) {
        this.descripcionTipotrabajo = descripcionTipotrabajo;
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
        hash += (idTipotrabajo != null ? idTipotrabajo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTrabajo)) {
            return false;
        }
        TipoTrabajo other = (TipoTrabajo) object;
        if ((this.idTipotrabajo == null && other.idTipotrabajo != null) || (this.idTipotrabajo != null && !this.idTipotrabajo.equals(other.idTipotrabajo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoTrabajo[ idTipotrabajo=" + idTipotrabajo + " ]";
    }
    
}
