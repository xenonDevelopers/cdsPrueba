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
@Table(name = "tipo_nivelacademico")
@NamedQueries({
    @NamedQuery(name = "TipoNivelacademico.findAll", query = "SELECT t FROM TipoNivelacademico t"),
    @NamedQuery(name = "TipoNivelacademico.findByIdTiponivelacademico", query = "SELECT t FROM TipoNivelacademico t WHERE t.idTiponivelacademico = :idTiponivelacademico"),
    @NamedQuery(name = "TipoNivelacademico.findByDescripcionTiponivelacademico", query = "SELECT t FROM TipoNivelacademico t WHERE t.descripcionTiponivelacademico = :descripcionTiponivelacademico")})
public class TipoNivelacademico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tiponivelacademico")
    private Long idTiponivelacademico;
    @Column(name = "descripcion_tiponivelacademico")
    private String descripcionTiponivelacademico;
    @OneToMany(mappedBy = "idTiponivelacademico")
    private List<PlanEstudio> planEstudioList;
    @OneToMany(mappedBy = "idTiponivelacademico")
    private List<FormacionAcademica> formacionAcademicaList;

    public TipoNivelacademico() {
    }

    public TipoNivelacademico(Long idTiponivelacademico) {
        this.idTiponivelacademico = idTiponivelacademico;
    }

    public Long getIdTiponivelacademico() {
        return idTiponivelacademico;
    }

    public void setIdTiponivelacademico(Long idTiponivelacademico) {
        this.idTiponivelacademico = idTiponivelacademico;
    }

    public String getDescripcionTiponivelacademico() {
        return descripcionTiponivelacademico;
    }

    public void setDescripcionTiponivelacademico(String descripcionTiponivelacademico) {
        this.descripcionTiponivelacademico = descripcionTiponivelacademico;
    }

    public List<PlanEstudio> getPlanEstudioList() {
        return planEstudioList;
    }

    public void setPlanEstudioList(List<PlanEstudio> planEstudioList) {
        this.planEstudioList = planEstudioList;
    }

    public List<FormacionAcademica> getFormacionAcademicaList() {
        return formacionAcademicaList;
    }

    public void setFormacionAcademicaList(List<FormacionAcademica> formacionAcademicaList) {
        this.formacionAcademicaList = formacionAcademicaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTiponivelacademico != null ? idTiponivelacademico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoNivelacademico)) {
            return false;
        }
        TipoNivelacademico other = (TipoNivelacademico) object;
        if ((this.idTiponivelacademico == null && other.idTiponivelacademico != null) || (this.idTiponivelacademico != null && !this.idTiponivelacademico.equals(other.idTiponivelacademico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoNivelacademico[ idTiponivelacademico=" + idTiponivelacademico + " ]";
    }
    
}
