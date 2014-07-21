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
@Table(name = "asignacion_encuestadocente")
@NamedQueries({
    @NamedQuery(name = "AsignacionEncuestadocente.findAll", query = "SELECT a FROM AsignacionEncuestadocente a"),
    @NamedQuery(name = "AsignacionEncuestadocente.findByIdAsignacionencuestadocente", query = "SELECT a FROM AsignacionEncuestadocente a WHERE a.idAsignacionencuestadocente = :idAsignacionencuestadocente"),
    @NamedQuery(name = "AsignacionEncuestadocente.findByEstadoEncuesta", query = "SELECT a FROM AsignacionEncuestadocente a WHERE a.estadoEncuesta = :estadoEncuesta")})
public class AsignacionEncuestadocente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignacionencuestadocente")
    private Long idAsignacionencuestadocente;
    @Column(name = "estado_encuesta")
    private String estadoEncuesta;
    @JoinColumn(name = "id_fechaexamen", referencedColumnName = "id_fecha_examen")
    @ManyToOne
    private FechaExamenprogramado idFechaexamen;
    @JoinColumn(name = "id_encuesta", referencedColumnName = "id_encuesta")
    @ManyToOne
    private Encuesta idEncuesta;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    @ManyToOne
    private Alumno matricula;
    @OneToMany(mappedBy = "idAsignacionencuestadocente")
    private List<RespuestaEncuestadocente> respuestaEncuestadocenteList;

    public AsignacionEncuestadocente() {
    }

    public AsignacionEncuestadocente(Long idAsignacionencuestadocente) {
        this.idAsignacionencuestadocente = idAsignacionencuestadocente;
    }

    public Long getIdAsignacionencuestadocente() {
        return idAsignacionencuestadocente;
    }

    public void setIdAsignacionencuestadocente(Long idAsignacionencuestadocente) {
        this.idAsignacionencuestadocente = idAsignacionencuestadocente;
    }

    public String getEstadoEncuesta() {
        return estadoEncuesta;
    }

    public void setEstadoEncuesta(String estadoEncuesta) {
        this.estadoEncuesta = estadoEncuesta;
    }

    public FechaExamenprogramado getIdFechaexamen() {
        return idFechaexamen;
    }

    public void setIdFechaexamen(FechaExamenprogramado idFechaexamen) {
        this.idFechaexamen = idFechaexamen;
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

    public List<RespuestaEncuestadocente> getRespuestaEncuestadocenteList() {
        return respuestaEncuestadocenteList;
    }

    public void setRespuestaEncuestadocenteList(List<RespuestaEncuestadocente> respuestaEncuestadocenteList) {
        this.respuestaEncuestadocenteList = respuestaEncuestadocenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacionencuestadocente != null ? idAsignacionencuestadocente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionEncuestadocente)) {
            return false;
        }
        AsignacionEncuestadocente other = (AsignacionEncuestadocente) object;
        if ((this.idAsignacionencuestadocente == null && other.idAsignacionencuestadocente != null) || (this.idAsignacionencuestadocente != null && !this.idAsignacionencuestadocente.equals(other.idAsignacionencuestadocente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionEncuestadocente[ idAsignacionencuestadocente=" + idAsignacionencuestadocente + " ]";
    }
    
}
