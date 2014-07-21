/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.entity;

import com.utez.integracion.entity.AsesorTitulartitulacion;
import com.utez.integracion.entity.AsignacionAutorbanco;
import com.utez.integracion.entity.AsignacionAutorintegradora;
import com.utez.integracion.entity.AsignaturaAsesor;
import com.utez.integracion.entity.EsquemaAlumnoasignatura;
import com.utez.integracion.entity.ExamenExtemporaneo;
import com.utez.integracion.entity.ExamenIndividual;
import com.utez.integracion.entity.GrupoExamenextemporaneo;
import com.utez.integracion.entity.GrupoInduccion;
import com.utez.integracion.entity.ProgramacionOpcionc;
import com.utez.integracion.entity.RecursoHumano;
import com.utez.integracion.entity.SinodoExamen;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "asesor")
@NamedQueries({
    @NamedQuery(name = "Asesor.findAll", query = "SELECT a FROM Asesor a"),
    @NamedQuery(name = "Asesor.findByIdasesor", query = "SELECT a FROM Asesor a WHERE a.idasesor = :idasesor"),
    @NamedQuery(name = "Asesor.findByFechatitulacion", query = "SELECT a FROM Asesor a WHERE a.fechatitulacion = :fechatitulacion"),
    @NamedQuery(name = "Asesor.findByFechacedula", query = "SELECT a FROM Asesor a WHERE a.fechacedula = :fechacedula"),
    @NamedQuery(name = "Asesor.findByProfesion", query = "SELECT a FROM Asesor a WHERE a.profesion = :profesion"),
    @NamedQuery(name = "Asesor.findByEstado", query = "SELECT a FROM Asesor a WHERE a.estado = :estado"),
    @NamedQuery(name = "Asesor.findByNumerocedula", query = "SELECT a FROM Asesor a WHERE a.numerocedula = :numerocedula")})
