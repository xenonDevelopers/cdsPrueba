/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Asignatura;
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
@Table(name = "bloque_asignatura")
@NamedQueries({
    @NamedQuery(name = "BloqueAsignatura.findAll", query = "SELECT b FROM BloqueAsignatura b"),
    @NamedQuery(name = "BloqueAsignatura.findByIdBloqueasignatura", query = "SELECT b FROM BloqueAsignatura b WHERE b.idBloqueasignatura = :idBloqueasignatura"),
    @NamedQuery(name = "BloqueAsignatura.findByNumeroOrden", query = "SELECT b FROM BloqueAsignatura b WHERE b.numeroOrden = :numeroOrden"),
    @NamedQuery(name = "BloqueAsignatura.findByBloque", query = "SELECT b FROM BloqueAsignatura b WHERE b.bloque = :bloque"),
    @NamedQuery(name = "BloqueAsignatura.findByNumeroAxo", query = "SELECT b FROM BloqueAsignatura b WHERE b.numeroAxo = :numeroAxo")})
public class BloqueAsignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bloqueasignatura")
    private Long idBloqueasignatura;
    @Column(name = "numero_orden")
    private Integer numeroOrden;
    @Column(name = "bloque")
    private Integer bloque;
    @Column(name = "numero_axo")
    private Integer numeroAxo;
    @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura")
    @ManyToOne
    private Asignatura idAsignatura;

    public BloqueAsignatura() {
    }

    public BloqueAsignatura(Long idBloqueasignatura) {
        this.idBloqueasignatura = idBloqueasignatura;
    }

    public Long getIdBloqueasignatura() {
        return idBloqueasignatura;
    }

    public void setIdBloqueasignatura(Long idBloqueasignatura) {
        this.idBloqueasignatura = idBloqueasignatura;
    }

    public Integer getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(Integer numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public Integer getBloque() {
        return bloque;
    }

    public void setBloque(Integer bloque) {
        this.bloque = bloque;
    }

    public Integer getNumeroAxo() {
        return numeroAxo;
    }

    public void setNumeroAxo(Integer numeroAxo) {
        this.numeroAxo = numeroAxo;
    }

    public Asignatura getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Asignatura idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBloqueasignatura != null ? idBloqueasignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BloqueAsignatura)) {
            return false;
        }
        BloqueAsignatura other = (BloqueAsignatura) object;
        if ((this.idBloqueasignatura == null && other.idBloqueasignatura != null) || (this.idBloqueasignatura != null && !this.idBloqueasignatura.equals(other.idBloqueasignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.BloqueAsignatura[ idBloqueasignatura=" + idBloqueasignatura + " ]";
    }
    
}
