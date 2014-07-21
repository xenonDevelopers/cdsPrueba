/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Asesor;
import java.io.Serializable;
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
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "sinodo_examen")
@NamedQueries({
    @NamedQuery(name = "SinodoExamen.findAll", query = "SELECT s FROM SinodoExamen s"),
    @NamedQuery(name = "SinodoExamen.findByIdSinodo", query = "SELECT s FROM SinodoExamen s WHERE s.idSinodo = :idSinodo")})
public class SinodoExamen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sinodo")
    private Long idSinodo;
    @JoinColumn(name = "id_tramitetitulacion", referencedColumnName = "id_tramitetitulacion")
    @ManyToOne
    private TramiteTitulacion idTramitetitulacion;
    @JoinColumn(name = "id_tiposinodo", referencedColumnName = "id_tiposinodo")
    @ManyToOne
    private TipoSinodo idTiposinodo;
    @JoinColumn(name = "id_asesor", referencedColumnName = "id_asesor")
    @ManyToOne
    private Asesor idAsesor;

    public SinodoExamen() {
    }

    public SinodoExamen(Long idSinodo) {
        this.idSinodo = idSinodo;
    }

    public Long getIdSinodo() {
        return idSinodo;
    }

    public void setIdSinodo(Long idSinodo) {
        this.idSinodo = idSinodo;
    }

    public TramiteTitulacion getIdTramitetitulacion() {
        return idTramitetitulacion;
    }

    public void setIdTramitetitulacion(TramiteTitulacion idTramitetitulacion) {
        this.idTramitetitulacion = idTramitetitulacion;
    }

    public TipoSinodo getIdTiposinodo() {
        return idTiposinodo;
    }

    public void setIdTiposinodo(TipoSinodo idTiposinodo) {
        this.idTiposinodo = idTiposinodo;
    }

    public Asesor getIdAsesor() {
        return idAsesor;
    }

    public void setIdAsesor(Asesor idAsesor) {
        this.idAsesor = idAsesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSinodo != null ? idSinodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SinodoExamen)) {
            return false;
        }
        SinodoExamen other = (SinodoExamen) object;
        if ((this.idSinodo == null && other.idSinodo != null) || (this.idSinodo != null && !this.idSinodo.equals(other.idSinodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.SinodoExamen[ idSinodo=" + idSinodo + " ]";
    }
    
}
