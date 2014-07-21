/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.entity;

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
@Table(name = "bloqueasignatura")
@NamedQueries({
    @NamedQuery(name = "Bloqueasignatura.findAll", query = "SELECT b FROM Bloqueasignatura b"),
    @NamedQuery(name = "Bloqueasignatura.findByIdbloqueasignatura", query = "SELECT b FROM Bloqueasignatura b WHERE b.idbloqueasignatura = :idbloqueasignatura"),
    @NamedQuery(name = "Bloqueasignatura.findByNumeroorden", query = "SELECT b FROM Bloqueasignatura b WHERE b.numeroorden = :numeroorden"),
    @NamedQuery(name = "Bloqueasignatura.findByBloque", query = "SELECT b FROM Bloqueasignatura b WHERE b.bloque = :bloque"),
    @NamedQuery(name = "Bloqueasignatura.findByNumeroaxo", query = "SELECT b FROM Bloqueasignatura b WHERE b.numeroaxo = :numeroaxo"),
    @NamedQuery(name = "Bloqueasignatura.findByEstado", query = "SELECT b FROM Bloqueasignatura b WHERE b.estado = :estado")})
public class Bloqueasignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbloqueasignatura")
    private Integer idbloqueasignatura;
    @Column(name = "numeroorden")
    private Integer numeroorden;
    @Column(name = "bloque")
    private Integer bloque;
    @Column(name = "numeroaxo")
    private Integer numeroaxo;
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "idasignatura", referencedColumnName = "idasignatura")
    @ManyToOne
    private Asignatura idasignatura;

    public Bloqueasignatura() {
    }

    public Bloqueasignatura(Integer idbloqueasignatura) {
        this.idbloqueasignatura = idbloqueasignatura;
    }

    public Integer getIdbloqueasignatura() {
        return idbloqueasignatura;
    }

    public void setIdbloqueasignatura(Integer idbloqueasignatura) {
        this.idbloqueasignatura = idbloqueasignatura;
    }

    public Integer getNumeroorden() {
        return numeroorden;
    }

    public void setNumeroorden(Integer numeroorden) {
        this.numeroorden = numeroorden;
    }

    public Integer getBloque() {
        return bloque;
    }

    public void setBloque(Integer bloque) {
        this.bloque = bloque;
    }

    public Integer getNumeroaxo() {
        return numeroaxo;
    }

    public void setNumeroaxo(Integer numeroaxo) {
        this.numeroaxo = numeroaxo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Asignatura getIdasignatura() {
        return idasignatura;
    }

    public void setIdasignatura(Asignatura idasignatura) {
        this.idasignatura = idasignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbloqueasignatura != null ? idbloqueasignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bloqueasignatura)) {
            return false;
        }
        Bloqueasignatura other = (Bloqueasignatura) object;
        if ((this.idbloqueasignatura == null && other.idbloqueasignatura != null) || (this.idbloqueasignatura != null && !this.idbloqueasignatura.equals(other.idbloqueasignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Bloqueasignatura[ idbloqueasignatura=" + idbloqueasignatura + " ]";
    }
    
}
