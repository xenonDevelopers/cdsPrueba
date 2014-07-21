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
@Table(name = "resultado_evaluacion")
@NamedQueries({
    @NamedQuery(name = "ResultadoEvaluacion.findAll", query = "SELECT r FROM ResultadoEvaluacion r"),
    @NamedQuery(name = "ResultadoEvaluacion.findByIdResultadoevaluacion", query = "SELECT r FROM ResultadoEvaluacion r WHERE r.idResultadoevaluacion = :idResultadoevaluacion"),
    @NamedQuery(name = "ResultadoEvaluacion.findByCalificacion", query = "SELECT r FROM ResultadoEvaluacion r WHERE r.calificacion = :calificacion"),
    @NamedQuery(name = "ResultadoEvaluacion.findByTiempoTranscurrido", query = "SELECT r FROM ResultadoEvaluacion r WHERE r.tiempoTranscurrido = :tiempoTranscurrido"),
    @NamedQuery(name = "ResultadoEvaluacion.findByEstadoEvaluacion", query = "SELECT r FROM ResultadoEvaluacion r WHERE r.estadoEvaluacion = :estadoEvaluacion"),
    @NamedQuery(name = "ResultadoEvaluacion.findByFechaHorainicio", query = "SELECT r FROM ResultadoEvaluacion r WHERE r.fechaHorainicio = :fechaHorainicio"),
    @NamedQuery(name = "ResultadoEvaluacion.findByFechaHorafin", query = "SELECT r FROM ResultadoEvaluacion r WHERE r.fechaHorafin = :fechaHorafin")})
public class ResultadoEvaluacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_resultadoevaluacion")
    private Long idResultadoevaluacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "calificacion")
    private Double calificacion;
    @Column(name = "tiempo_transcurrido")
    @Temporal(TemporalType.TIME)
    private Date tiempoTranscurrido;
    @Column(name = "estado_evaluacion")
    private String estadoEvaluacion;
    @Column(name = "fecha_horainicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHorainicio;
    @Column(name = "fecha_horafin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHorafin;
    @JoinColumn(name = "id_asignacionevaluacion", referencedColumnName = "id_asignacionevaluacion")
    @ManyToOne
    private AsignacionEvaluacion idAsignacionevaluacion;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    @ManyToOne
    private Alumno matricula;
    @OneToMany(mappedBy = "idResultadoevaluacion")
    private List<RespuestaEvaluacion> respuestaEvaluacionList;

    public ResultadoEvaluacion() {
    }

    public ResultadoEvaluacion(Long idResultadoevaluacion) {
        this.idResultadoevaluacion = idResultadoevaluacion;
    }

    public Long getIdResultadoevaluacion() {
        return idResultadoevaluacion;
    }

    public void setIdResultadoevaluacion(Long idResultadoevaluacion) {
        this.idResultadoevaluacion = idResultadoevaluacion;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public Date getTiempoTranscurrido() {
        return tiempoTranscurrido;
    }

    public void setTiempoTranscurrido(Date tiempoTranscurrido) {
        this.tiempoTranscurrido = tiempoTranscurrido;
    }

    public String getEstadoEvaluacion() {
        return estadoEvaluacion;
    }

    public void setEstadoEvaluacion(String estadoEvaluacion) {
        this.estadoEvaluacion = estadoEvaluacion;
    }

    public Date getFechaHorainicio() {
        return fechaHorainicio;
    }

    public void setFechaHorainicio(Date fechaHorainicio) {
        this.fechaHorainicio = fechaHorainicio;
    }

    public Date getFechaHorafin() {
        return fechaHorafin;
    }

    public void setFechaHorafin(Date fechaHorafin) {
        this.fechaHorafin = fechaHorafin;
    }

    public AsignacionEvaluacion getIdAsignacionevaluacion() {
        return idAsignacionevaluacion;
    }

    public void setIdAsignacionevaluacion(AsignacionEvaluacion idAsignacionevaluacion) {
        this.idAsignacionevaluacion = idAsignacionevaluacion;
    }

    public Alumno getMatricula() {
        return matricula;
    }

    public void setMatricula(Alumno matricula) {
        this.matricula = matricula;
    }

    public List<RespuestaEvaluacion> getRespuestaEvaluacionList() {
        return respuestaEvaluacionList;
    }

    public void setRespuestaEvaluacionList(List<RespuestaEvaluacion> respuestaEvaluacionList) {
        this.respuestaEvaluacionList = respuestaEvaluacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResultadoevaluacion != null ? idResultadoevaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResultadoEvaluacion)) {
            return false;
        }
        ResultadoEvaluacion other = (ResultadoEvaluacion) object;
        if ((this.idResultadoevaluacion == null && other.idResultadoevaluacion != null) || (this.idResultadoevaluacion != null && !this.idResultadoevaluacion.equals(other.idResultadoevaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.ResultadoEvaluacion[ idResultadoevaluacion=" + idResultadoevaluacion + " ]";
    }
    
}
