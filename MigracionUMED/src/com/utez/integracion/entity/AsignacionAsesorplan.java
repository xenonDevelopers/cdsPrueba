/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "asignacion_asesorplan")
@NamedQueries({
    @NamedQuery(name = "AsignacionAsesorplan.findAll", query = "SELECT a FROM AsignacionAsesorplan a"),
    @NamedQuery(name = "AsignacionAsesorplan.findByIdAsignacionasesorplan", query = "SELECT a FROM AsignacionAsesorplan a WHERE a.idAsignacionasesorplan = :idAsignacionasesorplan"),
    @NamedQuery(name = "AsignacionAsesorplan.findByIdAsesor", query = "SELECT a FROM AsignacionAsesorplan a WHERE a.idAsesor = :idAsesor")})
public class AsignacionAsesorplan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignacionasesorplan")
    private Long idAsignacionasesorplan;
    @Column(name = "id_asesor")
    private BigInteger idAsesor;
    @JoinColumn(name = "id_planestudio", referencedColumnName = "id_planestudio")
    @ManyToOne
    private PlanEstudio idPlanestudio;
    @JoinColumn(name = "id_formacionacademica", referencedColumnName = "id_formacionacademica")
    @ManyToOne
    private FormacionAcademica idFormacionacademica;

    public AsignacionAsesorplan() {
    }

    public AsignacionAsesorplan(Long idAsignacionasesorplan) {
        this.idAsignacionasesorplan = idAsignacionasesorplan;
    }

    public Long getIdAsignacionasesorplan() {
        return idAsignacionasesorplan;
    }

    public void setIdAsignacionasesorplan(Long idAsignacionasesorplan) {
        this.idAsignacionasesorplan = idAsignacionasesorplan;
    }

    public BigInteger getIdAsesor() {
        return idAsesor;
    }

    public void setIdAsesor(BigInteger idAsesor) {
        this.idAsesor = idAsesor;
    }

    public PlanEstudio getIdPlanestudio() {
        return idPlanestudio;
    }

    public void setIdPlanestudio(PlanEstudio idPlanestudio) {
        this.idPlanestudio = idPlanestudio;
    }

    public FormacionAcademica getIdFormacionacademica() {
        return idFormacionacademica;
    }

    public void setIdFormacionacademica(FormacionAcademica idFormacionacademica) {
        this.idFormacionacademica = idFormacionacademica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacionasesorplan != null ? idAsignacionasesorplan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionAsesorplan)) {
            return false;
        }
        AsignacionAsesorplan other = (AsignacionAsesorplan) object;
        if ((this.idAsignacionasesorplan == null && other.idAsignacionasesorplan != null) || (this.idAsignacionasesorplan != null && !this.idAsignacionasesorplan.equals(other.idAsignacionasesorplan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionAsesorplan[ idAsignacionasesorplan=" + idAsignacionasesorplan + " ]";
    }
    
}
