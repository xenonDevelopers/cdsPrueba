/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Grupo;
import com.utez.secretariaAcademica.entity.Periodo;
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
@Table(name = "generacion")
@NamedQueries({
    @NamedQuery(name = "Generacion.findAll", query = "SELECT g FROM Generacion g"),
    @NamedQuery(name = "Generacion.findByIdGeneracion", query = "SELECT g FROM Generacion g WHERE g.idGeneracion = :idGeneracion"),
    @NamedQuery(name = "Generacion.findByNumeroGeneracion", query = "SELECT g FROM Generacion g WHERE g.numeroGeneracion = :numeroGeneracion")})
public class Generacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_generacion")
    private Long idGeneracion;
    @Column(name = "numero_generacion")
    private Integer numeroGeneracion;
    @OneToMany(mappedBy = "idGeneracion")
    private List<Grupo> grupoList;
    @JoinColumn(name = "id_planestudio", referencedColumnName = "id_planestudio")
    @ManyToOne
    private PlanEstudio idPlanestudio;
    @JoinColumn(name = "id_periodo", referencedColumnName = "id_periodo")
    @ManyToOne
    private Periodo idPeriodo;

    public Generacion() {
    }

    public Generacion(Long idGeneracion) {
        this.idGeneracion = idGeneracion;
    }

    public Long getIdGeneracion() {
        return idGeneracion;
    }

    public void setIdGeneracion(Long idGeneracion) {
        this.idGeneracion = idGeneracion;
    }

    public Integer getNumeroGeneracion() {
        return numeroGeneracion;
    }

    public void setNumeroGeneracion(Integer numeroGeneracion) {
        this.numeroGeneracion = numeroGeneracion;
    }

    public List<Grupo> getGrupoList() {
        return grupoList;
    }

    public void setGrupoList(List<Grupo> grupoList) {
        this.grupoList = grupoList;
    }

    public PlanEstudio getIdPlanestudio() {
        return idPlanestudio;
    }

    public void setIdPlanestudio(PlanEstudio idPlanestudio) {
        this.idPlanestudio = idPlanestudio;
    }

    public Periodo getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Periodo idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGeneracion != null ? idGeneracion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Generacion)) {
            return false;
        }
        Generacion other = (Generacion) object;
        if ((this.idGeneracion == null && other.idGeneracion != null) || (this.idGeneracion != null && !this.idGeneracion.equals(other.idGeneracion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Generacion[ idGeneracion=" + idGeneracion + " ]";
    }
    
}
