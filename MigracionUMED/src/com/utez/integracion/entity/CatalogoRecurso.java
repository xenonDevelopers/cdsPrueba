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
@Table(name = "catalogo_recurso")
@NamedQueries({
    @NamedQuery(name = "CatalogoRecurso.findAll", query = "SELECT c FROM CatalogoRecurso c"),
    @NamedQuery(name = "CatalogoRecurso.findByIdCatalogo", query = "SELECT c FROM CatalogoRecurso c WHERE c.idCatalogo = :idCatalogo"),
    @NamedQuery(name = "CatalogoRecurso.findByNombreCatalogo", query = "SELECT c FROM CatalogoRecurso c WHERE c.nombreCatalogo = :nombreCatalogo")})
public class CatalogoRecurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_catalogo")
    private Integer idCatalogo;
    @Column(name = "nombre_catalogo")
    private String nombreCatalogo;
    @OneToMany(mappedBy = "idCatalogo")
    private List<Recurso> recursoList;

    public CatalogoRecurso() {
    }

    public CatalogoRecurso(Integer idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public Integer getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(Integer idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public String getNombreCatalogo() {
        return nombreCatalogo;
    }

    public void setNombreCatalogo(String nombreCatalogo) {
        this.nombreCatalogo = nombreCatalogo;
    }

    public List<Recurso> getRecursoList() {
        return recursoList;
    }

    public void setRecursoList(List<Recurso> recursoList) {
        this.recursoList = recursoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatalogo != null ? idCatalogo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatalogoRecurso)) {
            return false;
        }
        CatalogoRecurso other = (CatalogoRecurso) object;
        if ((this.idCatalogo == null && other.idCatalogo != null) || (this.idCatalogo != null && !this.idCatalogo.equals(other.idCatalogo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.CatalogoRecurso[ idCatalogo=" + idCatalogo + " ]";
    }
    
}
