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
@Table(name = "asignacion_autorintegradora")
@NamedQueries({
    @NamedQuery(name = "AsignacionAutorintegradora.findAll", query = "SELECT a FROM AsignacionAutorintegradora a"),
    @NamedQuery(name = "AsignacionAutorintegradora.findByIdAsignacionautorintegradora", query = "SELECT a FROM AsignacionAutorintegradora a WHERE a.idAsignacionautorintegradora = :idAsignacionautorintegradora")})
public class AsignacionAutorintegradora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignacionautorintegradora")
    private Long idAsignacionautorintegradora;
    @JoinColumn(name = "id_tipoautor", referencedColumnName = "id_tipoautor")
    @ManyToOne
    private TipoAutor idTipoautor;
    @JoinColumn(name = "id_autor", referencedColumnName = "id_asesor")
    @ManyToOne
    private Asesor idAutor;
    @JoinColumn(name = "id_actividadintegradora", referencedColumnName = "id_actividadintegradora")
    @ManyToOne
    private ActividadIntegradora idActividadintegradora;

    public AsignacionAutorintegradora() {
    }

    public AsignacionAutorintegradora(Long idAsignacionautorintegradora) {
        this.idAsignacionautorintegradora = idAsignacionautorintegradora;
    }

    public Long getIdAsignacionautorintegradora() {
        return idAsignacionautorintegradora;
    }

    public void setIdAsignacionautorintegradora(Long idAsignacionautorintegradora) {
        this.idAsignacionautorintegradora = idAsignacionautorintegradora;
    }

    public TipoAutor getIdTipoautor() {
        return idTipoautor;
    }

    public void setIdTipoautor(TipoAutor idTipoautor) {
        this.idTipoautor = idTipoautor;
    }

    public Asesor getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Asesor idAutor) {
        this.idAutor = idAutor;
    }

    public ActividadIntegradora getIdActividadintegradora() {
        return idActividadintegradora;
    }

    public void setIdActividadintegradora(ActividadIntegradora idActividadintegradora) {
        this.idActividadintegradora = idActividadintegradora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacionautorintegradora != null ? idAsignacionautorintegradora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionAutorintegradora)) {
            return false;
        }
        AsignacionAutorintegradora other = (AsignacionAutorintegradora) object;
        if ((this.idAsignacionautorintegradora == null && other.idAsignacionautorintegradora != null) || (this.idAsignacionautorintegradora != null && !this.idAsignacionautorintegradora.equals(other.idAsignacionautorintegradora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionAutorintegradora[ idAsignacionautorintegradora=" + idAsignacionautorintegradora + " ]";
    }
    
}
