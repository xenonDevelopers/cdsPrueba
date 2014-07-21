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
@Table(name = "modulo")
@NamedQueries({
    @NamedQuery(name = "Modulo.findAll", query = "SELECT m FROM Modulo m"),
    @NamedQuery(name = "Modulo.findByIdModulo", query = "SELECT m FROM Modulo m WHERE m.idModulo = :idModulo"),
    @NamedQuery(name = "Modulo.findByNombreModulo", query = "SELECT m FROM Modulo m WHERE m.nombreModulo = :nombreModulo")})
public class Modulo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_modulo")
    private Long idModulo;
    @Column(name = "nombre_modulo")
    private String nombreModulo;
    @OneToMany(mappedBy = "idModulo")
    private List<CalificacionModulo> calificacionModuloList;
    @JoinColumn(name = "id_evento", referencedColumnName = "id_evento")
    @ManyToOne
    private Evento idEvento;

    public Modulo() {
    }

    public Modulo(Long idModulo) {
        this.idModulo = idModulo;
    }

    public Long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Long idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    public List<CalificacionModulo> getCalificacionModuloList() {
        return calificacionModuloList;
    }

    public void setCalificacionModuloList(List<CalificacionModulo> calificacionModuloList) {
        this.calificacionModuloList = calificacionModuloList;
    }

    public Evento getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Evento idEvento) {
        this.idEvento = idEvento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModulo != null ? idModulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modulo)) {
            return false;
        }
        Modulo other = (Modulo) object;
        if ((this.idModulo == null && other.idModulo != null) || (this.idModulo != null && !this.idModulo.equals(other.idModulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Modulo[ idModulo=" + idModulo + " ]";
    }
    
}
