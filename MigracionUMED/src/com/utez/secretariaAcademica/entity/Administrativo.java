/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.entity;

import com.utez.integracion.entity.HistorialCargo;
import com.utez.integracion.entity.RecursoHumano;
import java.io.Serializable;
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

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "administrativo")
@NamedQueries({
    @NamedQuery(name = "Administrativo.findAll", query = "SELECT a FROM Administrativo a"),
    @NamedQuery(name = "Administrativo.findByIdadministrativo", query = "SELECT a FROM Administrativo a WHERE a.idadministrativo = :idadministrativo"),
    @NamedQuery(name = "Administrativo.findByDepartamento", query = "SELECT a FROM Administrativo a WHERE a.departamento = :departamento"),
    @NamedQuery(name = "Administrativo.findByPuesto", query = "SELECT a FROM Administrativo a WHERE a.puesto = :puesto"),
    @NamedQuery(name = "Administrativo.findByEstado", query = "SELECT a FROM Administrativo a WHERE a.estado = :estado")})
public class Administrativo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_administrativo")
    private Long idAdministrativo;
    @Column(name = "estado_administrativo")
    private String estadoAdministrativo;
    @JoinTable(name = "administrativo_plantel", joinColumns = {
        @JoinColumn(name = "id_administrativo", referencedColumnName = "id_administrativo")}, inverseJoinColumns = {
        @JoinColumn(name = "id_plantel", referencedColumnName = "id_plantel")})
    @ManyToMany
    private List<Plantel> plantelList;
    @JoinColumn(name = "id_recursohumano", referencedColumnName = "id_recursohumano")
    @ManyToOne
    private RecursoHumano idRecursohumano;
    @OneToMany(mappedBy = "idAdministrativo")
    private List<HistorialCargo> historialCargoList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idadministrativo")
    private Integer idadministrativo;
    @Basic(optional = false)
    @Column(name = "departamento")
    private String departamento;
    @Basic(optional = false)
    @Column(name = "puesto")
    private String puesto;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "idrecursohumano", referencedColumnName = "idrecursohumano")
    @ManyToOne(optional = false)
    private Recursohumano idrecursohumano;

    public Administrativo() {
    }

    public Administrativo(Integer idadministrativo) {
        this.idadministrativo = idadministrativo;
    }

    public Administrativo(Integer idadministrativo, String departamento, String puesto, String estado) {
        this.idadministrativo = idadministrativo;
        this.departamento = departamento;
        this.puesto = puesto;
        this.estado = estado;
    }

    public Integer getIdadministrativo() {
        return idadministrativo;
    }

    public void setIdadministrativo(Integer idadministrativo) {
        this.idadministrativo = idadministrativo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
        hash += (idadministrativo != null ? idadministrativo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrativo)) {
            return false;
        }
        Administrativo other = (Administrativo) object;
        if ((this.idadministrativo == null && other.idadministrativo != null) || (this.idadministrativo != null && !this.idadministrativo.equals(other.idadministrativo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Administrativo[ idadministrativo=" + idadministrativo + " ]";
    }

    public Administrativo(Long idAdministrativo) {
        this.idAdministrativo = idAdministrativo;
    }

    public Long getIdAdministrativo() {
        return idAdministrativo;
    }

    public void setIdAdministrativo(Long idAdministrativo) {
        this.idAdministrativo = idAdministrativo;
    }

    public String getEstadoAdministrativo() {
        return estadoAdministrativo;
    }

    public void setEstadoAdministrativo(String estadoAdministrativo) {
        this.estadoAdministrativo = estadoAdministrativo;
    }

    public List<Plantel> getPlantelList() {
        return plantelList;
    }

    public void setPlantelList(List<Plantel> plantelList) {
        this.plantelList = plantelList;
    }

    public RecursoHumano getIdRecursohumano() {
        return idRecursohumano;
    }

    public void setIdRecursohumano(RecursoHumano idRecursohumano) {
        this.idRecursohumano = idRecursohumano;
    }

    public List<HistorialCargo> getHistorialCargoList() {
        return historialCargoList;
    }

    public void setHistorialCargoList(List<HistorialCargo> historialCargoList) {
        this.historialCargoList = historialCargoList;
    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (idAdministrativo != null ? idAdministrativo.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Administrativo)) {
//            return false;
//        }
//        Administrativo other = (Administrativo) object;
//        if ((this.idAdministrativo == null && other.idAdministrativo != null) || (this.idAdministrativo != null && !this.idAdministrativo.equals(other.idAdministrativo))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "com.utez.secretariaAcademica.entity.Administrativo[ idAdministrativo=" + idAdministrativo + " ]";
//    }
    
}
