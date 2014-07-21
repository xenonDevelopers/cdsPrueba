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
@Table(name = "opcionestudio")
@NamedQueries({
    @NamedQuery(name = "Opcionestudio.findAll", query = "SELECT o FROM Opcionestudio o"),
    @NamedQuery(name = "Opcionestudio.findByOpcionestudio", query = "SELECT o FROM Opcionestudio o WHERE o.opcionestudio = :opcionestudio"),
    @NamedQuery(name = "Opcionestudio.findByDescripcion", query = "SELECT o FROM Opcionestudio o WHERE o.descripcion = :descripcion"),
    @NamedQuery(name = "Opcionestudio.findByCupomaximo", query = "SELECT o FROM Opcionestudio o WHERE o.cupomaximo = :cupomaximo"),
    @NamedQuery(name = "Opcionestudio.findByCupominimo", query = "SELECT o FROM Opcionestudio o WHERE o.cupominimo = :cupominimo"),
    @NamedQuery(name = "Opcionestudio.findByNivelacademico", query = "SELECT o FROM Opcionestudio o WHERE o.nivelacademico = :nivelacademico")})
public class Opcionestudio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "opcionestudio")
    private String opcionestudio;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "cupomaximo")
    private Integer cupomaximo;
    @Column(name = "cupominimo")
    private Integer cupominimo;
    @Column(name = "nivelacademico")
    private String nivelacademico;
    @OneToMany(mappedBy = "opcionestudio")
    private List<Grupo> grupoList;

    public Opcionestudio() {
    }

    public Opcionestudio(String opcionestudio) {
        this.opcionestudio = opcionestudio;
    }

    public String getOpcionestudio() {
        return opcionestudio;
    }

    public void setOpcionestudio(String opcionestudio) {
        this.opcionestudio = opcionestudio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCupomaximo() {
        return cupomaximo;
    }

    public void setCupomaximo(Integer cupomaximo) {
        this.cupomaximo = cupomaximo;
    }

    public Integer getCupominimo() {
        return cupominimo;
    }

    public void setCupominimo(Integer cupominimo) {
        this.cupominimo = cupominimo;
    }

    public String getNivelacademico() {
        return nivelacademico;
    }

    public void setNivelacademico(String nivelacademico) {
        this.nivelacademico = nivelacademico;
    }

    public List<Grupo> getGrupoList() {
        return grupoList;
    }

    public void setGrupoList(List<Grupo> grupoList) {
        this.grupoList = grupoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opcionestudio != null ? opcionestudio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opcionestudio)) {
            return false;
        }
        Opcionestudio other = (Opcionestudio) object;
        if ((this.opcionestudio == null && other.opcionestudio != null) || (this.opcionestudio != null && !this.opcionestudio.equals(other.opcionestudio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Opcionestudio[ opcionestudio=" + opcionestudio + " ]";
    }
    
}
