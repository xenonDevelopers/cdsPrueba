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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "tipo_nivelasignatura")
@NamedQueries({
    @NamedQuery(name = "TipoNivelasignatura.findAll", query = "SELECT t FROM TipoNivelasignatura t"),
    @NamedQuery(name = "TipoNivelasignatura.findByIdTiponivelasignatura", query = "SELECT t FROM TipoNivelasignatura t WHERE t.idTiponivelasignatura = :idTiponivelasignatura"),
    @NamedQuery(name = "TipoNivelasignatura.findByDescripcionTiponivelasignatura", query = "SELECT t FROM TipoNivelasignatura t WHERE t.descripcionTiponivelasignatura = :descripcionTiponivelasignatura")})
public class TipoNivelasignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tiponivelasignatura")
    private Long idTiponivelasignatura;
    @Column(name = "descripcion_tiponivelasignatura")
    private String descripcionTiponivelasignatura;
    @OneToMany(mappedBy = "idTiponivelasignatura")
    private List<Asignatura> asignaturaList;

    public TipoNivelasignatura() {
    }

    public TipoNivelasignatura(Long idTiponivelasignatura) {
        this.idTiponivelasignatura = idTiponivelasignatura;
    }

    public Long getIdTiponivelasignatura() {
        return idTiponivelasignatura;
    }

    public void setIdTiponivelasignatura(Long idTiponivelasignatura) {
        this.idTiponivelasignatura = idTiponivelasignatura;
    }

    public String getDescripcionTiponivelasignatura() {
        return descripcionTiponivelasignatura;
    }

    public void setDescripcionTiponivelasignatura(String descripcionTiponivelasignatura) {
        this.descripcionTiponivelasignatura = descripcionTiponivelasignatura;
    }

    public List<Asignatura> getAsignaturaList() {
        return asignaturaList;
    }

    public void setAsignaturaList(List<Asignatura> asignaturaList) {
        this.asignaturaList = asignaturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTiponivelasignatura != null ? idTiponivelasignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoNivelasignatura)) {
            return false;
        }
        TipoNivelasignatura other = (TipoNivelasignatura) object;
        if ((this.idTiponivelasignatura == null && other.idTiponivelasignatura != null) || (this.idTiponivelasignatura != null && !this.idTiponivelasignatura.equals(other.idTiponivelasignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoNivelasignatura[ idTiponivelasignatura=" + idTiponivelasignatura + " ]";
    }
    
}
