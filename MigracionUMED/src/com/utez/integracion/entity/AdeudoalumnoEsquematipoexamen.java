/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "adeudoalumno_esquematipoexamen")
@NamedQueries({
    @NamedQuery(name = "AdeudoalumnoEsquematipoexamen.findAll", query = "SELECT a FROM AdeudoalumnoEsquematipoexamen a"),
    @NamedQuery(name = "AdeudoalumnoEsquematipoexamen.findByIdEsquemaalumnoasignatura", query = "SELECT a FROM AdeudoalumnoEsquematipoexamen a WHERE a.adeudoalumnoEsquematipoexamenPK.idEsquemaalumnoasignatura = :idEsquemaalumnoasignatura"),
    @NamedQuery(name = "AdeudoalumnoEsquematipoexamen.findByIdTipoexamen", query = "SELECT a FROM AdeudoalumnoEsquematipoexamen a WHERE a.adeudoalumnoEsquematipoexamenPK.idTipoexamen = :idTipoexamen"),
    @NamedQuery(name = "AdeudoalumnoEsquematipoexamen.findByFechaRegistroadeudo", query = "SELECT a FROM AdeudoalumnoEsquematipoexamen a WHERE a.fechaRegistroadeudo = :fechaRegistroadeudo")})
public class AdeudoalumnoEsquematipoexamen implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AdeudoalumnoEsquematipoexamenPK adeudoalumnoEsquematipoexamenPK;
    @Column(name = "fecha_registroadeudo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistroadeudo;
    @JoinColumn(name = "tipo_adeudo", referencedColumnName = "id_adeudo")
    @ManyToOne
    private TipoAdeudo tipoAdeudo;

    public AdeudoalumnoEsquematipoexamen() {
    }

    public AdeudoalumnoEsquematipoexamen(AdeudoalumnoEsquematipoexamenPK adeudoalumnoEsquematipoexamenPK) {
        this.adeudoalumnoEsquematipoexamenPK = adeudoalumnoEsquematipoexamenPK;
    }

    public AdeudoalumnoEsquematipoexamen(long idEsquemaalumnoasignatura, long idTipoexamen) {
        this.adeudoalumnoEsquematipoexamenPK = new AdeudoalumnoEsquematipoexamenPK(idEsquemaalumnoasignatura, idTipoexamen);
    }

    public AdeudoalumnoEsquematipoexamenPK getAdeudoalumnoEsquematipoexamenPK() {
        return adeudoalumnoEsquematipoexamenPK;
    }

    public void setAdeudoalumnoEsquematipoexamenPK(AdeudoalumnoEsquematipoexamenPK adeudoalumnoEsquematipoexamenPK) {
        this.adeudoalumnoEsquematipoexamenPK = adeudoalumnoEsquematipoexamenPK;
    }

    public Date getFechaRegistroadeudo() {
        return fechaRegistroadeudo;
    }

    public void setFechaRegistroadeudo(Date fechaRegistroadeudo) {
        this.fechaRegistroadeudo = fechaRegistroadeudo;
    }

    public TipoAdeudo getTipoAdeudo() {
        return tipoAdeudo;
    }

    public void setTipoAdeudo(TipoAdeudo tipoAdeudo) {
        this.tipoAdeudo = tipoAdeudo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adeudoalumnoEsquematipoexamenPK != null ? adeudoalumnoEsquematipoexamenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdeudoalumnoEsquematipoexamen)) {
            return false;
        }
        AdeudoalumnoEsquematipoexamen other = (AdeudoalumnoEsquematipoexamen) object;
        if ((this.adeudoalumnoEsquematipoexamenPK == null && other.adeudoalumnoEsquematipoexamenPK != null) || (this.adeudoalumnoEsquematipoexamenPK != null && !this.adeudoalumnoEsquematipoexamenPK.equals(other.adeudoalumnoEsquematipoexamenPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AdeudoalumnoEsquematipoexamen[ adeudoalumnoEsquematipoexamenPK=" + adeudoalumnoEsquematipoexamenPK + " ]";
    }
    
}
