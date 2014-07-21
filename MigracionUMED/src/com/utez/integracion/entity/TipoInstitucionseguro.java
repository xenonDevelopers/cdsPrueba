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
@Table(name = "tipo_institucionseguro")
@NamedQueries({
    @NamedQuery(name = "TipoInstitucionseguro.findAll", query = "SELECT t FROM TipoInstitucionseguro t"),
    @NamedQuery(name = "TipoInstitucionseguro.findByIdInstitucionseguro", query = "SELECT t FROM TipoInstitucionseguro t WHERE t.idInstitucionseguro = :idInstitucionseguro"),
    @NamedQuery(name = "TipoInstitucionseguro.findByNombreInstitucion", query = "SELECT t FROM TipoInstitucionseguro t WHERE t.nombreInstitucion = :nombreInstitucion"),
    @NamedQuery(name = "TipoInstitucionseguro.findBySiglas", query = "SELECT t FROM TipoInstitucionseguro t WHERE t.siglas = :siglas")})
public class TipoInstitucionseguro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_institucionseguro")
    private Long idInstitucionseguro;
    @Column(name = "nombre_institucion")
    private String nombreInstitucion;
    @Column(name = "siglas")
    private String siglas;
    @OneToMany(mappedBy = "institucionSeguro")
    private List<SeguroSocial> seguroSocialList;

    public TipoInstitucionseguro() {
    }

    public TipoInstitucionseguro(Long idInstitucionseguro) {
        this.idInstitucionseguro = idInstitucionseguro;
    }

    public Long getIdInstitucionseguro() {
        return idInstitucionseguro;
    }

    public void setIdInstitucionseguro(Long idInstitucionseguro) {
        this.idInstitucionseguro = idInstitucionseguro;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public List<SeguroSocial> getSeguroSocialList() {
        return seguroSocialList;
    }

    public void setSeguroSocialList(List<SeguroSocial> seguroSocialList) {
        this.seguroSocialList = seguroSocialList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInstitucionseguro != null ? idInstitucionseguro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoInstitucionseguro)) {
            return false;
        }
        TipoInstitucionseguro other = (TipoInstitucionseguro) object;
        if ((this.idInstitucionseguro == null && other.idInstitucionseguro != null) || (this.idInstitucionseguro != null && !this.idInstitucionseguro.equals(other.idInstitucionseguro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoInstitucionseguro[ idInstitucionseguro=" + idInstitucionseguro + " ]";
    }
    
}
