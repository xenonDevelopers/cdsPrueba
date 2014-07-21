/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "recursohumano")
@NamedQueries({
    @NamedQuery(name = "Recursohumano.findAll", query = "SELECT r FROM Recursohumano r"),
    @NamedQuery(name = "Recursohumano.findByIdrecursohumano", query = "SELECT r FROM Recursohumano r WHERE r.idrecursohumano = :idrecursohumano"),
    @NamedQuery(name = "Recursohumano.findByApellidopaterno", query = "SELECT r FROM Recursohumano r WHERE r.apellidopaterno = :apellidopaterno"),
    @NamedQuery(name = "Recursohumano.findByApellidomaterno", query = "SELECT r FROM Recursohumano r WHERE r.apellidomaterno = :apellidomaterno"),
    @NamedQuery(name = "Recursohumano.findByNombres", query = "SELECT r FROM Recursohumano r WHERE r.nombres = :nombres"),
    @NamedQuery(name = "Recursohumano.findByFechanacimiento", query = "SELECT r FROM Recursohumano r WHERE r.fechanacimiento = :fechanacimiento"),
    @NamedQuery(name = "Recursohumano.findBySexo", query = "SELECT r FROM Recursohumano r WHERE r.sexo = :sexo"),
    @NamedQuery(name = "Recursohumano.findByEstadocivil", query = "SELECT r FROM Recursohumano r WHERE r.estadocivil = :estadocivil"),
    @NamedQuery(name = "Recursohumano.findByDireccion", query = "SELECT r FROM Recursohumano r WHERE r.direccion = :direccion"),
    @NamedQuery(name = "Recursohumano.findByTelefonocasa", query = "SELECT r FROM Recursohumano r WHERE r.telefonocasa = :telefonocasa"),
    @NamedQuery(name = "Recursohumano.findByTelefonomovil", query = "SELECT r FROM Recursohumano r WHERE r.telefonomovil = :telefonomovil"),
    @NamedQuery(name = "Recursohumano.findByTelefonorecados", query = "SELECT r FROM Recursohumano r WHERE r.telefonorecados = :telefonorecados"),
    @NamedQuery(name = "Recursohumano.findByCorreoelectronico", query = "SELECT r FROM Recursohumano r WHERE r.correoelectronico = :correoelectronico"),
    @NamedQuery(name = "Recursohumano.findByCurp", query = "SELECT r FROM Recursohumano r WHERE r.curp = :curp"),
    @NamedQuery(name = "Recursohumano.findByRfc", query = "SELECT r FROM Recursohumano r WHERE r.rfc = :rfc"),
    @NamedQuery(name = "Recursohumano.findByTitulo", query = "SELECT r FROM Recursohumano r WHERE r.titulo = :titulo"),
    @NamedQuery(name = "Recursohumano.findByAbreviaturatitulo", query = "SELECT r FROM Recursohumano r WHERE r.abreviaturatitulo = :abreviaturatitulo"),
    @NamedQuery(name = "Recursohumano.findByFechaingreso", query = "SELECT r FROM Recursohumano r WHERE r.fechaingreso = :fechaingreso"),
    @NamedQuery(name = "Recursohumano.findByUltimogradoestudios", query = "SELECT r FROM Recursohumano r WHERE r.ultimogradoestudios = :ultimogradoestudios")})
public class Recursohumano implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrecursohumano")
    private Integer idrecursohumano;
    @Basic(optional = false)
    @Column(name = "apellidopaterno")
    private String apellidopaterno;
    @Column(name = "apellidomaterno")
    private String apellidomaterno;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "fechanacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "estadocivil")
    private String estadocivil;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefonocasa")
    private String telefonocasa;
    @Column(name = "telefonomovil")
    private String telefonomovil;
    @Column(name = "telefonorecados")
    private String telefonorecados;
    @Column(name = "correoelectronico")
    private String correoelectronico;
    @Column(name = "curp")
    private String curp;
    @Column(name = "rfc")
    private String rfc;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "abreviaturatitulo")
    private String abreviaturatitulo;
    @Column(name = "fechaingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaingreso;
    @Column(name = "ultimogradoestudios")
    private String ultimogradoestudios;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idrecursohumano")
    private List<Administrativo> administrativoList;
    @JoinColumn(name = "idplantel", referencedColumnName = "idplantel")
    @ManyToOne
    private Plantel idplantel;
    @OneToMany(mappedBy = "idrecursohumano")
    private List<Asesor> asesorList;

    public Recursohumano() {
    }

    public Recursohumano(Integer idrecursohumano) {
        this.idrecursohumano = idrecursohumano;
    }

    public Recursohumano(Integer idrecursohumano, String apellidopaterno) {
        this.idrecursohumano = idrecursohumano;
        this.apellidopaterno = apellidopaterno;
    }

    public Integer getIdrecursohumano() {
        return idrecursohumano;
    }

    public void setIdrecursohumano(Integer idrecursohumano) {
        this.idrecursohumano = idrecursohumano;
    }

    public String getApellidopaterno() {
        return apellidopaterno;
    }

    public void setApellidopaterno(String apellidopaterno) {
        this.apellidopaterno = apellidopaterno;
    }

    public String getApellidomaterno() {
        return apellidomaterno;
    }

    public void setApellidomaterno(String apellidomaterno) {
        this.apellidomaterno = apellidomaterno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonocasa() {
        return telefonocasa;
    }

    public void setTelefonocasa(String telefonocasa) {
        this.telefonocasa = telefonocasa;
    }

    public String getTelefonomovil() {
        return telefonomovil;
    }

    public void setTelefonomovil(String telefonomovil) {
        this.telefonomovil = telefonomovil;
    }

    public String getTelefonorecados() {
        return telefonorecados;
    }

    public void setTelefonorecados(String telefonorecados) {
        this.telefonorecados = telefonorecados;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAbreviaturatitulo() {
        return abreviaturatitulo;
    }

    public void setAbreviaturatitulo(String abreviaturatitulo) {
        this.abreviaturatitulo = abreviaturatitulo;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public String getUltimogradoestudios() {
        return ultimogradoestudios;
    }

    public void setUltimogradoestudios(String ultimogradoestudios) {
        this.ultimogradoestudios = ultimogradoestudios;
    }

    public List<Administrativo> getAdministrativoList() {
        return administrativoList;
    }

    public void setAdministrativoList(List<Administrativo> administrativoList) {
        this.administrativoList = administrativoList;
    }

    public Plantel getIdplantel() {
        return idplantel;
    }

    public void setIdplantel(Plantel idplantel) {
        this.idplantel = idplantel;
    }

    public List<Asesor> getAsesorList() {
        return asesorList;
    }

    public void setAsesorList(List<Asesor> asesorList) {
        this.asesorList = asesorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrecursohumano != null ? idrecursohumano.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recursohumano)) {
            return false;
        }
        Recursohumano other = (Recursohumano) object;
        if ((this.idrecursohumano == null && other.idrecursohumano != null) || (this.idrecursohumano != null && !this.idrecursohumano.equals(other.idrecursohumano))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Recursohumano[ idrecursohumano=" + idrecursohumano + " ]";
    }
    
}
