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
@Table(name = "tipo_programacion")
@NamedQueries({
    @NamedQuery(name = "TipoProgramacion.findAll", query = "SELECT t FROM TipoProgramacion t"),
    @NamedQuery(name = "TipoProgramacion.findByIdTipoprogramacion", query = "SELECT t FROM TipoProgramacion t WHERE t.idTipoprogramacion = :idTipoprogramacion"),
    @NamedQuery(name = "TipoProgramacion.findByDescripcionTipoprogramacion", query = "SELECT t FROM TipoProgramacion t WHERE t.descripcionTipoprogramacion = :descripcionTipoprogramacion")})
public class TipoProgramacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipoprogramacion")
    private Long idTipoprogramacion;
    @Column(name = "descripcion_tipoprogramacion")
    private String descripcionTipoprogramacion;
    @OneToMany(mappedBy = "esquema")
    private List<EsquemaAlumnoasignatura> esquemaAlumnoasignaturaList;
    @OneToMany(mappedBy = "idTipoprogramacion")
    private List<Programacion> programacionList;

    public TipoProgramacion() {
    }

    public TipoProgramacion(Long idTipoprogramacion) {
        this.idTipoprogramacion = idTipoprogramacion;
    }

    public Long getIdTipoprogramacion() {
        return idTipoprogramacion;
    }

    public void setIdTipoprogramacion(Long idTipoprogramacion) {
        this.idTipoprogramacion = idTipoprogramacion;
    }

    public String getDescripcionTipoprogramacion() {
        return descripcionTipoprogramacion;
    }

    public void setDescripcionTipoprogramacion(String descripcionTipoprogramacion) {
        this.descripcionTipoprogramacion = descripcionTipoprogramacion;
    }

    public List<EsquemaAlumnoasignatura> getEsquemaAlumnoasignaturaList() {
        return esquemaAlumnoasignaturaList;
    }

    public void setEsquemaAlumnoasignaturaList(List<EsquemaAlumnoasignatura> esquemaAlumnoasignaturaList) {
        this.esquemaAlumnoasignaturaList = esquemaAlumnoasignaturaList;
    }

    public List<Programacion> getProgramacionList() {
        return programacionList;
    }

    public void setProgramacionList(List<Programacion> programacionList) {
        this.programacionList = programacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoprogramacion != null ? idTipoprogramacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoProgramacion)) {
            return false;
        }
        TipoProgramacion other = (TipoProgramacion) object;
        if ((this.idTipoprogramacion == null && other.idTipoprogramacion != null) || (this.idTipoprogramacion != null && !this.idTipoprogramacion.equals(other.idTipoprogramacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoProgramacion[ idTipoprogramacion=" + idTipoprogramacion + " ]";
    }
    
}
