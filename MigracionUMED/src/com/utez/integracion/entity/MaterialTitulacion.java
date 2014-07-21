/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "material_titulacion")
@NamedQueries({
    @NamedQuery(name = "MaterialTitulacion.findAll", query = "SELECT m FROM MaterialTitulacion m"),
    @NamedQuery(name = "MaterialTitulacion.findByIdExamentitulacion", query = "SELECT m FROM MaterialTitulacion m WHERE m.idExamentitulacion = :idExamentitulacion"),
    @NamedQuery(name = "MaterialTitulacion.findByNumeroSillas", query = "SELECT m FROM MaterialTitulacion m WHERE m.numeroSillas = :numeroSillas"),
    @NamedQuery(name = "MaterialTitulacion.findByObservaciones", query = "SELECT m FROM MaterialTitulacion m WHERE m.observaciones = :observaciones"),
    @NamedQuery(name = "MaterialTitulacion.findByAula", query = "SELECT m FROM MaterialTitulacion m WHERE m.aula = :aula"),
    @NamedQuery(name = "MaterialTitulacion.findByCanon", query = "SELECT m FROM MaterialTitulacion m WHERE m.canon = :canon"),
    @NamedQuery(name = "MaterialTitulacion.findByPantalla", query = "SELECT m FROM MaterialTitulacion m WHERE m.pantalla = :pantalla"),
    @NamedQuery(name = "MaterialTitulacion.findByBrindis", query = "SELECT m FROM MaterialTitulacion m WHERE m.brindis = :brindis"),
    @NamedQuery(name = "MaterialTitulacion.findByRetroproyector", query = "SELECT m FROM MaterialTitulacion m WHERE m.retroproyector = :retroproyector")})
public class MaterialTitulacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_examentitulacion")
    private Long idExamentitulacion;
    @Column(name = "numero_sillas")
    private Integer numeroSillas;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "aula")
    private String aula;
    @Column(name = "canon")
    private Boolean canon;
    @Column(name = "pantalla")
    private Boolean pantalla;
    @Column(name = "brindis")
    private Boolean brindis;
    @Column(name = "retroproyector")
    private Boolean retroproyector;
    @JoinColumn(name = "id_examentitulacion", referencedColumnName = "id_examentitulacion", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private ExamenTitulacion examenTitulacion;

    public MaterialTitulacion() {
    }

    public MaterialTitulacion(Long idExamentitulacion) {
        this.idExamentitulacion = idExamentitulacion;
    }

    public Long getIdExamentitulacion() {
        return idExamentitulacion;
    }

    public void setIdExamentitulacion(Long idExamentitulacion) {
        this.idExamentitulacion = idExamentitulacion;
    }

    public Integer getNumeroSillas() {
        return numeroSillas;
    }

    public void setNumeroSillas(Integer numeroSillas) {
        this.numeroSillas = numeroSillas;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public Boolean getCanon() {
        return canon;
    }

    public void setCanon(Boolean canon) {
        this.canon = canon;
    }

    public Boolean getPantalla() {
        return pantalla;
    }

    public void setPantalla(Boolean pantalla) {
        this.pantalla = pantalla;
    }

    public Boolean getBrindis() {
        return brindis;
    }

    public void setBrindis(Boolean brindis) {
        this.brindis = brindis;
    }

    public Boolean getRetroproyector() {
        return retroproyector;
    }

    public void setRetroproyector(Boolean retroproyector) {
        this.retroproyector = retroproyector;
    }

    public ExamenTitulacion getExamenTitulacion() {
        return examenTitulacion;
    }

    public void setExamenTitulacion(ExamenTitulacion examenTitulacion) {
        this.examenTitulacion = examenTitulacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExamentitulacion != null ? idExamentitulacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialTitulacion)) {
            return false;
        }
        MaterialTitulacion other = (MaterialTitulacion) object;
        if ((this.idExamentitulacion == null && other.idExamentitulacion != null) || (this.idExamentitulacion != null && !this.idExamentitulacion.equals(other.idExamentitulacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.MaterialTitulacion[ idExamentitulacion=" + idExamentitulacion + " ]";
    }
    
}
