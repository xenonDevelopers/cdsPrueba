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
@Table(name = "asignacion_autorbanco")
@NamedQueries({
    @NamedQuery(name = "AsignacionAutorbanco.findAll", query = "SELECT a FROM AsignacionAutorbanco a"),
    @NamedQuery(name = "AsignacionAutorbanco.findByIdAsignacionautorbanco", query = "SELECT a FROM AsignacionAutorbanco a WHERE a.idAsignacionautorbanco = :idAsignacionautorbanco")})
public class AsignacionAutorbanco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignacionautorbanco")
    private Long idAsignacionautorbanco;
    @JoinColumn(name = "id_tipoautor", referencedColumnName = "id_tipoautor")
    @ManyToOne
    private TipoAutor idTipoautor;
    @JoinColumn(name = "id_bancoreactivo", referencedColumnName = "id_bancoreactivo")
    @ManyToOne
    private BancoReactivo idBancoreactivo;
    @JoinColumn(name = "id_autor", referencedColumnName = "id_asesor")
    @ManyToOne
    private Asesor idAutor;

    public AsignacionAutorbanco() {
    }

    public AsignacionAutorbanco(Long idAsignacionautorbanco) {
        this.idAsignacionautorbanco = idAsignacionautorbanco;
    }

    public Long getIdAsignacionautorbanco() {
        return idAsignacionautorbanco;
    }

    public void setIdAsignacionautorbanco(Long idAsignacionautorbanco) {
        this.idAsignacionautorbanco = idAsignacionautorbanco;
    }

    public TipoAutor getIdTipoautor() {
        return idTipoautor;
    }

    public void setIdTipoautor(TipoAutor idTipoautor) {
        this.idTipoautor = idTipoautor;
    }

    public BancoReactivo getIdBancoreactivo() {
        return idBancoreactivo;
    }

    public void setIdBancoreactivo(BancoReactivo idBancoreactivo) {
        this.idBancoreactivo = idBancoreactivo;
    }

    public Asesor getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Asesor idAutor) {
        this.idAutor = idAutor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacionautorbanco != null ? idAsignacionautorbanco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionAutorbanco)) {
            return false;
        }
        AsignacionAutorbanco other = (AsignacionAutorbanco) object;
        if ((this.idAsignacionautorbanco == null && other.idAsignacionautorbanco != null) || (this.idAsignacionautorbanco != null && !this.idAsignacionautorbanco.equals(other.idAsignacionautorbanco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionAutorbanco[ idAsignacionautorbanco=" + idAsignacionautorbanco + " ]";
    }
    
}
