/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Alumno;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "asignacion_encuestaalumno")
@NamedQueries({
    @NamedQuery(name = "AsignacionEncuestaalumno.findAll", query = "SELECT a FROM AsignacionEncuestaalumno a"),
    @NamedQuery(name = "AsignacionEncuestaalumno.findByIdAsignacionencuestaalumno", query = "SELECT a FROM AsignacionEncuestaalumno a WHERE a.idAsignacionencuestaalumno = :idAsignacionencuestaalumno"),
    @NamedQuery(name = "AsignacionEncuestaalumno.findByFechaAplicacion", query = "SELECT a FROM AsignacionEncuestaalumno a WHERE a.fechaAplicacion = :fechaAplicacion"),
    @NamedQuery(name = "AsignacionEncuestaalumno.findByEstado", query = "SELECT a FROM AsignacionEncuestaalumno a WHERE a.estado = :estado")})
public class AsignacionEncuestaalumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignacionencuestaalumno")
    private Long idAsignacionencuestaalumno;
    @Column(name = "fecha_aplicacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAplicacion;
    @Column(name = "estado")
    private String estado;
    @OneToMany(mappedBy = "idAsignacionencuesta")
    private List<RespuestaEncuesta> respuestaEncuestaList;
    @JoinColumn(name = "id_encuesta", referencedColumnName = "id_encuesta")
    @ManyToOne
    private Encuesta idEncuesta;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    @ManyToOne
    private Alumno matricula;

    public AsignacionEncuestaalumno() {
    }

    public AsignacionEncuestaalumno(Long idAsignacionencuestaalumno) {
        this.idAsignacionencuestaalumno = idAsignacionencuestaalumno;
    }

    public Long getIdAsignacionencuestaalumno() {
        return idAsignacionencuestaalumno;
    }

    public void setIdAsignacionencuestaalumno(Long idAsignacionencuestaalumno) {
        this.idAsignacionencuestaalumno = idAsignacionencuestaalumno;
    }

    public Date getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(Date fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<RespuestaEncuesta> getRespuestaEncuestaList() {
        return respuestaEncuestaList;
    }

    public void setRespuestaEncuestaList(List<RespuestaEncuesta> respuestaEncuestaList) {
        this.respuestaEncuestaList = respuestaEncuestaList;
    }

    public Encuesta getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Encuesta idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public Alumno getMatricula() {
        return matricula;
    }

    public void setMatricula(Alumno matricula) {
        this.matricula = matricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacionencuestaalumno != null ? idAsignacionencuestaalumno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionEncuestaalumno)) {
            return false;
        }
        AsignacionEncuestaalumno other = (AsignacionEncuestaalumno) object;
        if ((this.idAsignacionencuestaalumno == null && other.idAsignacionencuestaalumno != null) || (this.idAsignacionencuestaalumno != null && !this.idAsignacionencuestaalumno.equals(other.idAsignacionencuestaalumno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionEncuestaalumno[ idAsignacionencuestaalumno=" + idAsignacionencuestaalumno + " ]";
    }
    
}
