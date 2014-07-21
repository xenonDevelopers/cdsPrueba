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
@Table(name = "aplicador")
@NamedQueries({
    @NamedQuery(name = "Aplicador.findAll", query = "SELECT a FROM Aplicador a"),
    @NamedQuery(name = "Aplicador.findByIdAplicador", query = "SELECT a FROM Aplicador a WHERE a.idAplicador = :idAplicador"),
    @NamedQuery(name = "Aplicador.findByEstadoAplicador", query = "SELECT a FROM Aplicador a WHERE a.estadoAplicador = :estadoAplicador")})
public class Aplicador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_aplicador")
    private Long idAplicador;
    @Column(name = "estado_aplicador")
    private String estadoAplicador;
    @OneToMany(mappedBy = "idAplicador")
    private List<AsignacionAplicador> asignacionAplicadorList;
    @JoinColumn(name = "id_recursohumano", referencedColumnName = "id_recursohumano")
    @ManyToOne
    private RecursoHumano idRecursohumano;

    public Aplicador() {
    }

    public Aplicador(Long idAplicador) {
        this.idAplicador = idAplicador;
    }

    public Long getIdAplicador() {
        return idAplicador;
    }

    public void setIdAplicador(Long idAplicador) {
        this.idAplicador = idAplicador;
    }

    public String getEstadoAplicador() {
        return estadoAplicador;
    }

    public void setEstadoAplicador(String estadoAplicador) {
        this.estadoAplicador = estadoAplicador;
    }

    public List<AsignacionAplicador> getAsignacionAplicadorList() {
        return asignacionAplicadorList;
    }

    public void setAsignacionAplicadorList(List<AsignacionAplicador> asignacionAplicadorList) {
        this.asignacionAplicadorList = asignacionAplicadorList;
    }

    public RecursoHumano getIdRecursohumano() {
        return idRecursohumano;
    }

    public void setIdRecursohumano(RecursoHumano idRecursohumano) {
        this.idRecursohumano = idRecursohumano;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAplicador != null ? idAplicador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aplicador)) {
            return false;
        }
        Aplicador other = (Aplicador) object;
        if ((this.idAplicador == null && other.idAplicador != null) || (this.idAplicador != null && !this.idAplicador.equals(other.idAplicador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Aplicador[ idAplicador=" + idAplicador + " ]";
    }
    
}
