/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "fechasexamen")
@NamedQueries({
    @NamedQuery(name = "Fechasexamen.findAll", query = "SELECT f FROM Fechasexamen f"),
    @NamedQuery(name = "Fechasexamen.findByTipoexamen", query = "SELECT f FROM Fechasexamen f WHERE f.tipoexamen = :tipoexamen"),
    @NamedQuery(name = "Fechasexamen.findByFecha", query = "SELECT f FROM Fechasexamen f WHERE f.fecha = :fecha"),
    @NamedQuery(name = "Fechasexamen.findByHora", query = "SELECT f FROM Fechasexamen f WHERE f.hora = :hora"),
    @NamedQuery(name = "Fechasexamen.findByIdfechaexamen", query = "SELECT f FROM Fechasexamen f WHERE f.idfechaexamen = :idfechaexamen")})
public class Fechasexamen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "tipoexamen")
    private String tipoexamen;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfechaexamen")
    private Integer idfechaexamen;
    @JoinColumn(name = "idasesoriaasignatura", referencedColumnName = "idasesoriaasignatura")
    @ManyToOne
    private Asesoriaasignatura idasesoriaasignatura;
    @OneToMany(mappedBy = "idfechaexamen")
    private List<Historicogrupo> historicogrupoList;

    public Fechasexamen() {
    }

    public Fechasexamen(Integer idfechaexamen) {
        this.idfechaexamen = idfechaexamen;
    }

    public String getTipoexamen() {
        return tipoexamen;
    }

    public void setTipoexamen(String tipoexamen) {
        this.tipoexamen = tipoexamen;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Integer getIdfechaexamen() {
        return idfechaexamen;
    }

    public void setIdfechaexamen(Integer idfechaexamen) {
        this.idfechaexamen = idfechaexamen;
    }

    public Asesoriaasignatura getIdasesoriaasignatura() {
        return idasesoriaasignatura;
    }

    public void setIdasesoriaasignatura(Asesoriaasignatura idasesoriaasignatura) {
        this.idasesoriaasignatura = idasesoriaasignatura;
    }

    public List<Historicogrupo> getHistoricogrupoList() {
        return historicogrupoList;
    }

    public void setHistoricogrupoList(List<Historicogrupo> historicogrupoList) {
        this.historicogrupoList = historicogrupoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfechaexamen != null ? idfechaexamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fechasexamen)) {
            return false;
        }
        Fechasexamen other = (Fechasexamen) object;
        if ((this.idfechaexamen == null && other.idfechaexamen != null) || (this.idfechaexamen != null && !this.idfechaexamen.equals(other.idfechaexamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Fechasexamen[ idfechaexamen=" + idfechaexamen + " ]";
    }
    
}
