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
@Table(name = "pregunta")
@NamedQueries({
    @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p"),
    @NamedQuery(name = "Pregunta.findByIdPregunta", query = "SELECT p FROM Pregunta p WHERE p.idPregunta = :idPregunta"),
    @NamedQuery(name = "Pregunta.findByDescripcionPregunta", query = "SELECT p FROM Pregunta p WHERE p.descripcionPregunta = :descripcionPregunta")})
public class Pregunta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pregunta")
    private Long idPregunta;
    @Column(name = "descripcion_pregunta")
    private String descripcionPregunta;
    @OneToMany(mappedBy = "idPregunta")
    private List<RespuestaEncuesta> respuestaEncuestaList;
    @OneToMany(mappedBy = "idPregunta")
    private List<AsignacionPreguntaseccion> asignacionPreguntaseccionList;
    @OneToMany(mappedBy = "idPregunta")
    private List<RespuestaEncuestadocente> respuestaEncuestadocenteList;
    @JoinColumn(name = "id_tipopregunta", referencedColumnName = "id_tipopregunta")
    @ManyToOne
    private TipoPreguntaencuesta idTipopregunta;
    @OneToMany(mappedBy = "idPregunta")
    private List<AsignacionRespuestapredeterminadapregunta> asignacionRespuestapredeterminadapreguntaList;

    public Pregunta() {
    }

    public Pregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getDescripcionPregunta() {
        return descripcionPregunta;
    }

    public void setDescripcionPregunta(String descripcionPregunta) {
        this.descripcionPregunta = descripcionPregunta;
    }

    public List<RespuestaEncuesta> getRespuestaEncuestaList() {
        return respuestaEncuestaList;
    }

    public void setRespuestaEncuestaList(List<RespuestaEncuesta> respuestaEncuestaList) {
        this.respuestaEncuestaList = respuestaEncuestaList;
    }

    public List<AsignacionPreguntaseccion> getAsignacionPreguntaseccionList() {
        return asignacionPreguntaseccionList;
    }

    public void setAsignacionPreguntaseccionList(List<AsignacionPreguntaseccion> asignacionPreguntaseccionList) {
        this.asignacionPreguntaseccionList = asignacionPreguntaseccionList;
    }

    public List<RespuestaEncuestadocente> getRespuestaEncuestadocenteList() {
        return respuestaEncuestadocenteList;
    }

    public void setRespuestaEncuestadocenteList(List<RespuestaEncuestadocente> respuestaEncuestadocenteList) {
        this.respuestaEncuestadocenteList = respuestaEncuestadocenteList;
    }

    public TipoPreguntaencuesta getIdTipopregunta() {
        return idTipopregunta;
    }

    public void setIdTipopregunta(TipoPreguntaencuesta idTipopregunta) {
        this.idTipopregunta = idTipopregunta;
    }

    public List<AsignacionRespuestapredeterminadapregunta> getAsignacionRespuestapredeterminadapreguntaList() {
        return asignacionRespuestapredeterminadapreguntaList;
    }

    public void setAsignacionRespuestapredeterminadapreguntaList(List<AsignacionRespuestapredeterminadapregunta> asignacionRespuestapredeterminadapreguntaList) {
        this.asignacionRespuestapredeterminadapreguntaList = asignacionRespuestapredeterminadapreguntaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPregunta != null ? idPregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pregunta)) {
            return false;
        }
        Pregunta other = (Pregunta) object;
        if ((this.idPregunta == null && other.idPregunta != null) || (this.idPregunta != null && !this.idPregunta.equals(other.idPregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Pregunta[ idPregunta=" + idPregunta + " ]";
    }
    
}
