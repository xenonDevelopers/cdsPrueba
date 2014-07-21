/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "tipo_ponderacionevaluacionlinea")
@NamedQueries({
    @NamedQuery(name = "TipoPonderacionevaluacionlinea.findAll", query = "SELECT t FROM TipoPonderacionevaluacionlinea t"),
    @NamedQuery(name = "TipoPonderacionevaluacionlinea.findByIdTipoponderacionevaluacionlinea", query = "SELECT t FROM TipoPonderacionevaluacionlinea t WHERE t.idTipoponderacionevaluacionlinea = :idTipoponderacionevaluacionlinea"),
    @NamedQuery(name = "TipoPonderacionevaluacionlinea.findByPonderacionMinima", query = "SELECT t FROM TipoPonderacionevaluacionlinea t WHERE t.ponderacionMinima = :ponderacionMinima"),
    @NamedQuery(name = "TipoPonderacionevaluacionlinea.findByPonderacionMaxima", query = "SELECT t FROM TipoPonderacionevaluacionlinea t WHERE t.ponderacionMaxima = :ponderacionMaxima"),
    @NamedQuery(name = "TipoPonderacionevaluacionlinea.findByComentario", query = "SELECT t FROM TipoPonderacionevaluacionlinea t WHERE t.comentario = :comentario")})
public class TipoPonderacionevaluacionlinea implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipoponderacionevaluacionlinea")
    private Long idTipoponderacionevaluacionlinea;
    @Column(name = "ponderacion_minima")
    private Integer ponderacionMinima;
    @Column(name = "ponderacion_maxima")
    private Integer ponderacionMaxima;
    @Column(name = "comentario")
    private String comentario;

    public TipoPonderacionevaluacionlinea() {
    }

    public TipoPonderacionevaluacionlinea(Long idTipoponderacionevaluacionlinea) {
        this.idTipoponderacionevaluacionlinea = idTipoponderacionevaluacionlinea;
    }

    public Long getIdTipoponderacionevaluacionlinea() {
        return idTipoponderacionevaluacionlinea;
    }

    public void setIdTipoponderacionevaluacionlinea(Long idTipoponderacionevaluacionlinea) {
        this.idTipoponderacionevaluacionlinea = idTipoponderacionevaluacionlinea;
    }

    public Integer getPonderacionMinima() {
        return ponderacionMinima;
    }

    public void setPonderacionMinima(Integer ponderacionMinima) {
        this.ponderacionMinima = ponderacionMinima;
    }

    public Integer getPonderacionMaxima() {
        return ponderacionMaxima;
    }

    public void setPonderacionMaxima(Integer ponderacionMaxima) {
        this.ponderacionMaxima = ponderacionMaxima;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoponderacionevaluacionlinea != null ? idTipoponderacionevaluacionlinea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPonderacionevaluacionlinea)) {
            return false;
        }
        TipoPonderacionevaluacionlinea other = (TipoPonderacionevaluacionlinea) object;
        if ((this.idTipoponderacionevaluacionlinea == null && other.idTipoponderacionevaluacionlinea != null) || (this.idTipoponderacionevaluacionlinea != null && !this.idTipoponderacionevaluacionlinea.equals(other.idTipoponderacionevaluacionlinea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoPonderacionevaluacionlinea[ idTipoponderacionevaluacionlinea=" + idTipoponderacionevaluacionlinea + " ]";
    }
    
}
