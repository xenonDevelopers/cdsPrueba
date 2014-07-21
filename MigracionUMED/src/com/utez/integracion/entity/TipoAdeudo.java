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
@Table(name = "tipo_adeudo")
@NamedQueries({
    @NamedQuery(name = "TipoAdeudo.findAll", query = "SELECT t FROM TipoAdeudo t"),
    @NamedQuery(name = "TipoAdeudo.findByIdAdeudo", query = "SELECT t FROM TipoAdeudo t WHERE t.idAdeudo = :idAdeudo"),
    @NamedQuery(name = "TipoAdeudo.findByDescripcionAdeudo", query = "SELECT t FROM TipoAdeudo t WHERE t.descripcionAdeudo = :descripcionAdeudo")})
public class TipoAdeudo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_adeudo")
    private String idAdeudo;
    @Column(name = "descripcion_adeudo")
    private String descripcionAdeudo;
    @OneToMany(mappedBy = "tipoAdeudo")
    private List<AdeudoalumnoEsquematipoexamen> adeudoalumnoEsquematipoexamenList;

    public TipoAdeudo() {
    }

    public TipoAdeudo(String idAdeudo) {
        this.idAdeudo = idAdeudo;
    }

    public String getIdAdeudo() {
        return idAdeudo;
    }

    public void setIdAdeudo(String idAdeudo) {
        this.idAdeudo = idAdeudo;
    }

    public String getDescripcionAdeudo() {
        return descripcionAdeudo;
    }

    public void setDescripcionAdeudo(String descripcionAdeudo) {
        this.descripcionAdeudo = descripcionAdeudo;
    }

    public List<AdeudoalumnoEsquematipoexamen> getAdeudoalumnoEsquematipoexamenList() {
        return adeudoalumnoEsquematipoexamenList;
    }

    public void setAdeudoalumnoEsquematipoexamenList(List<AdeudoalumnoEsquematipoexamen> adeudoalumnoEsquematipoexamenList) {
        this.adeudoalumnoEsquematipoexamenList = adeudoalumnoEsquematipoexamenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdeudo != null ? idAdeudo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAdeudo)) {
            return false;
        }
        TipoAdeudo other = (TipoAdeudo) object;
        if ((this.idAdeudo == null && other.idAdeudo != null) || (this.idAdeudo != null && !this.idAdeudo.equals(other.idAdeudo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoAdeudo[ idAdeudo=" + idAdeudo + " ]";
    }
    
}
