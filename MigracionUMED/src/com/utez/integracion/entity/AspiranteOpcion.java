/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "aspirante_opcion")
@NamedQueries({
    @NamedQuery(name = "AspiranteOpcion.findAll", query = "SELECT a FROM AspiranteOpcion a"),
    @NamedQuery(name = "AspiranteOpcion.findByIdAspiranteopcion", query = "SELECT a FROM AspiranteOpcion a WHERE a.aspiranteOpcionPK.idAspiranteopcion = :idAspiranteopcion"),
    @NamedQuery(name = "AspiranteOpcion.findByIdAspirante", query = "SELECT a FROM AspiranteOpcion a WHERE a.aspiranteOpcionPK.idAspirante = :idAspirante"),
    @NamedQuery(name = "AspiranteOpcion.findByIdOpcionestudio", query = "SELECT a FROM AspiranteOpcion a WHERE a.aspiranteOpcionPK.idOpcionestudio = :idOpcionestudio"),
    @NamedQuery(name = "AspiranteOpcion.findByNumeroPrioridad", query = "SELECT a FROM AspiranteOpcion a WHERE a.numeroPrioridad = :numeroPrioridad")})
public class AspiranteOpcion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AspiranteOpcionPK aspiranteOpcionPK;
    @Column(name = "numero_prioridad")
    private Integer numeroPrioridad;
    @JoinColumn(name = "id_opcionestudio", referencedColumnName = "id_opcionestudio", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private OpcionEstudio opcionEstudio;
    @JoinColumn(name = "id_aspirante", referencedColumnName = "id_aspirante", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Aspirante aspirante;

    public AspiranteOpcion() {
    }

    public AspiranteOpcion(AspiranteOpcionPK aspiranteOpcionPK) {
        this.aspiranteOpcionPK = aspiranteOpcionPK;
    }

    public AspiranteOpcion(long idAspiranteopcion, long idAspirante, String idOpcionestudio) {
        this.aspiranteOpcionPK = new AspiranteOpcionPK(idAspiranteopcion, idAspirante, idOpcionestudio);
    }

    public AspiranteOpcionPK getAspiranteOpcionPK() {
        return aspiranteOpcionPK;
    }

    public void setAspiranteOpcionPK(AspiranteOpcionPK aspiranteOpcionPK) {
        this.aspiranteOpcionPK = aspiranteOpcionPK;
    }

    public Integer getNumeroPrioridad() {
        return numeroPrioridad;
    }

    public void setNumeroPrioridad(Integer numeroPrioridad) {
        this.numeroPrioridad = numeroPrioridad;
    }

    public OpcionEstudio getOpcionEstudio() {
        return opcionEstudio;
    }

    public void setOpcionEstudio(OpcionEstudio opcionEstudio) {
        this.opcionEstudio = opcionEstudio;
    }

    public Aspirante getAspirante() {
        return aspirante;
    }

    public void setAspirante(Aspirante aspirante) {
        this.aspirante = aspirante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aspiranteOpcionPK != null ? aspiranteOpcionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AspiranteOpcion)) {
            return false;
        }
        AspiranteOpcion other = (AspiranteOpcion) object;
        if ((this.aspiranteOpcionPK == null && other.aspiranteOpcionPK != null) || (this.aspiranteOpcionPK != null && !this.aspiranteOpcionPK.equals(other.aspiranteOpcionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AspiranteOpcion[ aspiranteOpcionPK=" + aspiranteOpcionPK + " ]";
    }
    
}
