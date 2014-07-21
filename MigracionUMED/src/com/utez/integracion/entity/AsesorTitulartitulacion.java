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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "asesor_titulartitulacion")
@NamedQueries({
    @NamedQuery(name = "AsesorTitulartitulacion.findAll", query = "SELECT a FROM AsesorTitulartitulacion a"),
    @NamedQuery(name = "AsesorTitulartitulacion.findByIdTramitetitulacion", query = "SELECT a FROM AsesorTitulartitulacion a WHERE a.idTramitetitulacion = :idTramitetitulacion")})
public class AsesorTitulartitulacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tramitetitulacion")
    private Long idTramitetitulacion;
    @JoinColumn(name = "id_tramitetitulacion", referencedColumnName = "id_tramitetitulacion", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TramiteTitulacion tramiteTitulacion;
    @JoinColumn(name = "id_asesor", referencedColumnName = "id_asesor")
    @ManyToOne
    private Asesor idAsesor;

    public AsesorTitulartitulacion() {
    }

    public AsesorTitulartitulacion(Long idTramitetitulacion) {
        this.idTramitetitulacion = idTramitetitulacion;
    }

    public Long getIdTramitetitulacion() {
        return idTramitetitulacion;
    }

    public void setIdTramitetitulacion(Long idTramitetitulacion) {
        this.idTramitetitulacion = idTramitetitulacion;
    }

    public TramiteTitulacion getTramiteTitulacion() {
        return tramiteTitulacion;
    }

    public void setTramiteTitulacion(TramiteTitulacion tramiteTitulacion) {
        this.tramiteTitulacion = tramiteTitulacion;
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
        hash += (idTramitetitulacion != null ? idTramitetitulacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsesorTitulartitulacion)) {
            return false;
        }
        AsesorTitulartitulacion other = (AsesorTitulartitulacion) object;
        if ((this.idTramitetitulacion == null && other.idTramitetitulacion != null) || (this.idTramitetitulacion != null && !this.idTramitetitulacion.equals(other.idTramitetitulacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsesorTitulartitulacion[ idTramitetitulacion=" + idTramitetitulacion + " ]";
    }
    
}
