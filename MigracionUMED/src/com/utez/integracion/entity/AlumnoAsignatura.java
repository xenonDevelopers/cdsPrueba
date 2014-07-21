/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Alumno;
import com.utez.secretariaAcademica.entity.Asignatura;
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
@Table(name = "alumno_asignatura")
@NamedQueries({
    @NamedQuery(name = "AlumnoAsignatura.findAll", query = "SELECT a FROM AlumnoAsignatura a"),
    @NamedQuery(name = "AlumnoAsignatura.findByIdAlumnoasignatura", query = "SELECT a FROM AlumnoAsignatura a WHERE a.idAlumnoasignatura = :idAlumnoasignatura")})
public class AlumnoAsignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_alumnoasignatura")
    private Long idAlumnoasignatura;
    @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura")
    @ManyToOne
    private Asignatura idAsignatura;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    @ManyToOne
    private Alumno matricula;
    @OneToMany(mappedBy = "idAlumnoasignatura")
    private List<EsquemaAlumnoasignatura> esquemaAlumnoasignaturaList;
    @OneToMany(mappedBy = "idAlumnoasignatura")
    private List<ExamenIndividual> examenIndividualList;

    public AlumnoAsignatura() {
    }

    public AlumnoAsignatura(Long idAlumnoasignatura) {
        this.idAlumnoasignatura = idAlumnoasignatura;
    }

    public Long getIdAlumnoasignatura() {
        return idAlumnoasignatura;
    }

    public void setIdAlumnoasignatura(Long idAlumnoasignatura) {
        this.idAlumnoasignatura = idAlumnoasignatura;
    }

    public Asignatura getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Asignatura idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public Alumno getMatricula() {
        return matricula;
    }

    public void setMatricula(Alumno matricula) {
        this.matricula = matricula;
    }

    public List<EsquemaAlumnoasignatura> getEsquemaAlumnoasignaturaList() {
        return esquemaAlumnoasignaturaList;
    }

    public void setEsquemaAlumnoasignaturaList(List<EsquemaAlumnoasignatura> esquemaAlumnoasignaturaList) {
        this.esquemaAlumnoasignaturaList = esquemaAlumnoasignaturaList;
    }

    public List<ExamenIndividual> getExamenIndividualList() {
        return examenIndividualList;
    }

    public void setExamenIndividualList(List<ExamenIndividual> examenIndividualList) {
        this.examenIndividualList = examenIndividualList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlumnoasignatura != null ? idAlumnoasignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlumnoAsignatura)) {
            return false;
        }
        AlumnoAsignatura other = (AlumnoAsignatura) object;
        if ((this.idAlumnoasignatura == null && other.idAlumnoasignatura != null) || (this.idAlumnoasignatura != null && !this.idAlumnoasignatura.equals(other.idAlumnoasignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AlumnoAsignatura[ idAlumnoasignatura=" + idAlumnoasignatura + " ]";
    }
    
}