public class Asesor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asesor")
    private Long idAsesor;
    @Column(name = "estado_asesor")
    private String estadoAsesor;
    @JoinTable(name = "programacion_opcioncasesor", joinColumns = {
        @JoinColumn(name = "id_asesor", referencedColumnName = "id_asesor")}, inverseJoinColumns = {
        @JoinColumn(name = "id_programacionasignatura", referencedColumnName = "id_programacionasignatura")})
    @ManyToMany
    private List<ProgramacionOpcionc> programacionOpcioncList;
    @JoinTable(name = "asesor_calificacion", joinColumns = {
        @JoinColumn(name = "id_asesor", referencedColumnName = "id_asesor")}, inverseJoinColumns = {
        @JoinColumn(name = "id_alumnoasignaturaesquema", referencedColumnName = "id_esquemaalumnoasignatura")})
    @ManyToMany
    private List<EsquemaAlumnoasignatura> esquemaAlumnoasignaturaList;
    @OneToMany(mappedBy = "idAsesorcalificador")
    private List<ExamenExtemporaneo> examenExtemporaneoList;
    @OneToMany(mappedBy = "idAutor")
    private List<AsignacionAutorintegradora> asignacionAutorintegradoraList;
    @OneToMany(mappedBy = "idAsesor")
    private List<AsesorTitulartitulacion> asesorTitulartitulacionList;
    @JoinColumn(name = "id_recursohumano", referencedColumnName = "id_recursohumano")
    @ManyToOne
    private RecursoHumano idRecursohumano;
    @OneToMany(mappedBy = "idAsesor")
    private List<SinodoExamen> sinodoExamenList;
    @OneToMany(mappedBy = "idAutor")
    private List<AsignacionAutorbanco> asignacionAutorbancoList;
    @OneToMany(mappedBy = "responsable")
    private List<GrupoInduccion> grupoInduccionList;
    @OneToMany(mappedBy = "idAsesor")
    private List<AsignaturaAsesor> asignaturaAsesorList;
    @OneToMany(mappedBy = "idAsesor")
    private List<ExamenIndividual> examenIndividualList;
    @OneToMany(mappedBy = "idAsesor")
    private List<GrupoExamenextemporaneo> grupoExamenextemporaneoList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idasesor")
    private Integer idasesor;
    @Column(name = "fechatitulacion")
    @Temporal(TemporalType.DATE)
    private Date fechatitulacion;
    @Column(name = "fechacedula")
    @Temporal(TemporalType.DATE)
    private Date fechacedula;
    @Column(name = "profesion")
    private String profesion;
    @Column(name = "estado")
    private String estado;
    @Column(name = "numerocedula")
    private String numerocedula;
    @JoinTable(name = "asesorplan", joinColumns = {
        @JoinColumn(name = "idasesor", referencedColumnName = "idasesor")}, inverseJoinColumns = {
        @JoinColumn(name = "rvoe", referencedColumnName = "rvoe")})
    @ManyToMany
    private List<Planestudios> planestudiosList;
    @OneToMany(mappedBy = "idasesor")
    private List<Asesoriaasignatura> asesoriaasignaturaList;
    @JoinColumn(name = "idrecursohumano", referencedColumnName = "idrecursohumano")
    @ManyToOne
    private Recursohumano idrecursohumano;

    public Asesor() {
    }

    public Asesor(Integer idasesor) {
        this.idasesor = idasesor;
    }

    public Integer getIdasesor() {
        return idasesor;
    }

    public void setIdasesor(Integer idasesor) {
        this.idasesor = idasesor;
    }

    public Date getFechatitulacion() {
        return fechatitulacion;
    }

    public void setFechatitulacion(Date fechatitulacion) {
        this.fechatitulacion = fechatitulacion;
    }

    public Date getFechacedula() {
        return fechacedula;
    }

    public void setFechacedula(Date fechacedula) {
        this.fechacedula = fechacedula;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumerocedula() {
        return numerocedula;
    }

    public void setNumerocedula(String numerocedula) {
        this.numerocedula = numerocedula;
    }

    public List<Planestudios> getPlanestudiosList() {
        return planestudiosList;
    }

    public void setPlanestudiosList(List<Planestudios> planestudiosList) {
        this.planestudiosList = planestudiosList;
    }

    public List<Asesoriaasignatura> getAsesoriaasignaturaList() {
        return asesoriaasignaturaList;
    }

    public void setAsesoriaasignaturaList(List<Asesoriaasignatura> asesoriaasignaturaList) {
        this.asesoriaasignaturaList = asesoriaasignaturaList;
    }

    public Recursohumano getIdrecursohumano() {
        return idrecursohumano;
    }

    public void setIdrecursohumano(Recursohumano idrecursohumano) {
        this.idrecursohumano = idrecursohumano;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idasesor != null ? idasesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asesor)) {
            return false;
        }
        Asesor other = (Asesor) object;
        if ((this.idasesor == null && other.idasesor != null) || (this.idasesor != null && !this.idasesor.equals(other.idasesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Asesor[ idasesor=" + idasesor + " ]";
    }

    public Asesor(Long idAsesor) {
        this.idAsesor = idAsesor;
    }

    public Long getIdAsesor() {
        return idAsesor;
    }

    public void setIdAsesor(Long idAsesor) {
        this.idAsesor = idAsesor;
    }

    public String getEstadoAsesor() {
        return estadoAsesor;
    }

    public void setEstadoAsesor(String estadoAsesor) {
        this.estadoAsesor = estadoAsesor;
    }

    public List<ProgramacionOpcionc> getProgramacionOpcioncList() {
        return programacionOpcioncList;
    }

    public void setProgramacionOpcioncList(List<ProgramacionOpcionc> programacionOpcioncList) {
        this.programacionOpcioncList = programacionOpcioncList;
    }

    public List<EsquemaAlumnoasignatura> getEsquemaAlumnoasignaturaList() {
        return esquemaAlumnoasignaturaList;
    }

    public void setEsquemaAlumnoasignaturaList(List<EsquemaAlumnoasignatura> esquemaAlumnoasignaturaList) {
        this.esquemaAlumnoasignaturaList = esquemaAlumnoasignaturaList;
    }

    public List<ExamenExtemporaneo> getExamenExtemporaneoList() {
        return examenExtemporaneoList;
    }

    public void setExamenExtemporaneoList(List<ExamenExtemporaneo> examenExtemporaneoList) {
        this.examenExtemporaneoList = examenExtemporaneoList;
    }

    public List<AsignacionAutorintegradora> getAsignacionAutorintegradoraList() {
        return asignacionAutorintegradoraList;
    }

    public void setAsignacionAutorintegradoraList(List<AsignacionAutorintegradora> asignacionAutorintegradoraList) {
        this.asignacionAutorintegradoraList = asignacionAutorintegradoraList;
    }

    public List<AsesorTitulartitulacion> getAsesorTitulartitulacionList() {
        return asesorTitulartitulacionList;
    }

    public void setAsesorTitulartitulacionList(List<AsesorTitulartitulacion> asesorTitulartitulacionList) {
        this.asesorTitulartitulacionList = asesorTitulartitulacionList;
    }

    public RecursoHumano getIdRecursohumano() {
        return idRecursohumano;
    }

    public void setIdRecursohumano(RecursoHumano idRecursohumano) {
        this.idRecursohumano = idRecursohumano;
    }

    public List<SinodoExamen> getSinodoExamenList() {
        return sinodoExamenList;
    }

    public void setSinodoExamenList(List<SinodoExamen> sinodoExamenList) {
        this.sinodoExamenList = sinodoExamenList;
    }

    public List<AsignacionAutorbanco> getAsignacionAutorbancoList() {
        return asignacionAutorbancoList;
    }

    public void setAsignacionAutorbancoList(List<AsignacionAutorbanco> asignacionAutorbancoList) {
        this.asignacionAutorbancoList = asignacionAutorbancoList;
    }

    public List<GrupoInduccion> getGrupoInduccionList() {
        return grupoInduccionList;
    }

    public void setGrupoInduccionList(List<GrupoInduccion> grupoInduccionList) {
        this.grupoInduccionList = grupoInduccionList;
    }

    public List<AsignaturaAsesor> getAsignaturaAsesorList() {
        return asignaturaAsesorList;
    }

    public void setAsignaturaAsesorList(List<AsignaturaAsesor> asignaturaAsesorList) {
        this.asignaturaAsesorList = asignaturaAsesorList;
    }

    public List<ExamenIndividual> getExamenIndividualList() {
        return examenIndividualList;
    }

    public void setExamenIndividualList(List<ExamenIndividual> examenIndividualList) {
        this.examenIndividualList = examenIndividualList;
    }

    public List<GrupoExamenextemporaneo> getGrupoExamenextemporaneoList() {
        return grupoExamenextemporaneoList;
    }

    public void setGrupoExamenextemporaneoList(List<GrupoExamenextemporaneo> grupoExamenextemporaneoList) {
        this.grupoExamenextemporaneoList = grupoExamenextemporaneoList;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (idAsesor != null ? idAsesor.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Asesor)) {
//            return false;
//        }
//        Asesor other = (Asesor) object;
//        if ((this.idAsesor == null && other.idAsesor != null) || (this.idAsesor != null && !this.idAsesor.equals(other.idAsesor))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "com.utez.secretariaAcademica.entity.Asesor[ idAsesor=" + idAsesor + " ]";
//    }
    
}
