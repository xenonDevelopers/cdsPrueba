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
@Table(name = "tipo_sexo")
@NamedQueries({
    @NamedQuery(name = "TipoSexo.findAll", query = "SELECT t FROM TipoSexo t"),
    @NamedQuery(name = "TipoSexo.findByIdTiposexo", query = "SELECT t FROM TipoSexo t WHERE t.idTiposexo = :idTiposexo"),
    @NamedQuery(name = "TipoSexo.findByDescripcionTiposexo", query = "SELECT t FROM TipoSexo t WHERE t.descripcionTiposexo = :descripcionTiposexo")})
public class TipoSexo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tiposexo")
    private Long idTiposexo;
    @Column(name = "descripcion_tiposexo")
    private String descripcionTiposexo;
    @OneToMany(mappedBy = "idTiposexo")
    private List<Persona> personaList;

    public TipoSexo() {
    }

    public TipoSexo(Long idTiposexo) {
        this.idTiposexo = idTiposexo;
    }

    public Long getIdTiposexo() {
        return idTiposexo;
    }

    public void setIdTiposexo(Long idTiposexo) {
        this.idTiposexo = idTiposexo;
    }

    public String getDescripcionTiposexo() {
        return descripcionTiposexo;
    }

    public void setDescripcionTiposexo(String descripcionTiposexo) {
        this.descripcionTiposexo = descripcionTiposexo;
    }

    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTiposexo != null ? idTiposexo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoSexo)) {
            return false;
        }
        TipoSexo other = (TipoSexo) object;
        if ((this.idTiposexo == null && other.idTiposexo != null) || (this.idTiposexo != null && !this.idTiposexo.equals(other.idTiposexo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoSexo[ idTiposexo=" + idTiposexo + " ]";
    }
    
}
