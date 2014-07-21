/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "encuesta_docente")
@NamedQueries({
    @NamedQuery(name = "EncuestaDocente.findAll", query = "SELECT e FROM EncuestaDocente e"),
    @NamedQuery(name = "EncuestaDocente.findByIdFechaexamen", query = "SELECT e FROM EncuestaDocente e WHERE e.idFechaexamen = :idFechaexamen"),
    @NamedQuery(name = "EncuestaDocente.findByCalificacionEscolares", query = "SELECT e FROM EncuestaDocente e WHERE e.calificacionEscolares = :calificacionEscolares"),
    @NamedQuery(name = "EncuestaDocente.findByCalificacionSede", query = "SELECT e FROM EncuestaDocente e WHERE e.calificacionSede = :calificacionSede"),
    @NamedQuery(name = "EncuestaDocente.findByCalificacionEvaluacion", query = "SELECT e FROM EncuestaDocente e WHERE e.calificacionEvaluacion = :calificacionEvaluacion"),
    @NamedQuery(name = "EncuestaDocente.findByCalificacionOpcionc", query = "SELECT e FROM EncuestaDocente e WHERE e.calificacionOpcionc = :calificacionOpcionc"),
    @NamedQuery(name = "EncuestaDocente.findByComentarios", query = "SELECT e FROM EncuestaDocente e WHERE e.comentarios = :comentarios")})
public class EncuestaDocente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_fechaexamen")
    private Long idFechaexamen;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "calificacion_escolares")
    private Float calificacionEscolares;
    @Column(name = "calificacion_sede")
    private Float calificacionSede;
    @Column(name = "calificacion_evaluacion")
    private Float calificacionEvaluacion;
    @Column(name = "calificacion_opcionc")
    private Float calificacionOpcionc;
    @Column(name = "comentarios")
    private String comentarios;
    @JoinColumn(name = "id_fechaexamen", referencedColumnName = "id_fechaexamen", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private FechaExamen fechaExamen;

    public EncuestaDocente() {
    }

    public EncuestaDocente(Long idFechaexamen) {
        this.idFechaexamen = idFechaexamen;
    }

    public Long getIdFechaexamen() {
        return idFechaexamen;
    }

    public void setIdFechaexamen(Long idFechaexamen) {
        this.idFechaexamen = idFechaexamen;
    }

    public Float getCalificacionEscolares() {
        return calificacionEscolares;
    }

    public void setCalificacionEscolares(Float calificacionEscolares) {
        this.calificacionEscolares = calificacionEscolares;
    }

    public Float getCalificacionSede() {
        return calificacionSede;
    }

    public void setCalificacionSede(Float calificacionSede) {
        this.calificacionSede = calificacionSede;
    }

    public Float getCalificacionEvaluacion() {
        return calificacionEvaluacion;
    }

    public void setCalificacionEvaluacion(Float calificacionEvaluacion) {
        this.calificacionEvaluacion = calificacionEvaluacion;
    }

    public Float getCalificacionOpcionc() {
        return calificacionOpcionc;
    }

    public void setCalificacionOpcionc(Float calificacionOpcionc) {
        this.calificacionOpcionc = calificacionOpcionc;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public FechaExamen getFechaExamen() {
        return fechaExamen;
    }

    public void setFechaExamen(FechaExamen fechaExamen) {
        this.fechaExamen = fechaExamen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFechaexamen != null ? idFechaexamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EncuestaDocente)) {
            return false;
        }
        EncuestaDocente other = (EncuestaDocente) object;
        if ((this.idFechaexamen == null && other.idFechaexamen != null) || (this.idFechaexamen != null && !this.idFechaexamen.equals(other.idFechaexamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.EncuestaDocente[ idFechaexamen=" + idFechaexamen + " ]";
    }
    
}
