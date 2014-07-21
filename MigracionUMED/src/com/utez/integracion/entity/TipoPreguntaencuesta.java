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
@Table(name = "tipo_preguntaencuesta")
@NamedQueries({
    @NamedQuery(name = "TipoPreguntaencuesta.findAll", query = "SELECT t FROM TipoPreguntaencuesta t"),
    @NamedQuery(name = "TipoPreguntaencuesta.findByIdTipopregunta", query = "SELECT t FROM TipoPreguntaencuesta t WHERE t.idTipopregunta = :idTipopregunta"),
    @NamedQuery(name = "TipoPreguntaencuesta.findByDescripcionTipopregunta", query = "SELECT t FROM TipoPreguntaencuesta t WHERE t.descripcionTipopregunta = :descripcionTipopregunta")})
public class TipoPreguntaencuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipopregunta")
    private Long idTipopregunta;
    @Column(name = "descripcion_tipopregunta")
    private String descripcionTipopregunta;
    @OneToMany(mappedBy = "idTipopregunta")
    private List<Pregunta> preguntaList;

    public TipoPreguntaencuesta() {
    }

    public TipoPreguntaencuesta(Long idTipopregunta) {
        this.idTipopregunta = idTipopregunta;
    }

    public Long getIdTipopregunta() {
        return idTipopregunta;
    }

    public void setIdTipopregunta(Long idTipopregunta) {
        this.idTipopregunta = idTipopregunta;
    }

    public String getDescripcionTipopregunta() {
        return descripcionTipopregunta;
    }

    public void setDescripcionTipopregunta(String descripcionTipopregunta) {
        this.descripcionTipopregunta = descripcionTipopregunta;
    }

    public List<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(List<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipopregunta != null ? idTipopregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPreguntaencuesta)) {
            return false;
        }
        TipoPreguntaencuesta other = (TipoPreguntaencuesta) object;
        if ((this.idTipopregunta == null && other.idTipopregunta != null) || (this.idTipopregunta != null && !this.idTipopregunta.equals(other.idTipopregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoPreguntaencuesta[ idTipopregunta=" + idTipopregunta + " ]";
    }
    
}
