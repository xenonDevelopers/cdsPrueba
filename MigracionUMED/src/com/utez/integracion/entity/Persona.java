/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "persona")
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByCurp", query = "SELECT p FROM Persona p WHERE p.curp = :curp"),
    @NamedQuery(name = "Persona.findByRfc", query = "SELECT p FROM Persona p WHERE p.rfc = :rfc"),
    @NamedQuery(name = "Persona.findByNombres", query = "SELECT p FROM Persona p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "Persona.findByApellidoPaterno", query = "SELECT p FROM Persona p WHERE p.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "Persona.findByApellidoMaterno", query = "SELECT p FROM Persona p WHERE p.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "Persona.findByFechaNacimiento", query = "SELECT p FROM Persona p WHERE p.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Persona.findByDireccion", query = "SELECT p FROM Persona p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Persona.findByCorreoElectronico", query = "SELECT p FROM Persona p WHERE p.correoElectronico = :correoElectronico")})
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "curp")
    private String curp;
    @Column(name = "rfc")
    private String rfc;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @OneToMany(mappedBy = "curp")
    private List<Fotografia> fotografiaList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "persona")
    private Usuario usuario;
    @OneToMany(mappedBy = "curp")
    private List<RecursoHumano> recursoHumanoList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "persona")
    private SeguroSocial seguroSocial;
    @OneToMany(mappedBy = "curp")
    private List<CalificacionModulo> calificacionModuloList;
    @JoinColumn(name = "id_tiposexo", referencedColumnName = "id_tiposexo")
    @ManyToOne
    private TipoSexo idTiposexo;
    @JoinColumn(name = "id_tipoestadocivil", referencedColumnName = "id_tipoestadocivil")
    @ManyToOne
    private TipoEstadocivil idTipoestadocivil;
    @JoinColumn(name = "id_municipionacimiento", referencedColumnName = "id_municipio")
    @ManyToOne
    private Municipio idMunicipionacimiento;
    @JoinColumn(name = "id_asentamiento", referencedColumnName = "id_asentamiento")
    @ManyToOne
    private Asentamiento idAsentamiento;
    @OneToMany(mappedBy = "curp")
    private List<Telefono> telefonoList;
    @OneToMany(mappedBy = "curp")
    private List<Aspirante> aspiranteList;

    public Persona() {
    }

    public Persona(String curp) {
        this.curp = curp;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public List<Fotografia> getFotografiaList() {
        return fotografiaList;
    }

    public void setFotografiaList(List<Fotografia> fotografiaList) {
        this.fotografiaList = fotografiaList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<RecursoHumano> getRecursoHumanoList() {
        return recursoHumanoList;
    }

    public void setRecursoHumanoList(List<RecursoHumano> recursoHumanoList) {
        this.recursoHumanoList = recursoHumanoList;
    }

    public SeguroSocial getSeguroSocial() {
        return seguroSocial;
    }

    public void setSeguroSocial(SeguroSocial seguroSocial) {
        this.seguroSocial = seguroSocial;
    }

    public List<CalificacionModulo> getCalificacionModuloList() {
        return calificacionModuloList;
    }

    public void setCalificacionModuloList(List<CalificacionModulo> calificacionModuloList) {
        this.calificacionModuloList = calificacionModuloList;
    }

    public TipoSexo getIdTiposexo() {
        return idTiposexo;
    }

    public void setIdTiposexo(TipoSexo idTiposexo) {
        this.idTiposexo = idTiposexo;
    }

    public TipoEstadocivil getIdTipoestadocivil() {
        return idTipoestadocivil;
    }

    public void setIdTipoestadocivil(TipoEstadocivil idTipoestadocivil) {
        this.idTipoestadocivil = idTipoestadocivil;
    }

    public Municipio getIdMunicipionacimiento() {
        return idMunicipionacimiento;
    }

    public void setIdMunicipionacimiento(Municipio idMunicipionacimiento) {
        this.idMunicipionacimiento = idMunicipionacimiento;
    }

    public Asentamiento getIdAsentamiento() {
        return idAsentamiento;
    }

    public void setIdAsentamiento(Asentamiento idAsentamiento) {
        this.idAsentamiento = idAsentamiento;
    }

    public List<Telefono> getTelefonoList() {
        return telefonoList;
    }

    public void setTelefonoList(List<Telefono> telefonoList) {
        this.telefonoList = telefonoList;
    }

    public List<Aspirante> getAspiranteList() {
        return aspiranteList;
    }

    public void setAspiranteList(List<Aspirante> aspiranteList) {
        this.aspiranteList = aspiranteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (curp != null ? curp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.curp == null && other.curp != null) || (this.curp != null && !this.curp.equals(other.curp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Persona[ curp=" + curp + " ]";
    }
    
}
