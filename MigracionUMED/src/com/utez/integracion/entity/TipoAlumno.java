/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Alumno;
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
@Table(name = "tipo_alumno")
@NamedQueries({
    @NamedQuery(name = "TipoAlumno.findAll", query = "SELECT t FROM TipoAlumno t"),
    @NamedQuery(name = "TipoAlumno.findByIdTipoalumno", query = "SELECT t FROM TipoAlumno t WHERE t.idTipoalumno = :idTipoalumno"),
    @NamedQuery(name = "TipoAlumno.findByDescripcionTipoalumno", query = "SELECT t FROM TipoAlumno t WHERE t.descripcionTipoalumno = :descripcionTipoalumno")})
public class TipoAlumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipoalumno")
    private Long idTipoalumno;
    @Column(name = "descripcion_tipoalumno")
    private String descripcionTipoalumno;
    @OneToMany(mappedBy = "idTipoalumno")
    private List<Alumno> alumnoList;

    public TipoAlumno() {
    }

    public TipoAlumno(Long idTipoalumno) {
        this.idTipoalumno = idTipoalumno;
    }

    public Long getIdTipoalumno() {
        return idTipoalumno;
    }

    public void setIdTipoalumno(Long idTipoalumno) {
        this.idTipoalumno = idTipoalumno;
    }

    public String getDescripcionTipoalumno() {
        return descripcionTipoalumno;
    }

    public void setDescripcionTipoalumno(String descripcionTipoalumno) {
        this.descripcionTipoalumno = descripcionTipoalumno;
    }

    public List<Alumno> getAlumnoList() {
        return alumnoList;
    }

    public void setAlumnoList(List<Alumno> alumnoList) {
        this.alumnoList = alumnoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoalumno != null ? idTipoalumno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAlumno)) {
            return false;
        }
        TipoAlumno other = (TipoAlumno) object;
        if ((this.idTipoalumno == null && other.idTipoalumno != null) || (this.idTipoalumno != null && !this.idTipoalumno.equals(other.idTipoalumno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoAlumno[ idTipoalumno=" + idTipoalumno + " ]";
    }
    
}
