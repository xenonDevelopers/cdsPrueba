/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.entity;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "asesoriaasignatura")
@NamedQueries({
    @NamedQuery(name = "Asesoriaasignatura.findAll", query = "SELECT a FROM Asesoriaasignatura a"),
    @NamedQuery(name = "Asesoriaasignatura.findByIdasesoriaasignatura", query = "SELECT a FROM Asesoriaasignatura a WHERE a.idasesoriaasignatura = :idasesoriaasignatura"),
    @NamedQuery(name = "Asesoriaasignatura.findByBloque", query = "SELECT a FROM Asesoriaasignatura a WHERE a.bloque = :bloque"),
    @NamedQuery(name = "Asesoriaasignatura.findByNumeroorden", query = "SELECT a FROM Asesoriaasignatura a WHERE a.numeroorden = :numeroorden")})
public class Asesoriaasignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idasesoriaasignatura")
    private Integer idasesoriaasignatura;
    @Column(name = "bloque")
    private Integer bloque;
    @Column(name = "numeroorden")
    private Integer numeroorden;
    @OneToMany(mappedBy = "idasesoriaasignatura")
    private List<Fechaasesoria> fechaasesoriaList;
    @JoinColumn(name = "idcalendario", referencedColumnName = "idcalendario")
    @ManyToOne
    private Calendario idcalendario;
    @JoinColumn(name = "idasignatura", referencedColumnName = "idasignatura")
    @ManyToOne
    private Asignatura idasignatura;
    @JoinColumn(name = "idasesor", referencedColumnName = "idasesor")
    @ManyToOne
    private Asesor idasesor;
    @OneToMany(mappedBy = "idasesoriaasignatura")
    private List<Fechasexamen> fechasexamenList;

    public Asesoriaasignatura() {
    }

    public Asesoriaasignatura(Integer idasesoriaasignatura) {
        this.idasesoriaasignatura = idasesoriaasignatura;
    }

    public Integer getIdasesoriaasignatura() {
        return idasesoriaasignatura;
    }

    public void setIdasesoriaasignatura(Integer idasesoriaasignatura) {
        this.idasesoriaasignatura = idasesoriaasignatura;
    }

    public Integer getBloque() {
        return bloque;
    }

    public void setBloque(Integer bloque) {
        this.bloque = bloque;
    }

    public Integer getNumeroorden() {
        return numeroorden;
    }

    public void setNumeroorden(Integer numeroorden) {
        this.numeroorden = numeroorden;
    }

    public List<Fechaasesoria> getFechaasesoriaList() {
        return fechaasesoriaList;
    }

    public void setFechaasesoriaList(List<Fechaasesoria> fechaasesoriaList) {
        this.fechaasesoriaList = fechaasesoriaList;
    }

    public Calendario getIdcalendario() {
        return idcalendario;
    }

    public void setIdcalendario(Calendario idcalendario) {
        this.idcalendario = idcalendario;
    }

    public Asignatura getIdasignatura() {
        return idasignatura;
    }

    public void setIdasignatura(Asignatura idasignatura) {
        this.idasignatura = idasignatura;
    }

    public Asesor getIdasesor() {
        return idasesor;
    }

    public void setIdasesor(Asesor idasesor) {
        this.idasesor = idasesor;
    }

    public List<Fechasexamen> getFechasexamenList() {
        return fechasexamenList;
    }

    public void setFechasexamenList(List<Fechasexamen> fechasexamenList) {
        this.fechasexamenList = fechasexamenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idasesoriaasignatura != null ? idasesoriaasignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asesoriaasignatura)) {
            return false;
        }
        Asesoriaasignatura other = (Asesoriaasignatura) object;
        if ((this.idasesoriaasignatura == null && other.idasesoriaasignatura != null) || (this.idasesoriaasignatura != null && !this.idasesoriaasignatura.equals(other.idasesoriaasignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Asesoriaasignatura[ idasesoriaasignatura=" + idasesoriaasignatura + " ]";
    }
    
}
