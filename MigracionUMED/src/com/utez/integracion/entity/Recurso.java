/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "recurso")
@NamedQueries({
    @NamedQuery(name = "Recurso.findAll", query = "SELECT r FROM Recurso r"),
    @NamedQuery(name = "Recurso.findByIdRecurso", query = "SELECT r FROM Recurso r WHERE r.idRecurso = :idRecurso"),
    @NamedQuery(name = "Recurso.findByNombreRecurso", query = "SELECT r FROM Recurso r WHERE r.nombreRecurso = :nombreRecurso"),
    @NamedQuery(name = "Recurso.findByDescripcionRecurso", query = "SELECT r FROM Recurso r WHERE r.descripcionRecurso = :descripcionRecurso"),
    @NamedQuery(name = "Recurso.findByRutaRecurso", query = "SELECT r FROM Recurso r WHERE r.rutaRecurso = :rutaRecurso"),
    @NamedQuery(name = "Recurso.findByReferencia", query = "SELECT r FROM Recurso r WHERE r.referencia = :referencia")})
public class Recurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_recurso")
    private Long idRecurso;
    @Column(name = "nombre_recurso")
    private String nombreRecurso;
    @Column(name = "descripcion_recurso")
    private String descripcionRecurso;
    @Column(name = "ruta_recurso")
    private String rutaRecurso;
    @Column(name = "referencia")
    private String referencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recurso")
    private List<RolRecursopermiso> rolRecursopermisoList;
    @JoinColumn(name = "id_catalogo", referencedColumnName = "id_catalogo")
    @ManyToOne
    private CatalogoRecurso idCatalogo;

    public Recurso() {
    }

    public Recurso(Long idRecurso) {
        this.idRecurso = idRecurso;
    }

    public Long getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(Long idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getNombreRecurso() {
        return nombreRecurso;
    }

    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }

    public String getDescripcionRecurso() {
        return descripcionRecurso;
    }

    public void setDescripcionRecurso(String descripcionRecurso) {
        this.descripcionRecurso = descripcionRecurso;
    }

    public String getRutaRecurso() {
        return rutaRecurso;
    }

    public void setRutaRecurso(String rutaRecurso) {
        this.rutaRecurso = rutaRecurso;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public List<RolRecursopermiso> getRolRecursopermisoList() {
        return rolRecursopermisoList;
    }

    public void setRolRecursopermisoList(List<RolRecursopermiso> rolRecursopermisoList) {
        this.rolRecursopermisoList = rolRecursopermisoList;
    }

    public CatalogoRecurso getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(CatalogoRecurso idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRecurso != null ? idRecurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recurso)) {
            return false;
        }
        Recurso other = (Recurso) object;
        if ((this.idRecurso == null && other.idRecurso != null) || (this.idRecurso != null && !this.idRecurso.equals(other.idRecurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Recurso[ idRecurso=" + idRecurso + " ]";
    }
    
}
