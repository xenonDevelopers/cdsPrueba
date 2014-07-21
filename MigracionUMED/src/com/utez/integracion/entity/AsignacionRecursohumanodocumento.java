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
@Table(name = "asignacion_recursohumanodocumento")
@NamedQueries({
    @NamedQuery(name = "AsignacionRecursohumanodocumento.findAll", query = "SELECT a FROM AsignacionRecursohumanodocumento a"),
    @NamedQuery(name = "AsignacionRecursohumanodocumento.findByIdAsignacionrecursohumanodocumento", query = "SELECT a FROM AsignacionRecursohumanodocumento a WHERE a.idAsignacionrecursohumanodocumento = :idAsignacionrecursohumanodocumento")})
public class AsignacionRecursohumanodocumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignacionrecursohumanodocumento")
    private Long idAsignacionrecursohumanodocumento;
    @JoinColumn(name = "id_recursohumano", referencedColumnName = "id_recursohumano")
    @ManyToOne
    private RecursoHumano idRecursohumano;
    @JoinColumn(name = "id_documento", referencedColumnName = "id_documento")
    @ManyToOne
    private Documento idDocumento;

    public AsignacionRecursohumanodocumento() {
    }

    public AsignacionRecursohumanodocumento(Long idAsignacionrecursohumanodocumento) {
        this.idAsignacionrecursohumanodocumento = idAsignacionrecursohumanodocumento;
    }

    public Long getIdAsignacionrecursohumanodocumento() {
        return idAsignacionrecursohumanodocumento;
    }

    public void setIdAsignacionrecursohumanodocumento(Long idAsignacionrecursohumanodocumento) {
        this.idAsignacionrecursohumanodocumento = idAsignacionrecursohumanodocumento;
    }

    public RecursoHumano getIdRecursohumano() {
        return idRecursohumano;
    }

    public void setIdRecursohumano(RecursoHumano idRecursohumano) {
        this.idRecursohumano = idRecursohumano;
    }

    public Documento getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Documento idDocumento) {
        this.idDocumento = idDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacionrecursohumanodocumento != null ? idAsignacionrecursohumanodocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionRecursohumanodocumento)) {
            return false;
        }
        AsignacionRecursohumanodocumento other = (AsignacionRecursohumanodocumento) object;
        if ((this.idAsignacionrecursohumanodocumento == null && other.idAsignacionrecursohumanodocumento != null) || (this.idAsignacionrecursohumanodocumento != null && !this.idAsignacionrecursohumanodocumento.equals(other.idAsignacionrecursohumanodocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionRecursohumanodocumento[ idAsignacionrecursohumanodocumento=" + idAsignacionrecursohumanodocumento + " ]";
    }
    
}
