/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Grupo;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "grupo_alumnoesquema")
@NamedQueries({
    @NamedQuery(name = "GrupoAlumnoesquema.findAll", query = "SELECT g FROM GrupoAlumnoesquema g"),
    @NamedQuery(name = "GrupoAlumnoesquema.findByIdEsquemaalumnoasignatura", query = "SELECT g FROM GrupoAlumnoesquema g WHERE g.idEsquemaalumnoasignatura = :idEsquemaalumnoasignatura")})
public class GrupoAlumnoesquema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_esquemaalumnoasignatura")
    private Long idEsquemaalumnoasignatura;
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")
    @ManyToOne
    private Grupo idGrupo;
    @JoinColumn(name = "id_esquemaalumnoasignatura", referencedColumnName = "id_esquemaalumnoasignatura", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private EsquemaAlumnoasignatura esquemaAlumnoasignatura;

    public GrupoAlumnoesquema() {
    }

    public GrupoAlumnoesquema(Long idEsquemaalumnoasignatura) {
        this.idEsquemaalumnoasignatura = idEsquemaalumnoasignatura;
    }

    public Long getIdEsquemaalumnoasignatura() {
        return idEsquemaalumnoasignatura;
    }

    public void setIdEsquemaalumnoasignatura(Long idEsquemaalumnoasignatura) {
        this.idEsquemaalumnoasignatura = idEsquemaalumnoasignatura;
    }

    public Grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupo idGrupo) {
        this.idGrupo = idGrupo;
    }

    public EsquemaAlumnoasignatura getEsquemaAlumnoasignatura() {
        return esquemaAlumnoasignatura;
    }

    public void setEsquemaAlumnoasignatura(EsquemaAlumnoasignatura esquemaAlumnoasignatura) {
        this.esquemaAlumnoasignatura = esquemaAlumnoasignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEsquemaalumnoasignatura != null ? idEsquemaalumnoasignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoAlumnoesquema)) {
            return false;
        }
        GrupoAlumnoesquema other = (GrupoAlumnoesquema) object;
        if ((this.idEsquemaalumnoasignatura == null && other.idEsquemaalumnoasignatura != null) || (this.idEsquemaalumnoasignatura != null && !this.idEsquemaalumnoasignatura.equals(other.idEsquemaalumnoasignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.GrupoAlumnoesquema[ idEsquemaalumnoasignatura=" + idEsquemaalumnoasignatura + " ]";
    }
    
}
