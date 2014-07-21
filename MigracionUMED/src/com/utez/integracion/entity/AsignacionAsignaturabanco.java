/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Asignatura;
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
@Table(name = "asignacion_asignaturabanco")
@NamedQueries({
    @NamedQuery(name = "AsignacionAsignaturabanco.findAll", query = "SELECT a FROM AsignacionAsignaturabanco a"),
    @NamedQuery(name = "AsignacionAsignaturabanco.findByIdAsignacionasignaturabanco", query = "SELECT a FROM AsignacionAsignaturabanco a WHERE a.idAsignacionasignaturabanco = :idAsignacionasignaturabanco")})
public class AsignacionAsignaturabanco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignacionasignaturabanco")
    private Long idAsignacionasignaturabanco;
    @OneToMany(mappedBy = "idAsignacionasignaturabanco")
    private List<AsignacionEvaluacion> asignacionEvaluacionList;
    @JoinColumn(name = "id_bancoreactivo", referencedColumnName = "id_bancoreactivo")
    @ManyToOne
    private BancoReactivo idBancoreactivo;
    @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura")
    @ManyToOne
    private Asignatura idAsignatura;
    @OneToMany(mappedBy = "idAsignacionasignaturabanco")
    private List<ExamenImpreso> examenImpresoList;

    public AsignacionAsignaturabanco() {
    }

    public AsignacionAsignaturabanco(Long idAsignacionasignaturabanco) {
        this.idAsignacionasignaturabanco = idAsignacionasignaturabanco;
    }

    public Long getIdAsignacionasignaturabanco() {
        return idAsignacionasignaturabanco;
    }

    public void setIdAsignacionasignaturabanco(Long idAsignacionasignaturabanco) {
        this.idAsignacionasignaturabanco = idAsignacionasignaturabanco;
    }

    public List<AsignacionEvaluacion> getAsignacionEvaluacionList() {
        return asignacionEvaluacionList;
    }

    public void setAsignacionEvaluacionList(List<AsignacionEvaluacion> asignacionEvaluacionList) {
        this.asignacionEvaluacionList = asignacionEvaluacionList;
    }

    public BancoReactivo getIdBancoreactivo() {
        return idBancoreactivo;
    }

    public void setIdBancoreactivo(BancoReactivo idBancoreactivo) {
        this.idBancoreactivo = idBancoreactivo;
    }

    public Asignatura getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Asignatura idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public List<ExamenImpreso> getExamenImpresoList() {
        return examenImpresoList;
    }

    public void setExamenImpresoList(List<ExamenImpreso> examenImpresoList) {
        this.examenImpresoList = examenImpresoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacionasignaturabanco != null ? idAsignacionasignaturabanco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionAsignaturabanco)) {
            return false;
        }
        AsignacionAsignaturabanco other = (AsignacionAsignaturabanco) object;
        if ((this.idAsignacionasignaturabanco == null && other.idAsignacionasignaturabanco != null) || (this.idAsignacionasignaturabanco != null && !this.idAsignacionasignaturabanco.equals(other.idAsignacionasignaturabanco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionAsignaturabanco[ idAsignacionasignaturabanco=" + idAsignacionasignaturabanco + " ]";
    }
    
}
